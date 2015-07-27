/*******************************************************************************
 * Copyright © 2015 Hoffmann-La Roche
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.roche.rogetmf.client.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.roche.rogetmf.client.callback.SearchConfigMenuEntriesCallback;
import com.roche.rogetmf.client.model.MenuEntry;
import com.roche.rogetmf.client.model.SearchTreeModel;
import com.roche.rogetmf.shared.oah.OpenAjaxHub;
import com.roche.rogetmf.shared.oah.OpenAjaxMessage;
import com.roche.rogetmf.shared.oah.OpenAjaxMessageFactory;
import com.roche.rogetmf.shared.oah.ServiceCallback;

public class SearchMenuEntriesService implements ISearchMenuEntriesService {

	private final Logger logger = Logger.getLogger(getClass().getName());

	private ISearchService searchService;
	private OpenAjaxHub openAjaxHub;

	public SearchMenuEntriesService(ISearchService searchService, OpenAjaxHub openAjaxHub) {
		logger.log(Level.FINE, "Creating SearchMenuEntriesService");
		this.searchService = searchService;
		this.openAjaxHub = openAjaxHub;
	}

	public void getMenuEntries(SearchTreeModel stm) {

		logger.log(Level.FINE, "Calling SearchMenuEntriesService.getMenuEntries itemId=" + stm.getId() + " type=" + stm.getType());

		SearchConfigMenuEntriesCallback callback = new SearchConfigMenuEntriesCallback(searchService);
		callback.setSearchTreeModel(stm);
		getSearchConfigMenuEntries(stm.getId(), callback);
	}

	public void executeSearchMenuAction(MenuEntry menuEntry, String id, String type) {
		String action = menuEntry.getAction();

		logger.log(Level.FINE, "Calling SearchMenuEntriesService.executeSearchMenuAction action=" + action + " id=" + id + " type=" + type);

		if (action.equals(RUN_SEARCH)) {

			searchService.handleRunSearch(id, type);

		} else if (action.equals(SHOW_SEARCH)) {

			String searchId = "";

			if (id.equals(SearchService.NODE_LAST_SEARCH)) {
				searchId = menuEntry.getId();
			} else {
				searchId = id;
			}

			if (type.equals("d2c_query_form") || type.equals("d2_queryform_config") || type.equals("rog_query_form") || type.equals("rog_queryform_config")) {
				searchService.handleRunSearch(searchId, type);
			} else {
				searchService.runAdvancedSearch(searchId);
			}

		} else if (menuEntry.getAction().equals(QUERY_FORM_PARAMETERS)) {
			searchService.displayLastSearchScreen();
		} else if (action.equals(DELETE_SEARCH)) {
			searchService.deleteSearchCriteria(id);
		}

	}

	private void getSearchConfigMenuEntries(String id, ServiceCallback<OpenAjaxMessage> callback) {
		logger.log(Level.FINE, "Calling SearchMenuEntriesService.getSearchConfigMenuEntries - id: " + id);

		OpenAjaxMessage message = OpenAjaxMessageFactory.create();
		message.setParameter("node_id", id);

		openAjaxHub.callService("SearchMenuEntriesService", "getSearchConfigMenuEntries", message, callback);
	}

}

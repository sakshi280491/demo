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

import com.roche.rogetmf.client.model.MenuEntry;
import com.roche.rogetmf.client.model.SearchTreeModel;

public interface ISearchMenuEntriesService {

	String RUN_SEARCH = "RUN_SEARCH";
	
	String SHOW_SEARCH = "SHOW_SEARCH";
	
	String DELETE_SEARCH = "DELETE_SEARCH";
	
	String QUERY_FORM_PARAMETERS = "QUERY_FORM_PARAMETERS";
	
	String CONFIG_RUN_SEARCH_ID = "menuContextTreeNodeRun";
	
	String CONFIG_QUERY_FORM_CRITERIA_ID = "menuContextTreeNodeSearchParameters";
	
	String CONFIG_SEARCH_FORM_CRITERIA_ID = "menuContextTreeNodeSearchFormParameters";
	
	String CONFIG_DELETE_SEARCH_ID = "menuContextDestroy";

	void getMenuEntries(SearchTreeModel searchTreeModel);
	
	void executeSearchMenuAction(MenuEntry menuEntry, String id, String type);
	
}

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
package com.roche.rogetmf.client;

import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.extjs.gxt.ui.client.data.DataProxy;
import com.extjs.gxt.ui.client.data.DataReader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.TreeModel;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.roche.rogetmf.client.model.SearchTreeModel;
import com.roche.rogetmf.client.model.TreeModelType;
import com.roche.rogetmf.client.service.IBrowserContentService;
import com.roche.rogetmf.client.service.ISearchMenuEntriesService;
import com.roche.rogetmf.client.service.SearchService;
import com.roche.rogetmf.shared.oah.OpenAjaxMessage;
import com.roche.rogetmf.shared.oah.ServiceCallback;

/**
 * Data proxy used to communicate with BrowserContentService over OAH
 * 
 * @author Krzysztof Jurkowski
 *
 * @param <D>
 */
public class JsonSearchTreeDataProxy<D> implements DataProxy<D> {

	private final Logger logger = Logger.getLogger(getClass().getName());

	private final TreeModelType treeModelType;

	private final IBrowserContentService browserContentService;

	private final ISearchMenuEntriesService searchMenuEntriesService;

	private static final String INTERNAL_SEARCH_OBJECT_PREFIX = "ROG_";

	private static final String FREEMARKER_PS_PREFIX = "FREEMARKER:";

	/**
	 * Creates an instance of a proxy, using custom {@link com.roche.rogetmf.client.model.TreeModelType}
	 * 
	 * @param treeModelType
	 *            Describes the model of tree nodes stored as JSON
	 * @param browserContentService
	 *            Service providing JSON data with the child nodes of a given parent
	 */
	public JsonSearchTreeDataProxy(TreeModelType treeModelType, IBrowserContentService browserContentService, ISearchMenuEntriesService searchMenuEntriesService) {
		logger.log(Level.FINE, "Creating JsonSearchTreeDataProxy");

		this.browserContentService = browserContentService;
		this.searchMenuEntriesService = searchMenuEntriesService;
		this.treeModelType = treeModelType;
	}

	/**
	 * Loads child nodes for a given parent
	 */
	public void load(final DataReader<D> reader, final Object parent, final AsyncCallback<D> callback) {
		final String type, parentId;

		if (parent == null) {
			type = SearchService.NODE_SEARCH;
			parentId = SearchService.NODE_SEARCH;

			logger.log(Level.FINE, "JsonSearchTreeDataProxy::loadConfig: " + type);
		} else {
			SearchTreeModel parentModel = (SearchTreeModel) parent;
			type = parentModel.getType();
			parentId = parentModel.getId();

			logger.log(Level.FINE, "JsonSearchTreeDataProxy::loadConfig: " + parentModel.toString());
		}

		browserContentService.getBrowserContents(type, parentId, new ServiceCallback<OpenAjaxMessage>() {
			public void onSuccess(OpenAjaxMessage result) {
				logger.log(Level.FINE, "BrowserContentService.getBrowserContent::onSuccess(" + type + "): " + result.getValue());

				JsonTreeReader<TreeModel> treeReader = new JsonTreeReader<TreeModel>(treeModelType);
				TreeModel treeModel = treeReader.read(parent, result.getValue());

				if (type.equalsIgnoreCase(SearchService.NODE_SAVED_SEARCHES)) {
					// for Saved searches, exclude internal searches named ROG_*
					for (Iterator<ModelData> iterator = treeModel.getChildren().iterator(); iterator.hasNext();) {
						SearchTreeModel node = (SearchTreeModel) iterator.next();

						if (node.getLabel().startsWith(INTERNAL_SEARCH_OBJECT_PREFIX)) {
							iterator.remove();
						}
					}
				}

				if (type.equalsIgnoreCase(SearchService.NODE_SAVED_PUBLIC_SEARCHES) || type.equalsIgnoreCase(SearchTreeModel.TYPE_D2C_QUERY_CATEGORY)) {
					// for Public searches, we have to differentiate OOTB D2 Query forms from custom FreeMarker searches
					// FreeMarker enabled searches have FREEMARKER: prefix in label - we need to remove it and append it to type
					for (Iterator<ModelData> iterator = treeModel.getChildren().iterator(); iterator.hasNext();) {
						SearchTreeModel searchNode = (SearchTreeModel) iterator.next();

						if (searchNode.getLabel().startsWith(FREEMARKER_PS_PREFIX)) {
							searchNode.setLabel(searchNode.getLabel().replace(FREEMARKER_PS_PREFIX, "").trim());
							searchNode.setType(searchNode.getType().replace("d2_", "rog_"));

							logger.log(Level.FINE, "Found FREEMARKER: prefix in Query form: " + searchNode.toString());
						}
					}
				}

				for (Iterator<ModelData> iterator = treeModel.getChildren().iterator(); iterator.hasNext();) {
					SearchTreeModel node = (SearchTreeModel) iterator.next();
					searchMenuEntriesService.getMenuEntries(node);
					node.setParent((SearchTreeModel) parent);
					// node.setMenuEntries(menuEntries);
				}

				try {
					callback.onSuccess(reader.read(parent, treeModel));
				} catch (Exception e) {
					logger.log(Level.WARNING, "BrowserContentService.getBrowserContent::onSuccess Exception: " + e.getMessage());
					callback.onFailure(e);
				}
			}

			public void onFailure(Throwable caught) {
				logger.log(Level.WARNING, "BrowserContentService.getBrowserContent::onFailure: " + caught.getMessage());
				callback.onFailure(caught);
			}
		});
	}

}

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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.extjs.gxt.ui.client.data.LoadEvent;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.ModelIconProvider;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.LoadListener;
import com.extjs.gxt.ui.client.event.TreePanelEvent;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.roche.rogetmf.client.model.SearchTreeModel;
import com.roche.rogetmf.client.service.ISearchMenuEntriesService;
import com.roche.rogetmf.client.service.ISearchService;
import com.roche.rogetmf.client.service.SearchService;
import com.roche.rogetmf.shared.oah.OpenAjaxHub;
import com.roche.rogetmf.shared.oah.OpenAjaxMessage;

public class SearchTree extends TreePanel<ModelData> {
	private final Logger logger = Logger.getLogger(getClass().getName());

	@SuppressWarnings("unused")
	private final OpenAjaxHub openAjaxHub;

	private final ISearchService searchService;

	private final ISearchMenuEntriesService menuEntriesService;

	private final SearchTreeModel rootNode;

	private ArrayList<String> nodesExpanedAfterReload = new ArrayList<String>();

	private DoubleClickTimer dblClickTimer;

	public SearchTree(TreeStore<ModelData> store, String displayProperty, OpenAjaxHub openAjaxHub, ISearchService searchService, SearchTreeModel rootNode,
			ISearchMenuEntriesService menuEntrService) {

		super(store);

		this.openAjaxHub = openAjaxHub;
		this.searchService = searchService;
		this.rootNode = rootNode;
		this.menuEntriesService = menuEntrService;

		logger.log(Level.FINE, "Creating Public Searches tree");

		setDisplayProperty(displayProperty);

		// double click timer will block consecutive if they occur faster than with 2000ms delay
		dblClickTimer = new DoubleClickTimer(2000);

		setIconProvider(new ModelIconProvider<ModelData>() {
			public AbstractImagePrototype getIcon(ModelData model) {
				SearchTreeModel node = (SearchTreeModel) model;
				String nodeType = node.getType();

				// in case when root node is auto expanded on widget load - display progress animation
				if (node.isNodeLoading())
					return IconHelper.createPath("resources/images/default/tree/loading.gif");

				if (nodeType.equalsIgnoreCase("node_search"))
					return IconHelper.createPath("resources/themes/slate/images/slate/special/node_saved_searches_16.png");
				else if (nodeType.equalsIgnoreCase("d2c_query_category"))
					return IconHelper.createPath("resources/themes/slate/images/slate/special/node_saved_searches_16.png");
				else if (nodeType.equalsIgnoreCase("node_saved_searches"))
					return IconHelper.createPath("resources/themes/slate/images/slate/special/node_saved_searches_16.png");

				else if (nodeType.equalsIgnoreCase("d2c_query"))
					return IconHelper.createPath("resources/themes/slate/images/slate/special/d2c_query_16.png");
				else if (nodeType.equalsIgnoreCase("d2c_query_dql"))
					return IconHelper.createPath("resources/themes/slate/images/slate/special/d2c_query_dql_wizard_16.png");
				else if (nodeType.equalsIgnoreCase("d2c_query_wizard"))
					return IconHelper.createPath("resources/themes/slate/images/slate/special/d2c_query_wizard_16.png");

				else if (nodeType.equalsIgnoreCase("node_last_search"))
					return IconHelper.createPath("resources/themes/slate/images/slate/special/node_last_search_16.png");
				else if (nodeType.equalsIgnoreCase("node_saved_public_searches"))
					return IconHelper.createPath("resources/themes/slate/images/slate/special/node_saved_public_searches_16.png");
				else
					return IconHelper.createPath("resources/themes/slate/images/slate/special/d2_queryform_config_16.png");
			}
		});

		addListener(Events.OnClick, new Listener<TreePanelEvent<ModelData>>() {
			public void handleEvent(TreePanelEvent<ModelData> event) {
				SearchTreeModel selectedNode = (SearchTreeModel) event.getItem();
				if (selectedNode.isLeaf()) {

					// notify double click blocker about the click
					dblClickTimer.handleClick(event);

					if (dblClickTimer.isSingleClicked()) {
						logger.log(Level.FINE, "Selected node: " + selectedNode.toString());
						handleSearchSelection(selectedNode);
					} else
						logger.log(Level.FINEST, "Consecutive click blocked for node: " + selectedNode.toString());

				}
			};
		});

		// automatically expand root node when loaded and expand reloaded nodes
		getStore().getLoader().addLoadListener(new LoadListener() {
			@Override
			public void loaderLoad(LoadEvent loadEvent) {
				logger.log(Level.FINER, "Tree load event");

				ModelData treeNode = loadEvent.getConfig();
				if (treeNode != null) {
					SearchTreeModel searchNode = (SearchTreeModel) treeNode;
					logger.log(Level.FINER, "Loaded node: " + searchNode.toString());

					searchNode.setNodeLoading(false);
					String type = searchNode.getType();
					if (SearchService.NODE_SEARCH.equalsIgnoreCase(type))
						setExpanded(getRootNode(), true);
					else {
						for (Iterator<String> iterator = nodesExpanedAfterReload.iterator(); iterator.hasNext();) {
							String typeToExpand = iterator.next();
							if (typeToExpand.equalsIgnoreCase(type)) {
								setExpanded(searchNode, true);
								iterator.remove();
							}
						}
					}
				}
			}

		});

		logger.log(Level.FINE, "Subscribing D2_EVENT_SEARCH_RUN");
		openAjaxHub.subscribe("D2_EVENT_SEARCH_RUN", new AsyncCallback<OpenAjaxMessage>() {
			public void onSuccess(OpenAjaxMessage result) {
				logger.log(Level.INFO, "D2_EVENT_SEARCH_RUN::onSuccess");

				ModelData treeNode = getStore().findModel("id", SearchService.NODE_LAST_SEARCH);

				if (treeNode != null) {
					SearchTreeModel lastSearchNode = (SearchTreeModel) treeNode;
					logger.log(Level.INFO, "Refreshing node: " + lastSearchNode.toString());
					menuEntriesService.getMenuEntries(lastSearchNode);
				}

			}

			public void onFailure(Throwable caught) {
				logger.log(Level.WARNING, "D2_EVENT_SEARCH_RUN::onFailure: " + caught.getMessage());
			}
		});

		logger.log(Level.FINE, "Subscribing D2_EVENT_SEARCH_SAVED");
		openAjaxHub.subscribe("D2_EVENT_SEARCH_SAVED", new AsyncCallback<OpenAjaxMessage>() {
			public void onSuccess(OpenAjaxMessage result) {
				logger.log(Level.INFO, "D2_EVENT_SEARCH_SAVED::onSuccess");

				boolean is_public = Boolean.parseBoolean(result.getParameter("is_public"));
				ModelData treeNode = getStore().findModel("type", is_public ? "node_saved_public_searches" : "node_saved_searches");
				if (treeNode != null) {
					SearchTreeModel searchNode = (SearchTreeModel) treeNode;
					logger.log(Level.INFO, "Refreshing node: " + searchNode.toString());
					reloadNode(searchNode, true);

				} else {
					logger.log(Level.INFO, "Can find node " + (is_public ? "node_saved_public_searches" : "node_saved_searches"));
				}

			}

			public void onFailure(Throwable caught) {
				logger.log(Level.WARNING, "D2_EVENT_SEARCH_SAVED::onFailure: " + caught.getMessage());
			}
		});

		logger.log(Level.FINE, "Subscribing D2_EVENT_OBJECT_DESTROYED");
		openAjaxHub.subscribe("D2_EVENT_OBJECT_DESTROYED", new AsyncCallback<OpenAjaxMessage>() {
			public void onSuccess(OpenAjaxMessage result) {
				logger.log(Level.INFO, "D2_EVENT_OBJECT_DESTROYED::onSuccess");

				String id = result.getParameter("oam_id");
				ModelData treeNode = getStore().findModel("id", id);
				if (treeNode != null) {
					SearchTreeModel searchNode = (SearchTreeModel) treeNode;
					SearchTreeModel parentNode = (SearchTreeModel) searchNode.getParent();
					logger.log(Level.INFO, "Refreshing node: " + parentNode.toString());

					reloadNode(parentNode, true);

				}

				treeNode = getStore().findModel("id", SearchService.NODE_LAST_SEARCH);

				if (treeNode != null) {
					SearchTreeModel lastSearchNode = (SearchTreeModel) treeNode;
					logger.log(Level.INFO, "Refreshing node: " + lastSearchNode.toString());
					menuEntriesService.getMenuEntries(lastSearchNode);
				}

			}

			public void onFailure(Throwable caught) {
				logger.log(Level.WARNING, "D2_EVENT_OBJECT_DESTROYED::onFailure: " + caught.getMessage());
			}
		});

	}

	public void handleSearchSelection(SearchTreeModel nodeItem) {
		searchService.handleRunSearch(nodeItem.getId(), nodeItem.getType());
	}

	public void reloadNode(SearchTreeModel node, boolean expand) {
		if (expand)
			nodesExpanedAfterReload.add(node.getType());

		getSelectionModel().select(node, true);
		getStore().getLoader().loadChildren(node);
	}

	/**
	 * @return the rootNode
	 */
	public SearchTreeModel getRootNode() {
		return rootNode;
	}
}

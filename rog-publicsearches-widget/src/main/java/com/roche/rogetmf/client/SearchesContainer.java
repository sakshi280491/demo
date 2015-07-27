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

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.data.BaseTreeLoader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.TreeLoader;
import com.extjs.gxt.ui.client.data.TreeModelReader;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.menu.SeparatorMenuItem;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.event.logical.shared.AttachEvent.Handler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.roche.rogetmf.client.model.MenuEntry;
import com.roche.rogetmf.client.model.SearchTreeModel;
import com.roche.rogetmf.client.model.SearchTreeModelType;
import com.roche.rogetmf.client.model.TreeModelType;
import com.roche.rogetmf.client.service.BrowserContentService;
import com.roche.rogetmf.client.service.IBrowserContentService;
import com.roche.rogetmf.client.service.ISearchMenuEntriesService;
import com.roche.rogetmf.client.service.ISearchService;
import com.roche.rogetmf.client.service.SearchMenuEntriesService;
import com.roche.rogetmf.client.service.SearchService;
import com.roche.rogetmf.shared.oah.OpenAjaxHub;
import com.roche.rogetmf.shared.oah.OpenAjaxMessage;

/**
 * Widget container with Public searches tree and Quick Searches toolbar
 * 
 * @author Krzysztof Jurkowski
 *
 */
public class SearchesContainer extends Viewport {
	private final Logger logger = Logger.getLogger(getClass().getName());

	private final OpenAjaxHub openAjaxHub;

	private TreeModelType treeModelType;

	private IBrowserContentService browserContentService;
	
	private ISearchMenuEntriesService searchMenuEntriesService;

	private ISearchService searchService;

	private TreeLoader<ModelData> loader;

	private TreeStore<ModelData> store;

	private TreePanel<ModelData> treePanel;

	private SearchToolbar searchToolbar;

	public SearchesContainer(OpenAjaxHub openAjaxHub) {
		super();
		setLayout(new BorderLayout());
		
		this.openAjaxHub = openAjaxHub;
		
		logger.log(Level.INFO, "Creating SearchesContainer");
		
		treeModelType = new SearchTreeModelType();

		browserContentService = new BrowserContentService(this.openAjaxHub);
		searchService = new SearchService(this.openAjaxHub);
		searchMenuEntriesService = new SearchMenuEntriesService(this.searchService,this.openAjaxHub);

		loader = new BaseTreeLoader<ModelData>(new JsonSearchTreeDataProxy<ModelData>(treeModelType, browserContentService,searchMenuEntriesService),
				new TreeModelReader<List<ModelData>>());

		logger.log(Level.FINE, "Creating Public search TreeStore");
		store = new TreeStore<ModelData>(loader);

		// add the default parent node
		logger.log(Level.FINE, "Creating Public search tree parent");
		final SearchTreeModel searchesParent = new SearchTreeModel(SearchService.NODE_SEARCH, "Searches", SearchService.NODE_SEARCH);
		searchesParent.setHasChildren(true);
		searchesParent.setNodeLoading(true);
		searchMenuEntriesService.getMenuEntries(searchesParent);
		//searchesParent.setMenuEntries(menuEntries);
		
		store.add(searchesParent, true);
		
		treePanel = new SearchTree(store, treeModelType.getDisplayProperty(), openAjaxHub, searchService, searchesParent,searchMenuEntriesService);
		
		searchToolbar = new SearchToolbar(this.openAjaxHub, searchService);

		ContentPanel panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);
		panel.setFrame(false);
		panel.setLayout(new FitLayout());
		panel.setTopComponent(searchToolbar);
		panel.setStyleName("rog-widget x3-widget");

		Handler attachHandler = new AttachEvent.Handler() {
			public void onAttachOrDetach(AttachEvent event) {
				logger.log(Level.FINE, "Search tree attached successfully");
				
				treePanel.getSelectionModel().select(searchesParent, true);
				loader.loadChildren(searchesParent);
			}
		};

		panel.addHandler(attachHandler, AttachEvent.getType());
		panel.add(treePanel);

		final Menu contextMenu = new Menu();
		contextMenu.setWidth(180);
		contextMenu.setMinWidth(180);
		contextMenu.setStyleName("x-tree-menu");
		//contextMenu.setAutoHeight(true);
		contextMenu.setEnableScrolling(false);
		
		
		treePanel.setContextMenu(contextMenu);
		treePanel.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		contextMenu.addListener(Events.BeforeShow, new Listener<MenuEvent>() {

			public void handleEvent(MenuEvent menuEvent) {
				SearchTreeModel selectedRow = (SearchTreeModel)treePanel.getSelectionModel().getSelectedItem();

				contextMenu.removeAll();
				
				for(final MenuEntry menu : selectedRow.getMenuEntries()) {
					logger.log(Level.FINE, "SearchesContainer MenuEntry: id=" + menu.getId() + ", name=" + menu.getName() + ", action=" + menu.getAction() + ", enabled=" + menu.getEnabled() + ", hidden=" + menu.getHidden());
					if(!menu.getHidden()) {
						MenuItem menuItem = new MenuItem();
						
						menuItem.setText(menu.getName());
						menuItem.setEnabled(menu.getEnabled());
						
						if(menu.getAction().equals(ISearchMenuEntriesService.RUN_SEARCH)){
							menuItem.setStyleName("x-tree-menu-item-default");
						}else{
							menuItem.setStyleName("x-tree-menu-item");	
						}
						
						menuItem.addSelectionListener(new SelectionListener<MenuEvent>() {

							public void componentSelected(MenuEvent menuEvent) {
								SearchTreeModel selectedRow = (SearchTreeModel)treePanel.getSelectionModel().getSelectedItem();
								searchMenuEntriesService.executeSearchMenuAction(menu, selectedRow.getId(), selectedRow.getType());
							};
						});
						
						if(menu.getAction().equals(ISearchMenuEntriesService.DELETE_SEARCH) && contextMenu.getItemCount()>0){
							contextMenu.add(new SeparatorMenuItem());
							
						}
						
						contextMenu.add(menuItem);
						
					}
				}
				
				logger.log(Level.FINE, "Menu.BeforeShow event, min width: " + contextMenu.getMinWidth() + ", width: " + contextMenu.getWidth() + ", style: " + contextMenu.getStyleName());
				
				// If no options are loaded dont show the menu
				if(contextMenu.getItemCount() == 0) {
					menuEvent.setCancelled(true);
				}

			}
		});
		
		BorderLayoutData centerData = new BorderLayoutData(LayoutRegion.CENTER);
		centerData.setMargins(new Margins(0, 0, 0, 0));
		centerData.setCollapsible(true);
		panel.syncSize();

		add(panel, centerData);
	}	
}

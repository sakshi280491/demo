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
package com.roche.rogetmf.client.callback;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;







import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.roche.rogetmf.client.model.MenuEntry;
import com.roche.rogetmf.client.model.SearchTreeModel;
import com.roche.rogetmf.client.service.ISearchMenuEntriesService;
import com.roche.rogetmf.client.service.ISearchService;
import com.roche.rogetmf.client.service.SearchService;
import com.roche.rogetmf.shared.oah.OpenAjaxMessage;
import com.roche.rogetmf.shared.oah.OpenAjaxMessageFactory;
import com.roche.rogetmf.shared.oah.ServiceCallback;

public class SearchConfigMenuEntriesCallback extends ServiceCallback<OpenAjaxMessage>{
	
	private final Logger logger = Logger.getLogger(getClass().getName());
	
	private SearchTreeModel searchTreeModel;

	private ISearchService searchService;
	
	public SearchConfigMenuEntriesCallback(ISearchService searchService) {
		this.searchService = searchService;
	}
	
	@Override
	public void onFailure(Throwable caught) {
		logger.log(Level.SEVERE,"SearchConfigMenuEntriesCallback.onFailure error:" + caught.getMessage());
	}

	@Override
	public void onSuccess(OpenAjaxMessage result) {
		logger.log(Level.FINE,"SearchConfigMenuEntriesCallback.onSuccess nodeId: " + searchTreeModel.getId() + " menu request result:" +  result.getValue());
		List<MenuEntry> configMenuEntries = new ArrayList<MenuEntry>();
		JSONArray nodes = (JSONArray) JSONParser.parseLenient((String) result.getValue());
		
		//JSONArray nodes = (JSONArray) rootNode.get(modelType.getRoot());
		
		for(int i=0;i<nodes.size();i++){
			
			JSONObject jsonObject = (JSONObject)nodes.get(i);
			MenuEntry configMenuEntry = getConfigMenuEntry(jsonObject);
			
			configMenuEntries.add(configMenuEntry);
			
			logger.log(Level.FINE,"SearchConfigMenuEntriesCallback.onSuccess nodeId: " + searchTreeModel.getId() + " config menu: id=" + configMenuEntry.getId() + ", enabled=" + configMenuEntry.getEnabled() + ", hidden=" + configMenuEntry.getHidden());
			
			List<MenuEntry> menuEntries = getMenuEntries(configMenuEntries);
			searchTreeModel.setMenuEntries(menuEntries);
			
		}
	}
	
	private List<MenuEntry> getMenuEntries(List<MenuEntry> configMenuEntries){
		
		final List<MenuEntry> list = new ArrayList<MenuEntry>();
		
		if(searchTreeModel.getType().equals(SearchService.NODE_SAVED_PUBLIC_SEARCHES)){
			
			MenuEntry me = getDeleteMenuEntry(configMenuEntries);
			list.add(me);
			
		}else if(searchTreeModel.getType().equals(SearchService.NODE_SAVED_SEARCHES)){
			
			MenuEntry me = getDeleteMenuEntry(configMenuEntries);
			list.add(me);
			
		}else if(searchTreeModel.getType().equals(SearchService.NODE_SEARCH)){
			
			MenuEntry me = getDeleteMenuEntry(configMenuEntries);
			list.add(me);
			
		}else if(searchTreeModel.getType().equals(SearchTreeModel.TYPE_D2C_QUERY_CATEGORY)){
			
			MenuEntry me = getDeleteMenuEntry(configMenuEntries);
			list.add(me);
			
		}else if(searchTreeModel.getType().equals(SearchTreeModel.TYPE_NODE_LAST_SEARCH)){
			
			MenuEntry me = new MenuEntry();
			me.setAction(ISearchMenuEntriesService.RUN_SEARCH);
			me.setName("Run");
			me.setEnabled(true);
			me.setHidden(false);
			list.add(me);
			
			searchService.getLastQueryDqlObjectData(new ServiceCallback<OpenAjaxMessage>() {
				@Override
				public void onSuccess(OpenAjaxMessage result) {
					
					String queryDqlObjectId = result.getParameter("r_object_id");
					String queryDqlObjectType = result.getParameter("r_object_type");

					logger.log(Level.INFO, "Retrieving last search parameters: " + queryDqlObjectId + ", " + queryDqlObjectType);

					if (SearchService.D2C_QUERY_FORM.equalsIgnoreCase(queryDqlObjectType)  || SearchService.D2C_QUERY_DQL.equalsIgnoreCase(queryDqlObjectType)) {
						
						MenuEntry me = new MenuEntry();
						me.setAction(ISearchMenuEntriesService.QUERY_FORM_PARAMETERS);
						me.setName("Query form parameters");
						me.setEnabled(true);
						me.setHidden(false);
						me.setId(queryDqlObjectId);
						
						list.add(me);
						
					}
					else if (SearchService.D2C_QUERY.equalsIgnoreCase(queryDqlObjectType)) {
						
						MenuEntry me = new MenuEntry();
						me.setAction(ISearchMenuEntriesService.SHOW_SEARCH);
						me.setName("Search criteria");
						me.setEnabled(true);
						me.setHidden(false);
						me.setId(queryDqlObjectId);
						
						list.add(me);
					}
					else {
						logger.log(Level.SEVERE, "Error while retrieving last search parameters: " + queryDqlObjectId + ", " + queryDqlObjectType);
					}
					
				}

				@Override
				public void onFailure(Throwable caught) {
					logger.log(Level.WARNING, "getLastQueryDqlObjectDataCallback::onFailure: " + caught.getMessage());
				}
			});
		/*	me = new MenuEntry();
			me.setAction(SHOW_SEARCH);
			me.setName("Search criteria");
			me.setEnabled(true);
			me.setHidden(false);
			list.add(me);*/
			
		}else if(searchTreeModel.getType().equals("d2c_query_form") || searchTreeModel.getType().equals("d2_queryform_config") || searchTreeModel.getType().equals("rog_query_form") || searchTreeModel.getType().equals("rog_queryform_config")){
			MenuEntry me = new MenuEntry();
			me.setAction(ISearchMenuEntriesService.RUN_SEARCH);
			me.setName("Run");
			me.setEnabled(true);
			me.setHidden(false);
			list.add(me);
			
			me = getDeleteMenuEntry(configMenuEntries);
			list.add(me);
			
		}else{
			
			MenuEntry me = new MenuEntry();
			me.setAction(ISearchMenuEntriesService.RUN_SEARCH);
			me.setName("Run");
			me.setEnabled(true);
			me.setHidden(false);
			list.add(me);
			
			me = new MenuEntry();
			me.setAction(ISearchMenuEntriesService.SHOW_SEARCH);
			me.setName("Search criteria");
			me.setEnabled(true);
			me.setHidden(false);
			list.add(me);
			
			me = getDeleteMenuEntry(configMenuEntries);
			list.add(me);
			
		}
		
		return list;
	}

	private MenuEntry getDeleteMenuEntry(List<MenuEntry> configMenuEntries){
		
		MenuEntry resultME = new MenuEntry();
		resultME.setAction(ISearchMenuEntriesService.DELETE_SEARCH);
		resultME.setName("Delete...");
		
		MenuEntry configME = null;
		
		for (MenuEntry menuEntry : configMenuEntries) {
			logger.log(Level.FINE, "SearchConfigMenuEntriesCallback.getDeleteMenuEntry MenuEntry: id=" + menuEntry.getId() + ", name=" + menuEntry.getName() + ", action=" + menuEntry.getAction() + ", enabled=" + menuEntry.getEnabled() + ", hidden=" + menuEntry.getHidden());
			
			if(menuEntry.getId().equals(ISearchMenuEntriesService.CONFIG_DELETE_SEARCH_ID)){
				configME = menuEntry;
			}
		}
		
		if(configME!=null){
			resultME.setEnabled(configME.getEnabled());
			resultME.setHidden(configME.getHidden());
		}else{
			resultME.setEnabled(false);
			resultME.setHidden(true);
		}
		
		return resultME;
		
	}
	
	private MenuEntry getConfigMenuEntry(JSONObject jsonObject){
		MenuEntry menuEntry = new MenuEntry();
		
		
		JSONString id = (JSONString)jsonObject.get("id");
		if(id!=null)
			menuEntry.setId(id.stringValue());
		
		JSONString name = (JSONString)jsonObject.get("name");
		if(name!=null)
			menuEntry.setName(name.stringValue());
		
		JSONString tooltip = (JSONString)jsonObject.get("tooltip");
		if(tooltip!=null)
			menuEntry.setTooltip(tooltip.stringValue());
		
		JSONString action = (JSONString)jsonObject.get("action");
		if(action!=null)
			menuEntry.setAction(action.stringValue());
		
		JSONBoolean disabled = (JSONBoolean)jsonObject.get("disabled");
		if(disabled!=null)
			menuEntry.setEnabled(!disabled.booleanValue());
		
		JSONBoolean hidden = (JSONBoolean)jsonObject.get("hidden");
		if(hidden!=null)
			menuEntry.setHidden(hidden.booleanValue());
		
		JSONBoolean _default = (JSONBoolean)jsonObject.get("_default");
		if(_default!=null)
			menuEntry.setIsDefault(!_default.booleanValue());
		
		return menuEntry;
	}
	
	public SearchTreeModel getSearchTreeModel() {
		return searchTreeModel;
	}

	public void setSearchTreeModel(SearchTreeModel searchTreeModel) {
		this.searchTreeModel = searchTreeModel;
	}
	
	
}

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

import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.roche.rogetmf.shared.oah.OpenAjaxHub;
import com.roche.rogetmf.shared.oah.OpenAjaxMessage;
import com.roche.rogetmf.shared.oah.OpenAjaxMessageFactory;
import com.roche.rogetmf.shared.oah.ServiceCallback;

public class SearchService implements ISearchService {
	private final Logger logger = Logger.getLogger(getClass().getName());

	private OpenAjaxHub openAjaxHub;

	public SearchService(OpenAjaxHub openAjaxHub) {
		logger.log(Level.FINE, "Creating SearchService");

		this.openAjaxHub = openAjaxHub;
		
		logger.log(Level.FINE, "Subscribing D2_EVENT_DIALOG_HIDE");
		
		openAjaxHub.subscribe("D2_EVENT_DIALOG_HIDE", new AsyncCallback<OpenAjaxMessage>() {
			public void onSuccess(OpenAjaxMessage result) {
				logger.log(Level.INFO, "showSearchScreenCallback::onSuccess");
				if(result.getParameter("callerManagerId")==null || !result.getParameter("callerManagerId").equals("DestroyManager")){
					runPreparedSearch();
				}
			}
			
			public void onFailure(Throwable caught) {
				logger.log(Level.WARNING, "showSearchScreenCallback::onFailure: " + caught.getMessage());
			}
		});
	}

	public void handleRunSearch(String id, String type) {

		if (type != null && !type.equals("d2c_query_category")) {
			if (type.equals("node_last_search")) {
				runLastSearch();
			} else if (type.equals("d2_facet_definition")) {
				String facet = id;
				String facetName = facet.split(SEPARATOR_VALUE)[1];

				resetFacetSearch(facet, facetName);
			} else if (type.equals("d2_facet_value")) {
				String facet = id;
				String[] facetParts = facet.split(SEPARATOR_VALUE);
				String facetName = facetParts[1];
				String facetValue = facetParts[2];

				runFacetSearch(facet, facetName, facetValue);
			} else if (type.equals("d2c_query_form") || type.equals("d2_queryform_config")) {
				// OOTB D2 Query forms are executed regular way
				showQueryForm(id);
			} else if (type.equals("rog_query_form") || type.equals("rog_queryform_config")) {
				// Custom FreeMarker forms require custom flow of execution
				prepareSearch(id);
			} else if (type.equals("d2c_query_wizard")) {
				runAdvancedSearch(id);
			} else if (type.equals("d2c_query")) {
				runSearch(id, type);
			} else {
				runSearch(id, type);
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.roche.rogetmf.client.service.ISearchService#prepareSearch(java.lang.String)
	 */
	public void prepareSearch(String queryFormId) {
		logger.log(Level.INFO, "Preparing search for query form: " + queryFormId);

		prepareQueryDqlObject(queryFormId, new ServiceCallback<OpenAjaxMessage>() {
			@Override
			public void onSuccess(OpenAjaxMessage result) {
				String queryDqlObjectId = result.getValue();
				String queryFormObjectId = result.getParameter("query_form_object_id");

				logger.log(Level.FINE, "Search object " + queryDqlObjectId + " prepared for Query form: " + queryFormObjectId);

				if (queryFormObjectId != null && queryDqlObjectId != null)
					displaySearchScreen(queryFormObjectId);
			}

			@Override
			public void onFailure(Throwable caught) {
				logger.log(Level.WARNING, "prepareSearch::onFailure: " + caught.getMessage());
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.roche.rogetmf.client.service.ISearchService#runPreparedSearch()
	 */
	public void runPreparedSearch() {
		logger.log(Level.FINE, "Retiving Id of prepared search");

		getQueryDqlObject(new ServiceCallback<OpenAjaxMessage>() {
			@Override
			public void onSuccess(OpenAjaxMessage result) {
				String queryDqlObjectId = result.getValue();

				logger.log(Level.INFO, "Running prepared Public search: " + queryDqlObjectId);

				if (queryDqlObjectId != null && queryDqlObjectId != DF_NULL_ID)
					runSearch(queryDqlObjectId, D2C_QUERY_DQL);
			}

			@Override
			public void onFailure(Throwable caught) {
				logger.log(Level.WARNING, "getQueryDqlCallback::onFailure: " + caught.getMessage());
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.roche.rogetmf.client.service.ISearchService#runLastSearch()
	 */
	public void runLastSearch() {
		logger.log(Level.FINE, "Retiving data of last executed search");

		getLastQueryDqlObjectData(new ServiceCallback<OpenAjaxMessage>() {
			@Override
			public void onSuccess(OpenAjaxMessage result) {
				String queryDqlObjectId = result.getParameter("r_object_id");
				String queryDqlObjectType = result.getParameter("r_object_type");

				logger.log(Level.INFO, "Running last search: " + queryDqlObjectId + ", " + queryDqlObjectType);

				if (D2C_QUERY_FORM.equalsIgnoreCase(queryDqlObjectType))
					runSearch("node_last_search", null);
				else if (queryDqlObjectId != null)
					runSearch(queryDqlObjectId, queryDqlObjectType);
			}

			@Override
			public void onFailure(Throwable caught) {
				logger.log(Level.WARNING, "getLastQueryDqlObjectDataCallback::onFailure: " + caught.getMessage());
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.roche.rogetmf.client.service.ISearchService#runQuickSearch(java.lang.String)
	 */
	public void runCustomQuickSearch(String quickSearchTerm) {
		logger.log(Level.INFO, "SearchToolbar - Quick search term:" + quickSearchTerm);

		OpenAjaxMessage message = OpenAjaxMessageFactory.create();
		message.setParameter("quick_search_term", quickSearchTerm);
		openAjaxHub.callService("QueryDqlObjectService", "getQueryDqlObjectForQuickSearch", message,
				new ServiceCallback<OpenAjaxMessage>() {
					@Override
					public void onSuccess(OpenAjaxMessage result) {
						String queryDqlObjectId = result.getValue();
						logger.log(Level.INFO, "SearchToolbar::queryDqlObjectId: " + queryDqlObjectId);

						if (queryDqlObjectId != null && queryDqlObjectId != DF_NULL_ID)
							runSearch(queryDqlObjectId, D2C_QUERY_DQL);
					}

					@Override
					public void onFailure(Throwable caught) {
						logger.log(Level.WARNING, "SearchService::getQueryDqlObjectForQuickSearch::onFailure: " + caught.getMessage());

					}
				});

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.roche.rogetmf.client.service.ISearchService#showAdvancedSearch(java.lang.String)
	 */
	public void runAdvancedSearch(String advancedSearchId) {
		OpenAjaxMessage message = OpenAjaxMessageFactory.create();
		message.setId(advancedSearchId);
		openAjaxHub.send("D2_ACTION_ADVANCED_SEARCH", message);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.roche.rogetmf.client.service.ISearchService#showQueryForm(java.lang.String)
	 */
	public void showQueryForm(String queryFormId) {
		OpenAjaxMessage message = OpenAjaxMessageFactory.create();
		message.setId(queryFormId);

		openAjaxHub.send("D2_ACTION_QUERYFORM_SHOW", message);
		openAjaxHub.send("D2_EVENT_SELECT_QUERYFORM", message);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.roche.rogetmf.client.service.ISearchService#showAdvancedSearchCriteria(java.lang.String)
	 */
	public void showQueryFormParameters() {
		OpenAjaxMessage message = OpenAjaxMessageFactory.create();
		message.setParameter("widgetType", "SearchWidget");
		message.setParameter("MANAGER", "QueryFormManager");
		message.setParameter("type", NODE_LAST_SEARCH);
		message.setId(NODE_LAST_SEARCH);
		openAjaxHub.send("D2_ACTION_EXECUTE_MANAGER", message);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.roche.rogetmf.client.service.ISearchService#showAdvancedSearchCriteria(java.lang.String)
	 */
	public void showAdvancedSearchCriteria(String advancedSearchId) {
		OpenAjaxMessage message = OpenAjaxMessageFactory.create();
		message.setId(advancedSearchId);
		message.setParameter("widgetType", "SearchWidget");
		message.setParameter("MANAGER", "AdvancedSearchManager");
		message.setParameter("type", "d2c_query");
		openAjaxHub.send("D2_ACTION_EXECUTE_MANAGER", message);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.roche.rogetmf.client.service.ISearchService#showAdvancedSearchCriteria(java.lang.String)
	 */
	public void deleteSearchCriteria(String searchId) {
		OpenAjaxMessage message = OpenAjaxMessageFactory.create();
		message.setId(searchId);
		message.setParameter("MANAGER", "DestroyManager");
		openAjaxHub.send("D2_ACTION_EXECUTE_MANAGER", message);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.roche.rogetmf.client.service.ISearchService#runSearch(java.lang.String, java.lang.String)
	 */
	public void runSearch(String searchObjectId, String searchObjecType) {
		OpenAjaxMessage message = OpenAjaxMessageFactory.create();
		if (searchObjectId != null)
			message.setId(searchObjectId);

		if (searchObjecType != null)
			message.setParameter("searched_type", searchObjecType);

		openAjaxHub.send("D2_ACTION_SEARCH_DOCUMENT", message);
		openAjaxHub.send("D2_EVENT_SELECT_SEARCH", message);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.roche.rogetmf.client.service.ISearchService#runFacetSearch(java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	public void runFacetSearch(String facetObjectId, String facetName, String facetValue) {
		OpenAjaxMessage message = OpenAjaxMessageFactory.create();
		message.setId(facetObjectId);
		message.setParameter("facet_attr_name", facetName);
		message.setParameter("facet_attr_value", facetValue);

		openAjaxHub.send("D2_ACTION_SEARCH_DOCUMENT", message);
		openAjaxHub.send("D2_EVENT_SELECT_FACET", message);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.roche.rogetmf.client.service.ISearchService#resetFacetSearch(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void resetFacetSearch(String facetObjectId, String facetName) {
		OpenAjaxMessage message = OpenAjaxMessageFactory.create();
		message.setId(facetObjectId);
		message.setParameter("reset_facet_attr_name", facetName);

		openAjaxHub.send("D2_ACTION_SEARCH_DOCUMENT", message);
		openAjaxHub.send("D2_EVENT_SELECT_FACET", message);
	}

	private void prepareQueryDqlObject(String queryFormId, ServiceCallback<OpenAjaxMessage> callback) {
		logger.log(Level.INFO, "initQueryDqlObject");

		OpenAjaxMessage msg = OpenAjaxMessageFactory.create();
		msg.setId(queryFormId);

		openAjaxHub.callService("QueryDqlObjectService", "initQueryDqlObject", msg, callback);
	}

	private void displaySearchScreen(String queryFormId) {
		logger.log(Level.INFO, "showSearchScreen");

		OpenAjaxMessage message = OpenAjaxMessageFactory.create();
		message.setParameter("DIALOG_NAME", "RogPublicSearchDialog");
		message.setId(queryFormId);

		openAjaxHub.send("D2_ACTION_DISPLAY_DIALOG", message);
	}

	public void displayLastSearchScreen() {

		logger.log(Level.FINE, "Retiving data of last executed search");

		getLastQueryDqlObjectData("ROG_PUBLIC_SEARCH", new ServiceCallback<OpenAjaxMessage>() {
			@Override
			public void onSuccess(OpenAjaxMessage result) {
				String queryDqlObjectId = result.getParameter("r_object_id");
				String queryDqlObjectType = result.getParameter("r_object_type");

				logger.log(Level.INFO, "Displaying last search parameters: " + queryDqlObjectId + ", " + queryDqlObjectType);

				if (D2C_QUERY_FORM.equalsIgnoreCase(queryDqlObjectType)) {
					logger.log(Level.INFO, "Displaying last " + D2C_QUERY_FORM + " search parameters");
					showQueryFormParameters();
				} else if (D2C_QUERY_DQL.equalsIgnoreCase(queryDqlObjectType)) {
					logger.log(Level.INFO, "Displaying last " + D2C_QUERY_DQL + " search parameters");

					OpenAjaxMessage message = OpenAjaxMessageFactory.create();
					message.setParameter("DIALOG_NAME", "RogLastSearchDialog");
					message.setId(queryDqlObjectId);

					openAjaxHub.send("D2_ACTION_DISPLAY_DIALOG", message);
				} else {
					Info.display("Query form parameters", "Cannot find last search object");
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				logger.log(Level.WARNING, "getLastQueryDqlObjectDataCallback::onFailure: " + caught.getMessage());
			}
		});
	}

	private void getQueryDqlObject(ServiceCallback<OpenAjaxMessage> callback) {
		logger.log(Level.INFO, "getQueryDqlObject");

		openAjaxHub.callService("QueryDqlObjectService", "getQueryDqlObject", callback);
	}

	public void getLastQueryDqlObjectData(ServiceCallback<OpenAjaxMessage> callback) {

		logger.log(Level.INFO, "getLastQueryDqlObjectData");
		openAjaxHub.callService("QueryDqlObjectService", "getLastQueryDqlObjectData", callback);

	}

	public void getLastQueryDqlObjectData(String specialAppFilter, ServiceCallback<OpenAjaxMessage> callback) {

		logger.log(Level.INFO, "getLastQueryDqlObjectData");
		OpenAjaxMessage message = OpenAjaxMessageFactory.create();
		message.setParameter("a_special_app", specialAppFilter);
		openAjaxHub.callService("QueryDqlObjectService", "getLastQueryDqlObjectData", message, callback);

	}

}

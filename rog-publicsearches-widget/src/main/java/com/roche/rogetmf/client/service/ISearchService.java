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

import com.roche.rogetmf.shared.oah.OpenAjaxMessage;
import com.roche.rogetmf.shared.oah.ServiceCallback;

public interface ISearchService {
	
	public static final String D2C_QUERY_FORM = "d2c_query_form";

	public static final String D2C_QUERY_DQL = "d2c_query_dql";

	public static final String D2C_QUERY = "d2c_query";

	public static final String NODE_LAST_SEARCH = "node_last_search";

	public static final String NODE_SEARCH = "node_search";

	public static final String NODE_SAVED_SEARCHES = "node_saved_searches";

	public static final String NODE_SAVED_PUBLIC_SEARCHES = "node_saved_public_searches";

	public static final String DF_NULL_ID = "0000000000000000";

	public static String SEPARATOR_VALUE = "¬";

	/**
	 * Resolves proper search based on id and type of a search
	 * 
	 * @param id
	 *            Id of the search
	 * @param type
	 *            type of the search
	 * 
	 */
	public void handleRunSearch(String id, String type);

	/**
	 * Displays Advanced search dialog
	 * 
	 * @param advancedSearchId
	 *            Id of the Advanced search
	 */
	public void runAdvancedSearch(String advancedSearchId);

	/**
	 * Displays Query form search dialog This method should be used only for OOTB D2 Query forms
	 * 
	 * @param queryFormId
	 *            Id of the Query form
	 */
	public void showQueryForm(String queryFormId);

	/**
	 * Displays My last search - Query Form Parameters dialog
	 */
	public void showQueryFormParameters();

	/**
	 * Displays Advanced search criteria dialog
	 * 
	 * @param advancedSearchId
	 *            Id of the Advanced search
	 */
	@Deprecated
	public void showAdvancedSearchCriteria(String advancedSearchId);

	/**
	 * Displays Destroy search dialog
	 * 
	 * @param searchId
	 *            Id of the search
	 */
	public void deleteSearchCriteria(String searchId);

	/**
	 * Creates a temporary search object for a FreeMarker Query form This method has to be executed before Property page of the Query form
	 * is displayed
	 * 
	 * @param queryFormId
	 *            Id of the Query form
	 */
	public void prepareSearch(String queryFormId);

	/**
	 * Runs search from a prepared temporary search object
	 */
	public void runPreparedSearch();

	/**
	 * Executes search defined in a search object
	 * 
	 * @param searchObjectId
	 *            Id of the search object
	 * @param searchObjecType
	 *            Type of the search object
	 */
	public void runSearch(String searchObjectId, String searchObjecType);

	/**
	 * Executes last search
	 */
	public void runLastSearch();

	/**
	 * Executes custom Quick search defined in ROG_QUICK_SEARCH object
	 * 
	 * @param quickSearchTerm
	 *            Quick search term typed by a user
	 */
	public void runCustomQuickSearch(String quickSearchTerm);

	/**
	 * Executes facet search
	 * 
	 * @param facetObjectId
	 *            Id of the facet object
	 * @param facetName
	 *            Name of the facet
	 * @param facetValue
	 *            Value of the facet
	 */
	public void runFacetSearch(String facetObjectId, String facetName, String facetValue);

	/**
	 * Resets facet search
	 * 
	 * @param facetObjectId
	 *            Id of the facet object
	 * @param facetName
	 *            Name of the facet
	 */
	public void resetFacetSearch(String facetObjectId, String facetName);

	public void displayLastSearchScreen();

	public void getLastQueryDqlObjectData(String specialAppFilter, ServiceCallback<OpenAjaxMessage> callback);

	public void getLastQueryDqlObjectData(ServiceCallback<OpenAjaxMessage> callback);

}

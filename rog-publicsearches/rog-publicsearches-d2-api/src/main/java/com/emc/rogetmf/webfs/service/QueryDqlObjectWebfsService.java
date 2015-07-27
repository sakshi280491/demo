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
package com.emc.rogetmf.webfs.service;

import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.documentum.fc.client.DfQuery;
import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.IDfPersistentObject;
import com.documentum.fc.client.IDfQuery;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfSysObject;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfId;
import com.emc.common.java.utils.ArgumentNotFoundException;
import com.emc.common.java.utils.StringUtil;
import com.emc.d2.api.config.modules.search.ID2QueryFormConfig;
import com.emc.d2.api.config.search.D2cQueryDql;
import com.emc.d2.api.config.search.ID2cQuery;
import com.emc.d2.api.config.search.ID2cQueryDql;
import com.emc.d2fs.dctm.web.services.D2fsContext;
import com.emc.d2fs.exceptions.D2fsException;
import com.emc.d2fs.utils.AttributeUtils;
import com.emc.rogetmf.webfs.ui.assistance.DQLTemplateUtils;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Service providing methods used to initiate and process {@link com.emc.d2.api.config.search.D2cQueryDql} objects based on
 * {@link com.emc.d2.api.config.modules.search.D2QueryFormConfig} templates with FreeMarker syntax used in DQL
 * 
 * @see <a href="http://freemarker.org/">FreeMarker Java Template Engine - Overview</a>
 * @author Krzysztof Jurkowski
 *
 */
public class QueryDqlObjectWebfsService extends AbstractWebfsService {
	private Logger LOGGER = LoggerFactory.getLogger(getClass());

	/**
	 * States of the {@link com.emc.d2.api.config.search.D2cQueryDql} objects
	 *
	 */
	public enum QueryDqlObjectStatus {
		/**
		 * Object contains FreeMarker DQL template
		 */
		INITIALIZED("Initialized"),

		/**
		 * Object contains DQL, resulted form the FreeMarker DQL template and search parameters passed by the user
		 */
		PROCESSED("Processed"),

		/**
		 * Object which was used to a execute a search
		 */
		EXECUTED("Executed"),

		/**
		 * Object for which the search execution was canceled
		 */
		CANCELED("Canceled");

		private String statusValue;

		private QueryDqlObjectStatus(String statusValue) {
			this.statusValue = statusValue;
		}

		@Override
		public String toString() {
			return statusValue;
		}
	}

	// prefix for the value of object_name and a_special_app attributes of D2cQueryDql objects created from Public Searches templates
	public static final String PUBLIC_SEARECH_APP_NAME = "ROG_PUBLIC_SEARCH";

	// prefix for the value of object_name and a_special_app attributes of D2cQueryDql objects created from Quick Search template
	public static final String QUICK_SEARCH_APP_NAME = "ROG_QUICK_SEARCH";

	// value of the parameter for the search term in the DQL template for the Quick Search
	public static final String QUICK_SEARCH_TEMPLATE_PARAMETER = "quick_search_term";

	// name of the FreeMamarker template - used internal by StringTemplateLoader
	private static String DQL_TEMPLATE = "dqlTemplate";

	/**
	 * Creates an instance of the service
	 * 
	 * @param context
	 *            {@link com.emc.d2fs.models.context.Context} object containing informations about client/session
	 */
	public QueryDqlObjectWebfsService(D2fsContext context) {
		super(context);
	}

	/**
	 * Initializes a {@link com.emc.d2.api.config.search.D2cQueryDql} object for a given Public search. FreeMarker DQL template from the
	 * {@link com.emc.d2.api.config.modules.search.D2QueryFormConfig} Query form is copied to the DQL attribute of
	 * {@link com.emc.d2.api.config.search.D2cQueryDql} object. The {@link com.emc.d2.api.config.search.D2cQueryDql} object is reused
	 * afterwards, so that each user has no more than on {@link com.emc.d2.api.config.search.D2cQueryDql} object in the repository.
	 * 
	 * @param queryFormObject
	 *            Query form from which the FreeMarker DQL template is to be copied
	 * @return Object ID of the initialized {@link com.emc.d2.api.config.search.D2cQueryDql} object.
	 * @throws DfException
	 * @throws D2fsException
	 * @throws NullPointerException
	 *             Occurs if passed QueryFormConfig object is null
	 */
	public String initQueryDqlObject(IDfPersistentObject queryFormObject) throws DfException, D2fsException, NullPointerException {
		String queryDqlObjectId = DfId.DF_NULLID_STR;

		ID2QueryFormConfig queryFormConfig = (ID2QueryFormConfig) queryFormObject;

		// check if Query form is not null
		if (queryFormConfig != null) {

			// get the D2cQueryDql object used for Public searches by the session user
			ID2cQueryDql d2cQueryDqlObject = getPublicSearchQueryObject();

			if (d2cQueryDqlObject == null) {
				// if there is no object, create one for the session user
				IDfSession session = getSession();
				d2cQueryDqlObject = D2cQueryDql.newObject(session, PUBLIC_SEARECH_APP_NAME + "_" + session.getUser(null).getUserName());
				((IDfSysObject) d2cQueryDqlObject).setSpecialApp(PUBLIC_SEARECH_APP_NAME);
			}

			// reset the a_status of the object to initialized
			((IDfSysObject) d2cQueryDqlObject).setStatus(QueryDqlObjectStatus.INITIALIZED.toString());
			d2cQueryDqlObject.setDql(queryFormConfig.getDqlValue());
			d2cQueryDqlObject.setHasMandatoryAttributes(queryFormConfig.hasMandatoryAttributesHidden());
			d2cQueryDqlObject.save();

			// return the id of the initialized object
			queryDqlObjectId = d2cQueryDqlObject.getObjectId().getId();
		} else {
			String exceptionMessage = "QueryFormConfig object is null";
			LOGGER.error(exceptionMessage);
			throw new NullPointerException(exceptionMessage);
		}

		return queryDqlObjectId;
	}

	/**
	 * Processes the FreeMarker DQL template stored in the initialized {@link com.emc.d2.api.config.search.D2cQueryDql} object. Resulting
	 * DQL is saved back in the {@link com.emc.d2.api.config.search.D2cQueryDql} object.
	 * 
	 * @param attributesHashtable
	 *            Contains pairs - the name of the parameter in the FreeMarker DQL template and the value of the parameter provided by the
	 *            user
	 * @return Object ID of the processed {@link com.emc.d2.api.config.search.D2cQueryDql} object
	 * @throws DfException
	 * @throws D2fsException
	 * @throws TemplateException
	 *             Occurs if the FreeMarker DQL template cannot be processed
	 * @throws IOException
	 * @throws NullPointerException
	 *             Occurs if no initialized D2cQueryDql object can be found for the session user
	 */
	public String processQueryDqlObject(String propertyPageName, Hashtable<String, Object> attributesHashtable) throws DfException,
			D2fsException, IOException, TemplateException, NullPointerException {

		// get the initialized D2cQueryDql object
		ID2cQueryDql d2cQueryDqlObject = getInitialisedPublicSearchQueryObject();
		return processQueryDqlObject(d2cQueryDqlObject, propertyPageName, attributesHashtable);
	}

	/**
	 * Processes the FreeMarker DQL template stored in the initialized {@link com.emc.d2.api.config.search.D2cQueryDql} object. Resulting
	 * DQL is saved back in the {@link com.emc.d2.api.config.search.D2cQueryDql} object.
	 * 
	 * @param attributesHashtable
	 *            Contains pairs - the name of the parameter in the FreeMarker DQL template and the value of the parameter provided by the
	 *            user
	 * @return Object ID of the processed {@link com.emc.d2.api.config.search.D2cQueryDql} object
	 * @throws DfException
	 * @throws D2fsException
	 * @throws TemplateException
	 *             Occurs if the FreeMarker DQL template cannot be processed
	 * @throws IOException
	 * @throws NullPointerException
	 *             Occurs if no initialized D2cQueryDql object can be found for the session user
	 */
	public String processQueryDqlObject(ID2cQueryDql d2cQueryDqlObject, String propertyPageName,
			Hashtable<String, Object> attributesHashtable) throws DfException, D2fsException, IOException, TemplateException,
			NullPointerException {
		String queryDqlObjectId = DfId.DF_NULLID_STR;

		if (d2cQueryDqlObject != null) {
			LOGGER.debug("DQL template: " + d2cQueryDqlObject.getDql());

			// prepare the DQL template to be processed
			StringTemplateLoader stringLoader = new StringTemplateLoader();
			stringLoader.putTemplate("dqlTemplate", d2cQueryDqlObject.getDql());

			Configuration templateConfiguration = new Configuration();
			templateConfiguration.setTemplateLoader(stringLoader);

			Template template = templateConfiguration.getTemplate("dqlTemplate");

			// process the DQL template - pass the values of parameters
			Writer dql = new StringWriter();
			template.process(attributesHashtable, dql);

			// save the resulting DQL back to the
			LOGGER.debug("DQL evaluated: " + dql.toString());
			d2cQueryDqlObject.setDql(dql.toString());
			// set the a_status of the object to processed
			((IDfSysObject) d2cQueryDqlObject).setStatus(QueryDqlObjectStatus.PROCESSED.toString());

			// save property page parameters for last search
			((IDfSysObject) d2cQueryDqlObject).setSubject(propertyPageName);
			setQueryParameters((IDfPersistentObject) d2cQueryDqlObject, attributesHashtable);
			d2cQueryDqlObject.save();

			// return the id of the processed object
			queryDqlObjectId = d2cQueryDqlObject.getObjectId().getId();
		} else {
			String exceptionMessage = "Cannot fetch an initialized D2cQueryDql object for user " + getSession().getUser(null);
			LOGGER.error(exceptionMessage);
			throw new NullPointerException(exceptionMessage);
		}

		return queryDqlObjectId;
	}

	/**
	 * Returns r_object_id and r_object_type of last executed search
	 * 
	 * @return Object ID of the {@link com.emc.d2.api.config.search.D2cQueryDql} object
	 * @throws DfException
	 * @throws D2fsException
	 */
	public HashMap<String, String> getLastExecutedQueryObjectData(String specialAppFilter) throws DfException, D2fsException {
		HashMap<String, String> result = null;
		IDfCollection collection = null;
		try {
			// returns list of last executed d2c_query objects
			StringBuilder dqlBuilder = new StringBuilder();
			dqlBuilder
					.append("SELECT r_object_id, r_object_type FROM d2c_query WHERE owner_name=USER and (object_name = 'lastSearch' or (a_special_app in('");
			if (StringUtils.isBlank(specialAppFilter)) {
				dqlBuilder.append(PUBLIC_SEARECH_APP_NAME);
				dqlBuilder.append("', '");
				dqlBuilder.append(QUICK_SEARCH_APP_NAME);
			} else
				dqlBuilder.append(specialAppFilter);

			dqlBuilder.append("') and a_status = 'Executed')) ORDER BY r_modify_date desc");

			LOGGER.debug("Dql evaluated: " + dqlBuilder.toString());

			DfQuery query = new DfQuery(dqlBuilder.toString());
			collection = query.execute(getSession(), IDfQuery.READ_QUERY);

			if (collection.next()) {
				result = new HashMap<String, String>();
				result.put("r_object_id", collection.getString("r_object_id"));
				result.put("r_object_type", collection.getString("r_object_type"));
				LOGGER.debug("Last query dql result: r_object_id: " + collection.getString("r_object_id") + ", r_object_type: "
						+ collection.getString("r_object_type"));
			}

		} finally {
			if (collection != null) {
				try {
					collection.close();
				} catch (DfException closeEx) {
					LOGGER.error("Error while closing collection", closeEx);
				}
			}
		}

		return result;
	}

	/**
	 * Returns the Object ID of the processed D2cQueryDql object before it is executed. The D2cQueryDql object is marked as executed, to
	 * prevent accidental re-execution on D2_EVENT_DIALOG_HIDE event
	 * 
	 * @return Object ID of the {@link com.emc.d2.api.config.search.D2cQueryDql} object
	 * @throws DfException
	 * @throws D2fsException
	 */
	public String getQueryDqlObject() throws DfException, D2fsException {
		String queryDqlObjectId = DfId.DF_NULLID_STR;

		ID2cQueryDql d2cQueryDqlObject = getProcessedPublicSearchQueryObject();
		if (d2cQueryDqlObject != null) {
			// set the a_status of the object to executed
			((IDfSysObject) d2cQueryDqlObject).setStatus(QueryDqlObjectStatus.EXECUTED.toString());
			d2cQueryDqlObject.save();

			// return the id of the object
			queryDqlObjectId = d2cQueryDqlObject.getObjectId().getId();
		}

		return queryDqlObjectId;

	}

	/**
	 * Prepares a D2cQueryDql object for a Quick search. It requires a ROG_QUICK_SEARCH Query form stored in the repository. The query form
	 * must contain a FreeMarker DQL template of the DQL executed by the Quick search.
	 * 
	 * @param quickSearchTerm
	 *            Quick search term value
	 * @return Object ID of the {@link com.emc.d2.api.config.search.D2cQueryDql} object
	 * @throws NullPointerException
	 *             Occurs if ROG_QUICK_SEARCH Query form was not found
	 * @throws D2fsException
	 * @throws DfException
	 * @throws IOException
	 * @throws TemplateException
	 *             Occurs if the FreeMarker DQL template cannot be processed
	 */
	public String getQueryDqlObjectForQuickSearch(String quickSearchTerm) throws NullPointerException, DfException, D2fsException,
			IOException, TemplateException {
		String queryDqlObjectId = DfId.DF_NULLID_STR;

		ID2QueryFormConfig quickSearchQueryForm = getQuickSearchQueryForm();

		// if ROG_QUICK_SEARCH Query form was not found throw an exception
		if (quickSearchQueryForm == null)
			throw new NullPointerException(QUICK_SEARCH_APP_NAME + " object not found");

		// get the D2cQueryDql object used for Quick search by the session user
		ID2cQueryDql d2cQueryDqlObject = getQuickSearchQueryObject();

		if (d2cQueryDqlObject == null) {
			// if there is no object, create one for the session user
			IDfSession session = getSession();
			d2cQueryDqlObject = D2cQueryDql.newObject(session, QUICK_SEARCH_APP_NAME + "_" + session.getUser(null).getUserName());
			((IDfSysObject) d2cQueryDqlObject).setSpecialApp(QUICK_SEARCH_APP_NAME);
			d2cQueryDqlObject.setHasMandatoryAttributes(true);
		}

		// prepare the DQL template to be processed
		StringTemplateLoader stringLoader = new StringTemplateLoader();
		LOGGER.debug("Quick search DQL template: " + quickSearchQueryForm.getDqlValue());
		stringLoader.putTemplate(DQL_TEMPLATE, quickSearchQueryForm.getDqlValue());
		Configuration templateConfiguration = new Configuration();
		templateConfiguration.setTemplateLoader(stringLoader);

		// process the DQL template - pass the quickSearchTerm parameter
		Map<String, String> attributes = new HashMap<String, String>();
		attributes.put(QUICK_SEARCH_TEMPLATE_PARAMETER, quickSearchTerm);

		Writer dql = new StringWriter();
		Template template = templateConfiguration.getTemplate(DQL_TEMPLATE);
		template.process(attributes, dql);
		LOGGER.debug("Quick search DQL evaluated: " + dql.toString());

		d2cQueryDqlObject.setDql(dql.toString());
		((IDfSysObject) d2cQueryDqlObject).setStatus(QueryDqlObjectStatus.EXECUTED.toString());
		d2cQueryDqlObject.save();

		// return the id of the object
		queryDqlObjectId = d2cQueryDqlObject.getObjectId().getId();

		return queryDqlObjectId;
	}

	/**
	 * Marks the D2cQueryDql object for Public search as canceled
	 * 
	 * @return Object ID of the canceled {@link com.emc.d2.api.config.search.D2cQueryDql} object
	 * @throws UnsupportedEncodingException
	 * @throws DfException
	 * @throws D2fsException
	 * @throws ArgumentNotFoundException
	 */
	public String cancelQueryDqlObject() throws UnsupportedEncodingException, DfException, D2fsException, ArgumentNotFoundException {
		String queryDqlObjectId = DfId.DF_NULLID_STR;

		// get the D2cQueryDql object used for Public searches by the session user
		ID2cQueryDql d2cQueryDqlObject = getPublicSearchQueryObject();

		if (d2cQueryDqlObject != null) {
			((IDfSysObject) d2cQueryDqlObject).setStatus(QueryDqlObjectStatus.CANCELED.toString());
			d2cQueryDqlObject.save();
			queryDqlObjectId = d2cQueryDqlObject.getObjectId().getId();
		}

		return queryDqlObjectId;
	}

	/**
	 * Returns {@link com.emc.d2.api.config.search.D2cQueryDql} object in any status for Public search for a given user
	 * 
	 * @return
	 * @throws DfException
	 * @throws D2fsException
	 */
	private ID2cQueryDql getPublicSearchQueryObject() throws DfException, D2fsException {
		return (ID2cQueryDql) getSession().getObjectByQualification(
				ID2cQueryDql.D2C_QUERY_DQL + " where r_creator_name = USER and a_special_app = '" + PUBLIC_SEARECH_APP_NAME + "'");
	}

	/**
	 * Returns initialized {@link com.emc.d2.api.config.search.D2cQueryDql} object for Public search for a given user
	 * 
	 * @return
	 * @throws DfException
	 * @throws D2fsException
	 */
	private ID2cQueryDql getInitialisedPublicSearchQueryObject() throws DfException, D2fsException {
		return (ID2cQueryDql) getSession().getObjectByQualification(
				ID2cQueryDql.D2C_QUERY_DQL + " where r_creator_name = USER and a_special_app = '" + PUBLIC_SEARECH_APP_NAME
						+ "' and a_status = '" + QueryDqlObjectStatus.INITIALIZED.toString() + "'");
	}

	/**
	 * Returns processed {@link com.emc.d2.api.config.search.D2cQueryDql} object for Public search for a given user
	 * 
	 * @return
	 * @throws DfException
	 * @throws D2fsException
	 */
	private ID2cQueryDql getProcessedPublicSearchQueryObject() throws DfException, D2fsException {
		return (ID2cQueryDql) getSession().getObjectByQualification(
				ID2cQueryDql.D2C_QUERY_DQL + " where r_creator_name = USER and a_special_app = '" + PUBLIC_SEARECH_APP_NAME
						+ "' and a_status = '" + QueryDqlObjectStatus.PROCESSED.toString() + "'");
	}

	/**
	 * Returns {@link com.emc.d2.api.config.search.D2cQueryDql} object for Quick search for a given user
	 * 
	 * @return
	 * @throws DfException
	 * @throws D2fsException
	 */
	private ID2cQueryDql getQuickSearchQueryObject() throws DfException, D2fsException {
		return (ID2cQueryDql) getSession().getObjectByQualification(
				ID2cQueryDql.D2C_QUERY_DQL + " where r_creator_name = USER and a_special_app = '" + QUICK_SEARCH_APP_NAME + "'");
	}

	/**
	 * Returns {@link com.emc.d2.api.config.modules.search.D2QueryFormConfig} objects with name ROG_QUICK_SEARCH. This object should store
	 * FreeMarker template for quick search.
	 * 
	 * @return
	 * @throws DfException
	 * @throws D2fsException
	 */
	private ID2QueryFormConfig getQuickSearchQueryForm() throws DfException, D2fsException {
		return (ID2QueryFormConfig) getSession().getObjectByQualification(
				ID2QueryFormConfig.D2_QUERYFORM_CONFIG + " where object_name = '" + QUICK_SEARCH_APP_NAME + "'");
	}

	public Map<String, Object> getQueryParameters(IDfPersistentObject lastSearchObject) throws DfException, NullPointerException {
		Map<String, Object> queryParameter = new HashMap<String, Object>();

		if (lastSearchObject != null) {
			for (int i = 0; i < lastSearchObject.getValueCount(ID2cQuery.ATTR_CONDITION_ATTRIBUTE); i++) {
				String parameterName = lastSearchObject.getRepeatingString(ID2cQuery.ATTR_CONDITION_ATTRIBUTE, i);
				String parameterValues = lastSearchObject.getRepeatingString(ID2cQuery.ATTR_CONDITION_VALUE, i);

				if (StringUtils.contains(parameterValues, AttributeUtils.SEPARATOR_VALUE))
					queryParameter.put(parameterName, StringUtil.split(parameterValues, AttributeUtils.SEPARATOR_VALUE));
				else
					queryParameter.put(parameterName, parameterValues);
			}
		} else
			throw new NullPointerException("lastSearchObject is null");

		return queryParameter;
	}

	public void setQueryParameters(IDfPersistentObject lastSearchObject, Hashtable<String, Object> queryParameter) throws DfException,
			NullPointerException {
		if (lastSearchObject != null) {
			lastSearchObject.removeAll(ID2cQuery.ATTR_CONDITION_ATTRIBUTE);
			lastSearchObject.removeAll("attr_value");

			if (queryParameter != null) {
				Iterator<Entry<String, Object>> iterator = queryParameter.entrySet().iterator();
				while (iterator.hasNext()) {
					Entry<String, Object> parameterEntry = iterator.next();

					String parameterName = parameterEntry.getKey();

					// check if this filed is not hidden
					if (!StringUtils.contains(parameterName, DQLTemplateUtils.HIDDEN_FIELD_SUFIX_SEPARATOR)
							&& !StringUtils.contains(parameterName, DQLTemplateUtils.PARAM_LIST)) {
						lastSearchObject.appendString(ID2cQuery.ATTR_CONDITION_ATTRIBUTE, parameterEntry.getKey());

						Object parameterValue = parameterEntry.getValue();
						if (parameterValue instanceof String)
							lastSearchObject.appendString(ID2cQuery.ATTR_CONDITION_VALUE, (String) parameterEntry.getValue());
						else if (parameterValue instanceof List<?>)
							lastSearchObject.appendString(ID2cQuery.ATTR_CONDITION_VALUE,
									StringUtils.join((List<?>) parameterValue, AttributeUtils.SEPARATOR_VALUE));
						else if (parameterValue != null)
							lastSearchObject.appendString(ID2cQuery.ATTR_CONDITION_VALUE, parameterValue.toString());
						else
							lastSearchObject.appendString(ID2cQuery.ATTR_CONDITION_VALUE, "");
					}
				}
			}
		} else
			throw new NullPointerException("lastSearchObject is null");
	}

}

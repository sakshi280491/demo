package com.emc.rogetmf.webfs.actions;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.documentum.fc.client.IDfPersistentObject;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfId;
import com.emc.common.java.utils.ArgumentNotFoundException;
import com.emc.d2fs.dctm.web.services.D2fsContext;
import com.emc.d2fs.exceptions.D2fsException;
import com.emc.d2fs.models.attribute.Attribute;
import com.emc.rogetmf.webfs.action.AbstractCallbackAction;
import com.emc.rogetmf.webfs.service.QueryDqlObjectWebfsService;

/**
 * Providing method used to run Public Search and Quick search based on FreeMarker DQL templates.
 * 
 * @see com.emc.rogetmf.webfs.service.QueryDqlObjectWebfsService
 * 
 * @author Krzysztof Jurkowski
 * 
 */
public class QueryDqlObjectService extends AbstractCallbackAction {

	public static final String SPECIAL_APP_FILTER_PARAMETER = "a_special_app";
	
	public static final String QUICK_SEARCH_PARAMETER = "quick_search_term";

	private Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Override
	protected Logger getLogger() {
		return LOGGER;
	}

	/**
	 * Initializes Public Search helper object with a DQL template stored in a given Query form object. This method must be called prior to
	 * executing a public search.
	 * 
	 * @param context
	 *            Should contain <code>r_object_id</code> of the Query form containing FreeMarker DQL template for the search.
	 * @return Object ID of the initialized {@link com.emc.d2.api.config.search.D2cQueryDql} object.
	 * @throws DfException
	 * @throws D2fsException
	 * @throws ArgumentNotFoundException
	 */
	public List<Attribute> initQueryDqlObject(D2fsContext context) throws DfException, D2fsException, ArgumentNotFoundException {
		List<Attribute> result = super.onServiceEntry(context);

		IDfPersistentObject queryFormObject = context.getFirstObject();

		Attribute oamValue = new Attribute();
		oamValue.setName("oam_value");

		String queryDqlObjectId = DfId.DF_NULLID_STR;

		if (queryFormObject != null) {
			Attribute queryFormObjectId = new Attribute();
			queryFormObjectId.setName("query_form_object_id");
			queryFormObjectId.setValue(queryFormObject.getObjectId().getId());
			result.add(queryFormObjectId);

			try {
				QueryDqlObjectWebfsService service = new QueryDqlObjectWebfsService(context);
				queryDqlObjectId = service.initQueryDqlObject(queryFormObject);
			} catch (Exception e) {
				queryDqlObjectId = DfId.DF_NULLID_STR;
				onServiceException(result, e);
			}
		}

		oamValue.setValue(queryDqlObjectId);
		result.add(oamValue);

		return result;
	}

	/**
	 * Returns Object ID of the Public Search helper object which DQL template has been evaluated with the parameters passed by the session
	 * user. This object can be used to execute a Public search with the D2_ACTION_SEARCH_DOCUMENT action.
	 * 
	 * @param context Should contain <code>r_object_id</code> of the Query form containing FreeMarker DQL template for the search.
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws DfException
	 * @throws D2fsException
	 * @throws ArgumentNotFoundException
	 */
	public List<Attribute> getQueryDqlObject(D2fsContext context) throws UnsupportedEncodingException, DfException, D2fsException,
			ArgumentNotFoundException {
		List<Attribute> result = super.onServiceEntry(context);

		Attribute oamValue = new Attribute();
		oamValue.setName("oam_value");

		String queryDqlObjectId = DfId.DF_NULLID_STR;
		try {
			QueryDqlObjectWebfsService service = new QueryDqlObjectWebfsService(context);
			queryDqlObjectId = service.getQueryDqlObject();
		} catch (Exception e) {
			queryDqlObjectId = DfId.DF_NULLID_STR;
			onServiceException(result, e);
		} finally {
			oamValue.setValue(queryDqlObjectId);
			result.add(oamValue);
		}

		return result;
	}
	
	/**
	 * Returns Object ID of the Public Search helper object which DQL template has been evaluated with the parameters passed by the session
	 * user. This object can be used to execute a Public search with the D2_ACTION_SEARCH_DOCUMENT action.
	 * 
	 * @param context Should contain <code>r_object_id</code> of the Query form containing FreeMarker DQL template for the search.
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws DfException
	 * @throws D2fsException
	 * @throws ArgumentNotFoundException
	 */
	public List<Attribute> getLastQueryDqlObjectData(D2fsContext context) throws UnsupportedEncodingException, DfException, D2fsException,
			ArgumentNotFoundException {
		List<Attribute> result = super.onServiceEntry(context);

		try {
			String specialAppFilter = context.getParameterParser().getStringParameter(SPECIAL_APP_FILTER_PARAMETER, null);

			QueryDqlObjectWebfsService service = new QueryDqlObjectWebfsService(context);
			HashMap<String, String> queryObjectData = service.getLastExecutedQueryObjectData(specialAppFilter);
			
			if(queryObjectData != null) {
				for (Iterator<String> iterator = queryObjectData.keySet().iterator(); iterator.hasNext();) {
					String attributeName = iterator.next();
					
					Attribute attribute = new Attribute();
					attribute.setName(attributeName);
					attribute.setValue(queryObjectData.get(attributeName));
					
					result.add(attribute);
				}

			}
			
		} catch (Exception e) {
			onServiceException(result, e);
		} 
		
		return result;
	}

	/**
	 * Returns Object ID of the Quick Search helper object. It requires a ROG_QUICK_SEARCH Query form stored in the repository. The query
	 * form must contain a FreeMarker DQL template of the DQL executed by the Quick search. The DQL template is evaluated with the Quick
	 * search term passed by the session user. This object can be used to execute a Public search with the D2_ACTION_SEARCH_DOCUMENT action.
	 * 
	 * @param context Should contain quick_search_term parameter with the value of search phrase passed by the user
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws DfException
	 * @throws D2fsException
	 * @throws ArgumentNotFoundException
	 */
	public List<Attribute> getQueryDqlObjectForQuickSearch(D2fsContext context) throws UnsupportedEncodingException, DfException,
			D2fsException, ArgumentNotFoundException {
		List<Attribute> result = super.onServiceEntry(context);

		String quickSearchTerm = context.getParameterParser().getStringParameter(QUICK_SEARCH_PARAMETER, null);

		Attribute oamValue = new Attribute();
		oamValue.setName("oam_value");

		String queryDqlObjectId = DfId.DF_NULLID_STR;

		if (StringUtils.isNotBlank(quickSearchTerm)) {
			try {
				QueryDqlObjectWebfsService service = new QueryDqlObjectWebfsService(context);
				queryDqlObjectId = service.getQueryDqlObjectForQuickSearch(quickSearchTerm);
			} catch (Exception e) {
				queryDqlObjectId = DfId.DF_NULLID_STR;
				onServiceException(result, e);
			}
		}

		oamValue.setValue(queryDqlObjectId);
		result.add(oamValue);

		return result;
	}
}
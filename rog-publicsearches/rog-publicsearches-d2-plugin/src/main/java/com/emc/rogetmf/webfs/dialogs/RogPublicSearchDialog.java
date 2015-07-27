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
package com.emc.rogetmf.webfs.dialogs;

import java.util.Hashtable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.documentum.fc.client.IDfPersistentObject;
import com.documentum.fc.client.IDfSysObject;
import com.documentum.fc.common.DfException;
import com.emc.common.java.utils.StringUtil;
import com.emc.common.java.xml.XmlNode;
import com.emc.d2.api.config.modules.search.ID2QueryFormConfig;
import com.emc.d2.api.config.search.ID2cQueryDql;
import com.emc.d2fs.dctm.dialogs.AbstractDialog;
import com.emc.d2fs.dctm.dialogs.ID2Dialog;
import com.emc.d2fs.dctm.dialogs.search.QueryFormDialog;
import com.emc.d2fs.dctm.exceptions.D2WarningException;
import com.emc.d2fs.dctm.utils.ParameterParser;
import com.emc.d2fs.dctm.web.services.D2fsContext;
import com.emc.d2fs.models.attribute.Attribute;
import com.emc.d2fs.utils.AttributeUtils;
import com.emc.rogetmf.webfs.service.QueryDqlObjectWebfsService;

/**
 * Custom Public search dialog class supporting {@link com.emc.d2.api.config.modules.search.D2QueryFormConfig} templates with FreeMarker
 * syntax used in DQL
 * 
 * @see <a href="http://freemarker.org/">FreeMarker Java Template Engine - Overview</a>
 * @author Krzysztof Jurkowski
 *
 */
public class RogPublicSearchDialog extends AbstractDialog implements ID2Dialog {

	private Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Override
	public XmlNode buildDialog(D2fsContext context, List<Attribute> attributes) throws Exception {
		XmlNode result = null;

		QueryFormDialog queryFormDialog = new QueryFormDialog();
		result = queryFormDialog.buildDialog(context, attributes);

		return result;
	}

	@Override
	public XmlNode validDialog(D2fsContext context) throws Exception {
		XmlNode result = super.validDialog(context);

		ParameterParser parameterParser = context.getParameterParser();
		List<Attribute> attributes = parameterParser.getParameters();

		// prepare hashtable to with search parameters passed by user
		Hashtable<String, Object> attributesHashtable = new Hashtable<String, Object>();
		for (Attribute attribute : attributes) {
			// escape single quote
			if (attribute.getValue().contains("'"))
				attribute.setValue(attribute.getValue().replace("'", "''"));

			// split list values
			if (attribute.getValue().contains(AttributeUtils.SEPARATOR_VALUE))
				attributesHashtable.put(attribute.getName(), StringUtil.split(attribute.getValue(), AttributeUtils.SEPARATOR_VALUE));
			else
				attributesHashtable.put(attribute.getName(), attribute.getValue());
		}

		try {
			String propertyPageName = "";
			IDfPersistentObject searchConfigObject = context.getFirstObject();
			if (searchConfigObject != null) {
				QueryDqlObjectWebfsService service = new QueryDqlObjectWebfsService(context);
				
				String searchConfigObjectType = searchConfigObject.getType().getName();
				if (ID2cQueryDql.D2C_QUERY_DQL.equalsIgnoreCase(searchConfigObjectType)) {
					propertyPageName = ((IDfSysObject) searchConfigObject).getSubject();
					
					// evaluates DQL template stored in the helper object with the parameters passed by the user
					service.processQueryDqlObject((ID2cQueryDql)searchConfigObject, propertyPageName, attributesHashtable);
				}
				else if (ID2QueryFormConfig.D2_QUERYFORM_CONFIG.equalsIgnoreCase(searchConfigObjectType)) {
					propertyPageName = ((ID2QueryFormConfig) searchConfigObject).getPropertyPage();
					
					// evaluates DQL template stored in the helper object with the parameters passed by the user
					service.processQueryDqlObject(propertyPageName, attributesHashtable);
				}
				else
					throw new Exception("Unknown Property page name from object " + searchConfigObject.getObjectId().getId() + "("
							+ searchConfigObjectType + ")");
			} else
				throw new NullPointerException("Query object is undefined");

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new D2WarningException("Error during search execution", e.getMessage());
		}

		return result;
	}

	@Override
	public XmlNode cancelDialog(D2fsContext context) throws Exception {
		XmlNode result = super.cancelDialog(context);

		try {
			QueryDqlObjectWebfsService service = new QueryDqlObjectWebfsService(context);

			// set status of public search helper object to canceled
			service.cancelQueryDqlObject();
		} catch (DfException e) {
			LOGGER.error(e.getMessage(), e);
		}

		return result;
	}

}

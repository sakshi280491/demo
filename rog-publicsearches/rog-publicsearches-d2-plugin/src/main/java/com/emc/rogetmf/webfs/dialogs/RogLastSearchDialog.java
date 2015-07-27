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

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.documentum.fc.client.IDfSysObject;
import com.emc.common.java.bundles.MultiResourceBundle;
import com.emc.common.java.xml.XmlNode;
import com.emc.d2.api.config.modules.property.D2PropertyConfig;
import com.emc.d2.api.config.modules.property.ID2PropertyConfig;
import com.emc.d2fs.dctm.dialogs.ID2Dialog;
import com.emc.d2fs.dctm.ui.DialogProcessor;
import com.emc.d2fs.dctm.ui.XmlFactory;
import com.emc.d2fs.dctm.web.services.D2fsContext;
import com.emc.d2fs.models.attribute.Attribute;
import com.emc.rogetmf.webfs.service.QueryDqlObjectWebfsService;

/**
 * Custom Public search dialog class supporting {@link com.emc.d2.api.config.modules.search.D2QueryFormConfig} templates with FreeMarker
 * syntax used in DQL. Allows to initialize the dialog controls with parameters of a previously executed search.
 * 
 * @see <a href="http://freemarker.org/">FreeMarker Java Template Engine - Overview</a>
 * @author Krzysztof Jurkowski
 *
 */
public class RogLastSearchDialog extends RogPublicSearchDialog implements ID2Dialog {

	private Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Override
	public XmlNode buildDialog(D2fsContext context, List<Attribute> attributes) throws Exception {
		XmlNode result = null;

		QueryDqlObjectWebfsService service = new QueryDqlObjectWebfsService(context);

		IDfSysObject lastSearchObject = (IDfSysObject) context.getFirstObject();
		Map<String, Object> parameters = service.getQueryParameters(lastSearchObject);
		String propertyName = lastSearchObject.getSubject();

		ID2PropertyConfig propertyConfig = D2PropertyConfig.getInstanceByName(context.getSession(), propertyName);

		XmlNode xmlConfigDialog = propertyConfig.getXmlContent();

		xmlConfigDialog.setAttribute("d2_property_config", propertyName);

		DialogProcessor dialogProcessor = new DialogProcessor(context, xmlConfigDialog);
		MultiResourceBundle dialogBundle = context.getResourceBundle(getClass().getCanonicalName());

		dialogProcessor.setLabelsBundle(dialogBundle);

		String objectType = propertyConfig.getForType();

		dialogProcessor.setDctmLabelsBundle(objectType);
		if (parameters != null) {
			dialogProcessor.setDefaultValues(parameters);
		}

		result = dialogProcessor.getDialog();
		result.setAttribute("id", "QueryFormDialog");
		result.setAttribute("CONFIG", propertyName);
		
		LOGGER.debug("Result : {}", result);

		return result;
	}

	@Override
	public XmlNode cancelDialog(D2fsContext context) throws Exception {
		// do not call super.cancelDialog
		// this will change the status of the query object to Canceled
		// we want to keep it Executed as this is last executed query object
		return XmlFactory.getRootSuccess();
	}

}

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
package com.emc.rogetmf.webfs.ui.assistances;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.documentum.fc.client.DfQuery;
import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.IDfQuery;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.common.DfException;
import com.emc.common.java.bundles.MultiResourceBundle;
import com.emc.common.java.xml.XmlNode;
import com.emc.common.java.xml.XmlOptionEx;
import com.emc.d2fs.dctm.ui.DialogProcessor;
import com.emc.d2fs.dctm.ui.assistances.IJavaAssistance;
import com.emc.d2fs.dctm.web.services.D2fsContext;
import com.emc.d2fs.exceptions.D2fsException;
import com.emc.rogetmf.webfs.ui.assistance.DQLTemplateUtils;
import com.emc.rogetmf.webfs.ui.dynamiccontrols.DQLTemplateControl;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;


/**
 * Custom value provider for dynamic list controls supported by DQLs with FreeMarker syntax
 * 
 * @author Krzysztof Jurkowski
 *
 */
public final class DQLTemplateList implements IJavaAssistance {
	private Logger LOGGER = LoggerFactory.getLogger(getClass());

	private static final String DQL_TEMPLATE = "dqlTemplate";
	private static final String LABEL_TEMPLATE = "labelTemplate";
	private static final String LABEL_TEMPLATE_ATTRS_DELIM = ",";

	private static final Pattern FREEMARKER_VARIABLE_PATTERN = Pattern.compile("(?<=\\$\\{)([^\\}]+)(?=\\})");

	public Object getValue(D2fsContext context, MultiResourceBundle labelsBundle, Map<String, Object> attributes) throws DfException,
			D2fsException {
		XmlNode result = new XmlNode();

		// get the FreeMarker DQL template configured for this control
		String dqlTemplateExpr = getDQLTemplete(attributes);
		LOGGER.debug("Assistance DQL template: {}", dqlTemplateExpr);

		// dqlTemplateExpr is not blank during property page initialization if dynamic control has no dependencies
		// or property page has been initialized
		if (StringUtils.isNotBlank(dqlTemplateExpr)) {

			IDfSession session = context.getSession();

			// prepare the DQL template to be processed
			StringTemplateLoader stringLoader = new StringTemplateLoader();
			stringLoader.putTemplate(DQL_TEMPLATE, dqlTemplateExpr);
			Configuration templateConfiguration = new Configuration();
			templateConfiguration.setTemplateLoader(stringLoader);

			String labelTemplateExpr = getLabelTemplete(attributes);
			boolean labelTemplateDefined = StringUtils.isNotBlank(labelTemplateExpr);
			// if label template is empty
			if (labelTemplateDefined) {
				LOGGER.debug("Label template: {}", labelTemplateExpr);
				stringLoader.putTemplate(LABEL_TEMPLATE, labelTemplateExpr);
			} else
				LOGGER.debug("Label template undefined - using 'label' column.");

			Writer dql = new StringWriter();
			IDfCollection col = null;

			try {
				// process the DQL template - attributes contain values of dependent controls
				Template dqlTemplate = templateConfiguration.getTemplate(DQL_TEMPLATE);
				dqlTemplate.process(attributes, dql);
				LOGGER.debug("Assistance DQL evaluated: {}", dql.toString());

				IDfQuery query = new DfQuery();

				query.setDQL(dql.toString());
				col = query.execute(session, IDfQuery.READ_QUERY);

				if (labelTemplateDefined) {
					Template labelTemplate = templateConfiguration.getTemplate(LABEL_TEMPLATE);

					// contains the list of columns used in the label template
					String[] labelTempleteAttrs = getLabelTempleteAttrs(attributes);
					// contains a map of columns and their values - it is used to evaluate template of the label
					Map<String, String> labelAttributes = new HashMap<String, String>();

					StringWriter labelWriter = new StringWriter();

					while (col.next()) {
						String key = col.getString(DialogProcessor.ATTR_DQL_NAME);
						for (int i = 0; i < labelTempleteAttrs.length; i++)
							labelAttributes.put(labelTempleteAttrs[i], col.getString(labelTempleteAttrs[i]));

						// evaluate the template for the label
						labelTemplate.process(labelAttributes, labelWriter);

						XmlNode option = new XmlOptionEx(key, labelWriter.toString());
						result.appendXmlNode(option);

						// clear the writer and map, to reuse it for the next label
						labelWriter.getBuffer().setLength(0);
						attributes.clear();
					}

				} else {
					// if label template is null use the "label" column
					while (col.next()) {
						// append DQL results to the value list
						String key = col.getString(DialogProcessor.ATTR_DQL_NAME);
						String label = col.getString(DialogProcessor.ATTR_DQL_LABEL);

						XmlNode option = new XmlOptionEx(key, label);
						result.appendXmlNode(option);
					}
				}

			} catch (IOException e) {
				LOGGER.error(e.getMessage(), e);
			} catch (TemplateException e) {
				LOGGER.error(e.getMessage(), e);
			} finally {
				if (col != null) {
					col.close();
				}
			}
		}

		return result;
	}

	/**
	 * Returns the DQL providing options for the control
	 * 
	 * @param attributes
	 * @return
	 */
	private String getDQLTemplete(Map<String, Object> attributes) {
		return DQLTemplateUtils.getHiddenControlValue(attributes, DQLTemplateControl.ATTR_DQL_TEMPLATE);
	}

	/**
	 * Returns a FreeMarker template to evaluate the label for each option
	 * 
	 * @param attributes
	 * @return
	 */
	private String getLabelTemplete(Map<String, Object> attributes) {
		return DQLTemplateUtils.getHiddenControlValue(attributes, DQLTemplateControl.ATTR_LABEL_TEMPLATE);
	}

	/**
	 * Returns list of DQL columns for the FreeMarker template to evaluate the label for each option
	 * 
	 * @param attributes
	 * @return
	 */
	private String[] getLabelTempleteAttrs(Map<String, Object> attributes) {
		String[] labelTempleteAttrsArr = null;

		String labelTempleteAttrs = DQLTemplateUtils.getHiddenControlValue(attributes, DQLTemplateControl.ATTR_LABEL_TEMPLATE_ATTRS);

		if (StringUtils.isNotBlank(labelTempleteAttrs))
			labelTempleteAttrsArr = StringUtils.split(labelTempleteAttrs, LABEL_TEMPLATE_ATTRS_DELIM);
		else {
			String labelTemplete = getLabelTemplete(attributes);
			if (StringUtils.isNotBlank(labelTemplete)) {
				LOGGER.debug("Extracting Freemarker variables from {} attribute", DQLTemplateControl.ATTR_LABEL_TEMPLATE);
				ArrayList<String> labelTempleteAttrsList = new ArrayList<String>();

				Matcher matcher = FREEMARKER_VARIABLE_PATTERN.matcher(labelTemplete);
				while (matcher.find())
					labelTempleteAttrsList.add(matcher.group());

				labelTempleteAttrsArr = labelTempleteAttrsList.toArray(new String[labelTempleteAttrsList.size()]);
			} else
				LOGGER.warn("ATTR_LABEL_TEMPLATE_ATTRS is empty for control");
		}

		return labelTempleteAttrsArr;
	}
}
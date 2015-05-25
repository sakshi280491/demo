package com.emc.rogetmf.webfs.ui.assistances;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.BooleanUtils;
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
import com.emc.rogetmf.webfs.ui.dynamiccontrols.DQLTemplateControl;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Custom value provider for dynamic list controls supported by DQLs with FreeMarker syntax
 * 
 * @author jurkowk1
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
				col = query.execute(session, 0);

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
		return getHiddenControlValue(attributes, DQLTemplateControl.ATTR_DQL_TEMPLATE);
	}

	/**
	 * Returns a FreeMarker template to evaluate the label for each option
	 * 
	 * @param attributes
	 * @return
	 */
	private String getLabelTemplete(Map<String, Object> attributes) {
		return getHiddenControlValue(attributes, DQLTemplateControl.ATTR_LABEL_TEMPLATE, true);
	}

	/**
	 * Returns list of DQL columns for the FreeMarker template to evaluate the label for each option
	 * 
	 * @param attributes
	 * @return
	 */
	private String[] getLabelTempleteAttrs(Map<String, Object> attributes) {
		String[] labelTempleteAttrsArr = null;

		String labelTempleteAttrs = getHiddenControlValue(attributes, DQLTemplateControl.ATTR_LABEL_TEMPLATE_ATTRS, true);

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

	private String getHiddenControlValue(Map<String, Object> attributes, String controlId) {
		boolean evaluateOnInit = BooleanUtils.toBoolean((String) attributes.get(DQLTemplateControl.ATTR_EVALUATE_ON_INIT));
		return getHiddenControlValue(attributes, controlId, evaluateOnInit);
	}
	
	/**
	 * Returns value of hidden control. Hidden controls are used to support evaluation of options for a dynamic control. If the dynamic
	 * control has dependencies (other controls which impact it's value) and property page is being initialized, this method will return
	 * null.
	 * 
	 * @param attributes
	 * @return
	 */
	private String getHiddenControlValue(Map<String, Object> attributes, String controlId, boolean evaluateOnInit) {
		// when the dialog is initialized, non standard attributes are included in the attributes map
		String controlValue = (String) attributes.get(controlId);

		// if list of is empty we can return the value of hidden control during property page initialization
		if (evaluateOnInit || hasNoDependencies(attributes))
			return controlValue;

		// otherwise, if control has dependencies, first the properties screen needs to be initialized
		// controlValue is only null after property page initialization
		else if (controlValue == null) {
			String controlIdSufixed = controlId + DQLTemplateControl.HIDDEN_FIELD_SUFIX_SEPARATOR;
			Iterator<Entry<String, Object>> attributeIterator = attributes.entrySet().iterator();
			while (attributeIterator.hasNext()) {
				Entry<String, Object> attribute = attributeIterator.next();
				if (attribute.getKey().startsWith(controlIdSufixed))
					return (String) attribute.getValue();
			}
		}

		return null;
	}

	/**
	 * Checks if a hidden control has dependencies. This can be only evaluated during property page initialization
	 * 
	 * @param attributes
	 * @return
	 */
	private boolean hasNoDependencies(Map<String, Object> attributes) {
		String dependencies = (String) attributes.get(DialogProcessor.ATTR_DEPENDENCIES);

		// dependencies attribute is passed during control initialization only,
		// therefore lack of the attribute in the map doesn't mean that control
		// has no dependencies
		if (dependencies == null)
			return false;

		// this situation should not happen, as a dynamic control will always
		// contain reference to hidden dql_template field
		if (StringUtils.isBlank(dependencies))
			return true;

		String[] dependenciesArray = StringUtils.split(dependencies, ",");
		if (dependenciesArray.length > 0) {
			// if list of dependencies contains nothing but ids of hidden fields,
			// than we consider the control to have no dependencies
			// hidden fields contain _-_[parent_control] suffix
			for (int i = 0; i < dependenciesArray.length; i++)
				if (!StringUtils.contains(dependenciesArray[i], DQLTemplateControl.HIDDEN_FIELD_SUFIX_SEPARATOR))
					return false;

			boolean asynchronous = BooleanUtils.toBoolean((String) attributes.get(DialogProcessor.ATTR_ASYNCHRONOUS));
			if (asynchronous)
				LOGGER.warn("Asynchronous control with no dependencies found. The assistance DQL will be evaluated twice.");

			// all dependencies contained "_-_" - this means the dynamic control has no dependencies
			return true;
		}

		return false;

	}
}
package com.emc.rogetmf.webfs.ui.dynamiccontrols;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.documentum.fc.client.IDfPersistentObject;
import com.emc.common.java.bundles.MultiResourceBundle;
import com.emc.common.java.xml.XmlNode;
import com.emc.d2fs.dctm.exceptions.D2SilentException;
import com.emc.d2fs.dctm.exceptions.D2WarningException;
import com.emc.d2fs.dctm.ui.DialogProcessor;
import com.emc.d2fs.dctm.ui.dynamiccontrols.IDynamicControls;
import com.emc.d2fs.dctm.web.services.D2fsContext;

/**
 * Custom dynamic control which values are provided by a DQLs with FreeMarker syntax. This is a generic class, which can generate any type
 * of supported control, based on control_type attribute.
 * 
 * @author jurkowk1
 *
 */
public final class DQLTemplateControl implements IDynamicControls {
	private Logger LOGGER = LoggerFactory.getLogger(getClass());

	public static final String ATTR_CONTROL = "control";
	
	public static final String ATTR_CONTROL_TYPE = "control_type";

	public static final String ATTR_DQL_TEMPLATE = "dql_template";

	public static final String ATTR_LABEL_TEMPLATE = "label_template";

	public static final String ATTR_LABEL_TEMPLATE_ATTRS = "label_template_attrs";
	
	public static final String ATTR_EVALUATE_ON_INIT = "evaluateOnInit";
	
	// id of control may consist of a-Z, 0-9, -, and _
	public static final String HIDDEN_FIELD_SUFIX_SEPARATOR = "_-_";	
	
	private static final String LIST_SEPARATOR = ",";	
	
	private ArrayList<String> hiddenControlIds = new ArrayList<String>();

	public XmlNode getXmlControls(D2fsContext context, MultiResourceBundle labelsBundle, @SuppressWarnings("rawtypes") Map attributes)
			throws Exception {
		XmlNode result = new XmlNode();

		try {
			// check the control type which is to be generated
			XmlNode listXmlNode = result.appendChildNode((String) attributes.get(ATTR_CONTROL_TYPE));
			@SuppressWarnings("unchecked")
			Iterator<Entry<String, Object>> attributeIterator = attributes.entrySet().iterator();
			while (attributeIterator.hasNext()) {
				// copy all attributes from dynamic control to target control
				Entry<String, Object> attribute = attributeIterator.next();
				if ((String) attribute.getKey() != ATTR_CONTROL_TYPE)
					listXmlNode.setAttribute((String) attribute.getKey(), attribute.getValue());
			}

			// generate a hidden control, that stores the DQL template for the generated control
			// this is necessary because all non standard attributes are wiped out from a control
			appendHiddenTextControl(result, attributes, ATTR_DQL_TEMPLATE, (String) attributes.get(ATTR_DQL_TEMPLATE));

			if (attributes.containsKey(ATTR_LABEL_TEMPLATE)) {
				// set the label template for the generated control
				appendHiddenTextControl(result, attributes, ATTR_LABEL_TEMPLATE, (String) attributes.get(ATTR_LABEL_TEMPLATE));
			}
			if (attributes.containsKey(ATTR_LABEL_TEMPLATE_ATTRS))
				// set the label template attributes for the generated control
				appendHiddenTextControl(result, attributes, ATTR_LABEL_TEMPLATE_ATTRS, (String) attributes.get(ATTR_LABEL_TEMPLATE_ATTRS));
			
			if (StringUtils.isBlank((String) attributes.get(DialogProcessor.ATTR_DEPENDENCIES)))
				listXmlNode.setAttribute(DialogProcessor.ATTR_DEPENDENCIES, StringUtils.join(hiddenControlIds, LIST_SEPARATOR));
			else
				listXmlNode.setAttribute(DialogProcessor.ATTR_DEPENDENCIES, attributes.get(DialogProcessor.ATTR_DEPENDENCIES) + ","
						+ StringUtils.join(hiddenControlIds, LIST_SEPARATOR));
			
			// if control is bounded to an attribute ("linked to property"), pass the value of the attribute
			// in this case "control" attribute is missing or set to false
			if(!attributes.containsKey(ATTR_CONTROL) || !BooleanUtils.toBoolean((String)attributes.get(ATTR_CONTROL))) {
				
				// check if the id of control is set (name of the attribute)
				if(attributes.containsKey(DialogProcessor.ATTR_ID)) {
					String attributeName = (String) attributes.get(DialogProcessor.ATTR_ID);
					
					IDfPersistentObject object = context.getFirstObject();
					// check if property page is bounded to an object
					if(object != null) {
						String attributeValue = object.getValue(attributeName).asString();
						listXmlNode.setAttribute(DialogProcessor.ATTR_VALUE, attributeValue);
					}
				}
				
			}
			
			LOGGER.debug(result.toString(true));

		} catch (NoClassDefFoundError e) {
			LOGGER.error(e.getMessage(), e);
			throw new D2SilentException();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new D2WarningException("Error during search initialisation", e.getMessage());
		}

		return result;
	}

	private XmlNode appendHiddenTextControl(XmlNode result, @SuppressWarnings("rawtypes") Map attributes, String controlId, String value) {
		// concatenate given id with the id of the parent control, so that the hidden control has a unique id
		controlId += HIDDEN_FIELD_SUFIX_SEPARATOR + attributes.get(DialogProcessor.ATTR_ID);
		
		// store the control id, so that we can add it later on as a dependency
		// otherwise the value of the hidden control would be inaccessible in IJavaAssistance::getValue 
		hiddenControlIds.add(controlId);
		
		XmlNode listSupportXmlNode = result.appendChildNode("text");
		listSupportXmlNode.setAttribute(DialogProcessor.ATTR_ID, controlId);
		// value is a standard argument, thus it is not erased
		listSupportXmlNode.setAttribute(DialogProcessor.ATTR_VALUE, value);
		listSupportXmlNode.setAttribute("control", Boolean.toString(true));
		listSupportXmlNode.setAttribute(DialogProcessor.ATTR_ENABLED_EDIT, Boolean.toString(false));
		listSupportXmlNode.setAttribute(DialogProcessor.ATTR_VISIBLE_EDIT, Boolean.toString(false));

		return listSupportXmlNode;
	}
}

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
package com.emc.rogetmf.webfs.ui.assistance;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;

import com.emc.d2fs.dctm.ui.DialogProcessor;

public class DQLTemplateUtils {

	public static final String ATTR_EVALUATE_ON_INIT = "evaluateOnInit";

	// id of control may consist of a-Z, 0-9, -, and _
	public static final String HIDDEN_FIELD_SUFIX_SEPARATOR = "_-_";
	
	public static final String PARAM_LIST = "list";

	/**
	 * Checks if a hidden control has dependencies. This can be only evaluated during property page initialization
	 * 
	 * @param attributes
	 * @return
	 */
	public static boolean hasNoDependencies(Map<String, Object> attributes) {
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
				if (!StringUtils.contains(dependenciesArray[i], HIDDEN_FIELD_SUFIX_SEPARATOR))
					return false;

			boolean asynchronous = BooleanUtils.toBoolean((String) attributes.get(DialogProcessor.ATTR_ASYNCHRONOUS));
			if (asynchronous)
				// LOGGER.warn("Asynchronous control with no dependencies found. The assistance DQL will be evaluated twice.");

				// all dependencies contained "_-_" - this means the dynamic control has no dependencies
				return true;
		}

		return false;

	}

	public static String getHiddenControlValue(Map<String, Object> attributes, String controlId) {
		// check if control has a preset value or ATTR_EVALUATE_ON_INIT is set to true
		boolean evaluateOnInit = attributes.containsKey(DialogProcessor.ATTR_VALUE)
				|| BooleanUtils.toBoolean((String) attributes.get(ATTR_EVALUATE_ON_INIT));
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
	public static String getHiddenControlValue(Map<String, Object> attributes, String controlId, boolean evaluateOnInit) {
		// when the dialog is initialized, non standard attributes are included in the attributes map
		String controlValue = (String) attributes.get(controlId);

		// if list of is empty we can return the value of hidden control during property page initialization
		if (evaluateOnInit || hasNoDependencies(attributes))
			return controlValue;

		// otherwise, if control has dependencies, first the properties screen needs to be initialized
		// controlValue is only null after property page initialization
		else if (controlValue == null) {
			String controlIdSufixed = controlId + HIDDEN_FIELD_SUFIX_SEPARATOR;
			Iterator<Entry<String, Object>> attributeIterator = attributes.entrySet().iterator();
			while (attributeIterator.hasNext()) {
				Entry<String, Object> attribute = attributeIterator.next();
				if (attribute.getKey().startsWith(controlIdSufixed))
					return (String) attribute.getValue();
			}
		}

		return null;
	}

}

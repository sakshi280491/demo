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
package com.roche.rogetmf.client.model;

import com.extjs.gxt.ui.client.data.ModelType;

/**
 * Used to describe the model of tree nodes stored as JSON
 * 
 * @author Krzysztof Jurkowski
 *
 */
public class TreeModelType extends ModelType {
	public static final String DEFAULT_CHILDREN_PROPERTY = "children";

	private String childrenProperty = null;
	
	private String displayProperty = null;

	public TreeModelType(String displayProperty) {
		super();
		setChildrenProperty(DEFAULT_CHILDREN_PROPERTY);
		setDisplayProperty(displayProperty);
	}

	/**
	 * Constructor with child property passed is argument
	 * 
	 * @param childrenProperty
	 *            Indicates name of the JSON filed containing list of child nodes or boolean value indicating if node has children
	 */
	public TreeModelType(String displayProperty, String childrenProperty) {
		this(displayProperty);
		setChildrenProperty(childrenProperty);
	}

	/**
	 * Returns name of the JSON filed containing list of child nodes or boolean value indicating if node has children
	 * @return
	 */
	public String getChildrenProperty() {
		return childrenProperty;
	}

	/**
	 * Sets name of the JSON filed containing list of child nodes or boolean value indicating if node has children
	 * @param childrenProperty Filed name
	 */
	public void setChildrenProperty(String childrenProperty) {
		this.childrenProperty = childrenProperty;
		addField(childrenProperty, childrenProperty);
	}

	public String getDisplayProperty() {
		return displayProperty;
	}

	public void setDisplayProperty(String displayProperty) {
		this.displayProperty = displayProperty;
	}
}

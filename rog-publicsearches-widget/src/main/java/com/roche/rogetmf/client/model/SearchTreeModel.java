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

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.extjs.gxt.ui.client.data.BaseTreeModel;

/**
 * Public search tree model
 * 
 * @author Krzysztof Jurkowski
 *
 */
public class SearchTreeModel extends BaseTreeModel {
	private final Logger logger = Logger.getLogger(getClass().getName());

	private static final long serialVersionUID = 1L;

	private Boolean hasChildren = null;

	private List<MenuEntry> menuEntries = new ArrayList<MenuEntry>();
	
	public static final String TYPE_NODE_LAST_SEARCH = "node_last_search";
	
	public static final String TYPE_D2C_QUERY_CATEGORY = "d2c_query_category";
	
	public static final String TYPE_D2C_QUERY = "d2c_query";
	
	public static final String TYPE_D2C_QUERY_WIZARD = "d2c_query_wizard";

	public static final String ICON_NODE_LOADING = "icon_node_loading";

	
	
	public SearchTreeModel() {
		super();
	}

	public SearchTreeModel(String id, String label, String type) {
		super();
		setId(id);
		setLabel(label);
		setType(type);
	}

	public SearchTreeModel(String id, String label, String type, SearchTreeModel[] children) {
		this(id, label, type);

		for (int i = 0; i < children.length; i++) {
			add(children[i]);
		}
	}

	public String getId() {
		return get("id");
	}

	public String getLabel() {
		return get("label");
	}

	public String getType() {
		return get("type");
	}

	public String getIcon() {
		return get("icon");
	}

	/**
	 * Default implementation is based only on the size of the list, while the service returns only boolean indicating if node has children
	 * or not.
	 */
	@Override
	public boolean isLeaf() {
		boolean isLeaf = getType().equalsIgnoreCase(TYPE_NODE_LAST_SEARCH) || !((getType().equalsIgnoreCase(TYPE_D2C_QUERY_CATEGORY)) || hasChildren());

		logger.log(Level.FINE, "isLeaf: " + isLeaf + " - " + toString());

		return isLeaf;
	}

	public boolean hasChildren() {
		// if hasChildren was explicitly set return the value, else check the list size
		if (hasChildren == null)
			return getChildren().size() > 0;
		else
			return hasChildren.booleanValue();
	}

	public void setHasChildren(boolean hasChildren) {
		this.hasChildren = Boolean.valueOf(hasChildren);

		logger.log(Level.FINE, "setting has children: " + hasChildren());
	}

	public String setId(String id) {
		return set("id", id);
	}

	public String setLabel(String label) {
		return set("label", label);
	}

	public String setType(String type) {
		return set("type", type);
	}

	public String setIcon(String icon) {
		return set("icon", icon);
	}

	public boolean isNodeLoading() {
		return ICON_NODE_LOADING.equalsIgnoreCase(getIcon());
	}

	public void setNodeLoading(boolean isLoading) {
		if (isLoading || isNodeLoading())
			setIcon(isLoading ? ICON_NODE_LOADING : "");
		
		logger.log(Level.FINE, "setting isLoading: " + isNodeLoading());
	}

	public List<MenuEntry> getMenuEntries() {
		return menuEntries;
	}

	public void setMenuEntries(List<MenuEntry> menuEntries) {
		this.menuEntries = menuEntries;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getLabel() + "(" + getType() + ", " + getId() + ")";
	}
}

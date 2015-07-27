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
package com.roche.rogetmf.client;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.extjs.gxt.ui.client.data.DataField;
import com.extjs.gxt.ui.client.data.DataReader;
import com.extjs.gxt.ui.client.data.TreeModel;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.roche.rogetmf.client.model.SearchTreeModel;
import com.roche.rogetmf.client.model.TreeModelType;

/**
 * Allows to parse JSON data representing tree node(s)
 * @author Krzysztof Jurkowski
 *
 * @param <D>
 */
@SuppressWarnings("deprecation")
public class JsonTreeReader<D> implements DataReader<D> {
	private final Logger logger = Logger.getLogger(getClass().getName());

	private TreeModelType treeModelType;

	/**
	 * Creates a new JSON reader.
	 * 
	 * @param treeModelType
	 *            the model type definition
	 */
	public JsonTreeReader(TreeModelType treeModelType) {
		this.treeModelType = treeModelType;
	}

	@SuppressWarnings({ "unchecked" })
	public D read(Object loadConfig, Object data) {
		TreeModel root = new SearchTreeModel();
		JSONArray nodes = null;

		logger.log(Level.FINE, "JsonTreeReader::read - " + data);

		if (data instanceof JSONArray) {
			logger.log(Level.FINE, "JsonTreeReader::read - data instanceof JSONArray");

			nodes = (JSONArray) data;
		} else {
			if (data instanceof JavaScriptObject) {
				logger.log(Level.FINE, "JsonTreeReader::read - data instanceof JavaScriptObject");

				JSONObject rootNode = new JSONObject((JavaScriptObject) data);
				nodes = (JSONArray) rootNode.get(treeModelType.getRoot());
			} else {
				logger.log(Level.FINE, "JsonTreeReader::read - data instanceof String");

				if (treeModelType.getRoot() != null) {
					logger.log(Level.FINE, "JsonTreeReader::read - parsing JSON String with root:" + treeModelType.getRoot());

					JSONObject rootNode = (JSONObject) JSONParser.parse((String) data);
					nodes = (JSONArray) rootNode.get(treeModelType.getRoot());
				} else {
					logger.log(Level.FINE, "JsonTreeReader::read - parsing JSON array String");
					nodes = (JSONArray) JSONParser.parse((String) data);
				}
			}
		}

		if (nodes == null || nodes.size() == 0)
			logger.log(Level.WARNING, "JsonTreeReader::read - parsed nodes are empty");

		if (nodes != null) {
			for (int i = 0; i < nodes.size(); i++) {
				JSONObject node = (JSONObject) nodes.get(i);
				populateNode(root, node);
			}
		}

		int totalCount = root.getChildCount();

		return (D) createReturnData(loadConfig, root, totalCount);
	}

	private TreeModel populateNode(TreeModel parent, JSONObject obj) {
		SearchTreeModel searchNode = new SearchTreeModel();

		for (int j = 0; j < treeModelType.getFieldCount(); j++) {
			DataField field = treeModelType.getField(j);
			String name = field.getName();
			@SuppressWarnings("rawtypes")
			Class type = field.getType();
			String map = field.getMap() != null ? field.getMap() : field.getName();
			JSONValue value = obj.get(map);

			if (value != null)
				logger.log(Level.FINER, "Processing " + name + ": " + value.toString());
			else
				logger.log(Level.FINER, "Processing " + name + ": null");

			if (value == null)
				continue;

			if (treeModelType.getChildrenProperty().equalsIgnoreCase(name)) {
				if (value.isArray() != null) {
					JSONArray children = value.isArray();
					int size = children.size();

					searchNode.setHasChildren(size > 0);

					for (int i = 0; i < size; i++) {
						JSONObject child = (JSONObject) children.get(i);
						populateNode(searchNode, child);
					}
				} else if (value.isBoolean() != null) {
					searchNode.setHasChildren(value.isBoolean().booleanValue());
				} else if (value.isString() != null) {
					searchNode.setHasChildren(Boolean.parseBoolean(value.isString().stringValue()));
				}
			} else if (value.isBoolean() != null) {
				searchNode.set(name, value.isBoolean().booleanValue());
			} else if (value.isNumber() != null) {
				if (type != null) {
					Double d = value.isNumber().doubleValue();
					if (type.equals(Integer.class)) {
						searchNode.set(name, d.intValue());
					} else if (type.equals(Long.class)) {
						searchNode.set(name, d.longValue());
					} else if (type.equals(Float.class)) {
						searchNode.set(name, d.floatValue());
					} else {
						searchNode.set(name, d);
					}
				} else {
					searchNode.set(name, value.isNumber().doubleValue());
				}
			} else if (value.isObject() != null) {
				// nothing
			} else if (value.isString() != null) {
				String s = value.isString().stringValue();
				if (type != null) {
					if (type.equals(Date.class)) {
						if ("timestamp".equals(field.getFormat())) {
							Date d = new Date(Long.parseLong(s) * 1000);
							searchNode.set(name, d);
						} else {
							DateTimeFormat format = DateTimeFormat.getFormat(field.getFormat());
							Date d = format.parse(s);
							searchNode.set(name, d);
						}
					}
				} else {
					searchNode.set(name, s);
				}
			} else if (value.isNull() != null) {
				searchNode.set(name, null);
			}
		}

		if (parent != null) {
			searchNode.setParent(parent);
			parent.add(searchNode);
		}

		return searchNode;
	}

	/**
	 * Responsible for the object being returned by the reader.
	 * 
	 * @param loadConfig
	 *            the load config
	 * @param records
	 *            the list of models
	 * @param totalCount
	 *            the total count
	 * @return the data to be returned by the reader
	 */
	@SuppressWarnings("unchecked")
	protected Object createReturnData(Object loadConfig, TreeModel records, int totalCount) {
		return (D) records;
	}
}

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
package com.emc.rogetmf.webfs.actions;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.emc.d2fs.dctm.web.services.D2fsContext;
import com.emc.d2fs.models.attribute.Attribute;
import com.emc.d2fs.models.menu.Menu;
import com.emc.rogetmf.d2fs.services.MenuD2fsService;
import com.emc.rogetmf.webfs.action.AbstractCallbackAction;
import com.google.gson.Gson;

/**
 * Returns context menu options for a given item
 * 
 * @author Krzysztof Jurkowski
 *
 */
public class SearchMenuEntriesService extends AbstractCallbackAction {

	private Logger LOGGER = LoggerFactory.getLogger(getClass());

	private static final String NODE_ID = "node_id";

	@Override
	protected Logger getLogger() {
		return LOGGER;
	}

	/**
	 * Returns context menu options for a given item. Result is stored as a JSON
	 * array of {@link com.emc.d2fs.models.menu.Menu} objects.
	 * 
	 * @param context
	 *            Should contain a parameter: <code>node_id</code>, which
	 *            normally is the <code>r_object_id</code> of clicked node or
	 *            reserved word like node_saved_public_searches
	 *
	 * @return
	 * @throws Exception
	 */
	public List<Attribute> getSearchConfigMenuEntries(D2fsContext context)
			throws Exception {
		List<Attribute> result = super.onServiceEntry(context);

		String nodeId = context.getParameterParser().getStringParameter(
				NODE_ID, null);
		LOGGER.info("SearchMenuEntriesService.getSearchConfigMenuEntries nodeId="
				+ nodeId);

		if (StringUtils.isBlank(nodeId))
			throw new NullPointerException(NODE_ID + " is parameter empty");

		Attribute oamValue = new Attribute();
		oamValue.setName("oam_value");
		List<Menu> serviceResult = new ArrayList<Menu>();
		try {
			MenuD2fsService menuService = new MenuD2fsService(context);
			serviceResult = menuService.getMenusContent(context, nodeId,
					"MenuContextTreeNodeSearch", new ArrayList<Attribute>());

		} finally {
			oamValue.setValue(new Gson().toJson(serviceResult));
			LOGGER.info("SearchMenuEntriesService.getSearchConfigMenuEntries oamValue="
					+ oamValue.getValue());
		}

		result.add(oamValue);

		return result;
	}
}

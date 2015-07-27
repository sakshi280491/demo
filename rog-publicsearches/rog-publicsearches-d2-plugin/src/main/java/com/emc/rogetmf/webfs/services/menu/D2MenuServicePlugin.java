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
package com.emc.rogetmf.webfs.services.menu;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.emc.common.java.utils.IVersion;
import com.emc.d2fs.dctm.web.services.ID2fsPlugin;
import com.emc.d2fs.dctm.web.services.menu.D2MenuService;
import com.emc.d2fs.models.attribute.Attribute;
import com.emc.d2fs.models.context.Context;
import com.emc.d2fs.models.menu.Menu;
import com.emc.rogetmf.api.ROGETMFPublicSearchesVersion;

public class D2MenuServicePlugin extends D2MenuService implements ID2fsPlugin {

	private static final IVersion VERSION = new ROGETMFPublicSearchesVersion();

	public String getFullName() {
		return VERSION.getFullName();
	}

	public String getProductName() {
		return VERSION.getProductName();
	}

	protected Logger LOG = LoggerFactory.getLogger(getClass());

	@Override
	public List<Menu> getMenusContent(Context context, String id, String type,
			List<Attribute> attributes) throws Exception {

		LOG.info("D2MenuServicePlugin.getMenusContent id=" + id + ", type="
				+ type);
		for (Attribute attribute : attributes) {

			LOG.info("D2MenuServicePlugin.getMenusContent Attribute name="
					+ attribute.getName() + ", type=" + attribute.getType()
					+ ", value=" + attribute.getValue());
		}

		List<Menu> menus = super.getMenusContent(context, id, type, attributes);

		for (Menu menu : menus) {
			LOG.info("D2MenuServicePlugin.getMenusContent Menu id="
					+ menu.getId() + ", action=" + menu.getAction() + ", name="
					+ menu.getName() + ", type=" + menu.getType() + ", class="
					+ menu.getClass() + ", src=" + menu.getSrc());
		}

		return menus;
	}

	@Override
	public List<Menu> getFullMenusContent(Context context, String id,
			String type, List<Attribute> attributes) throws Exception {

		LOG.info("D2MenuServicePlugin.getFullMenusContent id=" + id + ", type="
				+ type);
		for (Attribute attribute : attributes) {

			LOG.info("D2MenuServicePlugin.getFullMenusContent Attribute name="
					+ attribute.getName() + ", type=" + attribute.getType()
					+ ", value=" + attribute.getValue());
		}

		List<Menu> menus = super.getFullMenusContent(context, id, type,
				attributes);

		for (Menu menu : menus) {
			LOG.info("D2MenuServicePlugin.getFullMenusContent Menu id="
					+ menu.getId() + ", action=" + menu.getAction() + ", name="
					+ menu.getName() + ", type=" + menu.getType() + ", class="
					+ menu.getClass() + ", src=" + menu.getSrc());
		}

		return menus;
	}
}

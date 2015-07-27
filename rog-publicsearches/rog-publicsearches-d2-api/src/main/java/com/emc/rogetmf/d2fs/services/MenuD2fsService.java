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
package com.emc.rogetmf.d2fs.services;

import java.util.List;

import com.emc.d2fs.interfaces.IMenuService;
import com.emc.d2fs.models.attribute.Attribute;
import com.emc.d2fs.models.context.Context;
import com.emc.d2fs.models.menu.Menu;
import com.emc.d2fs.schemas.models.ModelPort;
import com.emc.d2fs.services.menu_service.GetFullMenusTypeRequest;
import com.emc.d2fs.services.menu_service.GetFullMenusTypeResponse;
import com.emc.d2fs.services.menu_service.GetMenusTypeRequest;
import com.emc.d2fs.services.menu_service.GetMenusTypeResponse;

/**
 * Delegate of the {@link com.emc.d2fs.interfaces.IMenuService}
 * 
 * @author Krzysztof Jurkowski
 *
 */
public class MenuD2fsService extends AbstractD2fsService implements
		IMenuService {
	public MenuD2fsService(Context context) {
		super(context);
	}

	@Override
	public boolean isRemote() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setRemote(boolean arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Menu> getFullMenusContent(Context context, String id,
			String type, List<Attribute> attributes) throws Exception {

		GetMenusTypeRequest request = new GetMenusTypeRequest();
		request.setId(id);
		request.setType(type);
		request.setContext(context);

		ModelPort port = getModelPort(context);
		GetMenusTypeResponse response = port.getMenusType(request);

		return response.getMenus();
	}

	@Override
	public List<Menu> getMenusContent(Context context, String id, String type,
			List<Attribute> attributes) throws Exception {
		GetFullMenusTypeRequest request = new GetFullMenusTypeRequest();
		request.setId(id);
		request.setType(type);
		request.setContext(context);

		ModelPort port = getModelPort(context);
		GetFullMenusTypeResponse response = port.getFullMenusType(request);

		return response.getMenus();

	}

}

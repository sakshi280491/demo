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
/**
 * 
 */
package com.emc.rogetmf.webfs.services.search;

import com.emc.common.java.utils.IVersion;
import com.emc.d2fs.dctm.web.services.ID2fsPlugin;
import com.emc.d2fs.dctm.web.services.search.D2SearchService;
import com.emc.rogetmf.api.ROGETMFPublicSearchesVersion;

/**
 * Custom service placeholder added so that plugin version is displayed in the About dialog
 * 
 * @author jurkowk1
 *
 */
public class D2SearchServicePlugin extends D2SearchService implements ID2fsPlugin {

	private static final IVersion VERSION = new ROGETMFPublicSearchesVersion();

	public String getFullName() {
		return VERSION.getFullName();
	}

	public String getProductName() {
		return VERSION.getProductName();
	}
}

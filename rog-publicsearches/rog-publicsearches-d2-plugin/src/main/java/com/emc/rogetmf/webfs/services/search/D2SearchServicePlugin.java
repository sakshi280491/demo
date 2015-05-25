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

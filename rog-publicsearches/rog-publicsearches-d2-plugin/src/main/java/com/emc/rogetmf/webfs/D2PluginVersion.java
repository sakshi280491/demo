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
package com.emc.rogetmf.webfs;
import com.emc.common.java.utils.IVersion;
import com.emc.rogetmf.api.ROGETMFPublicSearchesVersion;

/**
 * Display version number of plugin.
 */
public final class D2PluginVersion implements IVersion {

	// ~ Static fields/initializers -----------------------------------------------------------------

	/** Object version of plugin. */
	private static final IVersion VERSION = new ROGETMFPublicSearchesVersion();

	// ~ Methods ------------------------------------------------------------------------------------

	/**
	 * Return product name followed by version.
	 * 
	 * @return Product name + version.
	 **/
	public String getFullName() {
		return VERSION.getFullName();
	}

	/**
	 * Return product name.
	 * 
	 * @return Product name.
	 **/
	public String getProductName() {
		return VERSION.getProductName();
	}

	/**
	 * Return version number.
	 * 
	 * @return Version number.
	 **/
	public String getVersion() {
		return VERSION.getVersion();
	}

	/**
	 * Return package name.
	 * 
	 * @return Package name.
	 **/
	public String getPackage() {
		return VERSION.getPackage();
	}

	/**
	 * Return product name followed by version.
	 * 
	 * @return Product name + version.
	 **/
	public String toString() {
		return getFullName();
	}
}

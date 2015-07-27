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
package com.emc.d2fs.schemas.models;

import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.emc.d2fs.models.context.Context;

/**
 * Factory of D2FS services
 * 
 * @author Krzysztof Jurkowski
 *
 */
public class ModelPortServiceFactory {
	private static Logger LOGGER = LoggerFactory.getLogger(ModelPortServiceFactory.class);

	private ModelPortService service;
	private static String D2FS_WSDL_URL = "ws/d2fs.wsdl";

	private static final ModelPortServiceFactory INSTANCE = new ModelPortServiceFactory();

	private ModelPortServiceFactory() {
	}

	public static ModelPortServiceFactory getInstance() {
		return INSTANCE;
	}

	/**
	 * Return a D2FS service based on the D2 WebApp URL
	 * 
	 * @param context
	 * @return
	 * @throws Exception
	 */
	public ModelPortService getModelPortService(Context context) throws Exception {
		ModelPortService service = null;

		URL d2URL = new URL(context.getWebAppURL());
		URL wsdlURL = new URL(d2URL, D2FS_WSDL_URL);

		service = getModelPortService(wsdlURL);

		return service;
	}

	/**
	 * Return a D2FS service.
	 * 
	 * @param wsdlURL
	 * @return
	 * @throws Exception
	 */
	public ModelPortService getModelPortService(URL wsdlURL) throws Exception {
		if (service == null) {
			LOGGER.debug("Initializing D2FS services");
			try {
				service = new ModelPortService(wsdlURL);
			} catch (Exception e) {
				if (wsdlURL.getProtocol().equalsIgnoreCase("https")) {
					LOGGER.warn("Failed to instantiate service for {}. Trying to connect via http.", wsdlURL.toString());
					URL httpWsdlURL = new URL("http", wsdlURL.getHost(), wsdlURL.getPort(), wsdlURL.getFile());
					service = new ModelPortService(httpWsdlURL);
				} else
					throw e;
			}
		}

		return service;
	}

}

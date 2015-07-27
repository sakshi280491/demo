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

import com.emc.d2fs.models.context.Context;
import com.emc.d2fs.schemas.models.ModelPort;
import com.emc.d2fs.schemas.models.ModelPortService;
import com.emc.d2fs.schemas.models.ModelPortServiceFactory;

/**
 * Abstract service class providing access to D2FS services
 * 
 * @author Krzysztof Jurkowski
 *
 */
public abstract class AbstractD2fsService {
	private Context context;

	/**
	 * 
	 * @param context {@link com.emc.d2fs.models.context.Context} object containing informations about client/session
	 */
	public AbstractD2fsService(Context context) {
		this.context = context;
	}

	/**
	 * Returns D2FS service
	 * 
	 * @return
	 * @throws Exception 
	 */
	public ModelPortService getService() throws Exception {
		return ModelPortServiceFactory.getInstance().getModelPortService(getContext());
	}

	/**
	 * Returns D2FS service for a given context
	 * 
	 * @param context {@link com.emc.d2fs.models.context.Context} object containing informations about client/session
	 * @return
	 * @throws Exception 
	 */
	public ModelPortService getService(Context context) throws Exception {
		return ModelPortServiceFactory.getInstance().getModelPortService(context);
	}

	/**
	 * Returns proxy of the D2FS services. The proxy allows to execute operations provided by the D2FS services.
	 * 
	 * @return
	 * @throws Exception 
	 */
	public ModelPort getModelPort() throws Exception {
		return getModelPort(getContext());
	}

	/**
	 * Returns proxy of the D2FS services for a given context. The proxy allows to execute operations provided by the D2FS services.
	 * 
	 * @param context {@link com.emc.d2fs.models.context.Context} object containing informations about client/session
	 * @return
	 * @throws Exception 
	 */
	public ModelPort getModelPort(Context context) throws Exception {
		return getService(context).getModelPortSoap11();
	}

	/**
	 * Returns the {@link com.emc.d2fs.models.context.Context} - object containing informations about client/session
	 * @return
	 */
	public Context getContext() {
		return context;
	}

}

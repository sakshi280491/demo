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
package com.emc.rogetmf.webfs.service;

import com.documentum.fc.client.IDfSession;
import com.documentum.fc.common.DfException;
import com.emc.d2fs.dctm.web.services.D2fsContext;
import com.emc.d2fs.exceptions.D2fsException;

/**
 * Abstract service class providing access to repository via DFC classes
 * @author Krzysztof Jurkowski
 *
 */
public abstract class AbstractWebfsService {

	private D2fsContext context;

	/**
	 * 
	 * @param context {@link com.emc.d2fs.models.context.Context} object containing informations about client/session
	 */
	public AbstractWebfsService(D2fsContext context) {
		this.context = context;
	}

	/**
	 * Returns the {@link com.emc.d2fs.models.context.Context} - object containing informations about client/session
	 * @return
	 */
	public D2fsContext getContext() {
		return context;
	}
	
	/**
	 * Returns the {@link com.documentum.fc.client.IDfSession}
	 * @return
	 * @throws DfException
	 * @throws D2fsException
	 */
	public IDfSession getSession() throws DfException, D2fsException{
		return getContext().getSession();
	}
}

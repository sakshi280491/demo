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
package com.roche.rogetmf.client.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.roche.rogetmf.shared.oah.OpenAjaxHub;
import com.roche.rogetmf.shared.oah.OpenAjaxMessage;
import com.roche.rogetmf.shared.oah.OpenAjaxMessageFactory;
import com.roche.rogetmf.shared.oah.ServiceCallback;

/**
 * Implementation of the IBrowserContentService based on {@link com.emc.rogetmf.webfs.actions.BrowserContentService} action services
 * 
 * @author Krzysztof Jurkowski
 *
 */
public class BrowserContentService implements IBrowserContentService {
	private final Logger logger = Logger.getLogger(getClass().getName());

	private OpenAjaxHub openAjaxHub;

	public BrowserContentService(OpenAjaxHub openAjaxHub) {
		logger.log(Level.FINE, "Creating BrowserContentService");
		this.openAjaxHub = openAjaxHub;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.roche.rogetmf.client.service.IBrowserContentService#getBrowserContents(java.lang.String, java.lang.String, java.lang.String, com.roche.rogetmf.shared.oah.ServiceCallback)
	 */
	public void getBrowserContents(String type, String parentId, ServiceCallback<OpenAjaxMessage> callback) {
		logger.log(Level.FINE, "Calling BrowserContentService.getBrowserContent - type: " + type + ", parentId: " + parentId);

		OpenAjaxMessage message = OpenAjaxMessageFactory.create();
		message.setParameter("node_type", type);
		message.setParameter("parent_id", parentId);

		openAjaxHub.callService("BrowserContentService", "getBrowserContent", message, callback);
	}

}

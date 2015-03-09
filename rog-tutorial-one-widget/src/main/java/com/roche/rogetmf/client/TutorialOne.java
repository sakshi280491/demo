/*******************************************************************************
 * Copyright © 2015 Hoffmann-La Roche
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.roche.rogetmf.client;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.roche.rogetmf.shared.oah.HubConnectionMessage;
import com.roche.rogetmf.shared.oah.OpenAjaxHub;
import com.roche.rogetmf.shared.oah.OpenAjaxHubFactory;

/**
 * Entry point for the widget - responsible for establishing connection with Open Ajax Hub and initializing the GUI
 * 
 * @author jurkowk1
 *
 */
public class TutorialOne implements EntryPoint {

	private final Logger logger = Logger.getLogger(getClass().getName());

	private OpenAjaxHub openAjaxHub;

	public void onModuleLoad() {
		// subscribe widget to events
		OpenAjaxHubFactory openAjaxHubFactory = new OpenAjaxHubFactory(true, "D2_EVENT_SELECT_OBJECT");
		openAjaxHubFactory.setConnectCompletedCallback(new AsyncCallback<HubConnectionMessage>() {

			public void onSuccess(HubConnectionMessage result) {
				// widget successfully connected to
				logger.log(Level.INFO, "TutorialOne::onModuleLoad: connected to hub successfully");

				setOpenAjaxHub(result.getHubClient());
				RootPanel.get().add(new TutorialOneContainer(getOpenAjaxHub()));
			}

			public void onFailure(Throwable caught) {
				logger.log(Level.WARNING, "TutorialOne::onModuleLoad: failed to connect to the hub " + caught.getMessage());
				Info.display("TutorialOne widget", "Failed to connect to Open Ajax Hub");
			}
		});

		openAjaxHubFactory.connect();
	}

	/**
	 * Returns the D2 Open Ajax Hub for this widget.
	 * 
	 * @return D2 Open Ajax Hub for this widget. <code>null</code> will be returned if widget has not yet connected to Open Ajax Hub
	 */
	public OpenAjaxHub getOpenAjaxHub() {
		return openAjaxHub;
	}

	private void setOpenAjaxHub(OpenAjaxHub openAjaxHub) {
		this.openAjaxHub = openAjaxHub;
	}
}

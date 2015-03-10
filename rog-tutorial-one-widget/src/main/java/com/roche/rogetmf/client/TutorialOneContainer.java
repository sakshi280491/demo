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

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.roche.rogetmf.shared.oah.OpenAjaxHub;
import com.roche.rogetmf.shared.oah.OpenAjaxMessage;
import com.roche.rogetmf.shared.oah.OpenAjaxMessageFactory;

/**
 * Simple widget demonstrating OAH communication with a widget
 * The widget listens to the object selection event, and allows to bump the selected object
 * 
 * @author Krzysztof Jurkowski
 *
 */
public class TutorialOneContainer extends LayoutContainer {
	private final Logger logger = Logger.getLogger(getClass().getName());

	private final OpenAjaxHub openAjaxHub;
	
	private Button viewButton;
	
	private String selectedObjectId;

	public TutorialOneContainer(OpenAjaxHub openAjaxHub) {
		super();
		this.openAjaxHub = openAjaxHub;

		logger.log(Level.INFO, "Creating TutorialOneContainer");

		// adding listener for the D2_EVENT_SELECT_OBJECT
		AsyncCallback<OpenAjaxMessage> selectionCallback = new AsyncCallback<OpenAjaxMessage>() {
			public void onSuccess(OpenAjaxMessage result) {
				logger.log(Level.INFO, "D2_EVENT_SELECT_OBJECT arrived " + result.getId());
				viewButton.setEnabled(true);

				String id = result.getId();
				String name = result.getParameter("object_name");
				
				// store the id of selected object
				setSelectedObjectId(id);

				Info.display("Selected object", name + " " + id);
			}

			public void onFailure(Throwable caught) {
				logger.log(Level.WARNING, "D2_EVENT_SELECT_OBJECT failure: " + caught.getMessage());
				viewButton.setEnabled(false);
			}
		};

		logger.log(Level.FINE, "Subscribing D2_EVENT_SELECT_OBJECT, D2_EVENT_SELECT_OBJECT");
		this.openAjaxHub.subscribe("D2_EVENT_SELECT_OBJECT", selectionCallback);
		
		// adding the view button
		add(getViewButton());
		
	}

	/**
	 * @return the viewButton
	 */
	private Button getViewButton() {
		if(viewButton == null) {
			// initializing button which will view object using D2_ACTION_CONTENT_VIEW
			viewButton = new Button("View object", new SelectionListener<ButtonEvent>() {
			      @Override
			      public void componentSelected(ButtonEvent ce) {
			    		OpenAjaxMessage message = OpenAjaxMessageFactory.create();
			    		message.setId(getSelectedObjectId());
			    		openAjaxHub.send("D2_ACTION_CONTENT_VIEW", message);
			      }

			    });
			viewButton.setEnabled(false);
			viewButton.setAutoHeight(true);
			viewButton.setAutoWidth(true);
		}
		return viewButton;
	}

	/**
	 * Returns id of object selected by a user
	 * @return the selectedObjectId
	 */
	public String getSelectedObjectId() {
		return selectedObjectId;
	}

	/**
	 * Sets the id of object selected by a user
	 * @param selectedObjectId Id of the selected object
	 */
	private void setSelectedObjectId(String selectedObjectId) {
		this.selectedObjectId = selectedObjectId;
	}
	
	
}

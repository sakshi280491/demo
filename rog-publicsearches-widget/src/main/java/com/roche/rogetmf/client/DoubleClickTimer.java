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
package com.roche.rogetmf.client;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.extjs.gxt.ui.client.event.BoxComponentEvent;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Timer;

public class DoubleClickTimer extends Timer {

	private final Logger logger = Logger.getLogger(getClass().getName());

	private int clickCount = 0;

	private int clickTimeout;

	public DoubleClickTimer(int clickTimeout) {
		super();
		this.clickTimeout = clickTimeout;
	}

	@Override
	public void run() {
		logger.log(Level.FINEST, "Double click timer delay reached.");
		setClickCount(0);
	}

	public void handleClick(BoxComponentEvent event) {
		if (event != null && event.getEventTypeInt() == Event.BUTTON_LEFT) {
			logger.log(Level.FINEST, "Recorded left click");
			addClickCount();

			cancel();
			schedule(getClickTimeout());
		} else
			logger.log(Level.FINEST, "NON left click occured");
	}

	/**
	 * @return the clickTimeout
	 */
	public int getClickTimeout() {
		return clickTimeout;
	}

	/**
	 * @return the clickCount
	 */
	private int getClickCount() {
		return clickCount;
	}

	private void addClickCount() {
		setClickCount(clickCount + 1);
	}

	/**
	 * @param clickCount
	 *            the clickCount to set
	 */
	private void setClickCount(int clickCount) {
		this.clickCount = clickCount;
		logger.log(Level.FINEST, "Click count set to: " + getClickCount());
	}

	public boolean isSingleClicked() {
		return clickCount == 1;
	}

}

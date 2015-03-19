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
package com.roche.rogetmf.shared.oah;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Represents the JS object supplied by OAH on connection callback 
 * @author Krzysztof Jurkowski
 *
 */
public class HubConnectionMessage extends JavaScriptObject {
	protected HubConnectionMessage() {}

	/**
	 * Returns a OAH client 
	 * @return
	 */
	//@formatter:off
	public final native OpenAjaxHub getHubClient()/*-{ 
		console.log("HubConnectionMessage::getHubClient");
		return this.hubClient; 
		
	}-*/;
	
	/**
	 * Informs if connection was successful
	 * @return <code>true</code> is connection was successful
	 */
	public final native boolean isSuccess()/*-{ 
		console.log("HubConnectionMessage::isSuccess: " + this.success);
		return this.success; 
		
	}-*/;
	
	
	/**
	 * Informs if connection finished with error
	 * @return <code>true</code> is connection was unsuccessful
	 */
	public final native boolean isError()/*-{ 
		console.log("HubConnectionMessage::isError: " + this.error);
		
		return this.error; 
	}-*/;
}

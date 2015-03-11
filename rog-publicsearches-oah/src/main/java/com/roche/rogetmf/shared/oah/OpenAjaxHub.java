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
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * JSNI interface for the OAH client
 * 
 * @author Krzysztof Jurkowski
 *
 */
public class OpenAjaxHub extends JavaScriptObject {
	protected OpenAjaxHub() {
	}

	/**
	 * Calls a plugin action service, extending {@link com.emc.rogetmf.webfs.action.AbstractCallbackAction}. This method passes empty
	 * message to the service.
	 * 
	 * @param service
	 *            Name of the action service class
	 * @param method
	 *            Name of the method of the service
	 * @param callback
	 *            Callback object triggered on service response
	 */
	public final void callService(String service, String method, ServiceCallback<OpenAjaxMessage> callback) {
		OpenAjaxMessage msg = OpenAjaxMessageFactory.create();
		callService(service, method, msg, callback);
	}

	/**
	 * Sends a given message to a plugin action service, extending {@link com.emc.rogetmf.webfs.action.AbstractCallbackAction}.
	 * 
	 * @param service
	 *            Name of the action service class
	 * @param method
	 *            Name of the method of the service
	 * @param msg
	 *            Message sent to the service
	 * @param callback
	 *            Callback object triggered on service response. Service message is passed to the onSuccess method of the callback handler.
	 */
	public final void callService(String service, String method, OpenAjaxMessage msg, ServiceCallback<OpenAjaxMessage> callback) {
		msg.setParameter("CHANNEL_EVENT", "D2_ACTION_EXECUTE");
		msg.setParameter("eService", service);
		msg.setParameter("eMethod", method);
		msg.setParameter("eMode", "SINGLE");

		msg.setServiceCallback(callback);

		send("D2_ACTION_EXECUTE", msg);
	}

	/**
	 * @return widget's context string
	 */
	//@formatter:off
	public final native String getContextUid() /*-{
		return this.sContextUid;
	}-*/;
	
	/**
	 * @return identifier of the widget
	 */
	public final native String getWidgetId() /*-{
		return this.sWidgetId;
	}-*/;
	
	/**
	 * @return type of the widget, i.e. ExternalWidget
	 */
	public final native String getWidgetType() /*-{
		return this.sWidgetType;
	}-*/;

	public final native boolean isActive() /*-{
		return @java.lang.Boolean::valueOf(Z)(this.bActive);
	}-*/;
	
	public final native JsArrayString getRegisteredChannelIds() /*-{
		return this.registeredChannelIds;
	}-*/;
	
	/**
	 * @return list of channels to which the widget can subscribe
	 */
	public final native JsArrayString getRegisteredChannels() /*-{
		return this.registeredChannels;
	}-*/;

	
	/**
	 * Sends an OAH message to a channel
	 * @param chanel Name of the channel
	 * @param message The OAH message
	 */
	public final native void send(String channelName, OpenAjaxMessage message) /*-{
		console.log("OpenAjaxHub::send: " + channelName);
		this.sendMessage(channelName, message);
	}-*/;
	
	/**
	 * Adds an asynchronous callback handler for an event to which the OAH client subscribed.
	 * When the event is raised, callback's onSuccess method will be called. 
	 * The OAH message that came with the event, will be passed to the onSuccess method.
	 * @param eventName Name of the event
	 * @param callback Callback object triggered on the event
	 */
	public final native void subscribe(String eventName, AsyncCallback<OpenAjaxMessage> callback) /*-{
		// create new event callback handlers array, if not yet initialized for this even
		if(typeof $wnd.eventCallbackTable[eventName] == "undefined" || $wnd.eventCallbackTable[eventName] == null) {
			$wnd.eventCallbackTable[eventName] = [];
		}
		
		// add event callback handler 
		$wnd.eventCallbackTable[eventName].push(function(result) {
			callback.@com.google.gwt.user.client.rpc.AsyncCallback::onSuccess(*)(result);
		});
	}-*/;
}

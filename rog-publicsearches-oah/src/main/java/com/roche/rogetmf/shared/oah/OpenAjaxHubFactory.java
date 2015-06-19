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

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Factory class for OAH client creation
 * 
 * @author Krzysztof Jurkowski
 *
 */
public class OpenAjaxHubFactory {

	private final Logger logger = Logger.getLogger(getClass().getName());

	private String[] subscribedChannels;

	private boolean handleMessageIfInactive;

	private AsyncCallback<HubConnectionMessage> connectCompletedCallback;

	private AsyncCallback<Boolean> activeWidgetCallback;

	private AsyncCallback<OpenAjaxMessage> initWidgetCallback;

	private AsyncCallback<HubEventMessage> oahCallback;

	/**
	 * Simplified version of the factory constructor, that utilizes default OAH handlers, i.e. initialization, connection, activation, event
	 * callbacks are processed by default implementation of this factory
	 * 
	 * @param handleMessageIfInactive
	 *            <code>true</code> if widget should receive events if inactive
	 * @param subscribedChannels
	 *            List of channels to which the widget should subscribe. If widget is calling D2 action services, it must subscribe to
	 *            D2_EVENT_CUSTOM event
	 */
	public OpenAjaxHubFactory(boolean handleMessageIfInactive, String... subscribedChannels) {
		logger.log(Level.INFO, "[OpenAjaxHubFactory] constructor");

		this.subscribedChannels = subscribedChannels;
		this.handleMessageIfInactive = handleMessageIfInactive;

		connectCompletedCallback = new AsyncCallback<HubConnectionMessage>() {

			public void onFailure(Throwable caught) {
				logger.log(Level.WARNING, "[OpenAjaxHubFactory] connectCompletedCallback::onFailure: " + caught.getMessage());
			}

			public void onSuccess(HubConnectionMessage result) {
				logger.log(Level.INFO, "[OpenAjaxHubFactory] connectCompletedCallback::onSuccess: connected to hub successfully");
			}
		};

		activeWidgetCallback = new AsyncCallback<Boolean>() {
			public void onSuccess(Boolean result) {
				logger.log(Level.INFO, "[OpenAjaxHubFactory] activeWidgetCallback::onSuccess: " + result);
			}

			public void onFailure(Throwable caught) {
				logger.log(Level.WARNING, "[OpenAjaxHubFactory] activeWidgetCallback::onFailure: " + caught.getMessage());
			}
		};

		initWidgetCallback = new AsyncCallback<OpenAjaxMessage>() {
			public void onSuccess(OpenAjaxMessage result) {
				logger.log(Level.INFO, "[OpenAjaxHubFactory] initWidgetCallback::onSuccess");
			}

			public void onFailure(Throwable caught) {
				logger.log(Level.WARNING, "[OpenAjaxHubFactory] activeWidgetCallback::onFailure: " + caught.getMessage());
			}
		};

		oahCallback = new AsyncCallback<HubEventMessage>() {
			public void onSuccess(HubEventMessage result) {
				logger.log(Level.INFO, "[OpenAjaxHubFactory] oahCallback event captured");

				String eventName = result.getEventName();
				OpenAjaxMessage message = result.getMessage();

				logger.log(Level.INFO, "[OpenAjaxHubFactory] event: " + eventName);

				if (message != null) {
					logger.log(Level.FINE, "[OpenAjaxHubFactory] id: " + message.getId());
					logger.log(Level.FINE, "[OpenAjaxHubFactory] type: " + message.getParameter("type"));
				} else {
					logger.log(Level.WARNING, "[OpenAjaxHubFactory] message is null");
				}

				if ("D2_EVENT_CUSTOM".equalsIgnoreCase(eventName)) {
					String service = message.getParameter("eService");
					String method = message.getParameter("eMethod");
					String callbackCode = message.getParameter("callback_code");
					logger.log(Level.FINE, "[OpenAjaxHubFactory] action: " + service + "." + method);
					logger.log(Level.FINE, "[OpenAjaxHubFactory] callback_code: " + callbackCode);

					if (service == null && method == null && callbackCode == null) {
						callEventCallback(eventName, message);
					} else {
						String exceptionMessage = message.getParameter("service_exception");
						String dfExceptionMessageId = message.getParameter("service_dfexception_id");

						if (exceptionMessage != null)
							logger.log(Level.WARNING, "[OpenAjaxHubFactory] Service " + service + "." + method + " returned exception: "
									+ exceptionMessage);

						if (dfExceptionMessageId != null)
							logger.log(Level.WARNING, "[OpenAjaxHubFactory] Service " + service + "." + method + " returned DfException: "
									+ dfExceptionMessageId);

						callServiceCallback(service, method, callbackCode, message);
					}

				} else {
					callEventCallback(eventName, message);
				}
			}

			public void onFailure(Throwable caught) {
				logger.log(Level.WARNING, "[OpenAjaxHubFactory] oahCallback::onFailure: " + caught.getMessage());
			}
		};
	}

	/**
	 * Default constructor of the factory, allowing to provide custom callback to all OAH events (initialization, connection, activation,
	 * event).
	 * 
	 * @param handleMessageIfInactive
	 *            <code>true</code> if widget should receive events if inactive
	 * @param connectCompletedCallback
	 *            Callback raised when OAH connection is established (might be an notification of a connection error as well)
	 * @param activeWidgetCallback
	 *            Callback raised on widget activation
	 * @param initWidgetCallback
	 *            Callback raised on widget initialization
	 * @param oahCallback
	 *            Callback raised on event to which the widget has subscribed
	 * @param subscribedChannels
	 *            List of channels to which the widget should subscribe. If widget is calling D2 action services, it must subscribe to
	 *            D2_EVENT_CUSTOM event
	 */
	public OpenAjaxHubFactory(boolean handleMessageIfInactive, AsyncCallback<HubConnectionMessage> connectCompletedCallback,
			AsyncCallback<Boolean> activeWidgetCallback, AsyncCallback<OpenAjaxMessage> initWidgetCallback,
			AsyncCallback<HubEventMessage> oahCallback, String... subscribedChannels) {
		this.subscribedChannels = subscribedChannels;
		this.handleMessageIfInactive = handleMessageIfInactive;
		this.connectCompletedCallback = connectCompletedCallback;
		this.activeWidgetCallback = activeWidgetCallback;
		this.initWidgetCallback = initWidgetCallback;
		this.oahCallback = oahCallback;
	}

	/**
	 * Sets the callback handler raised when OAH connection is established (might be an notification of a connection error as well)
	 * 
	 * @param connectCompletedCallback
	 *            callback handler
	 */
	public void setConnectCompletedCallback(AsyncCallback<HubConnectionMessage> connectCompletedCallback) {
		this.connectCompletedCallback = connectCompletedCallback;
	}

	/**
	 * Sets the callback handler raised on widget activation
	 * 
	 * @param activeWidgetCallback
	 *            callback handler
	 */
	public void setActiveWidgetCallback(AsyncCallback<Boolean> activeWidgetCallback) {
		this.activeWidgetCallback = activeWidgetCallback;
	}

	/**
	 * Sets the callback handler raised on widget initialization
	 * 
	 * @param initWidgetCallback
	 *            callback handler
	 */
	public void setInitWidgetCallback(AsyncCallback<OpenAjaxMessage> initWidgetCallback) {
		this.initWidgetCallback = initWidgetCallback;
	}

	/**
	 * Sets the callback handler raised on event to which the widget has subscribed
	 * 
	 * @param oahCallback
	 *            callback handler
	 */
	private void setOahCallback(AsyncCallback<HubEventMessage> oahCallback) {
		this.oahCallback = oahCallback;
	}

	/**
	 * Connects the OAH client to D2
	 */
	public void connect() {
		connect(handleMessageIfInactive, subscribedChannels, connectCompletedCallback, activeWidgetCallback, initWidgetCallback,
				oahCallback);
	}

	/**
	 * Connects the OAH client to D2 and assigns given callback handlers to to all OAH events (initialization, connection, activation,
	 * event).
	 * 
	 * @param handleMessageIfInactive
	 *            <code>true</code> if widget should receive events if inactive
	 * @param subscribedChannels
	 *            List of channels to which the widget should subscribe. If widget is calling D2 action services, it must subscribe to
	 *            D2_EVENT_CUSTOM event
	 * @param connectCompletedCallback
	 *            Callback raised when OAH connection is established (might be an notification of a connection error as well)
	 * @param activeWidgetCallback
	 *            Callback raised on widget activation
	 * @param initWidgetCallback
	 *            Callback raised on widget initialization
	 * @param oahCallback
	 *            Callback raised on event to which the widget has subscribed
	 */
	//@formatter:off
	private native void connect(boolean handleMessageIfInactive, String[] subscribedChannels,
			AsyncCallback<HubConnectionMessage> connectCompletedCallback, AsyncCallback<Boolean> activeWidgetCallback,
			AsyncCallback<OpenAjaxMessage> initWidgetCallback, AsyncCallback<HubEventMessage> oahCallback) /*-{ 
		console.log("[OpenAjaxHubFactory] connect");
		
		console.log("[OpenAjaxHubFactory] create - creating eventCallbackTable");
		$wnd.eventCallbackTable = {};
		
		console.log("[OpenAjaxHubFactory] create - creating serviceCallbackTable");
		$wnd.serviceCallbackTable = {};
		
		console.log("[OpenAjaxHubFactory] create - declaring oahCallback");
		
		$wnd.oahCallbackWrapper = function(sChannel, oMessage) {
			console.log("[OpenAjaxHubFactory] oahCallback event captured: " + sChannel);

			var hubEventMessage = {};
			hubEventMessage.eventName = sChannel;
			hubEventMessage.message = oMessage;
			
			oahCallback.@com.google.gwt.user.client.rpc.AsyncCallback::onSuccess(*)(hubEventMessage);
	    };
		
		console.log("[OpenAjaxHubFactory] create - declaring connectCompletedCallbackWrapper");
		$wnd.connectCompletedCallbackWrapper = function(oHubClient, bSuccess, bError) {
			console.log("[OpenAjaxHubFactory] OpenAjaxHub connected: " + bSuccess);
			
			var hubConnectionMessage = {};
			hubConnectionMessage.hubClient = $wnd.d2OpenAjaxHub;
			hubConnectionMessage.success = bSuccess;
			hubConnectionMessage.error = bError;
			
			if (bSuccess) {			
				$wnd.d2OpenAjaxHub.subscribeToChannels(subscribedChannels, $wnd.oahCallbackWrapper, handleMessageIfInactive);
				
				connectCompletedCallback.@com.google.gwt.user.client.rpc.AsyncCallback::onSuccess(*)(hubConnectionMessage);
			} else {
				var error = new Error("Failed to connect to Open Ajax Hub");
				connectCompletedCallback.@com.google.gwt.user.client.rpc.AsyncCallback::onFailure(*)(error);
			}
			
		};
		
		console.log("[OpenAjaxHubFactory] create - declaring initWidgetCallbackWrapper");
		$wnd.initWidgetCallbackWrapper = function(oMessage) {
			initWidgetCallback.@com.google.gwt.user.client.rpc.AsyncCallback::onSuccess(*)(oMessage);
		};
		
		console.log("[OpenAjaxHubFactory] create - declaring activeWidgetCallbackWrapper");
		$wnd.activeWidgetCallbackWrapper = function(bActiveFlag) {
			activeWidgetCallback.@com.google.gwt.user.client.rpc.AsyncCallback::onSuccess(*)(@java.lang.Boolean::valueOf(Z)(bActiveFlag));
		};
		
		console.log("[OpenAjaxHubFactory] create - calling D2OpenAjaxHub.connectHub");
		$wnd.d2OpenAjaxHub = new $wnd.D2OpenAjaxHub(); 
		$wnd.d2OpenAjaxHub.connectHub($wnd.connectCompletedCallbackWrapper, $wnd.initWidgetCallbackWrapper, $wnd.activeWidgetCallbackWrapper);
	}-*/;
	
	/**
	 * JSNI handler for subscribed events
	 * @param sChannel Name of the event that triggered the callback 
	 * @param oMessage OAH message that arrived with the event
	 * @return <code>true</code> if a callback for this event was executed, <code>false</code> if no callback was subscribed to this channel 
	 */
	private native boolean callEventCallback(String sChannel, OpenAjaxMessage oMessage) /*-{ 
		if(typeof $wnd.eventCallbackTable[sChannel] != "undefined" && $wnd.eventCallbackTable[sChannel] != null) {
    		var eventCallbackHandlers = $wnd.eventCallbackTable[sChannel];	
    		
    		if(Array.isArray(eventCallbackHandlers)) {
    			console.log("[OpenAjaxHubFactory] Calling callback(s) of event " + sChannel);
        		
        		var arrayLength = eventCallbackHandlers.length;
        		if(arrayLength == 0) {
        			console.log("[OpenAjaxHubFactory] No handlers for event: " + sChannel);
        			return false;
        		}
        		
        		for(var handlerInd = 0; handlerInd < arrayLength; handlerInd++) {
				    var eventCallback = eventCallbackHandlers[handlerInd];
        			eventCallback(oMessage);
				}
				
				return true;
			}
    	} 
    	
		console.log("[OpenAjaxHubFactory] No handler for event: " + sChannel);
		return false;
	}-*/;
	
	/**
	 * JSNI handler for action services, extending {@link com.emc.rogetmf.webfs.action.AbstractCallbackAction}
	 * @param sService Name of the service form which the message came form
	 * @param sMethod Name of the method executed in the service
	 * @param sCallbackCode Hashcode of the callback object associated with this call to the service
	 * @param oMessage OAH message that arrived from the service
	 * @return <code>true</code> if a callback for this service was executed, <code>false</code> if no callback was found
	 */
	private native boolean callServiceCallback(String sService, String sMethod, String sCallbackCode, OpenAjaxMessage oMessage) /*-{ 
		// check if there is a callback for the callbackCode
		if(typeof $wnd.serviceCallbackTable[sCallbackCode] != "undefined") {
    		console.log("[OpenAjaxHubFactory] Calling callback of service: " + sService + "." + sMethod);
    		
    		var serviceCallback = $wnd.serviceCallbackTable[sCallbackCode];
    		serviceCallback(oMessage);
    		
    		return true;
       	} 
       	
		console.log("[OpenAjaxHubFactory] No handler for service: " + sAction);
		return false;
	}-*/;
}
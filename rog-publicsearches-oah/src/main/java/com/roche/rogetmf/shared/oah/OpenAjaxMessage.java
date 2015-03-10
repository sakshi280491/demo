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

/**
 * Represents a message send or received via OAH
 * 
 * @author Krzysztof Jurkowski
 *
 */
public class OpenAjaxMessage extends JavaScriptObject {
	protected OpenAjaxMessage() {
	}

	/**
	 * Stores the callback code of a given callback object. It is used to resolve the callback when asynchronous response message arrives
	 * from service.
	 * 
	 * @param callback
	 *            Callback object executed upon response from the service
	 */
	protected final void setServiceCallback(ServiceCallback<OpenAjaxMessage> callback) {
		setParameter("rAction", "D2_EVENT_CUSTOM");
		setParameter("rType", "EVENT");
		setParameter("callback_code", callback.getCallbackWrapperCode());
	}

	/**
	 * Returns id of the object associated with the message. If message contains multiple ids, only the first one is returned.
	 * 
	 * @return
	 */
	public final String getId() {
		return getId(false);
	}

	/**
	 * Returns value associated with the message.
	 * 
	 * @return
	 */
	public final String getValue() {
		return getParameter("oam_value");
	}

	/**
	 * Sets value associated with the message.
	 * 
	 * @param value
	 */
	public final void setValue(String value) {
		setParameter("oam_value", value);
	}

	/**
	 * Returns the value of a filed in the message
	 * @param paramName Name of the field
	 * @return Value of a filed
	 */
	//@formatter:off
	public final native String getParameter(String paramName)/*-{ 
		console.log("getParameter: " + paramName + " = " + this.get(paramName));
		return this.get(paramName); 
	}-*/;

	/**
	 * Sets value of a filed in the message
	 * @param paramName Name of the field
	 * @param value Value of the field to be set
	 */
	public final native void setParameter(String paramName, String value)/*-{ 
		console.log("setParameter: " + paramName + " = " + value);
		return this.put(paramName, value); 
	}-*/;

	/**
	 * Refer to the white paper "EMC Documentum D2 External Widgets - A guide for developers"	
	 */
	public final native boolean isGlobal()/*-{ 
		return this.isGlobal();
	}-*/;

	/**
	 * Refer to the white paper "EMC Documentum D2 External Widgets - A guide for developers"	
	 */
	public final native void setGlobal(boolean global) /*-{ 
		this.setGlobal(global);
	}-*/;
	
	/**
	 * Refer to the white paper "EMC Documentum D2 External Widgets - A guide for developers"	
	 */
	public final native String getId(boolean raw) /*-{ 
		return this.getId(raw);
	}-*/;

	/**
	 * Refer to the white paper "EMC Documentum D2 External Widgets - A guide for developers"	
	 */
	public final native void setId(String id) /*-{ 
		this.setId(id);
	}-*/;

	/**
	 * Refer to the white paper "EMC Documentum D2 External Widgets - A guide for developers"	
	 */
	public final native JsArrayString getIds() /*-{ 
		return this.getIds();
	}-*/;

	/**
	 * Refer to the white paper "EMC Documentum D2 External Widgets - A guide for developers"	
	 */
	public final native boolean isMultipleIds() /*-{ 
		return this.isMultipleIds();
	}-*/;

	/**
	 * Refer to the white paper "EMC Documentum D2 External Widgets - A guide for developers"	
	 */
	public final native String getTargetId() /*-{ 
		return this.getTargetId();
	}-*/;

	/**
	 * Refer to the white paper "EMC Documentum D2 External Widgets - A guide for developers"	
	 */
	public final native void setTargetId(String targetId) /*-{ 
		this.setTargetId(targetId);
	}-*/;

	/**
	 * Refer to the white paper "EMC Documentum D2 External Widgets - A guide for developers"	
	 */
	public final native String getTargetType() /*-{ 
		return this.getTargetType();
	}-*/;

	/**
	 * Refer to the white paper "EMC Documentum D2 External Widgets - A guide for developers"	
	 */
	public final native void setTargetType(String targetType) /*-{ 
		this.setTargetType(targetType);
	}-*/;

	/**
	 * Refer to the white paper "EMC Documentum D2 External Widgets - A guide for developers"	
	 */
	public final native String getExcludedType() /*-{ 
		return this.getExcludedType();
	}-*/;

	/**
	 * Refer to the white paper "EMC Documentum D2 External Widgets - A guide for developers"	
	 */
	public final native void setExcludedType(String excludedType) /*-{ 
		this.getExcludedType(excludedType);
	}-*/;

	/**
	 * Refer to the white paper "EMC Documentum D2 External Widgets - A guide for developers"	
	 */
	public final native String getExcludedId() /*-{ 
		return this.getExcludedId();
	}-*/;

	/**
	 * Refer to the white paper "EMC Documentum D2 External Widgets - A guide for developers"	
	 */
	public final native void setExcludedId(String excludedId) /*-{ 
		this.setExcludedId(excludedId);
	}-*/;

	/**
	 * Refer to the white paper "EMC Documentum D2 External Widgets - A guide for developers"	
	 */
	public final native String getContainerUid() /*-{ 
		return this.getContainerUid();
	}-*/;

	/**
	 * Refer to the white paper "EMC Documentum D2 External Widgets - A guide for developers"	
	 */
	public final native void setContainerUid(String containerUid) /*-{ 
		this.setContainerUid(containerUid);
	}-*/;

	/**
	 * Refer to the white paper "EMC Documentum D2 External Widgets - A guide for developers"	
	 */
	public final native String getSender() /*-{ 
		return this.getSender();
	}-*/;

	/**
	 * Refer to the white paper "EMC Documentum D2 External Widgets - A guide for developers"	
	 */
	public final native String setSender(String sender) /*-{ 
		this.setSender(sender);
	}-*/;

}

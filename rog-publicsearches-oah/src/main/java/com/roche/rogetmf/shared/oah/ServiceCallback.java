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

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Extension of the AsyncCallback for handling communication with D2 action services
 * 
 * @author Krzysztof Jurkowski
 *
 * @param <D>
 */
public abstract class ServiceCallback<D> implements AsyncCallback<D> {

	public ServiceCallback() {
		createCallbackWrapper(this, getCallbackWrapperCode());
	}

	abstract public void onFailure(Throwable caught);

	/**
	 * Called when D2 action services returns a message to the OAH
	 */
	abstract public void onSuccess(D result);

	public String getCallbackWrapperCode() {
		return Integer.toString(hashCode());
	}

	//@formatter:off
	private native void createCallbackWrapper(ServiceCallback<D> callback, String hashCode) /*-{
		$wnd.serviceCallbackTable[hashCode] = function(result) {
			callback.@com.roche.rogetmf.shared.oah.ServiceCallback::onSuccess(*)(result);
		};
	}-*/;

}

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

/**
 * JSNI factory for OAH message
 * @author Krzysztof Jurkowski
 *
 */
public class OpenAjaxMessageFactory {

	/**
	 * Creates an instance of Open Ajax Message
	 * @return New instance of Open Ajax Message
	 */
	//@formatter:off
	public static native OpenAjaxMessage create() /*-{ 
		console.log("OpenAjaxMessageFactory::create");
		return new $wnd.OpenAjaxMessage();
	}-*/;

}

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
package com.emc.rogetmf.webfs.action;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

import com.documentum.fc.common.DfException;
import com.emc.common.java.utils.ArgumentNotFoundException;
import com.emc.d2fs.dctm.plugin.IPluginAction;
import com.emc.d2fs.dctm.web.services.D2fsContext;
import com.emc.d2fs.exceptions.D2fsException;
import com.emc.d2fs.models.attribute.Attribute;

/**
 * Abstract class that provides schema for communication with Open Ajax Hub (OAH) triggered by D2_ACTION_EXECUTE event
 * 
 * @author Krzysztof Jurkowski
 * 
 */
public abstract class AbstractCallbackAction implements IPluginAction {

	// name of the OAH message attribute storing exception message
	private static String OAH_SERVICE_EXCEPTION = "service_exception";

	// name of the OAH message attribute storing dfc exception message
	private static String OAH_SERVICE_EXCEPTION_ID = "service_dfexception_id";

	/**
	 * Returns the logger for the extending class
	 * 
	 * @return
	 */
	protected abstract Logger getLogger();

	/**
	 * Sets up standard attributes for OAH response
	 * 
	 * @param context {@link com.emc.d2fs.models.context.Context} object containing informations about client/session
	 * @return Message passed back to OHA
	 * @throws D2fsException
	 * @throws DfException
	 * @throws ArgumentNotFoundException
	 */
	protected List<Attribute> onServiceEntry(D2fsContext context) throws DfException, D2fsException, ArgumentNotFoundException {
		List<Attribute> result = new ArrayList<Attribute>();
		
		// store hashcode to identify the AsynchCallback object to which the response message will be passed
		Attribute callbackAttr = new Attribute();
		callbackAttr.setName("callback_code");
		callbackAttr.setValue(context.getParameterParser().getStringParameter("callback_code", null));
		result.add(callbackAttr);
		
		// store the called service (for informational purposes)
		Attribute serviceAttr = new Attribute();
		serviceAttr.setName("eService");
		serviceAttr.setValue(context.getParameterParser().getStringParameter("eService", null));
		result.add(serviceAttr);
		
		// store the called method of the service (for informational purposes)
		Attribute methodAttr = new Attribute();
		methodAttr.setName("eMethod");
		methodAttr.setValue(context.getParameterParser().getStringParameter("eMethod", null));
		result.add(methodAttr);

		// store the widget id that called the service
		String sender = context.getParameterParser().getStringParameter("oam_sender", null);
		if (sender != null) {
			Attribute targetIdAttr = new Attribute();
			// results of the service are passed back to the widget
			targetIdAttr.setName("oam_target_id");
			targetIdAttr.setValue(sender);

			getLogger().debug("oam_sender: " + sender);
			result.add(targetIdAttr);
		}

		return result;

	}

	/**
	 * Handle for service exception, passes the exception message back to OHA
	 * 
	 * @param result
	 *            Message passed back to OHA
	 * @param e
	 *            Exception caught in service
	 */
	protected void onServiceException(List<Attribute> result, Throwable e) {
		getLogger().error(e.getMessage(), e);

		// pass the exception message back to OHA
		Attribute exceptionAttr = new Attribute();
		exceptionAttr.setName(OAH_SERVICE_EXCEPTION);
		exceptionAttr.setValue(e.getMessage());
		result.add(exceptionAttr);

		if (e != null && DfException.class.isInstance(e)) {
			// pass the exception message back to OHA
			Attribute dfExceptionAttr = new Attribute();
			dfExceptionAttr.setName(OAH_SERVICE_EXCEPTION_ID);
			dfExceptionAttr.setValue(((DfException) e).getMessageId());
			result.add(dfExceptionAttr);
		}

	}
}

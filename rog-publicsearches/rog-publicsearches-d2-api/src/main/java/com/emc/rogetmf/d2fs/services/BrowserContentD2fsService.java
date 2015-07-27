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
package com.emc.rogetmf.d2fs.services;

import org.apache.commons.lang.StringUtils;

import com.emc.d2fs.interfaces.IBrowserService;
import com.emc.d2fs.models.context.Context;
import com.emc.d2fs.models.node.Node;
import com.emc.d2fs.schemas.models.ModelPort;
import com.emc.d2fs.services.browser_service.GetBrowserContentRequest;
import com.emc.d2fs.services.browser_service.GetBrowserContentResponse;
import com.emc.d2fs.services.browser_service.GetBrowserFacetContentRequest;
import com.emc.d2fs.services.browser_service.GetBrowserFacetContentResponse;

/**
 * Delegate of the {@link com.emc.d2fs.interfaces.IBrowserService} - content
 * services for Tree/Browser
 * 
 * @author Krzysztof Jurkowski
 *
 */
public class BrowserContentD2fsService extends AbstractD2fsService implements
		IBrowserService {
	public BrowserContentD2fsService(Context context) {
		super(context);
	}

	/**
	 * Check if service is remote
	 * 
	 * @return <code>true</code> if remote
	 */
	public boolean isRemote() {
		return false;
	}

	/**
	 * Set service as remote
	 * 
	 * @param boolean value of remote state
	 */
	public void setRemote(boolean paramBoolean) {

	}

	/**
	 * Get document children as tree content
	 * 
	 * @param id
	 *            String value of root document id
	 * @param contentTypeName
	 *            String value of root content type name (repository, folder,
	 *            virtual document, ...)
	 * @return Node object as tree content
	 * @throws Exception
	 */
	public Node getBrowserContent(String id, String contentTypeName)
			throws Exception {
		return getBrowserContent(getContext(), id, contentTypeName, null);
	}

	/**
	 * Get document children as tree content
	 * 
	 * @param id
	 *            id String value of root document id
	 * @param contentTypeName
	 *            String value of root content type name (repository, folder,
	 *            virtual document, ...)
	 * @param checkChildren
	 * @return Node object as tree content
	 * @throws Exception
	 */
	public Node getBrowserContent(String id, String contentTypeName,
			String checkChildren) throws Exception {
		return getBrowserContent(getContext(), id, contentTypeName,
				checkChildren);
	}

	/**
	 * Get document children as tree content
	 * 
	 * @param context
	 *            {@link com.emc.d2fs.models.context.Context} object containing
	 *            informations about client/session
	 * @param id
	 *            id String value of root document id
	 * @param contentTypeName
	 *            String value of root content type name (repository, folder,
	 *            virtual document, ...)
	 * @param checkChildren
	 *            String value of targeted children logic control (folder,
	 *            folderOrVd, folderOrDocument or null)
	 * @return Node object as tree content
	 * @throws Exception
	 */
	public Node getBrowserContent(Context context, String id,
			String contentTypeName, String checkChildren) throws Exception {
		GetBrowserContentRequest getBrowserContentRequest = new GetBrowserContentRequest();
		getBrowserContentRequest.setContext(context);

		getBrowserContentRequest.setContentTypeName(contentTypeName);

		if (StringUtils.isNotBlank(id))
			getBrowserContentRequest.setId(id);

		if (StringUtils.isNotBlank(checkChildren))
			getBrowserContentRequest.setCheckChildren(checkChildren);

		ModelPort port = getModelPort(context);
		GetBrowserContentResponse response = port
				.getBrowserContent(getBrowserContentRequest);

		return response.getNode();
	}

	/**
	 * Get facet items as tree content
	 */
	public Node getBrowserFacetContent(Context context, String id,
			String contentTypeName, String facetName, String facetValue,
			String resetFacetName, String checkChildren) throws Exception {
		GetBrowserFacetContentRequest getBrowserFacetContentRequest = new GetBrowserFacetContentRequest();
		getBrowserFacetContentRequest.setContext(context);

		getBrowserFacetContentRequest.setId(id);
		getBrowserFacetContentRequest.setContentTypeName(contentTypeName);
		getBrowserFacetContentRequest.setFacetName(facetName);
		getBrowserFacetContentRequest.setFacetValue(facetValue);
		getBrowserFacetContentRequest.setResetFacetName(resetFacetName);
		getBrowserFacetContentRequest.setCheckChildren(checkChildren);

		ModelPort port = getModelPort(context);
		GetBrowserFacetContentResponse response = port
				.getBrowserFacetContent(getBrowserFacetContentRequest);

		return response.getNode();

	}

	@Override
	public Node getFilteredBrowserContent(Context arg0, String arg1,
			String arg2, String arg3, String arg4) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}

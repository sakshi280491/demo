package com.emc.rogetmf.webfs.actions;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.emc.d2fs.dctm.web.services.D2fsContext;
import com.emc.d2fs.models.attribute.Attribute;
import com.emc.d2fs.models.node.Node;
import com.emc.rogetmf.d2fs.services.BrowserContentD2fsService;
import com.emc.rogetmf.webfs.action.AbstractCallbackAction;
import com.google.gson.Gson;

/**
 * Returns contents of a given tree node
 * 
 * @author Krzysztof Jurkowski
 *
 */
public class BrowserContentService extends AbstractCallbackAction {

	private Logger LOGGER = LoggerFactory.getLogger(getClass());

	private static final String NODE_TYPE_PARAM = "node_type";

	private static final String PARENT_ID_PARAM = "parent_id";

	@Override
	protected Logger getLogger() {
		return LOGGER;
	}

	/**
	 * Returns list of child nodes of a given parent object. Result is stored as a JSON array of {@link com.emc.d2fs.models.node.Node}
	 * objects.
	 * 
	 * @param context
	 *            Should contain two parameters: <code>node_type</code>, which normally is the <code>r_object_type</code> of the parent node
	 *            and <code>parent_id</code>, which is the <code>r_object_id</code> of the parent node
	 * @return
	 * @throws Exception
	 */
	public List<Attribute> getBrowserContent(D2fsContext context) throws Exception {
		List<Attribute> result = super.onServiceEntry(context);

		String nodeType = context.getParameterParser().getStringParameter(NODE_TYPE_PARAM, null);
		String parentId = context.getParameterParser().getStringParameter(PARENT_ID_PARAM, null);

		if (StringUtils.isBlank(nodeType))
			throw new NullPointerException(NODE_TYPE_PARAM + " is parameter empty");

		Attribute oamValue = new Attribute();
		oamValue.setName("oam_value");
		List<Node> nodeChildren = new ArrayList<Node>();
		try {
			BrowserContentD2fsService browserService = new BrowserContentD2fsService(context);
			Node serviceResult = browserService.getBrowserContent(parentId, nodeType);
			if (serviceResult != null)
				nodeChildren.addAll(serviceResult.getNodes());
		} finally {
			oamValue.setValue(new Gson().toJson(nodeChildren));
		}

		result.add(oamValue);

		return result;
	}
}

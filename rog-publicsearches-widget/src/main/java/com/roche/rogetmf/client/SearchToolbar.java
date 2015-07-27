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

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.KeyboardEvents;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.util.Params;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ToggleButton;
import com.extjs.gxt.ui.client.widget.form.TriggerField;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.roche.rogetmf.client.service.ISearchService;
import com.roche.rogetmf.shared.oah.OpenAjaxHub;
import com.roche.rogetmf.shared.oah.OpenAjaxMessage;
import com.roche.rogetmf.shared.oah.OpenAjaxMessageFactory;

public class SearchToolbar extends ToolBar {
	private final Logger logger = Logger.getLogger(getClass().getName());
	private final Messages messages = GWT.create(Messages.class);

	private final OpenAjaxHub openAjaxHub;

	private final ISearchService searchService;

	private TriggerField<String> quickSearchField;
	private ToggleButton highlightTermsButton;
	private Button advanceSearchButton;

	private Button debugButton;

	public SearchToolbar(OpenAjaxHub openAjaxHub, ISearchService searchService) {
		super();

		logger.log(Level.FINE, "Creating SearchToolbar");

		this.openAjaxHub = openAjaxHub;
		this.searchService = searchService;

		addStyleName("x3-widget-toolbar");
		getLayout().setSpacing(5);
		setEnableOverflow(false);

		add(getQuickSearchField());

		// add(getDebugButton());
		add(getAdvancedSearchButton());
		add(getHighlightTermsButton());

		subscribeSearchTermHighlighting();
	}

	private TriggerField<String> getQuickSearchField() {
		if (quickSearchField == null) {
			quickSearchField = new TriggerField<String>();
			quickSearchField.setHideLabel(true);

			quickSearchField.setEmptyText(messages.quickSearchPlaceholder());
			quickSearchField.setWidth(240);
			quickSearchField.addStyleName("x3-searchterms");
			quickSearchField.setTriggerStyle("x3-search-trigger");

			quickSearchField.addListener(Events.SpecialKey, new Listener<FieldEvent>() {
				public void handleEvent(FieldEvent event) {
					if (KeyboardEvents.Enter.getEventCode() == event.getKeyCode()) {
						onQuickSearch();
					}
				}
			});

			quickSearchField.addListener(Events.TriggerClick, new Listener<FieldEvent>() {
				public void handleEvent(FieldEvent event) {
					onQuickSearch();
				}
			});
		}

		return quickSearchField;
	}

	private Button getAdvancedSearchButton() {
		if (advanceSearchButton == null) {
			advanceSearchButton = new Button();
			advanceSearchButton.setIcon(IconHelper.createPath("resources/themes/slate/images/slate/special/adv_search_tool.png"));
			advanceSearchButton.setToolTip("Advanced search dialog");
			advanceSearchButton.addListener(Events.Select, new Listener<ButtonEvent>() {
				public void handleEvent(ButtonEvent be) {
					OpenAjaxMessage message = OpenAjaxMessageFactory.create();
					message.setParameter("DIALOG_NAME", "AdvancedSearchDialog");
					message.setParameter("MANAGER", "AdvancedSearchManager");

					openAjaxHub.send("D2_ACTION_EXECUTE_MANAGER", message);
				}
			});
		}

		return advanceSearchButton;
	}

	private ToggleButton getHighlightTermsButton() {
		if (highlightTermsButton == null) {
			highlightTermsButton = new ToggleButton();
			highlightTermsButton.setIcon(IconHelper.createPath("resources/themes/slate/images/slate/special/d2_hilight_tool.png"));
			highlightTermsButton.addListener(Events.Select, new Listener<ButtonEvent>() {
				public void handleEvent(ButtonEvent be) {
					OpenAjaxMessage message = OpenAjaxMessageFactory.create();
					if (highlightTermsButton.isPressed())
						message.setValue("1");
					else
						message.setValue("0");

					openAjaxHub.send("D2_ACTION_SEARCH_HIGHLIGHT_TERMS", message);
				}
			});

			highlightTermsButton.toggle();
		}

		return highlightTermsButton;
	}

	/**
	 * @return the debugButton
	 */
	@SuppressWarnings("unused")
	private Button getDebugButton() {
		if (debugButton == null) {
			debugButton = new Button();

			debugButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
				public void componentSelected(ButtonEvent ce) {
					final MessageBox box = MessageBox.prompt("ID", "Please enter d2c_query id:");
					box.addCallback(new Listener<MessageBoxEvent>() {
						public void handleEvent(MessageBoxEvent be) {
							Info.display("MessageBox", "You entered '{0}'", new Params(be.getValue()));
							searchService.runSearch(be.getValue(), "d2c_query_dql");
						}
					});
				}
			});

			debugButton.setIcon(IconHelper.createPath("resources/themes/slate/images/slate/special/node_last_search_16.gif"));
			debugButton.setToolTip("Advanced search dialog");
		}
		return debugButton;
	}

	public String getQuickSearchTerm() {
		return getQuickSearchField().getValue();
	}

	private void subscribeSearchTermHighlighting() {
		logger.log(Level.FINE, "Subscribing to D2_ACTION_SEARCH_HIGHLIGHT_TERMS");

		openAjaxHub.subscribe("D2_ACTION_SEARCH_HIGHLIGHT_TERMS", new AsyncCallback<OpenAjaxMessage>() {
			public void onSuccess(OpenAjaxMessage result) {
				boolean highlighTerms = "1".equals(result.getValue());

				logger.log(Level.FINER, "D2_ACTION_SEARCH_HIGHLIGHT_TERMS result: " + highlighTerms + " (" + result.getValue() + ")");
				getHighlightTermsButton().toggle(highlighTerms);
			}

			public void onFailure(Throwable caught) {
				logger.log(Level.WARNING, "D2_ACTION_SEARCH_HIGHLIGHT_TERMS::onFailure: " + caught.getMessage());
			}
		});
	}

	private void onQuickSearch() {
		String quickSearchTerm = getQuickSearchTerm();

		if (quickSearchTerm.trim().length() > 0)
			searchService.runCustomQuickSearch(quickSearchTerm);
		else
			logger.log(Level.FINE, "Quick search term empty");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.extjs.gxt.ui.client.widget.BoxComponent#onResize(int, int)
	 */
	@Override
	protected void onResize(int width, int height) {
		// TODO Auto-generated method stub
		super.onResize(width, height);
		int buttonWidth = highlightTermsButton.getWidth() == 0 ? 22 : highlightTermsButton.getWidth();
		int calcWidth = width - (buttonWidth + 5) * 2 - 15;
		quickSearchField.setWidth(calcWidth);
	}
}

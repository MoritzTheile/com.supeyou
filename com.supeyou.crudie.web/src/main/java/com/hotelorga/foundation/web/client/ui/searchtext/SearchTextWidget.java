package com.hotelorga.foundation.web.client.ui.searchtext;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;

public class SearchTextWidget extends WidgetView {

	public SearchTextWidget(String searchString, Listener listener) {
		this(listener);
		textBox.setValue(searchString);
	}

	public SearchTextWidget(final Listener listener) {

		textBox.addBlurHandler(new BlurHandler() {

			@Override
			public void onBlur(BlurEvent event) {
				listener.search(textBox.getValue());

			}
		});

		textBox.addDomHandler(new KeyUpHandler() {

			@Override
			public void onKeyUp(KeyUpEvent event) {

				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {

					textBox.setFocus(false);
				}

			}
		}, KeyUpEvent.getType());

		textBox.addFocusHandler(new FocusHandler() {

			@Override
			public void onFocus(FocusEvent event) {

				textBox.selectAll();

			}
		});

	}

	public interface Listener {
		void search(String searchString);
	}
}

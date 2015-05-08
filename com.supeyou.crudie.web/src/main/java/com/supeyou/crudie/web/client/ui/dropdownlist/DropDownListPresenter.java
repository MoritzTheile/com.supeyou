package com.supeyou.crudie.web.client.ui.dropdownlist;

import java.util.Map;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.user.client.ui.HTML;

public class DropDownListPresenter extends WidgetView {

	private static final String HIDDEN_STYLE = "hiddenStyle";
	private static final String NON_HIDDEN_STYLE = "nonHiddenStyle";
	private static final String SELECTED_STYLE = "selectedStyle";

	private enum MODE {
		hidden, shown
	}

	private MODE mode = MODE.hidden;

	final Map<String, Object> name2object;

	private String selectedName;

	private DropDownListListener listener;

	public DropDownListPresenter(String selectedName, Map<String, Object> name2object, DropDownListListener listener) {

		this.name2object = name2object;

		this.listener = listener;

		this.selectedName = selectedName;

		label.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				toggle();

			}

		}, ClickEvent.getType());
		icon.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				toggle();

			}

		}, ClickEvent.getType());

		ul.addDomHandler(new MouseOutHandler() {

			@Override
			public void onMouseOut(MouseOutEvent event) {

				mode = MODE.hidden;
				render();

			}
		}, MouseOutEvent.getType());

		render();
	}

	private void toggle() {
		// toggle mode
		if (MODE.hidden.equals(mode)) {
			mode = MODE.shown;
		} else {
			mode = MODE.hidden;
		}

		render();
	}

	private void render() {

		if (MODE.hidden.equals(mode)) {
			this.addStyleName(HIDDEN_STYLE);
			this.removeStyleName(NON_HIDDEN_STYLE);
		} else {
			this.removeStyleName(HIDDEN_STYLE);
			this.addStyleName(NON_HIDDEN_STYLE);
		}

		label.setText(selectedName);

		ul.clear();

		for (final String name : name2object.keySet()) {

			if (name.equals(selectedName)) {
				continue;
			}

			final Object object = name2object.get(name);

			HTML liElement = new HTML("<li/>");
			liElement.setText(name);
			if (name.equals(selectedName)) {
				liElement.addStyleName(SELECTED_STYLE);

			}

			liElement.addDomHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					event.stopPropagation();
					selectedName = name;
					mode = MODE.hidden;
					listener.clicked(object);
					render();

				}

			}, ClickEvent.getType());

			ul.add(liElement);

		}

	}

	public interface DropDownListListener {
		void clicked(Object object);
	}
}

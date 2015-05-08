package com.hotelorga.foundation.web.client.uiorga.popup;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class PopupWidget extends WidgetView {

	private boolean glassPaneClickCloses = false;

	public PopupWidget(Widget content, int top, int left, boolean glassPaneClickCloses) {

		this.glassPaneClickCloses = glassPaneClickCloses;

		setPosition(top, left);

		init(content);

	}

	public void setPosition(int top, int left) {

		contentSlot.getElement().getStyle().setTop(top, Unit.PX);
		contentSlot.getElement().getStyle().setLeft(left, Unit.PX);

	}

	public PopupWidget(Widget content, boolean glassPaneClickCloses) {

		this.glassPaneClickCloses = glassPaneClickCloses;

		init(content);

	}

	public PopupWidget(boolean glassPaneClickCloses) {

		this.glassPaneClickCloses = glassPaneClickCloses;

	}

	public void init(Widget content) {

		contentSlot.add(content);

		RootPanel.get().add(this);

		if (glassPaneClickCloses) {
			glass.addDomHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {

					closePopup();

				}

			}, ClickEvent.getType());

		} else {

			glass.addDomHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {

					contentSlot.removeStyleName("shake");

					// giving a break before adding again, so browser will detect the change and restart animation
					new Timer() {

						@Override
						public void run() {

							contentSlot.addStyleName("shake");

						}

					}.schedule(100);

				}

			}, ClickEvent.getType());

		}

		contentSlot.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				event.stopPropagation();

			}

		}, ClickEvent.getType());

		History.newItem("POPUP");

		History.addValueChangeHandler(new ValueChangeHandler<String>() {

			@Override
			public void onValueChange(ValueChangeEvent<String> event) {

				closePopup();

			}

		});

	}

	public void closePopup() {

		removeFromParent();

	}

}

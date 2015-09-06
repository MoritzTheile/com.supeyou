package com.supeyou.core.web.client.view.heropage.invite.hintmanual;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.TouchMoveEvent;
import com.google.gwt.event.dom.client.TouchMoveHandler;

public class HintManualWidget extends WidgetView {

	public HintManualWidget() {

		this.addDomHandler(new TouchMoveHandler() {

			@Override
			public void onTouchMove(TouchMoveEvent event) {

				event.stopPropagation();

			}

		}, TouchMoveEvent.getType());

		this.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				event.stopPropagation();

				removePostItFromParent();

			}

		}, ClickEvent.getType());

	}

	private void removePostItFromParent() {

		removeFromParent();

	}
}

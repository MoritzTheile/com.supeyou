package com.supeyou.core.web.client.view.heropage.invite.recommendation;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.TouchMoveEvent;
import com.google.gwt.event.dom.client.TouchMoveHandler;

public class RecommendationWidget extends WidgetView {

	public RecommendationWidget() {

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

	public void removePostItFromParent() {

		removeFromParent();

	}
}

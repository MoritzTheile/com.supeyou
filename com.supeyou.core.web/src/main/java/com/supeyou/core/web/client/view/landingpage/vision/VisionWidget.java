package com.supeyou.core.web.client.view.landingpage.vision;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.supeyou.core.web.client.view.heropage.video.wistia.WistiaWidget;

public class VisionWidget extends WidgetView {

	public VisionWidget() {

		videoHTML.setHTML(WistiaWidget.htmlTemplate.replaceAll(WistiaWidget.urlToken, "//fast.wistia.net/embed/iframe/6juhe730yt?&videoFoam=true&autoPlay=false"));

		videoHTML.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Window.alert("asdfllasdfl");

			}
		}, ClickEvent.getType());

	}

}

package com.supeyou.core.web.client.view.heropage.video.youtube;

import com.google.gwt.user.client.ui.HTML;

public class YoutubeWidget extends WidgetView {

	public YoutubeWidget(String youtubeURL_TOKEN) {
		String html = htmlTemplate;
		html = html.replaceAll("youtubeURL_TOKEN", youtubeURL_TOKEN);
		root.add(new HTML(html));
	}

	private final String htmlTemplate = ""
			+ ""

			+ "<iframe width=\"100%\"  src=\"youtubeURL_TOKEN\" frameborder=\"0\" allowfullscreen></iframe>"

			+ "";

}

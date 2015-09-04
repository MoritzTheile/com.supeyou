package com.supeyou.core.web.client.view.heropage.video.wistia;

import com.google.gwt.user.client.ui.HTML;

public class WistiaWidget extends WidgetView {

	public WistiaWidget(String youtubeURL_TOKEN) {
		String html = htmlTemplate;
		html = html.replaceAll("url_TOKEN", youtubeURL_TOKEN);
		root.add(new HTML(html));
	}

	private final String htmlTemplate = ""
			+ ""
			+ "<iframe src=\"url_TOKEN\" "
			+ "allowtransparency=\"true\" "
			+ "frameborder=\"0\" "
			+ "scrolling=\"no\" "
			+ "class=\"wistia_embed\" "
			+ "name=\"wistia_embed\" "
			+ "allowfullscreen mozallowfullscreen webkitallowfullscreen oallowfullscreen msallowfullscreen "
			+ "width=\"100%\" height=\"360px\" >"
			+ "</iframe>"
			+ "<script src=\"//fast.wistia.net/assets/external/E-v1.js\" async></script>"
			+ "";

}

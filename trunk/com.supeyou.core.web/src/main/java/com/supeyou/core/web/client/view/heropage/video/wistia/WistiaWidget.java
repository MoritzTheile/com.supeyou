package com.supeyou.core.web.client.view.heropage.video.wistia;

import com.google.gwt.user.client.ui.HTML;

public class WistiaWidget extends WidgetView {

	public static final String urlToken = "url_TOKEN";

	public WistiaWidget(String youtubeURL_TOKEN) {
		String html = htmlTemplate;
		html = html.replaceAll(urlToken, youtubeURL_TOKEN);
		root.add(new HTML(html));
	}

	public static final String htmlTemplate = "" +

			"<div class=\"wistia_responsive_padding\" style=\"padding:56.25% 0 0 0;position:relative;\"><div class=\"wistia_responsive_wrapper\" style=\"height:100%;left:0;position:absolute;top:0;width:100%;\"><iframe src=\"url_TOKEN\" allowtransparency=\"true\" frameborder=\"0\" scrolling=\"no\" class=\"wistia_embed\" name=\"wistia_embed\" allowfullscreen mozallowfullscreen webkitallowfullscreen oallowfullscreen msallowfullscreen width=\"100%\" height=\"100%\"></iframe></div></div>" +
			"<script src=\"//fast.wistia.net/assets/external/E-v1.js\" async></script>" +
			"";

	public static final String htmlTemplateWithoutFoam = ""
			+ ""
			+ "<iframe src=\"url_TOKEN\" "
			+ "allowtransparency=\"true\" "
			+ "frameborder=\"0\" "
			+ "scrolling=\"no\" "
			+ "class=\"wistia_embed\" "
			+ "name=\"wistia_embed\" "
			+ "allowfullscreen mozallowfullscreen webkitallowfullscreen oallowfullscreen msallowfullscreen >"
			+ "</iframe>"
			+ "<script src=\"//fast.wistia.net/assets/external/E-v1.js\" async></script>"
			+ "";

}

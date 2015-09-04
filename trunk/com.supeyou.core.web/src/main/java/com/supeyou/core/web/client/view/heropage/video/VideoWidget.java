package com.supeyou.core.web.client.view.heropage.video;

import com.supeyou.core.web.client.view.heropage.video.wistia.WistiaWidget;
import com.supeyou.core.web.client.view.heropage.video.youtube.YoutubeWidget;

public class VideoWidget extends WidgetView {

	public VideoWidget(final String videoUrl) {

		if (videoUrl != null) {
			if (videoUrl.toLowerCase().contains("paypal")) {
				youtubeSlot.add(new YoutubeWidget(videoUrl));
			} else if (videoUrl.toLowerCase().contains("wistia")) {
				youtubeSlot.add(new WistiaWidget(videoUrl));
			}
		}
	}

}

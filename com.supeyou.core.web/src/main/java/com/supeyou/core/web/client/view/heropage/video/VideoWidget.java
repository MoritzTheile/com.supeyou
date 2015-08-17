package com.supeyou.core.web.client.view.heropage.video;

import com.supeyou.core.web.client.view.heropage.video.youtube.YoutubeWidget;

public class VideoWidget extends WidgetView {

	public VideoWidget(final String videoUrl) {

		youtubeSlot.add(new YoutubeWidget(videoUrl));
	}

}

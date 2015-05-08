package com.supeyou.crudie.web.client.ui.fileviewer.imageviewer;

public class ImageViewerWidget extends View {

	public ImageViewerWidget(String contentURL) {

		String html = "<image src=\"" + contentURL + "\" style=\"width:100%;\"></image>";

		this.html.setHTML(html);

	}

}

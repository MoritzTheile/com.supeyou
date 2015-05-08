package com.hotelorga.foundation.web.client.ui.fileviewer.textviewer;

public class TextViewerWidget extends View {

	public TextViewerWidget(String contentURL, String mimeType) {

		this.html.setHTML("<embed src=\"" + contentURL + "\" type=\"" + mimeType + "\"  ></embed>");

	}

}

package com.supeyou.crudie.web.client.ui.fileviewer.pdfviewer;

public class PDFViewerWidget extends View {

	public PDFViewerWidget(String contentURL, String mimeType) {

		this.html.setHTML("<object data=\"" + contentURL + "\" type=\"" + mimeType + "\" ></object>");

	}

}

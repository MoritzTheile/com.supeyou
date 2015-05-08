package com.hotelorga.foundation.web.client.ui.fileviewer;

import com.google.gwt.core.client.GWT;
import com.hotelorga.foundation.iface.dto.FileDTO;
import com.hotelorga.foundation.web.client.resources.GWTSTATICS;
import com.hotelorga.foundation.web.client.ui.fileviewer.defaultviewer.DefaultViewerWidget;
import com.hotelorga.foundation.web.client.ui.fileviewer.imageviewer.ImageViewerWidget;
import com.hotelorga.foundation.web.client.ui.fileviewer.pdfviewer.PDFViewerWidget;
import com.hotelorga.foundation.web.client.ui.fileviewer.textviewer.TextViewerWidget;

public class FileViewerWidget extends View {

	public FileViewerWidget(FileDTO file) {

		String contentURL = GWT.getHostPageBaseURL() + "FileDownloadServlet?" + GWTSTATICS.FILE_ID_TOKEN + "=" + file.getId();

		String mimeTypeString = "";

		if (file.getMimetype() != null) {

			mimeTypeString = file.getMimetype().getMimeTypeString();

		}

		if (mimeTypeString.toLowerCase().contains("pdf")) {

			fileViewerRoot.add(new PDFViewerWidget(contentURL, mimeTypeString));

		} else if (mimeTypeString.toLowerCase().contains("image")) {

			fileViewerRoot.add(new ImageViewerWidget(contentURL));

		} else if (mimeTypeString.toLowerCase().contains("text/plain")) {

			fileViewerRoot.add(new TextViewerWidget(contentURL, mimeTypeString));

		} else {

			fileViewerRoot.add(new DefaultViewerWidget(contentURL));

		}

	}

}

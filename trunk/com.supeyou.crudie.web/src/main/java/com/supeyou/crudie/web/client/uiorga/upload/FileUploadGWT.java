package com.supeyou.crudie.web.client.uiorga.upload;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormHandler;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormSubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormSubmitEvent;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.supeyou.crudie.iface.datatype.types.FileIDType;
import com.supeyou.crudie.web.client.resources.GWTSTATICS;

@SuppressWarnings("deprecation")
public class FileUploadGWT extends SimplePanel {

	public FileUploadGWT(FileIDType fileId, final FileUploadListener listener) {

		// Create a FormPanel and point it at a service.
		final FormPanel form = new FormPanel();

		form.setAction("./FileUploadServlet");

		// Because we're going to add a FileUpload widget, we'll need to set the
		// form to use the POST method, and multipart MIME encoding.
		form.setEncoding(FormPanel.ENCODING_MULTIPART);

		form.setMethod(FormPanel.METHOD_POST);

		// Create a panel to hold all of the form widgets.
		VerticalPanel panel = new VerticalPanel();

		form.setWidget(panel);

		Hidden hidden = new Hidden();
		hidden.setName(GWTSTATICS.FILE_ID_TOKEN);
		hidden.setValue(fileId.value() + "");
		panel.add(hidden);

		// Create a TextBox, giving it a name so that it will be submitted.
		// final TextBox tb = new TextBox();
		//
		// tb.setName("textBoxFormElement");
		//
		// panel.add(tb);

		// Create a FileUpload widget.
		FileUpload upload = new FileUpload();
		upload.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent arg0) {

				form.submit();

				listener.fileUploadStarted();

			}
		});

		upload.setName("uploadFormElement");

		panel.add(upload);

		// panel.add(new Label(" "));
		// panel.add(new Label(" "));
		// panel.add(new Label(" "));
		// panel.add(new Label(" "));
		//
		// // Add a 'submit' button.
		// panel.add(new Button("importieren", new ClickListener() {
		// public void onClick(Widget sender) {
		// form.submit();
		// }
		// }));

		// Add an event handler to the form.
		form.addFormHandler(new FormHandler() {
			public void onSubmit(FormSubmitEvent event) {
				// This event is fired just before the form is submitted. We can take
				// this opportunity to perform validation.
				// if (tb.getText().length() == 0) {
				// Window.alert("The text box must not be empty");
				// event.setCancelled(true);
				// }
			}

			public void onSubmitComplete(FormSubmitCompleteEvent event) {
				// When the form submission is successfully completed, this event is
				// fired. Assuming the service returned a response of type text/html,
				// we can get the result text here (see the FormPanel documentation for
				// further explanation).
				// Window.alert(event.getResults());

				listener.fileUploadCompleted();

			}
		});

		// form.addSubmitCompleteHandler(new SubmitCompleteHandler() {
		//
		// @Override
		// public void onSubmitComplete(SubmitCompleteEvent arg0) {
		//
		// Window.alert("Hochladen erfolgreich");
		//
		// }
		//
		// });

		this.add(form);
	}

	public interface FileUploadListener {

		public void fileUploadStarted();

		public void fileUploadCompleted();

		public void fileUploadCanceled();

	}

}

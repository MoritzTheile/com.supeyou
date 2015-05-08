package com.hotelorga.foundation.web.client.rpc.file.chooserlarge.item;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Label;
import com.hotelorga.foundation.iface.dto.FileDTO;
import com.hotelorga.foundation.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;
import com.hotelorga.foundation.web.client.rpc.file.RPCCRUDProxy;

public class ItemWidget extends WidgetView {

	private final ItemWidget thisWidget;

	private FileDTO thisDTO;

	public ItemWidget(FileDTO dto) {

		this.thisDTO = dto;

		RPCCRUDProxy.i().addListenerForAllDTOs(listener);

		thisWidget = this;

		render();

	}

	private void render() {

		columnOne.clear();
		columnOne.add(new Label(renderInfoString()));
		columnTwo.clear();
		if (thisDTO.getMimetype() != null) {
			columnTwo.add(new Label(thisDTO.getMimetype().getMimeTypeString()));
		}
		columnThree.clear();
		columnThree.add(new Label(thisDTO.getSize() + ""));

	}

	private String renderInfoString() {
		String infos = "";
		if (thisDTO.getName() != null) {
			infos += thisDTO.getName().value();
		}

		return infos;
	}

	private CRUDProxyListener<FileDTO> listener = new CRUDProxyListener<FileDTO>() {

		@Override
		public void wasUpdated(FileDTO dto) {

			final String updatedStyle = "updated";

			thisWidget.removeStyleName(updatedStyle);

			if (thisDTO.equals(dto)) {

				thisDTO = dto;

				// without the delay the change in style doesn't get picked up
				new Timer() {

					@Override
					public void run() {

						thisWidget.addStyleName(updatedStyle);

					}
				}.schedule(100);

				render();
			}
		}

		@Override
		public void wasDeleted(FileDTO dto) {

			removeFromParent();

		}

		@Override
		public void wasCreated(FileDTO dto) {

			// not possible

		}

	};

	protected void onDetach() {

		RPCCRUDProxy.i().removeListener(listener);

		super.onDetach();

	};

}

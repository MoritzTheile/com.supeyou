package com.supeyou.actor.web.client.rpc.event.choosersmall.item;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Label;
import com.supeyou.actor.iface.dto.EventDTO;
import com.supeyou.actor.web.client.rpc.event.RPCCRUDProxy;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;

public class ItemWidget extends WidgetView {

	private final ItemWidget thisWidget;

	private EventDTO thisDTO;

	public ItemWidget(EventDTO dto) {

		this.thisDTO = dto;

		RPCCRUDProxy.i().addListenerForAllDTOs(listener);

		thisWidget = this;

		render();

	}

	private void render() {

		columnOne.clear();
		columnOne.add(new Label(thisDTO.getId() + ""));
		columnTwo.clear();
		columnTwo.add(new Label(thisDTO.getUserLoginId() + ""));
		columnThree.clear();
		columnThree.add(new Label(thisDTO.getSessionId() + ""));
		columnFour.clear();
		columnFour.add(new Label(thisDTO.getFormattedTimestamp() + ""));
		columnFive.clear();
		columnFive.add(new Label(thisDTO.getPageAgeSeconds() + ""));
		columnSix.clear();
		columnSix.add(new Label(thisDTO.getCategory() + ""));
		columnSeven.clear();
		columnSeven.add(new Label(thisDTO.getAction() + ""));
		columnEight.clear();
		columnEight.add(new Label(thisDTO.getValue() + ""));
		columnNine.clear();
		columnNine.add(new Label(thisDTO.getUserName() + ""));
		columnTen.clear();
		columnTen.add(new Label(thisDTO.getUserId() + ""));

	}

	private CRUDProxyListener<EventDTO> listener = new CRUDProxyListener<EventDTO>() {

		@Override
		public void wasUpdated(EventDTO dto) {

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
		public void wasDeleted(EventDTO dto) {

			removeFromParent();

		}

		@Override
		public void wasCreated(EventDTO dto) {

			// not possible

		}

	};

	protected void onDetach() {

		RPCCRUDProxy.i().removeListener(listener);

		super.onDetach();

	};

}

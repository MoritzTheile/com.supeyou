package com.supeyou.core.web.client.rpc.supporter2donation.chooserlarge.item;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Label;
import com.supeyou.core.iface.dto.Supporter2DonationDTO;
import com.supeyou.core.web.client.rpc.supporter2donation.RPCCRUDProxy;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;

public class ItemWidget extends WidgetView {

	private final ItemWidget thisWidget;

	private Supporter2DonationDTO thisDTO;

	public ItemWidget(Supporter2DonationDTO dto) {

		this.thisDTO = dto;

		RPCCRUDProxy.i().addListenerForAllDTOs(listener);

		thisWidget = this;

		render();

	}

	private void render() {

		columnOne.clear();
		columnOne.add(new Label(thisDTO.getDtoA().getUserDTO().getLoginId() + " <-> " + thisDTO.getDtoB().getTxnId()));

	}

	private CRUDProxyListener<Supporter2DonationDTO> listener = new CRUDProxyListener<Supporter2DonationDTO>() {

		@Override
		public void wasUpdated(Supporter2DonationDTO dto) {

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
		public void wasDeleted(Supporter2DonationDTO dto) {

			removeFromParent();

		}

		@Override
		public void wasCreated(Supporter2DonationDTO dto) {

			// not possible

		}

	};

	protected void onDetach() {

		RPCCRUDProxy.i().removeListener(listener);

		super.onDetach();

	};

}

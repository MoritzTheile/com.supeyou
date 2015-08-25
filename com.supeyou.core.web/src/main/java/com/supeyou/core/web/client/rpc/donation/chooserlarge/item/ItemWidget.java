package com.supeyou.core.web.client.rpc.donation.chooserlarge.item;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.supeyou.core.iface.dto.DonationDTO;
import com.supeyou.core.web.client.rpc.donation.RPCCRUDProxy;
import com.supeyou.crudie.iface.datatype.types.AbstrType;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;

public class ItemWidget extends WidgetView {

	private final ItemWidget thisWidget;

	private DonationDTO thisDTO;

	public ItemWidget(DonationDTO dto) {

		this.thisDTO = dto;

		RPCCRUDProxy.i().addListenerForAllDTOs(listener);

		thisWidget = this;

	}

	private void render() {

		fillSlot(txnIdSlot, thisDTO.getTxnId());
		fillSlot(itemNameSlot, thisDTO.getItemName());
		fillSlot(itemNumberSlot, thisDTO.getItemNumber());
		fillSlot(paymentStatusSlot, thisDTO.getPaymentStatus());
		fillSlot(paymentAmountSlot, thisDTO.getPaymentAmount());
		fillSlot(paymentCurrencySlot, thisDTO.getPaymentCurrency());
		fillSlot(receiverEmailSlot, thisDTO.getReceiverEmail());
		fillSlot(payerEmailSlot, thisDTO.getPayerEmail());
		fillSlot(responseSlot, thisDTO.getResponse());
		fillSlot(requstParamySlot, thisDTO.getRequestParams());

	}

	private void fillSlot(FlowPanel slot, AbstrType<?> data) {
		slot.clear();
		if (data != null) {
			slot.add(new HTML(data.toString()));
		}
	}

	private CRUDProxyListener<DonationDTO> listener = new CRUDProxyListener<DonationDTO>() {

		@Override
		public void wasUpdated(DonationDTO dto) {

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
		public void wasDeleted(DonationDTO dto) {

			removeFromParent();

		}

		@Override
		public void wasCreated(DonationDTO dto) {

			// not possible

		}

	};

	protected void onDetach() {

		RPCCRUDProxy.i().removeListener(listener);

		super.onDetach();

	};

	@Override
	protected void onLoad() {
		super.onLoad();
		render();
	}

}

package com.supeyou.core.web.client.rpc.hero.chooserlarge.item;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Label;
import com.supeyou.core.iface.dto.HeroDTO;
import com.supeyou.core.web.client.rpc.hero.RPCCRUDProxy;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;

public class ItemWidget extends WidgetView {

	private final ItemWidget thisWidget;

	private HeroDTO thisDTO;

	public ItemWidget(HeroDTO dto) {

		this.thisDTO = dto;

		RPCCRUDProxy.i().addListenerForAllDTOs(listener);

		thisWidget = this;

	}

	private void render() {

		columnOne.clear();
		columnOne.add(new Label(renderInfoString()));
		columnTwo.clear();
		if (thisDTO.getComment() != null) {
			columnTwo.add(new Label(thisDTO.getComment().value()));
		}
	}

	private String renderInfoString() {
		String infos = "";
		if (thisDTO.getName() != null) {
			infos += thisDTO.getName().value();
		}

		return infos;
	}

	private CRUDProxyListener<HeroDTO> listener = new CRUDProxyListener<HeroDTO>() {

		@Override
		public void wasUpdated(HeroDTO dto) {

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
		public void wasDeleted(HeroDTO dto) {

			removeFromParent();

		}

		@Override
		public void wasCreated(HeroDTO dto) {

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

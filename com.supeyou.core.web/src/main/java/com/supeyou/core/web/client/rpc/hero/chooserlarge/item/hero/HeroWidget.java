package com.supeyou.core.web.client.rpc.hero.chooserlarge.item.hero;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.supeyou.core.iface.dto.HeroDTO;
import com.supeyou.core.web.client.rpc.hero.RPCCRUDProxy;
import com.supeyou.crudie.web.client.rpc.abstr.crud.RPCAbstractCRUDProxy.CRUDProxyListener;

public class HeroWidget extends WidgetView {

	private final HeroWidget thisWidget;

	private HeroDTO thisDTO;

	public HeroWidget(HeroDTO dto) {

		this.thisDTO = dto;

		RPCCRUDProxy.i().addListenerForAllDTOs(listener);

		thisWidget = this;

		websiteSlot.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				event.stopPropagation();

			}
		}, ClickEvent.getType());

	}

	private void render() {

		imageSlot.clear();
		if (thisDTO.getImageURL() != null) {
			imageSlot.add(new Image(thisDTO.getImageURL().value()));
		}
		websiteSlot.clear();
		if (thisDTO.getWebsiteURL() != null) {
			websiteSlot.add(new HTML("<a target=\"_blank\" href=\"" + thisDTO.getWebsiteURL().value() + "\">" + thisDTO.getWebsiteURL().value() + "</a>"));
		}
		commentSlot.clear();
		if (thisDTO.getComment() != null) {
			commentSlot.add(new HTML(thisDTO.getComment().value()));
		}

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

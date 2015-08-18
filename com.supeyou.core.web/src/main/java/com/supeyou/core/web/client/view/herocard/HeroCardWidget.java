package com.supeyou.core.web.client.view.herocard;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.supeyou.core.iface.dto.HeroDTO;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.web.client.rpc.supporter.RPCCRUDServiceAsync;
import com.supeyou.core.web.client.view.herocard.hero.HeroWidget;
import com.supeyou.core.web.client.view.herocard.supporterinfo.SupporterWidget;

public class HeroCardWidget extends WidgetView {

	public enum VIEW {
		ROOTVIEW, NODEVIEW
	}

	public HeroCardWidget(SupporterDTO supporterDTO, VIEW view) {

		render(supporterDTO, view);

	}

	public HeroCardWidget(HeroDTO data) {

		RPCCRUDServiceAsync.i.getOrCreateRootSupporter(data, new AsyncCallback<SupporterDTO>() {

			@Override
			public void onFailure(Throwable caught) {
				caught.printStackTrace();

			}

			@Override
			public void onSuccess(SupporterDTO result) {

				render(result, VIEW.ROOTVIEW);

			}

		});

	}

	private void render(SupporterDTO supporterDTO, VIEW view) {
		heroSlot.add(new HeroWidget(supporterDTO.getHeroDTO(), view));
		supporterSlot.add(new SupporterWidget(supporterDTO));

	}

}

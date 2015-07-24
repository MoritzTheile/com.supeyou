package com.supeyou.core.web.client.rpc.hero.chooserlarge.item;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.supeyou.core.iface.dto.HeroDTO;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.web.client.rpc.hero.chooserlarge.item.hero.HeroWidget;
import com.supeyou.core.web.client.rpc.hero.chooserlarge.item.rootinfo.RootInfoWidget;
import com.supeyou.core.web.client.rpc.supporter.RPCCRUDServiceAsync;

public class ItemWidget extends WidgetView {

	public ItemWidget(HeroDTO heroDTO) {

		heroSlot.add(new HeroWidget(heroDTO));

		RPCCRUDServiceAsync.i.getOrCreateRootSupporter(heroDTO, new AsyncCallback<SupporterDTO>() {

			@Override
			public void onSuccess(SupporterDTO rootSupporterDTO) {

				supporterSlot.add(new RootInfoWidget(rootSupporterDTO));

			}

			@Override
			public void onFailure(Throwable caught) {

				caught.printStackTrace();

			}
		});

	}

}

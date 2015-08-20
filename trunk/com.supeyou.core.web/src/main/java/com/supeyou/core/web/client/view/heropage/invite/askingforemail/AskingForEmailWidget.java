package com.supeyou.core.web.client.view.heropage.invite.askingforemail;

import com.supeyou.core.iface.dto.SupporterDTO;

public abstract class AskingForEmailWidget extends WidgetView {

	public AskingForEmailWidget(final SupporterDTO supporterDTO) {
	}

	protected abstract void onDismiss();

}

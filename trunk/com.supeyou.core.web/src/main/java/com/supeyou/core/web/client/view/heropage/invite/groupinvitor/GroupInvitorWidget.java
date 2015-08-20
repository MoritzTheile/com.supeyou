package com.supeyou.core.web.client.view.heropage.invite.groupinvitor;

import com.supeyou.core.iface.dto.SupporterDTO;

public abstract class GroupInvitorWidget extends WidgetView {

	public GroupInvitorWidget(final SupporterDTO supporterDTO) {
	}

	protected abstract void onDismiss();

}

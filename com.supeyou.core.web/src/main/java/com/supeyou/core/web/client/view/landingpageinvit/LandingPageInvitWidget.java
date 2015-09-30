package com.supeyou.core.web.client.view.landingpageinvit;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.supeyou.actor.web.client.login.ActorStatics;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.web.client.HistoryController.ANCHOR;
import com.supeyou.core.web.client.resources.i18n.Text;
import com.supeyou.core.web.client.view.heropage.video.wistia.WistiaWidget;
import com.supeyou.crudie.web.client.uiorga.flatbutton.FlatButtonWidget;

public class LandingPageInvitWidget extends WidgetView {

	public LandingPageInvitWidget(final SupporterDTO supporterDTO) {

		html.setHTML(Text.i.LANDINGPAGEINVIT_Text_HTML().replaceAll("HeroName_TOKEN", supporterDTO.getHeroDTO().getName() + ""));

		FlatButtonWidget flatButtonWidget = new FlatButtonWidget();
		flatButtonWidget.setText(Text.i.HEROSPAGE_FollowInvitation());
		flatButtonWidget.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				ActorStatics.fireActorEvent("click", "landingPageNext");

				History.newItem(ANCHOR.HERO.name() + "_" + supporterDTO.getHeroDTO().getId().value(), false);
				History.fireCurrentHistoryState();

			}
		});

		chooseHeroButtonSlot.add(flatButtonWidget);

		videoHTML.setHTML(WistiaWidget.htmlTemplate.replaceAll(WistiaWidget.urlToken, "//fast.wistia.net/embed/iframe/6juhe730yt?&videoFoam=true&autoPlay=false"));

	}
}

package com.supeyou.core.web.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.supeyou.actor.web.client.login.ActorStatics;
import com.supeyou.core.iface.dto.HeroDTO;
import com.supeyou.core.iface.dto.HeroIDType;
import com.supeyou.core.iface.dto.InvitationDTO;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.web.client.mainmenu.MainMenuWidget;
import com.supeyou.core.web.client.rpc.hero.RPCCRUDServiceAsync;
import com.supeyou.core.web.client.view.heropage.HeroPageWidget;
import com.supeyou.core.web.client.view.landingpage.LandingPageWidget;
import com.supeyou.crudie.web.client.model.LoginStateModel;

public class HistoryController {

	public enum ANCHOR {
		LP, HEROES, HERO
	}

	private HistoryController() {

		History.addValueChangeHandler(new ValueChangeHandler<String>() {

			@Override
			public void onValueChange(ValueChangeEvent<String> event) {

				String anchor = event.getValue();

				show(anchor);

				ActorStatics.fireActorEvent("history", "changed", anchor);

			}

		});

	}

	public void show(String anchor) {
		// there can be ANCHORS with args seperated by underscore
		String[] anchorParts = anchor.split("_");

		if (ANCHOR.LP.name().equals(anchorParts[0])) {
			showLandingPage();
		} else if (ANCHOR.HERO.name().equals(anchorParts[0])) {
			showHeroPage(anchorParts);
		} else if (ANCHOR.HEROES.name().equals(anchorParts[0])) {
			showHerosPage();
		} else {
			showLandingPage();
		}
	}

	private void showHerosPage() {

		RootPanel.get("main").clear();
		RootPanel.get("main").add(new MainMenuWidget());

	}

	private static HistoryController instance;

	public static HistoryController i() {
		if (instance == null) {
			instance = new HistoryController();
		}
		return instance;
	}

	private void showLandingPage() {
		RootPanel.get("main").clear();
		RootPanel.get("main").add(new LandingPageWidget());

	}

	private void showHeroPage(String[] anchor) {

		try {

			RPCCRUDServiceAsync.i.get(new HeroIDType(anchor[1]), new AsyncCallback<HeroDTO>() {

				@Override
				public void onFailure(Throwable caught) {

					Window.alert("codemarker=adogall\n\n" + caught.getMessage());
					caught.printStackTrace();

				}

				@Override
				public void onSuccess(HeroDTO result) {

					showHeroPage(result);

				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void showHeroPage(final HeroDTO heroDTO) {

		com.supeyou.core.web.client.rpc.supporter.RPCCRUDServiceAsync.i.get(LoginStateModel.i().getLoggedInUser(), heroDTO, new AsyncCallback<SupporterDTO>() {

			@Override
			public void onFailure(Throwable caught) {

				caught.printStackTrace();

			}

			@Override
			public void onSuccess(SupporterDTO result) {

				if (result != null) {
					showHeroPage(result);
				} else {
					createRootInvitationAndFollow(heroDTO);
				}

			}

		});

	}

	private void createRootInvitationAndFollow(HeroDTO heroDTO) {

		com.supeyou.core.web.client.rpc.supporter.RPCCRUDServiceAsync.i.getOrCreateRootSupporter(heroDTO, new AsyncCallback<SupporterDTO>() {

			@Override
			public void onFailure(Throwable caught) {

				caught.printStackTrace();

			}

			@Override
			public void onSuccess(SupporterDTO result) {

				com.supeyou.core.web.client.rpc.invitation.RPCCRUDServiceAsync.i.create(result, new AsyncCallback<InvitationDTO>() {

					@Override
					public void onFailure(Throwable caught) {

						caught.printStackTrace();

					}

					@Override
					public void onSuccess(InvitationDTO result) {

						com.supeyou.core.web.client.rpc.invitation.RPCCRUDServiceAsync.i.followInvitation(LoginStateModel.i().getLoggedInUser(), result.getToken(), new AsyncCallback<SupporterDTO>() {

							@Override
							public void onFailure(Throwable caught) {

								caught.printStackTrace();

							}

							@Override
							public void onSuccess(SupporterDTO result) {

								showHeroPage(result);

							}

						});

					}
				});

			}
		});

	}

	private void showHeroPage(SupporterDTO supporterDTO) {
		RootPanel.get("main").clear();
		RootPanel.get("main").add(new HeroPageWidget(supporterDTO));
		Window.scrollTo(0, 0);

		String historyToken = ANCHOR.HERO.name() + "_" + supporterDTO.getHeroDTO().getId().value();

		if (!History.getToken().equals(historyToken)) {
			History.newItem(historyToken, false);
		}
	}

}

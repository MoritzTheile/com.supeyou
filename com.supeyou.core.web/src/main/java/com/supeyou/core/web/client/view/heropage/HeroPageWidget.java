package com.supeyou.core.web.client.view.heropage;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.supeyou.actor.web.client.login.ActorStatics;
import com.supeyou.core.iface.dto.HeroDTO;
import com.supeyou.core.iface.dto.Invitation2SupporterDTO;
import com.supeyou.core.iface.dto.Invitation2SupporterFetchQuery;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.web.client.resources.i18n.Text;
import com.supeyou.core.web.client.rpc.supporter.RPCCRUDServiceAsync;
import com.supeyou.core.web.client.view.herocard.HeroCardWidget;
import com.supeyou.core.web.client.view.herocard.HeroCardWidget.VIEW;
import com.supeyou.core.web.client.view.heropage.donate.DonateWidget;
import com.supeyou.core.web.client.view.heropage.howitworks.HowItWorksWidget;
import com.supeyou.core.web.client.view.heropage.invite.askforemail.AskForEmailWidget;
import com.supeyou.core.web.client.view.heropage.invite.howtoinvite.HowToInviteWidget;
import com.supeyou.core.web.client.view.heropage.supportertree.SupporterTreeWidget;
import com.supeyou.crudie.iface.common.HELPER;
import com.supeyou.crudie.iface.datatype.Page;
import com.supeyou.crudie.iface.dto.DTOFetchList;
import com.supeyou.crudie.iface.dto.UserDTO;
import com.supeyou.crudie.web.client.model.LoginStateModel;
import com.supeyou.crudie.web.client.uiorga.flatbutton.FlatButtonWidget;
import com.supeyou.crudie.web.client.uiorga.popup.PopupWidget;
import com.supeyou.crudie.web.client.uiorga.popup.contentwrapper.ContentWrapperWidget;

public class HeroPageWidget extends WidgetView {

	private SupporterDTO loggedInSupporterDTO;

	private ROOT_OF_TREE rootOfTree = ROOT_OF_TREE.HERO;

	private enum ROOT_OF_TREE {
		HERO,
		LOGGED_IN_USER
	}

	public HeroPageWidget(SupporterDTO supporterDTO) {

		this.loggedInSupporterDTO = supporterDTO;

		render();

	}

	private void render() {

		supporterCardSlot.add(new HeroCardWidget(loggedInSupporterDTO, VIEW.NODEVIEW));

		{
			FlatButtonWidget flatButtonWidget = new FlatButtonWidget();
			flatButtonWidget.setText(Text.i.BUTTON_Donate());
			flatButtonWidget.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {

					ActorStatics.fireActorEvent("click", "donateButton");

					new PopupWidget(new ContentWrapperWidget(Text.i.DONATE_HeaderLabel(), new DonateWidget(loggedInSupporterDTO)), true);

				}
			});

			donationButtonSlot.add(flatButtonWidget);
		}
		{
			FlatButtonWidget flatButtonWidget = new FlatButtonWidget();
			flatButtonWidget.setText(Text.i.BUTTON_Invite());
			flatButtonWidget.addClickHandler(inviteClickHandler);

			invitationButtonSlot.add(flatButtonWidget);
		}
		renderSupporterTree();

	}

	private void renderSupporterTree() {

		supporterTreeSlot.clear();

		Label viewToggleLabel = new Label();

		supporterTreeSlot.add(viewToggleLabel);

		viewToggleLabel.addStyleName("tree-view-toggle-label");

		// not showing for now
		viewToggleLabel.setStyleName("hide");

		if (rootOfTree.equals(ROOT_OF_TREE.LOGGED_IN_USER)) {

			viewToggleLabel.setText("[Show hero on top]");
			viewToggleLabel.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					rootOfTree = ROOT_OF_TREE.HERO;
					renderSupporterTree();

				}
			});

			renderSupporterTree2(loggedInSupporterDTO);

		} else { // retrieving the hero self supporter. First the User, than the Supporter.

			viewToggleLabel.setText("[Show me on top]");
			viewToggleLabel.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					rootOfTree = ROOT_OF_TREE.LOGGED_IN_USER;
					renderSupporterTree();

				}
			});

			final HeroDTO heroDTO = loggedInSupporterDTO.getHeroDTO();

			com.supeyou.core.web.client.rpc.hero.RPCCRUDServiceAsync.i.getUser(heroDTO, new AsyncCallback<UserDTO>() {

				@Override
				public void onFailure(Throwable caught) {

					caught.printStackTrace();

				}

				@Override
				public void onSuccess(UserDTO result) {

					com.supeyou.core.web.client.rpc.supporter.RPCCRUDServiceAsync.i.get(result, heroDTO, new AsyncCallback<SupporterDTO>() {

						@Override
						public void onFailure(Throwable caught) {

							caught.printStackTrace();

						}

						@Override
						public void onSuccess(SupporterDTO result) {

							renderSupporterTree2(result);

						}

					});

				}
			});

		}

	}

	private void renderSupporterTree2(final SupporterDTO rootSupporterDTO) {
		Invitation2SupporterFetchQuery supporterFetchQuery = new Invitation2SupporterFetchQuery();

		supporterFetchQuery.setInvitor(rootSupporterDTO);

		com.supeyou.core.web.client.rpc.invitation2supporter.RPCCRUDServiceAsync.i.fetchList(new Page(), supporterFetchQuery, new AsyncCallback<DTOFetchList<Invitation2SupporterDTO>>() {

			@Override
			public void onFailure(Throwable caught) {

				caught.printStackTrace();

			}

			@Override
			public void onSuccess(DTOFetchList<Invitation2SupporterDTO> childrenDTO) {

				if (childrenDTO.size() > 0) {

					RPCCRUDServiceAsync.i.getSupporterInPathToRoot(loggedInSupporterDTO, new AsyncCallback<List<SupporterDTO>>() {

						@Override
						public void onSuccess(List<SupporterDTO> result) {

							supporterTreeSlot.add(new SupporterTreeWidget(loggedInSupporterDTO, rootSupporterDTO, result));

						}

						@Override
						public void onFailure(Throwable caught) {

							caught.printStackTrace();

						}
					});

				} else {

					HowItWorksWidget howItWorksWidget = new HowItWorksWidget();
					howItWorksWidget.addDomHandler(inviteClickHandler, ClickEvent.getType());
					supporterTreeSlot.add(howItWorksWidget);

				}

			}

		});
	}

	private ClickHandler inviteClickHandler = new ClickHandler() {

		@Override
		public void onClick(ClickEvent event) {

			ActorStatics.fireActorEvent("click", "inviteButton");

			final PopupWidget popupWidget = new PopupWidget() {

				public void onClose() {
					askForEmail();
				};

			};

			HowToInviteWidget content = new HowToInviteWidget(loggedInSupporterDTO) {

				@Override
				protected void onDismiss() {

					popupWidget.closePopup();

				}

			};

			popupWidget.init(new ContentWrapperWidget(Text.i.INVITE_HeaderLabel(), content));

		}

	};

	private void askForEmail() {

		if (HELPER.isAnonymous(LoginStateModel.i().getLoggedInUser())) {

			final PopupWidget popupWidget = new PopupWidget() {
				@Override
				public void onClose() {
					// nothing
				}
			};

			ActorStatics.fireActorEvent("popup", "askingForEmail");

			AskForEmailWidget contentWidget = new AskForEmailWidget(loggedInSupporterDTO) {

				@Override
				protected void onDismiss() {

					ActorStatics.fireActorEvent("popup", "dismiss");

					popupWidget.closePopup();

				}

			};

			popupWidget.init(new ContentWrapperWidget(Text.i.ASK_FOR_EMAIL_Header(), contentWidget));

		}

	}
}

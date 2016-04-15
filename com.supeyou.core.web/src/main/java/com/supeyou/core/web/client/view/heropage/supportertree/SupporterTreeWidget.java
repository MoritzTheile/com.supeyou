package com.supeyou.core.web.client.view.heropage.supportertree;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Node;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.supeyou.core.iface.dto.Invitation2SupporterDTO;
import com.supeyou.core.iface.dto.Invitation2SupporterFetchQuery;
import com.supeyou.core.iface.dto.InvitationDTO;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.web.client.resources.i18n.Text;
import com.supeyou.core.web.client.view.heropage.supportertree.edges.Edge;
import com.supeyou.core.web.client.view.heropage.supportertree.edges.EdgesWidget;
import com.supeyou.crudie.iface.common.HELPER;
import com.supeyou.crudie.iface.datatype.Page;
import com.supeyou.crudie.iface.datatype.types.AmountType;
import com.supeyou.crudie.iface.dto.DTOFetchList;

public class SupporterTreeWidget extends WidgetView {

	private final SupporterDTO supporterDTO;
	private final SupporterDTO loggedInSupporterDTO;
	private final List<SupporterDTO> nodesToExpand;

	private List<Invitation2SupporterDTO> childrenSupporterDTO;

	private final SupporterTreeWidget thisWidget;
	private final SupporterTreeWidget parentWidget;

	private final boolean treeDestroying;

	private final Integer level;

	public enum COLLAPSE_MODE {
		COLLAPSED, EXPANDED
	};

	private COLLAPSE_MODE collapseMode = COLLAPSE_MODE.COLLAPSED;

	public SupporterTreeWidget(final SupporterDTO loggedInSupporterDTO, final SupporterDTO supporterDTO, List<SupporterDTO> nodesToExpand) {
		this(loggedInSupporterDTO, supporterDTO, nodesToExpand, false, null, 1);
	}

	private SupporterTreeWidget(final SupporterDTO loggedInSupporterDTO, final SupporterDTO supporterDTO, List<SupporterDTO> nodesToExpand_, boolean treeDestroying, final SupporterTreeWidget parentWidget, Integer level) {
		this.treeDestroying = treeDestroying;

		if (treeDestroying) {
			root.addStyleName("gray");
		}

		thisWidget = this;

		this.level = level;

		this.parentWidget = parentWidget;

		this.loggedInSupporterDTO = loggedInSupporterDTO;
		this.supporterDTO = supporterDTO;
		this.nodesToExpand = nodesToExpand_;

		// if (level < 2) {
		// collapseMode = COLLAPSE_MODE.EXPANDED;
		// }

		Invitation2SupporterFetchQuery supporterFetchQuery = new Invitation2SupporterFetchQuery();

		supporterFetchQuery.setInvitor(supporterDTO);

		com.supeyou.core.web.client.rpc.invitation2supporter.RPCCRUDServiceAsync.i.fetchList(new Page(), supporterFetchQuery, new AsyncCallback<DTOFetchList<Invitation2SupporterDTO>>() {

			@Override
			public void onFailure(Throwable caught) {

				caught.printStackTrace();

			}

			@Override
			public void onSuccess(DTOFetchList<Invitation2SupporterDTO> childrenDTO) {

				childrenSupporterDTO = new ArrayList<>();

				// to have the path to logged in user always on the left of tree:
				// first adding the node to expand/the root to logged in user
				for (Invitation2SupporterDTO invitation2SupporterDTO : childrenDTO) {

					if (nodesToExpand.contains(invitation2SupporterDTO.getDtoB())) {
						childrenSupporterDTO.add(invitation2SupporterDTO);
					}

				}

				// than adding rest
				for (Invitation2SupporterDTO invitation2SupporterDTO : childrenDTO) {

					if (!nodesToExpand.contains(invitation2SupporterDTO.getDtoB())) {
						childrenSupporterDTO.add(invitation2SupporterDTO);
					}

				}

				render();

			}

		});

	}

	private void render() {

		// if (level == 1) {
		// amountLabel.getElement().getStyle().setDisplay(Display.NONE);
		// imageSlot.getElement().getStyle().setDisplay(Display.NONE);
		// nameLabel.getElement().getStyle().setDisplay(Display.NONE);
		// }

		if (supporterIsYou(loggedInSupporterDTO, supporterDTO)) {
			root.addStyleName("you");
			nameLabel.setHTML(getHtml(supporterDTO) + "<br> (You)");
		} else if (supporterIsHero(loggedInSupporterDTO, supporterDTO)) {
			root.addStyleName("hero");
			nameLabel.setHTML(getHtml(supporterDTO) + "<br> (Hero)");
		} else {
			nameLabel.setHTML(getHtml(supporterDTO));

		}

		edgeSlot.clear();
		childrenSlot.clear();

		if (supporterIsHero(loggedInSupporterDTO, supporterDTO)) {
			amountLabel.setText(HELPER.cent2euro((getAmountValueNullsave(supporterDTO.getDecendantAmount()) + getAmountValueNullsave(supporterDTO.getOwnAmount()))) + " " + Text.i.EUROSYMBOL());
		} else {
			amountLabel.setText(HELPER.cent2euro((getAmountValueNullsave(supporterDTO.getOwnAmount())/* + getAmountValueNullsave(supporterDTO.getDecendantAmount()) */)) + " " + Text.i.EUROSYMBOL());
		}

		// not showing zero values
		if (0 < getAmountValueNullsave(supporterDTO.getOwnAmount()) || supporterIsHero(loggedInSupporterDTO, supporterDTO)) {
			amountLabel.removeStyleName("hide");
		} else {
			amountLabel.addStyleName("hide");
		}
		amountLabel.setTitle(" decendants=" + supporterDTO.getDecendentCount());

		int shownChildren = 0;
		if (COLLAPSE_MODE.EXPANDED.equals(collapseMode)) {

			for (Invitation2SupporterDTO childSupporterDTO : childrenSupporterDTO) {
				shownChildren++;
				childrenSlot.add(new SupporterTreeWidget(loggedInSupporterDTO, childSupporterDTO.getDtoB(), nodesToExpand, childSupporterDTO.getTreeDestroying(), thisWidget, new Integer(level + 1)));

			}

			expandButtonSlot.clear();
			// if (level > 1)
			{
				Label collapseButton = new Label("-");
				collapseButton.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {

						collapseMode = COLLAPSE_MODE.COLLAPSED;

						render();

					}
				});
				expandButtonSlot.add(collapseButton);
			}
		} else {

			if (!childrenSupporterDTO.isEmpty() && !treeDestroying) {

				Label expandButton = new Label("+");

				if (nodesToExpand.contains(supporterDTO)) {

					for (Invitation2SupporterDTO childSupporterDTO : childrenSupporterDTO) {

						if (!nodesToExpand.contains(childSupporterDTO.getDtoB()) || childSupporterDTO.getTreeDestroying()) {

							continue;

						}

						shownChildren++;
						childrenSlot.add(new SupporterTreeWidget(loggedInSupporterDTO, childSupporterDTO.getDtoB(), nodesToExpand, childSupporterDTO.getTreeDestroying(), thisWidget, new Integer(level + 1)));

					}

				}

				expandButton.setText("+ " + (childrenSupporterDTO.size() - shownChildren));

				expandButton.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {

						collapseMode = COLLAPSE_MODE.EXPANDED;

						render();

					}
				});
				expandButtonSlot.clear();
				if (childrenSupporterDTO.size() - shownChildren > 0) {
					expandButtonSlot.add(expandButton);
				}

			}

			if (parentWidget != null) {
				parentWidget.childFinishedLoading(supporterDTO);
			}

		}

	}

	// protected void onLoad() {
	//
	// if (parentWidget != null) {
	// parentWidget.childFinishedLoading(supporterDTO);
	// }
	//
	// }

	private boolean supporterIsHero(SupporterDTO loggedInSupporterDTO2, SupporterDTO supporterDTO2) {
		// we can assume its the hero:
		return !supporterIsYou(loggedInSupporterDTO2, supporterDTO2) && level == 1;

	}

	private boolean supporterIsYou(SupporterDTO loggedInSupporterDTO2, SupporterDTO supporterDTO2) {
		return loggedInSupporterDTO.getId().equals(supporterDTO.getId());
	}

	public static Integer getAmountValueNullsave(AmountType amountType) {
		if (amountType == null) {
			return 0;
		}
		return amountType.value();
	}

	private String getHtml(SupporterDTO supporterDTO2) {

		String html = "";

		if (treeDestroying) {

			html += Text.i.TREE_WasSupportingAlready() + "<br>";

		}

		if (supporterDTO2 != null && supporterDTO2.getUserDTO().getLoginId() != null) {

			if (supporterDTO2.getUserDTO().getName() != null) {

				html += supporterDTO2.getUserDTO().getName().value();

			} else {

				html += "";

			}

			if (level <= 2) {
				// not showing the name of the links yet
				// html += getClickedLinks(supporterDTO2);
			}

		}
		return html;
	}

	@SuppressWarnings("unused")
	private String getClickedLinks(SupporterDTO supporterDTO2) {

		if (supporterDTO2.getInvitationDTOs().size() == 0) {
			return "";
		}

		String clickedLinks = "";

		for (InvitationDTO invitationDTO : supporterDTO2.getInvitationDTOs()) {
			if (invitationDTO.getComment() != null) {
				clickedLinks += "<br>" + invitationDTO.getComment();
			}
		}

		if (clickedLinks.startsWith("<br>")) {
			clickedLinks = clickedLinks.substring(4, clickedLinks.length());
		}

		return "<br>" + clickedLinks;

	}

	protected void childFinishedLoading(SupporterDTO childSupporterDTO) {

		renderEdges();

		if (parentWidget != null) {
			parentWidget.childFinishedLoading(supporterDTO);
		}

	};

	/**
	 * before calling this, children have to be completely loaded and rendered
	 */
	private void renderEdges() {

		edgeSlot.clear();

		final EdgesWidget edgesWidget = new EdgesWidget(this.getOffsetWidth(), 30);

		edgesWidget.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				collapseMode = COLLAPSE_MODE.COLLAPSED;

				render();

			}

		}, ClickEvent.getType());

		int lowerEndpointFromLeft = 0;

		for (int i = 0; i < childrenSlot.getElement().getChildNodes().getLength(); i++) {

			Node node = childrenSlot.getElement().getChildNodes().getItem(i);

			if (node instanceof Element) {

				int width = ((Element) node).getClientWidth();

				lowerEndpointFromLeft += width;

				Edge edgeWidget = new Edge(lowerEndpointFromLeft - width / 2);

				edgesWidget.addNewEdge(edgeWidget);
			}

		}

		edgesWidget.render();

		edgeSlot.add(edgesWidget);

	}

}

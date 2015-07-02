package com.supeyou.core.web.client.view.invitationclicks;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Node;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.iface.dto.SupporterFetchQuery;
import com.supeyou.core.web.client.view.invitationclicks.edges.Edge;
import com.supeyou.core.web.client.view.invitationclicks.edges.EdgesWidget;
import com.supeyou.crudie.iface.datatype.Page;
import com.supeyou.crudie.iface.dto.DTOFetchList;

public class InvitationclicksWidget extends WidgetView {

	private final List<SupporterDTO> childrenStillLoading = new ArrayList<>();

	private final InvitationclicksWidget parentWidget;

	private final InvitationclicksWidget thisWidget;

	private final SupporterDTO supporterDTO;

	public InvitationclicksWidget(final InvitationclicksWidget parentWidget, final SupporterDTO supporterDTO) {

		thisWidget = this;
		nameLabel.setText(supporterDTO.getComment().value());

		this.supporterDTO = supporterDTO;

		this.parentWidget = parentWidget;

		SupporterFetchQuery supporterFetchQuery = new SupporterFetchQuery();

		supporterFetchQuery.setInvitor(supporterDTO);

		com.supeyou.core.web.client.rpc.supporter.RPCCRUDServiceAsync.i.fetchList(new Page(), supporterFetchQuery, new AsyncCallback<DTOFetchList<SupporterDTO>>() {

			@Override
			public void onFailure(Throwable caught) {

				caught.printStackTrace();

			}

			@Override
			public void onSuccess(DTOFetchList<SupporterDTO> childrenDTO) {

				if (childrenDTO.isEmpty()) {

					if (parentWidget != null) {
						parentWidget.loadingAChildFinished(supporterDTO);
					}

				} else {

					childrenStillLoading.addAll(childrenDTO);

					for (final SupporterDTO childSupporterDTO : childrenDTO) {

						childrenSlot.add(new InvitationclicksWidget(thisWidget, childSupporterDTO));

					}

				}

			}
		});

	}

	/**
	 * before calling this, the children have to be loaded
	 */
	protected void renderEdges() {

		EdgesWidget edgesWidget = new EdgesWidget(thisWidget.getOffsetWidth(), 30);

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

	protected void loadingAChildFinished(SupporterDTO childSupporterDTO) {

		childrenStillLoading.remove(childSupporterDTO);

		if (childrenStillLoading.isEmpty()) {

			renderEdges();

			if (parentWidget != null) {

				parentWidget.loadingAChildFinished(supporterDTO);

			}

		}

	};

}

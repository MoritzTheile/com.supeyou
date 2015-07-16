package com.supeyou.core.web.client.view.heropage.supportertree;

import java.util.List;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Node;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.supeyou.core.iface.dto.SupporterDTO;
import com.supeyou.core.iface.dto.SupporterFetchQuery;
import com.supeyou.core.web.client.view.heropage.supportertree.edges.Edge;
import com.supeyou.core.web.client.view.heropage.supportertree.edges.EdgesWidget;
import com.supeyou.crudie.iface.datatype.Page;
import com.supeyou.crudie.iface.dto.DTOFetchList;

public class SupporterTreeWidget extends WidgetView {

	private final SupporterDTO supporterDTO;

	private List<SupporterDTO> childrenSupporterDTO;

	private final SupporterTreeWidget thisWidget;
	private final SupporterTreeWidget parentWidget;

	private final Integer level;

	public enum COLLAPSE_MODE {
		COLLAPSED, EXPANDED
	};

	private COLLAPSE_MODE collapseMode = COLLAPSE_MODE.COLLAPSED;

	public SupporterTreeWidget(final SupporterDTO supporterDTO) {
		this(supporterDTO, null, 1);
	}

	private SupporterTreeWidget(final SupporterDTO supporterDTO, final SupporterTreeWidget parentWidget, Integer level) {

		thisWidget = this;

		this.level = level;

		this.parentWidget = parentWidget;

		this.supporterDTO = supporterDTO;

		if (level < 2) {
			collapseMode = COLLAPSE_MODE.EXPANDED;
		}

		SupporterFetchQuery supporterFetchQuery = new SupporterFetchQuery();

		supporterFetchQuery.setInvitor(supporterDTO);

		com.supeyou.core.web.client.rpc.supporter.RPCCRUDServiceAsync.i.fetchList(new Page(), supporterFetchQuery, new AsyncCallback<DTOFetchList<SupporterDTO>>() {

			@Override
			public void onFailure(Throwable caught) {

				caught.printStackTrace();

			}

			@Override
			public void onSuccess(DTOFetchList<SupporterDTO> childrenDTO) {

				childrenSupporterDTO = childrenDTO;

				render();

			}

		});

	}

	private void render() {

		edgeSlot.clear();
		childrenSlot.clear();

		if (supporterDTO != null && supporterDTO.getUserDTO().getLoginId() != null) {
			nameLabel.setText(supporterDTO.getUserDTO().getLoginId().value());
		} else {
			nameLabel.setText("null");
		}

		if (COLLAPSE_MODE.EXPANDED.equals(collapseMode)) {

			for (SupporterDTO childSupporterDTO : childrenSupporterDTO) {

				childrenSlot.add(new SupporterTreeWidget(childSupporterDTO, thisWidget, new Integer(level + 1)));

			}

		} else {

			if (!childrenSupporterDTO.isEmpty()) {

				Label expandButton = new Label("+ " + childrenSupporterDTO.size());

				expandButton.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {

						collapseMode = COLLAPSE_MODE.EXPANDED;

						render();

					}
				});

				childrenSlot.add(expandButton);

			}

			if (parentWidget != null) {
				parentWidget.childFinishedLoading(supporterDTO);
			}

		}

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
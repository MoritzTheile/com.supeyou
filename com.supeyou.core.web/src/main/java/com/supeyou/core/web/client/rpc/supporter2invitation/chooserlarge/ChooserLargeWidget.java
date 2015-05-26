package com.supeyou.core.web.client.rpc.supporter2invitation.chooserlarge;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Widget;
import com.supeyou.core.iface.dto.Supporter2InvitationDTO;
import com.supeyou.core.iface.dto.Supporter2InvitationFetchQuery;
import com.supeyou.core.web.client.rpc.supporter2invitation.ListDataProvider;
import com.supeyou.core.web.client.rpc.supporter2invitation.chooserlarge.item.ItemWidget;
import com.supeyou.core.web.client.rpc.supporter2invitation.form.FormWidget;
import com.supeyou.core.web.client.rpc.supporter2invitation.imexport.ExportLink;
import com.supeyou.core.web.client.rpc.supporter2invitation.imexport.ImportLink;
import com.supeyou.core.web.client.rpc.supporter2invitation.query.QueryWidget;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListDataProvider;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListWidgetList;
import com.supeyou.crudie.web.client.rpc.abstr.list.ListSelectionModel.SelectionListener;
import com.supeyou.crudie.web.client.rpc.abstr.list.listpager.ListPagerWidget;
import com.supeyou.crudie.web.client.uiorga.popup.PopupWidget;

public class ChooserLargeWidget extends WidgetView {

	private final AbstrListDataProvider<Supporter2InvitationDTO, Supporter2InvitationFetchQuery> dataProvider;

	public ChooserLargeWidget() {

		dataProvider = new ListDataProvider(new Supporter2InvitationFetchQuery());
		dataProvider.setPageSize(5);

		final AbstrListWidgetList<Supporter2InvitationDTO, Supporter2InvitationFetchQuery> widgetList = new AbstrListWidgetList<Supporter2InvitationDTO, Supporter2InvitationFetchQuery>(dataProvider) {

			@Override
			public Widget getWidget(final Supporter2InvitationDTO data) {

				return new ItemWidget(data);

			}

		};

		widgetList.getSelectionModel().addListener(new SelectionListener<Supporter2InvitationDTO>() {

			@Override
			public void onHasChanged(List<Supporter2InvitationDTO> selection) {

				if (selection.size() == 1) {

					final PopupWidget formPopup = new PopupWidget(false);
					formPopup.setPosition(100, 700);
					formPopup.init(
							new FormWidget(dataProvider, selection.iterator().next(), true, true) {
								@Override
								protected void close() {
									widgetList.getSelectionModel().clearSelection();
									formPopup.closePopup();
								}
							}
							);

				} else {
					// nothing yet
				}

			}

		});

		itemsSlot.add(widgetList);

		querySlot.add(new QueryWidget(dataProvider));

		pagerSlot.add(new ListPagerWidget(dataProvider));

		createLink.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				event.stopPropagation();

				event.stopPropagation();

				final PopupWidget formPopup = new PopupWidget(false);
				formPopup.setPosition(100, 700);
				formPopup.init(
						new FormWidget(dataProvider, new Supporter2InvitationDTO(), true, true) {
							@Override
							protected void close() {
								formPopup.closePopup();
							}
						}
						);

			}

		}, ClickEvent.getType());

		dataProvider.fetchData();

		footer.add(new ExportLink(dataProvider.getFetchQuery()));

		footer.add(new ImportLink() {
			@Override
			public void importFinished() {
				dataProvider.fetchData();
			}
		});

	}

}

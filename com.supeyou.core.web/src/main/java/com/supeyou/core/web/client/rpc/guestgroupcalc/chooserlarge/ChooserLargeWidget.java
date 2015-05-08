package com.supeyou.core.web.client.rpc.guestgroupcalc.chooserlarge;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Widget;
import com.supeyou.core.iface.datatype.enums.MONTH;
import com.supeyou.core.iface.dto.GuestGroupDTO;
import com.supeyou.core.iface.dto.GuestGroupFetchQuery;
import com.supeyou.core.web.client.fields.types.FieldForMonth;
import com.supeyou.core.web.client.rpc.guestgroup.ListDataProvider;
import com.supeyou.core.web.client.rpc.guestgroup.form.FormWidget;
import com.supeyou.core.web.client.rpc.guestgroup.imexport.ExportLink;
import com.supeyou.core.web.client.rpc.guestgroup.imexport.ImportLink;
import com.supeyou.core.web.client.rpc.guestgroup.query.QueryWidget;
import com.supeyou.core.web.client.rpc.guestgroupcalc.chooserlarge.item.ItemWidget;
import com.supeyou.crudie.iface.datatype.FetchQuery.SORTDIRECTION;
import com.supeyou.crudie.iface.datatype.types.DateType;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListDataProvider;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListWidgetList;
import com.supeyou.crudie.web.client.rpc.abstr.list.ListSelectionModel.SelectionListener;
import com.supeyou.crudie.web.client.rpc.abstr.list.listpager.ListPagerWidget;
import com.supeyou.crudie.web.client.uiorga.linkbutton.LinkButtonWidget;
import com.supeyou.crudie.web.client.uiorga.popup.PopupWidget;

public class ChooserLargeWidget extends WidgetView {
	private final AbstrListDataProvider<GuestGroupDTO, GuestGroupFetchQuery> dataProvider;
	private DateType from;
	private DateType to;

	public ChooserLargeWidget() {

		{
			MONTH defaultMonth = MONTH.APR2015;

			this.from = defaultMonth.from;
			this.to = defaultMonth.to;

			monthSelectorSlot.add(new FieldForMonth(defaultMonth) {
				@Override
				public void onHasChanged(MONTH value) {

					from = value.from;
					to = value.to;

					initWidgetList();
					dataProvider.fetchData();
				}
			});
		}
		{

			settings.add(new GenerateExcelOverviewLink() {

				@Override
				public DateType getFrom() {
					return from;
				}

				@Override
				public DateType getTo() {
					return to;
				}
			});
		}
		dataProvider = new ListDataProvider(new GuestGroupFetchQuery());
		dataProvider.getFetchQuery().setOrderByColumn("name");
		dataProvider.getFetchQuery().setSortDirection(SORTDIRECTION.ASC);
		dataProvider.setPageSize(5);

		initWidgetList();

		querySlot.add(new QueryWidget(dataProvider));

		pagerSlot.add(new ListPagerWidget(dataProvider));

		LinkButtonWidget refreshButton = new LinkButtonWidget("refresh");
		refreshButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				dataProvider.fetchData();
			}
		});
		pagerSlot.add(refreshButton);

		createLink.setTitle("Rechnungsgruppe erstellen");
		createLink.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				event.stopPropagation();

				final PopupWidget formPopup = new PopupWidget(false);
				formPopup.setPosition(100, 700);
				formPopup.init(
						new FormWidget(dataProvider, new GuestGroupDTO()) {
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

	private void initWidgetList() {

		final AbstrListWidgetList<GuestGroupDTO, GuestGroupFetchQuery> widgetList = new AbstrListWidgetList<GuestGroupDTO, GuestGroupFetchQuery>(dataProvider) {

			@Override
			public Widget getWidget(final GuestGroupDTO data) {

				return new ItemWidget(data, from, to);

			}

		};

		widgetList.getSelectionModel().addListener(new SelectionListener<GuestGroupDTO>() {

			@Override
			public void onHasChanged(List<GuestGroupDTO> selection) {

				if (selection.size() == 1) {

					final PopupWidget formPopup = new PopupWidget(false);
					formPopup.setPosition(100, 700);
					formPopup.init(
							new FormWidget(dataProvider, selection.iterator().next()) {
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

		itemsSlot.clear();
		itemsSlot.add(widgetList);
	}
}

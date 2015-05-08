package com.supeyou.core.web.client.rpc.acceptance.choosersmall;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.supeyou.core.iface.dto.AcceptanceDTO;
import com.supeyou.core.iface.dto.AcceptanceFetchQuery;
import com.supeyou.core.web.client.rpc.acceptance.ListDataProvider;
import com.supeyou.core.web.client.rpc.acceptance.choosersmall.item.ItemWidget;
import com.supeyou.core.web.client.rpc.acceptance.form.FormForFieldsWidget;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListDataProvider;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListWidgetList;
import com.supeyou.crudie.web.client.rpc.abstr.list.ListSelectionModel.SelectionListener;
import com.supeyou.crudie.web.client.rpc.abstr.list.listpager.ListPagerWidget;
import com.supeyou.crudie.web.client.ui.searchtext.SearchTextWidget;
import com.supeyou.crudie.web.client.ui.searchtext.SearchTextWidget.Listener;
import com.supeyou.crudie.web.client.uiorga.popup.PopupWidget;

public class ChooserSmallWidget extends WidgetView {

	private final AbstrListDataProvider<AcceptanceDTO, AcceptanceFetchQuery> dataProvider;
	private final SelectionListener<AcceptanceDTO> selectionListener;

	public ChooserSmallWidget(SelectionListener<AcceptanceDTO> selectionListener, boolean showAddLink) {

		this.selectionListener = selectionListener;

		dataProvider = new ListDataProvider(new AcceptanceFetchQuery());
		dataProvider.setPageSize(5);

		searchSlot.add(new SearchTextWidget(dataProvider.getFetchQuery().getSearchString(), new Listener() {

			@Override
			public void search(String searchString) {
				dataProvider.getFetchQuery().setSearchString(searchString);
				dataProvider.fetchData();
			}
		}));

		AbstrListWidgetList<AcceptanceDTO, AcceptanceFetchQuery> widgetList = new AbstrListWidgetList<AcceptanceDTO, AcceptanceFetchQuery>(dataProvider) {

			@Override
			public Widget getWidget(final AcceptanceDTO data) {

				return new ItemWidget(data);

			}

		};

		widgetList.getSelectionModel().addListener(selectionListener);

		itemsSlot.add(widgetList);

		pagerSlot.add(new ListPagerWidget(dataProvider));

		if (showAddLink) {

			createLink.addDomHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {

					event.stopPropagation();

					showCreationFormInPopup(new AcceptanceDTO());
				}

			}, ClickEvent.getType());

			createLink.add(new Label());
		} else {
			createLink.removeFromParent();
		}
		dataProvider.fetchData();

	}

	private PopupWidget popup;

	private void showCreationFormInPopup(AcceptanceDTO dto) {
		popup = new PopupWidget(

				new FormForFieldsWidget(dataProvider, dto) {

					@Override
					protected void close() {
						popup.closePopup();
					}

					@Override
					protected void updadded(AcceptanceDTO dto) {
						List<AcceptanceDTO> selection = new ArrayList<AcceptanceDTO>();
						selection.add(dto);
						selectionListener.onHasChanged(selection);
					}
				}, 100, 700, true
				);
	}

}

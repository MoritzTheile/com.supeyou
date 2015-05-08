package com.supeyou.crudie.web.client.rpc.file.choosersmall;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.supeyou.crudie.iface.dto.FileDTO;
import com.supeyou.crudie.iface.dto.FileFetchQuery;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListDataProvider;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListWidgetList;
import com.supeyou.crudie.web.client.rpc.abstr.list.ListSelectionModel.SelectionListener;
import com.supeyou.crudie.web.client.rpc.abstr.list.listpager.ListPagerWidget;
import com.supeyou.crudie.web.client.rpc.file.ListDataProvider;
import com.supeyou.crudie.web.client.rpc.file.choosersmall.item.ItemWidget;
import com.supeyou.crudie.web.client.rpc.file.form.FormWidget;
import com.supeyou.crudie.web.client.ui.searchtext.SearchTextWidget;
import com.supeyou.crudie.web.client.ui.searchtext.SearchTextWidget.Listener;
import com.supeyou.crudie.web.client.uiorga.popup.PopupWidget;

public class ChooserSmallWidget extends WidgetView {

	private final AbstrListDataProvider<FileDTO, FileFetchQuery> dataProvider;
	private final SelectionListener<FileDTO> selectionListener;

	public ChooserSmallWidget(SelectionListener<FileDTO> selectionListener) {

		this.selectionListener = selectionListener;

		dataProvider = new ListDataProvider(new FileFetchQuery());
		dataProvider.setPageSize(5);

		searchSlot.add(new SearchTextWidget(dataProvider.getFetchQuery().getSearchString(), new Listener() {

			@Override
			public void search(String searchString) {
				dataProvider.getFetchQuery().setSearchString(searchString);
				dataProvider.fetchData();
			}
		}));

		AbstrListWidgetList<FileDTO, FileFetchQuery> widgetList = new AbstrListWidgetList<FileDTO, FileFetchQuery>(dataProvider) {

			@Override
			public Widget getWidget(final FileDTO data) {

				return new ItemWidget(data);

			}

		};

		widgetList.getSelectionModel().addListener(selectionListener);

		itemsSlot.add(widgetList);

		pagerSlot.add(new ListPagerWidget(dataProvider));

		createLink.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				event.stopPropagation();

				showCreationFormInPopup(new FileDTO());
			}

		}, ClickEvent.getType());

		createLink.add(new Label());

		dataProvider.fetchData();

	}

	private PopupWidget popup;

	private void showCreationFormInPopup(FileDTO dto) {
		popup = new PopupWidget(

				new FormWidget(dataProvider, dto) {

					@Override
					protected void close() {
						popup.closePopup();
					}

					@Override
					protected void updadded(FileDTO dto) {
						List<FileDTO> selection = new ArrayList<FileDTO>();
						selection.add(dto);
						selectionListener.onHasChanged(selection);
					}
				}, 100, 700, true
				);
	}

}

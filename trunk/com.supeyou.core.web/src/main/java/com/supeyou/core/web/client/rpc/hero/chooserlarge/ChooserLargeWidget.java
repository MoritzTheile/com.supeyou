package com.supeyou.core.web.client.rpc.hero.chooserlarge;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Widget;
import com.supeyou.core.iface.dto.HeroDTO;
import com.supeyou.core.iface.dto.HeroFetchQuery;
import com.supeyou.core.web.client.rpc.hero.ListDataProvider;
import com.supeyou.core.web.client.rpc.hero.chooserlarge.item.ItemWidget;
import com.supeyou.core.web.client.rpc.hero.form.FormWidget;
import com.supeyou.core.web.client.rpc.hero.query.QueryWidget;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListDataProvider;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListWidgetList;
import com.supeyou.crudie.web.client.rpc.abstr.list.ListSelectionModel.SelectionListener;
import com.supeyou.crudie.web.client.uiorga.popup.PopupWidget;

public abstract class ChooserLargeWidget extends WidgetView {

	protected final AbstrListDataProvider<HeroDTO, HeroFetchQuery> dataProvider;
	public final AbstrListWidgetList<HeroDTO, HeroFetchQuery> widgetList;

	public ChooserLargeWidget() {

		dataProvider = new ListDataProvider(new HeroFetchQuery());
		dataProvider.setPageSize(5);

		widgetList = new AbstrListWidgetList<HeroDTO, HeroFetchQuery>(dataProvider) {

			@Override
			public Widget getWidget(final HeroDTO data) {

				return getHeroWidget(data);

			}

		};

		widgetList.getSelectionModel().addListener(new SelectionListener<HeroDTO>() {

			@Override
			public void onHasChanged(List<HeroDTO> selection) {

				onSelectionChange(selection);

			}

		});

		itemsSlot.add(widgetList);

		querySlot.add(new QueryWidget(dataProvider));

		// pagerSlot.add(new ListPagerWidget(dataProvider));

		createLink.setVisible(false);

		createLink.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				event.stopPropagation();

				final PopupWidget formPopup = new PopupWidget(false);
				formPopup.setPosition(100, 700);
				formPopup.init(
						new FormWidget(dataProvider, new HeroDTO()) {
							@Override
							protected void close() {
								formPopup.closePopup();
							}
						}
						);

			}
		}, ClickEvent.getType());

		dataProvider.fetchData();

		// footer.add(new ExportLink(dataProvider.getFetchQuery()));
		//
		// footer.add(new ImportLink() {
		// @Override
		// public void importFinished() {
		// dataProvider.fetchData();
		// }
		// });

	}

	public abstract void onSelectionChange(List<HeroDTO> selection);

	public abstract ItemWidget getHeroWidget(final HeroDTO data);
}

package com.supeyou.core.web.client.rpc.hero.chooserlarge;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Widget;
import com.supeyou.core.iface.dto.HeroDTO;
import com.supeyou.core.iface.dto.HeroFetchQuery;
import com.supeyou.core.web.client.HistoryController.ANCHOR;
import com.supeyou.core.web.client.rpc.hero.ListDataProvider;
import com.supeyou.core.web.client.rpc.hero.chooserlarge.item.ItemWidget;
import com.supeyou.core.web.client.rpc.hero.form.FormWidget;
import com.supeyou.core.web.client.rpc.hero.imexport.ExportLink;
import com.supeyou.core.web.client.rpc.hero.imexport.ImportLink;
import com.supeyou.core.web.client.rpc.hero.query.QueryWidget;
import com.supeyou.crudie.web.client.model.LoginStateModel;
import com.supeyou.crudie.web.client.resources.GWTSTATICS;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListDataProvider;
import com.supeyou.crudie.web.client.rpc.abstr.list.AbstrListWidgetList;
import com.supeyou.crudie.web.client.rpc.abstr.list.ListSelectionModel.SelectionListener;
import com.supeyou.crudie.web.client.rpc.abstr.list.listpager.ListPagerWidget;
import com.supeyou.crudie.web.client.uiorga.popup.PopupWidget;

public class ChooserLargeWidget extends WidgetView {

	private final AbstrListDataProvider<HeroDTO, HeroFetchQuery> dataProvider;

	public ChooserLargeWidget() {

		if (LoginStateModel.i().userIsAdmin()) {// UI visibility
			pagerSlot.removeStyleName(GWTSTATICS.HIDESTYLE_CSS);
			createLink.removeStyleName(GWTSTATICS.HIDESTYLE_CSS);
			footer.removeStyleName(GWTSTATICS.HIDESTYLE_CSS);
		} else {
			pagerSlot.addStyleName(GWTSTATICS.HIDESTYLE_CSS);
			createLink.addStyleName(GWTSTATICS.HIDESTYLE_CSS);
			footer.addStyleName(GWTSTATICS.HIDESTYLE_CSS);
		}

		dataProvider = new ListDataProvider(new HeroFetchQuery());
		dataProvider.setPageSize(5);

		final AbstrListWidgetList<HeroDTO, HeroFetchQuery> widgetList = new AbstrListWidgetList<HeroDTO, HeroFetchQuery>(dataProvider) {

			@Override
			public Widget getWidget(final HeroDTO data) {

				return getHeroWidget(data);

			}

		};

		widgetList.getSelectionModel().addListener(new SelectionListener<HeroDTO>() {

			@Override
			public void onHasChanged(List<HeroDTO> selection) {

				if (selection.size() == 1) {

					History.newItem(ANCHOR.HERO.name() + "_" + selection.iterator().next().getId().value());

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

		footer.add(new ExportLink(dataProvider.getFetchQuery()));

		footer.add(new ImportLink() {
			@Override
			public void importFinished() {
				dataProvider.fetchData();
			}
		});

	}

	public ItemWidget getHeroWidget(final HeroDTO data) {

		return new ItemWidget(data);
	}

}

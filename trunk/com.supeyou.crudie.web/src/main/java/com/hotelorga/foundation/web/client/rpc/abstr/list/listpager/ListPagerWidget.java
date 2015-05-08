package com.hotelorga.foundation.web.client.rpc.abstr.list.listpager;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.hotelorga.foundation.web.client.model.AbstrObservable.Observer;
import com.hotelorga.foundation.web.client.resources.i18n.Text;
import com.hotelorga.foundation.web.client.rpc.abstr.list.AbstrListDataProvider;
import com.hotelorga.foundation.web.client.rpc.abstr.list.ListPager;

public class ListPagerWidget extends WidgetView {

	private final ListPager listPager;

	public ListPagerWidget(AbstrListDataProvider<?, ?> cwxDataProvider) {

		cwxDataProvider.addObserver(new Observer<Void>() {

			@Override
			public void modelHasChanged(Void changes) {
				render();
			}

		});

		listPager = new ListPager(cwxDataProvider);
		this.allBackButton.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				event.stopPropagation();
				listPager.goToStart();
			}

		}, ClickEvent.getType());

		this.oneBackButton.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				event.stopPropagation();
				listPager.goToPreviousPage();

			}
		}, ClickEvent.getType());

		this.oneForwardButton.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				event.stopPropagation();
				listPager.goToNextPage();

			}
		}, ClickEvent.getType());

		this.allForwardButton.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				event.stopPropagation();
				listPager.goToEnd();
			}
		}, ClickEvent.getType());

		render();

	}

	public void render() {

		String disabledStyle = "disabled";

		{
			FlowPanel button = this.allBackButton;

			if (listPager.cwxDataProvider.getStartRow() > 0) {
				button.removeStyleName(disabledStyle);
			} else {
				button.addStyleName(disabledStyle);
			}
		}
		{
			FlowPanel button = this.oneBackButton;

			if (listPager.cwxDataProvider.getStartRow() > 0) {
				button.removeStyleName(disabledStyle);
			} else {
				button.addStyleName(disabledStyle);
			}
		}
		{
			this.label.setText(getLabelText());
		}
		{
			FlowPanel button = this.oneForwardButton;

			if (listPager.cwxDataProvider.getStartRow() + listPager.cwxDataProvider.getPageSize() < listPager.cwxDataProvider.getTotalSize()) {
				button.removeStyleName(disabledStyle);
			} else {
				button.addStyleName(disabledStyle);
			}
		}
		{
			FlowPanel button = this.allForwardButton;

			if (listPager.cwxDataProvider.getStartRow() + listPager.cwxDataProvider.getPageSize() < listPager.cwxDataProvider.getTotalSize()) {
				button.removeStyleName(disabledStyle);
			} else {
				button.addStyleName(disabledStyle);
			}
		}
	}

	private String getLabelText() {
		if (listPager.cwxDataProvider.getTotalSize() > 0) {
			return (listPager.cwxDataProvider.getStartRow() + 1) + " " + Text.i.PAGER_To() + " " + (listPager.cwxDataProvider.getStartRow() + listPager.cwxDataProvider.getData().size()) + " " + Text.i.PAGER_Of() + " " + listPager.cwxDataProvider.getTotalSize();

		} else {
			return Text.i.PAGER_NoHits();
		}
	}

}

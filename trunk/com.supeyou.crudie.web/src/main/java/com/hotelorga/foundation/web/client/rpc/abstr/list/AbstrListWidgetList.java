package com.hotelorga.foundation.web.client.rpc.abstr.list;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.hotelorga.foundation.iface.datatype.FetchQuery;
import com.hotelorga.foundation.iface.dto.AbstrDTO;
import com.hotelorga.foundation.web.client.model.AbstrObservable.Observer;
import com.hotelorga.foundation.web.client.rpc.abstr.list.ListSelectionModel.SelectionListener;

public abstract class AbstrListWidgetList<T extends AbstrDTO<?>, F extends FetchQuery> extends FlowPanel {

	private final AbstrListWidgetList<?, F> thisWidget;

	private AbstrListDataProvider<T, F> dataProvider;

	private final ListSelectionModel<T> selectionModel;

	private final Map<T, Widget> data2widget = new HashMap<T, Widget>();

	public enum SELECTIONMODE {
		MULTISELECT, SINGLESELECT
	}

	/*
	 * The SELECTIONMODE is saved to give Observers the possibility to decide weather to take action on selection.
	 */
	private SELECTIONMODE selectionmode = SELECTIONMODE.SINGLESELECT;

	public AbstrListWidgetList(AbstrListDataProvider<T, F> dataProvider) {

		thisWidget = this;

		this.dataProvider = dataProvider;

		selectionModel = new ListSelectionModel<T>();

		selectionModel.addListener(new SelectionListener<T>() {

			@Override
			public void onHasChanged(List<T> selection) {

				render();

			}

		});

	}

	private void render() {

		String selectedStyle = "selected";

		for (final T data : data2widget.keySet()) {

			Widget widget = data2widget.get(data);

			if (selectionModel.isSelected(data)) {
				widget.addStyleName(selectedStyle);
			} else {
				widget.removeStyleName(selectedStyle);
			}
		}

	}

	public ListSelectionModel<T> getSelectionModel() {

		return selectionModel;

	}

	public abstract Widget getWidget(T data);

	private Observer<Void> observer = new Observer<Void>() {

		@Override
		public void modelHasChanged(Void noll) {

			data2widget.clear();
			thisWidget.clear();

			for (final T data : dataProvider.getData()) {

				Widget widget = getWidget(data);

				// without this style, text gets selected on holding Shift while clicking mouse
				widget.addStyleName("unselectable");

				widget.addDomHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {

						event.stopPropagation();

						// if someone clears selection, selection goes to SINGLESELECTION
						if (selectionModel.getSelection().isEmpty()) {
							setSelectionmode(SELECTIONMODE.SINGLESELECT);
						}

						if (event.getNativeEvent().getCtrlKey()) {
							setSelectionmode(SELECTIONMODE.MULTISELECT);
							if (selectionModel.isSelected(data)) {
								selectionModel.unselect(data);
							} else {
								selectionModel.select(data);
							}
						} else if (event.getNativeEvent().getShiftKey()) {
							setSelectionmode(SELECTIONMODE.MULTISELECT);
							selectionModel.selectSilently(data);
							selectionModel.selectRange(dataProvider.getData());
						} else {
							setSelectionmode(SELECTIONMODE.SINGLESELECT);
							selectionModel.selectOnly(data);
						}

					}
				}, ClickEvent.getType());

				thisWidget.add(widget);

				data2widget.put(data, widget);

			}

			render();

		}

	};

	protected void onAttach() {

		dataProvider.addObserver(observer);

		super.onAttach();

	};

	@Override
	protected void onDetach() {

		dataProvider.removeObserver(observer);

		super.onDetach();

	}

	public SELECTIONMODE getSelectionmode() {
		return selectionmode;
	}

	private void setSelectionmode(SELECTIONMODE selectionmode) {
		this.selectionmode = selectionmode;
	}
}

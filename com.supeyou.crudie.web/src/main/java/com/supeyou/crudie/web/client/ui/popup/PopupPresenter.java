package com.supeyou.crudie.web.client.ui.popup;

import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.PopupPanel.PositionCallback;
import com.google.gwt.user.client.ui.Widget;

@Deprecated
public class PopupPresenter extends View {

	public final PopupPanel popup = new PopupPanel();

	public PopupPresenter(Widget widget, boolean glassEnabled, boolean autoHide) {

		init(widget);

		popup.setGlassEnabled(glassEnabled);

		popup.setAutoHideEnabled(autoHide);

		popup.center();

	}

	public PopupPresenter(Widget widget, boolean glassEnabled, final int posLeft, final int posTop) {

		init(widget);

		popup.setGlassEnabled(glassEnabled);

		popup.setPopupPositionAndShow(new PositionCallback() {

			@Override
			public void setPosition(int offsetWidth, int offsetHeight) {

				popup.setPopupPosition(posLeft - (offsetWidth / 2), posTop - offsetHeight);

			}
		});

	}

	private void init(Widget widget) {

		this.content.add(widget);

		popup.setAnimationEnabled(true);

		popup.setWidget(this);

		popup.setAutoHideEnabled(true);
	}

	public void hide() {

		popup.hide();
	}

	public void center() {

		popup.center();

	}

	public void show() {

		popup.show();

	}

	public boolean isShowing() {

		// isShowing() didn't work. Using isAttached() instead:
		return popup.isAttached();

	}

}

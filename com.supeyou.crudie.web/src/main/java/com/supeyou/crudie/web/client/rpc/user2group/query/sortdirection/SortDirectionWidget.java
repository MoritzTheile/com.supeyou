package com.supeyou.crudie.web.client.rpc.user2group.query.sortdirection;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.supeyou.crudie.iface.datatype.FetchQuery.SORTDIRECTION;

public abstract class SortDirectionWidget extends FlowPanel {

	public SortDirectionWidget(SORTDIRECTION sortdirection) {

		this.sortDirection = sortdirection;

		this.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				event.stopPropagation();

				// toggling in circle:
				if (SORTDIRECTION.ASC.equals(sortDirection)) {
					sortDirection = SORTDIRECTION.DESC;
					render();
					hasChanged(sortDirection);
				} else if (SORTDIRECTION.DESC.equals(sortDirection)) {
					sortDirection = SORTDIRECTION.NONE;
					render();
					hasChanged(sortDirection);
				} else if (SORTDIRECTION.NONE.equals(sortDirection)) {
					sortDirection = SORTDIRECTION.ASC;
					render();
					hasChanged(sortDirection);
				}

			}

		}, ClickEvent.getType());

		com.supeyou.crudie.web.client.resources.GWTSTATICS.setJClassAsCSSClass(this);

		this.addStyleName("sort-direction-toggler");

		render();

	}

	private SORTDIRECTION sortDirection = SORTDIRECTION.NONE;

	private void render() {

		String stylePrefix = "sortdirection-";

		// removing all eventually set styles.
		for (SORTDIRECTION direction : SORTDIRECTION.values()) {
			this.removeStyleName(stylePrefix + direction.name());
		}

		// setting style according to sortdirection
		this.addStyleName(stylePrefix + sortDirection.name());
	}

	public abstract void hasChanged(SORTDIRECTION sortdirection);
}

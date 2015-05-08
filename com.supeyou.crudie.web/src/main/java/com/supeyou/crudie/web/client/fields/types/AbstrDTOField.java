package com.supeyou.crudie.web.client.fields.types;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.supeyou.crudie.web.client.fields.base.AbstrCompositeField;
import com.supeyou.crudie.web.client.resources.GWTSTATICS;

public abstract class AbstrDTOField<D> extends AbstrCompositeField<D> {

	private FlowPanel slot = new FlowPanel();

	public AbstrDTOField(D dto) {

		this.initWidget(slot);

		setOrigValue(dto);

		slot.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				showChooser(new ChooserAction<D>() {

					@Override
					public void chosen(D dto) {

						setValue(dto);

					}
				});

			}
		}, ClickEvent.getType());
	}

	protected abstract void showChooser(ChooserAction<D> chooserAction);

	@Override
	protected void render() {

		if (isDirty()) {

			slot.addStyleName(GWTSTATICS.fieldDirtyStyle);

		} else {

			slot.removeStyleName(GWTSTATICS.fieldDirtyStyle);

		}

	}

	@Override
	public void value2field() {
		slot.clear();
		slot.add(getWidget(getValue()));

	}

	public abstract Widget getWidget(D dto);

	public interface ChooserAction<D> {
		void chosen(D dto);
	}

}

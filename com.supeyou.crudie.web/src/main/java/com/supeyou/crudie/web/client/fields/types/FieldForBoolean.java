package com.supeyou.crudie.web.client.fields.types;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.supeyou.crudie.web.client.fields.base.AbstrCompositeField;
import com.supeyou.crudie.web.client.resources.GWTSTATICS;

public class FieldForBoolean extends AbstrCompositeField<Boolean> {

	private CheckBox checkBox;

	public FieldForBoolean(Boolean active) {
		this(active, false);
	}

	public FieldForBoolean(Boolean active, boolean focused) {

		checkBox = new CheckBox() {

		};

		setOrigValue(active);

		if (focused) {
			setFocus();
		}
		this.initWidget(checkBox);

		checkBox.addKeyUpHandler(new KeyUpHandler() {

			@Override
			public void onKeyUp(KeyUpEvent event) {

				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					checkBox.setFocus(false);
				}

				if (event.getNativeKeyCode() == KeyCodes.KEY_ESCAPE) {
					checkBox.setFocus(false);
				}

				updateValue();

			}

		});

		checkBox.addBlurHandler(new BlurHandler() {

			@Override
			public void onBlur(BlurEvent event) {

				updateValue();

			}

		});

		checkBox.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				event.stopPropagation();

				updateValue();

			}
		}, ClickEvent.getType());

		value2field();
	}

	private void updateValue() {

		setValue(checkBox.getValue());
		value2field();

	}

	public boolean isValid() {

		return true;

	}

	@Override
	public void value2field() {

		if (getValue() != null) {
			checkBox.setValue(getValue());
		}

	}

	public void setInvalidStyle(boolean invalid) {

		if (invalid) {
			checkBox.addStyleName(GWTSTATICS.fieldInvalidStyle);
		} else {
			checkBox.removeStyleName(GWTSTATICS.fieldInvalidStyle);
		}

	}

	@Override
	protected void render() {

		if (!isValid()) {

			setInvalidStyle(true);

		} else if (isDirty()) {

			checkBox.addStyleName(GWTSTATICS.fieldDirtyStyle);

		} else {

			checkBox.removeStyleName(GWTSTATICS.fieldDirtyStyle);

		}
	}

}

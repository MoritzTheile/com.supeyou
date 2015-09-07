package com.supeyou.crudie.web.client.fields.types;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.TextBox;
import com.supeyou.crudie.web.client.fields.base.AbstrCompositeField;
import com.supeyou.crudie.web.client.resources.GWTSTATICS;

public abstract class AbstrTextBoxField<T> extends AbstrCompositeField<T> {

	private TextBox textBox = null;

	public AbstrTextBoxField(T value, boolean focused) {

		textBox = new TextBox();

		setOrigValue(value);

		if (focused) {
			setFocus();
		}

		this.initWidget(textBox);

		textBox.addKeyUpHandler(new KeyUpHandler() {

			@Override
			public void onKeyUp(KeyUpEvent event) {

				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					textBox.setFocus(false);
				} else if (event.getNativeKeyCode() == KeyCodes.KEY_ESCAPE) {
					textBox.setFocus(false);
				} else {
					render();
				}

			}

		});

		textBox.addBlurHandler(new BlurHandler() {

			@Override
			public void onBlur(BlurEvent event) {

				field2value();

			}

		});

	}

	protected abstract boolean valid(String value);

	protected abstract String getWrongFormatMessage();

	protected abstract T string2value(String string);

	protected abstract String value2String(T value);

	public void field2value() {

		if (valid(textBox.getValue())) {
			setValue(string2value(textBox.getValue()));
		}

		render();

	}

	@Override
	public void value2field() {

		textBox.setValue(value2String(getValue()));

	}

	public void render() {

		textBox.removeStyleName(GWTSTATICS.fieldInvalidStyle);
		textBox.removeStyleName(GWTSTATICS.fieldDirtyStyle);

		if (!valid(textBox.getValue())) {

			textBox.addStyleName(GWTSTATICS.fieldInvalidStyle);

		} else if (isDirty()) {

			textBox.addStyleName(GWTSTATICS.fieldDirtyStyle);

		}

	}

	public void setFocus(boolean hasFocus) {
		textBox.setFocus(hasFocus);
	}

}

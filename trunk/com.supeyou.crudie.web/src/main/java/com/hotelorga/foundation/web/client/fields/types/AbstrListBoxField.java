package com.hotelorga.foundation.web.client.fields.types;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ListBox;
import com.hotelorga.foundation.web.client.fields.base.AbstrCompositeField;
import com.hotelorga.foundation.web.client.resources.GWTSTATICS;

public abstract class AbstrListBoxField<T> extends AbstrCompositeField<T> {

	private Map<String, T> displayName2value;
	private Map<T, String> value2displayName;
	private Map<String, Integer> displayName2index;

	private ListBox listBox;

	public AbstrListBoxField(T value, boolean focused) {

		listBox = new ListBox();
		displayName2value = new HashMap<String, T>();
		value2displayName = new HashMap<T, String>();
		displayName2index = new HashMap<String, Integer>();

		if (focused) {
			setFocus();
		}

		this.initWidget(listBox);

		for (T value_ : getValues()) {

			String displayName = value2displayName(value_);

			if (displayName == null) {
				displayName = value_.toString();
			}

			addTupel(value_, displayName);

		}

		addTupel(null, "");

		try {
			for (int i = 0; i < listBox.getItemCount(); i++) {
				String displayName = listBox.getItemText(i);

				displayName2index.put(displayName, i);
			}

		} catch (Exception e) {
			Window.alert("codemarker=gh8wgla\n" + e.getMessage());
		}

		setOrigValue(value);

		listBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {

				setValue(displayName2value.get(listBox.getValue(listBox.getSelectedIndex())));

			}

		});

		listBox.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				// just preventing event propagation
				event.stopPropagation();

			}
		});

	}

	private void addTupel(T value, String displayName) {
		displayName2value.put(displayName, value);
		value2displayName.put(value, displayName);

		listBox.addItem(displayName);
	}

	@Override
	public void value2field() {
		listBox.setSelectedIndex(displayName2index.get(value2displayName.get(getValue())));
	}

	public abstract String value2displayName(T value);

	public abstract List<T> getValues();

	@Override
	protected void render() {

		if (isDirty()) {

			listBox.addStyleName(GWTSTATICS.fieldDirtyStyle);

		} else {

			listBox.removeStyleName(GWTSTATICS.fieldDirtyStyle);

		}

	}

}

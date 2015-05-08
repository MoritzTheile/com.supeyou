package com.hotelorga.foundation.web.client.fields.base;

import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.event.logical.shared.AttachEvent.Handler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusWidget;
import com.hotelorga.foundation.web.client.resources.GWTSTATICS;

public abstract class AbstrCompositeField<T> extends Composite implements Field<T> {

	private T origValue;

	private T value;

	public void setFocus() {

		this.addAttachHandler(new Handler() {

			@Override
			public void onAttachOrDetach(AttachEvent event) {

				// a hack for waiting until focus gets picked up
				new Timer() {

					@Override
					public void run() {
						setFocus(true);

					}
				}.schedule(300);

			}
		});

	}

	protected abstract void render();

	public final void setOrigValue(T value) {

		this.origValue = value;
		this.value = value;
		value2field();

		render();

	}

	protected final T getOrigValue() {
		return origValue;
	}

	public final T getValue() {
		return value;
	}

	protected final void setValue(T value) {

		boolean hasChanged = !GWTSTATICS.nullsafeEquals(value, this.value);

		this.value = value;

		if (hasChanged) {
			onHasChanged(value);
		}

		value2field();

	}

	@Override
	public final boolean isDirty() {

		return !GWTSTATICS.nullsafeEquals(value, origValue);

	}

	public void onHasChanged(T value) {
		// can be overwritten
	};

	public abstract void value2field();

	private final void setFocus(boolean focused) {
		if (getWidget() instanceof FocusWidget) {
			((FocusWidget) getWidget()).setFocus(focused);
		}
	}

}

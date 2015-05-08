package com.hotelorga.foundation.web.client.fields.base;

public interface Field<T> {

	boolean isDirty();

	void setOrigValue(T value);

	T getValue();

}

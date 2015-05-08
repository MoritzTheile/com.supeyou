package com.supeyou.crudie.iface.datatype.types;

import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class AbstrType<T> implements Serializable {

	protected T value;

	public void setValue(T value) {
		isValid(value);
		this.value = value;
	}

	protected abstract void isValid(T value2) throws TypeException;

	public T value() {
		return value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		try {
			@SuppressWarnings("unchecked")
			AbstrType<T> abstrType = (AbstrType<T>) obj;
			return nullsafeEquals(this.value(), abstrType.value());
		} catch (Exception e) {
			System.out.println("codemarker=a9rgalllo");
		}
		return false;
	}

	private static boolean nullsafeEquals(Object o1, Object o2) {

		if (o1 == null && o2 == null) {
			return true;
		}
		if (o1 != null && o2 == null) {
			return false;
		}
		if (o1 == null && o2 != null) {
			return false;
		}

		return o1.equals(o2);

	}

	@Override
	public String toString() {
		return value + "";
	}

}

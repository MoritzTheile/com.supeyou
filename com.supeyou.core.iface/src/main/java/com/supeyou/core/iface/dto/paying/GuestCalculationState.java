package com.supeyou.core.iface.dto.paying;

import java.io.Serializable;

public class GuestCalculationState implements Serializable {

	private static final long serialVersionUID = 7414478439399194L;

	// zero arg constructor
	public GuestCalculationState() {
	}

	public enum STATE {

		OK, WARN, ERROR;

	}

	private STATE state;

	private String message = "";

	public GuestCalculationState(STATE state) {
		this.state = state;
	}

	public GuestCalculationState(STATE state, String message) {
		this.state = state;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public STATE getState() {
		return state;
	}

	public void setState(STATE state) {
		this.state = state;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		GuestCalculationState other = (GuestCalculationState) obj;
		if (state != other.state)
			return false;
		return true;
	}

}
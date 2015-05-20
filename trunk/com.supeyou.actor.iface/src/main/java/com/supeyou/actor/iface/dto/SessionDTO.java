package com.supeyou.actor.iface.dto;

import java.io.Serializable;

import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.iface.dto.AbstrDTO;

public class SessionDTO extends AbstrDTO<SessionIDType> implements Serializable {

	private static final long serialVersionUID = 82461466818L;

	private SingleLineString256Type httpSessionId;
	private SingleLineString256Type browserMark;

	public SessionDTO() {

	}

	public SessionDTO(SessionIDType id) {
		setId(id);
	}

	public SessionDTO(String id) {
		setId(new SessionIDType(id));
	}

	@Override
	public void setId(SessionIDType id) {
		super.setId(id);
	}

	@Override
	public SessionIDType getId() {
		return super.getId();
	}

	public SingleLineString256Type getHttpSessionId() {
		return httpSessionId;
	}

	public void setHttpSessionId(SingleLineString256Type httpSessionId) {
		this.httpSessionId = httpSessionId;
	}

	public SingleLineString256Type getBrowserMark() {
		return browserMark;
	}

	public void setBrowserMark(SingleLineString256Type browserMark) {
		this.browserMark = browserMark;
	}

}

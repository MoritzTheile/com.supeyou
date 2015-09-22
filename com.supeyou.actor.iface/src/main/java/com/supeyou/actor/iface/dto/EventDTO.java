package com.supeyou.actor.iface.dto;

import java.io.Serializable;

import com.supeyou.crudie.iface.datatype.types.FormattedTimeType;
import com.supeyou.crudie.iface.datatype.types.PositivIntegerType;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.iface.dto.AbstrDTO;

public class EventDTO extends AbstrDTO<EventIDType> implements Serializable {

	private static final long serialVersionUID = 82461466818L;

	private FormattedTimeType formattedTimestamp;
	private PositivIntegerType pageAgeSeconds;
	private SingleLineString256Type category;
	private SingleLineString256Type action;
	private SingleLineString256Type value;

	private SingleLineString256Type userLoginId;
	private SingleLineString256Type userId;
	private SingleLineString256Type userName;
	private SingleLineString256Type sessionId;

	public EventDTO() {

	}

	public EventDTO(EventIDType id) {
		setId(id);
	}

	public EventDTO(String id) {
		setId(new EventIDType(id));
	}

	@Override
	public void setId(EventIDType id) {
		super.setId(id);
	}

	@Override
	public EventIDType getId() {
		return super.getId();
	}

	public SingleLineString256Type getCategory() {
		return category;
	}

	public void setCategory(SingleLineString256Type category) {
		this.category = category;
	}

	public SingleLineString256Type getAction() {
		return action;
	}

	public void setAction(SingleLineString256Type action) {
		this.action = action;
	}

	public SingleLineString256Type getValue() {
		return value;
	}

	public void setValue(SingleLineString256Type value) {
		this.value = value;
	}

	public FormattedTimeType getFormattedTimestamp() {
		return formattedTimestamp;
	}

	public void setFormattedTimestamp(FormattedTimeType formattedTimestamp) {
		this.formattedTimestamp = formattedTimestamp;
	}

	public PositivIntegerType getPageAgeSeconds() {
		return pageAgeSeconds;
	}

	public void setPageAgeSeconds(PositivIntegerType pageAgeSeconds) {
		this.pageAgeSeconds = pageAgeSeconds;
	}

	public SingleLineString256Type getUserLoginId() {
		return userLoginId;
	}

	public void setUserLoginId(SingleLineString256Type userLoginId) {
		this.userLoginId = userLoginId;
	}

	public SingleLineString256Type getUserId() {
		return userId;
	}

	public void setUserId(SingleLineString256Type userId) {
		this.userId = userId;
	}

	public SingleLineString256Type getUserName() {
		return userName;
	}

	public void setUserName(SingleLineString256Type userName) {
		this.userName = userName;
	}

	public SingleLineString256Type getSessionId() {
		return sessionId;
	}

	public void setSessionId(SingleLineString256Type sessionId) {
		this.sessionId = sessionId;
	}

}

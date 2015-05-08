package com.hotelorga.core.iface.dto;

import com.hotelorga.foundation.iface.datatype.FetchAssoQuery;
import com.hotelorga.foundation.iface.datatype.types.AbstrType;
import com.hotelorga.foundation.iface.datatype.types.DateType;

public class Guest2RoomFetchQuery extends FetchAssoQuery {

	private static final long serialVersionUID = 827206L;

	private RoomDTO room = null;

	private DateType from = null;

	private DateType to = null;

	public DateType getFrom() {
		return from;
	}

	public void setFrom(DateType from) {
		this.from = from;
	}

	public DateType getTo() {
		return to;
	}

	public void setTo(DateType to) {
		this.to = to;
	}

	public RoomDTO getRoom() {
		return room;
	}

	/**
	 * The attribute room is just for having the room when rendering the query view. Since it is redundant information the id is synchronized on setting room or id.
	 */
	public void setRoomAndRoomID(RoomDTO room) {
		if (room == null) {
			setIdB(null);
		} else {
			setIdB(room.getId());
		}
		this.room = room;
	}

	@Override
	public void setIdB(AbstrType<Long> idB) {
		if (room != null && room.getId() != idB) {
			room = null;
		}
		super.setIdB(idB);
	}
}

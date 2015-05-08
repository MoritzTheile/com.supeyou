package com.hotelorga.core.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.hotelorga.core.iface.dto.RoomIDType;
import com.hotelorga.foundation.iface.datatype.types.PositivIntegerType;
import com.hotelorga.foundation.iface.datatype.types.SingleLineString256Type;
import com.hotelorga.foundation.impl.entity.AbstrEntity;

@Entity
@Table(name = "RoomEntity")
public class RoomEntity extends AbstrEntity<RoomIDType> {

	/**
	 * Login of User. Can be used additionally to the email.
	 */
	// @Index(name = "Room_NAME_INDEX")
	@Column(name = "NAME", nullable = true, length = 256, unique = true)
	private String name;

	private String comment;
	private Integer numberOfBeds;

	// // no getter or setter, just for defining cascades
	// @OneToMany(mappedBy = "b"/* =the attribute name, not the column name! */, cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	// private Collection<User2GroupEntity> user2groupCollection = new ArrayList<User2GroupEntity>();

	public SingleLineString256Type getName() {
		if (name == null) {
			return null;
		}
		return new SingleLineString256Type(name);
	}

	public void setName(SingleLineString256Type name) {
		if (name != null) {
			this.name = name.value();
		} else {
			this.name = null;
		}
	}

	public PositivIntegerType getNumberOfBeds() {
		if (numberOfBeds == null) {
			return null;
		}
		return new PositivIntegerType(numberOfBeds);
	}

	public void setNumberOfBeds(PositivIntegerType value) {
		if (value != null) {
			this.numberOfBeds = value.value();
		} else {
			this.numberOfBeds = null;
		}
	}

	public SingleLineString256Type getComment() {
		if (comment == null) {
			return null;
		}
		return new SingleLineString256Type(comment);
	}

	public void setComment(SingleLineString256Type comment) {
		if (comment != null) {
			this.comment = comment.value();
		} else {
			this.comment = null;
		}
	}

	@Override
	public RoomIDType getId() {
		if (dbid == null) {
			return null;
		}
		return new RoomIDType(dbid);
	}

	@Override
	public void setId(RoomIDType typedId) {
		if (typedId != null) {
			dbid = typedId.value();
		} else {
			this.dbid = null;
		}

	}

}
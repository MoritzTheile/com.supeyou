package com.hotelorga.core.impl.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.hotelorga.core.iface.dto.Guest2AcceptanceIDType;
import com.hotelorga.foundation.impl.entity.AbstrManyToManyAsso;
import com.hotelorga.foundation.impl.util.STATICS;

@Entity
@Table(name = "Guest2AcceptanceAsso", uniqueConstraints = @UniqueConstraint(columnNames = { STATICS.A_DBID, STATICS.B_DBID }))
public class Guest2AcceptanceEntity extends AbstrManyToManyAsso<GuestEntity, AcceptanceEntity, Guest2AcceptanceIDType> {

	@Override
	public Guest2AcceptanceIDType getId() {
		if (dbid == null) {
			return null;
		}
		return new Guest2AcceptanceIDType(dbid);
	}

	@Override
	public void setId(Guest2AcceptanceIDType typedId) {
		if (typedId != null) {
			dbid = typedId.value();
		} else {
			this.dbid = null;
		}

	}

}

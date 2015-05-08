package com.hotelorga.core.impl.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.hotelorga.core.iface.dto.Acceptance2PayerIDType;
import com.hotelorga.foundation.impl.entity.AbstrManyToManyAsso;
import com.hotelorga.foundation.impl.util.STATICS;

@Entity
@Table(name = "Acceptance2PayerAsso", uniqueConstraints = @UniqueConstraint(columnNames = { STATICS.A_DBID }))
public class Acceptance2PayerEntity extends AbstrManyToManyAsso<AcceptanceEntity, PayerEntity, Acceptance2PayerIDType> {

	@Override
	public Acceptance2PayerIDType getId() {
		if (dbid == null) {
			return null;
		}
		return new Acceptance2PayerIDType(dbid);
	}

	@Override
	public void setId(Acceptance2PayerIDType typedId) {
		if (typedId != null) {
			dbid = typedId.value();
		} else {
			this.dbid = null;
		}

	}

}

package com.supeyou.core.impl.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.supeyou.core.iface.dto.Invitation2SupporterIDType;
import com.supeyou.crudie.impl.entity.AbstrManyToManyAsso;
import com.supeyou.crudie.impl.util.STATICS;

@Entity
@Table(name = "Invitation2SupporterAsso", uniqueConstraints = @UniqueConstraint(columnNames = { STATICS.A_DBID, STATICS.B_DBID }))
public class Invitation2SupporterEntity extends AbstrManyToManyAsso<InvitationEntity, SupporterEntity, Invitation2SupporterIDType> {

	private boolean createsCycle = false;

	@Override
	public Invitation2SupporterIDType getId() {
		if (this.dbid != null) {
			return new Invitation2SupporterIDType(dbid);
		} else {
			return null;
		}
	}

	@Override
	public void setId(Invitation2SupporterIDType typedId) {
		if (typedId != null) {
			this.dbid = typedId.value();
		} else {
			this.dbid = null;
		}
	}

	public boolean isCreatesCycle() {
		return createsCycle;
	}

	public void setCreatesCycle(boolean createsCycle) {
		this.createsCycle = createsCycle;
	}

}

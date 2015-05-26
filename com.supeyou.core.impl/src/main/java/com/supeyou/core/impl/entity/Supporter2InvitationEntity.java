package com.supeyou.core.impl.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.supeyou.core.iface.dto.Supporter2InvitationIDType;
import com.supeyou.crudie.impl.entity.AbstrManyToManyAsso;
import com.supeyou.crudie.impl.util.STATICS;

@Entity
@Table(name = "Supporter2InvitationAsso", uniqueConstraints = @UniqueConstraint(columnNames = { STATICS.A_DBID, STATICS.B_DBID }))
public class Supporter2InvitationEntity extends AbstrManyToManyAsso<SupporterEntity, InvitationEntity, Supporter2InvitationIDType> {

	@Override
	public Supporter2InvitationIDType getId() {
		if (this.dbid != null) {
			return new Supporter2InvitationIDType(dbid);
		} else {
			return null;
		}
	}

	@Override
	public void setId(Supporter2InvitationIDType typedId) {
		if (typedId != null) {
			this.dbid = typedId.value();
		} else {
			this.dbid = null;
		}
	}

}

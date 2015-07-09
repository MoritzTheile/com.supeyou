package com.supeyou.core.impl.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.supeyou.core.iface.dto.Hero2SupporterIDType;
import com.supeyou.crudie.impl.entity.AbstrManyToManyAsso;
import com.supeyou.crudie.impl.util.STATICS;

@Entity
@Table(name = "Hero2SupporterAsso", uniqueConstraints = @UniqueConstraint(columnNames = { STATICS.A_DBID, STATICS.B_DBID }))
public class Hero2SupporterEntity extends AbstrManyToManyAsso<HeroEntity, SupporterEntity, Hero2SupporterIDType> {

	@Override
	public Hero2SupporterIDType getId() {
		if (this.dbid != null) {
			return new Hero2SupporterIDType(dbid);
		} else {
			return null;
		}
	}

	@Override
	public void setId(Hero2SupporterIDType typedId) {
		if (typedId != null) {
			this.dbid = typedId.value();
		} else {
			this.dbid = null;
		}
	}

}

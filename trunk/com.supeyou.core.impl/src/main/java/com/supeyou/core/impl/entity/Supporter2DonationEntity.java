package com.supeyou.core.impl.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.supeyou.core.iface.dto.Supporter2DonationIDType;
import com.supeyou.crudie.impl.entity.AbstrManyToManyAsso;
import com.supeyou.crudie.impl.util.STATICS;

@Entity
@Table(name = "Supporter2DonationAsso", uniqueConstraints = @UniqueConstraint(columnNames = { STATICS.A_DBID, STATICS.B_DBID }))
public class Supporter2DonationEntity extends AbstrManyToManyAsso<SupporterEntity, DonationEntity, Supporter2DonationIDType> {

	@Override
	public Supporter2DonationIDType getId() {
		if (this.dbid != null) {
			return new Supporter2DonationIDType(dbid);
		} else {
			return null;
		}
	}

	@Override
	public void setId(Supporter2DonationIDType typedId) {
		if (typedId != null) {
			this.dbid = typedId.value();
		} else {
			this.dbid = null;
		}
	}

}

package com.hotelorga.foundation.impl.entity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.hotelorga.foundation.iface.datatype.types.AbstrType;
import com.hotelorga.foundation.impl.util.STATICS;

@MappedSuperclass
public abstract class AbstrManyToManyAsso<A extends AbstrEntity<?>, B extends AbstrEntity<?>, T extends AbstrType<Long>> extends AbstrEntity<T> {

	@ManyToOne(optional = false)
	@JoinColumn(name = STATICS.A_DBID)
	private A a;

	@ManyToOne(optional = false)
	@JoinColumn(name = STATICS.B_DBID)
	private B b;

	public A getA() {
		return a;
	}

	public void setA(A a) {
		this.a = a;
	}

	public B getB() {
		return b;
	}

	public void setB(B b) {
		this.b = b;
	}

}

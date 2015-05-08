package com.supeyou.crudie.impl.entity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.supeyou.crudie.iface.datatype.types.AbstrType;
import com.supeyou.crudie.impl.util.STATICS;

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

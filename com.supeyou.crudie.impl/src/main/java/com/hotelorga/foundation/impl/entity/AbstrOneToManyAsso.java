package com.hotelorga.foundation.impl.entity;

import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import com.hotelorga.foundation.iface.datatype.types.AbstrType;

@MappedSuperclass
public abstract class AbstrOneToManyAsso<A extends AbstrEntity<?>, B extends AbstrEntity<?>, T extends AbstrType<Long>> extends AbstrEntity<T> {

	@OneToOne(optional = false)
	private A a;

	@ManyToOne(optional = false)
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

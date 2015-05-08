package com.hotelorga.foundation.iface.dto;

import com.hotelorga.foundation.iface.datatype.types.AbstrType;

public abstract class AbstrAssoDTO<A extends AbstrDTO<?>, B extends AbstrDTO<?>, I extends AbstrType<Long>> extends AbstrDTO<I> {

	private static final long serialVersionUID = 9064258929478920902L;

	private A dtoA;
	private B dtoB;

	public AbstrAssoDTO() {

	}

	public A getDtoA() {
		return dtoA;
	}

	public void setDtoA(A dtoA) {
		this.dtoA = dtoA;
	}

	public B getDtoB() {
		return dtoB;
	}

	public void setDtoB(B dtoB) {
		this.dtoB = dtoB;
	}

}

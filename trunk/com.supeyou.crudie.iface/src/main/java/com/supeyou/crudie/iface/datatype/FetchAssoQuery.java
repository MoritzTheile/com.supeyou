package com.supeyou.crudie.iface.datatype;

import com.supeyou.crudie.iface.datatype.types.AbstrType;

public class FetchAssoQuery extends FetchQuery {

	private static final long serialVersionUID = -1693345588064509577L;

	private AbstrType<Long> idA;
	private AbstrType<Long> idB;

	public AbstrType<Long> getIdA() {
		return idA;
	}

	public void setIdA(AbstrType<Long> idA) {
		this.idA = idA;
	}

	public AbstrType<Long> getIdB() {
		return idB;
	}

	public void setIdB(AbstrType<Long> idB) {
		this.idB = idB;
	}

}

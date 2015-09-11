package com.supeyou.crudie.iface;

import com.supeyou.crudie.iface.datatype.types.AbstrType;

public interface CRUDObserver<D> {

	void wasCreated(D dto);

	void wasRead(D dto);

	void wasUpdated(D dto, D oldDTO);

	void wasDeleted(AbstrType<Long> dtoId);
}

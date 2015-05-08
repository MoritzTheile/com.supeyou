package com.hotelorga.foundation.iface;

import com.hotelorga.foundation.iface.datatype.FetchAssoQuery;
import com.hotelorga.foundation.iface.datatype.types.AbstrType;
import com.hotelorga.foundation.iface.dto.AbstrAssoDTO;
import com.hotelorga.foundation.iface.dto.AbstrDTO;

public interface CRUDAssoService<A extends AbstrDTO<?>, B extends AbstrDTO<?>, I extends AbstrType<Long>, D extends AbstrAssoDTO<A, B, I>, F extends FetchAssoQuery>
		extends CRUDService<D, F> {

}

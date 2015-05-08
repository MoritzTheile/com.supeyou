package com.supeyou.crudie.iface;

import com.supeyou.crudie.iface.datatype.FetchAssoQuery;
import com.supeyou.crudie.iface.datatype.types.AbstrType;
import com.supeyou.crudie.iface.dto.AbstrAssoDTO;
import com.supeyou.crudie.iface.dto.AbstrDTO;

public interface CRUDAssoService<A extends AbstrDTO<?>, B extends AbstrDTO<?>, I extends AbstrType<Long>, D extends AbstrAssoDTO<A, B, I>, F extends FetchAssoQuery>
		extends CRUDService<D, F> {

}

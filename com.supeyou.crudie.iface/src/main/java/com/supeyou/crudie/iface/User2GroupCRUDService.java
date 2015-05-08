package com.supeyou.crudie.iface;

import com.supeyou.crudie.iface.datatype.types.User2GroupIDType;
import com.supeyou.crudie.iface.dto.AbstrAssoDTO;
import com.supeyou.crudie.iface.dto.GroupDTO;
import com.supeyou.crudie.iface.dto.User2GroupFetchQuery;
import com.supeyou.crudie.iface.dto.UserDTO;

public interface User2GroupCRUDService extends CRUDAssoService<UserDTO, GroupDTO, User2GroupIDType, AbstrAssoDTO<UserDTO, GroupDTO, User2GroupIDType>, User2GroupFetchQuery> {

}
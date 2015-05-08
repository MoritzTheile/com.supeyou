package com.hotelorga.foundation.iface;

import com.hotelorga.foundation.iface.datatype.types.User2GroupIDType;
import com.hotelorga.foundation.iface.dto.AbstrAssoDTO;
import com.hotelorga.foundation.iface.dto.GroupDTO;
import com.hotelorga.foundation.iface.dto.User2GroupFetchQuery;
import com.hotelorga.foundation.iface.dto.UserDTO;

public interface User2GroupCRUDService extends CRUDAssoService<UserDTO, GroupDTO, User2GroupIDType, AbstrAssoDTO<UserDTO, GroupDTO, User2GroupIDType>, User2GroupFetchQuery> {

}
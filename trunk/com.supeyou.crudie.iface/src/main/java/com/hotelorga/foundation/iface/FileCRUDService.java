package com.hotelorga.foundation.iface;

import com.hotelorga.foundation.iface.datatype.CRUDException;
import com.hotelorga.foundation.iface.datatype.types.FileIDType;
import com.hotelorga.foundation.iface.dto.FileDTO;
import com.hotelorga.foundation.iface.dto.FileFetchQuery;
import com.hotelorga.foundation.iface.dto.UserDTO;

public interface FileCRUDService extends CRUDService<FileDTO, FileFetchQuery> {

	void setData(UserDTO actorDTO, FileIDType fileId, byte[] data) throws CRUDException;

	byte[] getData(UserDTO actorDTO, FileIDType fileId) throws CRUDException;

}
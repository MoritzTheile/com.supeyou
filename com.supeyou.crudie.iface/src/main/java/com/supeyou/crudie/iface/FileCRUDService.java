package com.supeyou.crudie.iface;

import com.supeyou.crudie.iface.datatype.CRUDException;
import com.supeyou.crudie.iface.datatype.types.FileIDType;
import com.supeyou.crudie.iface.dto.FileDTO;
import com.supeyou.crudie.iface.dto.FileFetchQuery;
import com.supeyou.crudie.iface.dto.UserDTO;

public interface FileCRUDService extends CRUDService<FileDTO, FileFetchQuery> {

	void setData(UserDTO actorDTO, FileIDType fileId, byte[] data) throws CRUDException;

	byte[] getData(UserDTO actorDTO, FileIDType fileId) throws CRUDException;

}
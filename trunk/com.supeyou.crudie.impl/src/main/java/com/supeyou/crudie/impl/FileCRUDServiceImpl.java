package com.supeyou.crudie.impl;

import com.supeyou.crudie.iface.FileCRUDService;
import com.supeyou.crudie.iface.datatype.CRUDException;
import com.supeyou.crudie.iface.datatype.CRUDException.CODE;
import com.supeyou.crudie.iface.datatype.types.FileIDType;
import com.supeyou.crudie.iface.datatype.types.PositivLongType;
import com.supeyou.crudie.iface.dto.FileDTO;
import com.supeyou.crudie.iface.dto.FileFetchQuery;
import com.supeyou.crudie.iface.dto.UserDTO;
import com.supeyou.crudie.impl.entity.FileEntity;
import com.supeyou.crudie.impl.entity.UserEntity;
import com.supeyou.crudie.impl.util.STATICS;
import com.supeyou.crudie.impl.util.TransactionTemplate;

public class FileCRUDServiceImpl extends AbstrCRUDServiceImpl<FileDTO, FileEntity, FileFetchQuery> implements FileCRUDService {

	@Override
	public void setData(UserDTO actorDTO, final FileIDType fileId, final byte[] data) throws CRUDException {

		new TransactionTemplate<Void>(actorDTO, STATICS.getEntityManager()) {

			@Override
			public void checkPermissions(UserEntity actor) throws CRUDException {
				// Permissions still have to be implemented
			}

			@Override
			protected Void transactionBody() throws Exception {

				FileEntity entity = em.find(FileEntity.class, fileId.value());

				if (entity == null) {
					throw new CRUDException(CODE.NO_ENTITY_FOUND_BY_GIVEN_ID);
				}

				entity.setData(data);

				if (data != null) {
					entity.setSize(new PositivLongType(new Long(data.length)));
				}

				return null;

			}

		}.execute();

	}

	@Override
	public byte[] getData(UserDTO actorDTO, final FileIDType fileId) throws CRUDException {

		return new TransactionTemplate<byte[]>(actorDTO, STATICS.getEntityManager()) {

			@Override
			public void checkPermissions(UserEntity actor) throws CRUDException {
				// Permissions still have to be implemented
			}

			@Override
			protected byte[] transactionBody() throws Exception {

				FileEntity entity = em.find(FileEntity.class, fileId.value());

				if (entity == null) {
					throw new CRUDException(CODE.NO_ENTITY_FOUND_BY_GIVEN_ID);
				}

				return entity.getData();

			}
		}.execute();

	}

	// Singleton

	private static FileCRUDServiceImpl service;

	private FileCRUDServiceImpl() {
		super(FileDTO.class, FileEntity.class);
	}

	public static FileCRUDServiceImpl i() {
		if (service == null) {
			service = new FileCRUDServiceImpl();
		}
		return service;
	}

}

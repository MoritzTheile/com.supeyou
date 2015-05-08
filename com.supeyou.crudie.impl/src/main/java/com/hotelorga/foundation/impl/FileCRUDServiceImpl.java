package com.hotelorga.foundation.impl;

import com.hotelorga.foundation.iface.FileCRUDService;
import com.hotelorga.foundation.iface.datatype.CRUDException;
import com.hotelorga.foundation.iface.datatype.CRUDException.CODE;
import com.hotelorga.foundation.iface.datatype.types.FileIDType;
import com.hotelorga.foundation.iface.datatype.types.PositivLongType;
import com.hotelorga.foundation.iface.dto.FileDTO;
import com.hotelorga.foundation.iface.dto.FileFetchQuery;
import com.hotelorga.foundation.iface.dto.UserDTO;
import com.hotelorga.foundation.impl.entity.FileEntity;
import com.hotelorga.foundation.impl.entity.UserEntity;
import com.hotelorga.foundation.impl.util.STATICS;
import com.hotelorga.foundation.impl.util.TransactionTemplate;

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

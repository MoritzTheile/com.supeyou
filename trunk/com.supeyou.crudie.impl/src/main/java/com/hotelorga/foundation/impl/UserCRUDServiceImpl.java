package com.hotelorga.foundation.impl;

import javax.persistence.EntityManager;

import com.hotelorga.foundation.iface.UserCRUDService;
import com.hotelorga.foundation.iface.datatype.CRUDException;
import com.hotelorga.foundation.iface.datatype.enums.ROLE;
import com.hotelorga.foundation.iface.datatype.types.EmailAddressType;
import com.hotelorga.foundation.iface.datatype.types.SingleLineString256Type;
import com.hotelorga.foundation.iface.datatype.types.UserIDType;
import com.hotelorga.foundation.iface.dto.UserDTO;
import com.hotelorga.foundation.iface.dto.UserFetchQuery;
import com.hotelorga.foundation.impl.entity.UserEntity;
import com.hotelorga.foundation.impl.util.STATICS;
import com.hotelorga.foundation.impl.util.TransactionTemplate;

public class UserCRUDServiceImpl extends AbstrCRUDServiceImpl<UserDTO, UserEntity, UserFetchQuery> implements UserCRUDService {

	private static final UserIDType initialAdminId = new UserIDType(1L);

	@Override
	public UserDTO getInitialAdmin() throws CRUDException {
		return new TransactionTemplate<UserDTO>(null, STATICS.getEntityManager()) {

			@Override
			public void checkPermissions(UserEntity actor) throws CRUDException {
				// no permissions necessary
				// => make sure this service is not accessible from the outside world

			}

			@Override
			protected UserDTO transactionBody() throws Exception {

				UserEntity entity = em.find(UserEntity.class, initialAdminId.value());

				if (entity == null) {

					entity = new UserEntity();

					entity.setId(initialAdminId);
					entity.setLoginId(new SingleLineString256Type("MT"));
					entity.setEmailAddress(new EmailAddressType("theile@mtheile.com"));
					entity.getRoles().add(ROLE.ADMIN);

					em.merge(entity);
				}

				UserDTO dto = helper.entity2DTO(entity);

				return dto;
			}
		}.execute();
	}

	@Override
	protected String getWhereClause(EntityManager em, UserEntity actor, UserFetchQuery query) {
		String whereClause = "";
		if (query.getSearchString() != null && !query.getSearchString().isEmpty()) {
			whereClause = "where " + UserFetchQuery.COLUMN_EMAILADDRESS + " like '%" + query.getSearchString() + "%'";
		}
		return whereClause;
	}

	// Singleton

	private static UserCRUDServiceImpl service;

	private UserCRUDServiceImpl() {
		super(UserDTO.class, UserEntity.class);
	}

	public static UserCRUDServiceImpl i() {
		if (service == null) {
			service = new UserCRUDServiceImpl();
		}
		return service;
	}

}

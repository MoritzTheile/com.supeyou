package com.supeyou.crudie.impl;

import javax.persistence.EntityManager;

import com.supeyou.crudie.iface.UserCRUDService;
import com.supeyou.crudie.iface.datatype.CRUDException;
import com.supeyou.crudie.iface.datatype.enums.ROLE;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.iface.datatype.types.UserIDType;
import com.supeyou.crudie.iface.dto.UserDTO;
import com.supeyou.crudie.iface.dto.UserFetchQuery;
import com.supeyou.crudie.impl.entity.UserEntity;
import com.supeyou.crudie.impl.util.Random;
import com.supeyou.crudie.impl.util.STATICS;
import com.supeyou.crudie.impl.util.TransactionTemplate;

public class UserCRUDServiceImpl extends AbstrCRUDServiceImpl<UserDTO, UserEntity, UserFetchQuery> implements UserCRUDService {

	private static final UserIDType initialAdminId = new UserIDType(1L);

	@Override
	protected void beforeUpdadd(EntityManager em, UserEntity actor, UserDTO dto) {
		if (dto.getAuthToken() == null) {
			dto.setAuthToken(new SingleLineString256Type(Random.randomKey(12)));
		}
		super.beforeUpdadd(em, actor, dto);
	}

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

		if (query.getAuthToken() != null) {
			whereClause = "where " + UserFetchQuery.COLUMN_AUTHTOKEN + " = '" + query.getAuthToken() + "'";
		} else if (query.getSearchString() != null && !query.getSearchString().isEmpty()) {
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

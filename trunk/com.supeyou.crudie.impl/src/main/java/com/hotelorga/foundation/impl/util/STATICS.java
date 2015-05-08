package com.hotelorga.foundation.impl.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.hotelorga.foundation.iface.datatype.CRUDException;
import com.hotelorga.foundation.iface.datatype.CRUDException.CODE;
import com.hotelorga.foundation.iface.datatype.enums.ROLE;
import com.hotelorga.foundation.impl.entity.UserEntity;

public class STATICS {

	public final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("appData");

	public static final String A_DBID = "A_DBID";
	public static final String B_DBID = "B_DBID";

	public static EntityManager getEntityManager() {
		EntityManager em = emf.createEntityManager(); // Retrieve an application managed entity manager
		return em;
	}

	public static void checkActorNotNull(UserEntity actor) throws CRUDException {
		if (actor == null) {
			throw new CRUDException(CODE.AUTHORIZATION_EXCEPTION, "Actor required");
		}

	}

	public static void checkActorIsAdmin(UserEntity actor) throws CRUDException {
		if (!actor.getRoles().contains(ROLE.ADMIN)) {
			throw new CRUDException(CODE.AUTHORIZATION_EXCEPTION, "Actor needs admin role");
		}

	}

}

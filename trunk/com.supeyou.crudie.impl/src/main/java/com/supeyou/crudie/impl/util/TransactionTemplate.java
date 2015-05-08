package com.supeyou.crudie.impl.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import com.supeyou.crudie.iface.datatype.CRUDException;
import com.supeyou.crudie.iface.dto.UserDTO;
import com.supeyou.crudie.impl.entity.UserEntity;

public abstract class TransactionTemplate<T> {

	private static transient Logger log = Logger.getLogger(TransactionTemplate.class.getName());

	public final EntityManager em;

	public final UserEntity actor;

	public TransactionTemplate(UserDTO actorDTO, EntityManager entityManager) {

		this.em = entityManager;

		if (actorDTO != null && actorDTO.getId() != null) {
			this.actor = em.find(UserEntity.class, actorDTO.getId().value());
		} else {
			actor = null;
		}

	}

	public abstract void checkPermissions(UserEntity actor) throws CRUDException;

	public T execute() throws CRUDException {

		T result = null;

		try {

			startTransaction();
			checkPermissions(actor);
			result = transactionBody();
			commitTransaction();

		} catch (Exception e) {

			log.log(Level.WARNING, "Exception during transaction. Rollback will be started...", e);

			// if any Exception is thrown the transaction is rolled back:
			rollbackTransaction();

			log.log(Level.WARNING, "rollback done");

			if (e instanceof CRUDException) {
				throw (CRUDException) e;
			}

		} finally {

			closeTransaction();

		}

		return result;

	}

	private void startTransaction() {

		em.getTransaction().begin();

	}

	private void commitTransaction() {

		if (em.getTransaction().isActive())
			try {

				em.getTransaction().commit();

			} catch (Exception e) {

				e.printStackTrace();

			}

	}

	private void rollbackTransaction() {

		em.getTransaction().rollback();

	}

	private void closeTransaction() {

		em.close();

	}

	protected abstract T transactionBody() throws Exception;

}

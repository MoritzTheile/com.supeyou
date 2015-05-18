package com.supeyou.crudie.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.supeyou.crudie.iface.CRUDService;
import com.supeyou.crudie.iface.datatype.CRUDException;
import com.supeyou.crudie.iface.datatype.CRUDException.CODE;
import com.supeyou.crudie.iface.datatype.FetchQuery;
import com.supeyou.crudie.iface.datatype.FetchQuery.SORTDIRECTION;
import com.supeyou.crudie.iface.datatype.Page;
import com.supeyou.crudie.iface.datatype.enums.MIMETYPE;
import com.supeyou.crudie.iface.datatype.types.AbstrType;
import com.supeyou.crudie.iface.datatype.types.FileIDType;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.iface.dto.AbstrDTO;
import com.supeyou.crudie.iface.dto.DTOFetchList;
import com.supeyou.crudie.iface.dto.FileDTO;
import com.supeyou.crudie.iface.dto.UserDTO;
import com.supeyou.crudie.impl.dtohelper.AbstrDTOHelper;
import com.supeyou.crudie.impl.dtohelper.ReflectionExporter;
import com.supeyou.crudie.impl.dtohelper.ReflectionImporter;
import com.supeyou.crudie.impl.dtohelper.ReflectionImporter.Callback;
import com.supeyou.crudie.impl.entity.AbstrEntity;
import com.supeyou.crudie.impl.entity.UserEntity;
import com.supeyou.crudie.impl.util.STATICS;
import com.supeyou.crudie.impl.util.TransactionTemplate;

public class AbstrCRUDServiceImpl<D extends AbstrDTO<?>, E extends AbstrEntity<?>, F extends FetchQuery> implements CRUDService<D, F> {

	private Logger log = Logger.getLogger(this.getClass().getName());

	public final Class<D> dtoClass;
	public final Class<E> entityClass;

	public final AbstrDTOHelper<E, D> helper;

	public AbstrCRUDServiceImpl(Class<D> dtoClass, Class<E> entityClass) {

		this.dtoClass = dtoClass;
		this.entityClass = entityClass;

		helper = new AbstrDTOHelper<>(dtoClass, entityClass);

	}

	public DTOFetchList<D> fetchList(UserDTO actorDTO, final Page page, final F dtoQuery) throws CRUDException {
		return new TransactionTemplate<DTOFetchList<D>>(actorDTO, STATICS.getEntityManager()) {

			public void checkPermissions(UserEntity actor) throws CRUDException {

				// STATICS.checkActorNotNull(actor);
				// STATICS.checkActorIsAdmin(actor);

			}

			@SuppressWarnings("unchecked")
			protected DTOFetchList<D> transactionBody() throws Exception {
				DTOFetchList<D> fetchList = new DTOFetchList<D>();

				{// count all

					Query query = getCountQuery(em, actor, dtoQuery);

					Long count = (Long) query.getSingleResult();

					fetchList.setTotalSize(count.intValue());

				}

				{// get page data

					Query query = getEntityQuery(em, actor, dtoQuery);

					if (page != null && page.getStartIndex() < (page.getStartIndex() + page.getPageSize())) {

						if (page.getStartIndex() < 0 && page.getPageSize() < 0) {
							throw new CRUDException(CODE.INVALID_PAGESIZE, "startIndex=" + page.getStartIndex() + "pageSize=" + page.getPageSize());

						}

						query.setFirstResult(page.getStartIndex());
						query.setMaxResults(page.getPageSize());

					}

					for (E entity : (List<E>) query.getResultList()) {

						D dto = helper.entity2DTO(entity);
						postprocessEntity2DTO(em, entity, dto);
						fetchList.add(dto);

					}

				}

				return fetchList;

			}
		}.execute();
	}

	protected void postprocessEntity2DTO(EntityManager em, E entity, D dto) throws Exception {
		// made for overriding

	}

	public D get(UserDTO actorDTO, final AbstrType<Long> dtoId) throws CRUDException {

		return new TransactionTemplate<D>(actorDTO, STATICS.getEntityManager()) {

			public void checkPermissions(UserEntity actor) throws CRUDException {

				// STATICS.checkActorNotNull(actor);
				// STATICS.checkActorIsAdmin(actor);

			}

			protected D transactionBody() throws Exception {
				E entity = em.find(entityClass, dtoId.value());
				D resultDTO = helper.entity2DTO(entity);
				postprocessEntity2DTO(em, entity, resultDTO);
				return resultDTO;
			}
		}.execute();

	}

	public D updadd(UserDTO actorDTO, final D dto) throws CRUDException {
		return new TransactionTemplate<D>(actorDTO, STATICS.getEntityManager()) {

			public void checkPermissions(UserEntity actor) throws CRUDException {

				// STATICS.checkActorNotNull(actor);
				// STATICS.checkActorIsAdmin(actor);

			}

			protected D transactionBody() throws Exception {

				beforeUpdadd(em, actor, dto);

				E entity = null;

				boolean toBePersisted = false;

				if (dto.getId() == null) {

					entity = entityClass.newInstance();

					toBePersisted = true;

				} else {

					entity = em.find(entityClass, dto.getId().value());

					if (entity == null) {

						entity = entityClass.newInstance();

						toBePersisted = true;
					}

				}

				helper.updateEntityFromDTO(dto, entity);

				postprocessDTO2Entity(em, dto, entity);

				if (toBePersisted) {

					if (dto.getId() != null) {

						entity.setId(dto.getId().value());

					} else {

						{// my simple id generator
							Long id = System.currentTimeMillis();
							while (em.find(entityClass, id) != null) {
								id = System.currentTimeMillis();
							}
							entity.setId(id);
						}

					}

					em.persist(entity);

				}

				log.log(Level.FINER, "dto.getId()" + dto.getId());
				log.log(Level.FINER, "entity.getId()" + entity.getId());

				log.log(Level.FINE, "updadding dto id=" + entity.getId());

				D resultDTO = helper.entity2DTO(entity);
				postprocessEntity2DTO(em, entity, resultDTO);
				return resultDTO;

			}

		}.execute();
	}

	protected void beforeUpdadd(EntityManager em, UserEntity actor, D dto) {

		// made for overriding

	}

	protected void postprocessDTO2Entity(EntityManager em, D dto, E entity) {

		// made for overriding

	}

	public void delete(UserDTO actorDTO, final AbstrType<Long> dtoId) throws CRUDException {
		new TransactionTemplate<Void>(actorDTO, STATICS.getEntityManager()) {

			public void checkPermissions(UserEntity actor) throws CRUDException {

				STATICS.checkActorNotNull(actor);
				// STATICS.checkActorIsAdmin(actor);

			}

			protected Void transactionBody() throws Exception {

				E entity = em.find(entityClass, dtoId.value());
				em.remove(entity);
				return null;

			}
		}.execute();

	}

	public FileIDType exportData(UserDTO actorDTO, F query) throws CRUDException {

		FileDTO csvFile = createFileForBackupData(actorDTO);

		DTOFetchList<D> fetchList = fetchList(actorDTO, new Page(), query);

		byte[] data = ReflectionExporter.dtos2csv(fetchList).getBytes();

		FileCRUDServiceImpl.i().setData(actorDTO, csvFile.getId(), data);

		return csvFile.getId();

	}

	private FileDTO createFileForBackupData(UserDTO actorDTO) throws CRUDException {

		FileDTO csvFile = FileCRUDServiceImpl.i().updadd(actorDTO, new FileDTO());

		csvFile.setName(new SingleLineString256Type("backup_" + dtoClass.getCanonicalName() + ".csv"));
		csvFile.setMimetype(MIMETYPE.TEXT_COMMA_SEPERATED_VALUES);

		return FileCRUDServiceImpl.i().updadd(actorDTO, csvFile);

	}

	public void importData(final UserDTO actorDTO, FileIDType fileId) throws CRUDException {

		final ByteArrayInputStream inputStream = new ByteArrayInputStream(FileCRUDServiceImpl.i().getData(actorDTO, fileId));

		ReflectionImporter.getDTOsFromCSVStream(inputStream, Charset.forName("UTF-8"), new Callback<D>() {

			@Override
			public void filledDTO(D dto) {
				try {
					updadd(actorDTO, dto);
				} catch (CRUDException e) {
					log.log(Level.WARNING, "Exception during import data", e);
				}

			}

			@Override
			public void finished() {
				try {
					inputStream.close();
				} catch (IOException e) {
					log.log(Level.WARNING, "Exception during import data", e);
				}

			}

			@Override
			public D newDTO() {
				try {
					return dtoClass.newInstance();
				} catch (Exception e) {
					log.log(Level.WARNING, "Exception during import data", e);
				}
				return null;
			}
		});

	}

	protected String getFromClause(EntityManager em, UserEntity actor, F query, Class<?> entityClass) {
		return "from " + entityClass.getSimpleName() + " as entity";
	}

	protected String getWhereClause(EntityManager em, UserEntity actor, F query) {
		return "";
	}

	protected String getOrderByClause(EntityManager em, UserEntity actor, F query) {

		String orderByClause = "";

		if (query.getOrderByColumn() != null && !query.getOrderByColumn().isEmpty()) {

			orderByClause += " order by " + query.getOrderByColumn() + " ";

		} else {

			orderByClause += " order by CreationTimestamp ";

		}

		if (SORTDIRECTION.ASC.equals(query.getSortDirection())) {
			orderByClause += "asc ";
		} else {
			orderByClause += "desc ";
		}

		return orderByClause;
	}

	protected Query getEntityQuery(EntityManager em, UserEntity actor, F query) throws CRUDException {
		String queryString = ""
				+ "select entity "
				+ " " + getFromClause(em, actor, query, entityClass)
				+ " " + getWhereClause(em, actor, query)
				+ " " + getOrderByClause(em, actor, query);

		return em.createQuery(queryString, entityClass);
	}

	protected Query getCountQuery(EntityManager em, UserEntity actor, F query) throws CRUDException {
		String queryString = ""
				+ "select count(*)"
				+ " " + getFromClause(em, actor, query, entityClass)
				+ " " + getWhereClause(em, actor, query);

		return em.createQuery(queryString);
	}

}

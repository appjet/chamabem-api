package com.chamabem.core.persistence;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;

import com.chamabem.core.exception.RecordAlreadyExistsException;

public abstract class CBPersistenceService {

	@Inject	
	protected Logger log;

    @PersistenceContext
    protected EntityManager em;

	public <E extends CBEntity> E findByPrimaryKey(Class<E> entityClass, Serializable primaryKey) {
		return em.find(entityClass, primaryKey);
	}

	@SuppressWarnings("unchecked")
	public <E extends CBEntity> List<E> findByClass(Class<E> entityClass) {
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(entityClass);
		return criteria.list();
	}

	protected void persist(CBEntity entity) {
		try {
			if (em.contains(entity)) {
				throw new RecordAlreadyExistsException();
			}

			em.persist(entity);
			em.flush();

		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	protected void update(CBEntity... entity) {
		try {

			for (CBEntity defaultEntity : entity) {
				em.merge(defaultEntity);
			}

			em.flush();

		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	protected void remove(CBEntity... list) {
		try {

			for (CBEntity entity : list) {
				em.remove(em.contains(entity) ? entity : em.getReference(entity.getClass(), entity.getId()));
			}
			em.flush();

		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
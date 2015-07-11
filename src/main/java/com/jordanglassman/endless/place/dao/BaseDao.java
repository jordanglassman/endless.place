package com.jordanglassman.endless.place.dao;

import com.jordanglassman.endless.place.entity.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
public class BaseDao<T extends Document> {
	private static final Logger LOG = LoggerFactory.getLogger(BaseDao.class);

	@PersistenceContext private EntityManager entityManager;

	private static Class type = Object.class;

	public BaseDao() {
		LOG.info("new BaseDao()");
	}

	public BaseDao(Class<T> t) {
		LOG.info("new BaseDao({})", t);
		type = t;
	}

	public Class<T> getType() {
		return type;
	}

	public void setType(Class clazz) {
		type = clazz;
	}

	public List<T> all() {
		final CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		final CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(type);
		final Root<T> root = criteriaQuery.from(type);
		criteriaQuery.select(root);
		final TypedQuery<T> typedQuery = this.entityManager.createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}

	public T save(final T t) {
		LOG.info("save({}) - t.getClass() = {}", t, t.getClass());
		this.entityManager.persist(t);
		return t;
	}

	public T update(final T t) {
		this.entityManager.merge(t);
		return t;
	}

	public void delete(final T t) {
		this.entityManager.remove(t);
	}

	public T get(final Object key) {
		return this.entityManager.find(this.getType(), key);
	}
}

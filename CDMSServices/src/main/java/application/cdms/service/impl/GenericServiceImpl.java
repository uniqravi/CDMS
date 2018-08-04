package application.cdms.service.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

import application.cdms.hibernate.utility.HibernateUtils;
import application.cdms.service.GenericSerivce;

public abstract class GenericServiceImpl implements GenericSerivce{

	private Session getSession(){
		return HibernateUtils.getCustomeTrasationManager().initTx();
	}
	
	@Override
	public <T> void saveEntity(T entity) {
		getSession().save(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T mergeEntity(T entity) {
		return (T) getSession().merge(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T findById(Class<T> type, Serializable id) {
		return (T) getSession().get(type, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findAllEntity(Class<T> type) {
		return getSession().createCriteria(type).list();
	}
}

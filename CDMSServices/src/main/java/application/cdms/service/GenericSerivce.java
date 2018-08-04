package application.cdms.service;

import java.io.Serializable;
import java.util.List;

public interface GenericSerivce {
	public <T> void saveEntity(final T entity);
	public <T> T mergeEntity(final T entity);
	public <T> T findById(final Class<T> type,final Serializable id);
	public <T> List<T> findAllEntity(final Class<T> type);
}

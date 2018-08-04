package application.cdms.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import application.cdms.dao.CDMSDataProviderDao;
import application.cdms.hibernate.utility.HibernateUtils;

public final class CDMSDataProviderDaoImpl implements CDMSDataProviderDao {
	
	private transient Logger logger  = Logger.getLogger(CDMSDataProviderDaoImpl.class);
	
	private static volatile CDMSDataProviderDaoImpl instance; 
	
	private CDMSDataProviderDaoImpl(){
		
	}

	public static CDMSDataProviderDao getInstance() {
		if(instance==null){
			synchronized (CDMSDataProviderDaoImpl.class) {
				if(instance==null){
					instance=new CDMSDataProviderDaoImpl();
				}
			}
		}
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getCDMSDataList(String logKey,String queryId, Object[] values) {
		logger.info(logKey+":: getCDMSDataList :: begin");
		Query query = HibernateUtils.getCustomeTrasationManager().initTx().createSQLQuery(queryId);
		if(values!=null){
			for(int i=0;i<values.length;i++){
				query.setParameter(i,values[i]);
			}
		}
		//query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		//List<Map<String,Object>> aliasToValueMapList=query.list();
		List<Object[]> dataList=query.list();
		HibernateUtils.commitCloseCustomeTransationManager();
		logger.info(logKey+":: getCDMSDataList:: list retrieved ### "+dataList!=null ? dataList.size():"");
		logger.info(logKey+":: getCDMSDataList :: End");
		return dataList;
	}
}
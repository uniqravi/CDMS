package application.cdms.dao;

import java.util.List;

public interface CDMSDataProviderDao {

	List<Object[]> getCDMSDataList(String logKey,String queryId,int limit,Object[] values) throws Exception;
}

package application.cdms.dao;

import java.util.List;

public interface CDMSDataProviderDao {

	List<Object[]> getCDMSDataList(String logKey,String queryId,Object[] values) throws Exception;
}

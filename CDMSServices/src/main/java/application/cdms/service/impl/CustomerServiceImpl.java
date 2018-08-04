package application.cdms.service.impl;

import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import application.cdms.db.entities.CstmrAcntsDtl;
import application.cdms.db.entities.CstmrDtl;
import application.cdms.hibernate.utility.HibernateUtils;
import application.cdms.models.CstmrLedger;
import application.cdms.models.CustomerDtl;
import application.cdms.service.CustomerService;
import application.cdms.transformer.BeanTransformer;
import application.cdms.transformer.Initialization;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CustomerServiceImpl extends GenericServiceImpl implements CustomerService {

	private static Logger logger = Logger.getLogger(CustomerServiceImpl.class);
	//private ProductDao productDao = ProductDaoImpl.getInstance();
	
	//private CustomerDao customerDao = CustomerDaoImpl.getInstance();
	
	private CustomerServiceImpl() {
		super();
	}

	public static CustomerService getInstance() {
		return CustomerServiceSingletoneHolder.getInstance();
	}

	private static class CustomerServiceSingletoneHolder {
		private static CustomerService customerService = new CustomerServiceImpl();

		public static CustomerService getInstance() {
			return customerService;
		}
	}

	@Override
	public CustomerDtl addNewCstmr(CustomerDtl cstmrDtl) {
		logger.info("CustomerServiceImpl :: addNewCstmr :: begin");
		try{
			CstmrDtl csDtlEnty = BeanTransformer.getCstmrEntity(cstmrDtl);
			csDtlEnty.setCstmrCreatedDt(Calendar.getInstance());
			logger.info("CustomerServiceImpl :: addNewCstmr :: db activity start");
			HibernateUtils.getCustomeTrasationManager().initTx();
			logger.info("CustomerServiceImpl :: addNewCstmr :: customer entry start");
			this.saveEntity(csDtlEnty);
			logger.info("CustomerServiceImpl :: addNewCstmr :: customer entry end");
			HibernateUtils.getCustomeTrasationManager().commitTx();;
			logger.info("CustomerServiceImpl :: addNewCstmr :: db activity end");
			logger.info("CustomerServiceImpl :: addNewCstmr :: Customer Id ### "+csDtlEnty.getCstmrId());
			//CustomerDtl cstmrBean=BeanTransformer.getCstmrBean(csDtlEnty);
			cstmrDtl.setCstmrId(csDtlEnty.getCstmrId());
		}
		catch(Exception e){
			logger.fatal("CustomerServiceImpl :: addNewCstmr :: exception ### "+e.getMessage());
			HibernateUtils.getCustomeTrasationManager().rollBackTx();
			throw e;
		}
		finally{
			HibernateUtils.CloseCustomeTransationManager();
		}
		logger.info("CustomerServiceImpl :: addNewCstmr :: end");
		return cstmrDtl;
	}
	@Override
	public ObservableList<CustomerDtl> getAllCustomers() {
		logger.info("CustomerServiceImpl :: getAllCustomers :: begin");
		logger.info("CustomerServiceImpl :: getAllCustomers :: db activity start");
		HibernateUtils.getCustomeTrasationManager().initTx();
		List<CstmrDtl> CstmrDtlEntities = this.findAllEntity(CstmrDtl.class);
		logger.info("CustomerServiceImpl :: getAllCustomers :: db activity end");
		ObservableList<CustomerDtl> customerDtlLst = FXCollections.observableArrayList();
		for (CstmrDtl cstmr : CstmrDtlEntities) {
			customerDtlLst.add(BeanTransformer.getCstmrBean(cstmr));
		}
		logger.info("CustomerServiceImpl :: getAllCustomers :: customer list count ### "+customerDtlLst.size());
		HibernateUtils.commitCloseCustomeTransationManager();
		logger.info("CustomerServiceImpl :: getAllCustomers :: end");
		return customerDtlLst;
	}

	@Override
	public CstmrLedger createNewLedger(CstmrLedger cstmrLedger) {
		logger.info("CustomerServiceImpl :: createNewLedger :: begin");
		try{
			CstmrAcntsDtl cstmrAcnt = BeanTransformer.getCsmtrAcntEntity(cstmrLedger,Initialization.EAGER);
			logger.info("CustomerServiceImpl :: createNewLedger :: db activity start");
			HibernateUtils.getCustomeTrasationManager().initTx();
			this.saveEntity(cstmrAcnt);
			//CstmrAcntsDtl acntdtl=this.mergeEntity(cstmrAcnt);
			HibernateUtils.getCustomeTrasationManager().commitTx();;
			logger.info("CustomerServiceImpl :: createNewLedger :: db activity end");
			logger.info("CustomerServiceImpl :: createNewLedger :: Customer account number ### "+cstmrAcnt.getCstmrAcntNo());
			cstmrLedger.setCstmrAcntNo(cstmrAcnt.getCstmrAcntNo());
		}
		catch(Exception e){
			logger.fatal("CustomerServiceImpl :: createNewLedger :: exception ### "+e.getMessage());
			HibernateUtils.getCustomeTrasationManager().rollBackTx();
			throw e;
		}finally{
			HibernateUtils.CloseCustomeTransationManager();
		}
		logger.info("CustomerServiceImpl :: createNewLedger :: end");
		return cstmrLedger;
	} 
}

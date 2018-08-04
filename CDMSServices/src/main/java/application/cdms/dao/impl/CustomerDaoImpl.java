package application.cdms.dao.impl;

import application.cdms.dao.CustomerDao;

public class CustomerDaoImpl implements CustomerDao{
	private CustomerDaoImpl(){
		super();
	}
	
	public static CustomerDao getInstance(){
		return CustomerDaoInstanceHolder.custmrDaoInstance;
	}
	
	private static class CustomerDaoInstanceHolder{
		public static CustomerDao custmrDaoInstance=new CustomerDaoImpl();
	}
}

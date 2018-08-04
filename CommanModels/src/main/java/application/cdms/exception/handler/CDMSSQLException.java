package application.cdms.exception.handler;

public class CDMSSQLException extends CDMSGeneralException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CDMSSQLException(String msg) {
		super(msg);
		//HibernateUtils.getCustomeTrasationManager().rollBackTx();
	}

}

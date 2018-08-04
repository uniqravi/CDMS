package application.cdms.exception.handler;

public class CDMSGeneralException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private String msg;
	
	public CDMSGeneralException(String msg){
		this.msg=msg;
	}
	
	@Override
	public String getMessage() {
		return msg;
	}
}

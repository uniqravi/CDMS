package application.cdms.enums;

public enum InvoiceType {

	RETURNABLE_MATERIAL("RM"),SALE("S"),RETURN_PURCHASE_SALE("RS"),RETURN_BREAKAGE_SALE("RBS");
	
	private String type;
	
	private InvoiceType(final String type){
		this.type=type;
	}
	
	public String getType() {
        return type;
    }
}

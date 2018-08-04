package application.cdms.models;

public class FirmSeller {
	private String firmCd;
	private String firmNm;
	private String firmGstnNumber;
	public String getFirmCd() {
		return firmCd;
	}
	public void setFirmCd(String firmCd) {
		this.firmCd = firmCd;
	}
	public String getFirmNm() {
		return firmNm;
	}
	public void setFirmNm(String firmNm) {
		this.firmNm = firmNm;
	}
	public String getFirmGstnNumber() {
		return firmGstnNumber;
	}
	public void setFirmGstnNumber(String firmGstnNumber) {
		this.firmGstnNumber = firmGstnNumber;
	}
	@Override
	public String toString() {
		return firmNm+"-"+firmGstnNumber;
	}
}

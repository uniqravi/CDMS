package application.cdms.db.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="firm_or_seller_dtl",schema="cdms")
public class FirmOrSellerDtl {

	@Id
	@Column(name="firm_cd")
	private String firmCd;
	@Column(name="firm_name")
	private String firmNm;
	@Column(name="firm_gstn_number")
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
}

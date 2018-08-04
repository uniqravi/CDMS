package application.cdms.db.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the beverage_product_group_dtl database table.
 * 
 */
@Entity
@Table(name="beverage_product_group_dtl",schema="cdms")
//@NamedQuery(name="BeverageProductGroupDtl.findAll", query="SELECT b FROM BeverageProductGroupDtl b")
public class BeverageProductGroupDtl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="group_name")
	private String groupName;

	/*//@OneToOne
	@re(name="filing_qty_cd1")
	private FillingQtyCategory filingQtyCd1;

	@Column(name="filling_qty_cd2")
	private FillingQtyCategory fillingQtyCd2;

	@Column(name="group_comments")
	private String groupComments;

	@Column(name="packing_name_cd")
	private PackingNameCategory packingNameCd;

	@Column(name="packing_qty_cd")
	private PackingQtyCategory packingQtyCd;*/
	@Column(name="filing_qty_cd1")
	private String fillingQtyCd1;
	@Column(name="filling_qty_cd2")
	private String fillingQtyCd2;
	@Column(name="group_comments")
	private String groupComments;
	@Column(name="packing_name_cd")
	private String packingNameCd;
	@Column(name="packing_qty_cd")
	private String packingQtyCd;
	

	public BeverageProductGroupDtl() {
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupComments() {
		return this.groupComments;
	}

	public void setGroupComments(String groupComments) {
		this.groupComments = groupComments;
	}
/*
	public FillingQtyCategory getFilingQtyCd1() {
		return filingQtyCd1;
	}

	public void setFilingQtyCd1(FillingQtyCategory filingQtyCd1) {
		this.filingQtyCd1 = filingQtyCd1;
	}

	public FillingQtyCategory getFillingQtyCd2() {
		return fillingQtyCd2;
	}

	public void setFillingQtyCd2(FillingQtyCategory fillingQtyCd2) {
		this.fillingQtyCd2 = fillingQtyCd2;
	}

	public PackingNameCategory getPackingNameCd() {
		return packingNameCd;
	}

	public void setPackingNameCd(PackingNameCategory packingNameCd) {
		this.packingNameCd = packingNameCd;
	}

	public PackingQtyCategory getPackingQtyCd() {
		return packingQtyCd;
	}

	public void setPackingQtyCd(PackingQtyCategory packingQtyCd) {
		this.packingQtyCd = packingQtyCd;
	}*/

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getFillingQtyCd1() {
		return fillingQtyCd1;
	}

	public void setFillingQtyCd1(String fillingQtyCd1) {
		this.fillingQtyCd1 = fillingQtyCd1;
	}

	public String getFillingQtyCd2() {
		return fillingQtyCd2;
	}

	public void setFillingQtyCd2(String fillingQtyCd2) {
		this.fillingQtyCd2 = fillingQtyCd2;
	}

	public String getPackingNameCd() {
		return packingNameCd;
	}

	public void setPackingNameCd(String packingNameCd) {
		this.packingNameCd = packingNameCd;
	}

	public String getPackingQtyCd() {
		return packingQtyCd;
	}

	public void setPackingQtyCd(String packingQtyCd) {
		this.packingQtyCd = packingQtyCd;
	}
	
}
package application.cdms.db.entities;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the prdct_breakage_dtl database table.
 * 
 */
@Entity
@Table(name="prdct_breakage_dtl",schema="cdms")
//@NamedQuery(name="PrdctBreakageDtl.findAll", query="SELECT p FROM PrdctBreakageDtl p")
public class PrdctBreakageDtl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@TableGenerator(name="breakageGnrtr",table="CDMS.SERIAL_NO_GENERATOR",pkColumnName="SEQ_NAME",valueColumnName="SEQ_VALUE",pkColumnValue="BREAKAGE_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.TABLE,generator="breakageGnrtr")
	@Column(name="breakage_seq")
	private long breakageSeq;

	@Column(name="breakage_dt")
	@Temporal(TemporalType.TIMESTAMP)
	private  Calendar breakageDt;

	@Column(name="breakage_source")
	private String breakageSource;

	@Column(name="burst_bs")
	private long burstBs;

	@Column(name="open_mouth_bs")
	private long openMouthBs;

	@Column(name="other_breakage_bs")
	private long otherBreakageBs;

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="product_cd")
	private BeverageProductCategory product;

	@Column(name="seal_pack_shortage_bs")
	private long sealPackShortageBs;

	@Column(name="shortage_bs")
	private long shortageBs;
	
	@Column(name="leakage_bs")
	private long leakageBs;

	/*@Column(name="source_id")
	private String sourceId;*/

	public PrdctBreakageDtl() {
	}

	public long getBreakageSeq() {
		return breakageSeq;
	}

	public void setBreakageSeq(long breakageSeq) {
		this.breakageSeq = breakageSeq;
	}

	public Calendar getBreakageDt() {
		return breakageDt;
	}

	public void setBreakageDt(Calendar breakageDt) {
		this.breakageDt = breakageDt;
	}

	public String getBreakageSource() {
		return breakageSource;
	}

	public void setBreakageSource(String breakageSource) {
		this.breakageSource = breakageSource;
	}

	public long getBurstBs() {
		return burstBs;
	}

	public void setBurstBs(long burstBs) {
		this.burstBs = burstBs;
	}

	public long getOpenMouthBs() {
		return openMouthBs;
	}

	public void setOpenMouthBs(long openMouthBs) {
		this.openMouthBs = openMouthBs;
	}

	public long getOtherBreakageBs() {
		return otherBreakageBs;
	}

	public void setOtherBreakageBs(long otherBreakageBs) {
		this.otherBreakageBs = otherBreakageBs;
	}
	
	public BeverageProductCategory getProduct() {
		return product;
	}

	public void setProduct(BeverageProductCategory product) {
		this.product = product;
	}

	public long getSealPackShortageBs() {
		return sealPackShortageBs;
	}

	public void setSealPackShortageBs(long sealPackShortageBs) {
		this.sealPackShortageBs = sealPackShortageBs;
	}

	public long getShortageBs() {
		return shortageBs;
	}

	public void setShortageBs(long shortageBs) {
		this.shortageBs = shortageBs;
	}
/*
	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
*/
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public long getLeakageBs() {
		return leakageBs;
	}

	public void setLeakageBs(long leakageBs) {
		this.leakageBs = leakageBs;
	}
	
}
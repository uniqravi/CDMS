package application.cdms.db.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


/**
 * The persistent class for the supply_records database table.
 * 
 */
@Entity
@Table(name="supply_records",schema="cdms")
//@NamedQuery(name="SupplyRecord.findAll", query="SELECT s FROM SupplyRecord s")
public class SupplyRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(
			name="stringSeq",
			strategy="application.cdms.hibernate.utility.StringSequenceIdentifier",
			parameters={
					@Parameter(name="seqColumnNm",value="SUPPLY_SEQ")
			}
	)
	@GeneratedValue(generator="stringSeq")
	@Column(name="supply_seq_no")
	private String supplySeqNo;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="vehicle_cd")
	private SupplyVehicleDtl vehicleCd;

	@Column(name="total_glass_bs_sent")
	private long totalGlassBsSent;
	
	@Column(name="expected_return_bs_glass", precision=10)
	private Long expectedReturnBsGlass;
	
	@Column(name="actual_return_bs_glass", precision=10)
	private Long actualReturnBsGlass;
	
	@Column(name="total_cell_sent", nullable=false, precision=10)
	private long totalCellSent;
	
	@Column(name="expected_return_cell", precision=10)
	private Long expectedReturnCell;
	
	@Column(name="actual_cell_return")
	private Long actualCellReturn;
	
	@Column(name="lost_glass_bs_qty", precision=10)
	private Long lostGlassBsQty;
	
	@Column(name="lost_cell_qty", precision=10)
	private Long lostCellQty;

	@Column(name="cstmr_due_glass_bs_qty", precision=10)
	private Long cstmrDueGlassBsQty;
	
	@Column(name="cstmr_due_cell_qty", precision=10)
	private Long cstmrDueCellQty;

	@Column(name="sys_gen_total_supplysale_amnt", precision=10, scale=2)
	private Double sysGenTotalSupplysaleAmnt;
	
	@Column(name="sys_gnrted_total_supplysale_discnt", precision=10, scale=2)
	private Double sysGnrtedTotalSupplysaleDiscnt;
	
	@Column(name="total_supplysale_adjustment_discnt", precision=10, scale=2)
	private Double totalSupplysaleAdjustmentDiscnt;
	
	@Column(name="total_supplysale_adjustment_amnt", precision=10, scale=2)
	private Double totalSupplysaleAdjustmentAmnt;
	
	@Column(name="total_net_actual_supplysale_amnt", precision=10, scale=2)
	private Double totalNetActualSupplysaleAmnt;
	
	@Column(name="total_cstmr_supplysale_due_amnt", precision=10, scale=2)
	private Double totalCstmrSupplysaleDueAmnt;
	
	@Column(name="total_supplysale_amnt_recieved", precision=10, scale=2)
	private Double totalSupplysaleAmntRecieved;
	
	@Column(name="mismatch_amnt", precision=10, scale=2)
	private Double mismatchAmnt;

	@OneToOne
	@JoinColumn(name="route_covered")
	private RouteMapDtl routeCovered;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="supply_vehicle_back_time")
	private Calendar supplyVehicleBackTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="supply_vehicle_exit_time")
	private Calendar supplyVehicleExitTime;

	@Column(name="supply_worker_name")
	private String supplyWorkerName;
	
	@OneToMany(mappedBy="supplyRecordMaster",cascade={CascadeType.ALL})
	private List<SupplyPrdctDtl> supplyPrdctDtlLst;

	public SupplyRecord() {
	}

	public String getSupplySeqNo() {
		return supplySeqNo;
	}

	public void setSupplySeqNo(String supplySeqNo) {
		this.supplySeqNo = supplySeqNo;
	}

	public SupplyVehicleDtl getVehicleCd() {
		return vehicleCd;
	}

	public void setVehicleCd(SupplyVehicleDtl vehicleCd) {
		this.vehicleCd = vehicleCd;
	}

	public long getTotalGlassBsSent() {
		return totalGlassBsSent;
	}

	public void setTotalGlassBsSent(long totalGlassBsSent) {
		this.totalGlassBsSent = totalGlassBsSent;
	}

	public Long getExpectedReturnBsGlass() {
		return expectedReturnBsGlass;
	}

	public void setExpectedReturnBsGlass(Long expectedReturnBsGlass) {
		this.expectedReturnBsGlass = expectedReturnBsGlass;
	}

	public Long getActualReturnBsGlass() {
		return actualReturnBsGlass;
	}

	public void setActualReturnBsGlass(Long actualReturnBsGlass) {
		this.actualReturnBsGlass = actualReturnBsGlass;
	}

	public long getTotalCellSent() {
		return totalCellSent;
	}

	public void setTotalCellSent(long totalCellSent) {
		this.totalCellSent = totalCellSent;
	}

	public Long getExpectedReturnCell() {
		return expectedReturnCell;
	}

	public void setExpectedReturnCell(Long expectedReturnCell) {
		this.expectedReturnCell = expectedReturnCell;
	}

	public Long getActualCellReturn() {
		return actualCellReturn;
	}

	public void setActualCellReturn(Long actualCellReturn) {
		this.actualCellReturn = actualCellReturn;
	}

	public Long getLostGlassBsQty() {
		return lostGlassBsQty;
	}

	public void setLostGlassBsQty(Long lostGlassBsQty) {
		this.lostGlassBsQty = lostGlassBsQty;
	}

	public Long getLostCellQty() {
		return lostCellQty;
	}

	public void setLostCellQty(Long lostCellQty) {
		this.lostCellQty = lostCellQty;
	}

	public Long getCstmrDueGlassBsQty() {
		return cstmrDueGlassBsQty;
	}

	public void setCstmrDueGlassBsQty(Long cstmrDueGlassBsQty) {
		this.cstmrDueGlassBsQty = cstmrDueGlassBsQty;
	}

	public Long getCstmrDueCellQty() {
		return cstmrDueCellQty;
	}

	public void setCstmrDueCellQty(Long cstmrDueCellQty) {
		this.cstmrDueCellQty = cstmrDueCellQty;
	}

	public Double getSysGenTotalSupplysaleAmnt() {
		return sysGenTotalSupplysaleAmnt;
	}

	public void setSysGenTotalSupplysaleAmnt(Double sysGenTotalSupplysaleAmnt) {
		this.sysGenTotalSupplysaleAmnt = sysGenTotalSupplysaleAmnt;
	}

	public Double getSysGnrtedTotalSupplysaleDiscnt() {
		return sysGnrtedTotalSupplysaleDiscnt;
	}

	public void setSysGnrtedTotalSupplysaleDiscnt(Double sysGnrtedTotalSupplysaleDiscnt) {
		this.sysGnrtedTotalSupplysaleDiscnt = sysGnrtedTotalSupplysaleDiscnt;
	}

	public Double getTotalSupplysaleAdjustmentDiscnt() {
		return totalSupplysaleAdjustmentDiscnt;
	}

	public void setTotalSupplysaleAdjustmentDiscnt(Double totalSupplysaleAdjustmentDiscnt) {
		this.totalSupplysaleAdjustmentDiscnt = totalSupplysaleAdjustmentDiscnt;
	}

	public Double getTotalSupplysaleAdjustmentAmnt() {
		return totalSupplysaleAdjustmentAmnt;
	}

	public void setTotalSupplysaleAdjustmentAmnt(Double totalSupplysaleAdjustmentAmnt) {
		this.totalSupplysaleAdjustmentAmnt = totalSupplysaleAdjustmentAmnt;
	}

	public Double getTotalNetActualSupplysaleAmnt() {
		return totalNetActualSupplysaleAmnt;
	}

	public void setTotalNetActualSupplysaleAmnt(Double totalNetActualSupplysaleAmnt) {
		this.totalNetActualSupplysaleAmnt = totalNetActualSupplysaleAmnt;
	}

	public Double getTotalCstmrSupplysaleDueAmnt() {
		return totalCstmrSupplysaleDueAmnt;
	}

	public void setTotalCstmrSupplysaleDueAmnt(Double totalCstmrSupplysaleDueAmnt) {
		this.totalCstmrSupplysaleDueAmnt = totalCstmrSupplysaleDueAmnt;
	}

	public Double getTotalSupplysaleAmntRecieved() {
		return totalSupplysaleAmntRecieved;
	}

	public void setTotalSupplysaleAmntRecieved(Double totalSupplysaleAmntRecieved) {
		this.totalSupplysaleAmntRecieved = totalSupplysaleAmntRecieved;
	}

	public Double getMismatchAmnt() {
		return mismatchAmnt;
	}

	public void setMismatchAmnt(Double mismatchAmnt) {
		this.mismatchAmnt = mismatchAmnt;
	}

	public RouteMapDtl getRouteCovered() {
		return routeCovered;
	}

	public void setRouteCovered(RouteMapDtl routeCovered) {
		this.routeCovered = routeCovered;
	}

	public Calendar getSupplyVehicleBackTime() {
		return supplyVehicleBackTime;
	}

	public void setSupplyVehicleBackTime(Calendar supplyVehicleBackTime) {
		this.supplyVehicleBackTime = supplyVehicleBackTime;
	}

	public Calendar getSupplyVehicleExitTime() {
		return supplyVehicleExitTime;
	}

	public void setSupplyVehicleExitTime(Calendar supplyVehicleExitTime) {
		this.supplyVehicleExitTime = supplyVehicleExitTime;
	}

	public String getSupplyWorkerName() {
		return supplyWorkerName;
	}

	public void setSupplyWorkerName(String supplyWorkerName) {
		this.supplyWorkerName = supplyWorkerName;
	}

	public List<SupplyPrdctDtl> getSupplyPrdctDtlLst() {
		return supplyPrdctDtlLst;
	}

	public void setSupplyPrdctDtlLst(List<SupplyPrdctDtl> supplyPrdctDtlLst) {
		this.supplyPrdctDtlLst = supplyPrdctDtlLst;
	}

}
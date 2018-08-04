package application.cdms.models;

import java.util.List;

public class SupplyDtl {

	private String supplySeqNo;
	
	private Vehicle vehicle;

	private long totalGlassBsSent;
	
	private Long expectedReturnBsGlass;
	
	private Long actualReturnBsGlass;
	
	private long totalCellSent;
	
	private Long expectedReturnCell;
	
	private Long actualCellReturn;
	
	private Long lostGlassBsQty;
	
	private Long lostCellQty;

	private Long cstmrDueGlassBsQty;
	
	private Long cstmrDueCellQty;

	private Double sysGenTotalSupplysaleAmnt;
	
	private Double sysGnrtedTotalSupplysaleDiscnt;
	
	private Double totalSupplysaleAdjustmentDiscnt;
	
	private Double totalSupplysaleAdjustmentAmnt;
	
	private Double totalNetActualSupplysaleAmnt;
	
	private Double totalCstmrSupplysaleDueAmnt;
	
	private Double totalSupplysaleAmntRecieved;
	
	private Double mismatchAmnt;
	
	private RouteDtl routeCovered;

	private String supplyVehicleBackTime;

	private String supplyVehicleExitTime;

	private String supplyWorkerName;
	
	private List<SupplyPrdcts> supplyPrdctDtlLst;

	public String getSupplySeqNo() {
		return supplySeqNo;
	}

	public void setSupplySeqNo(String supplySeqNo) {
		this.supplySeqNo = supplySeqNo;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
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

	public RouteDtl getRouteCovered() {
		return routeCovered;
	}

	public void setRouteCovered(RouteDtl routeCovered) {
		this.routeCovered = routeCovered;
	}

	public String getSupplyVehicleBackTime() {
		return supplyVehicleBackTime;
	}

	public void setSupplyVehicleBackTime(String supplyVehicleBackTime) {
		this.supplyVehicleBackTime = supplyVehicleBackTime;
	}

	public String getSupplyVehicleExitTime() {
		return supplyVehicleExitTime;
	}

	public void setSupplyVehicleExitTime(String supplyVehicleExitTime) {
		this.supplyVehicleExitTime = supplyVehicleExitTime;
	}

	public String getSupplyWorkerName() {
		return supplyWorkerName;
	}

	public void setSupplyWorkerName(String supplyWorkerName) {
		this.supplyWorkerName = supplyWorkerName;
	}

	public List<SupplyPrdcts> getSupplyPrdctDtlLst() {
		return supplyPrdctDtlLst;
	}

	public void setSupplyPrdctDtlLst(List<SupplyPrdcts> supplyPrdctDtlLst) {
		this.supplyPrdctDtlLst = supplyPrdctDtlLst;
	}
}
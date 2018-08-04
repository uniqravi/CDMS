package application.cdms.service.impl;

import java.util.List;

import application.cdms.db.entities.CstmrLedgerCategory;
import application.cdms.db.entities.HsnTaxStructure;
import application.cdms.db.entities.RouteMapDtl;
import application.cdms.db.entities.SupplyVehicleDtl;
import application.cdms.db.entities.TerritoryAreaDtl;
import application.cdms.hibernate.utility.HibernateUtils;
import application.cdms.models.HsnTax;
import application.cdms.models.LedgerType;
import application.cdms.models.RouteDtl;
import application.cdms.models.Territory;
import application.cdms.models.Vehicle;
import application.cdms.service.MasterService;
import application.cdms.transformer.BeanTransformer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MasterServiceImpl extends GenericServiceImpl implements MasterService{

	private MasterServiceImpl() {
		super();
	}

	public static MasterService getInstance() {
		return MasterServiceSingletoneHolder.getInstance();
	}

	private static class MasterServiceSingletoneHolder {
		private static MasterService masterService = new MasterServiceImpl();

		public static MasterService getInstance() {
			return masterService;
		}
	}
	
	@Override
	public ObservableList<Territory> getAllTerritory() {
		HibernateUtils.getCustomeTrasationManager().initTx();
		List<TerritoryAreaDtl> territoryEntities = this.findAllEntity(TerritoryAreaDtl.class);
		ObservableList<Territory> territoryList = FXCollections.observableArrayList();
		for (TerritoryAreaDtl territory : territoryEntities) {
			territoryList.add(BeanTransformer.getTerritoryBean(territory));
		}
		HibernateUtils.commitCloseCustomeTransationManager();
		return territoryList;
	}
	@Override
	public ObservableList<LedgerType> getAllLedgerTypes() {
		HibernateUtils.getCustomeTrasationManager().initTx();
		List<CstmrLedgerCategory> ledgerTypeEntities = this.findAllEntity(CstmrLedgerCategory.class);
		ObservableList<LedgerType> ledgerTypeLst = FXCollections.observableArrayList();
		for (CstmrLedgerCategory ledgerCat : ledgerTypeEntities) {
			ledgerTypeLst.add(BeanTransformer.getLedgerTypeBean(ledgerCat));
		}
		HibernateUtils.commitCloseCustomeTransationManager();
		return ledgerTypeLst;
	}
	
	@Override
	public ObservableList<HsnTax> getAllHSn(){
		HibernateUtils.getCustomeTrasationManager().initTx();
		List<HsnTaxStructure> hsnEntities = this.findAllEntity(HsnTaxStructure.class);
		ObservableList<HsnTax> hsnLst = FXCollections.observableArrayList();
		for(HsnTaxStructure hsnTaxStru: hsnEntities){
			hsnLst.add(BeanTransformer.getHsnTaxBean(hsnTaxStru));
		}
		HibernateUtils.commitCloseCustomeTransationManager();
		return hsnLst;
	}
	@Override
	public ObservableList<RouteDtl> getAllRoute(){
		HibernateUtils.getCustomeTrasationManager().initTx();
		List<RouteMapDtl> rouMapDtl = this.findAllEntity(RouteMapDtl.class);
		ObservableList<RouteDtl> routeLst = FXCollections.observableArrayList();
		for(RouteMapDtl route : rouMapDtl){
			routeLst.add(BeanTransformer.getRouteBean(route));
		}
		HibernateUtils.commitCloseCustomeTransationManager();
		return routeLst;
	}
	
	@Override
	public ObservableList<Vehicle> getAllVehicle(){
		HibernateUtils.getCustomeTrasationManager().initTx();
		List<SupplyVehicleDtl> vehicleLst = this.findAllEntity(SupplyVehicleDtl.class);
		ObservableList<Vehicle> veBeanLst = FXCollections.observableArrayList();
		for(SupplyVehicleDtl ve : vehicleLst){
			veBeanLst.add(BeanTransformer.getVehicleBean(ve));
		}
		HibernateUtils.commitCloseCustomeTransationManager();
		return veBeanLst;
	}
}

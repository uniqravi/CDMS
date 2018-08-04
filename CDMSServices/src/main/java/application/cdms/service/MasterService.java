package application.cdms.service;

import application.cdms.models.HsnTax;
import application.cdms.models.LedgerType;
import application.cdms.models.RouteDtl;
import application.cdms.models.Territory;
import application.cdms.models.Vehicle;
import javafx.collections.ObservableList;

public interface MasterService {

	ObservableList<Territory> getAllTerritory();

	ObservableList<LedgerType> getAllLedgerTypes();

	ObservableList<HsnTax> getAllHSn();

	ObservableList<RouteDtl> getAllRoute();

	ObservableList<Vehicle> getAllVehicle();

}

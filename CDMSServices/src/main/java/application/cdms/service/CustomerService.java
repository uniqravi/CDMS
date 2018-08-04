package application.cdms.service;

import application.cdms.models.CstmrLedger;
import application.cdms.models.CustomerDtl;
import javafx.collections.ObservableList;

public interface CustomerService {

	CustomerDtl addNewCstmr(CustomerDtl cstmrDtl);

	ObservableList<CustomerDtl> getAllCustomers();

	CstmrLedger createNewLedger(CstmrLedger cstmrLedger);
}

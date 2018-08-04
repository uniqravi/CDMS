package application.cdms.dao;

import java.util.List;

import application.cdms.db.entities.BeverageProductCategory;
import application.cdms.db.entities.PrdctCurrPriceScheme;
import application.cdms.db.entities.PurchaseDtl;
import application.cdms.models.SearchBean;



public interface ProductDao {

	List<String> getgroupNmLstByParameters(String mlQty, String packingnmcd, String packingQtyCd) throws Exception;

	List<Object[]> getPrdctSchemePriceDtl(String groupNm) throws Exception;

	List<String> getAllgroupNmLst() throws Exception;

	List<BeverageProductCategory> getProductListByParams(BeverageProductCategory productCat) throws Exception;

	List<PrdctCurrPriceScheme> latestAllSchemePrice() throws Exception;

	List<String[]> viewPurchaseSummeryByDt(SearchBean searchBean) throws Exception;

	List<String[]> viewPurchsTaxCmpontByInvoice(String challanInvNo);

	PurchaseDtl getChallanDetailByInvoice(String invoice);

	List<String[]> GETPurchaseBreakLstByInvoice(String challanInvoice);

	void updateRtnPurchaseInvoiceNumber(String challanNumber, String purchaseInvoiceNo, String saleInvoiceNo) throws Exception;

	List<String[]> purchaseTaxReportBydtMethod(SearchBean serchBean) throws Exception;

	void updateRtnEmtpyInvoiceNumber(String challanNumber, String purchaseInvoiceNo, String nbSaleInvoiceNum) throws Exception;

}

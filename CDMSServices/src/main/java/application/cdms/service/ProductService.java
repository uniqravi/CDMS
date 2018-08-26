package application.cdms.service;

import java.util.List;
import java.util.Map;

import application.cdms.models.Choice;
import application.cdms.models.FillingQty;
import application.cdms.models.FirmSeller;
import application.cdms.models.Flavour;
import application.cdms.models.NonBeveragePrdct;
import application.cdms.models.NonBeverageSale;
import application.cdms.models.PackingName;
import application.cdms.models.PackingQty;
import application.cdms.models.Product;
import application.cdms.models.ProductGroup;
import application.cdms.models.ProductPriceSchemeDtl;
import application.cdms.models.PurchaseDtls;
import application.cdms.models.Sale;
import application.cdms.models.SearchBean;
import application.cdms.models.SupplyDtl;
import application.cdms.transformer.Initialization;
import javafx.collections.ObservableList;

public interface ProductService {
   List<FillingQty> fillingQtyList();
   List<Flavour> flavrList();
   List<PackingName> packingtypeList();
   List<PackingQty> packingQtyList();
   boolean addNewBeverageProduct(Product productModel) throws Exception;
   List<String> groupNmLstByParameters(String key, String key2, String key3) throws Exception;
   ObservableList<Product> productList();
   String addPurchaseDtls(PurchaseDtls purcDtls);
   Map<String, Map<String, List<ProductPriceSchemeDtl>>> getPrdctSchemePriceDtl(String groupNm) throws Exception;
   ObservableList<Choice> getAllGroupNm();
   ObservableList<Product> productLstByParams(Product product);
   ObservableList<ProductGroup> getAllProductGroup();
   String updateSchemeNPrice(List<ProductPriceSchemeDtl> prodPriceDtls);
   Map<String, ProductPriceSchemeDtl> latestAllSchemePrice() throws Exception;
   Sale sellProduct(Sale saleBean) throws Exception;
   ObservableList<String[]> viewPurchaseSummeryByDt(SearchBean serchBean) throws Exception;
   ObservableList<String[]> viewPurchaseTaxCompntByInvoice(String invoive) throws Exception;
   PurchaseDtls getChallanDetailByInvoice(String str,Initialization mode) throws Exception;
   void updatePurchasePaymentDtl(String purchaseSeq, Double paidAmt, String payMethod, String payDateStr, String payId) throws Exception;
   ObservableList<String[]> viewPurchaseBreakByInvoice(String invoive) throws Exception;
   void intiateSupply(SupplyDtl supplyDtl) throws Exception;
   void updateRtnPurchaseInvoiceNumber(String challanNumber, String purchaseInvoiceNo, String saleInvoiceNo) throws Exception;
   Sale storeBreakageClaimDetails(Sale sale)throws Exception;
   List<PurchaseDtls> getAllChallanDetail(Initialization mode);
   ObservableList<FirmSeller> getAllSellerFirmDtl();
   ObservableList<String[]> viewPurchaseTaxByDt(SearchBean serchBean) throws Exception;
   ObservableList<NonBeveragePrdct> nonBproductList();
   NonBeverageSale returnEmptyInvoice(NonBeverageSale sale) throws Exception;
   void updateRtnEmtpyInvoiceNumber(String challanNumber, String purchaseInvoiceNo, String nbSaleInvoiceNum)throws Exception;
   public List<PurchaseDtls> getAllChallanDtlsForForRtnEmpty(Initialization mode);
}
package application.cdms.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import application.cdms.dao.ProductDao;
import application.cdms.db.entities.BeverageProductCategory;
import application.cdms.db.entities.PrdctCurrPriceScheme;
import application.cdms.db.entities.PurchaseDtl;
import application.cdms.hibernate.utility.HibernateUtils;
import application.cdms.models.SearchBean;

public final class ProductDaoImpl implements ProductDao{
	
	private Logger logger = Logger.getLogger(ProductDaoImpl.class);
	
	private ProductDaoImpl(){
		super();
	}
	
	public static ProductDao getInstance(){
		return ProductDaoInstanceHolder.fillingQtyDao;
	}
	
	private static class ProductDaoInstanceHolder{
		public static ProductDao fillingQtyDao=new ProductDaoImpl();
	}
    
	
	private final String groupNmQuery="select group_name from cdms.beverage_product_group_dtl where (filing_qty_cd1=? or filling_qty_cd2=?) and packing_name_cd=? and packing_qty_cd=?";
	@Override
	public List<String> getgroupNmLstByParameters(String mlQty,String packingnmcd,String packingQtyCd)throws Exception{
		logger.info("ProductDaoImpl :: groupNmLstByParameters :: begin");
		logger.info("ProductDaoImpl :: groupNmLstByParameters :: ml Qty ### "+mlQty);
		logger.info("ProductDaoImpl :: groupNmLstByParameters :: packingn cd ### "+packingnmcd);
		logger.info("ProductDaoImpl :: groupNmLstByParameters :: packingQty Cd ### "+packingQtyCd);
		Query query = HibernateUtils.getCustomeTrasationManager().initTx().createSQLQuery(groupNmQuery);//.list();
		query.setParameter(0,mlQty);
		query.setParameter(1,mlQty);
		query.setParameter(2,packingnmcd);
		query.setParameter(3,packingQtyCd);
		@SuppressWarnings("unchecked")
		List<String> grpNmLst=query.list();
		logger.info("ProductDaoImpl :: groupNmLstByParameters :: group name list "+grpNmLst);
		logger.info("ProductDaoImpl :: groupNmLstByParameters :: End");
		return grpNmLst;
	}

	private final String allGroupNm="select group_name from cdms.beverage_product_group_dtl";
	@Override
	public List<String> getAllgroupNmLst()throws Exception{
		logger.info("ProductDaoImpl :: getAllgroupNmLst :: begin");
		Query query = HibernateUtils.getCustomeTrasationManager().initTx().createSQLQuery(allGroupNm);
		@SuppressWarnings("unchecked")
		List<String> grpNmLst=query.list();
		logger.info("ProductDaoImpl :: getAllgroupNmLst :: all group list ### "+grpNmLst);
		logger.info("ProductDaoImpl :: getAllgroupNmLst :: End");
		return grpNmLst;
	}
	
	//private final String prdctSchemeDtlsQuery="select a.*,b.* from cdms.beverage_product_category as a left join  cdms.prdct_curr_price_scheme as b on a.product_cd=b.product_cd where a.group_name=?";
	private final String prdctSchemeDtlsQuery="select a.*,b.* from cdms.beverage_product_category "
			+ "as a left join  cdms.prdct_curr_price_scheme as b on a.product_cd=b.product_cd "
			+ "where a.group_name=? and (b.price_lastchange_dt is null or b.price_lastchange_dt in "
			+ "(select max(c.price_lastchange_dt) from cdms.prdct_curr_price_scheme c group by c.product_cd))";
	@Override
	public List<Object[]> getPrdctSchemePriceDtl(String groupNm) throws Exception{
		logger.info("ProductDaoImpl :: getPrdctSchemePriceDtl :: begin");
		logger.info("ProductDaoImpl :: getPrdctSchemePriceDtl :: Group Name ### :: groupNm");
		Query query = HibernateUtils.getCustomeTrasationManager().initTx().createSQLQuery(prdctSchemeDtlsQuery).addEntity(BeverageProductCategory.class).addEntity(PrdctCurrPriceScheme.class);	
		query.setParameter(0,groupNm);
		@SuppressWarnings("unchecked")
		List<Object[]> lst=query.list();
		logger.info("ProductDaoImpl :: getPrdctSchemePriceDtl :: price Scheme List ### "+lst);
		logger.info("ProductDaoImpl :: getPrdctSchemePriceDtl :: End");
		return lst;
	}
	
	@Override
	public List<BeverageProductCategory> getProductListByParams(BeverageProductCategory productCat) throws Exception{
		logger.info("ProductDaoImpl :: getProductListByParams :: begin");
		String prdctListByGroupNm = "select * from cdms.beverage_product_category where ";
		if(productCat.getGroupName()!=null){
			prdctListByGroupNm+="group_name='"+productCat.getGroupName()+"' ";
		}
		prdctListByGroupNm+="order by product_name";
		logger.info("ProductDaoImpl :: getProductListByParams :: Executed Query ### "+prdctListByGroupNm);
		Query query = HibernateUtils.getCustomeTrasationManager().initTx().createSQLQuery(prdctListByGroupNm).addEntity(BeverageProductCategory.class);
		@SuppressWarnings("unchecked")
		List<BeverageProductCategory> prdctList=query.list();
		logger.info("ProductDaoImpl :: getProductListByParams :: Product list ### "+prdctList);
		logger.info("ProductDaoImpl :: getProductListByParams :: End");
		return prdctList;
	}
	
	private final String LATESTALLPRDCTPIRCE = "select * from cdms.prdct_curr_price_scheme a where a.price_lastchange_dt in (select max(price_lastchange_dt) from cdms.prdct_curr_price_scheme group by product_cd) order by price_lastchange_dt desc";
	@Override
	public List<PrdctCurrPriceScheme> latestAllSchemePrice() throws Exception{
		logger.info("ProductDaoImpl :: latestAllSchemePrice :: begin");
		Query query = HibernateUtils.getCustomeTrasationManager().initTx().createSQLQuery(LATESTALLPRDCTPIRCE).addEntity(PrdctCurrPriceScheme.class);
		@SuppressWarnings("unchecked")
		List<PrdctCurrPriceScheme> prdctPriceShemeLst=query.list();
		logger.info("ProductDaoImpl :: latestAllSchemePrice :: defined price list retrieved ### "+prdctPriceShemeLst);
		logger.info("ProductDaoImpl :: latestAllSchemePrice :: End");
		return prdctPriceShemeLst;
	}
	
	private final String purchasesSummeryByDt = "select to_char(challan_dt,'DD-MON-YYYY') as challan_dt,pur.challan_invoice_no,pur.challan_no,sum(purprd.purchase_prdct_qty) as total_load, "
			+ "pur.total_purchased_glass_qty,pur.total_return_empty_glass_qty, "+
	"sum(purprd.purchase_prdct_cgst) as cgst_sum, "
	+ "sum(purprd.purchase_prdct_igst) as igst_sum, "
	+ "sum(purprd.purchase_prdct_sgst) as sgst_sum, "+
	"sum(purprd.purchase_prdct_cess_tax) as cess_sum ,pur.purchase_seq_no "+
	"from cdms.purchase_dtl as pur,cdms.purchase_prdct_dtl as purprd "+
	"where pur.purchase_seq_no=purprd.purchase_seq_no and challan_dt between to_date(?,'DD-MM-YYYY') and to_date(?,'DD-MM-YYYY') "+
	"group by  pur.challan_no, pur.challan_invoice_no,pur.purchase_seq_no order by pur.challan_dt desc";
	
	/*
	private final String purchasesSummeryQuery = "select to_char(challan_dt,'DD-MON-YYYY') as challan_dt,pur.challan_invoice_no,pur.challan_no,sum(purprd.purchase_prdct_qty) as total_load, "
			+ "pur.total_purchased_glass_qty,pur.total_return_empty_glass_qty, "+
	"sum(purprd.purchase_prdct_cgst) as cgst_sum, "
	+ "sum(purprd.purchase_prdct_igst) as igst_sum, "
	+ "sum(purprd.purchase_prdct_sgst) as sgst_sum, "+
	"sum(purprd.purchase_prdct_cess_tax) as cess_sum ,pur.purchase_seq_no "+
	"from cdms.purchase_dtl as pur,cdms.purchase_prdct_dtl as purprd "+
	"where pur.purchase_seq_no=purprd.purchase_seq_no "+
	"group by  pur.challan_no, pur.challan_invoice_no,pur.purchase_seq_no order by pur.challan_dt desc";
	*/
	
	private final String purchasesSummeryQuery="select to_char(pur.challan_dt,'DD-MON-YYYY') as challan_dt,pur.challan_invoice_no,"
			+ "pur.challan_no,sum(purprd.purchase_prdct_qty) as total_load,pur.total_purchased_glass_qty,pur.total_return_empty_glass_qty,"
			+ "sum(purprd.purchase_prdct_cgst) as cgst_sum,sum(purprd.purchase_prdct_igst) as igst_sum,"
			+ "sum(purprd.purchase_prdct_sgst) as sgst_sum,sum(purprd.purchase_prdct_cess_tax) as cess_sum ,pur.purchase_seq_no "
			+ "from cdms.purchase_dtl as pur "
			+ "inner join cdms.purchase_prdct_dtl as purprd on pur.purchase_seq_no=purprd.purchase_seq_no "
			+ "group by  pur.challan_no, pur.challan_invoice_no,pur.purchase_seq_no order by pur.challan_dt desc";
	
	@Override
	public List<String[]> viewPurchaseSummeryByDt(SearchBean serchBean) throws Exception{
		logger.info("viewPurchaseSummeryByDt :: begin");
		Query query=null;
		if(serchBean!=null){
			query = HibernateUtils.getCustomeTrasationManager().initTx().createSQLQuery(purchasesSummeryByDt);
			query.setParameter(0,serchBean.getFromDt());
			query.setParameter(1, serchBean.getToDt());
		}
		if(serchBean==null){
			query = HibernateUtils.getCustomeTrasationManager().initTx().createSQLQuery(purchasesSummeryQuery);
			query.setMaxResults(10);
		}
		
		@SuppressWarnings("unchecked")
		List<String[]> billSumryDtlLst=query.list();
		logger.info("viewPurchaseSummeryByDt:: list retrieved ### "+billSumryDtlLst);
		logger.info("viewPurchaseSummeryByDt :: End");
		return billSumryDtlLst;
	}
	
	private final String purchaseTaxComponentByInvoice="select hsn.hsn_cd,"
			+ "(hsn.hsn_discription || ' | ' || hsn.cgst || ' cgst | ' || hsn.sgst_or_igst || ' sgst_igst | ' || hsn.cess || ' cess') as tax_description, "
			+ "sum(purprd.purchase_prdct_cgst) as cgst_sum,sum(purprd.purchase_prdct_sgst) as sgst_sum, "
			+ "sum(purprd.purchase_prdct_igst) as igst_sum,sum(purprd.purchase_prdct_cess_tax) as cess_sum "
			+ "from cdms.purchase_prdct_dtl as purprd, cdms.beverage_product_category as prdct,cdms.hsn_tax_structure hsn "
			+ " where purchase_seq_no=(select pur.purchase_seq_no from cdms.purchase_dtl as pur where "
			+ "pur.challan_invoice_no=?) and purprd.product_cd=prdct.product_cd and prdct.hsn_code=hsn.hsn_cd "
			+ " group by hsn.hsn_cd,hsn.hsn_discription,hsn.cgst,hsn.sgst_or_igst,hsn.cess ";
	
	@Override
	public List<String[]> viewPurchsTaxCmpontByInvoice(String challanInvNo){
		logger.info("viewPurchsTaxCmpontByInvoice :: begin");
		Query query = HibernateUtils.getCustomeTrasationManager().initTx().createSQLQuery(purchaseTaxComponentByInvoice);
		query.setParameter(0,challanInvNo);
		@SuppressWarnings("unchecked")
		List<String[]> taxComponents=query.list();
		logger.info("viewPurchsTaxCmpontByInvoice:: list retrieved ### "+taxComponents);
		logger.info("viewPurchsTaxCmpontByInvoice :: End");
		return taxComponents;
	}
	
	private final String purchaseDtlsQuery="select * from cdms.purchase_dtl as pur where pur.challan_invoice_no=?";
	
	@Override
	public PurchaseDtl getChallanDetailByInvoice(String invoice){
		logger.info("getChallanDetailByInvoice :: begin");
		Query query = HibernateUtils.getCustomeTrasationManager().initTx().createSQLQuery(purchaseDtlsQuery).addEntity(PurchaseDtl.class);
		query.setParameter(0,invoice);
		PurchaseDtl purchaseDtl = (PurchaseDtl) query.uniqueResult();
		logger.info("getChallanDetailByInvoice :: recived purchaseDtl ### "+purchaseDtl);
		return purchaseDtl;
	}
	
	private final String purchaseBreakByInvoiceQuery = "select prdct.group_name, "
			+ "sum(break.burst_bs) as burst, "
			+ "sum(break.open_mouth_bs) as open_mount, sum(break.shortage_bs) as shortage, "
			+ "sum(break.seal_pack_shortage_bs) as seal_shortage, "
			+ "sum(break.leakage_bs) as leakage "
			+ "from cdms.prdct_breakage_dtl break INNER join cdms.beverage_product_category as prdct on break.product_cd=prdct.product_cd "
			+ "where break.breakage_seq in "
			+ "(select purprd.purchase_breakage_seq from cdms.purchase_prdct_dtl as purprd where "
			+ "purchase_seq_no=(select pur.purchase_seq_no from cdms.purchase_dtl as pur where "
			+ "pur.challan_invoice_no=?) "
			+ ") group by prdct.group_name ";
	
	@Override
	public List<String[]> GETPurchaseBreakLstByInvoice(String challanInvoice){
		logger.info("GETPurchaseBreakLstByInvoice :: begin");
		Query query = HibernateUtils.getCustomeTrasationManager().initTx().createSQLQuery(purchaseBreakByInvoiceQuery);
		query.setParameter(0,challanInvoice);
		@SuppressWarnings("unchecked")
		List<String[]> breakLst=query.list();
		logger.info("GETPurchaseBreakLstByInvoice :: list retrieved ### "+breakLst);
		logger.info("GETPurchaseBreakLstByInvoice :: End");
		return breakLst;
	}

	private final String updatePurchaseRtnInvoiceQuery="update cdms.purchase_dtl set return_invoice_no=? where challan_invoice_no=? and challan_no=?";
	@Override
	public void updateRtnPurchaseInvoiceNumber(String challanNumber, String purchaseInvoiceNo, String saleInvoiceNo) throws Exception{
		logger.info("ProductDaoImpl :: updateRtnPurchaseInvoiceNumber :: begin");
		Query query = HibernateUtils.getCustomeTrasationManager().initTx().createSQLQuery(updatePurchaseRtnInvoiceQuery);
		query.setParameter(0, saleInvoiceNo);
		query.setParameter(1, purchaseInvoiceNo);
		query.setParameter(2, challanNumber);
		int count=query.executeUpdate();
		logger.info("ProductDaoImpl :: updateRtnPurchaseInvoiceNumber :: updated Row count ### "+count);
		//HibernateUtils.CloseCustomeTransationManager();
	}
	
	private final String purchaseTaxReportBydt="select hsn.hsn_cd,"
			+ "(hsn.hsn_discription || ' | ' || hsn.cgst || ' cgst | ' || hsn.sgst_or_igst || ' sgst_igst | ' || hsn.cess || ' cess') as tax_description,"
			+ "sum(purprd.purchase_prdct_cgst) as cgst_sum,sum(purprd.purchase_prdct_sgst) as sgst_sum,"
			+ "sum(purprd.purchase_prdct_igst) as igst_sum,sum(purprd.purchase_prdct_cess_tax) as cess_sum "
			+ "from cdms.purchase_prdct_dtl as purprd, cdms.beverage_product_category as prdct,cdms.hsn_tax_structure hsn "
			+ "where purchase_seq_no in (select pur.purchase_seq_no from cdms.purchase_dtl as pur where "
			+ "pur.challan_dt between to_date(?,'DD-MM-YYYY') and to_date(?,'DD-MM-YYYY')) "
			+ "and purprd.product_cd=prdct.product_cd and prdct.hsn_code=hsn.hsn_cd "
			+ "group by hsn.hsn_cd,hsn.hsn_discription,hsn.cgst,hsn.sgst_or_igst,hsn.cess";
	@Override
	public List<String[]> purchaseTaxReportBydtMethod(SearchBean serchBean)throws Exception{
		logger.info("purchaseTaxReportBydtMethod :: begin");
		Query query = HibernateUtils.getCustomeTrasationManager().initTx().createSQLQuery(purchaseTaxReportBydt);
		query.setParameter(0,serchBean.getFromDt());
		query.setParameter(1, serchBean.getToDt());
		@SuppressWarnings("unchecked")
		List<String[]> taxComponents=query.list();
		logger.info("purchaseTaxReportBydtMethod:: list retrieved ### "+taxComponents);
		logger.info("purchaseTaxReportBydtMethod :: End");
		return taxComponents;
	}

	private final String updateEmptyRtnInvoiceQuery="update cdms.purchase_dtl set return_empty_invoice_no=? where challan_invoice_no=? and challan_no=?";
	@Override
	public void updateRtnEmtpyInvoiceNumber(String challanNumber, String purchaseInvoiceNo, String nbSaleInvoiceNum)
			throws Exception {
		logger.info("ProductDaoImpl :: updateRtnEmtpyInvoiceNumber :: begin");
		Query query = HibernateUtils.getCustomeTrasationManager().initTx().createSQLQuery(updateEmptyRtnInvoiceQuery);
		query.setParameter(0, nbSaleInvoiceNum);
		query.setParameter(1, purchaseInvoiceNo);
		query.setParameter(2, challanNumber);
		int count=query.executeUpdate();
		logger.info("ProductDaoImpl :: updateRtnEmtpyInvoiceNumber :: updated Row count ### "+count);
	}
	
}

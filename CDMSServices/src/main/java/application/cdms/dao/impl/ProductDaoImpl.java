package application.cdms.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import application.cdms.dao.ProductDao;
import application.cdms.db.entities.BeverageProductCategory;
import application.cdms.db.entities.PrdctCurrPriceScheme;
import application.cdms.db.entities.PurchaseDtl;
import application.cdms.hibernate.utility.HibernateUtils;

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
		Criteria criteria = HibernateUtils.getCustomeTrasationManager().getSession().createCriteria(BeverageProductCategory.class);
		if(productCat.getGroupName()!=null){
			criteria.add(Restrictions.eq("groupName",productCat.getGroupName()));
		}
		criteria.setFetchMode("fillingQty", FetchMode.JOIN);
		criteria.setFetchMode("flavr", FetchMode.JOIN);
		criteria.setFetchMode("packing", FetchMode.JOIN);
		criteria.setFetchMode("packingQty", FetchMode.JOIN);
		criteria.setFetchMode("hsn", FetchMode.JOIN);
		@SuppressWarnings("unchecked")
		List<BeverageProductCategory> prdctList=criteria.list();
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
	
	//private final String purchaseDtlsQuery="select * from cdms.purchase_dtl as pur where pur.challan_invoice_no=?";
	
	@Override
	public PurchaseDtl getChallanDetailByInvoice(String invoice){
		logger.info("getChallanDetailByInvoice :: begin");
		Criteria criteria = HibernateUtils.getCustomeTrasationManager().getSession().createCriteria(PurchaseDtl.class);
		criteria.add(Restrictions.eq("challanInvoiceNo",invoice));
		criteria.setFetchMode("puchasedPrdctList", FetchMode.JOIN);
		//criteria.setFetchMode("nonBevPrdctList", FetchMode.JOIN);
		PurchaseDtl purchaseDtl = (PurchaseDtl) criteria.uniqueResult();
		logger.info("getChallanDetailByInvoice :: recived purchaseDtl ### "+purchaseDtl);
		return purchaseDtl;
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

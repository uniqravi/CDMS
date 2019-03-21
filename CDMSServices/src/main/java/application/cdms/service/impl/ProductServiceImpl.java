package application.cdms.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import application.cdms.constants.ApplicationConstant;
import application.cdms.dao.ProductDao;
import application.cdms.dao.impl.ProductDaoImpl;
import application.cdms.db.entities.BeverageProductCategory;
import application.cdms.db.entities.BeverageProductGroupDtl;
import application.cdms.db.entities.ClaimBreakagePrdctDtl;
import application.cdms.db.entities.ClaimHeader;
import application.cdms.db.entities.FillingQtyCategory;
import application.cdms.db.entities.FirmOrSellerDtl;
import application.cdms.db.entities.FlavourCategory;
import application.cdms.db.entities.NonBeverageProductCatergory;
import application.cdms.db.entities.NonBeverageSaleDtl;
import application.cdms.db.entities.PackingNameCategory;
import application.cdms.db.entities.PackingQtyCategory;
import application.cdms.db.entities.PrdctBreakageDtl;
import application.cdms.db.entities.PrdctCurrPriceScheme;
import application.cdms.db.entities.ProductInstantStockDtl;
import application.cdms.db.entities.PurchaseDtl;
import application.cdms.db.entities.PurchaseNonBeveragePrdctDtl;
import application.cdms.db.entities.PurchasePrdctDtl;
import application.cdms.db.entities.SaleBreakageDtl;
import application.cdms.db.entities.SaleDtl;
import application.cdms.db.entities.SaleInvoicesPrdctDtl;
import application.cdms.db.entities.SalePrdctDtl;
import application.cdms.db.entities.SalePrdctSchemeDtl;
import application.cdms.db.entities.SupplyRecord;
import application.cdms.enums.Claim_Type;
import application.cdms.exception.handler.CDMSGeneralException;
import application.cdms.hibernate.utility.HibernateUtils;
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
import application.cdms.models.SalePrdct;
import application.cdms.models.SalePrdctInvoice;
import application.cdms.models.Scheme;
import application.cdms.models.SchemeProduct;
import application.cdms.models.SupplyDtl;
import application.cdms.service.ProductService;
import application.cdms.transformer.BeanTransformer;
import application.cdms.transformer.Initialization;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public final class ProductServiceImpl extends GenericServiceImpl implements ProductService {

	private ProductDao productDao = ProductDaoImpl.getInstance();
	
	private Logger logger = Logger.getLogger(ProductServiceImpl.class);
	
	private static volatile ProductService instance;

	private ProductServiceImpl() {
		super();
	}

	public static ProductService getInstance() {
		if(instance==null){
			synchronized (ProductService.class) {
				if(instance==null){
					instance=new ProductServiceImpl();
					//instance=(ProductService) Proxy.newProxyInstance(ProductService.class.getClassLoader(), new Class[]{ProductService.class},new CustomeInvocationHandler(new ProductServiceImpl()));
				}
			}
		}
		return instance;
	}
	
	/*
	private static class CustomeInvocationHandler implements InvocationHandler {

        private ProductServiceImpl impl;

        public CustomeInvocationHandler(ProductServiceImpl impl) {
            this.impl = impl;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getDeclaringClass() == ProductService.class) {
                System.out.println("proxy has been called");
            }

            return method.invoke(this.impl, args);
        }
    }
*/
	@Override
	public List<FillingQty> fillingQtyList() {
		logger.info("ProductServiceImpl :: fillingQtyList :: begin");
		HibernateUtils.getCustomeTrasationManager().initTx();
		List<FillingQtyCategory> listfillingQtyAll = this.findAllEntity(FillingQtyCategory.class);
		List<FillingQty> fillingQtyList = new ArrayList<FillingQty>();
		for (FillingQtyCategory fillingQtyCategory : listfillingQtyAll) {
			fillingQtyList.add(BeanTransformer.getfillingQtyBean(fillingQtyCategory));
		}
		HibernateUtils.commitCloseCustomeTransationManager();
		Collections.sort(fillingQtyList);
		logger.info("ProductServiceImpl :: fillingQtyList :: Filling Qty List count ### "+fillingQtyList.size());
		logger.info("ProductServiceImpl :: fillingQtyList :: end");
		return fillingQtyList;
	}

	@Override
	public List<Flavour> flavrList() {
		logger.info("ProductServiceImpl :: flavrList :: begin");
		HibernateUtils.getCustomeTrasationManager().initTx();
		List<FlavourCategory> flavrEntityList = this.findAllEntity(FlavourCategory.class);
		List<Flavour> flvourList = new ArrayList<Flavour>();
		for (FlavourCategory flavourCategory : flavrEntityList) {
			flvourList.add(BeanTransformer.getFlavrBean(flavourCategory));
		}
		HibernateUtils.commitCloseCustomeTransationManager();
		Collections.sort(flvourList);
		logger.info("ProductServiceImpl :: flavrList :: flavour Name List count ### "+flvourList.size());
		logger.info("ProductServiceImpl :: flavrList :: end");
		return flvourList;
	}

	@Override
	public List<PackingName> packingtypeList() {
		logger.info("ProductServiceImpl :: packingtypeList :: begin");
		HibernateUtils.getCustomeTrasationManager().initTx();
		List<PackingNameCategory> packingNmEntityList = this.findAllEntity(PackingNameCategory.class);
		List<PackingName> packingNmlst = new ArrayList<PackingName>();
		for (PackingNameCategory packingNameCategory : packingNmEntityList) {
			packingNmlst.add(BeanTransformer.getPackingNameBean(packingNameCategory));
		}
		HibernateUtils.commitCloseCustomeTransationManager();
		Collections.sort(packingNmlst);
		logger.info("ProductServiceImpl :: packingtypeList :: Packing Name List count ### "+packingNmlst.size());
		logger.info("ProductServiceImpl :: packingtypeList :: end");
		return packingNmlst;
	}

	@Override
	public List<PackingQty> packingQtyList() {
		logger.info("ProductServiceImpl :: packingQtyList :: begin");
		HibernateUtils.getCustomeTrasationManager().initTx();
		List<PackingQtyCategory> packingQtyEntityList = this.findAllEntity(PackingQtyCategory.class);
		List<PackingQty> packingQtylst = new ArrayList<PackingQty>();
		for (PackingQtyCategory packingQtyCategory : packingQtyEntityList) {
			packingQtylst.add(BeanTransformer.getPackingQtyBean(packingQtyCategory));
		}
		HibernateUtils.commitCloseCustomeTransationManager();
		Collections.sort(packingQtylst);
		logger.info("ProductServiceImpl :: packingQtyList :: Packing Qty List count ### "+packingQtylst.size());
		logger.info("ProductServiceImpl :: packingQtyList :: end");
		return packingQtylst;
	}

	@Override
	public boolean addNewBeverageProduct(Product product) throws Exception {
		boolean status = false;
		try{
		logger.info("ProductServiceImpl :: addNewBeverageProduct :: begin");
		BeverageProductCategory beverageProductCategory = BeanTransformer.getProductEntity(product);
		logger.info("ProductServiceImpl :: addNewBeverageProduct :: db activity start");
		HibernateUtils.getCustomeTrasationManager().initTx();
		if (product.isHasNewFilling()) {
			logger.info("ProductServiceImpl :: addNewBeverageProduct ::  new filling ");
			FillingQtyCategory fillingQtyCategory = beverageProductCategory.getFillingQty();
			fillingQtyCategory.setFillingQtyAddedBy(beverageProductCategory.getProductAddedBy());
			fillingQtyCategory.setFillingQtyAddedDt(beverageProductCategory.getProductAddedDt());
			this.saveEntity(fillingQtyCategory);
		}
		if (product.isHasNewFlavour()) {
			logger.info("ProductServiceImpl :: addNewBeverageProduct ::  new flavour ");
			FlavourCategory flavourCategory = beverageProductCategory.getFlavr();
			flavourCategory.setFalvrAddedBy(beverageProductCategory.getProductAddedBy());
			flavourCategory.setFlavrAddedDt(beverageProductCategory.getProductAddedDt());
			this.saveEntity(flavourCategory);
		}
		if (product.isHasNewPakingType()) {
			logger.info("ProductServiceImpl :: addNewBeverageProduct ::  new Packing Type ");
			PackingNameCategory packingNameCategory = beverageProductCategory.getPacking();
			packingNameCategory.setPakingAddedBy(beverageProductCategory.getProductAddedBy());
			packingNameCategory.setPakingAddedDt(beverageProductCategory.getProductAddedDt());
			this.saveEntity(packingNameCategory);
		}
		if (product.isHasNewPakingQty()) {
			logger.info("ProductServiceImpl :: addNewBeverageProduct ::  new Packing Qty ");
			PackingQtyCategory packingQtyCategory = beverageProductCategory.getPackingQty();
			packingQtyCategory.setPackingQtyAddedBy(beverageProductCategory.getProductAddedBy());
			packingQtyCategory.setPackingQtyAddedDt(beverageProductCategory.getProductAddedDt());
			this.saveEntity(packingQtyCategory);
		}
		if (product.isHasNewGroup()) {
			logger.info("ProductServiceImpl :: addNewBeverageProduct ::  new Group Name ");
			BeverageProductGroupDtl beverageProductGroupDtl = new BeverageProductGroupDtl();
			beverageProductGroupDtl.setGroupName(product.getGroupNm());
			beverageProductGroupDtl.setFillingQtyCd1(beverageProductCategory.getFillingQty().getFillingQtyCd());
			beverageProductGroupDtl.setPackingNameCd(beverageProductCategory.getPacking().getPackingNameCd());
			beverageProductGroupDtl.setPackingQtyCd(beverageProductCategory.getPackingQty().getPackingQtyCd());
			this.saveEntity(beverageProductGroupDtl);
		}
		else if(product.getGroupNm()==null || product.getGroupNm().trim().equals("")){
			logger.info("ProductServiceImpl :: addNewBeverageProduct ::  checking Group Name is already Present");
			Criteria cr = HibernateUtils.getCustomeTrasationManager().getSession().createCriteria(BeverageProductGroupDtl.class);
			cr.add(Restrictions.eq("fillingQtyCd1",beverageProductCategory.getFillingQty().getFillingQtyCd()));
			cr.add(Restrictions.eq("packingNameCd",beverageProductCategory.getPacking().getPackingNameCd()));
			cr.add(Restrictions.eq("packingQtyCd", beverageProductCategory.getPackingQty().getPackingQtyCd()));
			BeverageProductGroupDtl beverageGroup=(BeverageProductGroupDtl) cr.uniqueResult();
			if(beverageGroup!=null){
				throw new CDMSGeneralException("Please select Product Group.");
			}
			else{
				String groupNm = beverageProductCategory.getFillingQty().getFillingQtyMl()+"_"+beverageProductCategory.getPacking().getPackingName()+"_"+beverageProductCategory.getPackingQty().getPackingQuantity();
				beverageProductCategory.setGroupName(groupNm);
				BeverageProductGroupDtl beverageProductGroupDtl = new BeverageProductGroupDtl();
				beverageProductGroupDtl.setGroupName(groupNm);
				beverageProductGroupDtl.setFillingQtyCd1(beverageProductCategory.getFillingQty().getFillingQtyCd());
				beverageProductGroupDtl.setPackingNameCd(beverageProductCategory.getPacking().getPackingNameCd());
				beverageProductGroupDtl.setPackingQtyCd(beverageProductCategory.getPackingQty().getPackingQtyCd());
				this.saveEntity(beverageProductGroupDtl);
			}
		}
		this.saveEntity(beverageProductCategory);
		HibernateUtils.getCustomeTrasationManager().commitTx();
		logger.info("ProductServiceImpl :: addNewBeverageProduct :: db activity end");
		logger.info("ProductServiceImpl :: addNewBeverageProduct :: new product creation");
		//System.out.println("END");
		status = true;
		}
		catch(Exception e){
			logger.fatal("ProductServiceImpl :: addNewBeverageProduct :: Exception :: "+e.getCause().getMessage());
			HibernateUtils.getCustomeTrasationManager().rollBackTx();
			throw e;
		}
		finally{
			HibernateUtils.CloseCustomeTransationManager();
		}
		return status;
	}

	@Override
	public List<String> groupNmLstByParameters(String key, String key2, String key3) throws Exception {
		List<String> gropArrayLst=null;
		logger.info("ProductServiceImpl :: groupNmLstByParameters :: begin");
		HibernateUtils.getCustomeTrasationManager().initTx();
		try{
			gropArrayLst = productDao.getgroupNmLstByParameters(key, key2, key3);
			logger.info("ProductServiceImpl :: groupNmLstByParameters :: response recieved from db");
			HibernateUtils.getCustomeTrasationManager().commitTx();
		}
		catch(Exception e){
			logger.fatal("ProductServiceImpl :: groupNmLstByParameters :: Exception :: "+e.getMessage());
			throw e;
		}
		finally{
			HibernateUtils.CloseCustomeTransationManager();
		}
		logger.info("ProductServiceImpl :: groupNmLstByParameters :: end");
		return gropArrayLst;
	}

	@Override
	public ObservableList<Product> productList() {
		logger.info("ProductServiceImpl :: productList :: begin");
		HibernateUtils.getCustomeTrasationManager().initTx();
		Criteria criteria = HibernateUtils.getCustomeTrasationManager().getSession().createCriteria(BeverageProductCategory.class);
		criteria.setFetchMode("fillingQty", FetchMode.JOIN);
		criteria.setFetchMode("flavr", FetchMode.JOIN);
		criteria.setFetchMode("packing", FetchMode.JOIN);
		criteria.setFetchMode("packingQty", FetchMode.JOIN);
		criteria.setFetchMode("hsn", FetchMode.JOIN);
		//List<BeverageProductCategory> beveProductCateList = this.findAllEntity(BeverageProductCategory.class);
		@SuppressWarnings("unchecked")
		List<BeverageProductCategory> beveProductCateList=criteria.list();
		ObservableList<Product> productList = FXCollections.observableArrayList();
		HibernateUtils.commitCloseCustomeTransationManager();
		for (BeverageProductCategory beverage : beveProductCateList) {
			productList.add(BeanTransformer.getProductBean(beverage,Initialization.EAGER));
		}
		Collections.sort(productList);
		logger.info("ProductServiceImpl :: productList :: product List count ### "+productList.size());
		logger.info("ProductServiceImpl :: productList :: end");
		return productList;
	}

	@Override
	public String addPurchaseDtls(PurchaseDtls purchaseDtls) {
		logger.info("ProductServiceImpl :: addPurchaseDtls :: begin");
		try{
		Calendar cal = Calendar.getInstance();
		PurchaseDtl purchaseDtl = BeanTransformer.getPurchaseEntity(purchaseDtls);
		Iterator<PurchasePrdctDtl> itr = purchaseDtl.getPuchasedPrdctList().iterator();
		while (itr.hasNext()) {
			PurchasePrdctDtl purchasePrdctDtl = itr.next();
			purchasePrdctDtl.setPrdctRecievedDt(cal);
			purchasePrdctDtl.setPurchaseMasterdtl(purchaseDtl);
			PrdctBreakageDtl breakageEntity = purchasePrdctDtl.getPrdctBreakageDtl();
			if (breakageEntity.getBurstBs() == 0 && breakageEntity.getLeakageBs() == 0
					&& breakageEntity.getOpenMouthBs() == 0 && breakageEntity.getSealPackShortageBs() == 0
					&& breakageEntity.getShortageBs() == 0) {
				breakageEntity = null;
			} else {
				breakageEntity.setBreakageDt(cal);
			}
			purchasePrdctDtl.setPrdctBreakageDtl(breakageEntity);
		}
		if(purchaseDtl.getNonBevPrdctList()!=null && !purchaseDtl.getNonBevPrdctList().isEmpty()) {
			Iterator<PurchaseNonBeveragePrdctDtl> itr2 = purchaseDtl.getNonBevPrdctList().iterator();
			while(itr2.hasNext()){
				PurchaseNonBeveragePrdctDtl purchaseNonBev = itr2.next();
				purchaseNonBev.setPrdctRecievedDt(cal);
				purchaseNonBev.setPurchaseMasterdtl(purchaseDtl);
			}
		}
		HibernateUtils.getCustomeTrasationManager().initTx();
		logger.info("ProductServiceImpl :: addPurchaseDtls :: db activity start");
		logger.info("ProductServiceImpl :: addPurchaseDtls :: saving purchase detail start");
		this.saveEntity(purchaseDtl);
		// make entry in instant stock
		logger.info("ProductServiceImpl :: addPurchaseDtls :: saving purchase end");
		logger.info("ProductServiceImpl :: addPurchaseDtls :: instant product stock saving");
		for(PurchasePrdctDtl purchprdct:purchaseDtl.getPuchasedPrdctList()){
			ProductInstantStockDtl prdctInstantStock = new ProductInstantStockDtl();
			BeverageProductGroupDtl bvrgGrop = new BeverageProductGroupDtl();
			bvrgGrop.setGroupName(purchprdct.getProduct().getGroupName());
			prdctInstantStock.setProduct(purchprdct.getProduct());
			prdctInstantStock.setPrdctGroup(bvrgGrop);
			int packqty = purchprdct.getProduct().getPackingQty().getPackingQuantity();
			prdctInstantStock.setPrdctPurchaseQty(purchprdct.getPurchasePrdctQty()*packqty);
			prdctInstantStock.setPurchaseInvoiceNumber(purchaseDtl.getChallanInvoiceNo()+"");
			prdctInstantStock.setPrdctStockCurrTime(Calendar.getInstance());
			if(purchprdct.getPrdctBreakageDtl()!=null){
				long breakQty =purchprdct.getPrdctBreakageDtl().getBurstBs()+purchprdct.getPrdctBreakageDtl().getLeakageBs()
						+purchprdct.getPrdctBreakageDtl().getOpenMouthBs()+purchprdct.getPrdctBreakageDtl().getSealPackShortageBs()+
						purchprdct.getPrdctBreakageDtl().getShortageBs()+purchprdct.getPrdctBreakageDtl().getOtherBreakageBs();
				prdctInstantStock.setPrdctBreakageQtyBs(breakQty);
			}
			this.saveEntity(prdctInstantStock);
		}
		HibernateUtils.getCustomeTrasationManager().commitTx();
		//HibernateUtils.getCustomeTrasationManager().initTx();
		
		logger.info("ProductServiceImpl :: addPurchaseDtls :: instant product stock end");
		}
		catch(Exception e){
			logger.info("ProductServiceImpl :: addPurchaseDtls :: exception ### "+e.getMessage());
			HibernateUtils.getCustomeTrasationManager().rollBackTx();
			throw e;
		}
		finally{
			HibernateUtils.CloseCustomeTransationManager();
		}
		logger.info("ProductServiceImpl :: addPurchaseDtls :: end");
		return "saved";
	}

	@Override
	public Map<String, Map<String, List<ProductPriceSchemeDtl>>> getPrdctSchemePriceDtl(String groupNm) throws Exception {
		logger.info("ProductServiceImpl :: getPrdctSchemePriceDtl :: begin");
		Map<String, Map<String, List<ProductPriceSchemeDtl>>> prdctPriceSchemeMap = null;
		try{
			logger.info("ProductServiceImpl :: getPrdctSchemePriceDtl :: db activity start");
			List<Object[]> dbPrdctPriceLst = productDao.getPrdctSchemePriceDtl(groupNm);
				if (dbPrdctPriceLst != null && dbPrdctPriceLst.size() > 0) {
					logger.info("ProductServiceImpl :: getPrdctSchemePriceDtl :: price scheme list size ### "+dbPrdctPriceLst.size());
					prdctPriceSchemeMap = new HashMap<>();
					//int notDefinedPrice = 0;
					int loopcount=0;
					for (Object[] objArray : dbPrdctPriceLst) {
						Map<String,List<ProductPriceSchemeDtl>> singlePriceSchemesMap=null;
						BeverageProductCategory prdctEntity = (BeverageProductCategory) objArray[0];
						PrdctCurrPriceScheme prdctCurrPriceScheme = (PrdctCurrPriceScheme) objArray[1];
						ProductPriceSchemeDtl prdctPriceSchemeDtl = new ProductPriceSchemeDtl();
						Product prodBean=BeanTransformer.getProductBean(prdctEntity,Initialization.EAGER);
						prdctPriceSchemeDtl.setProduct(prodBean);
							if (prdctCurrPriceScheme == null) {
								String notPrice="Price to be defined";
								if(prdctPriceSchemeMap.containsKey(notPrice)){
									singlePriceSchemesMap=prdctPriceSchemeMap.get(notPrice);
									singlePriceSchemesMap.get(notPrice).add(prdctPriceSchemeDtl);
								}
								else{
									singlePriceSchemesMap=new HashMap<>();
									List<ProductPriceSchemeDtl> products=new ArrayList<>();
									products.add(prdctPriceSchemeDtl);
									singlePriceSchemesMap.put(notPrice, products);
								}
								prdctPriceSchemeMap.put(notPrice, singlePriceSchemesMap);
								continue;
				}
				
				prdctPriceSchemeDtl.setPrdctPrice(prdctCurrPriceScheme.getPrdctCurrPrice());
				prdctPriceSchemeDtl.setDbSchemeScript(prdctCurrPriceScheme.getPrdctCurrScheme());
				if(prdctPriceSchemeDtl.getPrdctPrice()!=0){
					
					String priceTab="INR-"+prdctCurrPriceScheme.getPrdctCurrPrice()+" Products";
					if(prdctPriceSchemeMap.containsKey(priceTab)){
						singlePriceSchemesMap=prdctPriceSchemeMap.get(priceTab);
						boolean isNotFound=true;
						Iterator<Entry<String, List<ProductPriceSchemeDtl>>> itr = singlePriceSchemesMap.entrySet().iterator();
						while(itr.hasNext()){
							Map.Entry<String, List<ProductPriceSchemeDtl>> entry = itr.next();
							String key = entry.getKey();
							if(key.endsWith(prdctPriceSchemeDtl.getDbSchemeScript())){
								entry.getValue().add(prdctPriceSchemeDtl);
								isNotFound=false;
								break;
							}
						}
						if(isNotFound){
							String sTab = "Scheme-"+(++loopcount)+"_"+prdctPriceSchemeDtl.getDbSchemeScript();
							List<ProductPriceSchemeDtl> prdctlst= new ArrayList<>();
							prdctlst.add(prdctPriceSchemeDtl);
							singlePriceSchemesMap.put(sTab, prdctlst);
						}
					}
					else{
						String sTab = "Scheme-"+(++loopcount)+"_"+prdctPriceSchemeDtl.getDbSchemeScript();
						List<ProductPriceSchemeDtl> prdctlst= new ArrayList<>();
						prdctlst.add(prdctPriceSchemeDtl);
						singlePriceSchemesMap = new HashMap<>();
						singlePriceSchemesMap.put(sTab, prdctlst);
						prdctPriceSchemeMap.put(priceTab, singlePriceSchemesMap);
					}
				}
				
				List<Scheme> avaiableSchemeChoices = null;
				// scheme split
				if (prdctCurrPriceScheme.getPrdctCurrScheme() != null && !prdctCurrPriceScheme.getPrdctCurrScheme().equals("")) {
					String[] schemesOptionArr = prdctCurrPriceScheme.getPrdctCurrScheme().split("\\~");
					avaiableSchemeChoices = new ArrayList<>();
					for (String schemeOptStr : schemesOptionArr) {
						String[] schemesArr = schemeOptStr.split("\\^");
						List<SchemeProduct> schemeProducts = new ArrayList<>();
						Scheme scheme = new Scheme();
						scheme.setSchemePrdcts(schemeProducts);
						for (String schemeStr : schemesArr) {
							String[] schemeQtyArr = schemeStr.split("\\|");
							String prdctSchemeStr = schemeQtyArr[0];
							String prdctQtySchemeStr = schemeQtyArr[1];
							SchemeProduct shemePrdct = new SchemeProduct();
							if (prdctSchemeStr.startsWith("PROD")) {
								BeverageProductCategory prodCat=this.findById(BeverageProductCategory.class, prdctSchemeStr);
								Product product = BeanTransformer.getProductBean(prodCat,Initialization.LAZY);
								shemePrdct.setProduct(product);
							} else {
								ProductGroup productGroup = new ProductGroup();
								productGroup.setGroupName(prdctSchemeStr);
								shemePrdct.setGroupName(productGroup);
							}
							shemePrdct.setPrdctOrGroupBSQty(Long.parseLong(prdctQtySchemeStr));
							schemeProducts.add(shemePrdct);
						}
						avaiableSchemeChoices.add(scheme);
					}
					prdctPriceSchemeDtl.setSchemeChoiceLst(avaiableSchemeChoices);
				}
			}
		}
				HibernateUtils.getCustomeTrasationManager().commitTx();	
		logger.info("ProductServiceImpl :: getPrdctSchemePriceDtl :: db activity end");
		}
		catch(Exception e){
			logger.info("ProductServiceImpl :: getPrdctSchemePriceDtl :: exception ### "+e.getMessage());
			HibernateUtils.getCustomeTrasationManager().rollBackTx();
			throw e;
		}
		finally{
			HibernateUtils.CloseCustomeTransationManager();
		}
		logger.info("ProductServiceImpl :: getPrdctSchemePriceDtl :: begin");
		return prdctPriceSchemeMap;
	}

	@Override
	public ObservableList<application.cdms.models.Choice> getAllGroupNm() {
		logger.info("ProductServiceImpl :: getAllGroupNm :: begin");
		logger.info("ProductServiceImpl :: getAllGroupNm :: db activity start");
		HibernateUtils.getCustomeTrasationManager().initTx();
		List<BeverageProductGroupDtl> beveProductCateList = this.findAllEntity(BeverageProductGroupDtl.class);
		ObservableList<Choice> groupList = FXCollections.observableArrayList();
		for (BeverageProductGroupDtl group : beveProductCateList) {
			groupList.add(new Choice(group.getGroupName(), group.getGroupName()));
		}
		logger.info("ProductServiceImpl :: getAllGroupNm :: all group list count ### "+groupList.size());
		HibernateUtils.commitCloseCustomeTransationManager();
		logger.info("ProductServiceImpl :: getAllGroupNm :: end");
		return groupList;
	}
	
	@Override
	public ObservableList<ProductGroup> getAllProductGroup() {
		logger.info("ProductServiceImpl :: getAllProductGroup :: begin");
		logger.info("ProductServiceImpl :: getAllProductGroup :: db activity start");
		HibernateUtils.getCustomeTrasationManager().initTx();
		List<BeverageProductGroupDtl> beveProductCateList = this.findAllEntity(BeverageProductGroupDtl.class);
		ObservableList<ProductGroup> groupList = FXCollections.observableArrayList();
		for (BeverageProductGroupDtl group : beveProductCateList) {
			groupList.add(BeanTransformer.getProductGroupBean(group));
		}
		logger.info("ProductServiceImpl :: getAllProductGroup :: all product group count ### "+groupList.size());
		HibernateUtils.commitCloseCustomeTransationManager();
		return groupList;
	}

	@Override
	public ObservableList<Product> productLstByParams(Product product) {
		logger.info("ProductServiceImpl :: productLstByParams :: begin");
		ObservableList<Product> productList=null;
		try{
			BeverageProductCategory beverageProductCategory = new BeverageProductCategory();
			beverageProductCategory.setGroupName(product.getGroupNm());
			List<BeverageProductCategory> productEntityList = productDao.getProductListByParams(beverageProductCategory);
			logger.info("ProductServiceImpl :: productLstByParams :: db response recieved");
			productList = FXCollections.observableArrayList();
			if(productEntityList!=null){
				for (BeverageProductCategory beverage : productEntityList) {
					productList.add(BeanTransformer.getProductBean(beverage,Initialization.EAGER));
				}
			}
			HibernateUtils.getCustomeTrasationManager().commitTx();
			logger.info("ProductServiceImpl :: productLstByParams :: product list upon search parameter ### "+productList.size());
		}
		catch(Exception e){
			logger.fatal("ProductServiceImpl :: productLstByParams :: exception ### "+e.getMessage());
			HibernateUtils.getCustomeTrasationManager().rollBackTx();
			e.printStackTrace();
		}
		finally{
			HibernateUtils.CloseCustomeTransationManager();
		}
		logger.info("ProductServiceImpl :: productLstByParams :: end");
		return productList;
	}
	
	@Override
	public String updateSchemeNPrice(List<ProductPriceSchemeDtl> prodSchemPriceDtlList){
		logger.info("ProductServiceImpl :: updateSchemeNPrice :: begin");
		try{
		List<Scheme> schms = prodSchemPriceDtlList.get(0).getSchemeChoiceLst();
		StringBuilder dbScriptBuildr = new StringBuilder();
		int outI=0;
		int outSize=schms.size();
		for(Scheme sc : schms){
			List<SchemeProduct> SchProds = sc.getSchemePrdcts();
			int i=0;
			int size = SchProds.size();
			for(SchemeProduct SchP : SchProds){
				if(SchP.getProduct()!=null){
					dbScriptBuildr.append(SchP.getProduct().getProductCd()+"|"+SchP.getPrdctOrGroupBSQty());
				}
				else{dbScriptBuildr.append(SchP.getGroupName().getGroupName()+"|"+SchP.getPrdctOrGroupBSQty());
				}
			i++;
				if(i!=size){
					dbScriptBuildr.append("^");
				}
			}
			outI++;
			if(outI!=outSize){
				dbScriptBuildr.append("~");
			}
		}
		logger.info("ProductServiceImpl :: updateSchemeNPrice :: Scheme script ### "+dbScriptBuildr.toString());
		
		List<PrdctCurrPriceScheme> prdctCurrPriceLst = new ArrayList<>();
		for(ProductPriceSchemeDtl prdPricScmeDtl:prodSchemPriceDtlList){
			PrdctCurrPriceScheme prdCurrPriceSch = new PrdctCurrPriceScheme();
			prdCurrPriceSch.setProduct(BeanTransformer.getProductEntity(prdPricScmeDtl.getProduct()));
			prdCurrPriceSch.setPrdctCurrPrice(prdPricScmeDtl.getPrdctPrice());
			prdCurrPriceSch.setPrdctCurrScheme(dbScriptBuildr.toString());
			prdCurrPriceSch.setPriceLastchangeDt(Calendar.getInstance());
			prdCurrPriceSch.setPrdctCurrMrp(0.0);
			prdctCurrPriceLst.add(prdCurrPriceSch);
		}
		logger.info("ProductServiceImpl :: updateSchemeNPrice :: start db activtiy");
		HibernateUtils.getCustomeTrasationManager().initTx();
		for(PrdctCurrPriceScheme prdctCurrPriceScheme : prdctCurrPriceLst){
			logger.info("ProductServiceImpl :: updateSchemeNPrice :: saving prdct current price ### "+prdctCurrPriceScheme.getPrdctCurrScheme());
			this.saveEntity(prdctCurrPriceScheme);
		}
		HibernateUtils.getCustomeTrasationManager().commitTx();
		logger.info("ProductServiceImpl :: updateSchemeNPrice :: end db activtiy");
		}
		catch(Exception e){
			logger.info("ProductServiceImpl :: updateSchemeNPrice :: exception ### "+e.getMessage());
			HibernateUtils.getCustomeTrasationManager().rollBackTx();
			throw e;
		}
		finally{
			HibernateUtils.CloseCustomeTransationManager();
		}
		return "Success";
	}
	
	@Override
	public Map<String, ProductPriceSchemeDtl> latestAllSchemePrice() throws Exception {
		logger.info("ProductServiceImpl :: latestAllSchemePrice :: begin");
		Map<String, ProductPriceSchemeDtl> priceSchemeMap =null;
		try{
			logger.info("ProductServiceImpl :: latestAllSchemePrice :: db activity start");
			HibernateUtils.getCustomeTrasationManager().initTx();
			List<PrdctCurrPriceScheme> priceSchemEntityLst = productDao.latestAllSchemePrice();
			logger.info("ProductServiceImpl :: latestAllSchemePrice :: db response recieved");
			//List<Scheme> avaiableSchemeChoices;
			ProductPriceSchemeDtl prdctPriceSchemeDtl = null;
			// scheme split
			if (priceSchemEntityLst != null && priceSchemEntityLst.size() > 0) {
				logger.info("ProductServiceImpl :: latestAllSchemePrice :: price scheme list size ### "+priceSchemEntityLst.size());
				priceSchemeMap = new HashMap<>();
				for (PrdctCurrPriceScheme prdctCurrPriceScheme : priceSchemEntityLst) {
					prdctPriceSchemeDtl = new ProductPriceSchemeDtl();
					prdctPriceSchemeDtl.setPrdctPrice(prdctCurrPriceScheme.getPrdctCurrPrice());
					prdctPriceSchemeDtl.setProduct(BeanTransformer.getProductBean(prdctCurrPriceScheme.getProduct(), Initialization.LAZY));
					prdctPriceSchemeDtl.setDbSchemeScript(prdctCurrPriceScheme.getPrdctCurrScheme());
					priceSchemeMap.put(prdctPriceSchemeDtl.getProduct().getProductCd(), prdctPriceSchemeDtl);
					
					/*	if (prdctCurrPriceScheme.getPrdctCurrScheme() != null && !prdctCurrPriceScheme.getPrdctCurrScheme().equals("")) {
						String[] schemesOptionArr = prdctCurrPriceScheme.getPrdctCurrScheme().split("\\~");
						avaiableSchemeChoices = new ArrayList<>();
						for (String schemeOptStr : schemesOptionArr) {
							String[] schemesArr = schemeOptStr.split("\\^");
							List<SchemeProduct> schemeProducts = new ArrayList<>();
							Scheme scheme = new Scheme();
							scheme.setSchemePrdcts(schemeProducts);
							for (String schemeStr : schemesArr) {
								String[] schemeQtyArr = schemeStr.split("\\|");
								String prdctSchemeStr = schemeQtyArr[0];
								String prdctQtySchemeStr = schemeQtyArr[1];
								SchemeProduct shemePrdct = new SchemeProduct();
								if (prdctSchemeStr.startsWith("PROD")) {
									BeverageProductCategory prodCat = this.findById(BeverageProductCategory.class,
											prdctSchemeStr);
									Product product = BeanTransformer.getProductBean(prodCat, Initialization.LAZY);
									shemePrdct.setProduct(product);
								} else {
									ProductGroup productGroup = new ProductGroup();
									productGroup.setGroupName(prdctSchemeStr);
									shemePrdct.setGroupName(productGroup);
								}
								shemePrdct.setPrdctOrGroupBSQty(Long.parseLong(prdctQtySchemeStr));
								schemeProducts.add(shemePrdct);
							}
							avaiableSchemeChoices.add(scheme);
						}
						prdctPriceSchemeDtl.setSchemeChoiceLst(avaiableSchemeChoices);
					}*/
				}
			}
			HibernateUtils.getCustomeTrasationManager().commitTx();
			logger.info("ProductServiceImpl :: latestAllSchemePrice :: db activity end");
		}
		catch(Exception e){
			logger.fatal("ProductServiceImpl :: latestAllSchemePrice :: exception ### "+e.getMessage());
			HibernateUtils.getCustomeTrasationManager().rollBackTx();
			throw e;
		}
		finally{
			HibernateUtils.CloseCustomeTransationManager();
		}
		logger.info("ProductServiceImpl :: latestAllSchemePrice :: end");
		return priceSchemeMap;
	}
	@Override
	public Sale sellProduct(Sale saleBean)throws Exception{
		logger.info("ProductServiceImpl :: sellProduct :: begin");
		try{
		SaleDtl saleDtl = BeanTransformer.getSaleDtlEntity(saleBean);
		
		HashMap<String,ProductInstantStockDtl> instantMaps = new HashMap<>();
		logger.info("ProductServiceImpl :: sellProduct :: instant stock listing start of invoice product");
		for(SaleInvoicesPrdctDtl saleInvoicesPrdctDtl : saleDtl.getInvoicedPrdctDtlsSet()){
			for(SalePrdctDtl salprdctDtl : saleInvoicesPrdctDtl.getSalePrdctSet()){
				ProductInstantStockDtl instantStock = new ProductInstantStockDtl();
				instantStock.setPrdctGroup(saleInvoicesPrdctDtl.getBeverageGroup());
				instantStock.setProduct(salprdctDtl.getPrdct());
				instantStock.setPrdctOutschemeSaleQty(salprdctDtl.getPrdctSallingQty());
				instantStock.setPackatePrice(saleInvoicesPrdctDtl.getSysGrossNetPerCs()+saleInvoicesPrdctDtl.getSchemeDiscntPerCs());
				instantStock.setPrdctStockCurrTime(Calendar.getInstance());
				instantMaps.put(salprdctDtl.getPrdct().getProductCd(),instantStock);
			}
		}
		logger.info("ProductServiceImpl :: sellProduct :: instant stock listing start of scheme product");
		if("Y".equalsIgnoreCase(saleDtl.getIsSchemeAlloted())){
		for(SalePrdctSchemeDtl salescheme : saleDtl.getSalePrdctSchemeDtlSet()){
			ProductInstantStockDtl instantStock = instantMaps.get(salescheme.getSchemePrdct().getProductCd());
			if(instantStock==null){
				instantStock = new ProductInstantStockDtl();
				BeverageProductGroupDtl grop = new BeverageProductGroupDtl();
				grop.setGroupName(salescheme.getSchemePrdct().getGroupName());
				instantStock.setPrdctGroup(grop);
				instantStock.setProduct(salescheme.getSchemePrdct());
				instantStock.setPrdctStockCurrTime(Calendar.getInstance());
				instantMaps.put(salescheme.getSchemePrdct().getProductCd(), instantStock);
			}
			instantStock.setPrdctDistributedSchemeQtyBs(instantStock.getPrdctDistributedSchemeQtyBs()+salescheme.getGivenSchemeQtyBs());
		}
		}
		logger.info("ProductServiceImpl :: sellProduct :: instant stock listing start of breakage product");
		if("Y".equalsIgnoreCase(saleDtl.getIsBreakageReturn())){
		for(SaleBreakageDtl saleBrekage : saleDtl.getSaleBrekageLst()){
			ProductInstantStockDtl instantStock = instantMaps.get(saleBrekage.getProductCd().getProductCd());
			if(instantStock==null){
				instantStock = new ProductInstantStockDtl();
				BeverageProductGroupDtl grop = new BeverageProductGroupDtl();
				grop.setGroupName(saleBrekage.getProductCd().getGroupName());
				instantStock.setPrdctGroup(grop);
				instantStock.setProduct(saleBrekage.getProductCd());
				instantStock.setPrdctStockCurrTime(Calendar.getInstance());
				instantMaps.put(saleBrekage.getProductCd().getProductCd(), instantStock);
			}
			instantStock.setPrdctBreakageQtyBs(instantStock.getPrdctBreakageQtyBs()+saleBrekage.getBreakageBs());
		}
		}
		logger.info("ProductServiceImpl :: sellProduct :: db activity start");
		HibernateUtils.getCustomeTrasationManager().initTx();
		logger.info("ProductServiceImpl :: sellProduct :: salling saving start");
		this.saveEntity(saleDtl);
		HibernateUtils.getCustomeTrasationManager().getSession().flush();
		logger.info("ProductServiceImpl :: sellProduct :: salling saving end");
		logger.info("ProductServiceImpl :: sellProduct :: instant stock saving start");
		Iterator<ProductInstantStockDtl> itr= instantMaps.values().iterator();
		while(itr.hasNext()){
			ProductInstantStockDtl instantStock=itr.next();
			instantStock.setSaleInvoiceNumber(saleDtl.getSaleInvoiceNo());
			instantStock.setPrdctStockCurrTime(Calendar.getInstance());
			this.saveEntity(instantStock);
		}
		logger.info("ProductServiceImpl :: sellProduct :: instant stock saving end ");
		
		saleBean.setSaleInvoiceNo(saleDtl.getSaleInvoiceNo());
		HibernateUtils.getCustomeTrasationManager().commitTx();
		logger.info("ProductServiceImpl :: sellProduct :: db activity end");
		logger.info("ProductServiceImpl :: sellProduct :: end");
		}
		catch(Exception e){
			logger.fatal("ProductServiceImpl :: sellProduct :: exception ### "+e.getMessage());
			HibernateUtils.getCustomeTrasationManager().rollBackTx();
			throw e;
		}
		finally{
			HibernateUtils.CloseCustomeTransationManager();
		}
		return saleBean;
	}
	
	@Override
	public PurchaseDtls getChallanDetailByInvoice(String str,Initialization mode)throws Exception{
		logger.info("getChallanDetailByInvoice :: begin");
		logger.info("getChallanDetailByInvoice :: Invoice Number ### "+str);
		PurchaseDtls purchaseDtlsBean=null;
			try{
				PurchaseDtl purchaseDtlEntity = productDao.getChallanDetailByInvoice(str);
				if(purchaseDtlEntity!=null){
					logger.info("getChallanDetailByInvoice :: starting to get PurchaseDtl bean");
					purchaseDtlsBean=BeanTransformer.getPurchaseBean(purchaseDtlEntity,mode);
					logger.info("getChallanDetailByInvoice :: End to get PurchaseDtl bean");
				}
				HibernateUtils.getCustomeTrasationManager().commitTx();
			}
			catch(Exception e){
				throw e;
			}
			finally{
				HibernateUtils.CloseCustomeTransationManager();
			}
		logger.info("getChallanDetailByInvoice :: End");
		return purchaseDtlsBean;
	}

	@Override
	public List<PurchaseDtls> getAllChallanDetail(Initialization mode){
		logger.info("getAllChallanDetail :: begin");
		List<PurchaseDtls> purchaseDtlsList=null;
			try{
				Criteria criteria=HibernateUtils.getCustomeTrasationManager().getSession().createCriteria(PurchaseDtl.class);
				criteria.addOrder(Order.desc("challanDt"));
				criteria.setFetchMode("puchasedPrdctList", FetchMode.JOIN);
				//criteria.setFetchMode("nonBevPrdctList", FetchMode.JOIN);
				//criteria.setFetchMode("puchasedPrdctList.product", FetchMode.JOIN);
				criteria.setMaxResults(5);
				@SuppressWarnings("unchecked")
				List<PurchaseDtl> purchaseEntityList=criteria.list();
				if(purchaseEntityList!=null && purchaseEntityList.size()>0){
					purchaseDtlsList = new ArrayList<>();
					logger.info("getAllChallanDetail :: starting to get PurchaseDtl bean");
					for(PurchaseDtl purchaseDtlEntity:purchaseEntityList){
						PurchaseDtls purchaseDtlsBean=BeanTransformer.getPurchaseBean(purchaseDtlEntity,mode);
						purchaseDtlsList.add(purchaseDtlsBean);
					}
					logger.info("getAllChallanDetail :: End to get PurchaseDtl bean");
				}
				HibernateUtils.getCustomeTrasationManager().commitTx();
			}
			catch(Exception e){
				logger.fatal("Error while fetching purchase list");
				e.printStackTrace();
			}
			finally{
				HibernateUtils.CloseCustomeTransationManager();
			}
		logger.info("getAllChallanDetail :: End");
		return purchaseDtlsList;
	}
	
	@Override
	public void updatePurchasePaymentDtl(String purchaseSeq, Double paidAmt, String payMethod, String payDateStr,String payId) throws Exception {
		logger.info("updatePurchasePaymentDtl :: begin");
		logger.info("updatePurchasePaymentDtl :: purchaseSeq ### "+purchaseSeq);
		logger.info("updatePurchasePaymentDtl :: paidAmt ### "+paidAmt);
		logger.info("updatePurchasePaymentDtl :: payMethod ### "+payMethod);
		logger.info("updatePurchasePaymentDtl :: payDate  ### "+payDateStr);
		logger.info("updatePurchasePaymentDtl :: pay Id  ### "+payId);
		try{
			Calendar cal = Calendar.getInstance();
			cal.setTime(ApplicationConstant.dateformatter.parse(payDateStr));
			PurchaseDtl purchaseDtl=(PurchaseDtl) HibernateUtils.getCustomeTrasationManager().initTx().load(PurchaseDtl.class,purchaseSeq);
			if(purchaseDtl!=null){
				purchaseDtl.setPayment_dt(cal);
				purchaseDtl.setPaidAmount(paidAmt);
				purchaseDtl.setPaymentId(payId);
				purchaseDtl.setPaymentMethod(payMethod);
				HibernateUtils.getCustomeTrasationManager().getSession().merge(purchaseDtl);
			}
			HibernateUtils.getCustomeTrasationManager().commitTx();
		}
		catch(Exception e){
			logger.fatal("updatePurchasePaymentDtl :: exception ### "+e.getMessage());
			HibernateUtils.getCustomeTrasationManager().rollBackTx();
			throw e;
		}
		finally{
			HibernateUtils.CloseCustomeTransationManager();
		}
	}
	
	@Override
	public void intiateSupply(SupplyDtl supplyDtl) throws Exception{
		SupplyRecord supplyEntity = BeanTransformer.getSupplyEntity(supplyDtl);
		HibernateUtils.getCustomeTrasationManager().initTx();
		this.saveEntity(supplyEntity);
		HibernateUtils.commitCloseCustomeTransationManager();
	}

	public void updateRtnPurchaseInvoiceNumber(String challanNumber, String purchaseInvoiceNo, String saleInvoiceNo)throws Exception{
		logger.info("ProductServiceImpl :: updateRtnPurchaseInvoiceNumber :: begin");
		HibernateUtils.getCustomeTrasationManager().initTx();
		logger.info("ProductServiceImpl :: updateRtnPurchaseInvoiceNumber :: db Activity Start");
		productDao.updateRtnPurchaseInvoiceNumber(challanNumber,purchaseInvoiceNo,saleInvoiceNo);
		HibernateUtils.commitCloseCustomeTransationManager();
	}

	@Override
	public Sale storeBreakageClaimDetails(Sale saleBean) throws Exception {
		logger.info("ProductServiceImpl :: storeBreakageClaimDetails :: begin");
		try{
			SaleDtl saleDtl = BeanTransformer.getSaleDtlEntity(saleBean);
			HibernateUtils.getCustomeTrasationManager().initTx();
			HashMap<String,ProductInstantStockDtl> instantMaps = new HashMap<>();
			ClaimHeader clmHeader = new ClaimHeader();
			clmHeader.setClaimAmount(saleBean.getTotalNetActualAmount());
			clmHeader.setClaimStatus(ApplicationConstant.CLAIM_PENDING);
			clmHeader.setClaimType(Claim_Type.BREAKAGE_CLAIM.name());
			clmHeader.setInitiatedBy("CDMS");
			Calendar cal=Calendar.getInstance();
			clmHeader.setInitiationDate(cal);
			clmHeader.setGivenGlassQty(saleBean.getTotalDeliverBsGlass());
			clmHeader.setGiveCellQty(saleBean.getTotalDeliverCell());
			clmHeader.setClaimBreakPrdctsLst(new ArrayList<>());
			List<SalePrdctInvoice> prdctsList=saleBean.getInvoicedPrdctDtlsSet();
			for(SalePrdctInvoice saleProdctInvoice :prdctsList){
				ClaimBreakagePrdctDtl claimBreakagePrdctDtl = new ClaimBreakagePrdctDtl();
				Iterator<SalePrdct> itr=saleProdctInvoice.getSalePrdctSet().iterator();
				while(itr.hasNext()){
					SalePrdct salePrdctDt=itr.next();
					BeverageProductCategory beverageProductCategory=(BeverageProductCategory) HibernateUtils.getCustomeTrasationManager().getSession().load(BeverageProductCategory.class,salePrdctDt.getProduct().getProductCd());
					claimBreakagePrdctDtl.setProduct(beverageProductCategory);
					ProductInstantStockDtl instantStock = instantMaps.get(salePrdctDt.getProduct().getProductCd());
					if(instantStock==null){
						instantStock = new ProductInstantStockDtl();
						instantStock.setPrdctGroup((BeverageProductGroupDtl) HibernateUtils.getCustomeTrasationManager().getSession().load(BeverageProductGroupDtl.class,salePrdctDt.getProduct().getGroupNm()));
						instantStock.setProduct(beverageProductCategory);
						instantStock.setPrdctStockCurrTime(Calendar.getInstance());
						instantMaps.put(salePrdctDt.getProduct().getProductCd(), instantStock);
					}
					instantStock.setPrdctBreakageQtyBs(instantStock.getPrdctBreakageQtyBs()+salePrdctDt.getPrdctSallingQty());
				}
				claimBreakagePrdctDtl.setBasicAmt(saleProdctInvoice.getNetBaseAmt());
				claimBreakagePrdctDtl.setDiscountAmt(saleProdctInvoice.getSchemeDisocuntAmt());
				claimBreakagePrdctDtl.setTaxableAmt(saleProdctInvoice.getTaxableAmt());
				claimBreakagePrdctDtl.setTaxAmt(saleProdctInvoice.getCgstAmt()+saleProdctInvoice.getSgstAmt()+saleProdctInvoice.getCessAmt());
				claimBreakagePrdctDtl.setNetAmt(saleProdctInvoice.getTotalPrdctNetAmt());
				claimBreakagePrdctDtl.setProductQtyBs(saleProdctInvoice.getSellingQty());
				claimBreakagePrdctDtl.setMrp(saleProdctInvoice.getMrp());
				//claimBreakagePrdctDtl.set
				claimBreakagePrdctDtl.setHeader(clmHeader);
				//claimBreakagePrdctDtl.setCellQtyWithoutBs();
				clmHeader.getClaimBreakPrdctsLst().add(claimBreakagePrdctDtl);
				//set product breakage qty
				
			}
			logger.info("ProductServiceImpl :: storeBreakageClaimDetails :: Db Activity Start");
			this.saveEntity(saleDtl);
			logger.info("ProductServiceImpl :: storeBreakageClaimDetails :: sale Entity Saved with Invoice Number ### "+saleDtl.getSaleInvoiceNo());
			HibernateUtils.getCustomeTrasationManager().getSession().flush();
			clmHeader.setLinkedClaimDetailId(saleDtl.getSaleInvoiceNo());
			
			this.saveEntity(clmHeader);
			Iterator<ProductInstantStockDtl> itr= instantMaps.values().iterator();
			while(itr.hasNext()){
				ProductInstantStockDtl instantStock=itr.next();
				instantStock.setSaleInvoiceNumber(saleDtl.getSaleInvoiceNo());
				instantStock.setPrdctStockCurrTime(Calendar.getInstance());
				this.saveEntity(instantStock);
			}
			logger.info("ProductServiceImpl :: storeBreakageClaimDetails :: Breakage Claim Entity saved");
			HibernateUtils.getCustomeTrasationManager().commitTx();
			saleBean.setSaleInvoiceNo(saleDtl.getSaleInvoiceNo());
			logger.info("ProductServiceImpl :: storeBreakageClaimDetails :: Db Activity end");
		}
		catch(Exception e){
			logger.fatal("ProductServiceImpl :: storeBreakageClaimDetails :: exception ### "+e.getMessage());
			HibernateUtils.getCustomeTrasationManager().rollBackTx();
			throw e;
		}
		finally{
			HibernateUtils.CloseCustomeTransationManager();
		}
		return saleBean;
	}

	@Override
	public ObservableList<FirmSeller> getAllSellerFirmDtl() {
		HibernateUtils.getCustomeTrasationManager().initTx();
		List<FirmOrSellerDtl> sellerEntityLst = this.findAllEntity(FirmOrSellerDtl.class);
		ObservableList<FirmSeller> sellerLst = FXCollections.observableArrayList();
		for(FirmOrSellerDtl frmEntity : sellerEntityLst){
			sellerLst.add(BeanTransformer.getSellerBean(frmEntity));
		}
		HibernateUtils.commitCloseCustomeTransationManager();
		return sellerLst;
	}
	@SuppressWarnings("unchecked")
	@Override
	public ObservableList<NonBeveragePrdct> nonBproductList() {
		logger.info("ProductServiceImpl :: nonBproductList :: begin");
		Criteria ct = HibernateUtils.getCustomeTrasationManager().initTx().createCriteria(NonBeverageProductCatergory.class);
		ct.addOrder(Order.asc("productNm"));
		List<NonBeverageProductCatergory> NonBProductCateList = ct.list();
		ObservableList<NonBeveragePrdct> productList = FXCollections.observableArrayList();
		for (NonBeverageProductCatergory Nonbeverage : NonBProductCateList) {
			productList.add(BeanTransformer.getNonBeverageProductBean(Nonbeverage));
		}
		HibernateUtils.commitCloseCustomeTransationManager();
		//Collections.sort(productList);
		logger.info("ProductServiceImpl :: nonBproductList :: product List count ### "+productList.size());
		logger.info("ProductServiceImpl :: nonBproductList :: end");
		return productList;
	}
	
	@Override
	public NonBeverageSale returnEmptyInvoice(NonBeverageSale nbSale){
		logger.info("ProductServiceImpl :: returnEmptyInvoice :: begin");
		try{
		HibernateUtils.getCustomeTrasationManager().initTx();
		logger.info("ProductServiceImpl :: returnEmptyInvoice :: non beverage sale saving start");
		NonBeverageSaleDtl nbSaleEntity = BeanTransformer.getNonBeverageSaleEntity(nbSale);
		this.saveEntity(nbSaleEntity);
		HibernateUtils.getCustomeTrasationManager().commitTx();
		nbSale.setNbSaleInvoiceNo(nbSaleEntity.getNbSaleInvoiceNo());
		logger.info("ProductServiceImpl :: returnEmptyInvoice :: non beverage sale saving End");
		}
		catch(Exception e){
			logger.fatal("Exception : returnEmptyInvoice :: error "+e.getMessage());
			nbSale=null;
			throw e;
		}
		finally{
			HibernateUtils.CloseCustomeTransationManager();
		}
		return nbSale;
	}
	
	@Override
	public void updateRtnEmtpyInvoiceNumber(String challanNumber, String purchaseInvoiceNo, String nbSaleInvoiceNum)throws Exception{
		logger.info("ProductServiceImpl :: updateRtnPurchaseInvoiceNumber :: begin");
		HibernateUtils.getCustomeTrasationManager().initTx();
		logger.info("ProductServiceImpl :: updateRtnPurchaseInvoiceNumber :: db Activity Start");
		productDao.updateRtnEmtpyInvoiceNumber(challanNumber,purchaseInvoiceNo,nbSaleInvoiceNum);
		HibernateUtils.commitCloseCustomeTransationManager();
	}
	
	public List<PurchaseDtls> getAllChallanDtlsForForRtnEmpty(Initialization mode){
		logger.info("getAllChallanDtlsForForRtnEmpty :: begin");
		List<PurchaseDtls> purchaseDtlsList=null;
			try{
				Criteria criteria=HibernateUtils.getCustomeTrasationManager().getSession().createCriteria(PurchaseDtl.class);
				Criterion cn1=Restrictions.isNull("rtnEmptyInvoiceNo");
				Criterion cn2 = Restrictions.ne("returningBottleQty",0L);
				Criterion cn3 = Restrictions.ne("returningCellQty",0L);
				Criterion cn4 = Restrictions.and(cn1, cn2,cn3);
				criteria.add(cn4);
				criteria.addOrder(Order.desc("challanDt"));
				criteria.setMaxResults(5);
				@SuppressWarnings("unchecked")
				List<PurchaseDtl> purchaseEntityList=criteria.list();
				if(purchaseEntityList!=null && purchaseEntityList.size()>0){
					purchaseDtlsList = new ArrayList<>();
					logger.info("getAllChallanDtlsForForRtnEmpty :: starting to get PurchaseDtl bean");
					for(PurchaseDtl purchaseDtlEntity:purchaseEntityList){
						PurchaseDtls purchaseDtlsBean=BeanTransformer.getPurchaseBean(purchaseDtlEntity,mode);
						purchaseDtlsList.add(purchaseDtlsBean);
					}
					logger.info("getAllChallanDtlsForForRtnEmpty :: End to get PurchaseDtl bean");
				}
				HibernateUtils.getCustomeTrasationManager().commitTx();
			}
			catch(Exception e){
				logger.fatal("Error while fetching purchase list");
				e.printStackTrace();
			}
			finally{
				HibernateUtils.CloseCustomeTransationManager();
			}
		logger.info("getAllChallanDtlsForForRtnEmpty :: End");
		return purchaseDtlsList;
	}
}
package application.cdms.transformer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import application.cdms.db.entities.ApplicationUser;
import application.cdms.db.entities.BeverageProductCategory;
import application.cdms.db.entities.BeverageProductGroupDtl;
import application.cdms.db.entities.CstmrAcntsDtl;
import application.cdms.db.entities.CstmrDtl;
import application.cdms.db.entities.CstmrLedgerCategory;
import application.cdms.db.entities.FillingQtyCategory;
import application.cdms.db.entities.FirmOrSellerDtl;
import application.cdms.db.entities.FlavourCategory;
import application.cdms.db.entities.HsnTaxStructure;
import application.cdms.db.entities.NonBeveragePrdctSaleDtl;
import application.cdms.db.entities.NonBeverageProductCatergory;
import application.cdms.db.entities.NonBeverageSaleDtl;
import application.cdms.db.entities.PackingNameCategory;
import application.cdms.db.entities.PackingQtyCategory;
import application.cdms.db.entities.PrdctBreakageDtl;
import application.cdms.db.entities.ProductTypes;
import application.cdms.db.entities.PurchaseDtl;
import application.cdms.db.entities.PurchaseNonBeveragePrdctDtl;
import application.cdms.db.entities.PurchasePrdctDtl;
import application.cdms.db.entities.RouteMapDtl;
import application.cdms.db.entities.SaleBreakageDtl;
import application.cdms.db.entities.SaleDtl;
import application.cdms.db.entities.SaleInvoicesPrdctDtl;
import application.cdms.db.entities.SalePrdctDtl;
import application.cdms.db.entities.SalePrdctSchemeDtl;
import application.cdms.db.entities.SupplyPrdctDtl;
import application.cdms.db.entities.SupplyRecord;
import application.cdms.db.entities.SupplyVehicleDtl;
import application.cdms.db.entities.TerritoryAreaDtl;
import application.cdms.models.CstmrLedger;
import application.cdms.models.CustomerDtl;
import application.cdms.models.FillingQty;
import application.cdms.models.FirmSeller;
import application.cdms.models.Flavour;
import application.cdms.models.HsnTax;
import application.cdms.models.LedgerType;
import application.cdms.models.LoginUser;
import application.cdms.models.NonBeveragePrdct;
import application.cdms.models.NonBeveragePrdctSale;
import application.cdms.models.NonBeverageSale;
import application.cdms.models.PackingName;
import application.cdms.models.PackingQty;
import application.cdms.models.Product;
import application.cdms.models.ProductBreakageDtl;
import application.cdms.models.ProductCatagory;
import application.cdms.models.ProductGroup;
import application.cdms.models.PurchaseDtls;
import application.cdms.models.PurchaseProductDtl;
import application.cdms.models.RouteDtl;
import application.cdms.models.Sale;
import application.cdms.models.SaleBrekageDtl;
import application.cdms.models.SalePrdct;
import application.cdms.models.SalePrdctInvoice;
import application.cdms.models.SaleScheme;
import application.cdms.models.SupplyDtl;
import application.cdms.models.SupplyPrdcts;
import application.cdms.models.Territory;
import application.cdms.models.Vehicle;

public class BeanTransformer {
	
	private static SimpleDateFormat formatter=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");//.SSS
	
	private static final String appendZeroTime=" 00:00:00.000";
	
	public static LoginUser getLoginUserBean(ApplicationUser loginUserEntity){
		LoginUser loginUser = new LoginUser();
		loginUser.setUsername(loginUserEntity.getUserNm());
		//loginUser.setPassword(loginUserEntity.get);
		loginUser.setRole(loginUserEntity.getRole());
		return loginUser;
	}
	
	public static FillingQty getfillingQtyBean(FillingQtyCategory fillingQtyCategory){
		FillingQty fillingQty = new FillingQty();
		fillingQty.setFillingQtyCd(fillingQtyCategory.getFillingQtyCd());
		fillingQty.setFillingQtyMl(fillingQtyCategory.getFillingQtyMl());
		fillingQty.setFillingAddedBy(fillingQtyCategory.getFillingQtyAddedBy());
		//Calendar cal=
		fillingQty.setFillingQtyAddedDt(formatter.format(fillingQtyCategory.getFillingQtyAddedDt().getTime()));
		return fillingQty;
	}
	public static FillingQtyCategory getFillingQtyEntity(FillingQty fillingQty){
		FillingQtyCategory fillingQtyCategory = new FillingQtyCategory();
		fillingQtyCategory.setFillingQtyCd(fillingQty.getFillingQtyCd());
		fillingQtyCategory.setFillingQtyMl(fillingQty.getFillingQtyMl());
		fillingQtyCategory.setFillingQtyAddedBy(fillingQty.getFillingAddedBy());
		if(fillingQty.getFillingQtyAddedDt()!=null && !fillingQty.getFillingQtyAddedDt().equals("")){
			Calendar cal = Calendar.getInstance();
			try {
				cal.setTime(formatter.parse(fillingQty.getFillingQtyAddedDt()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			fillingQtyCategory.setFillingQtyAddedDt(cal);	
		}
		return fillingQtyCategory;
	}
	public static Flavour getFlavrBean(FlavourCategory flavourCategory){
		Flavour flavour = new Flavour();
		flavour.setFlavrCd(flavourCategory.getFlavrCd());
		flavour.setFlavrName(flavourCategory.getFlavrName());
		flavour.setFalvrAddedBy(flavourCategory.getFalvrAddedBy());
		flavour.setFlavrAddedDt(formatter.format(flavourCategory.getFlavrAddedDt().getTime()));
		return flavour;
	}
	public static FlavourCategory getFlavrEntity(Flavour flavour){
		FlavourCategory flavourCategory = new FlavourCategory();
		flavourCategory.setFlavrCd(flavour.getFlavrCd());
		flavourCategory.setFlavrName(flavour.getFlavrName());
		flavourCategory.setFalvrAddedBy(flavour.getFalvrAddedBy());
		if(flavour.getFlavrAddedDt()!=null && !flavour.getFlavrAddedDt().equals("")){
			Calendar cal = Calendar.getInstance();
			try {
				cal.setTime(formatter.parse(flavour.getFlavrAddedDt()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			flavourCategory.setFlavrAddedDt(cal);	
		}
		return flavourCategory;
	}
	public static PackingName getPackingNameBean(PackingNameCategory packingNameCategory){
		PackingName packingName = new PackingName();
		packingName.setPackingNameCd(packingNameCategory.getPackingNameCd());
		packingName.setPackingName(packingNameCategory.getPackingName());
		packingName.setPakingAddedBy(packingNameCategory.getPakingAddedBy());
		packingName.setPakingAddedDt(formatter.format(packingNameCategory.getPakingAddedDt().getTime()));
		return packingName;
	}
	public static PackingNameCategory getPackingNameEntity(PackingName packingName){
		PackingNameCategory packingNameCategory = new PackingNameCategory();
		packingNameCategory.setPackingNameCd(packingName.getPackingNameCd());
		packingNameCategory.setPackingName(packingName.getPackingName());
		packingNameCategory.setPakingAddedBy(packingName.getPakingAddedBy());
		if(packingName.getPakingAddedDt()!=null && !packingName.getPakingAddedDt().equals("")){
			Calendar cal = Calendar.getInstance();
			try {
				cal.setTime(formatter.parse(packingName.getPakingAddedDt()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			packingNameCategory.setPakingAddedDt(cal);
		}
		return packingNameCategory;
	}
	public static PackingQty getPackingQtyBean(PackingQtyCategory packingQtyCategory){
		PackingQty packingQty = new PackingQty();
		packingQty.setPackingQtyCd(packingQtyCategory.getPackingQtyCd());
		packingQty.setPackingQuantity(packingQtyCategory.getPackingQuantity());
		packingQty.setPackingQtyAddedBy(packingQtyCategory.getPackingQtyAddedBy());
		packingQty.setPackingQtyAddedDt(formatter.format(packingQtyCategory.getPackingQtyAddedDt().getTime()));
		return packingQty;
	}
	public static PackingQtyCategory getPackingQtyEntity(PackingQty packingQty){
		PackingQtyCategory packingQtyCategory = new PackingQtyCategory();
		packingQtyCategory.setPackingQtyCd(packingQty.getPackingQtyCd());
		packingQtyCategory.setPackingQuantity(packingQty.getPackingQuantity());
		packingQtyCategory.setPackingQtyAddedBy(packingQty.getPackingQtyAddedBy());
		if(packingQty.getPackingQtyAddedDt()!=null && !packingQty.getPackingQtyAddedDt().equals("")){
			Calendar cal = Calendar.getInstance();
			try {
				cal.setTime(formatter.parse(packingQty.getPackingQtyAddedDt()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			packingQtyCategory.setPackingQtyAddedDt(cal);
		}
		return packingQtyCategory;
	}
	public static ProductTypes getProductTypeEntity(ProductCatagory prodctcat){
		ProductTypes productEnt = null;
		if(prodctcat instanceof Product){
			productEnt=getProductEntity((Product)prodctcat);
		}
		else if(prodctcat instanceof NonBeveragePrdct){
			productEnt=getNonBeverageProductEntity((NonBeveragePrdct) prodctcat);
		}
		return productEnt;
	}
	public static BeverageProductCategory getProductEntity(Product product){
		BeverageProductCategory beverageProductCategory = new BeverageProductCategory();
		beverageProductCategory.setProductCd(product.getProductCd());
		beverageProductCategory.setProductAddedBy(product.getProductAddedBy());
		if(product.getProductAddedDt()!=null && !product.getProductAddedDt().equals("")){
			Calendar cal = Calendar.getInstance();
			String time=product.getProductAddedDt();
			if(time.length()<=10){
				time=time+appendZeroTime;
			}
			try {
				cal.setTime(formatter.parse(time));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			beverageProductCategory.setProductAddedDt(cal);
		}
		beverageProductCategory.setGroupName(product.getGroupNm());
		beverageProductCategory.setProductNm(product.getProductNm());
		beverageProductCategory.setFillingQty(getFillingQtyEntity(product.getFillingQty()));
		beverageProductCategory.setFlavr(getFlavrEntity(product.getFlavour()));
		beverageProductCategory.setPacking(getPackingNameEntity(product.getPacking()));
		beverageProductCategory.setPackingQty(getPackingQtyEntity(product.getPackingQty()));
		beverageProductCategory.setHsn(getHsnTaxEntity(product.getHsnTax()));
		return beverageProductCategory;
	}
	public static NonBeverageProductCatergory getNonBeverageProductEntity(NonBeveragePrdct product){
		NonBeverageProductCatergory nonBeverageProductCategory = new NonBeverageProductCatergory();
		nonBeverageProductCategory.setProductCd(product.getProductCd());
		//beverageProductCategory.setProductAddedBy(product.getProductAddedBy());
		if(product.getProductAddedDt()!=null && !product.getProductAddedDt().equals("")){
			Calendar cal = Calendar.getInstance();
			String time=product.getProductAddedDt();
			if(time.length()<=10){
				time=time+appendZeroTime;
			}
			try {
				cal.setTime(formatter.parse(time));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			nonBeverageProductCategory.setProductAddedDt(cal);
		}
		nonBeverageProductCategory.setGroupName(product.getGroupName());
		nonBeverageProductCategory.setProductNm(product.getProductNm());
		nonBeverageProductCategory.setHsn(getHsnTaxEntity(product.getHsnTax()));
		return nonBeverageProductCategory;
	}
	public static Product getProductBean(BeverageProductCategory beverageProductCategory,Initialization iniMode){
		Product product = new Product();
		product.setProductCd(beverageProductCategory.getProductCd());
		product.setProductNm(beverageProductCategory.getProductNm());
		product.setGroupNm(beverageProductCategory.getGroupName());
		if(Initialization.EAGER.name().equals(iniMode.name())){
			product.setFillingQty(getfillingQtyBean(beverageProductCategory.getFillingQty()));
			product.setFlavour(getFlavrBean(beverageProductCategory.getFlavr()));
			product.setPacking(getPackingNameBean(beverageProductCategory.getPacking()));
			product.setPackingQty(getPackingQtyBean(beverageProductCategory.getPackingQty()));
			product.setHsnTax(getHsnTaxBean(beverageProductCategory.getHsn()));
		}
		product.setProductAddedBy(beverageProductCategory.getProductAddedBy());
		product.setProductAddedDt(formatter.format(beverageProductCategory.getProductAddedDt().getTime()));
		return product;
	}
	public static PrdctBreakageDtl getProductBreakageEntity(ProductBreakageDtl productBreakageDtl){
		PrdctBreakageDtl prdctbreakageDtlEntity = new PrdctBreakageDtl();
		if(productBreakageDtl.getBreakageDt()!=null && !productBreakageDtl.getBreakageDt().equals("")){
			Calendar cal = Calendar.getInstance();
			String time=productBreakageDtl.getBreakageDt();
			if(time.length()<=10){
				time=time+appendZeroTime;
			}
			try {
				cal.setTime(formatter.parse(time));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			prdctbreakageDtlEntity.setBreakageDt(cal);
		}
		prdctbreakageDtlEntity.setBreakageSeq(productBreakageDtl.getBreakageId());
		prdctbreakageDtlEntity.setBreakageSource(productBreakageDtl.getBreakageSource());
		prdctbreakageDtlEntity.setBurstBs(productBreakageDtl.getBurst());
		prdctbreakageDtlEntity.setOpenMouthBs(productBreakageDtl.getOpenMouth());
		prdctbreakageDtlEntity.setProduct(getProductEntity(productBreakageDtl.getProduct()));
		prdctbreakageDtlEntity.setShortageBs(productBreakageDtl.getShortage());
		prdctbreakageDtlEntity.setSealPackShortageBs(productBreakageDtl.getSealPackShortage());
		prdctbreakageDtlEntity.setLeakageBs(productBreakageDtl.getLeakage());
		return prdctbreakageDtlEntity;
	}
	public static PurchasePrdctDtl getPurchaseProductEntity(PurchaseProductDtl purchaseProductDtl){
		PurchasePrdctDtl purchasePrdctDtlEntity = new PurchasePrdctDtl();
		purchasePrdctDtlEntity.setProduct((BeverageProductCategory) getProductTypeEntity(purchaseProductDtl.getProduct()));
		purchasePrdctDtlEntity.setPurchasePrdctQty(purchaseProductDtl.getProduct_qty());
		purchasePrdctDtlEntity.setPerCsBasePrice(purchaseProductDtl.getPerPacketBasePrice());
		purchasePrdctDtlEntity.setPrdctTotalBaseAmt(purchaseProductDtl.getTotalBaseAmt());
		purchasePrdctDtlEntity.setPrdctTotalDiscountAmt(purchaseProductDtl.getDiscountAmt());
		purchasePrdctDtlEntity.setPrdctTotalTaxableAmt(purchaseProductDtl.getNetTaxableAmt());
		purchasePrdctDtlEntity.setPrdctTotalCGST(purchaseProductDtl.getTotalPrdctCGSTAmt());
		purchasePrdctDtlEntity.setPrdctTotalSGST(purchaseProductDtl.getTotalPrdctSGSTAmt());
		purchasePrdctDtlEntity.setPrdctTotalIGST(purchaseProductDtl.getTotalPrdctIGSTAmt());
		purchasePrdctDtlEntity.setPrdctTotalCess(purchaseProductDtl.getTotalPrdctCessAmt());
		purchasePrdctDtlEntity.setPerCsNetPrice(purchaseProductDtl.getNetPerCsPrice());
		purchasePrdctDtlEntity.setPrdctNetAmt(purchaseProductDtl.getNetPrdctAmnt());
		
		purchasePrdctDtlEntity.setPrdctBreakageDtl(getProductBreakageEntity(purchaseProductDtl.getBraekageDtl()));
		if(purchaseProductDtl.getRecieveDt()!=null && !purchaseProductDtl.getRecieveDt().equals("")){
			String time = purchaseProductDtl.getRecieveDt();
			Calendar calendar = Calendar.getInstance();
			try {
				calendar.setTime(formatter.parse(time));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			purchasePrdctDtlEntity.setPrdctRecievedDt(calendar);
		}
		purchasePrdctDtlEntity.setPurchasedPrdctSeqNo(purchaseProductDtl.getPurchaseProductId());
		purchasePrdctDtlEntity.setMrp(purchaseProductDtl.getMrp());
	return purchasePrdctDtlEntity;
	}
	public static PurchaseNonBeveragePrdctDtl getPurchaseNonBeverageEntity(PurchaseProductDtl purchaseProductDtl){
		PurchaseNonBeveragePrdctDtl purchaseNonBeveragePrdctEntity = new  PurchaseNonBeveragePrdctDtl();
		purchaseNonBeveragePrdctEntity.setPurchasedPrdctSeqNo(purchaseProductDtl.getPurchaseProductId());
		purchaseNonBeveragePrdctEntity.setProduct((NonBeverageProductCatergory) getProductTypeEntity(purchaseProductDtl.getProduct()));
		purchaseNonBeveragePrdctEntity.setPurchasePrdctQty(purchaseProductDtl.getProduct_qty());
		purchaseNonBeveragePrdctEntity.setPrdctTotalBaseAmt(purchaseProductDtl.getTotalBaseAmt());
		purchaseNonBeveragePrdctEntity.setPrdctTotalDiscountAmt(purchaseProductDtl.getDiscountAmt());
		purchaseNonBeveragePrdctEntity.setPrdctTotalTaxableAmt(purchaseProductDtl.getNetTaxableAmt());
		purchaseNonBeveragePrdctEntity.setPrdctTotalCGST(purchaseProductDtl.getTotalPrdctCGSTAmt());
		purchaseNonBeveragePrdctEntity.setPrdctTotalSGST(purchaseProductDtl.getTotalPrdctSGSTAmt());
		purchaseNonBeveragePrdctEntity.setPrdctTotalIGST(purchaseProductDtl.getTotalPrdctIGSTAmt());
		purchaseNonBeveragePrdctEntity.setPrdctTotalCess(purchaseProductDtl.getTotalPrdctCessAmt());
		purchaseNonBeveragePrdctEntity.setUnitPrice(purchaseProductDtl.getUnitPrice());
		purchaseNonBeveragePrdctEntity.setPrdctNetAmt(purchaseProductDtl.getNetPrdctAmnt());
		if(purchaseProductDtl.getRecieveDt()!=null && !purchaseProductDtl.getRecieveDt().equals("")){
			String time = purchaseProductDtl.getRecieveDt();
			Calendar calendar = Calendar.getInstance();
			try {
				calendar.setTime(formatter.parse(time));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			purchaseNonBeveragePrdctEntity.setPrdctRecievedDt(calendar);
		}
		purchaseNonBeveragePrdctEntity.setPurchasedPrdctSeqNo(purchaseProductDtl.getPurchaseProductId());
		return purchaseNonBeveragePrdctEntity;
	}
	public static PurchaseDtl getPurchaseEntity(PurchaseDtls purchaseDtls){
		PurchaseDtl purchaseDtlEntity = new PurchaseDtl();
		if(purchaseDtls.getChallanDt()!=null && !purchaseDtls.getChallanDt().equals("")){
			String time = purchaseDtls.getChallanDt();
			Calendar calendar = Calendar.getInstance();
			if(time.length()<=10){
				time=time+appendZeroTime;
			}
			try {
				calendar.setTime(formatter.parse(time));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			purchaseDtlEntity.setChallanDt(calendar);
		}
		purchaseDtlEntity.setFirmName(purchaseDtls.getFirmNm());
		purchaseDtlEntity.setFirmGstnNumber(purchaseDtls.getFirmGstn());
		purchaseDtlEntity.setChallanInvoiceNo(purchaseDtls.getChallanInvoiceNumber());
		purchaseDtlEntity.setChallanNo(purchaseDtls.getChallanNumber());
		purchaseDtlEntity.setTotalChallanAmount(purchaseDtls.getChallanAmount());
		purchaseDtlEntity.setTotalDiscountAmount(purchaseDtls.getTotalDiscount());
		purchaseDtlEntity.setPaidAmount(purchaseDtls.getPaidAmount());
		purchaseDtlEntity.setPaymentId(purchaseDtls.getPaymentId());
		purchaseDtlEntity.setPaymentMethod(purchaseDtls.getPaymentMethod());
		purchaseDtlEntity.setTotalPrdctQty(purchaseDtls.getTotalpurchasedProduct());
		purchaseDtlEntity.setTotalPurchasedGlassQty(purchaseDtls.getTotalGlassQty());
		purchaseDtlEntity.setTotalReturnEmptyGlassQty(purchaseDtls.getTotalReturnGlassQty());
		purchaseDtlEntity.setBuyingBottleQty(purchaseDtls.getBuyingBottleQty());
		purchaseDtlEntity.setBuyingCellQty(purchaseDtls.getBuyingCellQty());
		purchaseDtlEntity.setReturningBottleQty(purchaseDtls.getReturingBottleQty());
		purchaseDtlEntity.setReturningCellQty(purchaseDtls.getReturningCellQty());
		purchaseDtlEntity.setPurchaseSeqNo(purchaseDtls.getPurchaseId());
		purchaseDtlEntity.setPurchaseComment(purchaseDtls.getPurchaseComment());
		List<PurchaseProductDtl> purchasedProducts =purchaseDtls.getPurchaseProductDtls();
		List<PurchaseProductDtl> nonBeverageProducts =purchaseDtls.getNonBeverageProdctsDtls();
		List<PurchasePrdctDtl> purchasedPrdctEntities = new ArrayList<PurchasePrdctDtl>();
		for(PurchaseProductDtl productDtl : purchasedProducts){
			purchasedPrdctEntities.add(getPurchaseProductEntity(productDtl));
		}
		purchaseDtlEntity.setPuchasedPrdctList(purchasedPrdctEntities);
		if(nonBeverageProducts!=null && nonBeverageProducts.size()>0){
			List<PurchaseNonBeveragePrdctDtl> nonBeveragePurchaseEntities = new ArrayList<PurchaseNonBeveragePrdctDtl>();
			for(PurchaseProductDtl productDtl : nonBeverageProducts){
				nonBeveragePurchaseEntities.add(getPurchaseNonBeverageEntity(productDtl));
			}
			purchaseDtlEntity.setNonBevPrdctList(nonBeveragePurchaseEntities);
		}
		return purchaseDtlEntity;
	}
	public static PurchaseDtls getPurchaseBean(PurchaseDtl purchaseDtlEntity,Initialization mode){
		PurchaseDtls purchaseDtlBean = new PurchaseDtls();
		purchaseDtlBean.setChallanDt(formatter.format(purchaseDtlEntity.getChallanDt().getTime()));
		purchaseDtlBean.setChallanInvoiceNumber(purchaseDtlEntity.getChallanInvoiceNo());
		purchaseDtlBean.setChallanNumber(purchaseDtlEntity.getChallanNo());
		purchaseDtlBean.setChallanAmount(purchaseDtlEntity.getTotalChallanAmount());
		purchaseDtlBean.setTotalDiscount(purchaseDtlEntity.getTotalDiscountAmount());
		purchaseDtlBean.setPaidAmount(purchaseDtlEntity.getPaidAmount());
		purchaseDtlBean.setPaymentId(purchaseDtlEntity.getPaymentId());
		purchaseDtlBean.setPaymentMethod(purchaseDtlEntity.getPaymentMethod());
		purchaseDtlBean.setTotalpurchasedProduct(purchaseDtlEntity.getTotalPrdctQty());
		purchaseDtlBean.setTotalGlassQty(purchaseDtlEntity.getTotalPurchasedGlassQty());
		purchaseDtlBean.setTotalReturnGlassQty(purchaseDtlEntity.getTotalReturnEmptyGlassQty());
		purchaseDtlBean.setPurchaseId(purchaseDtlEntity.getPurchaseSeqNo());
		purchaseDtlBean.setPurchaseComment(purchaseDtlEntity.getPurchaseComment());
		purchaseDtlBean.setFirmNm(purchaseDtlEntity.getFirmName());
		purchaseDtlBean.setFirmGstn(purchaseDtlEntity.getFirmGstnNumber());
		purchaseDtlBean.setBuyingBottleQty(purchaseDtlEntity.getBuyingBottleQty());
		purchaseDtlBean.setBuyingCellQty(purchaseDtlEntity.getBuyingCellQty());
		purchaseDtlBean.setReturingBottleQty(purchaseDtlEntity.getReturningBottleQty());
		purchaseDtlBean.setReturningCellQty(purchaseDtlEntity.getReturningCellQty());
		if(Initialization.EAGER.name().equals(mode.name())){
			List<PurchasePrdctDtl> purchasedProducts =purchaseDtlEntity.getPuchasedPrdctList();
			List<PurchaseProductDtl> purchasedPrdctBeans = new ArrayList<PurchaseProductDtl>();
				for(PurchasePrdctDtl productPurchaseDtlEntity : purchasedProducts){
					purchasedPrdctBeans.add(getPurchaseProductBean(productPurchaseDtlEntity,Initialization.LAZY));
				}
				purchaseDtlBean.setPurchaseProductDtls(purchasedPrdctBeans);
				List<PurchaseNonBeveragePrdctDtl> purchaseNonBevePrdctDtlLst = purchaseDtlEntity.getNonBevPrdctList();
				if(purchaseNonBevePrdctDtlLst!=null && !purchaseNonBevePrdctDtlLst.isEmpty()){
					List<PurchaseProductDtl> purchasedNbPrdctBeans = new ArrayList<PurchaseProductDtl>();
					for(PurchaseNonBeveragePrdctDtl nonBve:purchaseNonBevePrdctDtlLst){
						purchasedNbPrdctBeans.add(getPurchaseNonBeveragePrdctBean(nonBve, Initialization.LAZY));
					}
					
				}
		}
		else if(Initialization.FULL_EAGER.name().equals(mode.name())){
			List<PurchasePrdctDtl> purchasedProducts =purchaseDtlEntity.getPuchasedPrdctList();
			List<PurchaseProductDtl> purchasedPrdctBeans = new ArrayList<PurchaseProductDtl>();
			for(PurchasePrdctDtl productPurchaseDtlEntity : purchasedProducts){
				purchasedPrdctBeans.add(getPurchaseProductBean(productPurchaseDtlEntity,Initialization.EAGER));
			}
			purchaseDtlBean.setPurchaseProductDtls(purchasedPrdctBeans);

			List<PurchaseNonBeveragePrdctDtl> purchaseNonBevePrdctDtlLst = purchaseDtlEntity.getNonBevPrdctList();
			if(purchaseNonBevePrdctDtlLst!=null && !purchaseNonBevePrdctDtlLst.isEmpty()){
				List<PurchaseProductDtl> purchasedNBPrdctBeans = new ArrayList<PurchaseProductDtl>();
				for(PurchaseNonBeveragePrdctDtl nonBve:purchaseNonBevePrdctDtlLst){
					purchasedNBPrdctBeans.add(getPurchaseNonBeveragePrdctBean(nonBve, Initialization.EAGER));
				}
				purchaseDtlBean.setNonBeverageProdctsDtls(purchasedNBPrdctBeans);
			}
		
		}
		return purchaseDtlBean;
	}
	public static PurchaseProductDtl getPurchaseProductBean(PurchasePrdctDtl purchaseProductDtlEntity,Initialization mode){
		PurchaseProductDtl purchasePrdctDtlBean = new PurchaseProductDtl();
		if(purchaseProductDtlEntity.getProduct()!=null){
			purchasePrdctDtlBean.setProduct(getProductBean(purchaseProductDtlEntity.getProduct(),mode));
		}
		purchasePrdctDtlBean.setProduct_qty(purchaseProductDtlEntity.getPurchasePrdctQty());
		purchasePrdctDtlBean.setPerPacketBasePrice(purchaseProductDtlEntity.getPerCsBasePrice());
		purchasePrdctDtlBean.setTotalBaseAmt(purchaseProductDtlEntity.getPrdctTotalBaseAmt());
		purchasePrdctDtlBean.setDiscountAmt(purchaseProductDtlEntity.getPrdctTotalDiscountAmt());
		purchasePrdctDtlBean.setNetTaxableAmt(purchaseProductDtlEntity.getPrdctTotalTaxableAmt());
		purchasePrdctDtlBean.setTotalPrdctCGSTAmt(purchaseProductDtlEntity.getPrdctTotalCGST());
		purchasePrdctDtlBean.setTotalPrdctSGSTAmt(purchaseProductDtlEntity.getPrdctTotalSGST());
		purchasePrdctDtlBean.setTotalPrdctIGSTAmt(purchaseProductDtlEntity.getPrdctTotalIGST());
		purchasePrdctDtlBean.setTotalPrdctCessAmt(purchaseProductDtlEntity.getPrdctTotalCess());
		purchasePrdctDtlBean.setNetPerCsPrice(purchaseProductDtlEntity.getPerCsNetPrice());
		purchasePrdctDtlBean.setNetPrdctAmnt(purchaseProductDtlEntity.getPrdctNetAmt());
		//purchasePrdctDtlEntity.setPrdctBreakageDtl(getProductBreakageEntity(purchaseProductDtl.getBraekageDtl()));
		purchasePrdctDtlBean.setRecieveDt(formatter.format(purchaseProductDtlEntity.getPrdctRecievedDt().getTime()));
		purchasePrdctDtlBean.setPurchaseProductId(purchaseProductDtlEntity.getPurchasedPrdctSeqNo());
		return purchasePrdctDtlBean;
	}
	public static PurchaseProductDtl getPurchaseNonBeveragePrdctBean(PurchaseNonBeveragePrdctDtl purchaseProductEntity,Initialization mode){
		PurchaseProductDtl purchaseNonBeverageBean = new  PurchaseProductDtl();
		purchaseNonBeverageBean.setPurchaseProductId(purchaseProductEntity.getPurchasedPrdctSeqNo());
		purchaseNonBeverageBean.setProduct( getProductTypeBean(purchaseProductEntity.getProduct(), mode));
		purchaseNonBeverageBean.setProduct_qty(purchaseProductEntity.getPurchasePrdctQty());
		purchaseNonBeverageBean.setTotalBaseAmt(purchaseProductEntity.getPrdctTotalBaseAmt());
		purchaseNonBeverageBean.setDiscountAmt(purchaseProductEntity.getPrdctTotalDiscountAmt());
		purchaseNonBeverageBean.setNetTaxableAmt(purchaseProductEntity.getPrdctTotalTaxableAmt());
		purchaseNonBeverageBean.setTotalPrdctCGSTAmt(purchaseProductEntity.getPrdctTotalCGST());
		purchaseNonBeverageBean.setTotalPrdctSGSTAmt(purchaseProductEntity.getPrdctTotalSGST());
		purchaseNonBeverageBean.setTotalPrdctIGSTAmt(purchaseProductEntity.getPrdctTotalIGST());
		purchaseNonBeverageBean.setTotalPrdctCessAmt(purchaseProductEntity.getPrdctTotalCess());
		purchaseNonBeverageBean.setUnitPrice(purchaseProductEntity.getUnitPrice());
		purchaseNonBeverageBean.setNetPrdctAmnt(purchaseProductEntity.getPrdctNetAmt());
		purchaseNonBeverageBean.setPurchaseProductId(purchaseProductEntity.getPurchasedPrdctSeqNo());
		return purchaseNonBeverageBean;
	}
	public static ProductGroup getProductGroupBean(BeverageProductGroupDtl group){
		ProductGroup productGroup = new ProductGroup();
		productGroup.setGroupName(group.getGroupName());
		return productGroup;
	}
	public static BeverageProductGroupDtl getProductGroupEntity(ProductGroup prodcutGroupBean){
		BeverageProductGroupDtl BeverageProductGroupDtlEntity = new BeverageProductGroupDtl();
		BeverageProductGroupDtlEntity.setGroupName(prodcutGroupBean.getGroupName());
		return BeverageProductGroupDtlEntity;
	}
	public static HsnTax getHsnTaxBean(HsnTaxStructure hsnTaxStr){
		HsnTax hsn = new HsnTax();
		hsn.setHsnCd(hsnTaxStr.getHsnCd());
		hsn.setHsnDiscription(hsnTaxStr.getHsnDiscription());
		hsn.setCgst(hsnTaxStr.getCgst());
		hsn.setSgstOrIgst(hsnTaxStr.getSgstOrIgst());
		hsn.setIgst(hsnTaxStr.getIgst());
		hsn.setCess(hsnTaxStr.getCess());
		return hsn;
	}
	public static HsnTaxStructure getHsnTaxEntity(HsnTax hsnTax){
		HsnTaxStructure hsn = new HsnTaxStructure();
		hsn.setHsnCd(hsnTax.getHsnCd());
		hsn.setHsnDiscription(hsnTax.getHsnDiscription());
		hsn.setCgst(hsnTax.getCgst());
		hsn.setSgstOrIgst(hsnTax.getSgstOrIgst());
		hsn.setIgst(hsnTax.getIgst());		
		hsn.setCess(hsnTax.getCess());
		return hsn;
	}
    public static Territory getTerritoryBean(TerritoryAreaDtl territoryArea){
    	Territory territory = new Territory();
    	territory.setTerritoryCode(territoryArea.getTerritoryCd());
    	territory.setTerritoryName(territoryArea.getTerritoryName());
    	territory.setTerritoryDistance(territoryArea.getTerritoryDistance());
    	return territory;
    }
    public static TerritoryAreaDtl getTerritoryEntity(Territory territoryArea){
    	TerritoryAreaDtl territoryEntity = new TerritoryAreaDtl();
    	territoryEntity.setTerritoryCd(territoryArea.getTerritoryCode());
    	territoryEntity.setTerritoryName(territoryArea.getTerritoryName());
    	territoryEntity.setTerritoryDistance(territoryArea.getTerritoryDistance());
    	return territoryEntity;
    }
    public static CstmrDtl getCstmrEntity(CustomerDtl cstmrDtl){
    	CstmrDtl cstmrEntity = new CstmrDtl();
    	cstmrEntity.setCstmrId(cstmrDtl.getCstmrId());
    	cstmrEntity.setCstmrFullname(cstmrDtl.getCstmrFullname());
    	cstmrEntity.setCstmrFathername(cstmrDtl.getCstmrFathername());
    	cstmrEntity.setCstmrTerritory(getTerritoryEntity(cstmrDtl.getTerritory()));
    	cstmrEntity.setCstmrMobNo(cstmrDtl.getCstmrMobNo());
    	cstmrEntity.setCstmrEmail(cstmrDtl.getCstmrEmail());
    	cstmrEntity.setCstmrCreatedBy(cstmrDtl.getCstmrCreatedBy());
    	return cstmrEntity;
    }
    public static CustomerDtl getCstmrBean(CstmrDtl cstmrDtl){
    	CustomerDtl cstmrBean = new CustomerDtl();
    	cstmrBean.setCstmrId(cstmrDtl.getCstmrId());
    	cstmrBean.setCstmrFullname(cstmrDtl.getCstmrFullname());
    	cstmrBean.setCstmrFathername(cstmrDtl.getCstmrFathername());
    	cstmrBean.setTerritory(getTerritoryBean(cstmrDtl.getCstmrTerritory()));
    	cstmrBean.setCstmrMobNo(cstmrDtl.getCstmrMobNo());
    	cstmrBean.setCstmrEmail(cstmrDtl.getCstmrEmail());
    	if(cstmrDtl.getCstmrAcnts()!=null && cstmrDtl.getCstmrAcnts().size()>0){
    		Set<CstmrAcntsDtl> acnts = cstmrDtl.getCstmrAcnts();
    		Set<CstmrLedger> ledgers = new HashSet<>();
    		for(CstmrAcntsDtl act : acnts){
    			CstmrLedger ledgr = getCsmtrAcntBean(act);
    			ledgr.setCstmr(cstmrBean);
    			ledgers.add(ledgr);
    		}
    		cstmrBean.setLedgers(ledgers);
    	}
    	cstmrBean.setCstmrCreatedBy(cstmrDtl.getCstmrCreatedBy());
    	return cstmrBean;
    }
    public static LedgerType getLedgerTypeBean(CstmrLedgerCategory ledgerCat){
    	LedgerType ledgrType = new LedgerType();
    	ledgrType.setLedgerTypeCd(ledgerCat.getCstmrLedgerTypeCd());
    	ledgrType.setLedgerTypeNm(ledgerCat.getCstmrLedgerTypeName());
    	ledgrType.setDiscountQty(ledgerCat.getLedgerCurrSpecialDiscount());
    	return ledgrType;
    }
    public static CstmrLedgerCategory getLedgerEntity(LedgerType ledgerTyp){
    	CstmrLedgerCategory ledgerEntity = new CstmrLedgerCategory();
    	ledgerEntity.setCstmrLedgerTypeCd(ledgerTyp.getLedgerTypeCd());
    	ledgerEntity.setCstmrLedgerTypeName(ledgerTyp.getLedgerTypeNm());
    	ledgerEntity.setLedgerCurrSpecialDiscount(ledgerTyp.getDiscountQty());
    	return ledgerEntity;
    }
    public static CstmrAcntsDtl getCsmtrAcntEntity(CstmrLedger cstmrLedger,Initialization iniMode){
    	CstmrAcntsDtl cstmrAcntsDtl = new CstmrAcntsDtl();
    	cstmrAcntsDtl.setCstmrAcntNo(cstmrLedger.getCstmrAcntNo());
    	if(Initialization.EAGER.name().equals(iniMode.name())){
    		cstmrAcntsDtl.setCstmr(getCstmrEntity(cstmrLedger.getCstmr()));
        	cstmrAcntsDtl.setCstmrLedgerTypeCd(getLedgerEntity(cstmrLedger.getLedgerType()));
    	}
    	cstmrAcntsDtl.setAcntOpenBy(cstmrLedger.getAcntOpenBy());
    	if(cstmrLedger.getAcntOpeningDt()!=null && !cstmrLedger.getAcntOpeningDt().equals("")){
			String time = cstmrLedger.getAcntOpeningDt();
			Calendar calendar = Calendar.getInstance();
			if(time.length()<=10){
				time=time+appendZeroTime;
			}
			try {
				calendar.setTime(formatter.parse(time));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			cstmrAcntsDtl.setAcntOpeningDt(calendar);
		}
    	return cstmrAcntsDtl;
    }
    public static CstmrLedger getCsmtrAcntBean(CstmrAcntsDtl cstmracntDtl){
    	CstmrLedger cstmrLedger = new CstmrLedger();
    	//cstmrLedger.setCstmr(getCstmr(cstmracntDtl.getCstmr()));
    	cstmrLedger.setCstmrAcntNo(cstmracntDtl.getCstmrAcntNo());
    	cstmrLedger.setLedgerType(getLedgerTypeBean(cstmracntDtl.getCstmrLedgerTypeCd()));
    	//cstmrAcntsDtl.setAcntOpenBy(cstmrLedger.getAcntOpenBy());
    	return cstmrLedger;
    }
    public static SaleBreakageDtl getSaleBreakageEntity(SaleBrekageDtl saleBrekageDtlBean){
    	SaleBreakageDtl saleBreakageDtlEntity = new SaleBreakageDtl();
    	saleBreakageDtlEntity.setBreakageSeq(saleBreakageDtlEntity.getBreakageSeq());
    	saleBreakageDtlEntity.setBreakageDt(Calendar.getInstance());
    	saleBreakageDtlEntity.setProductCd(getProductEntity(saleBrekageDtlBean.getProductCd()));
    	saleBreakageDtlEntity.setBreakageBs(saleBrekageDtlBean.getBreakageBs());
    	return saleBreakageDtlEntity;
    }
    public static SalePrdctSchemeDtl getSalePrdctSchemeEntity(SaleScheme saleSchemeBean){
    	SalePrdctSchemeDtl salePrdctSchemeDtlEntity = new SalePrdctSchemeDtl();
    	salePrdctSchemeDtlEntity.setAllotedSchemeId(saleSchemeBean.getAllotedSchemeId());
    	salePrdctSchemeDtlEntity.setAllotedToGroup(getProductGroupEntity(saleSchemeBean.getAllotedToGroup()));
    	if(saleSchemeBean.getAllotedToProduct()!=null){
    		salePrdctSchemeDtlEntity.setAllotedToProduct(getProductEntity(saleSchemeBean.getAllotedToProduct()));
    	}
    	salePrdctSchemeDtlEntity.setCalculatedSchemeQtyBs(saleSchemeBean.getGivenSchemeQtyBs());
    	salePrdctSchemeDtlEntity.setGivenSchemeQtyBs(saleSchemeBean.getGivenSchemeQtyBs());
    	salePrdctSchemeDtlEntity.setSchemePrdct(getProductEntity(saleSchemeBean.getSchemePrdct()));
    	salePrdctSchemeDtlEntity.setSchemeAllotedDt(Calendar.getInstance());
    	return salePrdctSchemeDtlEntity;
    }
    public static SaleInvoicesPrdctDtl getInvoicedPrdctsEntity(SalePrdctInvoice salePrdctInvoiceBean){
    	SaleInvoicesPrdctDtl saleInvoicedPrdctEntity = new SaleInvoicesPrdctDtl();
    	saleInvoicedPrdctEntity.setSeqNumber(salePrdctInvoiceBean.getSeqNumber());
    	saleInvoicedPrdctEntity.setBeverageGroup(getProductGroupEntity(salePrdctInvoiceBean.getProductGroup()));
    	saleInvoicedPrdctEntity.setPrdctGroupDescription(salePrdctInvoiceBean.getPrdctGroupDescription());
    	saleInvoicedPrdctEntity.setBaseRatePerCs(salePrdctInvoiceBean.getBaseRatePerCs());
    	saleInvoicedPrdctEntity.setSellingQty(salePrdctInvoiceBean.getSellingQty());
    	saleInvoicedPrdctEntity.setNetBaseAmt(salePrdctInvoiceBean.getNetBaseAmt());
    	saleInvoicedPrdctEntity.setSchemeDiscntPerCs(salePrdctInvoiceBean.getShemeDiscountPerCs());
    	saleInvoicedPrdctEntity.setSchemeDisocuntAmt(salePrdctInvoiceBean.getSchemeDisocuntAmt());
    	saleInvoicedPrdctEntity.setTaxableAmt(salePrdctInvoiceBean.getTaxableAmt());
    	saleInvoicedPrdctEntity.setCgstRate(salePrdctInvoiceBean.getCgstRate());
    	saleInvoicedPrdctEntity.setCgstAmt(salePrdctInvoiceBean.getCgstAmt());
    	saleInvoicedPrdctEntity.setSgstRate(salePrdctInvoiceBean.getSgstRate());
    	saleInvoicedPrdctEntity.setSgstAmt(salePrdctInvoiceBean.getSgstAmt());
    	saleInvoicedPrdctEntity.setIgstRate(salePrdctInvoiceBean.getIgstRate());
    	saleInvoicedPrdctEntity.setIgstAmt(salePrdctInvoiceBean.getIgstAmt());
    	saleInvoicedPrdctEntity.setCessRate(salePrdctInvoiceBean.getCessRate());
    	saleInvoicedPrdctEntity.setCessAmt(salePrdctInvoiceBean.getCessAmt());
    	saleInvoicedPrdctEntity.setSysGrossNetPerCs(salePrdctInvoiceBean.getSysGrossNetPerCs());
    	saleInvoicedPrdctEntity.setSysGnrtdNetAmt(salePrdctInvoiceBean.getSysGnrtdNetAmt());
    	saleInvoicedPrdctEntity.setSysSpecialDiscountPerCs(salePrdctInvoiceBean.getSysSpecialDiscountPerCs());
    	saleInvoicedPrdctEntity.setTotalSysGnrtdDiscount(salePrdctInvoiceBean.getTotalSysGnrtdDiscount());
    	saleInvoicedPrdctEntity.setTotalAmtAdjustment(salePrdctInvoiceBean.getTotalAmtAdjustment());
    	saleInvoicedPrdctEntity.setTotalDiscountAdjustment(salePrdctInvoiceBean.getTotalDiscountAdjustment());
    	saleInvoicedPrdctEntity.setTotalPrdctNetAmt(salePrdctInvoiceBean.getTotalPrdctNetAmt());
    	Set<SalePrdctDtl> salePrdctEntities = new HashSet<>();
    	for(SalePrdct saleprdct : salePrdctInvoiceBean.getSalePrdctSet()){
    		SalePrdctDtl salePrdctDtlEntity = getSalePrdctEnity(saleprdct);
    		salePrdctDtlEntity.setSaleInvoicedtl(saleInvoicedPrdctEntity);
    		salePrdctEntities.add(salePrdctDtlEntity);
    	}
    	saleInvoicedPrdctEntity.setSalePrdctSet(salePrdctEntities);
    	//saleInvoicedPrdctEntity.set
    	return saleInvoicedPrdctEntity;
    }
    public static SalePrdctDtl getSalePrdctEnity(SalePrdct salePrdctBean){
    	SalePrdctDtl salePrdctEntity = new SalePrdctDtl();
    	salePrdctEntity.setPrdct(getProductEntity(salePrdctBean.getProduct()));
    	salePrdctEntity.setPrdctSaleDt(Calendar.getInstance());
    	salePrdctEntity.setPrdctSaleTxnId(salePrdctBean.getPrdctSaleTxnId());
    	salePrdctEntity.setPrdctSallingQty(salePrdctBean.getPrdctSallingQty());
    	return salePrdctEntity;
    }
    
    public static SaleDtl getSaleDtlEntity(Sale salebean){
    	SaleDtl saleDtlEntity = new SaleDtl();
    	saleDtlEntity.setSaleInvoiceNo(salebean.getSaleInvoiceNo());
    	if(salebean.getCstmrAcnt()!=null){
    		saleDtlEntity.setCstmrAcnt(getCsmtrAcntEntity(salebean.getCstmrAcnt(),Initialization.LAZY));
    	}
    	saleDtlEntity.setCstmrName(salebean.getCstmrName());
    	saleDtlEntity.setAddress(salebean.getAddress());
    	saleDtlEntity.setDeliveryMode(salebean.getDeliveryMode());
    	saleDtlEntity.setIsBreakageReturn(salebean.getIsBreakageReturn());
    	List<SaleInvoicesPrdctDtl> saleIvoiceEntites = new ArrayList<>();
    	for(SalePrdctInvoice salePrdctInvoiceBean : salebean.getInvoicedPrdctDtlsSet()){
    		SaleInvoicesPrdctDtl saleInvoicesPrdctDtl = getInvoicedPrdctsEntity(salePrdctInvoiceBean);
    		saleInvoicesPrdctDtl.setSaledtl(saleDtlEntity);
    		saleIvoiceEntites.add(saleInvoicesPrdctDtl);
    	}
    	saleDtlEntity.setInvoicedPrdctDtlsSet(saleIvoiceEntites);
    	if(AppConstant.POSITIVE.equals(salebean.getIsBreakageReturn())){
    		List<SaleBreakageDtl> saleBreakEntities = new ArrayList<>();
    		for(SaleBrekageDtl saleBreakBean : salebean.getSaleBrekageLst()){
    			SaleBreakageDtl saleBreakageEntity = getSaleBreakageEntity(saleBreakBean);
    			saleBreakageEntity.setSaleDtl(saleDtlEntity);
    			saleBreakEntities.add(saleBreakageEntity);
    		}
    		saleDtlEntity.setSaleBrekageLst(saleBreakEntities);
    	}
    	saleDtlEntity.setIsSchemeAlloted(salebean.getIsSchemeAlloted());
    	if(AppConstant.POSITIVE.equals(salebean.getIsSchemeAlloted())){
    		List<SalePrdctSchemeDtl> schemeEntities = new ArrayList<>();
    		for(SaleScheme saleScheme : salebean.getSalePrdctSchemeDtlSet()){
    			SalePrdctSchemeDtl salePrdctSchemeEntity = getSalePrdctSchemeEntity(saleScheme);
    			salePrdctSchemeEntity.setSaleDtl(saleDtlEntity);
    			schemeEntities.add(salePrdctSchemeEntity);
    		}
    		saleDtlEntity.setSalePrdctSchemeDtlSet(schemeEntities);
    	}
    	String saleDt = salebean.getSaleDt();
    	Calendar calendar = Calendar.getInstance();
    	try {
			calendar.setTime(formatter.parse(saleDt));
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	saleDtlEntity.setSaleDt(calendar);
    	saleDtlEntity.setSaleEntrySystemDt(calendar);
    	saleDtlEntity.setSoldBy(salebean.getSoldBy());
    	saleDtlEntity.setSysGnrtdTotalAmount(salebean.getSysGnrtdTotalAmount());
    	saleDtlEntity.setTotalSchemeDiscnt(salebean.getTotalSchemeDiscnt());
    	saleDtlEntity.setSysGnrtdTotalDiscount(salebean.getSysGnrtdTotalDiscount());
    	saleDtlEntity.setTotalAdjustmentDiscount(salebean.getTotalAdjustmentDiscount());
    	saleDtlEntity.setTotalAmountAdjustment(salebean.getTotalAmountAdjustment());
    	saleDtlEntity.setTotalNetActualAmount(salebean.getTotalNetActualAmount());
    	saleDtlEntity.setPaidAmount(salebean.getPaidAmount());
    	saleDtlEntity.setPaymentMode(salebean.getPaymentMode());
    	saleDtlEntity.setTotalDeliverBsGlass(salebean.getTotalDeliverBsGlass());
    	saleDtlEntity.setTotalDeliverCell(salebean.getTotalDeliverCell());
    	saleDtlEntity.setTotalReturnBsGlass(salebean.getTotalReturnBsGlass());
    	saleDtlEntity.setTotalReturnCell(salebean.getTotalReturnCell());
    	saleDtlEntity.setInvoiceType(salebean.getInvoiceType());
    	return saleDtlEntity;
    }
    public static RouteDtl getRouteBean(RouteMapDtl routeMap){
    	RouteDtl routeDtl = new RouteDtl();
    	routeDtl.setRouteCd(routeMap.getRouteCd());
    	routeDtl.setRouteName(routeMap.getRouteName());
    	return routeDtl;
    }
    public static RouteMapDtl getRouteEntity(RouteDtl routeBean){
    	RouteMapDtl routeEntity = new RouteMapDtl();
    	routeEntity.setRouteCd(routeBean.getRouteCd());
    	routeEntity.setRouteName(routeBean.getRouteName());
    	return routeEntity;
    }
    public static Vehicle getVehicleBean(SupplyVehicleDtl supplyVehicle){
    	Vehicle vehicleBean = new Vehicle();
    	vehicleBean.setVehicleCd(supplyVehicle.getVehicleCd());
    	vehicleBean.setVehicleNo(supplyVehicle.getVehicleNo());
    	vehicleBean.setVehicleType(supplyVehicle.getVehicleType());
    	return vehicleBean;
    }
    public static SupplyVehicleDtl getVehicleEntity(Vehicle vehicleBean){
    	SupplyVehicleDtl vehicleEntity = new SupplyVehicleDtl();
    	vehicleEntity.setVehicleCd(vehicleBean.getVehicleCd());
    	vehicleEntity.setVehicleNo(vehicleBean.getVehicleNo());
    	vehicleEntity.setVehicleType(vehicleBean.getVehicleType());
    	return vehicleEntity;
    }
    
    public static SupplyRecord getSupplyEntity(SupplyDtl supplyDtBean){
    	SupplyRecord supplyEntity = new SupplyRecord();
    	supplyEntity.setSupplySeqNo(supplyDtBean.getSupplySeqNo());
    	supplyEntity.setTotalGlassBsSent(supplyDtBean.getTotalGlassBsSent());
    	supplyEntity.setTotalCellSent(supplyDtBean.getTotalCellSent());
    	supplyEntity.setExpectedReturnBsGlass(supplyDtBean.getExpectedReturnBsGlass());
    	supplyEntity.setExpectedReturnCell(supplyDtBean.getExpectedReturnCell());
    	supplyEntity.setActualReturnBsGlass(supplyDtBean.getActualReturnBsGlass());
    	supplyEntity.setActualCellReturn(supplyDtBean.getExpectedReturnCell());
    	supplyEntity.setCstmrDueGlassBsQty(supplyDtBean.getCstmrDueGlassBsQty());
    	supplyEntity.setCstmrDueCellQty(supplyDtBean.getCstmrDueCellQty());
    	supplyEntity.setLostGlassBsQty(supplyDtBean.getLostGlassBsQty());
    	supplyEntity.setLostCellQty(supplyDtBean.getLostCellQty());
    	supplyEntity.setSysGenTotalSupplysaleAmnt(supplyDtBean.getSysGenTotalSupplysaleAmnt());
    	supplyEntity.setSysGnrtedTotalSupplysaleDiscnt(supplyDtBean.getSysGnrtedTotalSupplysaleDiscnt());
    	supplyEntity.setTotalSupplysaleAdjustmentDiscnt(supplyDtBean.getTotalSupplysaleAdjustmentDiscnt());
    	supplyEntity.setTotalSupplysaleAdjustmentAmnt(supplyDtBean.getTotalSupplysaleAdjustmentAmnt());
    	supplyEntity.setTotalNetActualSupplysaleAmnt(supplyDtBean.getTotalNetActualSupplysaleAmnt());
    	supplyEntity.setTotalCstmrSupplysaleDueAmnt(supplyDtBean.getTotalCstmrSupplysaleDueAmnt());
    	supplyEntity.setTotalSupplysaleAmntRecieved(supplyDtBean.getTotalSupplysaleAmntRecieved());
    	supplyEntity.setMismatchAmnt(supplyDtBean.getMismatchAmnt());
    	supplyEntity.setVehicleCd(getVehicleEntity(supplyDtBean.getVehicle()));
    	supplyEntity.setRouteCovered(getRouteEntity(supplyDtBean.getRouteCovered()));
    	List<SupplyPrdctDtl> supplyPrdctsListEntity = new ArrayList<>();
    	for(SupplyPrdcts supplyPrdctBean: supplyDtBean.getSupplyPrdctDtlLst()){
    		SupplyPrdctDtl supplyPrdctEntity = getSupplyPrdctEntity(supplyPrdctBean);
    		supplyPrdctEntity.setSupplyRecordMaster(supplyEntity);
    		supplyPrdctsListEntity.add(supplyPrdctEntity);
    	}
    	supplyEntity.setSupplyPrdctDtlLst(supplyPrdctsListEntity);
    	String exitTime = supplyDtBean.getSupplyVehicleExitTime();
    	Calendar calendar = Calendar.getInstance();
    	try {
			calendar.setTime(formatter.parse(exitTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	supplyEntity.setSupplyVehicleExitTime(calendar);
    	supplyEntity.setSupplyWorkerName(supplyDtBean.getSupplyWorkerName());
    	return supplyEntity;
    }
    public static SupplyPrdctDtl getSupplyPrdctEntity(SupplyPrdcts supplyPrdctsBean){
    	SupplyPrdctDtl supplyPrdctEntity =new SupplyPrdctDtl();
    	supplyPrdctEntity.setSupplyPrdctSeq(supplyPrdctsBean.getSupplyPrdctSeq());
    	supplyPrdctEntity.setTotalSentPrdctQty(supplyPrdctsBean.getTotalSentPrdctQty());
    	supplyPrdctEntity.setTotalSellingPrdctQty(supplyPrdctsBean.getTotalSellingPrdctQty());
    	supplyPrdctEntity.setExpectedReturnQty(supplyPrdctsBean.getExpectedReturnQty());
    	supplyPrdctEntity.setActualReturnPrdctQty(supplyPrdctsBean.getActualReturnPrdctQty());
    	supplyPrdctEntity.setPrdctLostQty(supplyPrdctsBean.getPrdctLostQty());
    	supplyPrdctEntity.setProduct(getProductEntity(supplyPrdctsBean.getProduct()));
    	return supplyPrdctEntity;
    }

	public static FirmSeller getSellerBean(FirmOrSellerDtl frmEntity) {
		FirmSeller frm = new FirmSeller();
		frm.setFirmCd(frmEntity.getFirmCd());
		frm.setFirmNm(frmEntity.getFirmNm());
		frm.setFirmGstnNumber(frmEntity.getFirmGstnNumber());
		return frm;
	}
	
	public static FirmOrSellerDtl getSellerEntity(FirmSeller frm) {
		FirmOrSellerDtl frmEntity = new FirmOrSellerDtl();
		frmEntity.setFirmCd(frm.getFirmCd());
		frmEntity.setFirmNm(frm.getFirmNm());
		frmEntity.setFirmGstnNumber(frm.getFirmGstnNumber());
		return frmEntity;
	}
	public static ProductCatagory getProductTypeBean(ProductTypes prodctEnt,Initialization iniMode){
		ProductCatagory productCatBean = null;
		if(prodctEnt instanceof BeverageProductCategory){
			productCatBean=getProductBean((BeverageProductCategory)prodctEnt, iniMode);
		}
		else if(prodctEnt instanceof NonBeverageProductCatergory){
			productCatBean=getNonBeverageProductBean((NonBeverageProductCatergory)prodctEnt);
		}
		return productCatBean;
	}
	public static NonBeveragePrdct getNonBeverageProductBean(NonBeverageProductCatergory nonBevEnt){
		NonBeveragePrdct nonBproductBean = new NonBeveragePrdct();
		nonBproductBean.setProductCd(nonBevEnt.getProductCd());
		nonBproductBean.setProductNm(nonBevEnt.getProductNm());
		nonBproductBean.setGroupName(nonBevEnt.getGroupName());
		
		//product.setProductAddedBy(beverageProductCategory.getProductAddedBy());
		nonBproductBean.setProductAddedDt(formatter.format(nonBevEnt.getProductAddedDt().getTime()));
		nonBproductBean.setHsnTax(getHsnTaxBean(nonBevEnt.getHsn()));
		return nonBproductBean;
	}
	public static NonBeverageSaleDtl getNonBeverageSaleEntity(NonBeverageSale nbSale){
		NonBeverageSaleDtl nbSaleEntity = new NonBeverageSaleDtl();
		nbSaleEntity.setNbSaleInvoiceNo(nbSale.getNbSaleInvoiceNo());
		nbSaleEntity.setAddress(nbSale.getAddress());
		nbSaleEntity.setCstmrFrmNm(nbSale.getCstmrFrmNm());
		nbSaleEntity.setCstmrAcntNo(nbSale.getCstmrAcntNo());
		nbSaleEntity.setInvoiceType(nbSale.getInvoiceType().getType());
		nbSaleEntity.setCstmrFirmGstn(nbSale.getBuyerGstn());
		String saleDt = nbSale.getNbSaleDt();
    	Calendar calendar = Calendar.getInstance();
    	try {
			calendar.setTime(formatter.parse(saleDt));
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	nbSaleEntity.setNbSaleDt(calendar);
    	List<NonBeveragePrdctSaleDtl> nbSalePrdctLst = new ArrayList<>();
    	for(NonBeveragePrdctSale nbprdctSale : nbSale.getNbSaleProductlst()){
    		NonBeveragePrdctSaleDtl prdctSaleEntity = getNonBeveragePrdctSaleEntity(nbprdctSale);
    		prdctSaleEntity.setNbSaleDtl(nbSaleEntity);
    		nbSalePrdctLst.add(prdctSaleEntity);
    	}
    	nbSaleEntity.setNbSaleProductlst(nbSalePrdctLst);
		return nbSaleEntity;
	}
	public static NonBeveragePrdctSaleDtl getNonBeveragePrdctSaleEntity(NonBeveragePrdctSale nonBeveragePrdctSale){
		NonBeveragePrdctSaleDtl nbSalePrdctEntity = new NonBeveragePrdctSaleDtl();
		nbSalePrdctEntity.setSeqNumber(nonBeveragePrdctSale.getSeqNumber());
		nbSalePrdctEntity.setNonBeverageProduct(getNonBeverageProductEntity(nonBeveragePrdctSale.getNonBeveragePrdct()));
		nbSalePrdctEntity.setSellingQty(nonBeveragePrdctSale.getSellingQty());
		nbSalePrdctEntity.setUnitPrice(nonBeveragePrdctSale.getUnitPrice());
		nbSalePrdctEntity.setNetBaseAmt(nonBeveragePrdctSale.getNetBaseAmt());
		nbSalePrdctEntity.setDisocuntAmt(nonBeveragePrdctSale.getDisocuntAmt());
		nbSalePrdctEntity.setTaxableAmt(nonBeveragePrdctSale.getTaxableAmt());
		nbSalePrdctEntity.setCgstRate(nonBeveragePrdctSale.getCgstRate());
		nbSalePrdctEntity.setCgstAmt(nonBeveragePrdctSale.getCgstAmt());
		nbSalePrdctEntity.setSgstRate(nonBeveragePrdctSale.getSgstRate());
		nbSalePrdctEntity.setSgstAmt(nonBeveragePrdctSale.getSgstAmt());
		nbSalePrdctEntity.setCessRate(nonBeveragePrdctSale.getCessRate());
		nbSalePrdctEntity.setCessAmt(nonBeveragePrdctSale.getCessAmt());
		nbSalePrdctEntity.setIgstRate(nonBeveragePrdctSale.getIgstRate());
		nbSalePrdctEntity.setIgstAmt(nonBeveragePrdctSale.getIgstAmt());
		nbSalePrdctEntity.setSysGnrtdNetAmt(nonBeveragePrdctSale.getSysGnrtdNetAmt());
		return nbSalePrdctEntity;
	}
}
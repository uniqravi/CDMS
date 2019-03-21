package application.cdms.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

import org.apache.log4j.Logger;

import application.cdms.common.annotation.DaoQuery;
import application.cdms.dao.CDMSDataProviderDao;
import application.cdms.dao.impl.CDMSDataProviderDaoImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public interface CDMSDataProviderService {

	@DaoQuery(queryString = "select pur.firm_name,hsn.hsn_cd,"
			+ "(hsn.hsn_discription || ' | ' || hsn.cgst || ' cgst | ' || hsn.sgst_or_igst || ' sgst_igst | ' || hsn.cess || ' cess') "
			+ "as tax_description,sum(purprd.purchase_prdct_cgst) as cgst_sum,sum(purprd.purchase_prdct_sgst) as sgst_sum,"
			+ "sum(purprd.purchase_prdct_igst) as igst_sum,sum(purprd.purchase_prdct_cess_tax) as cess_sum "
			+ "from cdms.purchase_prdct_dtl as purprd "
			+ "inner join cdms.beverage_product_category as prdct on purprd.product_cd=prdct.product_cd "
			+ "inner join cdms.hsn_tax_structure hsn on prdct.hsn_code=hsn.hsn_cd "
			+ "inner join cdms.purchase_dtl as pur on purprd.purchase_seq_no=pur.purchase_seq_no "
			+ "where pur.challan_dt between to_date(?,'DD-MM-YYYY') and to_date(?,'DD-MM-YYYY') "
			+ "group by pur.firm_name,hsn.hsn_cd,hsn.hsn_discription,hsn.cgst,hsn.sgst_or_igst,hsn.cess order by pur.firm_name,hsn.hsn_cd")
	ObservableList<String[]> viewPurchaseTaxByDtWithGroupByFirm(Object... values);
	
	@DaoQuery(queryString = "select hsn.hsn_cd,"
			+ "(hsn.hsn_discription || ' | ' || hsn.cgst || ' cgst | ' || hsn.sgst_or_igst || ' sgst_igst | ' || hsn.cess || ' cess') "
			+ "as tax_description,sum(purprd.purchase_prdct_cgst) as cgst_sum,sum(purprd.purchase_prdct_sgst) as sgst_sum,"
			+ "sum(purprd.purchase_prdct_igst) as igst_sum,sum(purprd.purchase_prdct_cess_tax) as cess_sum "
			+ "from cdms.purchase_prdct_dtl as purprd "
			+ "inner join cdms.beverage_product_category as prdct on purprd.product_cd=prdct.product_cd "
			+ "inner join cdms.hsn_tax_structure hsn on prdct.hsn_code=hsn.hsn_cd "
			+ "inner join cdms.purchase_dtl as pur on purprd.purchase_seq_no=pur.purchase_seq_no "
			+ "where pur.challan_dt between to_date(?,'DD-MM-YYYY') and to_date(?,'DD-MM-YYYY') "
			+ "group by hsn.hsn_cd,hsn.hsn_discription,hsn.cgst,hsn.sgst_or_igst,hsn.cess order by hsn.hsn_cd")
	ObservableList<String[]> viewPurchaseTaxByDt(Object... values);

	@DaoQuery(queryString = "select hsn.hsn_cd,"
			+ "(hsn.hsn_discription || ' | ' || hsn.cgst || ' cgst | ' || hsn.sgst_or_igst || ' sgst_igst | ' || hsn.cess || ' cess') as tax_description,"
			+ "sum(purprd.purchase_prdct_cgst) as cgst_sum,sum(purprd.purchase_prdct_sgst) as sgst_sum,"
			+ "sum(purprd.purchase_prdct_igst) as igst_sum,sum(purprd.purchase_prdct_cess_tax) as cess_sum "
			+ "from cdms.purchase_prdct_dtl as purprd "
			+ "inner join cdms.beverage_product_category as prdct on purprd.product_cd=prdct.product_cd "
			+ "inner join cdms.hsn_tax_structure as hsn on prdct.hsn_code=hsn.hsn_cd "
			+ "inner join cdms.purchase_dtl as pur on purprd.purchase_seq_no=pur.purchase_seq_no "
			+ "where pur.challan_invoice_no=? group by hsn.hsn_cd,hsn.hsn_discription,hsn.cgst,hsn.sgst_or_igst,hsn.cess")
	ObservableList<String[]> viewPurchaseTaxCompntByInvoice(Object... values);

	@DaoQuery(queryString = "select prdct.group_name, " + "sum(break.burst_bs) as burst, "
			+ "sum(break.open_mouth_bs) as open_mount, sum(break.shortage_bs) as shortage, "
			+ "sum(break.seal_pack_shortage_bs) as seal_shortage, " + "sum(break.leakage_bs) as leakage "
			+ "from cdms.prdct_breakage_dtl break INNER join cdms.beverage_product_category as prdct on break.product_cd=prdct.product_cd "
			+ "where break.breakage_seq in "
			+ "(select purprd.purchase_breakage_seq from cdms.purchase_prdct_dtl as purprd where "
			+ "purchase_seq_no=(select pur.purchase_seq_no from cdms.purchase_dtl as pur where "
			+ "pur.challan_invoice_no=?) " + ") group by prdct.group_name ")
	ObservableList<String[]> viewPurchaseBreakByInvoice(Object... values);
	
	@DaoQuery(queryString="select to_char(pur.challan_dt,'DD-MON-YYYY') as challan_dt,pur.challan_invoice_no,pur.challan_no,"
			+ "sum(purprd.purchase_prdct_qty) as total_load,pur.total_purchased_glass_qty,pur.total_return_empty_glass_qty,"
			+ "sum(purprd.purchase_prdct_cgst) as cgst_sum,sum(purprd.purchase_prdct_igst) as igst_sum,"
			+ "sum(purprd.purchase_prdct_sgst) as sgst_sum,sum(purprd.purchase_prdct_cess_tax) as cess_sum ,pur.purchase_seq_no "
			+ "from cdms.purchase_dtl as pur inner join cdms.purchase_prdct_dtl as purprd on pur.purchase_seq_no=purprd.purchase_seq_no "
			+ "where pur.challan_dt between to_date(?,'DD-MM-YYYY') and to_date(?,'DD-MM-YYYY') "
			+ "group by  pur.challan_no, pur.challan_invoice_no,pur.purchase_seq_no order by pur.challan_dt desc")
	ObservableList<String[]> viewPurchaseSummeryByDt(Object... values);
	
	@DaoQuery(queryString="select nb.non_bev_product_cd,nb.product_name,pnb.unit_price,hsn.* from cdms.purchase_non_beverage_prdct_dtl pnb "
			+ "inner join cdms.non_beverage_product_category nb on  pnb.non_bev_product_cd=nb.non_bev_product_cd "
			+ "inner join cdms.hsn_tax_structure hsn on nb.hsn_code=hsn.hsn_cd "
			+ "where pnb.purchase_seq_no=(select p.purchase_seq_no from cdms.purchase_dtl p where p.challan_invoice_no=?)")
	ObservableList<String[]> viewNonBeveragePurchaseDtlByInvoice(Object... values);
	
	@DaoQuery(queryString="select nb.non_bev_product_cd,nb.product_name,npb.unit_price from cdms.non_beverage_product_category as nb " + 
			"left join cdms.purchase_non_beverage_prdct_dtl as npb on nb.non_bev_product_cd=npb.non_bev_product_cd order by prdct_recieved_dt desc",limit=2)
	ObservableList<String[]> getNonBeveragePrdctsPrice();
	
	@DaoQuery(queryString="select to_char(pur.challan_dt,'DD-MON-YYYY') as challan_dt,pur.challan_invoice_no,"
			+ "pur.challan_no,sum(purprd.purchase_prdct_qty) as total_load,pur.total_purchased_glass_qty,pur.total_return_empty_glass_qty,"
			+ "sum(purprd.purchase_prdct_cgst) as cgst_sum,sum(purprd.purchase_prdct_igst) as igst_sum,"
			+ "sum(purprd.purchase_prdct_sgst) as sgst_sum,sum(purprd.purchase_prdct_cess_tax) as cess_sum ,pur.purchase_seq_no "
			+ "from cdms.purchase_dtl as pur "
			+ "inner join cdms.purchase_prdct_dtl as purprd on pur.purchase_seq_no=purprd.purchase_seq_no "
			+ "group by  pur.challan_no, pur.challan_invoice_no,pur.purchase_seq_no order by pur.challan_dt desc",limit=30)
	ObservableList<String[]> viewPurchaseSummeryWithoutCriteria();

	static class CDMSDataIntanceProvider {
		private static CDMSDataProviderService instance = (CDMSDataProviderService) Proxy.newProxyInstance(
				CDMSDataProviderService.class.getClassLoader(), new Class[] { CDMSDataProviderService.class },
				new InvocationHandlerImpl());

		private CDMSDataIntanceProvider() {
		}

		public static CDMSDataProviderService getInstance() {
			return instance;
		}
	}

	Logger logger = Logger.getLogger(CDMSDataProviderService.class);

	class InvocationHandlerImpl implements InvocationHandler {

		@SuppressWarnings("unchecked")
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			Object returnObj = null;
			StringBuilder sb = new StringBuilder();
			try {
				sb.append(method.getName());
				Object[] objArray = null;
				sb.append("[");
				if (args != null && args[args.length - 1] instanceof Object[]) {
					objArray = (Object[]) args[args.length - 1];
					for (Object obj : objArray) {
						sb.append("," + obj.toString());
					}
				}
				sb.append("]");
				DaoQuery daoQuery = method.getAnnotation(DaoQuery.class);
				String queryId = daoQuery.queryString();
				int limit=daoQuery.limit();
				Class<?> returnClassType = method.getReturnType();
				if (returnClassType.getName().equalsIgnoreCase("javafx.collections.ObservableList")) {
					CDMSDataProviderDao cdmsDataProvider = CDMSDataProviderDaoImpl.getInstance();
					List<Object[]> list = cdmsDataProvider.getCDMSDataList(sb.toString(), queryId,limit, objArray);
					@SuppressWarnings("rawtypes")
					ObservableList returnLst = FXCollections.observableArrayList();
					if (list != null && !list.isEmpty())
						for (Object[] objArry : list) {
							String[] strArry = new String[objArry.length];
							for (int j = 0; j < objArry.length; j++) {
								strArry[j] = objArry[j]!=null?objArry[j].toString():null;
							}
							returnLst.add(strArry);
						}
					returnObj = returnLst;
				}
			} catch (Exception ex) {
				logger.fatal("Exception in " + sb + " ### " + ex.getMessage());
				throw ex;
			}
			return returnObj;
		}
	}

	static CDMSDataProviderService createCDMSDataProvider() {
		return CDMSDataIntanceProvider.getInstance();
	}
}

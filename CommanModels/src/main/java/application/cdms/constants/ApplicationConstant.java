package application.cdms.constants;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public interface ApplicationConstant {
  String RETURNABLE_PACKING_NAME="GLASS";
  String DOUBLE_SERO="0.0";
  DecimalFormat deciFormat = new DecimalFormat("0.00");
  SimpleDateFormat formatter=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");
  SimpleDateFormat dateformatter=new SimpleDateFormat("dd-MM-yyyy");
  SimpleDateFormat timeformatter=new SimpleDateFormat("hh:mm:ss a");
  String POSITIVE="Y";
  String NEGATIVE="N";
  String SUPPLY_STATUS_NEW="NEW";
  String SUPPLY_STATUS_RETURN="RETURN";
  //String SUPPLY_STATUS_ASSESS_START="ASSESS_START";
  String SUPPLY_STATUS_ASSESS_INPROGRESS="ASSESS_INPROGRESS";
  String SUPPLY_STATUS_ASSESS_END="ASSESS_END";
  String SUPPLY_STATUS_COMPLETED="END";
  String SALE = "S";
  String RETURN_PURCHASE_SALE="RS";
  String RETURN_BREAKAGE_SALE="RBS";
  String RETURN_CUSTOMER_SALE="RCS";
  String DELIVERY_MODE_SHOP="SHOP";
  String DELIVERY_MODE_SUPPLY="SUPPLY";
  String CLAIM_PENDING="P";
  String CLAIM_APPROVED_BY_FIRM="CA";
  String CLAIM_APPROVED_BY_TAX="TA";
  String CLAIM_APPROVED_BY_FIRM_TAX="A";
  String DELIVERY_TO_SAME_STATE="Same State";
  String DELIVERY_TO_OTHER_STATE="Other State";
  String BOTTLE = "BOTTLE";
  String SHELL = "SHELL";
  String GROUPBY_WITHFIRM = "withFirm";
  String GROUPBY_WITHOUTFIRM="withoutFirm";
}
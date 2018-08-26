package application.cdms.report.build;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;	

public class ReportElEvaluator {
	private Map<String, String> elValuesMap;

	private Pattern pattern;

	private final static String EL_REGEX = "(\\$\\{)([\\w[^\\W]]*)(\\})";

	public ReportElEvaluator(Map<String, String> elValuesMap) {
		this.elValuesMap = elValuesMap;
		if (elValuesMap.size() > 0) {
			pattern = Pattern.compile(EL_REGEX);
		}
	}

	public String getReportElValue(String elsString) {
		Matcher m = pattern.matcher(elsString);
		StringBuilder sb = new StringBuilder(elsString);
		while (m.find()) {
			String elValue = elValuesMap.get(m.group(2));
			if(elValue==null) {
				continue;
			}
			String elNm = m.group(0);
			int inex = sb.indexOf(elNm);
			sb.replace(inex, inex + elNm.length(), elValue);
		}
		return sb.toString();
	}
	
	public static void main(String[] ars) {
		Map<String,String> maps = new HashMap<String, String>();
		maps.put("employNm", "ravi");
		maps.put("AGE", "23");
		String str = "My Name is ${employNm} and age is ${AGE}";
		ReportElEvaluator eval = new ReportElEvaluator(maps);
		System.out.println(eval.getReportElValue(str)); ;
	}
}
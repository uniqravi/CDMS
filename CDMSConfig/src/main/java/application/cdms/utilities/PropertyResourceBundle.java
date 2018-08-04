package application.cdms.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyResourceBundle{

	private static Properties properties = new Properties();
	static{
		try {
			properties.load(new FileInputStream("/app/kcd/PropertiesFiles/kcd-server-details.properties"));
			Properties reportProperty = new Properties();
			reportProperty.load(PropertyResourceBundle.class.getClassLoader().getResourceAsStream("application/cdms/configfiles/report.properties"));
			properties.putAll(reportProperty);
		} catch (IOException e) {
			System.out.println("Loading Failed ### "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static String get(String key){
		return properties.getProperty(key);
	}
}
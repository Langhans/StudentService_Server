package application;

import java.io.FileInputStream;
import java.util.Properties;

public class Settings_original_NOT_USED {

	private static final long serialVersionUID = 2L;
	private static Settings_original_NOT_USED settings = new Settings_original_NOT_USED();
	private   Properties props;


	private Settings_original_NOT_USED() {
		try {
			FileInputStream fis = new FileInputStream("/WebContent/settings.properties");
			props = new Properties();
			props.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Settings: Cannot load settings file!");
		}
	}

	public static Properties getSettings() {
		return settings.props;
	}
}

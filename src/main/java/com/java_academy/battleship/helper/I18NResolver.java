package com.java_academy.battleship.helper;

import java.util.Locale;
import java.util.ResourceBundle;

public class I18NResolver {
	
	private static I18NResolver instance;
	
	private Locale locale;
	
	private I18NResolver(Locale locale) {
		this.locale = locale;
	}

	public static I18NResolver createI18NResolver(Locale locale) {
		instance = new I18NResolver(locale);
		return instance;
	}
	
	public static I18NResolver getI18NResolverInstance() {
		return instance;
	}

	public String getMsgByKey(String key) {
		return ResourceBundle.getBundle("Messages", locale).getString(key);
	}
}

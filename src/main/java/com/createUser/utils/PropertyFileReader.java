package com.createUser.utils;

import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
public class PropertyFileReader {

	@Autowired
	private MessageSource messageResource;
    
	public String getMessage(String key) {
		Locale locale = LocaleContextHolder.getLocale();
		if (System.getenv(key) != null) {
			return System.getenv(key);
		} else {
			return messageResource.getMessage(key, null, locale);
		}
	}
	
	public String getMessage(String key,Object [] params) {
		Locale locale = LocaleContextHolder.getLocale();
		if (System.getenv(key) != null) {
			return System.getenv(key);
		} else {
			return messageResource.getMessage(key, params, locale);
		}
	}
}
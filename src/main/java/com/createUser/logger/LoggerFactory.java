package com.createUser.logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import com.createUser.common.ApplicationConstants;
import com.createUser.utils.PropertyFileReader;

@RefreshScope
@Component
public class LoggerFactory {

	@Autowired
	private PropertyFileReader propertyFileReader;
	@Autowired
	private ILogger fileAppenderLog;
	
	@Value("${logger_select}")
	private String logger_select;
	
	@Value("${file}")
	private String file;
	
	public ILogger getLoggerInstance() {		
		String loggerMode =logger_select;
		if (loggerMode.equalsIgnoreCase(file)) {
			return fileAppenderLog;
		} 
		//TODO
		return fileAppenderLog;
	}
}

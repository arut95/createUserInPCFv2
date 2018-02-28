package com.createUser.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FileAppenderLog implements ILogger {
	private Logger logger;

	public FileAppenderLog() {
		logger = LoggerFactory.getLogger(getClass());
	}

	@Override
	public void debug(String msg) {
		this.logger.debug(msg);
	}

	@Override
	public void info(String msg) {
		this.logger.info(msg);

	}

	@Override
	public void error(String msg) {
		this.logger.error(msg);

	}

}

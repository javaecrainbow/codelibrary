package com.salk.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;

public class TestLog {
    private final static Logger logger = LoggerFactory.getLogger(TestLog.class); 

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		logger.trace("trace====");
		logger.debug("debug====");
		logger.info("info====");
		logger.warn("warn====");
		logger.error("error====");

	}

}

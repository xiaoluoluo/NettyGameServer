package com.hit.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class App {
	public static final Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {

		logger.debug("1111");
		logger.info("222");
		logger.error("333");

	}
}

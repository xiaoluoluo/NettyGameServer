package com.hit.game.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReadConfig {

	public static final Logger logger = LoggerFactory.getLogger(ReadConfig.class);

	public ReadConfig() {
		read();
	}

	public void read() {

		Properties prop = new Properties();
		InputStream in = Object.class.getResourceAsStream("/config.properties");
		try {
			prop.load(in);
			String param1 = prop.getProperty("game.id").trim();
			logger.debug(param1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

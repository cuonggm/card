package com.cuong.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class PathUtils {

	private static final Logger LOGGER = Logger.getLogger(PathUtils.class.getName());

	public static URL getViewPath(String viewFileName) {
		try {
			Path viewPath = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "views",
					viewFileName);
			return viewPath.toUri().toURL();
		} catch (MalformedURLException e) {
			LOGGER.severe(e.getMessage());
			return null;
		}
	}

	public static URL getDatabaseCfgPath() {
		try {
			Path viewPath = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "config",
					"database.cfg.xml");
			return viewPath.toUri().toURL();
		} catch (MalformedURLException e) {
			LOGGER.severe(e.getMessage());
			return null;
		}
	}

	private PathUtils() {

	}

}

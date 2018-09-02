package com.cuong.utils;

import java.net.URL;
import java.util.logging.Logger;

public class PathUtils {

	private static final Logger LOGGER = Logger.getLogger(PathUtils.class.getName());

	public static URL getPath(String filePath) {
		URL path = PathUtils.class.getResource("/resources" + filePath);
		if (path == null) {
			path = PathUtils.class.getResource(filePath);
		}
		return path;
	}

	public static URL getViewPath(String viewFileName) {
		return getPath("/views/" + viewFileName);
	}

	public static URL getDatabaseCfgPath() {
		return getPath("/config/database.cfg.xml");
	}

	public static URL getImagePath(String fileName) {
		return getPath("/images/" + fileName);
	}

	private PathUtils() {

	}

}

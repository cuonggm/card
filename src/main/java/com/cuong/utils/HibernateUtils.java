package com.cuong.utils;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtils {

	private static final Logger LOGGER = Logger.getLogger(HibernateUtils.class.getName());

	private static SessionFactory sessionFactory;

	private static SessionFactory setup() {
		StandardServiceRegistry registry = null;
		try {
			Path configFilePath = Paths.get(PathUtils.getDatabaseCfgPath().toURI());
			File configFile = configFilePath.toFile();
			LOGGER.info("Hibernate config file location: " + configFile.getPath());
			registry = new StandardServiceRegistryBuilder().configure(configFile).build();
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
			return sessionFactory;
		} catch (Exception e) {
			StandardServiceRegistryBuilder.destroy(registry);
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			return null;
		}
	}

	private HibernateUtils() {

	}

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			return setup();
		} else {
			return sessionFactory;
		}
	}

}

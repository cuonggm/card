package com.cuong.utils;

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
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		try {
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

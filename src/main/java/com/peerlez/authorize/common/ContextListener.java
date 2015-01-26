package com.peerlez.authorize.common;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.peerlez.authorize.db.DataSourceProvider;

/**
 * This is initialized once on Web Application deployment. Stores DB connection
 * as ServletContext attribute to allow it to be used and accessed on the 
 * anywhere from this application.
 *
 * @author A.Sillanpaa
 *
 */
@WebListener
public class ContextListener implements ServletContextListener {

	private static final Logger LOG = LoggerFactory
		.getLogger(ContextListener.class);

	/**
	 * Context initialized event.
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) 
			throws RuntimeException {
		
		ServletContext servletContext = sce.getServletContext();
		LOG.info("Servlet context initalized");
		/* store DB connection to ServletContext */
		try {
			servletContext.setAttribute("dbConnection", DataSourceProvider
					.instance().getDbConnection());
		} catch (SQLException | NamingException e) {
			LOG.error("Couldn't find the datasource", e);
			throw new RuntimeException("Data source is not avaiable", e);
		}
	}

	/**
	 * Context destroyed event.
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		LOG.info("Servlet context destroyed");
	}
}
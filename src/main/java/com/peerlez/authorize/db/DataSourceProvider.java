package com.peerlez.authorize.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Class to provide DB connection to be use to query databases.
 * 
 * @author A.Sillanpaa
 *
 */
public final class DataSourceProvider {

	private final static String DBPATH = "jdbc/postgres";
	private static DataSourceProvider _instance;
	private Connection _connection;
	private DataSource _dataSource;

    /**
     * Constructor to initialize data sources.
     * 
     * @throws NamingException
     * 				if a naming exception is encountered 
     * @throws SQLException
     * 				if a database access error occurs
     */
    private DataSourceProvider() throws NamingException, SQLException {
		//TODO Make SEPARATE DB connections for READ AND WRITE
    	 InitialContext cxt = new InitialContext();
    	 _dataSource = (DataSource) cxt.lookup("java:comp/env");
    	 _connection = _dataSource.getConnection();
    }

    /**
     * Gets singleton instance of this.
     * 
     * @return instance of this
     * 
     * @throws SQLException
     * 				if a database access error occurs
     * @throws NamingException
     * 				if a naming exception is encountered
     */
    public static final synchronized DataSourceProvider instance() 
    				throws SQLException, NamingException {
   
    	if (_instance == null) {
    		_instance = new DataSourceProvider();
    	}
    	return _instance;
    }
    
    /**
     * Get open DB {@link Connection}.
     * 
	 * @return {@link Connection}
	 * 				open DB connection to use
	 * 
	 * @throws SQLException
	 * 				if a database access error occurs
	 */
	public final Connection getDbConnection() throws SQLException  {
		if (_connection == null || _connection.isClosed()) {
			_connection = _dataSource.getConnection();
		}
		return _connection;
	}
}
package com.peerlez.authorize.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class to provide utilities to execute stored procedures on an database.
 * 
 * @author A.Sillanpaa
 *
 */
public final class DbQuery {

	private Connection _connection;
	private ResultSet _result;
	private CallableStatement _callStatement;
	
	/**
	 * Constructor
	 * 
	 * @param connection
	 * 				DB connection to use to make DB queries
	 */
	public DbQuery(final Connection connection) {
		_connection = connection;
	}

	/**
	 * Creates a SQL query string using the stored procedure name, returns value
	 * and the parameters for the stored procedure.
	 * 
	 * @param storedProcedureName
	 * 				The name of the stored procedure that will be executed
	 * @param returnsValue
	 * 				{@code boolean} <code>true</code> if stored procedure
	 * 				returns an value; <code>false</code> otherwise. 
	 * @param parameters
	 * 				The parameters of the stored procedure
	 * @return {@link ResultSet}
	 * 				the resulting ResultSet from the executing of the stored
	 * 				procedure
	 * 
	 * @throws SQLException
	 * 				if the stored procedure doesn't exist; or the execution 
	 * 				failed
	 */
	public synchronized ResultSet initializeCallable(String 
			storedProcedureName, final boolean returnsValue, final 
			SqlParameters[] parameters) throws SQLException {

		StringBuilder constructCallable = new StringBuilder("{");
		if (returnsValue) {
			constructCallable.append("?= ");
		}
		
		constructCallable.append("call ").append(storedProcedureName);

		if (parameters == null || parameters.length == 0) {
			constructCallable.append("() }");
			return executeCallable(constructCallable.toString(), null);
		}
		
		int length = parameters.length;
		
		constructCallable.append(" ( ").append("? ");

		/* add parameters*/
		for(int i = 1; i < length; i++) {
			constructCallable.append(",? ");
		}

		constructCallable.append(") }");
		return executeCallable(constructCallable.toString(), parameters);
	}

	/**
	 * Executes a stored procedure that returns data from the database.
	 * 
	 * @param callable
	 * 				computed SQL query String 
	 * @param param
	 * 				The parameters of the stored procedure
	 * @return {@link ResultSet}
	 * 				the resulting ResultSet from the executing of the stored
	 * 				procedure
	 * 
	 * @throws SQLException
	 * 				if the stored procedure doesn't exist; or the execution 
	 * 				failed
	 */
	private ResultSet executeCallable(String callable, SqlParameters[] param) 
			throws SQLException {

		//TODO fix this!
//		for(int index = 0; index < param.length; index++) {
//	         SqlParameters params = param[index];
//	         Object value = params.getValue();
//	         statement.setObject(params.getIndex(), params.getValue());
//	    }
		
		try {
			_callStatement = constructStatement(callable);
			_callStatement.execute();
			_result = (ResultSet) _callStatement.getObject(0);
			return _result;
		} finally {
			if (_callStatement != null) {
				_callStatement.close();
		    }
			closeDbConnection();
			_result.close();
		}
	}
	
	/**
	 * Creates {@link CallableStatement} object with the given SQL clause.
	 * 
	 * @param callable 
	 * 				the SQL clause that will be executed
	 * 
	 * @return the initialized 
	 * 				{@link CallableStatement}
	 *
	 * @throws SQLException 
	 * 				if a database access error occurs
	 */
	private CallableStatement constructStatement(String callable) 
			throws SQLException {

		CallableStatement statement = _connection.prepareCall(callable,
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

	return statement;
	}
	
	/**
	 * Close the {@link Connection} and release it's database and JDBC 
	 * resources.
 	 * 
	 * @throws SQLException
	 * 				if a database access error occurs
	 */
	private synchronized void closeDbConnection() throws SQLException {

		if (_connection == null || !_connection.isClosed()) {
				_connection.close();
				_connection = null;
		}
	}
}
package jsoft;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPoolImplV2 implements ConnectionPool {

	@Override
	public synchronized Connection getConnection(String objectName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public synchronized void releaseConnection(Connection con, String objectName) throws SQLException {
		// TODO Auto-generated method stub

	}

}

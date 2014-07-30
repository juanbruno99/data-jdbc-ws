package com.bemach.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbConnection {

	private static final Logger log = Logger.getLogger(DbConnection.class
			.getName());
	private static final String ERROR_MSG = "ERROR: ";
	private Connection conn = null;

	public Connection getConn() {
		return this.conn;
	}

	private DbConnection(String driverName, String subprot, String host,
			String port, String db, String uid, String psw) {
		log.info("Getting DB connection...");

		try {
			Class.forName(driverName);
			String url = String.format(
					"jdbc:%s://%s:%s/%s?user=%s&password=%s", subprot, host,
					port, db, uid, psw);
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			log.log(Level.SEVERE, ERROR_MSG + e);
		} catch (ClassNotFoundException ne) {
			log.log(Level.SEVERE, ERROR_MSG + ne);
		}
	}

	public static DbConnection getInstance(String driverName, String subprot, String host,
			String port, String db, String uid, String psw) {
		return new DbConnection(driverName, subprot, host, port, db, uid, psw);
	}
	
	public void close() {
		try {
			conn.close();
		} catch (Exception e) {
			log.log(Level.SEVERE, ERROR_MSG + e);
		}
	}

}

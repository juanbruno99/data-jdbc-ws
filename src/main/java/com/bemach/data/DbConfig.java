package com.bemach.data;
/**
 * @author Juan Bruno
 * Placeholder class for DB config
 * */
public class DbConfig {
	private String subprot = "mysql";
	private String host = "localhost";
	private String port = "3306";
	private String db = "employees";
	private String uid = "root";
	private String psw = "";
	private String driverName = "com.mysql.jdbc.Driver";
	
	public String getSubprot() {
		return subprot;
	}
	public void setSubprot(String subprot) {
		this.subprot = subprot;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getDb() {
		return db;
	}
	public void setDb(String db) {
		this.db = db;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getPsw() {
		return psw;
	}
	public void setPsw(String psw) {
		this.psw = psw;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	
}

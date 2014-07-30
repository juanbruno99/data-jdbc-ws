package com.bemach.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.logging.Logger;

/**
 * This class provides basic access to the employees2 table in the database We
 * create a class that allows access to this table. This class is called
 * EmployeeDao and allows four basic operations on a row of the employees table.
 * 
 * */
public class EmployeeDao {
	private static final Logger log = Logger.getLogger(EmployeeDao.class
			.getName());
	private DbConfig cfg = null;

	public EmployeeDao(DbConfig cfg) {
		this.cfg = cfg;
		log.info("Constructing Employee Dao...");
	}

	/**
	 * From a ResultSet returns an Employee record.
	 * 
	 * @param rs
	 * @return Employee
	 */
	protected Employee getEmployee(ResultSet rs) throws SQLException {
		Employee empl = new Employee();
		Calendar cal = Calendar.getInstance();
		empl.setEmplNo(rs.getInt("emp_no"));
		cal.setTimeInMillis(rs.getTimestamp("birth_date").getTime());
		empl.setBirthDate(cal);
		empl.setFirstName(rs.getString("first_name"));
		empl.setLastName(rs.getString("last_name"));
		empl.setGender(rs.getString("gender"));
		cal = Calendar.getInstance();
		cal.setTimeInMillis(rs.getTimestamp("hire_date").getTime());
		empl.setHireDate(cal);
		return empl;
	}

	public int createEmployee(Employee empl) throws SQLException {
		log.info("Create an employee");
		DbConnection dbConnection = DbConnection.getInstance(
				cfg.getDriverName(), cfg.getSubprot(), cfg.getHost(),
				cfg.getPort(), cfg.getDb(), cfg.getUid(), cfg.getPsw());

		String sql = "SELECT MAX(EMP_NO) FROM EMPLOYEES";
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = dbConnection.getConn().prepareStatement(sql);
			stmt.execute();
			rs = stmt.getResultSet();
			rs.next();
			int nextEmplNo = rs.getInt(1);

			sql = "INSERT INTO EMPLOYEES (EMP_NO, BIRTH_DATE, FIRST_NAME, LAST_NAME, GENDER, HIRE_DATE) "
					+ "VALUES (?,?,?,?,?,?)";
			stmt = dbConnection.getConn().prepareStatement(sql);
			int idx = 1;
			stmt.setInt(idx++, ++nextEmplNo);
			Timestamp ts = new Timestamp(empl.getBirthDate().getTimeInMillis());
			stmt.setTimestamp(idx++, ts);
			stmt.setString(idx++, empl.getFirstName());
			stmt.setString(idx++, empl.getLastName());
			stmt.setString(idx++, empl.getGender());
			ts = new Timestamp(empl.getHireDate().getTimeInMillis());
			stmt.setTimestamp(idx++, ts);
			stmt.execute();

			return nextEmplNo;

		} finally {
			if (stmt != null)
				stmt.close();
			if (rs != null)
				rs.close();

			dbConnection.close();
		}
	}

	public boolean updateEmpl(Employee empl) throws SQLException {
		log.info("Update an employee...");
		DbConnection dbConn = DbConnection.getInstance(cfg.getDriverName(),
				cfg.getSubprot(), cfg.getHost(), cfg.getPort(), cfg.getDb(),
				cfg.getUid(), cfg.getPsw());

		String sql = "UPDATE EMPLOYEES SET BIRTH_DATE=?, FIRST_NAME=?, LAST_NAME=?, GENDER=?, HIRE_DATE=? WHERE EMP_NO = ?";
		PreparedStatement stmt = null;

		try {
			stmt = dbConn.getConn().prepareStatement(sql);
			int idx = 1;
			Timestamp ts = new Timestamp(empl.getBirthDate().getTimeInMillis());
			stmt.setTimestamp(idx++, ts);
			stmt.setString(idx++, empl.getFirstName());
			stmt.setString(idx++, empl.getLastName());
			stmt.setString(idx++, empl.getGender());
			ts = new Timestamp(empl.getHireDate().getTimeInMillis());
			stmt.setTimestamp(idx++, ts);
			stmt.setInt(idx++, (int) empl.getEmplNo());
			stmt.execute();
			return true;
		} finally {
			if (stmt != null) {
				stmt.close();
			}

			dbConn.close();
		}
	}

	public boolean deleteEmpl(int emplNo) throws SQLException {
		log.info("Delete an employee");
		DbConnection dbConn = DbConnection.getInstance(cfg.getDriverName(),
				cfg.getSubprot(), cfg.getHost(), cfg.getPort(), cfg.getDb(),
				cfg.getUid(), cfg.getPsw());
		String sql = "DELETE FROM EMPLOYEES WHERE EMP_NO=?";
		PreparedStatement stmt = null;
		try {
			stmt = dbConn.getConn().prepareStatement(sql);
			stmt.setInt(1, emplNo);

			stmt.execute();
			return true;

		} finally {
			if (stmt != null)
				stmt.close();
			dbConn.close();
		}
	}

	/**
	 * Get an employee of a given unique employee number * @param emplNo * @return
	 */
	public Employee getEmpl(int emplNo) throws SQLException {
		log.info("Getting employee by Employee number: " + emplNo);
		DbConnection dbConn = DbConnection.getInstance(cfg.getDriverName(),
				cfg.getSubprot(), cfg.getHost(), cfg.getPort(), cfg.getDb(),
				cfg.getUid(), cfg.getPsw());
		String sql = "SELECT * FROM EMPLOYEES WHERE EMP_NO=?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = dbConn.getConn().prepareStatement(sql);
			stmt.setInt(1, emplNo);
			if (stmt.execute()) {
				rs = stmt.getResultSet();
				if (rs != null && rs.next()) {
				return getEmployee(rs); }
			}
		} finally {
			if (rs != null) {
			}
			rs.close();
		}
		dbConn.close();
		return null;
	}
}

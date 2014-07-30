package com.bemach.data;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.logging.Logger;

import org.junit.Test;

import junit.framework.TestCase;

public class EmployeeDataTest extends TestCase {
	private static final Logger logger = Logger
			.getLogger(EmployeeDataTest.class.getName());
	private EmployeeDao dao;

	protected void setUp() throws Exception {
		logger.info("Testing employee dao class ...");
		DbConfig cfg = new DbConfig();
		cfg.setDriverName("com.mysql.jdbc.Driver");
		cfg.setHost("localhost");
		cfg.setPort("3306");
		cfg.setDb("employees");
		cfg.setUid("root");
		cfg.setPsw("");
		dao = new EmployeeDao(cfg);
	}

	public void testGetEmplByEmplNo() throws SQLException {
		Employee empl = dao.getEmpl(10327);
		assertTrue("*** ERROR NULL ***", empl != null);
		logger.info("found " + empl.getFirstName() + "/" + empl.getLastName());
	}

	@Test
	public void testCRUDEmpl() throws SQLException {
		logger.info(">>> get empl");
		Employee empl = dao.getEmpl(10001);
		empl.setFirstName("Test_First");
		empl.setLastName("Test_Last");
		Timestamp ts = Timestamp.valueOf("1970-01-01 0:0:0.0");
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(ts.getTime());
		empl.setBirthDate(cal);
		ts = Timestamp.valueOf("1970-01-01 0:0:0.0");
		cal.setTimeInMillis(ts.getTime());
		empl.setHireDate(cal);
		empl.setGender("F");
		logger.info(">>> create empl");
		int newEmplNo = dao.createEmployee(empl);
		logger.info(">>> get new empl");
		Employee newEmpl = dao.getEmpl(newEmplNo);
		newEmpl.setGender("M");
		logger.info(">>> update new empl");
		dao.updateEmpl(newEmpl);
		logger.info(">>> get new empl again");
		newEmpl = dao.getEmpl(newEmplNo);
		printOutput(newEmpl);
		logger.info(">>> delete new empl");
		dao.deleteEmpl(newEmplNo);
	}

	private void printOutput(Employee empl) {
		StringBuffer sb = new StringBuffer();
		sb.append(", emplno=").append(empl.getEmplNo());
		sb.append(", fname=").append(empl.getFirstName());
		sb.append(", lname=").append(empl.getLastName());
		sb.append(", hire=").append(empl.getHireDate());
		sb.append(", birth=").append(empl.getBirthDate());
		sb.append(", gender=").append(empl.getGender());
		logger.info(sb.toString());
	}

	protected void tearDown() throws Exception {
	}

}

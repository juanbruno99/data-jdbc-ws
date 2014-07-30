package com.bemach.data;

import java.io.Serializable;
import java.util.Calendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/*We create an Employee data object that contains an employee record. 
 * Employee is a Java class that uses Java Architecture for XML Binding (JAXB) annotations to assist 
 * the marshalling process. 
 * JAXB allows Java developer to use Java API to read and write objects to and from an XML document
 * **/

@XmlRootElement(name = "EmployeeService")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "employee")
public class Employee implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@XmlElement(required = true)
	private long emplNo;
	@XmlElement(required = true)
	private String firstName;
	@XmlElement(required = true)
	private String lastName;
	@XmlElement(required = true)
	private Calendar birthDate;
	@XmlElement(required = true)
	private String gender;
	@XmlElement(required = true)
	private Calendar hireDate;
	
	public long getEmplNo() {
		return emplNo;
	}
	public void setEmplNo(long emplNo) {
		this.emplNo = emplNo;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Calendar getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Calendar birthDate) {
		this.birthDate = birthDate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Calendar getHireDate() {
		return hireDate;
	}
	public void setHireDate(Calendar hireDate) {
		this.hireDate = hireDate;
	}
	
	
}

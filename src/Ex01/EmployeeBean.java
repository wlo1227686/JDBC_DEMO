package Ex01;

import java.sql.Date;

public class EmployeeBean {
	int    empNo;
	String name;
	String address;
	Date   birthday;
	int    salary;
	double weight;
	
	public EmployeeBean() {
		super();
	}
	
	public EmployeeBean(String name, String address, Date birthday, int salary, double weight) {
		super();
		this.name = name;
		this.address = address;
		this.birthday = birthday;
		this.salary = salary;
		this.weight = weight;
	}
	
	public EmployeeBean(int empNo, String name, String address, Date birthday, int salary, double weight) {
		super();
		this.empNo = empNo;
		this.name = name;
		this.address = address;
		this.birthday = birthday;
		this.salary = salary;
		this.weight = weight;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	
	
}

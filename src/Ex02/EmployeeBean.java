package Ex02;

import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.Timestamp;

public class EmployeeBean {
	int empNo;
	String name;
	String address;
	Date birthday;
	int salary;
	double weight;
	Blob picture; // BLOB: Binary Large OBject 存圖檔
	String filename; // 圖片檔的檔名
	Clob comment; // CLOB: Character Large OBject 存文字檔
	Timestamp createTime; // java.sql.Timestamp 用來存放下單時間

	public EmployeeBean() {
		super();
	}

	public EmployeeBean(String name, String address, Date birthday, int salary, double weight, Blob picture,
			Clob comment, Timestamp createTime, String filename) {
		super();
		this.name = name;
		this.address = address;
		this.birthday = birthday;
		this.salary = salary;
		this.weight = weight;
		this.picture = picture;
		this.comment = comment;
		this.createTime = createTime;
		this.filename = filename;
	}

	public EmployeeBean(int empNo, String name, String address, Date birthday, int salary, double weight, Blob picture,
			Clob comment, Timestamp createTime, String filename) {
		super();
		this.empNo = empNo;
		this.name = name;
		this.address = address;
		this.birthday = birthday;
		this.salary = salary;
		this.weight = weight;
		this.picture = picture;
		this.comment = comment;
		this.createTime = createTime;
		this.filename = filename;
	}

	public Blob getPicture() {
		return picture;
	}

	public void setPicture(Blob picture) {
		this.picture = picture;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Clob getComment() {
		return comment;
	}

	public void setComment(Clob comment) {
		this.comment = comment;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
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

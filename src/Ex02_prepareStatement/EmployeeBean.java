package Ex02_prepareStatement;

import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.Timestamp;

public class EmployeeBean {
	int    empno;
	String empname;
	String address;
	Date   birthday;
	String title;
	int  money;
	Blob picture; // BLOB: Binary Large OBject �s����
	Clob Comment;  // CLOB: Character Large OBject �s��r��
	String filename;
	Timestamp createtime;
	public EmployeeBean(String empname, String address, Date birthday, String title, int money, Blob picture,
			Clob Comment, String filename, Timestamp createtime) {
		super();
		this.empname = empname;
		this.address = address;
		this.birthday = birthday;
		this.title = title;
		this.money = money;
		this.picture = picture;
		this.Comment = Comment;
		this.filename = filename;
		this.createtime = createtime;
	}
	public EmployeeBean(int empno, String empname, String address, Date birthday, String title, int money, Blob picture,
			Clob Comment, String filename, Timestamp createtime) {
		super();
		this.empno = empno;
		this.empname = empname;
		this.address = address;
		this.birthday = birthday;
		this.title = title;
		this.money = money;
		this.picture = picture;
		this.Comment = Comment;
		this.filename = filename;
		this.createtime = createtime;
	}
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public Blob getPicture() {
		return picture;
	}
	public void setPicture(Blob pitture) {
		this.picture = pitture;
	}
	public Clob getComment() {
		return Comment;
	}
	public void setComment(Clob Comment) {
		this.Comment = Comment;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	
}

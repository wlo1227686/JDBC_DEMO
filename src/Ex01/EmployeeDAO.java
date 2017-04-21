package Ex01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
	String url_login_str = "jdbc:mysql://localhost/demo_jdbc_db"
		 		 + "?useSSL=true"
				 + "&useUnicode=yes"
				 + "&characterEncoding=UTF-8";	
	int Result_n=0;//�^�Ǩ�sql�R�O�Ҽv�T������`��(insert�Bdelete�Bupdate_str)
	EmployeeBean mb;//���w��Ʈ榡(FindAllEmployee�BFindByPrimaryKey)
	
	public void insert(EmployeeBean emp){
		
		String name = emp.getName();
		String address = emp.getAddress();
		String birthday = emp.getBirthday().toString();//Date�榡 �ন String
		int  salary= emp.getSalary();
		double weight = emp.getWeight();	
		
		String insert_str = "INSERT INTO Emp VALUES(Null,"
							+ "'"+name+"',"
							+ "'"+address+"',"
							+ "'"+birthday+"',"
							+ "'"+salary+"',"
							+ "'"+weight+"')";		
		
	// try(java 7.0 ���@�۰��������귽){	;}
		try (Connection con = DriverManager.getConnection(url_login_str,"root","000000");){
			
			Class.forName("com.mysql.jdbc.Driver");
			//���Jmysql�M�Ϊ����O��(com.mysql.jdbc.Driver)�A�����O�ɥѸӸ�Ʈw�t�өҴ���
			
			Statement stmt = con.createStatement();
			
			//System.out.println(insert_str);//�˵�SQL�y�k�O�_���T
						
			Result_n=stmt.executeUpdate(insert_str);
			//�ϥ�executeUpdate��k�|�^�Ǥ@�Ӿ�ƭȡA�Ӿ�ƭȥN���sql�R�O(insert_str)�Ҽv�T���`��
			
			System.out.println("<�s�W���>	 n:"+Result_n);
			
			System.out.println("��Ʈw���O���槹��....");
		} catch (ClassNotFoundException e) {
			
			System.out.println("�䤣�����O:"+e.getMessage());	
			
		} catch (SQLException e) {
			
			System.out.println("�o��SQL�ҥ~:"+e.getMessage());	
			
		}
	}
	
	public void delete(int empNo){

		String delete_str = "DELETE FROM EMP WHERE EMP_NO = "+empNo;	
		
	// try(java 7.0 ���@�۰��������귽){	;}
		try (Connection con = DriverManager.getConnection(url_login_str,"root","000000");){
			
			Class.forName("com.mysql.jdbc.Driver");
			//���Jmysql�M�Ϊ����O��(com.mysql.jdbc.Driver)�A�����O�ɥѸӸ�Ʈw�t�өҴ���
			
			Statement stmt = con.createStatement();
			
			//System.out.println(delete_str);//�˵�SQL�y�k�O�_���T
						
			Result_n=stmt.executeUpdate(delete_str);
			//�ϥ�executeUpdate��k�|�^�Ǥ@�Ӿ�ƭȡA�Ӿ�ƭȥN���sql�R�O(insert_str)�Ҽv�T���`��
			
			System.out.println("<�R�����>	 n:"+Result_n);
			
			System.out.println("��Ʈw���O���槹��....");
		} catch (ClassNotFoundException e) {
			
			System.out.println("�䤣�����O:"+e.getMessage());	
			
		} catch (SQLException e) {
			
			System.out.println("�o��SQL�ҥ~:"+e.getMessage());	
			
		}
	}
	
	public void update(EmployeeBean emp){
		
		String name = emp.getName();
		String address = emp.getAddress();
		String birthday = emp.getBirthday().toString();//Date�榡 �ন String
		int  salary= emp.getSalary();
		double weight = emp.getWeight();	
		
		String update_str = "UPDATE EMP SET emp_name = '"+name+"',"
						  + "emp_addr='"+address+"',"
						  + "salary="+salary+","
						  + "weight="+weight+","
						  + "birthday='"+birthday+"'"
						  		+ "WHERE emp_no="+emp.getEmpNo();
		
		try (Connection con = DriverManager.getConnection(url_login_str,"root","000000");){
			
			Class.forName("com.mysql.jdbc.Driver");
			//���Jmysql�M�Ϊ����O��(com.mysql.jdbc.Driver)�A�����O�ɥѸӸ�Ʈw�t�өҴ���
			
			Statement stmt = con.createStatement();
			
			//System.out.println(insert_str);//�˵�SQL�y�k�O�_���T
						
			Result_n=stmt.executeUpdate(update_str);
			//�ϥ�executeUpdate��k�|�^�Ǥ@�Ӿ�ƭȡA�Ӿ�ƭȥN���sql�R�O(update_str)�Ҽv�T���`��
			
		    System.out.println("<�ק���>	ok ,n:"+Result_n);
			
			System.out.println("��Ʈw���O���槹��....");
		} catch (ClassNotFoundException e) {
			
			System.out.println("�䤣�����O:"+e.getMessage());	
			
		} catch (SQLException e) {
			
			System.out.println("�o��SQL�ҥ~:"+e.getMessage());	
			
		}		
	}
	
	
	public EmployeeBean findByPrimaryKey(int key_value){
		String SelectPrimaryKey_str = "SELECT * FROM EMP WHERE EMP_NO = "+key_value;		
	// try(java 7.0 ���@�۰��������귽){	;}
		try (Connection con = DriverManager.getConnection(url_login_str,"root","000000");){
			
			Class.forName("com.mysql.jdbc.Driver");
			//���Jmysql�M�Ϊ����O��(com.mysql.jdbc.Driver)�A�����O�ɥѸӸ�Ʈw�t�өҴ���
			
			Statement stmt = con.createStatement();
			
			//System.out.println(SelectPrimaryKey_str);//�˵�SQL�y�k�O�_���T
						
			ResultSet rs =stmt.executeQuery(SelectPrimaryKey_str);
			//�ϥ�executeUpdate��k�|�^�Ǥ@�Ӿ�ƭȡA�Ӿ�ƭȥN���sql�R�O(insert_str)�Ҽv�T���`��
			if(rs.next()){
				int empNo = rs.getInt("emp_no");
				String name = rs.getString("emp_name");
				String address = rs.getString("emp_addr");
				Date Birthday = rs.getDate("Birthday");
				int salary=rs.getInt("salary");
				double weight=rs.getDouble("weight");
				mb = new EmployeeBean(empNo,name,address,Birthday,salary,weight);
				System.out.println("Query OK name ="+name);
			}			
			System.out.println("��Ʈw���O���槹��....");
		} catch (ClassNotFoundException e) {
			
			System.out.println("�䤣�����O:"+e.getMessage());	
			
		} catch (SQLException e) {
			
			System.out.println("�o��SQL�ҥ~:"+e.getMessage());	
			
		}
		return mb ;
	}
	
	public List<EmployeeBean>findAllEmployees(){
		List<EmployeeBean> list =new ArrayList<>();
		String SelectAll_str = "SELECT * FROM Emp";
	
	// try(java 7.0 ���@�۰��������귽){	;}
		try (Connection con = DriverManager.getConnection(url_login_str,"root","000000");){
			
			Class.forName("com.mysql.jdbc.Driver");
			//���Jmysql�M�Ϊ����O��(com.mysql.jdbc.Driver)�A�����O�ɥѸӸ�Ʈw�t�өҴ���
			
			Statement stmt = con.createStatement();
			
			//System.out.println(SelectAll_str);//�˵�SQL�y�k�O�_���T
						
			ResultSet rs=stmt.executeQuery(SelectAll_str);
			//�ϥ�executeQuery��k�����f�tResultSet���O
			//ResultSet �Osql�Ҵ��Ѫ����O�M���ΨӦ^��executeQuery()�����G
			
			while(rs.next()){
				int empNo = rs.getInt("emp_no");
				String name = rs.getString("emp_name");
				String address = rs.getString("emp_addr");
				Date Birthday = rs.getDate("Birthday");
				int salary=rs.getInt("salary");
				double weight=rs.getDouble("weight");
				
				mb = new EmployeeBean(empNo,name,address,Birthday,salary,weight);
				
				list.add(mb);
				//�N�C����ƨ̷�EmployeeBean�ҳW�d���榡�g�Jlist				
			}
						
			System.out.println("��Ʈw���O���槹��....");
		} catch (ClassNotFoundException e) {
			
			System.out.println("�䤣�����O:"+e.getMessage());	
			
		} catch (SQLException e) {
			
			System.out.println("�o��SQL�ҥ~:"+e.getMessage());	
			
		}

		return list;
		//�̫�O�o�^��list��Main
	}



}

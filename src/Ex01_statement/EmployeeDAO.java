package Ex01_statement;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
	String url_login_str = "jdbc:mysql://localhost/statement_jdbc_db"
		 		 + "?useSSL=true"
				 + "&useUnicode=yes"
				 + "&characterEncoding=UTF-8";	
	int Result_n=0;//�^�Ǩ�sql�R�O�Ҽv�T������`��(insert�Bdelete�Bupdate_str)
	EmployeeBean mb;//���w��Ʈ榡(FindAllEmployee�BFindByPrimaryKey)


	public void insert(EmployeeBean emp){
		
		String name = emp.getEmpname();
		String address = emp.getAddress();
		Date birthday = emp.getBirthday();//Date�榡 �ন String
		String title = emp.getTitle();
		int money = emp.getMoney();
		String filename = emp.getFilename();
		Timestamp createTime = emp.getCreatetime();
		String insert_str = "INSERT INTO emp VALUES(Null,"
							+ "'"+name+"',"
							+ "'"+address+"',"
							+ "'"+birthday+"',"
							+ "'"+title+"',"
							+ "'"+money+"',"
							+ "'"+filename+"',"
							+ "'"+createTime+"')";		
		
	// try(java 7.0 ���@�۰��������귽){	;}
		try (Connection con = DriverManager.getConnection(url_login_str,"root","000000");){
			
			//Class.forName("com.mysql.jdbc.Driver");	//JDBC4.0����i�ٲ�(ClassNotFoundException)	
			//���Jmysql�M�Ϊ����O��(com.mysql.jdbc.Driver)�A�����O�ɥѸӸ�Ʈw�t�өҴ���
			
			Statement stmt = con.createStatement();
			
//			System.out.println(insert_str);//�˵�SQL�y�k�O�_���T
						
			Result_n=stmt.executeUpdate(insert_str);
			//�ϥ�executeUpdate��k�|�^�Ǥ@�Ӿ�ƭȡA�Ӿ�ƭȥN���sql�R�O(insert_str)�Ҽv�T���`��
			
			System.out.println("(DML,N�������)�s�W���: N="+Result_n);
			
			System.out.println("��Ʈw���O���槹��....");
//		} catch (ClassNotFoundException e) {
//			
//			System.out.println("�䤣�����O:"+e.getMessage());	
//			
		} catch (SQLException e) {
			
			System.out.println("123�o��SQL�ҥ~:"+e.getMessage());	
			
		}
	}
	
	public void delete(int empNo){

		String delete_str = "DELETE FROM EMP WHERE EMP_NO = "+empNo;	
		
	// try(java 7.0 ���@�۰��������귽){	;}
		try (Connection con = DriverManager.getConnection(url_login_str,"root","000000");){
			
			//Class.forName("com.mysql.jdbc.Driver");	//JDBC4.0����i�ٲ�(ClassNotFoundException)	
			//���Jmysql�M�Ϊ����O��(com.mysql.jdbc.Driver)�A�����O�ɥѸӸ�Ʈw�t�өҴ���
			
			Statement stmt = con.createStatement();
			
			//System.out.println(delete_str);//�˵�SQL�y�k�O�_���T
						
			Result_n=stmt.executeUpdate(delete_str);
			//�ϥ�executeUpdate��k�|�^�Ǥ@�Ӿ�ƭȡA�Ӿ�ƭȥN���sql�R�O(insert_str)�Ҽv�T���`��
			
			System.out.println("(DML,N�������)�R����"+empNo+"�����: N="+Result_n);
			
			System.out.println("��Ʈw���O���槹��....");
//		} catch (ClassNotFoundException e) {
//			
//			System.out.println("�䤣�����O:"+e.getMessage());	
//			
		} catch (SQLException e) {
			
			System.out.println("�o��SQL�ҥ~:"+e.getMessage());	
			
		}
	}
	
	public void update(EmployeeBean emp){
		int empNo = emp.getEmpno();
		String name = emp.getEmpname();
		String address = emp.getAddress();
		Date birthday = emp.getBirthday();//Date�榡 �ন String
		String title = emp.getTitle();
		int money = emp.getMoney();
		String filename = emp.getFilename();
		Timestamp createTime = emp.getCreatetime();
		String update_str = "UPDATE emp SET"
							+ " emp_name='"+name+"',"
							+ "emp_addr='"+address+"',"
							+ "birthday='"+birthday+"',"
							+ "title='"+title+"',"
							+ "money='"+money+"',"
							+ "filename='"+filename+"',"
							+ "createtime='"+createTime+"'WHERE emp_no="+empNo;	
		
		try (Connection con = DriverManager.getConnection(url_login_str,"root","000000");){
			
			//Class.forName("com.mysql.jdbc.Driver");	//JDBC4.0����i�ٲ�(ClassNotFoundException)	
			//���Jmysql�M�Ϊ����O��(com.mysql.jdbc.Driver)�A�����O�ɥѸӸ�Ʈw�t�өҴ���
			
			Statement stmt = con.createStatement();
			
			//System.out.println(update_str);//�˵�SQL�y�k�O�_���T
						
			Result_n=stmt.executeUpdate(update_str);
			//�ϥ�executeUpdate��k�|�^�Ǥ@�Ӿ�ƭȡA�Ӿ�ƭȥN���sql�R�O(update_str)�Ҽv�T���`��
			
		    System.out.println("(DML,N�������)�ק��"+empNo+"�����: N="+Result_n);
			
			System.out.println("��Ʈw���O���槹��....");
//		} catch (ClassNotFoundException e) {
//			
//			System.out.println("�䤣�����O:"+e.getMessage());	
//			
		} catch (SQLException e) {
			
			System.out.println("�o��SQL�ҥ~:"+e.getMessage());	
			
		}		
	}
	
	
	public EmployeeBean findByPrimaryKey(int key_value){
		String SelectPrimaryKey_str = "SELECT * FROM EMP WHERE EMP_NO = "+key_value;		
	// try(java 7.0 ���@�۰��������귽){	;}
		try (Connection con = DriverManager.getConnection(url_login_str,"root","000000");){
			
			//Class.forName("com.mysql.jdbc.Driver");	//JDBC4.0����i�ٲ�(ClassNotFoundException)	
			//���Jmysql�M�Ϊ����O��(com.mysql.jdbc.Driver)�A�����O�ɥѸӸ�Ʈw�t�өҴ���
			
			Statement stmt = con.createStatement();
			
			//System.out.println(SelectPrimaryKey_str);//�˵�SQL�y�k�O�_���T
						
			ResultSet rs =stmt.executeQuery(SelectPrimaryKey_str);
			//�ϥ�executeUpdate��k�|�^�Ǥ@�Ӿ�ƭȡA�Ӿ�ƭȥN���sql�R�O(insert_str)�Ҽv�T���`��
			if(rs.next()){
				int empNo = rs.getInt("emp_no");
				String empname = rs.getString("emp_name");
				String address = rs.getString("emp_addr");
				Date birthday = rs.getDate("birthday");
				String title = rs.getString("title");
				int money=rs.getInt("money");
				String filename = rs.getString("filename");
				Timestamp createtime=rs.getTimestamp("createtime");
				mb = new EmployeeBean(empNo,empname,address,birthday,title,money,filename,createtime);
				System.out.println("[SQL]Query OK empNo="+empNo);
			}			
			System.out.println("��Ʈw���O���槹��....");
//		} catch (ClassNotFoundException e) {
//			
//			System.out.println("�䤣�����O:"+e.getMessage());	
//			
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
			
			//Class.forName("com.mysql.jdbc.Driver");	//JDBC4.0����i�ٲ�(ClassNotFoundException)	
			//���Jmysql�M�Ϊ����O��(com.mysql.jdbc.Driver)�A�����O�ɥѸӸ�Ʈw�t�өҴ���
			
			Statement stmt = con.createStatement();
			
			//System.out.println(SelectAll_str);//�˵�SQL�y�k�O�_���T
						
			ResultSet rs=stmt.executeQuery(SelectAll_str);
			//�ϥ�executeQuery��k�����f�tResultSet���O
			//ResultSet �Osql�Ҵ��Ѫ����O�M���ΨӦ^��executeQuery()�����G
			
			while(rs.next()){
				int empNo = rs.getInt("emp_no");
				String empname = rs.getString("emp_name");
				String address = rs.getString("emp_addr");
				Date birthday = rs.getDate("birthday");
				String title = rs.getString("title");
				int money=rs.getInt("money");
				String filename = rs.getString("filename");
				Timestamp createtime=rs.getTimestamp("createtime");
				mb = new EmployeeBean(empNo,empname,address,birthday,title,money,filename,createtime);
				
				list.add(mb);
				//�N�C����ƨ̷�EmployeeBean�ҳW�d���榡�g�Jlist				
			}
						
			System.out.println("��Ʈw���O���槹��....");
//		} catch (ClassNotFoundException e) {
//			
//			System.out.println("�䤣�����O:"+e.getMessage());	
//			
		} catch (SQLException e) {
			
			System.out.println("�o��SQL�ҥ~:"+e.getMessage());	
			
		}

		return list;
		//�̫�O�o�^��list��Main
	}
//--------------------------------------------------------------------
	public static byte[] read_BinaryFile_To_ByteArray(String filename) {
		File file = new File(filename);
		if ( !file.exists()) {
			throw new RuntimeException("�L���ɮ�");	//�j����X�ҥ~�ƥ�
		}		
		byte[] read_file = new byte[8192];//�CŪ�@�����j�p
		byte[] ByteArray = null;
		int len = 0 ;
		try (
			FileInputStream fis = new FileInputStream(file);//Ū�J�O����
			ByteArrayOutputStream baos = new ByteArrayOutputStream();//�g�J��Ʈw				
			){
			
			while ((len=fis.read(read_file))!= -1){
				baos.write(read_file, 0, len);   // �f�Z: A.O.L.
			}
			ByteArray = baos.toByteArray();
		} catch (Exception e) {
			System.out.println("[�G�i���ɮ���줸�}�C����]"+e.getMessage());
		}
		return ByteArray;
	}

	public static char[] read_TextFile_To_CharArray(String filename,String code) {
		File file = new File(filename);
		if ( !file.exists()) {
			throw new RuntimeException("�L���ɮ�");
		}		
		char[] read_file = new char[8192];//�CŪ�@�����j�p
		char[] CharArray = null;
		int len = 0 ;
		try(
		BufferedReader bf = new BufferedReader(
							   new InputStreamReader(
								  new FileInputStream(filename),code));//Ū��
		
		CharArrayWriter caw = new CharArrayWriter();		
				) {
			while ((len=bf.read(read_file))!= -1){
				caw.write(read_file, 0, len);   // �f�Z: A.O.L.
			}
			CharArray = caw.toCharArray();
		} catch (Exception e) {
			System.out.println("[��r�ɮ���r���}�C����]"+e.getMessage());
		}
		return CharArray;
	}	
}

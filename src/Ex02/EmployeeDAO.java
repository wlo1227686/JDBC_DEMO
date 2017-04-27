package Ex02;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
	String url_login_str = "jdbc:mysql://localhost/jdbc_db"
		 		 + "?useSSL=true"
				 + "&useUnicode=yes"
				 + "&characterEncoding=UTF-8";	
	int Result_n=0;//�^�Ǩ�sql�R�O�Ҽv�T������`��(insert�Bdelete�Bupdate_str)
	EmployeeBean mb;//���w��Ʈ榡(FindAllEmployee�BFindByPrimaryKey)
	
	public void insert(EmployeeBean emp){
		
		String insert_str = "INSERT INTO Emp VALUES(Null,?,?,?,?,?,?,?,?,?)";		
		
	// try(java 7.0 ���@�۰��������귽){	;}
		try (Connection con = DriverManager.getConnection(url_login_str,"root","000000");
			 PreparedStatement pstmt = con.prepareStatement(insert_str);
				){
			
			//Class.forName("com.mysql.jdbc.Driver");	//JDBC4.0����i�ٲ�(ClassNotFoundException)	
			//���Jmysql�M�Ϊ����O��(com.mysql.jdbc.Driver)�A�����O�ɥѸӸ�Ʈw�t�өҴ���
			
			pstmt.setString(1, emp.getName());
			pstmt.setString(2, emp.getAddress());
			pstmt.setTimestamp(3, new Timestamp(emp.getBirthday().getTime()));//�ΦW��k
			pstmt.setInt(4, emp.getSalary());
			pstmt.setDouble(5, emp.getWeight());
			pstmt.setBlob(6, emp.getPicture());
			pstmt.setClob(7, emp.getComment());
			pstmt.setTimestamp(8, emp.getCreateTime());
			pstmt.setString(9, emp.getFilename());
									
			Result_n=pstmt.executeUpdate();
			//�ϥ�executeUpdate��k�|�^�Ǥ@�Ӿ�ƭȡA�Ӿ�ƭȥN���sql�R�O(insert_str)�Ҽv�T���`��
			
			System.out.println("<�s�W���>	 n:"+Result_n);
			
			System.out.println("��Ʈw���O���槹��....");
//		} catch (ClassNotFoundException e) {
//			
//			System.out.println("�䤣�����O:"+e.getMessage());	
//			
		} catch (SQLException e) {
			
			System.out.println("�o��SQL�ҥ~:"+e.getMessage());	
			
		}
	}
	
	public void delete(int empNo){

		String delete_str = "DELETE FROM EMP WHERE EMP_NO = ?";	
		
	// try(java 7.0 ���@�۰��������귽){	;}
		try (Connection con = DriverManager.getConnection(url_login_str,"root","000000");
				 PreparedStatement pstmt = con.prepareStatement(delete_str);				
				){
			
			//Class.forName("com.mysql.jdbc.Driver");	//JDBC4.0����i�ٲ�(ClassNotFoundException)	
			//���Jmysql�M�Ϊ����O��(com.mysql.jdbc.Driver)�A�����O�ɥѸӸ�Ʈw�t�өҴ���

			pstmt.setInt(1, empNo);
						
			Result_n=pstmt.executeUpdate();
			//�ϥ�executeUpdate��k�|�^�Ǥ@�Ӿ�ƭȡA�Ӿ�ƭȥN���sql�R�O(insert_str)�Ҽv�T���`��
			
			System.out.println("<�R�����  �s����"+empNo+"��>	 n:"+Result_n);
			
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
		String update_str=null;
		String update_str_createTime = "UPDATE EMP SET emp_name=?,"
													+ "emp_addr=?,"
													+ "birthday=?,"
													+   "salary=?,"
													+   "weight=?,"
													+  "picture=?,"
													+  "comment=?,"
												   +"createTime=?,"
													 + "filename=?"
													 	+ "WHERE emp_no=?";
		String update_str_UncreateTime = "UPDATE EMP SET emp_name=?,"
													  + "emp_addr=?,"
													  + "birthday=?,"
													  +   "salary=?,"
													  +   "weight=?,"
													  +  "picture=?,"
													  +  "comment=?,"
												      + "filename=?"
													 	+ "WHERE emp_no=?";
		if(emp.getCreateTime()==null){
			update_str=update_str_UncreateTime;
		}else{
			update_str=update_str_createTime;
		}
		try (Connection con = DriverManager.getConnection(url_login_str,"root","000000");
			 PreparedStatement pstmt = con.prepareStatement(update_str);
				){
			
			//Class.forName("com.mysql.jdbc.Driver");	//JDBC4.0����i�ٲ�(ClassNotFoundException)	
			//���Jmysql�M�Ϊ����O��(com.mysql.jdbc.Driver)�A�����O�ɥѸӸ�Ʈw�t�өҴ���
		if(emp.getCreateTime()==null){
			pstmt.setString(1, emp.getName());
			pstmt.setString(2, emp.getAddress());
			pstmt.setTimestamp(3, new Timestamp(emp.getBirthday().getTime()));//�ΦW��k
			pstmt.setInt(4, emp.getSalary());
			pstmt.setDouble(5, emp.getWeight());
			pstmt.setBlob(6, emp.getPicture());
			pstmt.setClob(7, emp.getComment());
			pstmt.setString(8, emp.getFilename());
			pstmt.setInt(9, emp.getEmpNo());				
		}else{	
			pstmt.setString(1, emp.getName());
			pstmt.setString(2, emp.getAddress());
			pstmt.setTimestamp(3, new Timestamp(emp.getBirthday().getTime()));//�ΦW��k
			pstmt.setInt(4, emp.getSalary());
			pstmt.setDouble(5, emp.getWeight());
			pstmt.setBlob(6, emp.getPicture());
			pstmt.setClob(7, emp.getComment());
			pstmt.setTimestamp(8, emp.getCreateTime());
			pstmt.setString(9, emp.getFilename());
			pstmt.setInt(10, emp.getEmpNo());	
		}
			Result_n=pstmt.executeUpdate();
			//�ϥ�executeUpdate��k�|�^�Ǥ@�Ӿ�ƭȡA�Ӿ�ƭȥN���sql�R�O(insert_str)�Ҽv�T���`��
			
			System.out.println("<�ק���:��"+emp.getEmpNo()+"��>	 n:"+Result_n);
			
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
		String SelectPrimaryKey_str = "SELECT * FROM EMP WHERE EMP_NO = ?";		
	// try(java 7.0 ���@�۰��������귽){	;}
		try (Connection con = DriverManager.getConnection(url_login_str,"root","000000");
			 PreparedStatement pstmt = con.prepareStatement(SelectPrimaryKey_str);
				){
			
			//Class.forName("com.mysql.jdbc.Driver");	//JDBC4.0����i�ٲ�(ClassNotFoundException)	
			//���Jmysql�M�Ϊ����O��(com.mysql.jdbc.Driver)�A�����O�ɥѸӸ�Ʈw�t�өҴ���
			pstmt.setInt(1, key_value);
			
						
			ResultSet rs = pstmt.executeQuery();
			//�ϥ�executeUpdate��k�|�^�Ǥ@�Ӿ�ƭȡA�Ӿ�ƭȥN���sql�R�O(insert_str)�Ҽv�T���`��
			if(rs.next()){
				int empNo = rs.getInt("emp_no");
				String name = rs.getString("emp_name");
				String address = rs.getString("emp_addr");
				Date birthday = rs.getDate("Birthday");
				int salary=rs.getInt("salary");
				double weight=rs.getDouble("weight");
				Blob picture = rs.getBlob("picture");
				Clob comment = rs.getClob("comment");
				 Timestamp createTime = rs.getTimestamp("create_time");
				 String filename = rs.getString("filename");
				 mb = new EmployeeBean(empNo,name,address,birthday,salary,weight,picture,comment,createTime,filename);
				
				System.out.println("Query OK name ="+name);
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
		try (Connection con = DriverManager.getConnection(url_login_str,"root","000000");
			 PreparedStatement pstmt = con.prepareStatement(SelectAll_str);	
				){
			
			//Class.forName("com.mysql.jdbc.Driver");	//JDBC4.0����i�ٲ�(ClassNotFoundException)	
			//���Jmysql�M�Ϊ����O��(com.mysql.jdbc.Driver)�A�����O�ɥѸӸ�Ʈw�t�өҴ���
			
			
			//System.out.println(SelectAll_str);//�˵�SQL�y�k�O�_���T
						
			ResultSet rs=pstmt.executeQuery();
			//�ϥ�executeQuery��k�����f�tResultSet���O
			//ResultSet �Osql�Ҵ��Ѫ����O�M���ΨӦ^��executeQuery()�����G
			
			while(rs.next()){
				int empNo = rs.getInt("emp_no");
				String name = rs.getString("emp_name");
				String address = rs.getString("emp_addr");
				Date Birthday = rs.getDate("Birthday");
				int salary=rs.getInt("salary");
				double weight=rs.getDouble("weight");
				Blob picture = rs.getBlob("picture");
				Clob comment = rs.getClob("comment");
				Timestamp createTime = rs.getTimestamp("create_time");
				String filename = rs.getString("filename");
				mb = new EmployeeBean(empNo,name,address,Birthday,salary,weight,picture,comment,createTime,filename);
				
				list.add(mb);
				//�N�C����ƨ̷�EmployeeBean�ҳW�d���榡�g�Jlist				
			}
						
			System.out.println("��Ʈw���O���槹��....");
//		} catch (ClassNotFoundException e) {
//			
//			System.out.println("�䤣�����O:"+e.getMessage());	
			
		} catch (SQLException e) {
			
			System.out.println("�o��SQL�ҥ~:"+e.getMessage());	
			
		}

		return list;
		//�̫�O�o�^��list��Main
	}

	public static byte[] binaryFileToByteArray2(String filename) {
		File f = new File(filename);
		if ( !f.exists()) {
			throw new RuntimeException("�L���ɮ�");	//�j����X�ҥ~�ƥ�
		}		
		byte[] read_file = new byte[8192];//�CŪ�@�����j�p
		byte[] wirte_database = null;
		int len = 0 ;
		try (
			FileInputStream fis = new FileInputStream(f);//Ū�J�O����
			ByteArrayOutputStream baos = new ByteArrayOutputStream();//�g�J��Ʈw				
				){
			while ((len=fis.read(read_file))!= -1){
				baos.write(read_file, 0, len);   // �f�Z: A.O.L.
			}
			wirte_database = baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wirte_database;
	}

	public static char[] textFileToCharArray1(String filename,String code) {
		File f = new File(filename);
		if ( !f.exists()) {
			throw new RuntimeException("�L���ɮ�");
		}		
		char[] read_file = new char[8192];//�CŪ�@�����j�p
		char[] wirte_database = null;
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
			wirte_database = caw.toCharArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wirte_database;
	}

}

package Ex02_prepareStatement;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
	String url_login_str = "jdbc:mysql://localhost/preparestatement_jdbc_db"
		 		 + "?useSSL=true"
				 + "&useUnicode=yes"
				 + "&characterEncoding=UTF-8";	
	int Result_n=0;//�^�Ǩ�sql�R�O�Ҽv�T������`��(insert�Bdelete�Bupdate_str)
	static EmployeeBean mb;//���w��Ʈ榡(FindAllEmployee�BFindByPrimaryKey)
	
	public void insert(EmployeeBean emp){
		
		String insert_str = "INSERT INTO Emp VALUES(Null,?,?,?,?,?,?,?,?,?)";		
		
	// try(java 7.0 ���@�۰��������귽){	;}
		try (Connection con = DriverManager.getConnection(url_login_str,"root","000000");
			 PreparedStatement pstmt = con.prepareStatement(insert_str);
				){
			
			//Class.forName("com.mysql.jdbc.Driver");	//JDBC4.0����i�ٲ�(ClassNotFoundException)	
			//���Jmysql�M�Ϊ����O��(com.mysql.jdbc.Driver)�A�����O�ɥѸӸ�Ʈw�t�өҴ���
			
			pstmt.setString(1, emp.getEmpname());
			pstmt.setString(2, emp.getAddress());
			pstmt.setTimestamp(3, new Timestamp(emp.getBirthday().getTime()));//�ΦW��k
			pstmt.setString(4, emp.getTitle());
			pstmt.setInt(5, emp.getMoney());
			pstmt.setBlob(6, emp.getPicture());
			pstmt.setClob(7, emp.getComment());			
			pstmt.setString(8, emp.getFilename());
			pstmt.setTimestamp(9, emp.getCreatetime());			
												
			Result_n=pstmt.executeUpdate();
			//�ϥ�executeUpdate��k�|�^�Ǥ@�Ӿ�ƭȡA�Ӿ�ƭȥN���sql�R�O(insert_str)�Ҽv�T���`��
			
			System.out.println("(DML,N�������)�s�W���: N="+Result_n);
			
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
			
			System.out.println("(DML,N�������)�R����"+empNo+"��   N="+Result_n);
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

		String update_str = "UPDATE emp SET"
						  + " emp_name=?,"
						  + "emp_addr=?,"
						  + "birthday=?,"
						  + "title=?,"
						  + "money=?,"
						  + "picture=?,"
						  + "comment=?,"
						  + "filename=?,"
						  + "createtime=? WHERE emp_no=?";	
		// try(java 7.0 ���@�۰��������귽){	;}
		try (Connection con = DriverManager.getConnection(url_login_str,"root","000000");
			 PreparedStatement pstmt = con.prepareStatement(update_str);
				){
			
			//Class.forName("com.mysql.jdbc.Driver");	//JDBC4.0����i�ٲ�(ClassNotFoundException)	
			//���Jmysql�M�Ϊ����O��(com.mysql.jdbc.Driver)�A�����O�ɥѸӸ�Ʈw�t�өҴ���
			pstmt.setInt(10, emp.getEmpno());
			pstmt.setString(1, emp.getEmpname());
			pstmt.setString(2, emp.getAddress());
			pstmt.setTimestamp(3, new Timestamp(emp.getBirthday().getTime()));//�ΦW��k
			pstmt.setString(4, emp.getTitle());
			pstmt.setInt(5, emp.getMoney());
			pstmt.setBlob(6, emp.getPicture());
			pstmt.setClob(7, emp.getComment());			
			pstmt.setString(8, emp.getFilename());
			pstmt.setTimestamp(9, emp.getCreatetime());				
												
			Result_n=pstmt.executeUpdate();
			//�ϥ�executeUpdate��k�|�^�Ǥ@�Ӿ�ƭȡA�Ӿ�ƭȥN���sql�R�O(insert_str)�Ҽv�T���`��
			System.out.println("(DML,N�������)��s���: N="+Result_n);
			
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
				String empname = rs.getString("emp_name");
				String address = rs.getString("emp_addr");
				Date birthday = rs.getDate("birthday");
				String title = rs.getString("title");
				int money=rs.getInt("money");
				Blob picture = rs.getBlob("picture");
				Clob comment = rs.getClob("comment");
				String filename = rs.getString("filename");
				Timestamp createtime=rs.getTimestamp("createtime");
				mb = new EmployeeBean(empNo,empname,address,birthday,title,money,picture,comment,filename,createtime);
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
		try (Connection con = DriverManager.getConnection(url_login_str,"root","000000");
			 PreparedStatement pstmt = con.prepareStatement(SelectAll_str);	
				){
			
			//Class.forName("com.mysql.jdbc.Driver");	//JDBC4.0����i�ٲ�(ClassNotFoundException)	
			//���Jmysql�M�Ϊ����O��(com.mysql.jdbc.Driver)�A�����O�ɥѸӸ�Ʈw�t�өҴ���
						
			ResultSet rs=pstmt.executeQuery();
			//�ϥ�executeQuery��k�����f�tResultSet���O
			//ResultSet �Osql�Ҵ��Ѫ����O�M���ΨӦ^��executeQuery()�����G
			
			while(rs.next()){
				int empNo = rs.getInt("emp_no");
				String empname = rs.getString("emp_name");
				String address = rs.getString("emp_addr");
				Date birthday = rs.getDate("birthday");
				String title = rs.getString("title");
				int money=rs.getInt("money");
				Blob picture = rs.getBlob("picture");
				Clob comment = rs.getClob("comment");
				String filename = rs.getString("filename");
				Timestamp createtime=rs.getTimestamp("createtime");
				mb = new EmployeeBean(empNo,empname,address,birthday,title,money,picture,comment,filename,createtime);
				
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

		public void write_ByteArray_To_ByteFile(int Empno,byte[] byteArray,String filename){
				
				File dir = new File("Ex02_data\\re_Ex02_statement_img");
				if (!dir.exists())dir.mkdirs();		
				String Output_name =""+Empno+"_"+mb.getEmpname()+filename.substring(filename.lastIndexOf("."));
			try (FileOutputStream fos = new FileOutputStream(new File(dir,Output_name));
					ByteArrayInputStream bais = new ByteArrayInputStream(byteArray);
				){


				byte[] b = new byte[8192];
				int len = 0;
				while ((len = bais.read(b)) != -1) {
					fos.write(b, 0, len);
				}fos.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("[�줸�}�C��G�i���ɮץ���]"+e.getMessage());
			}

		}

		public void write_CharArray_To_TextFile(int Empno,String EmpName,InputStream CharArray){
			
			String Output_name =""+Empno+"_info_"+EmpName+".txt";			
			File dir = new File("Ex02_data\\re_Ex02_statement_text");
			if (!dir.exists())dir.mkdirs();			

			try {
				File file = new File(dir,Output_name);
				BufferedReader br = new BufferedReader(
										new InputStreamReader(CharArray, "BIG5"));
				PrintWriter pw = new PrintWriter(
									new FileWriter(file), true);
				String line = null;
				
				while ((line = br.readLine()) != null) {
					pw.println(line);
				}pw.close();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("[�r���}�C���r�ɮץ���]"+e.getMessage());
			} 
			
			
			
		}
}
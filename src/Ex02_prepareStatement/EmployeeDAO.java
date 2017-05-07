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
	int Result_n=0;//回傳受sql命令所影響的資料總數(insert、delete、update_str)
	static EmployeeBean mb;//指定資料格式(FindAllEmployee、FindByPrimaryKey)
	
	public void insert(EmployeeBean emp){
		
		String insert_str = "INSERT INTO Emp VALUES(Null,?,?,?,?,?,?,?,?,?)";		
		
	// try(java 7.0 提共自動關閉的資源){	;}
		try (Connection con = DriverManager.getConnection(url_login_str,"root","000000");
			 PreparedStatement pstmt = con.prepareStatement(insert_str);
				){
			
			//Class.forName("com.mysql.jdbc.Driver");	//JDBC4.0之後可省略(ClassNotFoundException)	
			//載入mysql專用的類別檔(com.mysql.jdbc.Driver)，此類別檔由該資料庫廠商所提供
			
			pstmt.setString(1, emp.getEmpname());
			pstmt.setString(2, emp.getAddress());
			pstmt.setTimestamp(3, new Timestamp(emp.getBirthday().getTime()));//匿名方法
			pstmt.setString(4, emp.getTitle());
			pstmt.setInt(5, emp.getMoney());
			pstmt.setBlob(6, emp.getPicture());
			pstmt.setClob(7, emp.getComment());			
			pstmt.setString(8, emp.getFilename());
			pstmt.setTimestamp(9, emp.getCreatetime());			
												
			Result_n=pstmt.executeUpdate();
			//使用executeUpdate方法會回傳一個整數值，該整數值代表受sql命令(insert_str)所影響的總數
			
			System.out.println("(DML,N必為整數)新增資料: N="+Result_n);
			
			System.out.println("資料庫指令執行完畢....");
//		} catch (ClassNotFoundException e) {
//			
//			System.out.println("找不到類別:"+e.getMessage());	
//			
		} catch (SQLException e) {
			
			System.out.println("發生SQL例外:"+e.getMessage());	
			
		}
	}
	
	public void delete(int empNo){

		String delete_str = "DELETE FROM EMP WHERE EMP_NO = ?";	
		
	// try(java 7.0 提共自動關閉的資源){	;}
		try (Connection con = DriverManager.getConnection(url_login_str,"root","000000");
				 PreparedStatement pstmt = con.prepareStatement(delete_str);				
				){
			
			//Class.forName("com.mysql.jdbc.Driver");	//JDBC4.0之後可省略(ClassNotFoundException)	
			//載入mysql專用的類別檔(com.mysql.jdbc.Driver)，此類別檔由該資料庫廠商所提供

			pstmt.setInt(1, empNo);
						
			Result_n=pstmt.executeUpdate();
			//使用executeUpdate方法會回傳一個整數值，該整數值代表受sql命令(insert_str)所影響的總數
			
			System.out.println("(DML,N必為整數)刪除第"+empNo+"筆   N="+Result_n);
			System.out.println("資料庫指令執行完畢....");
//		} catch (ClassNotFoundException e) {
//			
//			System.out.println("找不到類別:"+e.getMessage());	
//			
		} catch (SQLException e) {
			
			System.out.println("發生SQL例外:"+e.getMessage());	
			
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
		// try(java 7.0 提共自動關閉的資源){	;}
		try (Connection con = DriverManager.getConnection(url_login_str,"root","000000");
			 PreparedStatement pstmt = con.prepareStatement(update_str);
				){
			
			//Class.forName("com.mysql.jdbc.Driver");	//JDBC4.0之後可省略(ClassNotFoundException)	
			//載入mysql專用的類別檔(com.mysql.jdbc.Driver)，此類別檔由該資料庫廠商所提供
			pstmt.setInt(10, emp.getEmpno());
			pstmt.setString(1, emp.getEmpname());
			pstmt.setString(2, emp.getAddress());
			pstmt.setTimestamp(3, new Timestamp(emp.getBirthday().getTime()));//匿名方法
			pstmt.setString(4, emp.getTitle());
			pstmt.setInt(5, emp.getMoney());
			pstmt.setBlob(6, emp.getPicture());
			pstmt.setClob(7, emp.getComment());			
			pstmt.setString(8, emp.getFilename());
			pstmt.setTimestamp(9, emp.getCreatetime());				
												
			Result_n=pstmt.executeUpdate();
			//使用executeUpdate方法會回傳一個整數值，該整數值代表受sql命令(insert_str)所影響的總數
			System.out.println("(DML,N必為整數)更新資料: N="+Result_n);
			
			System.out.println("資料庫指令執行完畢....");
//		} catch (ClassNotFoundException e) {
//			
//			System.out.println("找不到類別:"+e.getMessage());	
//			
		} catch (SQLException e) {
			
			System.out.println("發生SQL例外:"+e.getMessage());	
			
		}
	}
	
	
	public EmployeeBean findByPrimaryKey(int key_value){
		String SelectPrimaryKey_str = "SELECT * FROM EMP WHERE EMP_NO = ?";		
	// try(java 7.0 提共自動關閉的資源){	;}
		try (Connection con = DriverManager.getConnection(url_login_str,"root","000000");
			 PreparedStatement pstmt = con.prepareStatement(SelectPrimaryKey_str);
				){
			//Class.forName("com.mysql.jdbc.Driver");	//JDBC4.0之後可省略(ClassNotFoundException)	
			//載入mysql專用的類別檔(com.mysql.jdbc.Driver)，此類別檔由該資料庫廠商所提供
			pstmt.setInt(1, key_value);
			
						
			ResultSet rs = pstmt.executeQuery();
			//使用executeUpdate方法會回傳一個整數值，該整數值代表受sql命令(insert_str)所影響的總數
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
			System.out.println("資料庫指令執行完畢....");
//		} catch (ClassNotFoundException e) {
//			
//			System.out.println("找不到類別:"+e.getMessage());	
//			
		} catch (SQLException e) {
			
			System.out.println("發生SQL例外:"+e.getMessage());	
			
		}
		return mb ;
	}
	
	public List<EmployeeBean>findAllEmployees(){
		List<EmployeeBean> list =new ArrayList<>();
		String SelectAll_str = "SELECT * FROM Emp";
	
	// try(java 7.0 提共自動關閉的資源){	;}
		try (Connection con = DriverManager.getConnection(url_login_str,"root","000000");
			 PreparedStatement pstmt = con.prepareStatement(SelectAll_str);	
				){
			
			//Class.forName("com.mysql.jdbc.Driver");	//JDBC4.0之後可省略(ClassNotFoundException)	
			//載入mysql專用的類別檔(com.mysql.jdbc.Driver)，此類別檔由該資料庫廠商所提供
						
			ResultSet rs=pstmt.executeQuery();
			//使用executeQuery方法必須搭配ResultSet類別
			//ResultSet 是sql所提供的類別專門用來回傳executeQuery()的結果
			
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
				//將每筆資料依照EmployeeBean所規範的格式寫入list				
			}
						
			System.out.println("資料庫指令執行完畢....");
//		} catch (ClassNotFoundException e) {
//			
//			System.out.println("找不到類別:"+e.getMessage());	
			
		} catch (SQLException e) {
			
			System.out.println("發生SQL例外:"+e.getMessage());	
			
		}

		return list;
		//最後記得回傳list給Main
	}

	//--------------------------------------------------------------------
		public static byte[] read_BinaryFile_To_ByteArray(String filename) {
			File file = new File(filename);
			if ( !file.exists()) {
				throw new RuntimeException("無此檔案");	//強制跳出例外事件
			}		
			byte[] read_file = new byte[8192];//每讀一次的大小
			byte[] ByteArray = null;
			int len = 0 ;
			try (
				FileInputStream fis = new FileInputStream(file);//讀入記憶體
				ByteArrayOutputStream baos = new ByteArrayOutputStream();//寫入資料庫				
				){
				
				while ((len=fis.read(read_file))!= -1){
					baos.write(read_file, 0, len);   // 口訣: A.O.L.
				}
				ByteArray = baos.toByteArray();
			} catch (Exception e) {
				System.out.println("[二進位檔案轉位元陣列失敗]"+e.getMessage());
			}
			return ByteArray;
		}

		public static char[] read_TextFile_To_CharArray(String filename,String code) {
			File file = new File(filename);
			if ( !file.exists()) {
				throw new RuntimeException("無此檔案");
			}		
			char[] read_file = new char[8192];//每讀一次的大小
			char[] CharArray = null;
			int len = 0 ;
			try(
			BufferedReader bf = new BufferedReader(
								   new InputStreamReader(
									  new FileInputStream(filename),code));//讀檔
			
			CharArrayWriter caw = new CharArrayWriter();		
					) {
				while ((len=bf.read(read_file))!= -1){
					caw.write(read_file, 0, len);   // 口訣: A.O.L.
				}
				CharArray = caw.toCharArray();
			} catch (Exception e) {
				System.out.println("[文字檔案轉字元陣列失敗]"+e.getMessage());
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
				System.out.println("[位元陣列轉二進位檔案失敗]"+e.getMessage());
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
				System.out.println("[字元陣列轉文字檔案失敗]"+e.getMessage());
			} 
			
			
			
		}
}
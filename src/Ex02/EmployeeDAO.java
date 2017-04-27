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
	int Result_n=0;//回傳受sql命令所影響的資料總數(insert、delete、update_str)
	EmployeeBean mb;//指定資料格式(FindAllEmployee、FindByPrimaryKey)
	
	public void insert(EmployeeBean emp){
		
		String insert_str = "INSERT INTO Emp VALUES(Null,?,?,?,?,?,?,?,?,?)";		
		
	// try(java 7.0 提共自動關閉的資源){	;}
		try (Connection con = DriverManager.getConnection(url_login_str,"root","000000");
			 PreparedStatement pstmt = con.prepareStatement(insert_str);
				){
			
			//Class.forName("com.mysql.jdbc.Driver");	//JDBC4.0之後可省略(ClassNotFoundException)	
			//載入mysql專用的類別檔(com.mysql.jdbc.Driver)，此類別檔由該資料庫廠商所提供
			
			pstmt.setString(1, emp.getName());
			pstmt.setString(2, emp.getAddress());
			pstmt.setTimestamp(3, new Timestamp(emp.getBirthday().getTime()));//匿名方法
			pstmt.setInt(4, emp.getSalary());
			pstmt.setDouble(5, emp.getWeight());
			pstmt.setBlob(6, emp.getPicture());
			pstmt.setClob(7, emp.getComment());
			pstmt.setTimestamp(8, emp.getCreateTime());
			pstmt.setString(9, emp.getFilename());
									
			Result_n=pstmt.executeUpdate();
			//使用executeUpdate方法會回傳一個整數值，該整數值代表受sql命令(insert_str)所影響的總數
			
			System.out.println("<新增資料>	 n:"+Result_n);
			
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
			
			System.out.println("<刪除資料  編號第"+empNo+"筆>	 n:"+Result_n);
			
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
			
			//Class.forName("com.mysql.jdbc.Driver");	//JDBC4.0之後可省略(ClassNotFoundException)	
			//載入mysql專用的類別檔(com.mysql.jdbc.Driver)，此類別檔由該資料庫廠商所提供
		if(emp.getCreateTime()==null){
			pstmt.setString(1, emp.getName());
			pstmt.setString(2, emp.getAddress());
			pstmt.setTimestamp(3, new Timestamp(emp.getBirthday().getTime()));//匿名方法
			pstmt.setInt(4, emp.getSalary());
			pstmt.setDouble(5, emp.getWeight());
			pstmt.setBlob(6, emp.getPicture());
			pstmt.setClob(7, emp.getComment());
			pstmt.setString(8, emp.getFilename());
			pstmt.setInt(9, emp.getEmpNo());				
		}else{	
			pstmt.setString(1, emp.getName());
			pstmt.setString(2, emp.getAddress());
			pstmt.setTimestamp(3, new Timestamp(emp.getBirthday().getTime()));//匿名方法
			pstmt.setInt(4, emp.getSalary());
			pstmt.setDouble(5, emp.getWeight());
			pstmt.setBlob(6, emp.getPicture());
			pstmt.setClob(7, emp.getComment());
			pstmt.setTimestamp(8, emp.getCreateTime());
			pstmt.setString(9, emp.getFilename());
			pstmt.setInt(10, emp.getEmpNo());	
		}
			Result_n=pstmt.executeUpdate();
			//使用executeUpdate方法會回傳一個整數值，該整數值代表受sql命令(insert_str)所影響的總數
			
			System.out.println("<修改資料:第"+emp.getEmpNo()+"筆>	 n:"+Result_n);
			
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
			
			
			//System.out.println(SelectAll_str);//檢視SQL語法是否正確
						
			ResultSet rs=pstmt.executeQuery();
			//使用executeQuery方法必須搭配ResultSet類別
			//ResultSet 是sql所提供的類別專門用來回傳executeQuery()的結果
			
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

	public static byte[] binaryFileToByteArray2(String filename) {
		File f = new File(filename);
		if ( !f.exists()) {
			throw new RuntimeException("無此檔案");	//強制跳出例外事件
		}		
		byte[] read_file = new byte[8192];//每讀一次的大小
		byte[] wirte_database = null;
		int len = 0 ;
		try (
			FileInputStream fis = new FileInputStream(f);//讀入記憶體
			ByteArrayOutputStream baos = new ByteArrayOutputStream();//寫入資料庫				
				){
			while ((len=fis.read(read_file))!= -1){
				baos.write(read_file, 0, len);   // 口訣: A.O.L.
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
			throw new RuntimeException("無此檔案");
		}		
		char[] read_file = new char[8192];//每讀一次的大小
		char[] wirte_database = null;
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
			wirte_database = caw.toCharArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wirte_database;
	}

}

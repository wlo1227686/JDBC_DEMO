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
	int Result_n=0;//回傳受sql命令所影響的資料總數(insert、delete、update_str)
	EmployeeBean mb;//指定資料格式(FindAllEmployee、FindByPrimaryKey)


	public void insert(EmployeeBean emp){
		
		String name = emp.getEmpname();
		String address = emp.getAddress();
		Date birthday = emp.getBirthday();//Date格式 轉成 String
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
		
	// try(java 7.0 提共自動關閉的資源){	;}
		try (Connection con = DriverManager.getConnection(url_login_str,"root","000000");){
			
			//Class.forName("com.mysql.jdbc.Driver");	//JDBC4.0之後可省略(ClassNotFoundException)	
			//載入mysql專用的類別檔(com.mysql.jdbc.Driver)，此類別檔由該資料庫廠商所提供
			
			Statement stmt = con.createStatement();
			
//			System.out.println(insert_str);//檢視SQL語法是否正確
						
			Result_n=stmt.executeUpdate(insert_str);
			//使用executeUpdate方法會回傳一個整數值，該整數值代表受sql命令(insert_str)所影響的總數
			
			System.out.println("(DML,N必為整數)新增資料: N="+Result_n);
			
			System.out.println("資料庫指令執行完畢....");
//		} catch (ClassNotFoundException e) {
//			
//			System.out.println("找不到類別:"+e.getMessage());	
//			
		} catch (SQLException e) {
			
			System.out.println("123發生SQL例外:"+e.getMessage());	
			
		}
	}
	
	public void delete(int empNo){

		String delete_str = "DELETE FROM EMP WHERE EMP_NO = "+empNo;	
		
	// try(java 7.0 提共自動關閉的資源){	;}
		try (Connection con = DriverManager.getConnection(url_login_str,"root","000000");){
			
			//Class.forName("com.mysql.jdbc.Driver");	//JDBC4.0之後可省略(ClassNotFoundException)	
			//載入mysql專用的類別檔(com.mysql.jdbc.Driver)，此類別檔由該資料庫廠商所提供
			
			Statement stmt = con.createStatement();
			
			//System.out.println(delete_str);//檢視SQL語法是否正確
						
			Result_n=stmt.executeUpdate(delete_str);
			//使用executeUpdate方法會回傳一個整數值，該整數值代表受sql命令(insert_str)所影響的總數
			
			System.out.println("(DML,N必為整數)刪除第"+empNo+"筆資料: N="+Result_n);
			
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
		int empNo = emp.getEmpno();
		String name = emp.getEmpname();
		String address = emp.getAddress();
		Date birthday = emp.getBirthday();//Date格式 轉成 String
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
			
			//Class.forName("com.mysql.jdbc.Driver");	//JDBC4.0之後可省略(ClassNotFoundException)	
			//載入mysql專用的類別檔(com.mysql.jdbc.Driver)，此類別檔由該資料庫廠商所提供
			
			Statement stmt = con.createStatement();
			
			//System.out.println(update_str);//檢視SQL語法是否正確
						
			Result_n=stmt.executeUpdate(update_str);
			//使用executeUpdate方法會回傳一個整數值，該整數值代表受sql命令(update_str)所影響的總數
			
		    System.out.println("(DML,N必為整數)修改第"+empNo+"筆資料: N="+Result_n);
			
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
		String SelectPrimaryKey_str = "SELECT * FROM EMP WHERE EMP_NO = "+key_value;		
	// try(java 7.0 提共自動關閉的資源){	;}
		try (Connection con = DriverManager.getConnection(url_login_str,"root","000000");){
			
			//Class.forName("com.mysql.jdbc.Driver");	//JDBC4.0之後可省略(ClassNotFoundException)	
			//載入mysql專用的類別檔(com.mysql.jdbc.Driver)，此類別檔由該資料庫廠商所提供
			
			Statement stmt = con.createStatement();
			
			//System.out.println(SelectPrimaryKey_str);//檢視SQL語法是否正確
						
			ResultSet rs =stmt.executeQuery(SelectPrimaryKey_str);
			//使用executeUpdate方法會回傳一個整數值，該整數值代表受sql命令(insert_str)所影響的總數
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
		try (Connection con = DriverManager.getConnection(url_login_str,"root","000000");){
			
			//Class.forName("com.mysql.jdbc.Driver");	//JDBC4.0之後可省略(ClassNotFoundException)	
			//載入mysql專用的類別檔(com.mysql.jdbc.Driver)，此類別檔由該資料庫廠商所提供
			
			Statement stmt = con.createStatement();
			
			//System.out.println(SelectAll_str);//檢視SQL語法是否正確
						
			ResultSet rs=stmt.executeQuery(SelectAll_str);
			//使用executeQuery方法必須搭配ResultSet類別
			//ResultSet 是sql所提供的類別專門用來回傳executeQuery()的結果
			
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
				//將每筆資料依照EmployeeBean所規範的格式寫入list				
			}
						
			System.out.println("資料庫指令執行完畢....");
//		} catch (ClassNotFoundException e) {
//			
//			System.out.println("找不到類別:"+e.getMessage());	
//			
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
}

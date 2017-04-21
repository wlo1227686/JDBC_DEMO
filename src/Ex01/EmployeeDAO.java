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
	int Result_n=0;//回傳受sql命令所影響的資料總數(insert、delete、update_str)
	EmployeeBean mb;//指定資料格式(FindAllEmployee、FindByPrimaryKey)
	
	public void insert(EmployeeBean emp){
		
		String name = emp.getName();
		String address = emp.getAddress();
		String birthday = emp.getBirthday().toString();//Date格式 轉成 String
		int  salary= emp.getSalary();
		double weight = emp.getWeight();	
		
		String insert_str = "INSERT INTO Emp VALUES(Null,"
							+ "'"+name+"',"
							+ "'"+address+"',"
							+ "'"+birthday+"',"
							+ "'"+salary+"',"
							+ "'"+weight+"')";		
		
	// try(java 7.0 提共自動關閉的資源){	;}
		try (Connection con = DriverManager.getConnection(url_login_str,"root","000000");){
			
			Class.forName("com.mysql.jdbc.Driver");
			//載入mysql專用的類別檔(com.mysql.jdbc.Driver)，此類別檔由該資料庫廠商所提供
			
			Statement stmt = con.createStatement();
			
			//System.out.println(insert_str);//檢視SQL語法是否正確
						
			Result_n=stmt.executeUpdate(insert_str);
			//使用executeUpdate方法會回傳一個整數值，該整數值代表受sql命令(insert_str)所影響的總數
			
			System.out.println("<新增資料>	 n:"+Result_n);
			
			System.out.println("資料庫指令執行完畢....");
		} catch (ClassNotFoundException e) {
			
			System.out.println("找不到類別:"+e.getMessage());	
			
		} catch (SQLException e) {
			
			System.out.println("發生SQL例外:"+e.getMessage());	
			
		}
	}
	
	public void delete(int empNo){

		String delete_str = "DELETE FROM EMP WHERE EMP_NO = "+empNo;	
		
	// try(java 7.0 提共自動關閉的資源){	;}
		try (Connection con = DriverManager.getConnection(url_login_str,"root","000000");){
			
			Class.forName("com.mysql.jdbc.Driver");
			//載入mysql專用的類別檔(com.mysql.jdbc.Driver)，此類別檔由該資料庫廠商所提供
			
			Statement stmt = con.createStatement();
			
			//System.out.println(delete_str);//檢視SQL語法是否正確
						
			Result_n=stmt.executeUpdate(delete_str);
			//使用executeUpdate方法會回傳一個整數值，該整數值代表受sql命令(insert_str)所影響的總數
			
			System.out.println("<刪除資料>	 n:"+Result_n);
			
			System.out.println("資料庫指令執行完畢....");
		} catch (ClassNotFoundException e) {
			
			System.out.println("找不到類別:"+e.getMessage());	
			
		} catch (SQLException e) {
			
			System.out.println("發生SQL例外:"+e.getMessage());	
			
		}
	}
	
	public void update(EmployeeBean emp){
		
		String name = emp.getName();
		String address = emp.getAddress();
		String birthday = emp.getBirthday().toString();//Date格式 轉成 String
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
			//載入mysql專用的類別檔(com.mysql.jdbc.Driver)，此類別檔由該資料庫廠商所提供
			
			Statement stmt = con.createStatement();
			
			//System.out.println(insert_str);//檢視SQL語法是否正確
						
			Result_n=stmt.executeUpdate(update_str);
			//使用executeUpdate方法會回傳一個整數值，該整數值代表受sql命令(update_str)所影響的總數
			
		    System.out.println("<修改資料>	ok ,n:"+Result_n);
			
			System.out.println("資料庫指令執行完畢....");
		} catch (ClassNotFoundException e) {
			
			System.out.println("找不到類別:"+e.getMessage());	
			
		} catch (SQLException e) {
			
			System.out.println("發生SQL例外:"+e.getMessage());	
			
		}		
	}
	
	
	public EmployeeBean findByPrimaryKey(int key_value){
		String SelectPrimaryKey_str = "SELECT * FROM EMP WHERE EMP_NO = "+key_value;		
	// try(java 7.0 提共自動關閉的資源){	;}
		try (Connection con = DriverManager.getConnection(url_login_str,"root","000000");){
			
			Class.forName("com.mysql.jdbc.Driver");
			//載入mysql專用的類別檔(com.mysql.jdbc.Driver)，此類別檔由該資料庫廠商所提供
			
			Statement stmt = con.createStatement();
			
			//System.out.println(SelectPrimaryKey_str);//檢視SQL語法是否正確
						
			ResultSet rs =stmt.executeQuery(SelectPrimaryKey_str);
			//使用executeUpdate方法會回傳一個整數值，該整數值代表受sql命令(insert_str)所影響的總數
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
			System.out.println("資料庫指令執行完畢....");
		} catch (ClassNotFoundException e) {
			
			System.out.println("找不到類別:"+e.getMessage());	
			
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
			
			Class.forName("com.mysql.jdbc.Driver");
			//載入mysql專用的類別檔(com.mysql.jdbc.Driver)，此類別檔由該資料庫廠商所提供
			
			Statement stmt = con.createStatement();
			
			//System.out.println(SelectAll_str);//檢視SQL語法是否正確
						
			ResultSet rs=stmt.executeQuery(SelectAll_str);
			//使用executeQuery方法必須搭配ResultSet類別
			//ResultSet 是sql所提供的類別專門用來回傳executeQuery()的結果
			
			while(rs.next()){
				int empNo = rs.getInt("emp_no");
				String name = rs.getString("emp_name");
				String address = rs.getString("emp_addr");
				Date Birthday = rs.getDate("Birthday");
				int salary=rs.getInt("salary");
				double weight=rs.getDouble("weight");
				
				mb = new EmployeeBean(empNo,name,address,Birthday,salary,weight);
				
				list.add(mb);
				//將每筆資料依照EmployeeBean所規範的格式寫入list				
			}
						
			System.out.println("資料庫指令執行完畢....");
		} catch (ClassNotFoundException e) {
			
			System.out.println("找不到類別:"+e.getMessage());	
			
		} catch (SQLException e) {
			
			System.out.println("發生SQL例外:"+e.getMessage());	
			
		}

		return list;
		//最後記得回傳list給Main
	}



}

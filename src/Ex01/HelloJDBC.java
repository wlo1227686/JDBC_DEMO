package Ex01;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class HelloJDBC {
	public static void main(String[] args) {
		String url_login_str = "jdbc:mysql://localhost/demo_jdbc_db"
					 		 + "?useSSL=true"
							 + "&useUnicode=yes"
							 + "&characterEncoding=UTF-8";	
		
		try {
			System.out.println("資料庫連線中.....");
			Class.forName("com.mysql.jdbc.Driver");		
//<連線方法一>-----------------------------------------------------			
// getConnection(String url,userName,password)
						
			Connection con = DriverManager.getConnection(url_login_str,"root","000000");
			
//<連線方法二>-----------------------------------------------------			
//利用Properties類別使用key-value方式來連線
			
//			Properties pr = new Properties();
//			pr.put("user", "root");
//			pr.put("password", "000000");
//			pr.put("useSSL", "true");
//			pr.put("useUnicode", "yes");
//			pr.put("characterEncoding", "UTF-8");
//			
//			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_db",pr);	

			
			
			System.out.println("資料庫連線成功");	
			
//<建立Statement物件來傳送SQL指令>-----------------------------------------------------			
//所有的SQL指令必須以Statement物件規定的格式來與資料庫做溝通		
			int n=0;
			String str = "CREATE TABLE Emp(emp_no int auto_increment primary key,"
					+ "emp_name varchar(30),"
					+ "emp_addr varchar(50),"
					+ "Birthday datetime,	"
					+ "salary int,			"
					+ "weight Decimal"
					+ ")";			
			Statement stmt = con.createStatement();
			String str0 = "DROP TABLE IF EXISTS Emp";
			 n = stmt.executeUpdate(str0);//刪除Emp table (如果有重複)
		     n = stmt.executeUpdate(str);
			System.out.println("表格新建:"+n);
			
			
			System.out.println("資料庫指令執行完畢....");
		} catch (ClassNotFoundException e) {
			
			System.out.println("找不到類別:"+e.getMessage());	
			
		} catch (SQLException e) {
			
			System.out.println("發生SQL例外:"+e.getMessage());	
			
		}
		
}
}

package Ex01_statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class HelloJDBC {
	public static void main(String[] args) {
		String url_login_str = "jdbc:mysql://localhost/statement_jdbc_db"
					 		 + "?useSSL=true"
							 + "&useUnicode=yes"
							 + "&characterEncoding=UTF-8";	
		
		try {
			System.out.println("��Ʈw�s�u��.....");
			Class.forName("com.mysql.jdbc.Driver");		
//<�s�u��k�@>-----------------------------------------------------			
// getConnection(String url,userName,password)
						
			Connection con = DriverManager.getConnection(url_login_str,"root","000000");
			
//<�s�u��k�G>-----------------------------------------------------			
//�Q��Properties���O�ϥ�key-value�覡�ӳs�u
			
//			Properties pr = new Properties();
//			pr.put("user", "root");
//			pr.put("password", "000000");
//			pr.put("useSSL", "true");
//			pr.put("useUnicode", "yes");
//			pr.put("characterEncoding", "UTF-8");
//			
//			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/statement_jdbc_db",pr);	
			
			System.out.println("��Ʈw�s�u���\");	
			
//<�إ�Statement����ӶǰeSQL���O>-----------------------------------------------------			
//�Ҧ���SQL���O�����HStatement����W�w���榡�ӻP��Ʈw�����q		
			int n=0;
			String str = "CREATE TABLE emp(emp_no int auto_increment primary key,"
					+ "emp_name varchar(30) not null,"
					+ "emp_addr varchar(50) not null,"
					+ "birthday datetime not null,	 "
					+ "title varchar(30) not null,	 "
					+ "money int not null,			 "
//					+ "picture longblob not null,	 "
//					+ "comment longtext not null,	 "
					+ "filename varchar(30) not null,"
					+ "createtime timestamp not null "
					+ ")CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' ";			
			Statement stmt = con.createStatement();
			String str0 = "DROP TABLE IF EXISTS Emp";
			
			 n = stmt.executeUpdate(str0);//�R��Emp table (�p�G������)
		     n = stmt.executeUpdate(str);
		     
			System.out.println("(DDL,N����0)���s��: N="+n);
			
			
			System.out.println("��Ʈw���O���槹��....");
		} catch (ClassNotFoundException e) {
			
			System.out.println("�䤣�����O:"+e.getMessage());	
			
		} catch (SQLException e) {
			
			System.out.println("�o��SQL�ҥ~:"+e.getMessage());	
			
		}
		
}
}

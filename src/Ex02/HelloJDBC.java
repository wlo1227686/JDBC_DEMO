package Ex02;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class HelloJDBC {
	public static void main(String[] args) {
		String url_login_str = "jdbc:mysql://localhost/jdbc_db"
					 		 + "?useSSL=true"
							 + "&useUnicode=yes"
							 + "&characterEncoding=UTF-8";	
		
		try {
			System.out.println("��Ʈw�s�u��.....");
//			Class.forName("com.mysql.jdbc.Driver");	//JDBC4.0����i�ٲ�(ClassNotFoundException)	
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
//			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_db",pr);	

			
			
			System.out.println("��Ʈw�s�u���\");	
			
//<�إ�Statement����ӶǰeSQL���O>-----------------------------------------------------			
//�Ҧ���SQL���O�����HStatement����W�w���榡�ӻP��Ʈw�����q		
			int n=0;
			String str = "CREATE TABLE Emp(emp_no int auto_increment primary key,"
					+ "emp_name varchar(30),"
					+ "emp_addr varchar(50),"
					+ "Birthday datetime,	"
					+ "salary int,			"
					+ "weight Decimal,"
					+ "picture longblob,"
					+ "comment longtext,"
					+ "create_time timestamp,"
					+ "filename varchar(30)"
					+ ")CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' ";			
			Statement stmt = con.createStatement();
			String str0 = "DROP TABLE IF EXISTS Emp";
			 n = stmt.executeUpdate(str0);//�R��Emp table (�p�G������)
		     n = stmt.executeUpdate(str);
			System.out.println("���s��:"+n);
			
			
			System.out.println("��Ʈw���O���槹��....");
//		} catch (ClassNotFoundException e) {
//			
//			System.out.println("�䤣�����O:"+e.getMessage());	
//			
		} catch (SQLException e) {
			
			System.out.println("�o��SQL�ҥ~:"+e.getMessage());	
		}
		File dir = new File("Ex02_Out_comment\\");
		if (dir.exists()){
			deleteDir(dir);
			System.out.println("���m:"+dir.getPath());
		}
		File img_dir = new File("Ex02_Out_images\\");
		if (img_dir.exists()){	
			deleteDir(img_dir);
			System.out.println("���m:"+img_dir.getPath());
		}
		
}
	public static boolean deleteDir(File dir) {
		   if (dir.isDirectory()) {
		       File[] files = dir.listFiles();
		       for (int i=0;i<files.length;i++) { 
		            if (!deleteDir(files[i]))
		               return false;
		       }
		   }
		   //�ؿ��{�b�M�ŤF�i�H�R��
		   return dir.delete();
		}
}

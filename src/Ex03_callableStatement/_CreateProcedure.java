package Ex03_callableStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class _CreateProcedure {
	static Connection conn = null;
	static Statement stmt = null;

	public static void main(String[] args) {
		String url_login_str = "jdbc:mysql://localhost/callablestatement_jdbc_db"
				 + "?useSSL=true" + "&useUnicode=yes"
				 + "&characterEncoding=UTF-8";
		
		
		String Drop_myEmpIn = "Drop PROCEDURE myEmpIn";
		String Drop_myEmpUp = "Drop PROCEDURE myEmpUp";
		String Drop_myEmpDe = "Drop PROCEDURE myEmpDe";
		String Drop_myEmpFindAll="Drop PROCEDURE myEmpFindAll";
		String Drop_myEmpFindPK="Drop PROCEDURE myEmpFindPK";	
		String Create_myEmp_Find_PK="CREATE PROCEDURE myEmpFindPK("
													 + "inout empno int,"
													 + "out emp_name varchar(30),"
													 + "out emp_addr varchar(30),"
													 + "out birthday timestamp,"
													 + "out title varchar(50),"
													 + "out money int,"
													 + "out picture longblob,"
													 + "out comment longtext,"
													 + "out filename varchar(30),"
													 + "out createtime timestamp)"
													 + "begin "
													 + "SELECT * FROM EMP WHERE emp_no = empno;"
													 + "end";
		String Create_myEmp_Find_All="CREATE PROCEDURE myEmpFindAll("
													 + "out empno int,"
													 + "out emp_name varchar(30),"
													 + "out emp_addr varchar(30),"
													 + "out birthday timestamp,"
													 + "out title varchar(50),"
													 + "out money int,"
													 + "out picture longblob,"
													 + "out comment longtext,"
													 + "out filename varchar(30),"
													 + "out createtime timestamp)"
													 + "begin "
													 + "SELECT * FROM EMP ;"
													 + "end";
		
		String Create_myEmp_De ="CREATE PROCEDURE myEmpDe(In empno int)"
													 + "begin "
													 + "DELETE FROM EMP WHERE EMP_NO = empno ;"
													 + "end";
		String Create_myEmp_in = "CREATE PROCEDURE myEmpIn(In empname varchar(30),"
													 + "In address varchar(30),"
													 + "In birthday timestamp,"
													 + "In title varchar(50),"
													 + "In money int,"
													 + "In picture longblob,"
													 + "In commit longtext,"
													 + "In filename varchar(30),"
													 + "In createtime timestamp)"
													 + "BEGIN "
													 + "insert into emp "
													 + "values(null,empname,address,birthday,title,money,picture,commit,filename,createtime);"
													 + "END";
		String Create_myEmp_Up = "CREATE PROCEDURE myEmpUp("
													 + "In empno int,"
													 + "In empname varchar(30),"
													 + "In address varchar(30),"
													 + "In birthday timestamp,"
													 + "In title varchar(50),"
													 + "In money int,"
													 + "In picture longblob,"
													 + "In comment longtext,"
													 + "In filename varchar(30),"
													 + "In createtime timestamp)"
													 + "BEGIN "
													 + "UPDATE emp SET "
													 + "emp_name=empname,"
													 + "emp_addr=address,"
													 + "birthday=birthday,"
													 + "title=title,"
													 + "money=money,"
													 + "picture=picture,"
													 + "comment=comment,"
													 + "filename=filename,"
													 + "createtime=createtime "
													 + "WHERE emp_no = empno;"
		//											 + "commit"
													 + "end";
		try {
			Connection con = DriverManager.getConnection(url_login_str, "root", "000000");
			stmt = con.createStatement();
			// §R°£ÂÂ¸ê®Æ-------------------------------------
			
			try {
				System.out.print("Drop_myEmpIn ");
				stmt.executeUpdate(Drop_myEmpIn);
				System.out.println("ok	 ");
			} catch (Exception e) {
				System.out.println("Error " + e.getMessage());
			}
			try {
				System.out.print("Drop_myEmpDe ");
				stmt.executeUpdate(Drop_myEmpDe);
				System.out.println("ok	 ");
			} catch (Exception e) {
				System.out.println("Error " + e.getMessage());
			}
			try {
				System.out.print("Drop_myEmpUp ");
				stmt.executeUpdate(Drop_myEmpUp);
				System.out.println("ok	 ");
			} catch (Exception e) {
				System.out.println("Error " + e.getMessage());
			}	
			try {
				System.out.print("Drop_myEmp_Find_All ");
				stmt.executeUpdate(Drop_myEmpFindAll);
				System.out.println("ok	 ");
			} catch (Exception e) {
				System.out.println("Error " + e.getMessage());
			}
			try {
				System.out.print("Drop_myEmp_Find_PK ");
				stmt.executeUpdate(Drop_myEmpFindPK);
				System.out.println("ok	 ");
			} catch (Exception e) {
				System.out.println("Error " + e.getMessage());
			}
//			// -------------------------------------
			try {
				stmt.executeUpdate(Create_myEmp_in);
				stmt.executeUpdate(Create_myEmp_Up);
				stmt.executeUpdate(Create_myEmp_De);
				stmt.executeUpdate(Create_myEmp_Find_All);
				stmt.executeUpdate(Create_myEmp_Find_PK);
			//	stmt.executeUpdate(Create_myEmp_out);
				System.out.println("happy ending .......");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

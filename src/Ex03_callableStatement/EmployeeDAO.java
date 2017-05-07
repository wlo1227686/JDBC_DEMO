package Ex03_callableStatement;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
	String url_login_str = "jdbc:mysql://localhost/callablestatement_jdbc_db" + "?useSSL=true" + "&useUnicode=yes"
			+ "&characterEncoding=UTF-8";
	ResultSet rs;
	int Result_n = 0;// 回傳受sql命令所影響的資料總數(insert、delete、update_str)
	static EmployeeBean mb;// 指定資料格式(FindAllEmployee、FindByPrimaryKey)

	public void insert(EmployeeBean emp) {
		try (Connection con = DriverManager.getConnection(url_login_str, "root", "000000");
				CallableStatement csIn = con.prepareCall("call myEmpIn(?,?,?,?,?,?,?,?,?)");) {


			csIn.setString(1, emp.getEmpname());
			csIn.setString(2, emp.getAddress());
			csIn.setTimestamp(3, new Timestamp(emp.getBirthday().getTime()));// 匿名方法
			csIn.setString(4, emp.getTitle());
			csIn.setInt(5, emp.getMoney());
			csIn.setBlob(6, emp.getPicture());
			csIn.setClob(7, emp.getComment());
			csIn.setString(8, emp.getFilename());
			csIn.setTimestamp(9, emp.getCreatetime());
			csIn.executeQuery();

			System.out.println("資料庫指令執行完畢....");
		} catch (SQLException e) {

			System.out.println("123發生SQL例外:" + e.getMessage());

		}
	}

	public void delete(int empNo) {
		// try(java 7.0 提共自動關閉的資源){ ;}
		try (Connection con = DriverManager.getConnection(url_login_str, "root", "000000");
				CallableStatement csIn = con.prepareCall("call myEmpDe(?)");) {

			// Class.forName("com.mysql.jdbc.Driver");
			// //JDBC4.0之後可省略(ClassNotFoundException)
			// 載入mysql專用的類別檔(com.mysql.jdbc.Driver)，此類別檔由該資料庫廠商所提供

			csIn.setInt(1, empNo);
			csIn.execute();
			System.out.println("(DML,N必為整數)刪除第" + empNo + "筆 ");
			System.out.println("資料庫指令執行完畢....");
			// } catch (ClassNotFoundException e) {
			//
			// System.out.println("找不到類別:"+e.getMessage());
			//
		} catch (SQLException e) {

			System.out.println("發生SQL例外:" + e.getMessage());

		}
	}

	public void update(EmployeeBean emp) {
		// try(java 7.0 提共自動關閉的資源){ ;}
		try (Connection con = DriverManager.getConnection(url_login_str, "root", "000000");
			 CallableStatement csIn = con.prepareCall("call myEmpUp(?,?,?,?,?,?,?,?,?,?)");) {

			csIn.setInt(1, emp.getEmpno());
			csIn.setString(2, emp.getEmpname());
			csIn.setString(3, emp.getAddress());
			csIn.setTimestamp(4, new Timestamp(emp.getBirthday().getTime()));// 匿名方法
			csIn.setString(5, emp.getTitle());
			csIn.setInt(6, emp.getMoney());
			csIn.setBlob(7, emp.getPicture());
			csIn.setClob(8, emp.getComment());
			csIn.setString(9, emp.getFilename());
			csIn.setTimestamp(10, emp.getCreatetime());
			csIn.execute();

			System.out.println("資料庫指令執行完畢....");

		} catch (SQLException e) {

			System.out.println("123發生SQL例外:" + e.getMessage());

		}
	}

	public EmployeeBean findByPrimaryKey(int key_value) {
				try (Connection con = DriverManager.getConnection(url_login_str, "root", "000000");
					 CallableStatement csIn = con.prepareCall(" call myEmpFindPK(?,?,?,?,?,?,?,?,?,?) ");) {
			// Class.forName("com.mysql.jdbc.Driver");
			// //JDBC4.0之後可省略(ClassNotFoundException)
			// 載入mysql專用的類別檔(com.mysql.jdbc.Driver)，此類別檔由該資料庫廠商所提供
				csIn.setInt(1, key_value);
				
				csIn.registerOutParameter(1,Types.INTEGER);
				csIn.registerOutParameter(2,Types.VARCHAR);
				csIn.registerOutParameter(3,Types.VARCHAR);
				csIn.registerOutParameter(4,Types.TIMESTAMP);
				csIn.registerOutParameter(5,Types.VARCHAR);
				csIn.registerOutParameter(6,Types.INTEGER);
				csIn.registerOutParameter(7,Types.BLOB);
				csIn.registerOutParameter(8,Types.CLOB);
				csIn.registerOutParameter(9,Types.VARCHAR);
				csIn.registerOutParameter(10,Types.TIMESTAMP);
				rs = csIn.executeQuery();
		 while(rs.next()){
				int empNo = rs.getInt(1);
				String empname = rs.getString(2);
				String address = rs.getString(3);
				Date birthday = rs.getDate(4);
				String title = rs.getString(5);
				int money = rs.getInt(6);
				Blob picture = rs.getBlob(7);
				Clob comment = rs.getClob(8);
				String filename = rs.getString(9);
				Timestamp createtime = rs.getTimestamp(10);
				mb = new EmployeeBean(empNo, empname, address, birthday, title, money, picture, comment, filename,
						createtime);
				System.out.println("[SQL]Query OK empNo=" + empNo);
			}
			System.out.println("資料庫指令執行完畢....");
			// } catch (ClassNotFoundException e) {
			//
			// System.out.println("找不到類別:"+e.getMessage());
			//
		} catch (SQLException e) {

			System.out.println("發生SQL例外:" + e.getMessage());

		}
		return mb;
	}

	public List<EmployeeBean> findAllEmployees() {
		List<EmployeeBean> list = new ArrayList<>();
		
		try (Connection con = DriverManager.getConnection(url_login_str, "root", "000000");
			 CallableStatement csIn = con.prepareCall("call myEmpFindAll(?,?,?,?,?,?,?,?,?,?)");) {
				rs=csIn.executeQuery();
			while (rs.next()) {
				int empNo = rs.getInt(1);
				String empname = rs.getString(2);
				String address = rs.getString(3);
				Date birthday = rs.getDate(4);
				String title = rs.getString(5);
				int money = rs.getInt(6);
				Blob picture = rs.getBlob(7);
				Clob comment = rs.getClob(8);
				String filename = rs.getString(9);
				Timestamp createtime = rs.getTimestamp(10);
				mb = new EmployeeBean(empNo, empname, address, birthday, title, money, picture, comment, filename,
						createtime);

				list.add(mb);
				// 將每筆資料依照EmployeeBean所規範的格式寫入list
			}

			System.out.println("資料庫指令執行完畢....");
			// } catch (ClassNotFoundException e) {
			//
			// System.out.println("找不到類別:"+e.getMessage());

		} catch (SQLException e) {

			System.out.println("發生SQL例外:" + e.getMessage());

		}

		return list;
		// 最後記得回傳list給Main
	}

	// --------------------------------------------------------------------
	public static byte[] read_BinaryFile_To_ByteArray(String filename) {
		File file = new File(filename);
		if (!file.exists()) {
			throw new RuntimeException("無此檔案"); // 強制跳出例外事件
		}
		byte[] read_file = new byte[8192];// 每讀一次的大小
		byte[] ByteArray = null;
		int len = 0;
		try (FileInputStream fis = new FileInputStream(file); // 讀入記憶體
				ByteArrayOutputStream baos = new ByteArrayOutputStream();// 寫入資料庫
		) {

			while ((len = fis.read(read_file)) != -1) {
				baos.write(read_file, 0, len); // 口訣: A.O.L.
			}
			ByteArray = baos.toByteArray();
		} catch (Exception e) {
			System.out.println("[二進位檔案轉位元陣列失敗]" + e.getMessage());
		}
		return ByteArray;
	}

	public static char[] read_TextFile_To_CharArray(String filename, String code) {
		File file = new File(filename);
		if (!file.exists()) {
			throw new RuntimeException("無此檔案");
		}
		char[] read_file = new char[8192];// 每讀一次的大小
		char[] CharArray = null;
		int len = 0;
		try (BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(filename), code)); // 讀檔

				CharArrayWriter caw = new CharArrayWriter();) {
			while ((len = bf.read(read_file)) != -1) {
				caw.write(read_file, 0, len); // 口訣: A.O.L.
			}
			CharArray = caw.toCharArray();
		} catch (Exception e) {
			System.out.println("[文字檔案轉字元陣列失敗]" + e.getMessage());
		}
		return CharArray;
	}

	public void write_ByteArray_To_ByteFile(int Empno, byte[] byteArray, String filename) {

		File dir = new File("Ex03_data\\re_Ex03_statement_img");
		if (!dir.exists())
			dir.mkdirs();
		String Output_name = "" + Empno + "_" + mb.getEmpname() + filename.substring(filename.lastIndexOf("."));
		try (FileOutputStream fos = new FileOutputStream(new File(dir, Output_name));
				ByteArrayInputStream bais = new ByteArrayInputStream(byteArray);) {

			byte[] b = new byte[8192];
			int len = 0;
			while ((len = bais.read(b)) != -1) {
				fos.write(b, 0, len);
			}
			fos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("[位元陣列轉二進位檔案失敗]" + e.getMessage());
		}

	}

	public void write_CharArray_To_TextFile(int Empno, String EmpName, InputStream CharArray) {

		String Output_name = "" + Empno + "_info_" + EmpName + ".txt";
		File dir = new File("Ex03_data\\re_Ex03_statement_text");
		if (!dir.exists())
			dir.mkdirs();

		try {
			File file = new File(dir, Output_name);
			BufferedReader br = new BufferedReader(new InputStreamReader(CharArray, "BIG5"));
			PrintWriter pw = new PrintWriter(new FileWriter(file), true);
			String line = null;

			while ((line = br.readLine()) != null) {
				pw.println(line);
			}
			pw.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("[字元陣列轉文字檔案失敗]" + e.getMessage());
		}

	}
}
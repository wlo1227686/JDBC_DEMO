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
	int Result_n = 0;// �^�Ǩ�sql�R�O�Ҽv�T������`��(insert�Bdelete�Bupdate_str)
	static EmployeeBean mb;// ���w��Ʈ榡(FindAllEmployee�BFindByPrimaryKey)

	public void insert(EmployeeBean emp) {
		try (Connection con = DriverManager.getConnection(url_login_str, "root", "000000");
				CallableStatement csIn = con.prepareCall("call myEmpIn(?,?,?,?,?,?,?,?,?)");) {


			csIn.setString(1, emp.getEmpname());
			csIn.setString(2, emp.getAddress());
			csIn.setTimestamp(3, new Timestamp(emp.getBirthday().getTime()));// �ΦW��k
			csIn.setString(4, emp.getTitle());
			csIn.setInt(5, emp.getMoney());
			csIn.setBlob(6, emp.getPicture());
			csIn.setClob(7, emp.getComment());
			csIn.setString(8, emp.getFilename());
			csIn.setTimestamp(9, emp.getCreatetime());
			csIn.executeQuery();

			System.out.println("��Ʈw���O���槹��....");
		} catch (SQLException e) {

			System.out.println("123�o��SQL�ҥ~:" + e.getMessage());

		}
	}

	public void delete(int empNo) {
		// try(java 7.0 ���@�۰��������귽){ ;}
		try (Connection con = DriverManager.getConnection(url_login_str, "root", "000000");
				CallableStatement csIn = con.prepareCall("call myEmpDe(?)");) {

			// Class.forName("com.mysql.jdbc.Driver");
			// //JDBC4.0����i�ٲ�(ClassNotFoundException)
			// ���Jmysql�M�Ϊ����O��(com.mysql.jdbc.Driver)�A�����O�ɥѸӸ�Ʈw�t�өҴ���

			csIn.setInt(1, empNo);
			csIn.execute();
			System.out.println("(DML,N�������)�R����" + empNo + "�� ");
			System.out.println("��Ʈw���O���槹��....");
			// } catch (ClassNotFoundException e) {
			//
			// System.out.println("�䤣�����O:"+e.getMessage());
			//
		} catch (SQLException e) {

			System.out.println("�o��SQL�ҥ~:" + e.getMessage());

		}
	}

	public void update(EmployeeBean emp) {
		// try(java 7.0 ���@�۰��������귽){ ;}
		try (Connection con = DriverManager.getConnection(url_login_str, "root", "000000");
			 CallableStatement csIn = con.prepareCall("call myEmpUp(?,?,?,?,?,?,?,?,?,?)");) {

			csIn.setInt(1, emp.getEmpno());
			csIn.setString(2, emp.getEmpname());
			csIn.setString(3, emp.getAddress());
			csIn.setTimestamp(4, new Timestamp(emp.getBirthday().getTime()));// �ΦW��k
			csIn.setString(5, emp.getTitle());
			csIn.setInt(6, emp.getMoney());
			csIn.setBlob(7, emp.getPicture());
			csIn.setClob(8, emp.getComment());
			csIn.setString(9, emp.getFilename());
			csIn.setTimestamp(10, emp.getCreatetime());
			csIn.execute();

			System.out.println("��Ʈw���O���槹��....");

		} catch (SQLException e) {

			System.out.println("123�o��SQL�ҥ~:" + e.getMessage());

		}
	}

	public EmployeeBean findByPrimaryKey(int key_value) {
				try (Connection con = DriverManager.getConnection(url_login_str, "root", "000000");
					 CallableStatement csIn = con.prepareCall(" call myEmpFindPK(?,?,?,?,?,?,?,?,?,?) ");) {
			// Class.forName("com.mysql.jdbc.Driver");
			// //JDBC4.0����i�ٲ�(ClassNotFoundException)
			// ���Jmysql�M�Ϊ����O��(com.mysql.jdbc.Driver)�A�����O�ɥѸӸ�Ʈw�t�өҴ���
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
			System.out.println("��Ʈw���O���槹��....");
			// } catch (ClassNotFoundException e) {
			//
			// System.out.println("�䤣�����O:"+e.getMessage());
			//
		} catch (SQLException e) {

			System.out.println("�o��SQL�ҥ~:" + e.getMessage());

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
				// �N�C����ƨ̷�EmployeeBean�ҳW�d���榡�g�Jlist
			}

			System.out.println("��Ʈw���O���槹��....");
			// } catch (ClassNotFoundException e) {
			//
			// System.out.println("�䤣�����O:"+e.getMessage());

		} catch (SQLException e) {

			System.out.println("�o��SQL�ҥ~:" + e.getMessage());

		}

		return list;
		// �̫�O�o�^��list��Main
	}

	// --------------------------------------------------------------------
	public static byte[] read_BinaryFile_To_ByteArray(String filename) {
		File file = new File(filename);
		if (!file.exists()) {
			throw new RuntimeException("�L���ɮ�"); // �j����X�ҥ~�ƥ�
		}
		byte[] read_file = new byte[8192];// �CŪ�@�����j�p
		byte[] ByteArray = null;
		int len = 0;
		try (FileInputStream fis = new FileInputStream(file); // Ū�J�O����
				ByteArrayOutputStream baos = new ByteArrayOutputStream();// �g�J��Ʈw
		) {

			while ((len = fis.read(read_file)) != -1) {
				baos.write(read_file, 0, len); // �f�Z: A.O.L.
			}
			ByteArray = baos.toByteArray();
		} catch (Exception e) {
			System.out.println("[�G�i���ɮ���줸�}�C����]" + e.getMessage());
		}
		return ByteArray;
	}

	public static char[] read_TextFile_To_CharArray(String filename, String code) {
		File file = new File(filename);
		if (!file.exists()) {
			throw new RuntimeException("�L���ɮ�");
		}
		char[] read_file = new char[8192];// �CŪ�@�����j�p
		char[] CharArray = null;
		int len = 0;
		try (BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(filename), code)); // Ū��

				CharArrayWriter caw = new CharArrayWriter();) {
			while ((len = bf.read(read_file)) != -1) {
				caw.write(read_file, 0, len); // �f�Z: A.O.L.
			}
			CharArray = caw.toCharArray();
		} catch (Exception e) {
			System.out.println("[��r�ɮ���r���}�C����]" + e.getMessage());
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
			System.out.println("[�줸�}�C��G�i���ɮץ���]" + e.getMessage());
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
			System.out.println("[�r���}�C���r�ɮץ���]" + e.getMessage());
		}

	}
}
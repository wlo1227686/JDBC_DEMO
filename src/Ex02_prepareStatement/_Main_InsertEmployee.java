
package Ex02_prepareStatement;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialClob;
import javax.sql.rowset.serial.SerialException;

public class _Main_InsertEmployee {

	public static void main(String[] args) {
		Timestamp createTime = new Timestamp(System.currentTimeMillis());
		
		try (// java 7.0 ���@�۰��������귽
				BufferedReader bf = new BufferedReader(new InputStreamReader(
						new FileInputStream("Ex02_data\\Ex02_statement_Initialzation.txt"), "BIG5"));
			) {
			String Read_line = "";
			EmployeeDAO ie = new EmployeeDAO();
			while ((Read_line = bf.readLine()) != null) {
				String[] sa = Read_line.split("\\|");
				
				try {
					Blob picture = new SerialBlob(EmployeeDAO.read_BinaryFile_To_ByteArray("Ex02_data\\Ex02_statement_img\\" + sa[5].trim()));
					Clob comment = new SerialClob(EmployeeDAO.read_TextFile_To_CharArray("Ex02_data\\Ex02_statement_text\\" + sa[6].trim(),"BIG5"));		
					EmployeeBean emp = new EmployeeBean(sa[0].trim() ,
								 						sa[1].trim() ,
								 		   Date.valueOf(sa[2].trim()),
								 		   				sa[3].trim() ,
								 	   Integer.parseInt(sa[4].trim()),
								 							 picture ,
								 							 comment ,
								 						sa[5].trim() ,
								 						 createTime);
					ie.insert(emp); // �s�W�iDB

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// EmployeeBean emp = new
			// EmployeeBean("�X��PD�P�|��","�F���Ū���L��",Date.valueOf("1970-05-05"),"��U�|��",500000000,"01_monkey_D_luffy.png",createTime);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
package Ex03_callableStatement;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.Timestamp;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialClob;

public class _Main_UpdataEmployee {

	public static void main(String[] args) {
		Timestamp createTime = new Timestamp(System.currentTimeMillis());
		try (// java 7.0 提共自動關閉的資源
				BufferedReader bf = new BufferedReader(new InputStreamReader(
						new FileInputStream("Ex03_data\\Ex03_statement_UpdateData.txt"), "BIG5"));) {
			String Read_line = "";
			EmployeeDAO ie = new EmployeeDAO();
			while ((Read_line = bf.readLine()) != null) {
				String[] sa = Read_line.split("\\|");
				try {
					Blob picture = new SerialBlob(EmployeeDAO.read_BinaryFile_To_ByteArray("Ex03_data\\Ex03_statement_img\\" + sa[6].trim()));
					Clob comment = new SerialClob(EmployeeDAO.read_TextFile_To_CharArray("Ex03_data\\Ex03_statement_text\\" + sa[7].trim(), "BIG5"));
					EmployeeBean emp = new EmployeeBean(
									   Integer.parseInt(sa[0].trim()),
														sa[1].trim(),
														sa[2].trim(),
										   Date.valueOf(sa[3].trim()),
										  				sa[4].trim(),
									   Integer.parseInt(sa[5].trim()),
									   						 picture,
									   						 comment,
									   					sa[6].trim(),
									   					 createTime);
					ie.update(emp); // 新增進DB

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}
}

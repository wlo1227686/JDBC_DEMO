package Ex01_statement;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.sql.Timestamp;

public class _Main_UpdataEmployee {

	public static void main(String[] args) {
	Timestamp createTime = new Timestamp(System.currentTimeMillis());

		
//<1.�ק�浧���>------------------------------------------------------------			
	// 1 | U�X��PD�P�|�� | �F���Ū���L�� | 1970-05-05 |��U�|�� | 500000000 | 01_monkey_D_luffy.png | info_01_luffy.txt		
//			EmployeeDAO ie= new EmployeeDAO();
//			EmployeeBean emp = new EmployeeBean(1,"U�X��PD�P�|��","�F���Ū���L��",Date.valueOf("1970-05-05"),"��U�|��", 500000000,	"01_monkey_D_luffy.png",createTime);
//			ie.update(emp);	//�s�W�iDB		
	
//<2.�ק�h�����(�ϥΥ~���ɮ�)>------------------------------------------------------------		
		
		try(//java 7.0 ���@�۰��������귽
		BufferedReader bf = new BufferedReader(
							   new InputStreamReader(
								  new FileInputStream("statement_UpdateData.txt"),"BIG5"));
			){
		 String Read_line="";
		 EmployeeDAO ie= new EmployeeDAO();
			while((Read_line=bf.readLine())!= null){
				 String[] sa = Read_line.split("\\|");
				 EmployeeBean emp = new EmployeeBean(
						 		    Integer.parseInt(sa[0].trim()),
						 							 sa[1].trim(),
				 									 sa[2].trim(),
										Date.valueOf(sa[3].trim()),
													 sa[4].trim(),
									Integer.parseInt(sa[5].trim()),
													 sa[6].trim(),
													 createTime);	
				 ie.update(emp);	//�s�W�iDB		
				}
		}catch (IOException e){
			e.printStackTrace();
		}
	}
}

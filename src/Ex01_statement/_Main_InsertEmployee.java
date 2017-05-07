package Ex01_statement;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.sql.Timestamp;

public class _Main_InsertEmployee {

	public static void main(String[] args) {
		Timestamp createTime = new Timestamp(System.currentTimeMillis());
//<1.�s�W�浧���>------------------------------------------------------------		
		
//			EmployeeDAO ie= new EmployeeDAO();
//			EmployeeBean emp = new EmployeeBean("�X��PD�P�|��","�F���Ū���L��",Date.valueOf("1970-05-05"),"��U�|��",500000000,"01_monkey_D_luffy.png",createTime);
//			ie.insert(emp);	//�s�W�iDB			

		
//		String empname, String address, Date birthday, String title, int money, Blob pitture,Clob commit, String filename, Timestamp createtime
//		 �X��PD�P�|�� | �F���Ū���L�� | 1970-05-05 |��U�|�� | 500000000 | 01_monkey_D_luffy.png | info_01_luffy.txt

//<2.�s�W�h�����(�ϥΥ~���ɮ�)>------------------------------------------------------------				
		try(//java 7.0 ���@�۰��������귽
		BufferedReader bf = new BufferedReader(
							   new InputStreamReader(
								  new FileInputStream("Ex01_data\\Ex01_statement_Initialzation.txt"),"BIG5"));
			){
		 String Read_line="";
		 EmployeeDAO ie= new EmployeeDAO();
		 while((Read_line=bf.readLine())!= null){
			 String[] sa = Read_line.split("\\|");
			 EmployeeBean emp = new EmployeeBean(sa[0].trim(),
			 									 sa[1].trim(),
									Date.valueOf(sa[2].trim()),
												 sa[3].trim(),
								Integer.parseInt(sa[4].trim()),
												 sa[5].trim(),
												 createTime);	
			 ie.insert(emp);	//�s�W�iDB		
			}
//			EmployeeBean emp = new EmployeeBean("�X��PD�P�|��","�F���Ū���L��",Date.valueOf("1970-05-05"),"��U�|��",500000000,"01_monkey_D_luffy.png",createTime);

		}catch (IOException e){
			e.printStackTrace();
		}
	}
}

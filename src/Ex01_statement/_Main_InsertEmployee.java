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
//<1.新增單筆資料>------------------------------------------------------------		
		
//			EmployeeDAO ie= new EmployeeDAO();
//			EmployeeBean emp = new EmployeeBean("蒙其·D·魯夫","東方藍的佛夏村",Date.valueOf("1970-05-05"),"草帽魯夫",500000000,"01_monkey_D_luffy.png",createTime);
//			ie.insert(emp);	//新增進DB			

		
//		String empname, String address, Date birthday, String title, int money, Blob pitture,Clob commit, String filename, Timestamp createtime
//		 蒙其·D·魯夫 | 東方藍的佛夏村 | 1970-05-05 |草帽魯夫 | 500000000 | 01_monkey_D_luffy.png | info_01_luffy.txt

//<2.新增多筆資料(使用外部檔案)>------------------------------------------------------------				
		try(//java 7.0 提共自動關閉的資源
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
			 ie.insert(emp);	//新增進DB		
			}
//			EmployeeBean emp = new EmployeeBean("蒙其·D·魯夫","東方藍的佛夏村",Date.valueOf("1970-05-05"),"草帽魯夫",500000000,"01_monkey_D_luffy.png",createTime);

		}catch (IOException e){
			e.printStackTrace();
		}
	}
}

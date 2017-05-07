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

		
//<1.修改單筆資料>------------------------------------------------------------			
	// 1 | U蒙其·D·魯夫 | 東方藍的佛夏村 | 1970-05-05 |草帽魯夫 | 500000000 | 01_monkey_D_luffy.png | info_01_luffy.txt		
//			EmployeeDAO ie= new EmployeeDAO();
//			EmployeeBean emp = new EmployeeBean(1,"U蒙其·D·魯夫","東方藍的佛夏村",Date.valueOf("1970-05-05"),"草帽魯夫", 500000000,	"01_monkey_D_luffy.png",createTime);
//			ie.update(emp);	//新增進DB		
	
//<2.修改多筆資料(使用外部檔案)>------------------------------------------------------------		
		
		try(//java 7.0 提共自動關閉的資源
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
				 ie.update(emp);	//新增進DB		
				}
		}catch (IOException e){
			e.printStackTrace();
		}
	}
}

package Ex01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;

public class _Main_InsertEmployee {

	public static void main(String[] args) {
		String code="BIG5";//文字編碼
		
//<1.新增單筆資料>------------------------------------------------------------			
//			EmployeeDAO ie= new EmployeeDAO();
//			EmployeeBean emp = new EmployeeBean("林俊傑","台北縣",Date.valueOf("1980-04-19"), 40000,	65.5);
//			ie.insert(emp);	//新增進DB		
	
		
		
//<2.新增多筆資料(使用外部檔案)>------------------------------------------------------------				
		try(//java 7.0 提共自動關閉的資源
		BufferedReader bf = new BufferedReader(
							   new InputStreamReader(
								  new FileInputStream("EmployeeInitializationData"+code+".txt"),code));
			){
		 String Read_line="";
		 EmployeeDAO ie= new EmployeeDAO();
		 while((Read_line=bf.readLine())!= null){
			 String[] sa = Read_line.split("\\|");
			 EmployeeBean emp = new EmployeeBean(sa[0].trim(),
			 									 sa[1].trim(),
									Date.valueOf(sa[2].trim()),
								Integer.parseInt(sa[3].trim()),
							  Double.parseDouble(sa[4].trim()));	
			 ie.insert(emp);	//新增進DB		
			}

		}catch (IOException e){
			e.printStackTrace();
		}
	}
}

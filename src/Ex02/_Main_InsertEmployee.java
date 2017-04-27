
package Ex02;

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

public class _Main_InsertEmployee {

	public static void main(String[] args) {
		String code="UTF8";//文字編碼
		
//<1.新增單筆資料>------------------------------------------------------------			
//			EmployeeDAO ie= new EmployeeDAO();
//			EmployeeBean emp = new EmployeeBean("林俊傑","台北縣",Date.valueOf("1980-04-19"), 40000,	65.5);
//			ie.insert(emp);	//新增進DB		
	
		
		
//<2.新增多筆資料(使用外部檔案)>------------------------------------------------------------				
		try(//java 7.0 提共自動關閉的資源
		BufferedReader bf = new BufferedReader(
							   new InputStreamReader(
								  new FileInputStream("Ex02_EmployeeInitializationData"+code+".txt"),code));
			){
		 String Read_line="";
		 EmployeeDAO ie= new EmployeeDAO();
		 while((Read_line=bf.readLine())!= null){
			 String[] sa = Read_line.split("\\|");
//  連國華| 新北市UTF8| 1975-12-25| 40000| 65.1| _JSP.zip|comment1.txt			 
			 String name = sa[0].trim();
			 String address = sa[1].trim();
			 Date birthday = Date.valueOf(sa[2].trim());
			 int salary =  Integer.parseInt(sa[3].trim());
			 double weight = Double.parseDouble(sa[4].trim());		 
			String picture_filename  = sa[5].trim();
			String comment_filename  = sa[6].trim();			
			Blob picture = new SerialBlob(EmployeeDAO.binaryFileToByteArray2("Ex02_images/" + picture_filename));
			Clob comment = new SerialClob(EmployeeDAO.textFileToCharArray1("Ex02_text\\" + comment_filename,code));
			Timestamp createTime = new Timestamp(System.currentTimeMillis()); 
			
			EmployeeBean emp = new EmployeeBean(name,
					 						  address,
					 						 birthday,
					 						   salary,
					 						   weight,
					 						  picture,
					 						  comment,
					 					   createTime,
					 				 picture_filename);	
			 ie.insert(emp);	//新增進DB		
			}

		}catch (Exception e){
			e.printStackTrace();
		}
	}
}

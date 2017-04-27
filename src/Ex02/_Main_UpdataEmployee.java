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

public class _Main_UpdataEmployee {

	public static void main(String[] args) {
		String code="UTF8";//��r�s�X
		
//<1.�ק�浧���>------------------------------------------------------------			
		
//			EmployeeDAO ie= new EmployeeDAO();
//			EmployeeBean emp = new EmployeeBean(8,"�L�T��U","�x�_��",Date.valueOf("1980-04-19"), 40000,	65.5,);
//			ie.update(emp);	//�s�W�iDB		
	
		
		
//<2.�ק�h�����(�ϥΥ~���ɮ�)>------------------------------------------------------------		
		
		try(//java 7.0 ���@�۰��������귽
		BufferedReader bf = new BufferedReader(
							   new InputStreamReader(
								  new FileInputStream("Ex02_EmployeeUpdate"+code+".txt"),code));
			){
		 String Read_line="";
		 EmployeeDAO ie= new EmployeeDAO();
		 while((Read_line=bf.readLine())!= null){
			 String[] sa = Read_line.split("\\|");
//  �s���| �s�_��UTF8| 1975-12-25| 40000| 65.1| _JSP.zip|comment1.txt	
			 int empNo = Integer.parseInt(sa[0].trim());
			 String name = sa[1].trim();
			 String address = sa[2].trim();
			 Date birthday = Date.valueOf(sa[3].trim());
			 int salary =  Integer.parseInt(sa[4].trim());
			 double weight = Double.parseDouble(sa[5].trim());		 
			String picture_filename  = sa[6].trim();
			String comment_filename  = sa[7].trim();			
			Blob picture = new SerialBlob(EmployeeDAO.binaryFileToByteArray2("Ex02_images/" + picture_filename));
			Clob comment = new SerialClob(EmployeeDAO.textFileToCharArray1("Ex02_text\\" + comment_filename,code));
			Timestamp createTime = null; 
			
			EmployeeBean emp = new EmployeeBean(empNo,
												 name,
					 						  address,
					 						 birthday,
					 						   salary,
					 						   weight,
					 						  picture,
					 						  comment,
					 					   createTime,
					 				 picture_filename);	
			 ie.update(emp);	//�ק���		
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}

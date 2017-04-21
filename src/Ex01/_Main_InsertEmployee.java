package Ex01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;

public class _Main_InsertEmployee {

	public static void main(String[] args) {
		String code="BIG5";//��r�s�X
		
//<1.�s�W�浧���>------------------------------------------------------------			
//			EmployeeDAO ie= new EmployeeDAO();
//			EmployeeBean emp = new EmployeeBean("�L�T��","�x�_��",Date.valueOf("1980-04-19"), 40000,	65.5);
//			ie.insert(emp);	//�s�W�iDB		
	
		
		
//<2.�s�W�h�����(�ϥΥ~���ɮ�)>------------------------------------------------------------				
		try(//java 7.0 ���@�۰��������귽
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
			 ie.insert(emp);	//�s�W�iDB		
			}

		}catch (IOException e){
			e.printStackTrace();
		}
	}
}

package Ex01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;

public class _Main_UpdataEmployee {

	public static void main(String[] args) {
		String code="BIG5";//��r�s�X
		
//<1.�ק�浧���>------------------------------------------------------------			
		
//			EmployeeDAO ie= new EmployeeDAO();
//			EmployeeBean emp = new EmployeeBean(8,"�L�T��U","�x�_��",Date.valueOf("1980-04-19"), 40000,	65.5);
//			ie.update(emp);	//�s�W�iDB		
	
		
		
//<2.�ק�h�����(�ϥΥ~���ɮ�)>------------------------------------------------------------		
		
		try(//java 7.0 ���@�۰��������귽
		BufferedReader bf = new BufferedReader(
							   new InputStreamReader(
								  new FileInputStream("Ex01_EmployeeUpdateData"+code+".txt"),code));
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
								   Integer.parseInt(sa[4].trim()),
								 Double.parseDouble(sa[5].trim()));	
				ie.update(emp);	//�ק�g�JDB	
			}
		}catch (IOException e){
			e.printStackTrace();
		}
	}
}

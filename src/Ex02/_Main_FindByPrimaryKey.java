package Ex02;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;

public class _Main_FindByPrimaryKey {

	public static void main(String[] args) {
		int key_value = 50;
		EmployeeDAO ie = new EmployeeDAO();
		EmployeeBean mb = ie.findByPrimaryKey(key_value);
		if (mb == null) {
			System.out.println("查無此筆資料");
		} else {
			System.out.println(" 編號        薪水           體重       出生年月日          姓名                  地址");
			System.out.printf("%4d %6d %5.1f %tY/%tm/%td %-12s %-40s \n", mb.getEmpNo(), mb.getSalary(), mb.getWeight(),
					mb.getBirthday(), mb.getBirthday(), mb.getBirthday(), mb.getName(), mb.getAddress());
			try {
				String filename = mb.getFilename();
				filename = mb.getEmpNo() // 補上 _主鍵
						+ "_" + filename.substring(0,filename.lastIndexOf(".")) // 取出主檔名
						+ filename.substring(filename.lastIndexOf(".")); // 取出副檔名 (.jpg)
				
				File img_dir = new File("Ex02_Out_images\\");
				if (!img_dir.exists()){	img_dir.mkdirs(); }// 如果沒有則建立Ex02_images2資料夾
				
				File picture_file = new File(img_dir, filename);
				FileOutputStream fos = new FileOutputStream(picture_file);
					
				
				byte[] buf = mb.getPicture().getBytes(1L,
												 (int) mb.getPicture().length()	);
				
				ByteArrayInputStream bais = new ByteArrayInputStream(buf);
				
				byte[] b = new byte[8192];
				
				int len = 0;
				while ((len = bais.read(b)) != -1) {
					fos.write(b, 0, len);
				}fos.close();

				BufferedReader br = new BufferedReader(
										new InputStreamReader(
												mb.getComment().getAsciiStream(), "BIG5"));

				File dir = new File("Ex02_Out_comment\\");
				if (!dir.exists()) {
					dir.mkdirs();
				}
				PrintWriter pw = new PrintWriter(
									new FileWriter(
										new File(dir, mb.getEmpNo()+ "comment_"  + ".txt")
													),true);

				String line = null;
				while (	(line = br.readLine()	) != null) {
					pw.println(line);
				}pw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}

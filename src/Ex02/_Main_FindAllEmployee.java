package Ex02;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Clob;
import java.util.List;

public class _Main_FindAllEmployee {

	public static void main(String[] args) {
		EmployeeDAO ie = new EmployeeDAO();
		List<EmployeeBean> list = ie.findAllEmployees();
		if (list.size() == 0) {
			System.out.println("�d�L�������");
		} else {
			System.out.println(" �s��        �~��           �魫       �X�ͦ~���          �m�W                  �a�}");
			for (EmployeeBean mb : list) {
				System.out.printf("%4d %6d %5.1f %tY/%tm/%td %-12s %-40s \n", mb.getEmpNo(), mb.getSalary(),
						mb.getWeight(), mb.getBirthday(), mb.getBirthday(), mb.getBirthday(), mb.getName(),
						mb.getAddress());
				try {
					String filename = mb.getFilename();
					filename = mb.getEmpNo() // �ɤW _�D��
							+ "_" + filename.substring(0,filename.lastIndexOf(".")) // ���X�D�ɦW
							+ filename.substring(filename.lastIndexOf(".")); // ���X���ɦW (.jpg)
					
					File img_dir = new File("Ex02_Out_images\\");
					if (!img_dir.exists()){	img_dir.mkdirs(); }// �p�G�S���h�إ�Ex02_images2��Ƨ�
					
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
													mb.getComment().getAsciiStream(),"BIG5"	)
															);
					File dir = new File("Ex02_Out_comment\\");
					if (!dir.exists()){	dir.mkdirs(); }

					PrintWriter pw = new PrintWriter(
										new FileWriter(
											new File(dir,mb.getEmpNo() + "_comment"  + ".txt")
														), true);
					String line = null;
					while ((line = br.readLine()) != null) {
						pw.println(line);
					}pw.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		try{
			}catch (Exception e) {
				e.printStackTrace();
			}
	}

}

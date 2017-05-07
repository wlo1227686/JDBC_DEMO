package Ex03_callableStatement;

import java.sql.Blob;
import java.sql.Clob;
import java.util.List;

public class _Main_FindAllEmployee {
	
	public static void main(String[] args) {
		EmployeeDAO ie = new EmployeeDAO();
		List<EmployeeBean> list = ie.findAllEmployees();
		if (list.size() == 0) {
			System.out.println("查無此筆資料");
		} else {
			System.out.println(" 編號      姓名          出生地                出生年月日            稱號		賞金     	        檔名       	 最後更改日期 ");
			for (EmployeeBean mb : list) {
				System.out.printf("%3d  %-12s  %-12s %tY/%tm/%td    %-12s %6d  %-12s  %tY/%tm/%td  \n",
						mb.getEmpno(),mb.getEmpname(), mb.getAddress(), mb.getBirthday(),
						mb.getBirthday(), mb.getBirthday(),mb.getTitle(), mb.getMoney(),
						mb.getFilename(), mb.getCreatetime(), mb.getCreatetime(),mb.getCreatetime());
				Blob picture = mb.getPicture();
				Clob comment = mb.getComment();
				String filename = mb.getFilename();
				try {
					byte[] byteArray = picture.getBytes(1L, (int) picture.length());
					ie.write_ByteArray_To_ByteFile(mb.getEmpno(),byteArray, filename);
					ie.write_CharArray_To_TextFile(mb.getEmpno(),mb.getEmpname(),comment.getAsciiStream());

				} catch (Exception e) {
					e.getMessage();
				}
			}
		}

	}

}

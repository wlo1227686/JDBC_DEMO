package Ex01_statement;

import java.util.List;

public class _Main_FindAllEmployee {

	public static void main(String[] args) {
		EmployeeDAO ie= new EmployeeDAO();
		List<EmployeeBean> list = ie.findAllEmployees();
		if(list.size() == 0){
			System.out.println("查無此筆資料");
		}else{
			System.out.println(" 編號      姓名          		出生地                出生年月日            稱號		賞金     	        檔名       	 最後更改日期 ");
			for(EmployeeBean mb : list){
			System.out.printf("%3d  %-30s %-20s %tY/%tm/%td    %-12s %6d  %-12s  %tY/%tm/%td  \n",
								mb.getEmpno(),
								mb.getEmpname(),
								mb.getAddress(),
								mb.getBirthday(),mb.getBirthday(),mb.getBirthday(),
								mb.getTitle(),
								mb.getMoney(),
								mb.getFilename(),
								mb.getCreatetime(),mb.getCreatetime(),mb.getCreatetime()
								);
			}
		}
		
	}

}

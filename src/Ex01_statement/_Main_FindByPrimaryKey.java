package Ex01_statement;

public class _Main_FindByPrimaryKey {

	public static void main(String[] args) {
		int key_value=8;
		EmployeeDAO ie= new EmployeeDAO();
		EmployeeBean mb = ie.findByPrimaryKey(key_value);
		if(mb == null){
			System.out.println("查無此筆資料");
		}else{
			System.out.println(" 編號      姓名          出生地                出生年月日            稱號		賞金     	        檔名       	 最後更改日期 ");
			System.out.printf("%3d  %-12s  %-12s %tY/%tm/%td    %-12s %6d  %-12s  %tY/%tm/%td  \n",
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

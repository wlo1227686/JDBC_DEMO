package Ex01;

public class _Main_FindByPrimaryKey {

	public static void main(String[] args) {
		int key_value=8;
		EmployeeDAO ie= new EmployeeDAO();
		EmployeeBean mb = ie.findByPrimaryKey(key_value);
		if(mb == null){
			System.out.println("查無此筆資料");
		}else{
			System.out.println(" 編號        薪水           體重       出生年月日          姓名                  地址");
			System.out.printf("%4d %6d %5.1f %tY/%tm/%td %-12s %-40s \n",
								mb.getEmpNo(),
								mb.getSalary(),
								mb.getWeight(),
								mb.getBirthday(),mb.getBirthday(),mb.getBirthday(),
								mb.getName(),
								mb.getAddress()
							);
		}

	}

}

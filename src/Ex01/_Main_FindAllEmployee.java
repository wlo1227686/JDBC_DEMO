package Ex01;

import java.util.List;

public class _Main_FindAllEmployee {

	public static void main(String[] args) {
		EmployeeDAO ie= new EmployeeDAO();
		List<EmployeeBean> list = ie.findAllEmployees();
		if(list.size() == 0){
			System.out.println("查無此筆資料");
		}else{
			System.out.println(" 編號        薪水           體重       出生年月日          姓名                  地址");
			for(EmployeeBean mb : list){
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

}

package Ex01;

import java.util.List;

public class _Main_FindAllEmployee {

	public static void main(String[] args) {
		EmployeeDAO ie= new EmployeeDAO();
		List<EmployeeBean> list = ie.findAllEmployees();
		if(list.size() == 0){
			System.out.println("�d�L�������");
		}else{
			System.out.println(" �s��        �~��           �魫       �X�ͦ~���          �m�W                  �a�}");
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

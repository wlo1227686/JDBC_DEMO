package Ex01;

public class _Main_FindByPrimaryKey {

	public static void main(String[] args) {
		int key_value=8;
		EmployeeDAO ie= new EmployeeDAO();
		EmployeeBean mb = ie.findByPrimaryKey(key_value);
		if(mb == null){
			System.out.println("�d�L�������");
		}else{
			System.out.println(" �s��        �~��           �魫       �X�ͦ~���          �m�W                  �a�}");
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

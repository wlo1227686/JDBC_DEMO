package Ex01_statement;

public class _Main_FindByPrimaryKey {

	public static void main(String[] args) {
		int key_value=8;
		EmployeeDAO ie= new EmployeeDAO();
		EmployeeBean mb = ie.findByPrimaryKey(key_value);
		if(mb == null){
			System.out.println("�d�L�������");
		}else{
			System.out.println(" �s��      �m�W          �X�ͦa                �X�ͦ~���            �ٸ�		���     	        �ɦW       	 �̫����� ");
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

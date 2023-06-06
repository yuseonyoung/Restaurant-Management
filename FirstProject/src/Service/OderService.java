package Service;

import java.util.List;
import java.util.Map;

import DAO.OderDAO;

public class OderService {
	private static OderService instance= null;
	private OderService() {}
	
	public static OderService getInstance() {
		if (instance == null) {
			instance = new OderService();
		}
		return instance;
	}
//-------------------------------------------------------�̱��� ����
	
	OderDAO dao = OderDAO.getInstance();

	public void selectOder(){
		List<Map<String, Object>> list = dao.selectList();
		
		System.out.println("������������������������������������������������������������������������");
		System.out.println("               ��� �� ���� ���� ����� ��� ");
		System.out.println();
		for(Map<String, Object> n : list) {
		System.out.printf(" %s    %s    %d��    %s \n",n.get("I_ID"),n.get("I_NAME")
				,Integer.parseInt((String.valueOf(n.get("I_INVENTORY"))).trim()),n.get("I_ORIGIN"));
		System.out.println("������������������������������������������������������������������������");
		}
	}
//------------------------------------------------------�������� ���� ����� ��� �����ֱ�	
}

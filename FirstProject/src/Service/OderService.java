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
//-------------------------------------------------------싱글톤 패턴
	
	OderDAO dao = OderDAO.getInstance();

	public void selectOder(){
		List<Map<String, Object>> list = dao.selectList();
		
		System.out.println("────────────────────────────────────");
		System.out.println("               재고가 얼마 남지 않은 식재료 목록 ");
		System.out.println();
		for(Map<String, Object> n : list) {
		System.out.printf(" %s    %s    %d개    %s \n",n.get("I_ID"),n.get("I_NAME")
				,Integer.parseInt((String.valueOf(n.get("I_INVENTORY"))).trim()),n.get("I_ORIGIN"));
		System.out.println("────────────────────────────────────");
		}
	}
//------------------------------------------------------재고수량이 적은 식재료 목록 보여주기	
}

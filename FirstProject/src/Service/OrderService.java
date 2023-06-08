package Service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import DAO.OrderDAO;
import DTO.OrderDTO;

public class OrderService {
	Scanner sc = new Scanner(System.in);
	private static OrderService instance= null;
	private OrderService() {}
	
	public static OrderService getInstance() {
		if (instance == null) {
			instance = new OrderService();
		}
		return instance;
	}
//-------------------------------------------------------싱글톤 패턴
	
	OrderDAO dao = OrderDAO.getInstance();

	public void selectOrder(){
		int count =1;
		List<Map<String, Object>> list = dao.selectList();
		
		System.out.println("───────────────────────────────────────");
		System.out.println("               재고가 얼마 남지 않은 식재료 목록 ");
		System.out.println();
		System.out.println("  순번     식재료코드          식재료명       재고        원산지");
		for(Map<String, Object> n : list) {
		System.out.printf("%3d  %-11s %3s      %5d개        %3s \n",(count++),n.get("I_ID"),n.get("I_NAME")
				,Integer.parseInt((String.valueOf(n.get("I_INVENTORY"))).trim()),n.get("I_ORIGIN"));
		System.out.println("───────────────────────────────────────");
		
		
		}
	}
//------------------------------------------------------재고수량이 적은 식재료 목록 보여주기	
	public void ingredientOrder() {
		System.out.print("구입하실 식재료명을 입력하세요 : ");
		String name = sc.nextLine();
		OrderDTO od = new OrderDTO();
		od.setI_name(name);
		
		Map<String, Object> ingredient = dao.chooseOne(od.getI_name());
		Iterator<Map.Entry<String, Object>> iterator = ingredient.entrySet().iterator();
		
		while (iterator.hasNext()) {
		    Map.Entry<String, Object> entry = iterator.next();
		    
		    Object value = entry.getValue();
		    System.out.printf(" %3s ",value);
		
		}
	}
}

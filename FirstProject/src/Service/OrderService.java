package Service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import DAO.OrderDAO;
import DTO.OrderDTO;
import JDBCUtil.ScanUtil;

public class OrderService {
	Scanner sc = new Scanner(System.in);
	OrderDTO od = new OrderDTO();
	
	private static OrderService instance= null;
	private OrderService() {}
	
	public static OrderService getInstance() {
		if (instance == null) {
			instance = new OrderService();
		}
		return instance;
	}
//-------------------------------------------------------�̱��� ����
	
	OrderDAO dao = OrderDAO.getInstance();

	public void selectOrder(){
		int count =1;
		List<Map<String, Object>> list = dao.selectList();
		
		System.out.println("������������������������������������������������������������������������������");
		System.out.println("               ��� �� ���� ���� ����� ��� ");
		System.out.println();
		System.out.println("  ����     ������ڵ�          ������       ���        ������");
		for(Map<String, Object> n : list) {
		System.out.printf("%3d  %-11s %3s      %5d��        %3s \n",(count++),n.get("I_ID"),n.get("I_NAME")
				,Integer.parseInt((String.valueOf(n.get("I_INVENTORY"))).trim()),n.get("I_ORIGIN"));
		System.out.println("������������������������������������������������������������������������������");
		
		
		}
	}
//------------------------------------------------------�������� ���� ����� ��� �����ֱ�	
	public void ingredientOrder() {
		System.out.print("�����Ͻ� �������� �Է� �ϼ��� : ");
		String name = sc.nextLine();
		od.setI_name(name);
		
		System.out.print("�����Ͻ� ������ �Է� �ϼ��� : ");
		int num = ScanUtil.nextInt();
		od.setI_inventory(num);
		
		int ingredient = dao.upDateQty(od.getI_name(), od.getI_inventory());
//		Iterator<Map.Entry<String, Object>> iterator = ingredient.entrySet().iterator();
//		
//		while (iterator.hasNext()) {
//		    Map.Entry<String, Object> entry = iterator.next();
//		    
//		    Object value = entry.getValue();
//		    System.out.printf(" %3s ",value);
//		
//		}
	}
}

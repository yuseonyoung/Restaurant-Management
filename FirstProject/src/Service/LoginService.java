package Service;

import java.util.Map;
import java.util.Scanner;

import DAO.LoginDAO;
//import dao.LoginDAO;

//�ܺηκ��� �޾Ƶ��ϼ��ִ°��� �Է¹޴� Ŭ����
public class LoginService {
	Scanner sc = new Scanner(System.in);
	// �̱���
	private static LoginService instance;

	private LoginService() {
	}

	public static LoginService getInstance() {
		if (instance == null) {
			instance = new LoginService();
		}
		return instance;
	}
//----------------------------------------------------������� �̱��� ����

	public static int loginCount = 0;
	LoginDAO dao = LoginDAO.getInstance();
	Map<String, Object> result;

	// �α��� �޼���
	public void login() {
		System.out.println("������  �α��� ������");

		while (true) {
			
			System.out.print("ID �Է� : ");
			String id = sc.nextLine();
			
			System.out.print("PW �Է� : ");
			String pw = sc.nextLine();

			result = dao.login(id, pw);

			loginCount++;

			if (result == null) {
				System.out.println("ID�� PW�� Ȯ�����ּ���.");
			}else {
				System.out.println("������ �������� �����Ͽ����ϴ�.");
				break;
			}
			
			if (loginCount == 3) {
				System.out.println("3ȸ�̻� ������ �߸� �Է��Ͽ����ϴ�.");
				System.out.println("10�ʵ��� �α����� ���� �˴ϴ�.");
				for (int i = 0; i < 10; i++) {
					System.out.print("��");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println();
				loginCount = 0;
				continue;

			}

		}
	}

//-----------------------------------------------------------------������� �α��� �޼ҵ�
	public Map<String, Object> isDuplicate(String id) {
		result = dao.select(id);
		return result;
	}
//-----------------------------------------------------------------�ߺ��� id �˻� �޼ҵ�	
}

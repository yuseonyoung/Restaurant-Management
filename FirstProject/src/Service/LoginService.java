package Service;

import java.util.Map;
import java.util.Scanner;

import DAO.LoginDAO;
//import dao.LoginDAO;

//외부로부터 받아들일수있는것을 입력받는 클래스
public class LoginService {
	Scanner sc = new Scanner(System.in);
	// 싱글톤
	private static LoginService instance;

	private LoginService() {
	}

	public static LoginService getInstance() {
		if (instance == null) {
			instance = new LoginService();
		}
		return instance;
	}
//----------------------------------------------------여기까지 싱글톤 구현

	public static int loginCount = 0;
	LoginDAO dao = LoginDAO.getInstance();
	Map<String, Object> result;

	// 로그인 메서드
	public void login() {
		System.out.println("▶▶▶  로그인 ◀◀◀");

		while (true) {
			
			System.out.print("ID 입력 : ");
			String id = sc.nextLine();
			
			System.out.print("PW 입력 : ");
			String pw = sc.nextLine();

			result = dao.login(id, pw);

			loginCount++;

			if (result == null) {
				System.out.println("ID나 PW를 확인해주세요.");
			}else {
				System.out.println("관리자 계정으로 접속하였습니다.");
				break;
			}
			
			if (loginCount == 3) {
				System.out.println("3회이상 정보를 잘못 입력하였습니다.");
				System.out.println("10초동안 로그인이 제한 됩니다.");
				for (int i = 0; i < 10; i++) {
					System.out.print("■");
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

//-----------------------------------------------------------------여기까지 로그인 메소드
	public Map<String, Object> isDuplicate(String id) {
		result = dao.select(id);
		return result;
	}
//-----------------------------------------------------------------중복된 id 검사 메소드	
}

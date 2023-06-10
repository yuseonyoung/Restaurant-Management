package Service;

import java.util.Map;
import java.util.Scanner;

import Controller.MainController;
import DAO.LoginDAO;
import DTO.EmployeeDTO;
import JDBCUtil.TotalView;
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
	public Map<String, Object> login() {
		System.out.println("▶▶▶  로그인 ◀◀◀");

		Loop1: while (true) {

			System.out.print("ID 입력 : ");
			String id = sc.nextLine();

			System.out.print("PW 입력 : ");
			String pw = sc.nextLine();
			while (true) {
				System.out.println("로그인 하시려면 Y, 돌아가시려면 N을 눌러주세요");
				String value = sc.nextLine();
				loginCount++;

				EmployeeDTO eld = new EmployeeDTO();
				try {
					if (value.equalsIgnoreCase("Y")) {
												
						result = dao.login(id, pw);
						String rank=(String)result.get("E_RANK");
						eld.setE_rank(rank);
						
					} else if (value.equalsIgnoreCase("N")) {
						TotalView lv = TotalView.getInstance();
						lv.init();
					} else {
						System.out.println("잘못된값을 입력하였습니다. 다시 입력해주세요.");
						continue;
					}
					
					if (result == null) {
						System.out.println("ID나 PW를 확인해주세요.");
						
					} else {
						System.out.printf("%3s 계정으로 접속하였습니다.", eld.getE_rank());
						break Loop1;
					}
					
					if (loginCount == 3) {
						System.out.println("3회이상 정보를 잘못 입력하였습니다.");
						System.out.println("10초동안 로그인이 제한 됩니다.");
						for (int i = 0; i < 10; i++) {
							System.out.print("■");
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						loginCount = 0;
					}
				} catch (NumberFormatException e) {
					System.out.println("잘못된 값이 입력되었습니다.");
					continue Loop1;
				}

				System.out.println();
				continue Loop1;
			}
		}
		return result;
	}

//-----------------------------------------------------------------여기까지 로그인 메소드
	public Map<String, Object> isDuplicate(String id) {
		result = dao.select(id);
		return result;
	}
//-----------------------------------------------------------------중복된 id 검사 메소드	
}

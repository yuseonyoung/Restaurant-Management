package Controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import Service.LoginService;
import Service.OderService;

public class MainController {
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			new MainController().init();
			new MainController().managerSelect();
		}catch(Exception e) {
			e.printStackTrace();
		}	
	}
	public void init() {
		 int num = 0;
		 while(true) {
			System.out.println("┌────────────────────────────────────────────┐");
			System.out.println("│                                            │");
			System.out.println("│                                            │");
			System.out.println("│                                            │");
			System.out.println("│            레스토랑 관리 시스템 입니다                                 │");
			System.out.println("│                                            │");
			System.out.println("│          원하시는 모드를 숫자로 선택해 주세요.   	     │");
			System.out.println("│                                            │");
			System.out.println("│                                            │");
			System.out.println("│   1. 관리자                                             2. 손님	     │");
			System.out.println("│                                            │");
			System.out.println("│                                            │");
			System.out.println("└────────────────────────────────────────────┘");
			
			try {
			    num = Integer.valueOf(sc.nextLine());
			    break;
			} catch (NumberFormatException e) {
			    System.out.println("잘못된 값이 입력되었습니다.");
			    continue;
			}

		 }
				switch(num){
					case 1:
						System.out.println("┌────────────────────────────────────────────┐");
						System.out.println("│                                            │");
						System.out.println("│                                            │");
						System.out.println("│                                            │");
						System.out.println("│       관리자의 아이디와 패스워드를 입력해주세요        	     │");
						System.out.println("│                                            │");
						System.out.println("│       ID :                         	     │");
						System.out.println("│                                            │");
						System.out.println("│       PW :                                 │");
						System.out.println("│    	                                     │");
						System.out.println("│                                            │");
						System.out.println("│                                            │");
						System.out.println("└────────────────────────────────────────────┘");
						
						LoginService login = LoginService.getInstance();
						login.login();
						break;
					case 2:
						//손님꺼 구현
					default :
						System.out.println("잘못된 값을 입력 하였습니다.");
				}
			}
			
	
	public void managerSelect() {
		int num=0;
		while(true) {
		System.out.println("┌─────────────────────────────────────┐");
		System.out.println("│                                     │");
		System.out.println("│  1. 식재료 관리                                                        │");
		System.out.println("│                                     │");
		System.out.println("│  2. 예약 관리                                                           │");
		System.out.println("│                                     │");
		System.out.println("│  3. 직원 관리                                                           │");
		System.out.println("│                                     │");
		System.out.println("└─────────────────────────────────────┘");
		try {
		    num = Integer.valueOf(sc.nextLine());
		    break;
		} catch (NumberFormatException e) {
		    System.out.println("잘못된 값이 입력되었습니다.");
		    continue;
		}

	 }	
		switch(num) {
			case 1:
				System.out.println("┌─────────────────────────────────────┐");
				System.out.println("│                                     │");
				System.out.println("│  1. 식재료 구입                                                        │");
				System.out.println("│                                     │");
				System.out.println("│  2. 식재료 조회                                                        │");
				System.out.println("│                                     │");
				System.out.println("│  3. 식재료 폐기                                                        │");
				System.out.println("│                                     │");
				System.out.println("└─────────────────────────────────────┘");
				OderService order = OderService.getInstance();
				order.selectOder();
				break;
			case 2:
				System.out.println("┌─────────────────────────────────────┐");
				System.out.println("│                                     │");
				System.out.println("│  1. 예약 조회                                                           │");
				System.out.println("│                                     │");
				System.out.println("└─────────────────────────────────────┘");
				break;
			case 3:
				System.out.println("┌─────────────────────────────────────┐");
				System.out.println("│                                     │");
				System.out.println("│  1. 직원 조회                                                           │");
				System.out.println("│                                     │");
				System.out.println("└─────────────────────────────────────┘");
				break;
			default : 	
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
				break;
		}
	}
}

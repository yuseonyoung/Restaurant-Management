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
			System.out.println("��������������������������������������������������������������������������������������������");
			System.out.println("��                                            ��");
			System.out.println("��                                            ��");
			System.out.println("��                                            ��");
			System.out.println("��            ������� ���� �ý��� �Դϴ�                                 ��");
			System.out.println("��                                            ��");
			System.out.println("��          ���Ͻô� ��带 ���ڷ� ������ �ּ���.   	     ��");
			System.out.println("��                                            ��");
			System.out.println("��                                            ��");
			System.out.println("��   1. ������                                             2. �մ�	     ��");
			System.out.println("��                                            ��");
			System.out.println("��                                            ��");
			System.out.println("��������������������������������������������������������������������������������������������");
			
			try {
			    num = Integer.valueOf(sc.nextLine());
			    break;
			} catch (NumberFormatException e) {
			    System.out.println("�߸��� ���� �ԷµǾ����ϴ�.");
			    continue;
			}

		 }
				switch(num){
					case 1:
						System.out.println("��������������������������������������������������������������������������������������������");
						System.out.println("��                                            ��");
						System.out.println("��                                            ��");
						System.out.println("��                                            ��");
						System.out.println("��       �������� ���̵�� �н����带 �Է����ּ���        	     ��");
						System.out.println("��                                            ��");
						System.out.println("��       ID :                         	     ��");
						System.out.println("��                                            ��");
						System.out.println("��       PW :                                 ��");
						System.out.println("��    	                                     ��");
						System.out.println("��                                            ��");
						System.out.println("��                                            ��");
						System.out.println("��������������������������������������������������������������������������������������������");
						
						LoginService login = LoginService.getInstance();
						login.login();
						break;
					case 2:
						//�մԲ� ����
					default :
						System.out.println("�߸��� ���� �Է� �Ͽ����ϴ�.");
				}
			}
			
	
	public void managerSelect() {
		int num=0;
		while(true) {
		System.out.println("������������������������������������������������������������������������������");
		System.out.println("��                                     ��");
		System.out.println("��  1. ����� ����                                                        ��");
		System.out.println("��                                     ��");
		System.out.println("��  2. ���� ����                                                           ��");
		System.out.println("��                                     ��");
		System.out.println("��  3. ���� ����                                                           ��");
		System.out.println("��                                     ��");
		System.out.println("������������������������������������������������������������������������������");
		try {
		    num = Integer.valueOf(sc.nextLine());
		    break;
		} catch (NumberFormatException e) {
		    System.out.println("�߸��� ���� �ԷµǾ����ϴ�.");
		    continue;
		}

	 }	
		switch(num) {
			case 1:
				System.out.println("������������������������������������������������������������������������������");
				System.out.println("��                                     ��");
				System.out.println("��  1. ����� ����                                                        ��");
				System.out.println("��                                     ��");
				System.out.println("��  2. ����� ��ȸ                                                        ��");
				System.out.println("��                                     ��");
				System.out.println("��  3. ����� ���                                                        ��");
				System.out.println("��                                     ��");
				System.out.println("������������������������������������������������������������������������������");
				OderService order = OderService.getInstance();
				order.selectOder();
				break;
			case 2:
				System.out.println("������������������������������������������������������������������������������");
				System.out.println("��                                     ��");
				System.out.println("��  1. ���� ��ȸ                                                           ��");
				System.out.println("��                                     ��");
				System.out.println("������������������������������������������������������������������������������");
				break;
			case 3:
				System.out.println("������������������������������������������������������������������������������");
				System.out.println("��                                     ��");
				System.out.println("��  1. ���� ��ȸ                                                           ��");
				System.out.println("��                                     ��");
				System.out.println("������������������������������������������������������������������������������");
				break;
			default : 	
				System.out.println("�߸� �Է��ϼ̽��ϴ�. �ٽ� �Է����ּ���.");
				break;
		}
	}
}

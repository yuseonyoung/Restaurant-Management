package Service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import DAO.SalesDAO;
import DTO.SalesDTO;

public class SalesService {
//매출조회 : 주문시 발생하는 수익을 일별, 주간별로 조회가능
	Scanner sc = new Scanner(System.in);
	SalesDTO sd = new SalesDTO();
	// 싱글톤
	private static SalesService instance;

	private SalesService() {
	}

	public static SalesService getInstance() {
		if (instance == null) {
			instance = new SalesService();
		}
		return instance;
	}
//----------------------------------------------------여기까지 싱글톤 구현
	
	SalesDAO dao = SalesDAO.getInstance();
	DecimalFormat df = new DecimalFormat("#,###");
	
	//매일매출
	public void dailySales() {
		int count = 1;
		System.out.print("조회하실 연도를 입력하세요 (YYYY): ");
		String year = sc.nextLine();
		System.out.print("조회하실 월을 입력하세요 (MM): ");
		String month = sc.nextLine();
		if(month.length() == 1) {
			month = "0"+month;
		}
		
		List<Object> param = new ArrayList<Object>();
		
		param.add(year.substring(2)+month);
		List<Map<String, Object>> list = dao.selectDailySalesList(param);
		
		System.out.println("───────────────────────────────────────");
		System.out.println(year+"년 "+month+"월 일간 매출 내역");
		System.out.println();
		System.out.println("\t순번\t일자\t매출액");
		for(Map<String, Object> n : list) {
			System.out.printf("\t%d\t%s\t%7d\n",(count++),n.get("DATE"),n.get("GAIN"));
			System.out.println("───────────────────────────────────────");
		
		}
	}	
	//주간매출	
	public void weeklySales() {
	
		System.out.print("조회하실 연도를 입력하세요 (YYYY): ");
		String year = sc.nextLine();
		System.out.print("조회하실 월을 입력하세요 (MM): ");
		String month = sc.nextLine();
		if(month.length() == 1) {
			month = "0"+month;
		}
		// 입력받은 연도와 월을 기반으로 주차의 시작일과 종료일 계산
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(year), Integer.parseInt(month)- 1, 1); // month는 0부터 시작하기 때문에 -1을 해줍니다.
        int startWeek = calendar.get(Calendar.WEEK_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.WEEK_OF_MONTH, startWeek);
        String startDate = new SimpleDateFormat("yyMMdd").format(calendar.getTime());

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        int endWeek = calendar.get(Calendar.WEEK_OF_MONTH);
        calendar.set(Calendar.WEEK_OF_MONTH, endWeek);
        String endDate = new SimpleDateFormat("yyMMdd").format(calendar.getTime());
		
		List<Object> param = new ArrayList<Object>();
		
		param.add(startDate);
		param.add(endDate);
		
		List<Map<String, Object>> list = dao.selectDailySalesList(param);
		
		System.out.println("───────────────────────────────────────");
		System.out.println(year+"년 "+month+"월 주간별 매출내역");
		System.out.println();
		System.out.println("\t주차\t\t일자\t매출액");
		for(Map<String, Object> n : list) {
			System.out.printf("\t%s\t%s ~ %s\t%10s\n",n.get("WEEK"),n.get("MIN_DATE")
							 ,n.get("MAX_DATE"),df.format(n.get("GAIN")));
			System.out.println("───────────────────────────────────────");
		
		}		
	}
	
	//매일지출 
	public void dailyCost() {
		int count = 1;
		System.out.print("조회하실 연도를 입력하세요 (YYYY): ");
		String year = sc.nextLine();
		System.out.print("조회하실 월을 입력하세요 (MM): ");
		String month = sc.nextLine();
		if(month.length() == 1) {
			month = "0"+month;
		}
		
		List<Object> param = new ArrayList<Object>();
		
		param.add(year.substring(2)+month);
		List<Map<String, Object>> list = dao.selectDailyCostList(param);
		
		System.out.println("───────────────────────────────────────");
		System.out.println(year+"년 "+month+"월 일간 지출내역");
		System.out.println();
		System.out.println("\t순번\t일자\t식재료명\t총 지출액");
		for(Map<String, Object> n : list) {
			System.out.printf("\t%d\t%s\t%s\t%10s\n",(count++),n.get("BDATE"),n.get("I_NAME"),df.format(n.get("COST")));
			System.out.println("───────────────────────────────────────");
		}
	}	
	
	//주간 지출
	public void weeklyCost() {
		
		System.out.print("조회하실 연도를 입력하세요 (YYYY): ");
		String year = sc.nextLine();
		System.out.print("조회하실 월을 입력하세요 (MM): ");
		String month = sc.nextLine();
		if(month.length() == 1) {
			month = "0"+month;
		}
		// 입력받은 연도와 월을 기반으로 주차의 시작일과 종료일 계산
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(year), Integer.parseInt(month)- 1, 1); // month는 0부터 시작하기 때문에 -1을 해줍니다.
        int startWeek = calendar.get(Calendar.WEEK_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.WEEK_OF_MONTH, startWeek);
        String startDate = new SimpleDateFormat("yyMMdd").format(calendar.getTime());

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        int endWeek = calendar.get(Calendar.WEEK_OF_MONTH);
        calendar.set(Calendar.WEEK_OF_MONTH, endWeek);
        String endDate = new SimpleDateFormat("yyMMdd").format(calendar.getTime());
		
		List<Object> param = new ArrayList<Object>();
		
		param.add(startDate);
		param.add(endDate);
		
		List<Map<String, Object>> list = dao.selectWeeklyCostList(param);
		
		System.out.println("───────────────────────────────────────");
		System.out.println(year+"년 "+month+"월 주간별 지출내역");
		System.out.println();
		System.out.println("\t주차\t\t일자\t지출액");
		for(Map<String, Object> n : list) {
			System.out.printf("\t%s\t%s ~ %s\t%10s\n",n.get("WEEK"),n.get("MIN_DATE")
							 ,n.get("MAX_DATE"),df.format(n.get("COST")));
			System.out.println("───────────────────────────────────────");
		
		}		
	}
	//매일 순수익
	public void dailyGain() {
		int count = 1;
		System.out.print("조회하실 연도를 입력하세요 (YYYY): ");
		String year = sc.nextLine();
		System.out.print("조회하실 월을 입력하세요 (MM): ");
		String month = sc.nextLine();
		if(month.length() == 1) {
			month = "0"+month;
		}
		
		List<Object> param = new ArrayList<Object>();
		
		param.add(year.substring(2)+month);
		List<Map<String, Object>> list = dao.selectDailyGainList(param);
		
		System.out.println("───────────────────────────────────────");
		System.out.println(year+"년 "+month+"월 일간 순수익내역");
		System.out.println("\t\t[순수익 : 매출 - 지출]");
		System.out.println();
		System.out.println("\t순번\t일자\t순수익");
		for(Map<String, Object> n : list) {
			System.out.printf("\t%d\t%s\t%s\t%10s\n",(count++),n.get("DATE"),df.format(n.get("GAIN")));
			System.out.println("───────────────────────────────────────");
		}
	}
	
	//월간 직원 총 임금
	public void empSal() {
		
		Map<String, Object> map = dao.selectEmpSal();
		System.out.printf("현재 레스토랑 총 직원의 임금은 %s원 입니다.",df.format(map.get("E_SAL")));
	}
	
	//주간 순수익 + 막대그래프
	public void weeklyGain() {
		
		System.out.print("조회하실 연도를 입력하세요 (YYYY): ");
		String year = sc.nextLine();
		System.out.print("조회하실 월을 입력하세요 (MM): ");
		String month = sc.nextLine();
		if(month.length() == 1) {
			month = "0"+month;
		}
		
		List<Object> param = new ArrayList<Object>();
		
		param.add(year.substring(2)+month);
		List<Map<String, Object>> list = dao.selectDailyGainList(param);
		
		System.out.println("───────────────────────────────────────");
		System.out.println(year+"년 "+month+"월 주간 순수익내역");
		System.out.println("\t\t[순수익 : 매출 - 지출]");
		System.out.println();
		System.out.println("\t주차\t\t일자\t지출액");
		for(Map<String, Object> n : list) {
			System.out.printf("\t%s\t%s ~ %s\t%10s\n",n.get("WEEK"),n.get("MIN_DATE")
							 ,n.get("MAX_DATE"),df.format(n.get("GAIN")));
			System.out.println("───────────────────────────────────────");
			
		}
		System.out.println(year+"년 "+month+"월 주간 순수익내역 그래프");
		
		for(int i=10; i>=-1; i--) {
            if(i!=0) {
                System.out.printf("%3d", i*10);
            }else {
                System.out.print("   ");
            }    
            for(int j=0; j<list.size(); j++) {
                if(i>0) {
                    System.out.printf("   %c", getGraph(i*10, ((int)list.get(j).get("GAIN"))/10000));
                }else if(i==0) {
                    System.out.printf("   %s", list.get(j).get("WEEK"));
                }else if(i<0) {
                	System.out.print(((double)list.get(j).get("GAIN")/10000)+"(단위 : 만원)");
                }
            }//end j
            System.out.println();
            if(i==1||i==0) {
            	System.out.println("───────────────────────────────────────");
            }
        }//end i
		
	}	
		
	//그래프 출력시 ■ 반환	
	public char getGraph(int standard, int gain) {
		return gain >= standard ? '■' : '　';        // 점수가 기준점수 이상이면 * 출력

	}

}
//	public String format(String value) {
//		return df.format(Integer.parseInt(value));
//	}



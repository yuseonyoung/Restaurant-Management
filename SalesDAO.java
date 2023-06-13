package DAO;


import java.util.List;
import java.util.Map;

import JDBCUtil.JdbcUtil;

public class SalesDAO {
	private static SalesDAO instance=null;
	
	private SalesDAO() {}
	
	public static SalesDAO getInstance() {
		if (instance == null) {
			instance = new SalesDAO();
		}
		return instance;
	}
//----------------------------------------------------------���� ���� �̱���
	
	JdbcUtil jdbc = JdbcUtil.getInstance();
	
	// �Ϻ��� ���� ��ȸ
	public List<Map<String, Object>> selectDailySalesList(List<Object> param){
		String sql = " SELECT SUBSTR(A.O_ID,1,6) AS DATE, SUM(.OF_QTY*B.F_PARICE) AS GAIN"
					+ " FROM O_FOOD A"
					+ " INNER JOIN FOOD B"
					+ " ON A.F_ID = B.F_ID"
				    + " WHERE SUBSTR(A.O_ID,1,4) = ?"		//? : ���� 
				    + " GROUP BY SUBSTR(A.O_ID,1,6)";	
		return (List<Map<String, Object>>) jdbc.selectList(sql,param);
	}
	
	// �ְ����� ���� ��ȸ	
	public List<Map<String, Object>> selectWeeklySalesList(List<Object> param){
		String sql =  " SELECT "
					+ " 	MIN(SUBSTR(A.O_ID,1,6)) AS MIN_DATE"
					+ " 	, MAX(SUBSTR(A.O_ID,1,6)) AS MAX_DATE"
					+ " 	, TO_CHAR(TO_DATE(SUBSTR(A.O_ID,1,6),'YYMMDD'),'IW')||'��' AS WEEK" 
					+ " 	, SUM(A.OF_QTY * B.F_PRICE) AS GAIN" 
					+ " FROM O_FOOD A" 
					+ "	INNER JOIN FOOD B ON A.F_ID = B.F_ID" 
					+ " WHERE SUBSTR(A.O_ID,1,6) BETWEEN ? AND ?"
												//�� 1? : ������ �� ù��°�� , 2? : ������ �� ��������
					+ " GROUP BY TO_CHAR(TO_DATE(SUBSTR(A.O_ID,1,6),'YYMMDD'),'IW')" 
					+ " ORDER BY TO_CHAR(TO_DATE(SUBSTR(A.O_ID,1,6),'YYMMDD'),'IW')";
		return (List<Map<String, Object>>) jdbc.selectList(sql,param);
	}
	
	
	// �Ϻ��� ���� ��ȸ
	public List<Map<String, Object>> selectDailyCostList(List<Object> param){
		String sql =  " SELECT B.I_ID AS I_ID" 
					+ " 	, B.I_NAME AS I_NAME" 
					+ "		, TO_CHAR(A.P_BDATE,'YYMMDD') AS BDATE" 
					+ "		, SUM(A.P_QTY*B.I_PRICE) AS COST" 
					+ " FROM PUR A" 
					+ "	INNER JOIN ING B" 
					+ "	ON A.I_ID = B.I_ID" 
					+ " WHERE TO_CHAR(A.P_BDATE,'YYMMDD') = ?"  //? : ���
					+ " GROUP BY TO_CHAR(A.P_BDATE,'YYMMDD'), B.I_ID, B.I_NAME" 
					+ " ORDER BY TO_CHAR(A.P_BDATE,'YYMMDD')";
		return (List<Map<String, Object>>) jdbc.selectList(sql,param);
	}
	
	// �ְ����� ���� ��ȸ
	public List<Map<String, Object>> selectWeeklyCostList(List<Object> param){
		String sql =  " SELECT "
					+ " 	MIN(TO_CHAR(A.P_BDATE,'YYMMDD')) AS MIN_DATE"
					+ " 	, MAX(TO_CHAR(A.P_BDATE,'YYMMDD')) AS MAX_DATE"
					+ "		, B.I_ID AS I_ID"
					+ " 	, B.I_NAME AS I_NAME"
					+ " 	, TO_CHAR(A.P_BDATE,'IW')||'��' AS WEEK"
					+ " 	, SUM(A.P_QTY*B.I_PRICE) AS COST"
					+ " FROM PUR A"
					+ " INNER JOIN ING B"
					+ " ON A.I_ID = B.I_ID"
					+ " WHERE TO_CHAR(A.P_BDATE,'YYMMDD') BETWEEN ? AND ?"
														 //�� 1? : ������ �� ù��°�� , 2? : ������ �� ��������
					+ " GROUP BY TO_CHAR(A.P_BDATE,'IW')"
					+ " 	, B.I_ID, B.I_NAME"	
					+ " ORDER BY TO_CHAR(A.P_BDATE,'IW')";
		return (List<Map<String, Object>>) jdbc.selectList(sql,param);
	}
	
	
	// �� �� ���� ���� ��ȸ
	public Map<String ,Object> selectEmpSal(){
		String sql = "SELECT SUM(E_SAL) AS E_SAL FROM EMP ";
		return (Map<String ,Object>) jdbc.selectOne(sql);
	}
	
	// �Ϻ��� ������ ��ȸ
	public List<Map<String, Object>> selectDailyGainList(List<Object> param){
		String sql =  " SELECT A.DATE AS DATE, (A.GAIN-B.GAIN) AS GAIN" 
					+ " FROM" 
					+ " 	(SELECT" 
					+ " 		SUBSTR(A.O_ID,1,6) AS DATE" 
					+ " 		, SUM(A.OF_QTY*B.F_PRICE) AS GAIN" 
					+ " 	FROM O_FOOD A" 
					+ " 	INNER JOIN FOOD B" 
					+ " 	ON A.F_ID = B.F_ID" 
					+ " 	GROUP BY SUBSTR(A.O_ID,1,6)) A" 
					+ " INNER JOIN" 
					+ " 	(SELECT" 
					+ " 		, TO_CHAR(A.P_BDATE,'YYMMDD') AS BDATE" 
					+ " 		, SUM(A.P_QTY*B.I_PRICE) AS GAIN" 
					+ " 	FROM PUR A" 
					+ " 		INNER JOIN ING B" 
					+ " 		ON A.I_ID = B.I_ID" 
					+ " 	GROUP BY TO_CHAR(A.P_BDATE,'YYMMDD')B" 
					+ " ON	A.DATE = B.BDATE" 
					+ " WHERE A.DATE = ?"
					+ " ORDER BY A.DATE";
		return (List<Map<String, Object>>) jdbc.selectList(sql,param);
	}
	
	// �ְ����� ������ ��ȸ
	public List<Map<String, Object>> selectWeeklyGainList(List<Object> param){
		String sql =  " SELECT A.WEEK AS WEEK"
					+ " 	, (A.GAIN-B.GAIN) AS GAIN"
					+ " 	, A.MIN_DATE"
					+ " 	, A.MAX_DATE"
					+ " FROM" 
					+ " 	(SELECT "
					+ " 		MIN(SUBSTR(A.O_ID,1,6)) AS MIN_DATE"
					+ " 		, MAX(SUBSTR(A.O_ID,1,6)) AS MAX_DATE"
					+ "			, TO_CHAR(TO_DATE(SUBSTR(A.O_ID,1,6),'YYMMDD'),'IW')||'��' AS WEEK"
					+ " 		, SUM(A.OF_QTY * B.F_PRICE) AS GAIN"
					+ " 	FROM O_FOOD A"
					+ " 	INNER JOIN FOOD B ON A.F_ID = B.F_ID"
					+ " 	WHERE SUBSTR(A.O_ID,1,6) BETWEEN ? AND ?"
					+ " 	GROUP BY TO_CHAR(TO_DATE(SUBSTR(A.O_ID,1,6),'YYMMDD'),'IW'))A"	
					+ " INNER JOIN" 
					+ " 	(SELECT "
					+ " 		MIN(TO_CHAR(A.P_BDATE,'YYMMDD')) AS MIN_DATE"
					+ " 		, MAX(TO_CHAR(A.P_BDATE,'YYMMDD')) AS MAX_DATE"
					+ "			, TO_CHAR(A.P_BDATE,'IW')||'��' AS WEEK"
					+ " 		, SUM(A.P_QTY*B.I_PRICE) AS GAIN"
					+ " 	FROM PUR A"
					+ " 	INNER JOIN ING B"
					+ " 	ON A.I_ID = B.I_ID"
					+ " 	WHERE TO_CHAR(A.P_BDATE,'YYMMDD') BETWEEN ? AND ?"
					+ " 	GROUP BY TO_CHAR(A.P_BDATE,'IW'))B"
					+ " ON A.WEEK = B.WEEK"
					+ " ORDER BY A.WEEK";
		return (List<Map<String, Object>>) jdbc.selectList(sql,param);
	}
	
	
	
	
	
}

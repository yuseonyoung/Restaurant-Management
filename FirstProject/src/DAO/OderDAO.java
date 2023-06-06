package DAO;

import java.util.List;
import java.util.Map;

import JDBCUtil.JdbcUtil;

public class OderDAO {
	private static OderDAO instance=null;
	
	private OderDAO() {}
	
	public static OderDAO getInstance() {
		if (instance == null) {
			instance = new OderDAO();
		}
		return instance;
	}
//----------------------------------------------------------¿©±â ±îÁö ½Ì±¼Åæ
	
	JdbcUtil jdbc = JdbcUtil.getInstance();
	
	public List<Map<String, Object>> selectList() {
		String sql = "SELECT I_ID, I_NAME, I_INVENTORY,  I_ORIGIN "
				 + " FROM INGREDIENT WHERE I_INVENTORY <= 20 ";
		return (List<Map<String, Object>>) jdbc.selectList(sql);
	}
}

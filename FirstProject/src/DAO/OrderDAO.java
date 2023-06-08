package DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import JDBCUtil.JdbcUtil;

public class OrderDAO {
	private static OrderDAO instance=null;
	
	private OrderDAO() {}
	
	public static OrderDAO getInstance() {
		if (instance == null) {
			instance = new OrderDAO();
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
	
	public Map<String ,Object> chooseOne(String id){
		String sql = "select I_ID, I_NAME, I_INVENTORY,  I_ORIGIN from ingredient where i_name = ?";
		List<Object> param = new ArrayList<Object>();
		param.add(id);
		return jdbc.selectOne(sql,param);
	}
}

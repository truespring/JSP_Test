package com.koreait.matzip.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JdbcTemplate {
	// insert, update, delete에 사용할 메소드
	public static int executeUpdate(String sql, JdbcUpdateInterface jdbc) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DbManager.getCon();
			ps = con.prepareStatement(sql);
			jdbc.update(ps); // 달라지는 부분 처리
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbManager.close(con, ps);
		}
		return result;
	}
	// select에 사용할 메소드
	public static void executeQuery(String sql, JdbcSelectInterface jdbc) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = DbManager.getCon();
			ps = con.prepareStatement(sql);
			jdbc.prepared(ps);
			
			rs = ps.executeQuery();
			jdbc.executeQuery(rs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbManager.close(con, ps, rs);
		} 
	}

}

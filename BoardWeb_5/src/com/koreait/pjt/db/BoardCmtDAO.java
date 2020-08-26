package com.koreait.pjt.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.koreait.pjt.board.BoardCmtDomain;
import com.koreait.pjt.vo.BoardCmtVO;

public class BoardCmtDAO {
	public static int insCmt;
	public static List<BoardCmtDomain> selCmtList(int i_board) {
		final List<BoardCmtDomain> list = new ArrayList();
		
		String sql = " SELECT B.nm, A.cmt, A.r_dt "
				+ " FROM t_board5_cmt A "
				+ " INNER JOIN t_user B "
				+ " ON A.i_user = B.i_user "
				+ " WHERE i_board = ? "
				+ " ORDER BY i_cmt DESC ";
		
		int result = JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {

			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				ps.setInt(1, i_board);
			}

			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				while(rs.next()) {
					String nm = rs.getNString("nm");
					String cmt = rs.getNString("cmt");
					int r_dt = rs.getInt("r_dt");
					
					BoardCmtDomain vo = new BoardCmtDomain();
					vo.setNm(nm);
					vo.setCmt(cmt);
					vo.setR_dt(r_dt);
					
					list.add(vo);
				}
				return 1;
			}
		});
		return list;
	}
	
	public static int insCmt(BoardCmtVO param) {
		String sql = " INSERT INTO t_board5_cmt "
				+ " (i_cmt, cmt, i_board, i_user) "
				+ " VALUES "
				+ " (seq_board5_cmt.nextval, ?, ?, ?) ";
		
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setNString(1, param.getCmt());
				ps.setInt(2, param.getI_board());
				ps.setInt(3, param.getI_user());
			}	
		});
	}
	
	public static int updCmt(BoardCmtVO param) {
		String sql = " UPDATE t_board5_cmt "
				+ " SET cmt = ? "
				+ " WHERE i_board = ? AND i_user = ? AND i_cmt ";
		
		JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setNString(1, param.getCmt());
				ps.setInt(2, param.getI_board());
				ps.setInt(3, param.getI_user());
				ps.setInt(4, param.getI_cmt());
			}
			
		});
		
		return 0;
	}
	public static int delCmt(BoardCmtVO param) {
		String sql = " DELECT FROM t_board5_cmt "
				+ " WHERE i_board = ? AND i_user = ? ";
		
		JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param.getI_board());
				ps.setInt(2, param.getI_user());
			}
		});	
		return 0;
	}
}

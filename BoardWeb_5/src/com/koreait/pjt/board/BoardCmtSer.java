package com.koreait.pjt.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.pjt.MyUtils;
import com.koreait.pjt.db.BoardCmtDAO;
import com.koreait.pjt.vo.BoardCmtVO;
import com.koreait.pjt.vo.UserVO;

@WebServlet("/board/cmt")
public class BoardCmtSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// 댓글 삭제
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	// 댓글 (등록 / 수정)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strI_cmt = request.getParameter("i_cmt");
		String strI_board = request.getParameter("i_board");
		String cmt = request.getParameter("cmt");
		UserVO loginUser = MyUtils.getLoginUser(request);
		
		int i_user = loginUser.getI_user();
		int i_board = MyUtils.parseStrToInt(strI_board);
		
		BoardCmtVO param = new BoardCmtVO();
		param.setCmt(cmt);
		param.setI_board(i_board);
		int result;
		
		switch(strI_cmt) {
		case "0": // 등록
			param.setI_user(i_user);
			result = BoardCmtDAO.insCmt(param);
			System.out.println("글 등록 : " + result);
			break;
		default: // 수정
			int i_cmt = MyUtils.parseStrToInt(strI_cmt);
			param.setI_cmt(i_cmt);
			result = BoardCmtDAO.updCmt(param);
			System.out.println("글 수정 : " + result);
			break;
		}
		response.sendRedirect("/board/detail?i_board=" + strI_board);
	}

}

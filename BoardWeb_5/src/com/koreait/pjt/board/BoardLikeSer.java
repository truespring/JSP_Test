package com.koreait.pjt.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.koreait.pjt.MyUtils;
import com.koreait.pjt.db.BoardDAO;

@WebServlet("/board/like")
public class BoardLikeSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// 리스트 가져오기
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int i_board = MyUtils.getIntParameter(request, "i_board");
		System.out.println("i_board : " + i_board);
		
		List<BoardDomain> likeList = BoardDAO.selBoardLikeList(i_board);
		
		Gson gson = new Gson();
		
		String json = gson.toJson(likeList);
		
//		System.out.println("json : " + json); // json 단위 테스트
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json"); // 보내는 것이 json라는 것을 알리는 작업
		PrintWriter out = response.getWriter();
		out.print(json); // HTML 문서로 넘어간다
	}

	// 좋아요 처리
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}

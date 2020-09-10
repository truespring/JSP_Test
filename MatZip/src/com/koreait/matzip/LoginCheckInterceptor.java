package com.koreait.matzip;

import javax.servlet.http.HttpServletRequest;

public class LoginCheckInterceptor {
	
	
	public static String routerChk(HttpServletRequest request) {
		// 로그인 되어 있으면 login, join 접속 X

		// 로그인에 따른 접속 가능 여부 판단
		// 로그인이 안 되어 있으면 접속할 수 있는 주소만, 나머지 전부 로그인이 되어 있어야함
		// 
		
		String[] chkUserUriArr = {"login", "join", "loginProc", "joinProc", "ajaxIdChk"};
 		
		boolean isLogout = SecurityUtils.isLogout(request);
		String[] targetUri = request.getRequestURI().split("/");
		
		if(targetUri.length < 3) {return null;}
		if(isLogout) { // 로그아웃 상태
			if(targetUri[1].equals(ViewRef.URI_USER)) {
				for(String uri : chkUserUriArr) {
					if(uri.equals(targetUri[2])) {
						return null; // 아무일 없음
					}
				}
			}
			return "/user/login";
		} else { // 로그인 상태
			if(targetUri[1].equals(ViewRef.URI_USER)) {
				for(String uri : chkUserUriArr) {
					if(uri.equals(targetUri[2])) {
						return "/restaurant/restMap";
					}
				}
			}
		}
		return null;
	}
}

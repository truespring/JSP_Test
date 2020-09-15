package com.koreait.matzip;

import javax.servlet.http.HttpServletRequest;

import com.koreait.matzip.restaurant.RestaurantController;
import com.koreait.matzip.user.UserController;

public class HandlerMapper {
	private UserController userCon;
	private RestaurantController restCon;
	
	public HandlerMapper() {
		userCon = new UserController();
		restCon = new RestaurantController();
	}
	
	public String nav(HttpServletRequest request) {
		String[] uriArr = request.getRequestURI().split("/");
		
		if(uriArr.length < 3) {
			return "405"; // Error
		}
		
		if("ajax".equals(uriArr[1])) {
			return "ajax";
		}
		
		switch(uriArr[1]) {
		case ViewRef.URI_USER: // user
			switch(uriArr[2]) {
			case "login":
				return userCon.login(request);
			case "loginProc":
				return userCon.loginProc(request);
			case "join":
				return userCon.join(request);
			case "joinProc":
				return userCon.joinProc(request);
			case "ajaxIdChk":
				return userCon.ajaxIdChk(request);
			case "logout":
				return userCon.logout(request);
			}
			
		case ViewRef.URI_RESTAURANT: // restaurant
			switch(uriArr[2]) {
			case "restMap":
				return restCon.restMap(request);
			case "restReg":
				return restCon.restReg(request);
			case "restRegProc":
				return restCon.restRegProc(request);
			case "ajaxGetList":
				return restCon.ajaxGetList(request);
			case "restDetail":
				return restCon.restDetail(request);
			case "addRecMenusProc":
				return restCon.addRecMenusProc(request);
			case "ajaxDelRecMenu" :
				return restCon.ajaxDelRecMenu(request);
			}
		}
		
		return "404"; // notFound
	}
}

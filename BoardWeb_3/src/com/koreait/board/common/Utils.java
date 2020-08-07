package com.koreait.board.common;

public class Utils {
	
	public static int parseStrToInt(String str) {
		return parseStrToInt(str, 0);
	}
	
	public static int parseStrToInt(String str, int num) {
		try {
			return Integer.parseInt(str);
		} catch(Exception e) {
			return num;
		}
	}
}

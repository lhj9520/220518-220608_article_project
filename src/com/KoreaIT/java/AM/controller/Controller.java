package com.KoreaIT.java.AM.controller;

import com.KoreaIT.java.AM.dto.Member;

public abstract class Controller {
	public static Member loginedmember;

	public abstract void doAction(String command, String actionMethodName);

	// 로그인 상태 확인
	public static boolean isLogined() {
		return loginedmember != null;
	}
}

package com.KoreaIT.java.AM.controller;

import com.KoreaIT.java.AM.dto.Member;

public abstract class Controller {
	public static Member loginedmember;

	public abstract void doAction(String command, String actionMethodName);

	// �α��� ���� Ȯ��
	public static boolean isLogined() {
		return loginedmember != null;
	}
}

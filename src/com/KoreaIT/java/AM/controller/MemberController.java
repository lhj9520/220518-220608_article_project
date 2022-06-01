package com.KoreaIT.java.AM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.AM.Util.Util;
import com.KoreaIT.java.AM.dto.Member;

public class MemberController extends Controller {
	private Scanner sc;
	private List<Member> member;
	private String command;
	private String actionMethodName;

	public MemberController(Scanner sc) {
		member = new ArrayList<>();
		this.sc = sc;
	}

	public void doAction(String command, String actionMethodName) {
		this.command = command;
		this.actionMethodName = actionMethodName;

		switch (actionMethodName) {
		case "join":
			doJoin();
			break;
		}
	}
	
	public void makeTestData() {

		System.out.println("테스트를 위한 member 데이터를 생성합니다.");

		member.add(new Member(1, Util.getNowDateTimeStr(), "1", "1", "1"));
		member.add(new Member(2, Util.getNowDateTimeStr(), "2", "2", "2"));
		member.add(new Member(3, Util.getNowDateTimeStr(), "3", "3", "3"));
	}
	
	private void doJoin() {
		int idx = member.size() + 1;

		String userid = null;

		while (true) {
			System.out.printf("아이디를 입력하세요 : ");
			userid = sc.nextLine();

			if (id_Duplicatecheck(userid)) { // 중복 없음
				break;
			}
			System.out.printf("%s는(은) 중복된 ID입니다.\n", userid);
		}

		String userpwd = null;
		String userpwdconfirm = null;

		while (true) {
			// PWD 입력 받기
			System.out.printf("비밀번호를 입력하세요 : ");
			userpwd = sc.nextLine();
			System.out.printf("비밀번호 확인: ");
			userpwdconfirm = sc.nextLine();

			if (!userpwd.equals(userpwdconfirm)) {
				System.out.println("비밀번호를 다시 입력하세요.");
				continue;
			}
			break;
		}

		// 이름 입력 받기
		System.out.printf("이름을 입력하세요 : ");
		String name = sc.nextLine();

		Member members = new Member(idx, Util.getNowDateTimeStr(), userid, userpwd, name);
		member.add(members);

		System.out.println(idx + "번 회원가입이 완료되었습니다.");

	}

	// 중복 기능 제거 -> 메서드 생성
	private boolean id_Duplicatecheck(String userid) {

		int index = findMemberIndex(userid);

		if (index == -1) { // 해당 id 없음
			return true;
		}
		return false;
	}

	private int findMemberIndex(String userid) {
		// 입력된 번호를 가지고 있는 회원 foreach문으로 비교탐색
		int i = 0;
		for (Member member : member) {
			if (member.mid.equals(userid)) {
				return i;
			}
			i++;
		}
		return -1;
	}
}

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
			if (isLogined()) {
				System.out.printf("%s님 로그인 상태입니다.\n", loginedmember.mid);
				break;
			}
			doJoin();
			break;
		case "login":
			if (isLogined()) {
				System.out.printf("%s님 로그인 상태입니다.\n", loginedmember.mid);
				break;
			}
			doLogin();
			break;
		case "whoami":
			if (!isLogined()) {
				System.out.println("로그인 상태가 아닙니다.");
				break;
			}
			checkWhoami();
			break;
		case "logout":
			if (!isLogined()) {
				System.out.println("로그인 상태가 아닙니다.");
				break;
			}
			doLogout();
			break;
		default:
			System.out.println("존재하지 않는 명령어입니다.");
			break;
		}
	}

	public void makeTestData() {

		System.out.println("테스트를 위한 member 데이터를 생성합니다.");
		member.add(new Member(1, Util.getNowDateTimeStr(), "admin", "admin", "관리자"));
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

	private void doLogin() {
		String inputid = null;
		String inputpw = null;
		Member membercheck = null;

		System.out.printf("아이디를 입력하세요 : ");
		inputid = sc.nextLine();

		System.out.printf("비밀번호를 입력하세요 : ");
		inputpw = sc.nextLine();

		membercheck = getMemberByLoginId(inputid);

		if (membercheck == null) {
			System.out.println("아이디가 존재하지 않습니다.\n");
			return;
		}

		if (inputid.equals(membercheck.mid) && inputpw.equals(membercheck.mpwd)) {
			System.out.printf("로그인 성공! %s님 환영합니다.\n", membercheck.mname);
			loginedmember = membercheck;
		} else {
			System.out.println("아이디 혹은 비밀번호가 일치하지 않습니다.");
		}

	}

	private void checkWhoami() {
		System.out.println("=========================");
		System.out.printf("id : %s\n", loginedmember.mid);
		System.out.printf("password : %s\n", loginedmember.mpwd);
		System.out.printf("이름 : %s\n", loginedmember.mname);
		System.out.println("=========================");
	}

	private void doLogout() {
		System.out.printf("%s님 로그아웃 되었습니다.\n", loginedmember.mid);
		loginedmember = null;
	}

	// 아이디 체크
	private Member getMemberByLoginId(String userid) {

		int index = findMemberIndex(userid);

		if (index == -1) { // 해당 id 없음
			return null;
		}
		return member.get(index);
	}

	// 아이디 중복 체크
	private boolean id_Duplicatecheck(String userid) {

		int index = findMemberIndex(userid);

		if (index == -1) { // 해당 id 없음
			return true;
		}
		return false;
	}

	// 아이디 인덱스 체크
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

package com.KoreaIT.java.AM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.AM.Util.Util;
import com.KoreaIT.java.AM.container.Container;
import com.KoreaIT.java.AM.dto.Member;

public class MemberController extends Controller {
	private Scanner sc;
	private List<Member> member;
	private String command;
	private String actionMethodName;

	public MemberController(Scanner sc) {
		member = Container.memberDao.members;
		this.sc = sc;
	}

	public void doAction(String command, String actionMethodName) {
		this.command = command;
		this.actionMethodName = actionMethodName;

		switch (actionMethodName) {
		case "join":
			doJoin();
			break;
		case "login":
			doLogin();
			break;
		case "whoami":
			checkWhoami();
			break;
		case "logout":
			doLogout();
			break;
		default:
			System.out.println("�������� �ʴ� ��ɾ��Դϴ�.");
			break;
		}
	}

	public void makeTestData() {

		System.out.println("�׽�Ʈ�� ���� member �����͸� �����մϴ�.");
		Container.memberDao
				.add(new Member(Container.memberDao.getNewId(), Util.getNowDateTimeStr(), "admin", "admin", "������"));
		Container.memberDao.add(new Member(Container.memberDao.getNewId(), Util.getNowDateTimeStr(), "1", "1", "1"));
		Container.memberDao.add(new Member(Container.memberDao.getNewId(), Util.getNowDateTimeStr(), "2", "2", "2"));
		Container.memberDao.add(new Member(Container.memberDao.getNewId(), Util.getNowDateTimeStr(), "3", "3", "3"));
	}

	private void doJoin() {
		int id = Container.memberDao.getNewId();
		String userid = null;

		while (true) {
			System.out.printf("���̵� �Է��ϼ��� : ");
			userid = sc.nextLine();

			if (id_Duplicatecheck(userid)) { // �ߺ� ����
				break;
			}
			System.out.printf("%s��(��) �ߺ��� ID�Դϴ�.\n", userid);
		}

		String userpwd = null;
		String userpwdconfirm = null;

		while (true) {
			// PWD �Է� �ޱ�
			System.out.printf("��й�ȣ�� �Է��ϼ��� : ");
			userpwd = sc.nextLine();
			System.out.printf("��й�ȣ Ȯ��: ");
			userpwdconfirm = sc.nextLine();

			if (!userpwd.equals(userpwdconfirm)) {
				System.out.println("��й�ȣ�� �ٽ� �Է��ϼ���.");
				continue;
			}
			break;
		}

		// �̸� �Է� �ޱ�
		System.out.printf("�̸��� �Է��ϼ��� : ");
		String name = sc.nextLine();

		Member members = new Member(id, Util.getNowDateTimeStr(), userid, userpwd, name);
		Container.memberDao.add(members);

		System.out.println(id + "�� ȸ�������� �Ϸ�Ǿ����ϴ�.");
	}

	private void doLogin() {
		String inputid = null;
		String inputpw = null;
		Member membercheck = null;

		System.out.printf("���̵� �Է��ϼ��� : ");
		inputid = sc.nextLine();

		System.out.printf("��й�ȣ�� �Է��ϼ��� : ");
		inputpw = sc.nextLine();

		membercheck = getMemberByLoginId(inputid);

		if (membercheck == null) {
			System.out.println("���̵� �������� �ʽ��ϴ�.");
			return;
		}

		if (inputid.equals(membercheck.mid) && inputpw.equals(membercheck.mpwd)) {
			System.out.printf("�α��� ����! %s�� ȯ���մϴ�.\n", membercheck.mname);
			loginedmember = membercheck;
		} else {
			System.out.println("���̵� Ȥ�� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		}

	}

	private void checkWhoami() {
		System.out.println("=========================");
		System.out.printf("id : %s\n", loginedmember.mid);
		System.out.printf("password : %s\n", loginedmember.mpwd);
		System.out.printf("�̸� : %s\n", loginedmember.mname);
		System.out.println("=========================");
	}

	private void doLogout() {
		System.out.printf("%s�� �α׾ƿ� �Ǿ����ϴ�.\n", loginedmember.mid);
		loginedmember = null;
	}

	// ���̵� üũ
	private Member getMemberByLoginId(String userid) {

		int index = findMemberIndex(userid);

		if (index == -1) { // �ش� id ����
			return null;
		}
		return member.get(index);
	}

	// ���̵� �ߺ� üũ
	private boolean id_Duplicatecheck(String userid) {

		int index = findMemberIndex(userid);

		if (index == -1) { // �ش� id ����
			return true;
		}
		return false;
	}

	// ���̵� �ε��� üũ
	private int findMemberIndex(String userid) {
		// �Էµ� ��ȣ�� ������ �ִ� ȸ�� foreach������ ��Ž��
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

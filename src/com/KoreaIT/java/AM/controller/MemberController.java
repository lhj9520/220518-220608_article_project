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

		System.out.println("�׽�Ʈ�� ���� member �����͸� �����մϴ�.");

		member.add(new Member(1, Util.getNowDateTimeStr(), "1", "1", "1"));
		member.add(new Member(2, Util.getNowDateTimeStr(), "2", "2", "2"));
		member.add(new Member(3, Util.getNowDateTimeStr(), "3", "3", "3"));
	}
	
	private void doJoin() {
		int idx = member.size() + 1;

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

		Member members = new Member(idx, Util.getNowDateTimeStr(), userid, userpwd, name);
		member.add(members);

		System.out.println(idx + "�� ȸ�������� �Ϸ�Ǿ����ϴ�.");

	}

	// �ߺ� ��� ���� -> �޼��� ����
	private boolean id_Duplicatecheck(String userid) {

		int index = findMemberIndex(userid);

		if (index == -1) { // �ش� id ����
			return true;
		}
		return false;
	}

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

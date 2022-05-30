package com.KoreaIT.java.AM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.AM.Util.Util;
import com.KoreaIT.java.AM.controller.ArticleController;
import com.KoreaIT.java.AM.controller.MemberController;
import com.KoreaIT.java.AM.dto.Article;
import com.KoreaIT.java.AM.dto.Member;

public class App {
	private List<Article> article;
	private List<Member> member;

	App() {
		article = new ArrayList<>();
		member = new ArrayList<>();
	}

	public void start() {

//		System.out.println("========���α׷� ����========");
//		System.out.println("member join ȸ������");
//		System.out.println("article write �Խñ� �ۼ�");
//		System.out.println("article list <id> �Խñ� ��� ��ȸ");
//		System.out.println("article detail <id> �Խñ� �� ����");
//		System.out.println("article modify <id> �Խñ� ����");
//		System.out.println("article delete <id> �Խñ� ����");
//		System.out.println("=========================");

		makeTestData();

		Scanner sc = new Scanner(System.in);

		MemberController memberController = new MemberController(sc, member);
		ArticleController articleController = new ArticleController(sc, article);

		while (true) {
			System.out.printf("��ɾ� > ");
			String command = sc.nextLine().trim(); // trim() �� �� ���� ����

			if (command.length() == 0)
				continue; // �Էµ� ���ڰ� ���� ��� �ٽ� ��ɾ� �Է� �ޱ�
			if (command.equals("system exit"))
				break; // system exit �Էµ� ��� ����

			// ȸ������
			if (command.equals("member join")) {
				memberController.doJoin();
			}
			// �Խñ� �ۼ� - create
			else if (command.equals("article write")) {
				articleController.doWrite();
			}
			// �Խñ� ��� �����ֱ� - �˻� ��� �߰�
			else if (command.startsWith("article list")) {
				articleController.doList(command);

			}
			// �ش� �Խñ� �ڼ��� ���� - read
			else if (command.startsWith("article detail ")) {
				articleController.doDetail(command);
			}
			// �ش� �Խñ� ���� - update
			else if (command.startsWith("article modify ")) { // contains
				articleController.doUpdate(command);
			}
			// �ش� �Խñ� ���� - delete
			else if (command.startsWith("article delete ")) { // contains
				articleController.doDelete(command);
			}
			// �̿� ��ɾ� ����ó��
			else {
				System.out.println("�������� �ʴ� ��ɾ��Դϴ�.");
			}
		}
		sc.close();

		System.out.println("========���α׷� ����========");
	}

	void makeTestData() {

		System.out.println("�׽�Ʈ�� ���� �����͸� �����մϴ�.");

		article.add(new Article(1, "111", "111", Util.getNowDateTimeStr(), 1));
		article.add(new Article(2, "222", "222", Util.getNowDateTimeStr(), 2));
		article.add(new Article(3, "333", "333", Util.getNowDateTimeStr(), 3));
	}
}
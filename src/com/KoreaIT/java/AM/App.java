package com.KoreaIT.java.AM;

import java.util.Scanner;

import com.KoreaIT.java.AM.controller.ArticleController;
import com.KoreaIT.java.AM.controller.Controller;
import com.KoreaIT.java.AM.controller.MemberController;

public class App {
	App() {

	}

	public void start() {

		Scanner sc = new Scanner(System.in);

		MemberController memberController = new MemberController(sc);
		ArticleController articleController = new ArticleController(sc);

		articleController.makeTestData();
		memberController.makeTestData();

		while (true) {
			System.out.printf("��ɾ� > ");
			String command = sc.nextLine().trim();

			if (command.length() == 0) {
				System.out.println("��ɾ �Է����ּ���");
				continue;
			}

			if (command.equals("system exit")) {
				break;
			}

			String[] commandBits = command.split(" "); // article list

			if (commandBits.length == 1) {
				System.out.println("�������� �ʴ� ��ɾ��Դϴ�.");
				continue;
			}

			String controllerName = commandBits[0]; // article
			String actionMethodName = commandBits[1]; // list

			Controller controller = null;

			if (controllerName.equals("article")) {
				controller = articleController;
			} else if (controllerName.equals("member")) {
				controller = memberController;
			} else {
				System.out.println("�������� �ʴ� ��ɾ��Դϴ�.");
				continue;
			}
			// �α��� ���� Ȯ��
			String actionName = controllerName + "/" + actionMethodName;

			switch (actionName) {
			case "article/write":
			case "article/modify":
			case "article/delete":
			case "member/whoami":
				if (!Controller.isLogined()) {
					System.out.println("�α��� �� �̿����ּ���.");
					continue;
				}
				break;
			}

			switch (actionName) {
			case "member/logout":
				if (!Controller.isLogined()) {
					System.out.println("�α��� ���°� �ƴմϴ�.");
					continue;
				}
				break;
			}

			switch (actionName) {
			case "member/join":
			case "member/login":
				if (Controller.isLogined()) {
					System.out.printf("%s�� �α��� �����Դϴ�.\n", Controller.loginedmember.mid);
					continue;
				}
				break;
			}

			controller.doAction(command, actionMethodName);
		}
		sc.close();

		System.out.println("========���α׷� ����========");
	}
}
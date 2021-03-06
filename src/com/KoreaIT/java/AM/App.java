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
			System.out.printf("명령어 > ");
			String command = sc.nextLine().trim();

			if (command.length() == 0) {
				System.out.println("명령어를 입력해주세요");
				continue;
			}

			if (command.equals("system exit")) {
				break;
			}

			String[] commandBits = command.split(" "); // article list

			if (commandBits.length == 1) {
				System.out.println("존재하지 않는 명령어입니다.");
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
				System.out.println("존재하지 않는 명령어입니다.");
				continue;
			}
			// 로그인 상태 확인
			String actionName = controllerName + "/" + actionMethodName;

			switch (actionName) {
			case "article/write":
			case "article/modify":
			case "article/delete":
			case "member/whoami":
				if (!Controller.isLogined()) {
					System.out.println("로그인 후 이용해주세요.");
					continue;
				}
				break;
			}

			switch (actionName) {
			case "member/logout":
				if (!Controller.isLogined()) {
					System.out.println("로그인 상태가 아닙니다.");
					continue;
				}
				break;
			}

			switch (actionName) {
			case "member/join":
			case "member/login":
				if (Controller.isLogined()) {
					System.out.printf("%s님 로그인 상태입니다.\n", Controller.loginedmember.mid);
					continue;
				}
				break;
			}

			controller.doAction(command, actionMethodName);
		}
		sc.close();

		System.out.println("========프로그램 종료========");
	}
}
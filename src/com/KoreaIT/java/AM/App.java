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

//		System.out.println("========프로그램 시작========");
//		System.out.println("member join 회원가입");
//		System.out.println("article write 게시글 작성");
//		System.out.println("article list <id> 게시글 목록 조회");
//		System.out.println("article detail <id> 게시글 상세 보기");
//		System.out.println("article modify <id> 게시글 수정");
//		System.out.println("article delete <id> 게시글 삭제");
//		System.out.println("=========================");

		makeTestData();

		Scanner sc = new Scanner(System.in);

		MemberController memberController = new MemberController(sc, member);
		ArticleController articleController = new ArticleController(sc, article);

		while (true) {
			System.out.printf("명령어 > ");
			String command = sc.nextLine().trim(); // trim() 양 끝 공백 제거

			if (command.length() == 0)
				continue; // 입력된 문자가 없는 경우 다시 명령어 입력 받기
			if (command.equals("system exit"))
				break; // system exit 입력된 경우 종료

			// 회원가입
			if (command.equals("member join")) {
				memberController.doJoin();
			}
			// 게시글 작성 - create
			else if (command.equals("article write")) {
				articleController.doWrite();
			}
			// 게시글 목록 보여주기 - 검색 기능 추가
			else if (command.startsWith("article list")) {
				articleController.doList(command);

			}
			// 해당 게시글 자세히 보기 - read
			else if (command.startsWith("article detail ")) {
				articleController.doDetail(command);
			}
			// 해당 게시글 수정 - update
			else if (command.startsWith("article modify ")) { // contains
				articleController.doUpdate(command);
			}
			// 해당 게시글 삭제 - delete
			else if (command.startsWith("article delete ")) { // contains
				articleController.doDelete(command);
			}
			// 이외 명령어 예외처리
			else {
				System.out.println("존재하지 않는 명령어입니다.");
			}
		}
		sc.close();

		System.out.println("========프로그램 종료========");
	}

	void makeTestData() {

		System.out.println("테스트를 위한 데이터를 생성합니다.");

		article.add(new Article(1, "111", "111", Util.getNowDateTimeStr(), 1));
		article.add(new Article(2, "222", "222", Util.getNowDateTimeStr(), 2));
		article.add(new Article(3, "333", "333", Util.getNowDateTimeStr(), 3));
	}
}
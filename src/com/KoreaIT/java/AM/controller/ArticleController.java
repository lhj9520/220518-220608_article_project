package com.KoreaIT.java.AM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.AM.Util.Util;
import com.KoreaIT.java.AM.dto.Article;

public class ArticleController {
	
	private Scanner sc;
	private List<Article> article;
	int lastid = 0;

	public ArticleController(Scanner sc, List<Article> articles) {
		this.sc = sc;
		this.article = articles;
		this.lastid = article.size();
	}
	
	public void doWrite() {
		// int id = article.size() + 1; //리스트 객체의 크기를 가져오면 됨 근데 이렇게 하면 delete -> write 시
		// 게시글 index 꼬임 다시 lastid 변수 추가함
		int id = lastid + 1;
		// 제목 입력 받기
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		// 내용 입력 받기
		System.out.printf("내용 : ");
		String content = sc.nextLine();

		// 게시판 객체 생성, id,제목,내용,날짜시간 넘겨주기
		Article articlec = new Article(id, title, content, Util.getNowDateTimeStr());
		article.add(articlec);
		lastid = id;

		System.out.println(id + "번글이 생성되었습니다.");
	}

	public void doList(String command) {
		if (article.size() == 0) {
			System.out.println("게시글이 존재하지 않습니다."); // article 객체 리스트의 크기가 0
			return;
		}

		List<Article> forPrintArticles = article; // list 변수 만들어서 article리스트 복사

		String searchkeyword = command.substring("article list".length()).trim();

		if (searchkeyword.length() > 0) { // 키워드가 있는 경우
			forPrintArticles = new ArrayList<>(); // 새로운 리스트 생성
			for (Article articlec : article) {
				if (articlec.title.contains(searchkeyword)) { // 키워드가 제목에 포함되면
					forPrintArticles.add(articlec); // 해당 데이터 추가
				}
			}
			if (forPrintArticles.size() == 0) {
				System.out.printf("%s와 일치하는 검색결과가 없습니다.\n", searchkeyword);
				return;
			}
		}
		// 키워드가 존재하는 경우 새로운 리스트 생성하여 리스트 출력
		// 키워드가 존재하지 않는 경우 복사된 article 리스트 출력
		System.out.println("=========================");
		System.out.printf(" 번호 |  제목  |  조회\n", article.size());

		for (int i = forPrintArticles.size(); i > 0; i--) {
			Article articlec = forPrintArticles.get(i - 1);
			System.out.printf(" %2d  | %5s | %2d\n", articlec.id, articlec.title, articlec.hit);
		}
		System.out.println("=========================");
	}

	public void doDetail(String command) {
		String[] cmd = command.split(" ");
		int id = Integer.parseInt(cmd[2]);
		Article foundarticle = findarticle(id);

		if (foundarticle == null) {
			System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
			return;
		}

		foundarticle.increaseHit(); // 조회수

		// 저장된 객체의 정보 출력
		System.out.println("=========================");
		System.out.printf("번호 : %d\n", foundarticle.id);
		System.out.printf("날짜 : %s\n", foundarticle.datetime);
		System.out.printf("제목 : %s\n", foundarticle.title);
		System.out.printf("내용 : %s\n", foundarticle.content);
		System.out.printf("조회 : %s\n", foundarticle.hit);
		System.out.println("=========================");
	}

	public void doUpdate(String command) {
		String[] cmd = command.split(" ");
		int id = Integer.parseInt(cmd[2]);
		Article foundarticle = findarticle(id);

		if (foundarticle == null) {
			System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
			return;
		}

		// 제목 다시 입력 받기
		System.out.printf("제목 : ");
		foundarticle.title = sc.nextLine();
		// 내용 다시 입력 받기
		System.out.printf("내용 : ");
		foundarticle.content = sc.nextLine();

		System.out.printf("%d번 게시글이 수정되었습니다.\n", id);
	}

	public void doDelete(String command) {
		String[] cmd = command.split(" ");
		int id = Integer.parseInt(cmd[2]);
		int foundindex = findArticleIndex(id);

		if (foundindex == -1) {
			System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
			return;
		}

		// 저장된 객체의 인덱스 번호를 이용하여 array list에서 삭제
		article.remove(foundindex);
		System.out.printf("%d번 게시글이 삭제되었습니다.\n", id);
	}
	
	// 중복 기능 제거 -> 메서드 생성
	private Article findarticle(int id) {
		// for 탐색 중복 제거
		int index = findArticleIndex(id);

		if (index != -1) {
			return article.get(index);
		}
		return null;
	}

	private int findArticleIndex(int id) {
		// 입력된 번호를 가지고 있는 게시글 foreach문으로 비교탐색
		int i = 0;
		for (Article article : article) {
			if (id == article.id) {
				return i;
			}
			i++;
		}
		return -1;
	}
}

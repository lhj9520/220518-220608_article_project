package com.KoreaIT.java.AM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.AM.Util.Util;
import com.KoreaIT.java.AM.dto.Article;

public class App {
	private List<Article> article;
	static int lastid = 0;

	App() {
		article = new ArrayList<>();
	}

	public void start() {

		System.out.println("========프로그램 시작========");

		makeTestData();

		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.printf("명령어 > ");
			String command = sc.nextLine().trim(); // trim() 양 끝 공백 제거

			if (command.length() == 0)
				continue; // 입력된 문자가 없는 경우 다시 명령어 입력 받기
			if (command.equals("system exit"))
				break; // system exit 입력된 경우 종료

			// 게시글 작성 - create
			if (command.equals("article write")) {
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
			// 게시글 목록 보여주기 - 검색 기능 추가
			else if (command.startsWith("article list")) {

				if (article.size() == 0) {
					System.out.println("게시글이 존재하지 않습니다."); // article 객체 리스트의 크기가 0
					continue;
				}
				
				List<Article> forPrintArticles = article; // list 변수 만들어서 article리스트 복사

				String searchkeyword = command.substring("article list".length()).trim();
				
				if(searchkeyword.length() > 0) { //키워드가 있는 경우
					forPrintArticles = new ArrayList<>(); //새로운 리스트 생성
					for(Article articlec : article) {
						if(articlec.title.contains(searchkeyword)) { //키워드가 제목에 포함되면
							forPrintArticles.add(articlec); //해당 데이터 추가
						}
					}					
					if (forPrintArticles.size() == 0) {
						System.out.printf("%s와 일치하는 검색결과가 없습니다.\n",searchkeyword);
						continue;
					}
				}
				//키워드가 존재하는 경우 새로운 리스트 생성하여 리스트 출력
				//키워드가 존재하지 않는 경우 복사된 article 리스트 출력
				System.out.println("=========================");
				System.out.printf(" 번호 |  제목  |  조회\n", article.size());
				
				for (int i = forPrintArticles.size(); i > 0; i--) {
					Article articlec = forPrintArticles.get(i - 1);
					System.out.printf(" %2d  | %5s | %2d\n", articlec.id, articlec.title, articlec.hit);
				}
				System.out.println("=========================");
			}
			// 해당 게시글 자세히 보기 - read
			else if (command.startsWith("article detail ")) { // contains랑 뭐가 다른거지

				String[] cmd = command.split(" ");
				int id = Integer.parseInt(cmd[2]);
				Article foundarticle = findarticle(id);

				if (foundarticle == null) {
					System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
					continue;
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
			// 해당 게시글 수정 - update
			else if (command.startsWith("article modify ")) { // contains

				String[] cmd = command.split(" ");
				int id = Integer.parseInt(cmd[2]);
				Article foundarticle = findarticle(id);

				if (foundarticle == null) {
					System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
					continue;
				}

				// 제목 다시 입력 받기
				System.out.printf("제목 : ");
				foundarticle.title = sc.nextLine();
				// 내용 다시 입력 받기
				System.out.printf("내용 : ");
				foundarticle.content = sc.nextLine();

				System.out.printf("%d번 게시글이 수정되었습니다.\n", id);
			}
			// 해당 게시글 삭제 - delete
			else if (command.startsWith("article delete ")) { // contains

				String[] cmd = command.split(" ");
				int id = Integer.parseInt(cmd[2]);
				int foundindex = findarticleindex(id);

				if (foundindex == -1) {
					System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
					continue;
				}

				// 저장된 객체의 인덱스 번호를 이용하여 array list에서 삭제
				article.remove(foundindex);
				System.out.printf("%d번 게시글이 삭제되었습니다.\n", id);
			}
			else System.out.println("존재하지 않는 명령어입니다.");
		}
		sc.close();

		System.out.println("========프로그램 종료========");
	}

	void makeTestData() {

		System.out.println("테스트를 위한 데이터를 생성합니다.");

		article.add(new Article(1, "111", "111", Util.getNowDateTimeStr(), 1));
		article.add(new Article(2, "222", "222", Util.getNowDateTimeStr(), 2));
		article.add(new Article(3, "333", "333", Util.getNowDateTimeStr(), 3));
		lastid = article.size();
	}

	// 중복 기능 제거 -> 메서드 생성
	Article findarticle(int id) {
		//for 탐색 중복 제거
		int index = findarticleindex(id);
		
		if (index != -1) {
			return article.get(index);
		}
		return null;
	}

	int findarticleindex(int id) {
		// 입력된 번호를 가지고 있는 게시글 foreach문으로 비교탐색
		int i=0;
		for (Article article : article) {
			if (id == article.id) {
				return i;
			}
			i++;
		}
		return -1;
	}

}

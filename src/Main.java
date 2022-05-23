import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		int lastArticleid = 0;
		List<Article> article = new ArrayList<>();
		
		System.out.println("==프로그램 시작==");
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.printf("명령어 > ");
			String command = sc.nextLine().trim(); //trim() 양 끝 공백 제거
			
			if(command.length() == 0) continue; //입력된 문자가 없는 경우 다시 명령어 입력 받기
			if(command.equals("system exit")) break; //system exit 입력된 경우 종료
			
			if(command.equals("article write")) {
				int id = lastArticleid + 1;
				//제목 입력 받기
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				//내용 입력 받기
				System.out.printf("내용 : ");
				String content = sc.nextLine();
				//게시판 객체 생성, id,제목,내용 넘겨주기
				Article articlec = new Article(id, title, content);
				article.add(articlec);
				
				System.out.println(id+"번글이 생성되었습니다");
				lastArticleid = id;
			}
			else if(command.equals("article list")) {

				if(article.size() == 0) {
					System.out.println("게시글이 없습니다"); //article 객체 리스트의 크기가 0
					continue;
				}
				System.out.printf(" 번호 |  제목\n",article.size());
				for(int i=article.size();i>0;i--) {
					Article articlec = article.get(i-1);
					System.out.printf("  %d  |  제목:%s\n", articlec.id,articlec.title);
				}
			}
			else System.out.println("존재하지 않는 명령어입니다");
		}
		sc.close();
		
		System.out.println("==프로그램 종료==");
	}
}

class Article{
	int id;
	String title;
	String content;
	
	public Article(int id, String title, String body){
		this.id = id;
		this.title = title;
		this.content = body;
	}
}

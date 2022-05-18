import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		int idx = 0;
		List<Article> article = new ArrayList<>();
		
		System.out.println("==프로그램 시작==");
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.printf("명령어 > ");
			String command = sc.nextLine().trim(); //trim() 양 끝 공백 제거
			
			if(command.length() == 0) continue; //입력된 문자가 없는 경우 다시 명령어 입력 받기
			if(command.equals("system exit")) break; //system exit 입력된 경우 종료
			
			if(command.equals("article write")) {
				int id = idx + 1;
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
				idx = id;
			}
			else if(command.equals("article list")) {

				if(article.size() == 0) System.out.println("게시글이 없습니다"); //article 객체 리스트의 크기가 0
				else {
					System.out.printf("게시글이 %d개 존재합니다\n",idx);
					for(int i=0;i<article.size();i++) {
						System.out.printf("%d 제목:%s 내용:%s\n", article.get(i).id,article.get(i).title,article.get(i).content);
					}
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

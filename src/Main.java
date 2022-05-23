import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
			
			//게시글 작성
			if(command.equals("article write")) {
				int id = lastArticleid + 1;
				//제목 입력 받기
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				//내용 입력 받기
				System.out.printf("내용 : ");
				String content = sc.nextLine();
				//현재 날짜
				LocalDate now = LocalDate.now();
				DateTimeFormatter formatter_date = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				String formatedDate = now.format(formatter_date);
				//현재 시간
				LocalTime time = LocalTime.now();
				DateTimeFormatter formatter_time = DateTimeFormatter.ofPattern("HH:mm:ss");
				String formatedTime = time.format(formatter_time);
				//날짜+시간 하나의 문자열로
				String datetime = formatedDate +" "+ formatedTime; //StringBuffer로 합치는게 메모리 덜 씀

				//게시판 객체 생성, id,제목,내용,날짜시간 넘겨주기
				Article articlec = new Article(id, title, content, datetime);
				article.add(articlec);
				
				System.out.println(id+"번글이 생성되었습니다");
				lastArticleid = id;
			}
			//게시글 목록 보여주기
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
			//해당 게시글 자세히 보기
			else if(command.startsWith("article detail ")) { //contains랑 뭐가 다른거지

				String[] cmd = command.split(" ");
				int num = Integer.parseInt(cmd[2]);
				Article articlec = null;
				
				//입력된 번호를 가지고 있는 게시글 for문으로 비교탐색
				for(int i=0;i<article.size();i++) {
					if(num == article.get(i).id) { // 해당 번호의 게시글 객체가 있다면 저장
						articlec = article.get(i);
						break;
					}
				}
				
				if (articlec==null) {
					System.out.printf("%d번 게시글은 존재하지 않습니다\n",num);
					continue;
				}
				//저장된 객체의 정보 출력
				System.out.printf("번호 : %d\n",articlec.id);
				System.out.printf("날짜 : %s\n",articlec.datetime);
				System.out.printf("제목 : %s\n",articlec.title);
				System.out.printf("내용 : %s\n",articlec.content);
			}
			//해당 게시글 삭제
			else if(command.startsWith("article delete ")) { //contains

				String[] cmd = command.split(" ");
				int num = Integer.parseInt(cmd[2]);
				Article articlec = null;
				
				//입력된 번호를 가지고 있는 게시글 for문으로 비교탐색
				for(int i=0;i<article.size();i++) {
					if(num == article.get(i).id) { // 해당 번호의 게시글 객체가 있다면 저장
						articlec = article.get(i);
						break;
					}
				}
				
				if (articlec==null) {
					System.out.printf("%d번 게시글은 존재하지 않습니다\n",num);
					continue;
				}
				//저장된 객체의 인덱스 번호를 이용하여 array list에서 삭제
				article.remove(article.indexOf(articlec));
				System.out.printf("%d번 게시글이 삭제되었습니다\n",num);
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
	String datetime;
	
	public Article(int id, String title, String body, String datetime){
		this.id = id;
		this.title = title;
		this.content = body;
		this.datetime = datetime;
	}
}

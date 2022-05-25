import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		int lastArticleid = 0;
		List<Article> article = new ArrayList<>();
		
		System.out.println("========프로그램 시작========");
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.printf("명령어 > ");
			String command = sc.nextLine().trim(); //trim() 양 끝 공백 제거
			
			if(command.length() == 0) continue; //입력된 문자가 없는 경우 다시 명령어 입력 받기
			if(command.equals("system exit")) break; //system exit 입력된 경우 종료
			
			//게시글 작성 - create
			if(command.equals("article write")) {
				int id = lastArticleid + 1;
				//제목 입력 받기
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				//내용 입력 받기
				System.out.printf("내용 : ");
				String content = sc.nextLine();
				//현재 시간 받아오기
				String datetime = Util_DateTime.getNowDateTimeStr(); //다른 클래스로 만들어서 사용(자주 쓰는 애들 메서드로 만들기)

				//게시판 객체 생성, id,제목,내용,날짜시간 넘겨주기
				Article articlec = new Article(id, title, content, datetime);
				article.add(articlec);
				
				System.out.println(id+"번글이 생성되었습니다.");
				lastArticleid = id;
			}
			//게시글 목록 보여주기
			else if(command.equals("article list")) {

				if(article.size() == 0) {
					System.out.println("게시글이 존재하지 않습니다."); //article 객체 리스트의 크기가 0
					continue;
				}
				
				System.out.println("=========================");
				System.out.printf(" 번호 |  제목\n",article.size());
				for(int i=article.size();i>0;i--) {
					Article articlec = article.get(i-1);
					System.out.printf("  %d  |  제목:%s\n", articlec.id,articlec.title);
				}
				System.out.println("=========================");
			}
			//해당 게시글 자세히 보기 - read
			else if(command.startsWith("article detail ")) { //contains랑 뭐가 다른거지

				String[] cmd = command.split(" ");
				int id = Integer.parseInt(cmd[2]);
				Article foundarticle = null;
				
				//입력된 번호를 가지고 있는 게시글 for문으로 비교탐색
				for(int i=0;i<article.size();i++) {
					if(id == article.get(i).id) { // 해당 번호의 게시글 객체가 있다면 저장
						foundarticle = article.get(i);
						break;
					}
				}
				
				if (foundarticle==null) {
					System.out.printf("%d번 게시글은 존재하지 않습니다.\n",id);
					continue;
				}
				
				//저장된 객체의 정보 출력
				System.out.println("=========================");
				System.out.printf("번호 : %d\n",foundarticle.id);
				System.out.printf("날짜 : %s\n",foundarticle.datetime);
				System.out.printf("제목 : %s\n",foundarticle.title);
				System.out.printf("내용 : %s\n",foundarticle.content);
				System.out.println("=========================");
			}
			//해당 게시글 삭제 - delete
			else if(command.startsWith("article delete ")) { //contains

				String[] cmd = command.split(" ");
				int id = Integer.parseInt(cmd[2]);
				int foundindex = -1;
				
				//입력된 번호를 가지고 있는 게시글 for문으로 비교탐색
				for(int i=0;i<article.size();i++) {
					if(id == article.get(i).id) { // 해당 번호의 게시글의 id가 있다면 저장
						foundindex = i;
						break;
					}
				}
				
				if (foundindex == -1) {
					System.out.printf("%d번 게시글은 존재하지 않습니다.\n",id);
					continue;
				}
				
				//저장된 객체의 인덱스 번호를 이용하여 array list에서 삭제
				article.remove(foundindex);
				System.out.printf("%d번 게시글이 삭제되었습니다.\n",id);
			}
			//해당 게시글 수정 - update
			/*else if(command.startsWith("article update ")) { //contains

				String[] cmd = command.split(" ");
				int num = Integer.parseInt(cmd[2]);
				Article foundarticle = null;
				
				//입력된 번호를 가지고 있는 게시글 for문으로 비교탐색
				for(int i=0;i<article.size();i++) {
					if(num == article.get(i).id) { // 해당 번호의 게시글 객체가 있다면 저장
						foundarticle = article.get(i);
						break;
					}
				}
				
				if (foundarticle==null) {
					System.out.printf("%d번 게시글은 존재하지 않습니다.\n",num);
					continue;
				}

				//제목 다시 입력 받기
				System.out.printf("제목 : ");
				foundarticle.title = sc.nextLine();
				//내용 다시 입력 받기
				System.out.printf("내용 : ");
				foundarticle.content = sc.nextLine();
				
				System.out.printf("%d번 게시글이 수정되었습니다.\n",num);
			}*/
			else System.out.println("존재하지 않는 명령어입니다.");
		}
		sc.close();
		
		System.out.println("========프로그램 종료========");
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

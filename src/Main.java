import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	//인스턴스 변수 선언 -> 클래스 객체가 생성되면 인스턴스에 접근 가능하다.
	private static List<Article> article; //private 외부에서 접근 불가능
	//생성자
	static{
		article = new ArrayList<>();
	}
	
	//클래스 변수 선언 -> 클래스 객체가 생성되지 않아도 인스턴스에 접근 가능하다.
	//static int lastArticleid = 0;
	//static List<Article> article = new ArrayList<>();
	
	public static void main(String[] args) {
		//지역 변수 선언 -> 해당 메소드 내에서만 접근 가능하다.
		//int lastArticleid = 0;
		//List<Article> article = new ArrayList<>();
		
		//Main ex1 = new Main(); //-> 인스턴스 변수에 접근
		
		System.out.println("========프로그램 시작========");
		
		makeTestData();
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.printf("명령어 > ");
			String command = sc.nextLine().trim(); //trim() 양 끝 공백 제거
			
			if(command.length() == 0) continue; //입력된 문자가 없는 경우 다시 명령어 입력 받기
			if(command.equals("system exit")) break; //system exit 입력된 경우 종료
			
			//게시글 작성 - create
			if(command.equals("article write")) {
				int id = article.size() + 1; //리스트 객체의 크기를 가져오면 됨
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
			}
			//게시글 목록 보여주기
			else if(command.equals("article list")) {

				if(article.size() == 0) {
					System.out.println("게시글이 존재하지 않습니다."); //article 객체 리스트의 크기가 0
					continue;
				}
				
				System.out.println("=========================");
				System.out.printf(" 번호 |  제목  |  조회\n",article.size());
				for(int i=article.size();i>0;i--) {
					Article articlec = article.get(i-1);
					System.out.printf(" %2d  | %5s | %2d\n", articlec.id,articlec.title,articlec.hit);
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
				
				foundarticle.increaseHit(); //조회수
				
				//저장된 객체의 정보 출력
				System.out.println("=========================");
				System.out.printf("번호 : %d\n",foundarticle.id);
				System.out.printf("날짜 : %s\n",foundarticle.datetime);
				System.out.printf("제목 : %s\n",foundarticle.title);
				System.out.printf("내용 : %s\n",foundarticle.content);
				System.out.printf("조회 : %s\n",foundarticle.hit);
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
			else if(command.startsWith("article modify ")) { //contains

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
			}
			else System.out.println("존재하지 않는 명령어입니다.");
		}
		sc.close();
		
		System.out.println("========프로그램 종료========");
	}
	
	static void makeTestData() {
		
		System.out.println("테스트를 위한 데이터를 생성합니다.");
		
		article.add(new Article(1, "111", "111", Util_DateTime.getNowDateTimeStr(),1));
		article.add(new Article(2, "222", "222", Util_DateTime.getNowDateTimeStr(),2));
		article.add(new Article(3, "333", "333", Util_DateTime.getNowDateTimeStr(),3));
	}
}

class Article{
	int id;
	String title;
	String content;
	String datetime;
	int hit;
	
	//메서드 이름은 같고 매개변수의 개수가 다르면 다른 함수로 인식
	public Article(int id, String title, String body, String datetime){
		this(id,title,body,datetime,0); //다른 메서드 실행
	}
	
	public Article(int id, String title, String body, String datetime, int hit){
		this.id = id;
		this.title = title;
		this.content = body;
		this.datetime = datetime;
		this.hit = hit;
	}
	
	public void increaseHit() {
		hit++;
	}
}

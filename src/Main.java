import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		int idx = 0;
//		List<String> title = new ArrayList<>();
//		List<String> contents = new ArrayList<>();
		System.out.println("==프로그램 시작==");
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.printf("명령어 > ");
			String command = sc.nextLine();
			
			if(command.length() == 0) continue; //입력된 문자가 없는 경우 다시 명령어 입력 받기
			if(command.equals("system exit")) break; //system exit 입력된 경우 종료
			
			if(command.equals("article write")) {
				int id = idx + 1;
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				
				System.out.printf("내용 : ");
				String contents = sc.nextLine();
				
				System.out.println(id+"번글이 생성되었습니다");
				idx = id;
			}
			else if(command.equals("article list")) {
//				System.out.println("게시글이 없습니다");
				if(idx == 0) System.out.println("게시글이 없습니다");
				else System.out.printf("게시글이 %d개 존재합니다\n",idx);
			}
			else System.out.println("존재하지 않는 명령어입니다");
		}
		sc.close();
		
		System.out.println("==프로그램 종료==");
	}
}

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		int idx = 0;
//		List<String> title = new ArrayList<>();
//		List<String> contents = new ArrayList<>();
		System.out.println("==���α׷� ����==");
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.printf("��ɾ� > ");
			String command = sc.nextLine();
			
			if(command.length() == 0) continue; //�Էµ� ���ڰ� ���� ��� �ٽ� ��ɾ� �Է� �ޱ�
			if(command.equals("system exit")) break; //system exit �Էµ� ��� ����
			
			if(command.equals("article write")) {
				int id = idx + 1;
				System.out.printf("���� : ");
				String title = sc.nextLine();
				
				System.out.printf("���� : ");
				String contents = sc.nextLine();
				
				System.out.println(id+"������ �����Ǿ����ϴ�");
				idx = id;
			}
			else if(command.equals("article list")) {
//				System.out.println("�Խñ��� �����ϴ�");
				if(idx == 0) System.out.println("�Խñ��� �����ϴ�");
				else System.out.printf("�Խñ��� %d�� �����մϴ�\n",idx);
			}
			else System.out.println("�������� �ʴ� ��ɾ��Դϴ�");
		}
		sc.close();
		
		System.out.println("==���α׷� ����==");
	}
}

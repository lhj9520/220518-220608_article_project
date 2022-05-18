import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		int idx = 0;
		List<Article> article = new ArrayList<>();
		
		System.out.println("==���α׷� ����==");
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.printf("��ɾ� > ");
			String command = sc.nextLine().trim(); //trim() �� �� ���� ����
			
			if(command.length() == 0) continue; //�Էµ� ���ڰ� ���� ��� �ٽ� ��ɾ� �Է� �ޱ�
			if(command.equals("system exit")) break; //system exit �Էµ� ��� ����
			
			if(command.equals("article write")) {
				int id = idx + 1;
				//���� �Է� �ޱ�
				System.out.printf("���� : ");
				String title = sc.nextLine();
				//���� �Է� �ޱ�
				System.out.printf("���� : ");
				String content = sc.nextLine();
				//�Խ��� ��ü ����, id,����,���� �Ѱ��ֱ�
				Article articlec = new Article(id, title, content);
				article.add(articlec);
				
				System.out.println(id+"������ �����Ǿ����ϴ�");
				idx = id;
			}
			else if(command.equals("article list")) {

				if(article.size() == 0) System.out.println("�Խñ��� �����ϴ�"); //article ��ü ����Ʈ�� ũ�Ⱑ 0
				else {
					System.out.printf("�Խñ��� %d�� �����մϴ�\n",idx);
					for(int i=0;i<article.size();i++) {
						System.out.printf("%d ����:%s ����:%s\n", article.get(i).id,article.get(i).title,article.get(i).content);
					}
				}
			}
			else System.out.println("�������� �ʴ� ��ɾ��Դϴ�");
		}
		sc.close();
		
		System.out.println("==���α׷� ����==");
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

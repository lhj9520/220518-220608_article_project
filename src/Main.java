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
		
		System.out.println("==���α׷� ����==");
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.printf("��ɾ� > ");
			String command = sc.nextLine().trim(); //trim() �� �� ���� ����
			
			if(command.length() == 0) continue; //�Էµ� ���ڰ� ���� ��� �ٽ� ��ɾ� �Է� �ޱ�
			if(command.equals("system exit")) break; //system exit �Էµ� ��� ����
			
			//�Խñ� �ۼ�
			if(command.equals("article write")) {
				int id = lastArticleid + 1;
				//���� �Է� �ޱ�
				System.out.printf("���� : ");
				String title = sc.nextLine();
				//���� �Է� �ޱ�
				System.out.printf("���� : ");
				String content = sc.nextLine();
				//���� ��¥
				LocalDate now = LocalDate.now();
				DateTimeFormatter formatter_date = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				String formatedDate = now.format(formatter_date);
				//���� �ð�
				LocalTime time = LocalTime.now();
				DateTimeFormatter formatter_time = DateTimeFormatter.ofPattern("HH:mm:ss");
				String formatedTime = time.format(formatter_time);
				//��¥+�ð� �ϳ��� ���ڿ���
				String datetime = formatedDate +" "+ formatedTime; //StringBuffer�� ��ġ�°� �޸� �� ��

				//�Խ��� ��ü ����, id,����,����,��¥�ð� �Ѱ��ֱ�
				Article articlec = new Article(id, title, content, datetime);
				article.add(articlec);
				
				System.out.println(id+"������ �����Ǿ����ϴ�");
				lastArticleid = id;
			}
			//�Խñ� ��� �����ֱ�
			else if(command.equals("article list")) {

				if(article.size() == 0) {
					System.out.println("�Խñ��� �����ϴ�"); //article ��ü ����Ʈ�� ũ�Ⱑ 0
					continue;
				}
				System.out.printf(" ��ȣ |  ����\n",article.size());
				for(int i=article.size();i>0;i--) {
					Article articlec = article.get(i-1);
					System.out.printf("  %d  |  ����:%s\n", articlec.id,articlec.title);
				}
			}
			//�ش� �Խñ� �ڼ��� ����
			else if(command.startsWith("article detail ")) { //contains�� ���� �ٸ�����

				String[] cmd = command.split(" ");
				int num = Integer.parseInt(cmd[2]);
				Article articlec = null;
				
				//�Էµ� ��ȣ�� ������ �ִ� �Խñ� for������ ��Ž��
				for(int i=0;i<article.size();i++) {
					if(num == article.get(i).id) { // �ش� ��ȣ�� �Խñ� ��ü�� �ִٸ� ����
						articlec = article.get(i);
						break;
					}
				}
				
				if (articlec==null) {
					System.out.printf("%d�� �Խñ��� �������� �ʽ��ϴ�\n",num);
					continue;
				}
				//����� ��ü�� ���� ���
				System.out.printf("��ȣ : %d\n",articlec.id);
				System.out.printf("��¥ : %s\n",articlec.datetime);
				System.out.printf("���� : %s\n",articlec.title);
				System.out.printf("���� : %s\n",articlec.content);
			}
			//�ش� �Խñ� ����
			else if(command.startsWith("article delete ")) { //contains

				String[] cmd = command.split(" ");
				int num = Integer.parseInt(cmd[2]);
				Article articlec = null;
				
				//�Էµ� ��ȣ�� ������ �ִ� �Խñ� for������ ��Ž��
				for(int i=0;i<article.size();i++) {
					if(num == article.get(i).id) { // �ش� ��ȣ�� �Խñ� ��ü�� �ִٸ� ����
						articlec = article.get(i);
						break;
					}
				}
				
				if (articlec==null) {
					System.out.printf("%d�� �Խñ��� �������� �ʽ��ϴ�\n",num);
					continue;
				}
				//����� ��ü�� �ε��� ��ȣ�� �̿��Ͽ� array list���� ����
				article.remove(article.indexOf(articlec));
				System.out.printf("%d�� �Խñ��� �����Ǿ����ϴ�\n",num);
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
	String datetime;
	
	public Article(int id, String title, String body, String datetime){
		this.id = id;
		this.title = title;
		this.content = body;
		this.datetime = datetime;
	}
}

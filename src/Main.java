import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	//�ν��Ͻ� ���� ���� -> Ŭ���� ��ü�� �����Ǹ� �ν��Ͻ��� ���� �����ϴ�.
	private static List<Article> article; //private �ܺο��� ���� �Ұ���
	//������
	static{
		article = new ArrayList<>();
	}
	
	//Ŭ���� ���� ���� -> Ŭ���� ��ü�� �������� �ʾƵ� �ν��Ͻ��� ���� �����ϴ�.
	//static int lastArticleid = 0;
	//static List<Article> article = new ArrayList<>();
	
	public static void main(String[] args) {
		//���� ���� ���� -> �ش� �޼ҵ� �������� ���� �����ϴ�.
		//int lastArticleid = 0;
		//List<Article> article = new ArrayList<>();
		
		//Main ex1 = new Main(); //-> �ν��Ͻ� ������ ����
		
		System.out.println("========���α׷� ����========");
		
		makeTestData();
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.printf("��ɾ� > ");
			String command = sc.nextLine().trim(); //trim() �� �� ���� ����
			
			if(command.length() == 0) continue; //�Էµ� ���ڰ� ���� ��� �ٽ� ��ɾ� �Է� �ޱ�
			if(command.equals("system exit")) break; //system exit �Էµ� ��� ����
			
			//�Խñ� �ۼ� - create
			if(command.equals("article write")) {
				int id = article.size() + 1; //����Ʈ ��ü�� ũ�⸦ �������� ��
				//���� �Է� �ޱ�
				System.out.printf("���� : ");
				String title = sc.nextLine();
				//���� �Է� �ޱ�
				System.out.printf("���� : ");
				String content = sc.nextLine();
				//���� �ð� �޾ƿ���
				String datetime = Util_DateTime.getNowDateTimeStr(); //�ٸ� Ŭ������ ���� ���(���� ���� �ֵ� �޼���� �����)

				//�Խ��� ��ü ����, id,����,����,��¥�ð� �Ѱ��ֱ�
				Article articlec = new Article(id, title, content, datetime);
				article.add(articlec);
				
				System.out.println(id+"������ �����Ǿ����ϴ�.");
			}
			//�Խñ� ��� �����ֱ�
			else if(command.equals("article list")) {

				if(article.size() == 0) {
					System.out.println("�Խñ��� �������� �ʽ��ϴ�."); //article ��ü ����Ʈ�� ũ�Ⱑ 0
					continue;
				}
				
				System.out.println("=========================");
				System.out.printf(" ��ȣ |  ����  |  ��ȸ\n",article.size());
				for(int i=article.size();i>0;i--) {
					Article articlec = article.get(i-1);
					System.out.printf(" %2d  | %5s | %2d\n", articlec.id,articlec.title,articlec.hit);
				}
				System.out.println("=========================");
			}
			//�ش� �Խñ� �ڼ��� ���� - read
			else if(command.startsWith("article detail ")) { //contains�� ���� �ٸ�����

				String[] cmd = command.split(" ");
				int id = Integer.parseInt(cmd[2]);
				Article foundarticle = null;
				
				//�Էµ� ��ȣ�� ������ �ִ� �Խñ� for������ ��Ž��
				for(int i=0;i<article.size();i++) {
					if(id == article.get(i).id) { // �ش� ��ȣ�� �Խñ� ��ü�� �ִٸ� ����
						foundarticle = article.get(i);
						break;
					}
				}
				
				if (foundarticle==null) {
					System.out.printf("%d�� �Խñ��� �������� �ʽ��ϴ�.\n",id);
					continue;
				}
				
				foundarticle.increaseHit(); //��ȸ��
				
				//����� ��ü�� ���� ���
				System.out.println("=========================");
				System.out.printf("��ȣ : %d\n",foundarticle.id);
				System.out.printf("��¥ : %s\n",foundarticle.datetime);
				System.out.printf("���� : %s\n",foundarticle.title);
				System.out.printf("���� : %s\n",foundarticle.content);
				System.out.printf("��ȸ : %s\n",foundarticle.hit);
				System.out.println("=========================");
			}
			//�ش� �Խñ� ���� - delete
			else if(command.startsWith("article delete ")) { //contains

				String[] cmd = command.split(" ");
				int id = Integer.parseInt(cmd[2]);
				int foundindex = -1;
				
				//�Էµ� ��ȣ�� ������ �ִ� �Խñ� for������ ��Ž��
				for(int i=0;i<article.size();i++) {
					if(id == article.get(i).id) { // �ش� ��ȣ�� �Խñ��� id�� �ִٸ� ����
						foundindex = i;
						break;
					}
				}
				
				if (foundindex == -1) {
					System.out.printf("%d�� �Խñ��� �������� �ʽ��ϴ�.\n",id);
					continue;
				}
				
				//����� ��ü�� �ε��� ��ȣ�� �̿��Ͽ� array list���� ����
				article.remove(foundindex);
				System.out.printf("%d�� �Խñ��� �����Ǿ����ϴ�.\n",id);
			}
			//�ش� �Խñ� ���� - update
			else if(command.startsWith("article modify ")) { //contains

				String[] cmd = command.split(" ");
				int num = Integer.parseInt(cmd[2]);
				Article foundarticle = null;
				
				//�Էµ� ��ȣ�� ������ �ִ� �Խñ� for������ ��Ž��
				for(int i=0;i<article.size();i++) {
					if(num == article.get(i).id) { // �ش� ��ȣ�� �Խñ� ��ü�� �ִٸ� ����
						foundarticle = article.get(i);
						break;
					}
				}
				
				if (foundarticle==null) {
					System.out.printf("%d�� �Խñ��� �������� �ʽ��ϴ�.\n",num);
					continue;
				}

				//���� �ٽ� �Է� �ޱ�
				System.out.printf("���� : ");
				foundarticle.title = sc.nextLine();
				//���� �ٽ� �Է� �ޱ�
				System.out.printf("���� : ");
				foundarticle.content = sc.nextLine();
				
				System.out.printf("%d�� �Խñ��� �����Ǿ����ϴ�.\n",num);
			}
			else System.out.println("�������� �ʴ� ��ɾ��Դϴ�.");
		}
		sc.close();
		
		System.out.println("========���α׷� ����========");
	}
	
	static void makeTestData() {
		
		System.out.println("�׽�Ʈ�� ���� �����͸� �����մϴ�.");
		
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
	
	//�޼��� �̸��� ���� �Ű������� ������ �ٸ��� �ٸ� �Լ��� �ν�
	public Article(int id, String title, String body, String datetime){
		this(id,title,body,datetime,0); //�ٸ� �޼��� ����
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

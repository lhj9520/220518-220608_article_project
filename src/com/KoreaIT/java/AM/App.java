package com.KoreaIT.java.AM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.AM.Util.Util;
import com.KoreaIT.java.AM.dto.Article;

public class App {
	private List<Article> article;
	static int lastid = 0;

	App() {
		article = new ArrayList<>();
	}

	public void start() {

		System.out.println("========���α׷� ����========");

		makeTestData();

		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.printf("��ɾ� > ");
			String command = sc.nextLine().trim(); // trim() �� �� ���� ����

			if (command.length() == 0)
				continue; // �Էµ� ���ڰ� ���� ��� �ٽ� ��ɾ� �Է� �ޱ�
			if (command.equals("system exit"))
				break; // system exit �Էµ� ��� ����

			// �Խñ� �ۼ� - create
			if (command.equals("article write")) {
				// int id = article.size() + 1; //����Ʈ ��ü�� ũ�⸦ �������� �� �ٵ� �̷��� �ϸ� delete -> write ��
				// �Խñ� index ���� �ٽ� lastid ���� �߰���
				int id = lastid + 1;
				// ���� �Է� �ޱ�
				System.out.printf("���� : ");
				String title = sc.nextLine();
				// ���� �Է� �ޱ�
				System.out.printf("���� : ");
				String content = sc.nextLine();

				// �Խ��� ��ü ����, id,����,����,��¥�ð� �Ѱ��ֱ�
				Article articlec = new Article(id, title, content, Util.getNowDateTimeStr());
				article.add(articlec);
				lastid = id;

				System.out.println(id + "������ �����Ǿ����ϴ�.");
			}
			// �Խñ� ��� �����ֱ� - �˻� ��� �߰�
			else if (command.startsWith("article list")) {

				if (article.size() == 0) {
					System.out.println("�Խñ��� �������� �ʽ��ϴ�."); // article ��ü ����Ʈ�� ũ�Ⱑ 0
					continue;
				}
				
				List<Article> forPrintArticles = article; // list ���� ���� article����Ʈ ����

				String searchkeyword = command.substring("article list".length()).trim();
				
				if(searchkeyword.length() > 0) { //Ű���尡 �ִ� ���
					forPrintArticles = new ArrayList<>(); //���ο� ����Ʈ ����
					for(Article articlec : article) {
						if(articlec.title.contains(searchkeyword)) { //Ű���尡 ���� ���ԵǸ�
							forPrintArticles.add(articlec); //�ش� ������ �߰�
						}
					}					
					if (forPrintArticles.size() == 0) {
						System.out.printf("%s�� ��ġ�ϴ� �˻������ �����ϴ�.\n",searchkeyword);
						continue;
					}
				}
				//Ű���尡 �����ϴ� ��� ���ο� ����Ʈ �����Ͽ� ����Ʈ ���
				//Ű���尡 �������� �ʴ� ��� ����� article ����Ʈ ���
				System.out.println("=========================");
				System.out.printf(" ��ȣ |  ����  |  ��ȸ\n", article.size());
				
				for (int i = forPrintArticles.size(); i > 0; i--) {
					Article articlec = forPrintArticles.get(i - 1);
					System.out.printf(" %2d  | %5s | %2d\n", articlec.id, articlec.title, articlec.hit);
				}
				System.out.println("=========================");
			}
			// �ش� �Խñ� �ڼ��� ���� - read
			else if (command.startsWith("article detail ")) { // contains�� ���� �ٸ�����

				String[] cmd = command.split(" ");
				int id = Integer.parseInt(cmd[2]);
				Article foundarticle = findarticle(id);

				if (foundarticle == null) {
					System.out.printf("%d�� �Խñ��� �������� �ʽ��ϴ�.\n", id);
					continue;
				}

				foundarticle.increaseHit(); // ��ȸ��

				// ����� ��ü�� ���� ���
				System.out.println("=========================");
				System.out.printf("��ȣ : %d\n", foundarticle.id);
				System.out.printf("��¥ : %s\n", foundarticle.datetime);
				System.out.printf("���� : %s\n", foundarticle.title);
				System.out.printf("���� : %s\n", foundarticle.content);
				System.out.printf("��ȸ : %s\n", foundarticle.hit);
				System.out.println("=========================");
			}
			// �ش� �Խñ� ���� - update
			else if (command.startsWith("article modify ")) { // contains

				String[] cmd = command.split(" ");
				int id = Integer.parseInt(cmd[2]);
				Article foundarticle = findarticle(id);

				if (foundarticle == null) {
					System.out.printf("%d�� �Խñ��� �������� �ʽ��ϴ�.\n", id);
					continue;
				}

				// ���� �ٽ� �Է� �ޱ�
				System.out.printf("���� : ");
				foundarticle.title = sc.nextLine();
				// ���� �ٽ� �Է� �ޱ�
				System.out.printf("���� : ");
				foundarticle.content = sc.nextLine();

				System.out.printf("%d�� �Խñ��� �����Ǿ����ϴ�.\n", id);
			}
			// �ش� �Խñ� ���� - delete
			else if (command.startsWith("article delete ")) { // contains

				String[] cmd = command.split(" ");
				int id = Integer.parseInt(cmd[2]);
				int foundindex = findarticleindex(id);

				if (foundindex == -1) {
					System.out.printf("%d�� �Խñ��� �������� �ʽ��ϴ�.\n", id);
					continue;
				}

				// ����� ��ü�� �ε��� ��ȣ�� �̿��Ͽ� array list���� ����
				article.remove(foundindex);
				System.out.printf("%d�� �Խñ��� �����Ǿ����ϴ�.\n", id);
			}
			else System.out.println("�������� �ʴ� ��ɾ��Դϴ�.");
		}
		sc.close();

		System.out.println("========���α׷� ����========");
	}

	void makeTestData() {

		System.out.println("�׽�Ʈ�� ���� �����͸� �����մϴ�.");

		article.add(new Article(1, "111", "111", Util.getNowDateTimeStr(), 1));
		article.add(new Article(2, "222", "222", Util.getNowDateTimeStr(), 2));
		article.add(new Article(3, "333", "333", Util.getNowDateTimeStr(), 3));
		lastid = article.size();
	}

	// �ߺ� ��� ���� -> �޼��� ����
	Article findarticle(int id) {
		//for Ž�� �ߺ� ����
		int index = findarticleindex(id);
		
		if (index != -1) {
			return article.get(index);
		}
		return null;
	}

	int findarticleindex(int id) {
		// �Էµ� ��ȣ�� ������ �ִ� �Խñ� foreach������ ��Ž��
		int i=0;
		for (Article article : article) {
			if (id == article.id) {
				return i;
			}
			i++;
		}
		return -1;
	}

}

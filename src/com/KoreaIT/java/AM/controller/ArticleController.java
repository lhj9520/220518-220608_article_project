package com.KoreaIT.java.AM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.AM.Util.Util;
import com.KoreaIT.java.AM.dto.Article;

public class ArticleController {
	
	private Scanner sc;
	private List<Article> article;
	int lastid = 0;

	public ArticleController(Scanner sc, List<Article> articles) {
		this.sc = sc;
		this.article = articles;
		this.lastid = article.size();
	}
	
	public void doWrite() {
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

	public void doList(String command) {
		if (article.size() == 0) {
			System.out.println("�Խñ��� �������� �ʽ��ϴ�."); // article ��ü ����Ʈ�� ũ�Ⱑ 0
			return;
		}

		List<Article> forPrintArticles = article; // list ���� ���� article����Ʈ ����

		String searchkeyword = command.substring("article list".length()).trim();

		if (searchkeyword.length() > 0) { // Ű���尡 �ִ� ���
			forPrintArticles = new ArrayList<>(); // ���ο� ����Ʈ ����
			for (Article articlec : article) {
				if (articlec.title.contains(searchkeyword)) { // Ű���尡 ���� ���ԵǸ�
					forPrintArticles.add(articlec); // �ش� ������ �߰�
				}
			}
			if (forPrintArticles.size() == 0) {
				System.out.printf("%s�� ��ġ�ϴ� �˻������ �����ϴ�.\n", searchkeyword);
				return;
			}
		}
		// Ű���尡 �����ϴ� ��� ���ο� ����Ʈ �����Ͽ� ����Ʈ ���
		// Ű���尡 �������� �ʴ� ��� ����� article ����Ʈ ���
		System.out.println("=========================");
		System.out.printf(" ��ȣ |  ����  |  ��ȸ\n", article.size());

		for (int i = forPrintArticles.size(); i > 0; i--) {
			Article articlec = forPrintArticles.get(i - 1);
			System.out.printf(" %2d  | %5s | %2d\n", articlec.id, articlec.title, articlec.hit);
		}
		System.out.println("=========================");
	}

	public void doDetail(String command) {
		String[] cmd = command.split(" ");
		int id = Integer.parseInt(cmd[2]);
		Article foundarticle = findarticle(id);

		if (foundarticle == null) {
			System.out.printf("%d�� �Խñ��� �������� �ʽ��ϴ�.\n", id);
			return;
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

	public void doUpdate(String command) {
		String[] cmd = command.split(" ");
		int id = Integer.parseInt(cmd[2]);
		Article foundarticle = findarticle(id);

		if (foundarticle == null) {
			System.out.printf("%d�� �Խñ��� �������� �ʽ��ϴ�.\n", id);
			return;
		}

		// ���� �ٽ� �Է� �ޱ�
		System.out.printf("���� : ");
		foundarticle.title = sc.nextLine();
		// ���� �ٽ� �Է� �ޱ�
		System.out.printf("���� : ");
		foundarticle.content = sc.nextLine();

		System.out.printf("%d�� �Խñ��� �����Ǿ����ϴ�.\n", id);
	}

	public void doDelete(String command) {
		String[] cmd = command.split(" ");
		int id = Integer.parseInt(cmd[2]);
		int foundindex = findArticleIndex(id);

		if (foundindex == -1) {
			System.out.printf("%d�� �Խñ��� �������� �ʽ��ϴ�.\n", id);
			return;
		}

		// ����� ��ü�� �ε��� ��ȣ�� �̿��Ͽ� array list���� ����
		article.remove(foundindex);
		System.out.printf("%d�� �Խñ��� �����Ǿ����ϴ�.\n", id);
	}
	
	// �ߺ� ��� ���� -> �޼��� ����
	private Article findarticle(int id) {
		// for Ž�� �ߺ� ����
		int index = findArticleIndex(id);

		if (index != -1) {
			return article.get(index);
		}
		return null;
	}

	private int findArticleIndex(int id) {
		// �Էµ� ��ȣ�� ������ �ִ� �Խñ� foreach������ ��Ž��
		int i = 0;
		for (Article article : article) {
			if (id == article.id) {
				return i;
			}
			i++;
		}
		return -1;
	}
}

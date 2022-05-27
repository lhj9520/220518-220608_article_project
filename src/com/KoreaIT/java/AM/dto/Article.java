package com.KoreaIT.java.AM.dto;

public class Article {
	public int id;
	public String title;
	public String content;
	public String datetime;
	public int hit;

	// �޼��� �̸��� ���� �Ű������� ������ �ٸ��� �ٸ� �Լ��� �ν�
	public Article(int id, String title, String body, String datetime) {
		this(id, title, body, datetime, 0); // �ٸ� �޼��� ����
	}

	public Article(int id, String title, String body, String datetime, int hit) {
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
package com.KoreaIT.java.AM.dto;

public class Article extends Dto {
	public String title;
	public String content;
	public int hit;
	public int memberid;

	// �޼��� �̸��� ���� �Ű������� ������ �ٸ��� �ٸ� �Լ��� �ν�
	public Article(int id,int mid, String title, String body, String datetime) {
		this(id, mid, title, body, datetime, 0); // �ٸ� �޼��� ����
	}

	public Article(int id, int mid, String title, String body, String datetime, int hit) {
		this.id = id;
		this.memberid = mid;
		this.title = title;
		this.content = body;
		this.datetime = datetime;
		this.hit = hit;
	}

	public void increaseHit() {
		hit++;
	}
}
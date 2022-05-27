package com.KoreaIT.java.AM.dto;

public class Article {
	public int id;
	public String title;
	public String content;
	public String datetime;
	public int hit;

	// 메서드 이름은 같고 매개변수의 개수가 다르면 다른 함수로 인식
	public Article(int id, String title, String body, String datetime) {
		this(id, title, body, datetime, 0); // 다른 메서드 실행
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
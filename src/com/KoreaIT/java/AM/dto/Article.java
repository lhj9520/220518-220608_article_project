package com.KoreaIT.java.AM.dto;

public class Article extends Dto {
	public String title;
	public String content;
	public int hit;
	public int memberid;

	// 메서드 이름은 같고 매개변수의 개수가 다르면 다른 함수로 인식
	public Article(int id,int mid, String title, String body, String datetime) {
		this(id, mid, title, body, datetime, 0); // 다른 메서드 실행
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
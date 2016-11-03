package com.blog.entity;

import java.util.Date;

public class BlogAdvice {
	private int id;
	private String nickName;
	private String userIP;
	private String content;
	private Date publishTime;
	private String reply;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getUserIP() {
		return userIP;
	}
	public void setUserIP(String userIP) {
		this.userIP = userIP;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public BlogAdvice() {
		super();
	}
	public BlogAdvice(String nickName, String userIP, String content) {
		super();
		this.nickName = nickName;
		this.userIP = userIP;
		this.content = content;
	}
	
	
}

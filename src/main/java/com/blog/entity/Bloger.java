package com.blog.entity;

/**
 * Created by ldb on 2016/9/22.
 */
public class Bloger {
    private int id;
    private String userName;
    private String password;
    private String nickName;
    private String job;
    private String hobby;
    private String email;
    private String webClick;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	public String getWebClick() {
		return webClick;
	}

	public void setWebClick(String webClick) {
		this.webClick = webClick;
	}


	public Bloger(){
        super();
    }

    public Bloger(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}

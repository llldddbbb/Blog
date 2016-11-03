package com.blog.entity;

import java.util.Date;


public class Blog {

    private int id;//主键
    private String title;//博客标题
    private String content;//博客内容
    private String author;//博客作者
    private int readNum;//阅读量
    private Date publishTime;//发布时间
    private int isRecommend;//是否是推荐博文
    private int recommendOrder;//推荐博文顺序
    private BlogType blogType;//博客类别
    private BlogTag blogTag;//博客标签
    private String coverImageName;//封面图片名称
    
    private int blogCount;//每个时期的博客数量
    private String publishTimeStr;//发布时期

    
    
    public Blog() {
		super();
	}

    
	public Blog(int id) {
		super();
		this.id = id;
	}


	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getReadNum() {
        return readNum;
    }

    public void setReadNum(int readNum) {
        this.readNum = readNum;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }


    public BlogType getBlogType() {
        return blogType;
    }

    public void setBlogType(BlogType blogType) {
        this.blogType = blogType;
    }

    public BlogTag getBlogTag() {
       return blogTag;
    }

    public void setBlogTag(BlogTag blogTag) {
        this.blogTag = blogTag;
    }

    public int getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(int isRecommend) {
        this.isRecommend = isRecommend;
    }

    public int getRecommendOrder() {
        return recommendOrder;
    }

    public void setRecommendOrder(int recommendOrder) {
        this.recommendOrder = recommendOrder;
    }

   
	public int getBlogCount() {
		return blogCount;
	}

	public void setBlogCount(int blogCount) {
		this.blogCount = blogCount;
	}

	public String getPublishTimeStr() {
		return publishTimeStr;
	}

	public void setPublishTimeStr(String publishTimeStr) {
		this.publishTimeStr = publishTimeStr;
	}


	public String getCoverImageName() {
		return coverImageName;
	}


	public void setCoverImageName(String coverImageName) {
		this.coverImageName = coverImageName;
	}



    
}

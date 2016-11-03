package com.blog.entity;

public class PageBean {
	
	private int page;//��ǰҳ
	private int pageSize;//ҳ���С
	@SuppressWarnings("unused")
	private int start;//��ʼ��
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getStart() {
		return (page-1)*pageSize;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public PageBean() {
		super();
		// TODO �Զ����ɵĹ��캯�����
	}
	public PageBean(int page, int pageSize) {
		super();
		this.page = page;
		this.pageSize = pageSize;
	}
	
	
}

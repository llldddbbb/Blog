package com.blog.util;


public class PageUtil {
	
	public static String getPageCode(String tarUrl,int currentPage,int totalNum,int pageSize){
		if(totalNum==0){
			return null;
		}
		StringBuffer pageCode=new StringBuffer();
		int totalPage=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
		
		if(currentPage!=1){
			pageCode.append("<a href=javascript:window.open('"+tarUrl+"&page="+(currentPage-1)+"','newwindow')><<</a>");
		}else{
			pageCode.append("<a><b><<</b></a>");
		}
		
		for(int i=currentPage-2;i<=currentPage+2;i++){
			if(i<1||i>totalPage){
				continue;
			}
			if(currentPage==i){
				pageCode.append("<b>"+i+"</b>");
			}else{
				pageCode.append("<a href=javascript:window.open('"+tarUrl+"&page="+i+"','newwindow')>"+i+"</a>");
			}
		}
		if(currentPage!=totalPage){
			pageCode.append("<a href=javascript:window.open('"+tarUrl+"&page="+(currentPage+1)+"','newwindow')>>></a>");
		}else{
			pageCode.append("<a><b>>></b></a>");
		}
		return pageCode.toString();
	}
}

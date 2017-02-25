package com.wondersgroup.falcon.Util;
/**
 * 
 * <p>Title:[类标题] </p>
 * <p>Description: [类功能描述]</p> 
 *
 * Created by [www] [Aug 6, 2009]
 * Midified by [修改人] [修改时间]
 *
 */

public class PageTool {
	
	/*
	 * 第几页
	 */
	private int cur=1;  
	/*
	 * 每页显示多少行
	 */
    private int size=10;   
    /*
     * 一共多少行
     */
    private int total=0;
    /*
     *从第几行开始
     */
    private int start;
    
    public int getStart() {
		return (getCur()-1)*getSize();
	}

	public void setStart(int start) {
		this.start = start;
	}

	public PageTool(int cur,int size,int total){   
        this.cur=cur;   
        this.size=size;   
        this.total=total;   
    }   
  
    public PageTool(int size){   
        this.size=size;   
        this.cur=1;   
        this.total=1;   
    } 
    public PageTool(){
    	
    }
    
	public int getCur() {
		return cur;
	}
	public void setCur(int cur) {
		this.cur = cur;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	} 
}

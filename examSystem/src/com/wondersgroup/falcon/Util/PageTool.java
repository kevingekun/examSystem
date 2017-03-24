package com.wondersgroup.falcon.Util;
/**
 * 
 * <p>Title:[�����] </p>
 * <p>Description: [�๦������]</p> 
 *
 * Created by [www] [Aug 6, 2009]
 * Midified by [�޸���] [�޸�ʱ��]
 *
 */

public class PageTool {
	
	/*
	 * �ڼ�ҳ
	 */
	private int cur=1;  
	/*
	 * ÿҳ��ʾ������
	 */
    private int size=10;   
    /*
     * һ��������
     */
    private int total=0;
    /*
     *�ӵڼ��п�ʼ
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

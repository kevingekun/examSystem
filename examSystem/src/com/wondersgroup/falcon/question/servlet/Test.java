package com.wondersgroup.falcon.question.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Test  {

	/**
	 * Constructor of the object.
	 */
	public Test() {
		super();
	}

	public int[] getImportanceNum(int allnum){
		int[] nums = new int[3];
		nums[0]=0;
		nums[1]=0;
		nums[2]=0;
		try{
			List list1 = new ArrayList();
			List list2 = new ArrayList();
			List list3 = new ArrayList();
			
			for(int i=0;i<allnum;i++){
				int randomnum = (int) Math.ceil(Math.random()*300);
				System.out.println(randomnum);
				if(randomnum>=1&&randomnum<=100)list1.add(new Integer(randomnum));
				if(randomnum>100&&randomnum<=200)list2.add(new Integer(randomnum));				
				if(randomnum>200&&randomnum<=300)list3.add(new Integer(randomnum));				
			}
			
			if(list1!=null&&list1.size()>0) nums[0]=list1.size();
			if(list2!=null&&list2.size()>0) nums[1]=list2.size();			
			if(list3!=null&&list3.size()>0) nums[2]=list3.size();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return nums;
	}
	
	public static void main(String[] args) {
		Test t= new Test();
		int [] s =t.getImportanceNum(10);
		for(int i=0;i<s.length;i++){
			System.out.println("-----------"+s[i]);
		}
	}
}

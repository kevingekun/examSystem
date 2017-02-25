package com.wondersgroup.kaoshi.action;

import freemarker.template.Configuration;


public class SingletonFreemarker {

	private SingletonFreemarker(){
	}
	
	private static class SingletonFactory{
		private static Configuration cfg = new Configuration();
	}
	
	public static Configuration getConfiguration(){
		return SingletonFactory.cfg;
	}
}

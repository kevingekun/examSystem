package com.wondersgroup.utils;

import java.text.SimpleDateFormat;
import java.util.Comparator;

import org.apache.commons.lang.time.DateUtils;

import com.wondersgroup.falcon.Util.DateUtil;
import com.wondersgroup.falcon.paper.model.TableInfo;

public class SortByAvg implements Comparator {
	public int compare(Object o1, Object o2) {
		TableInfo s1 = (TableInfo) o1;
		TableInfo s2 = (TableInfo) o2;
		if (s1.getAvg() < s2.getAvg())
			return 1;
		return 0;
	}

}

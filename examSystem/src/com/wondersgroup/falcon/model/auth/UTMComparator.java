package com.wondersgroup.falcon.model.auth;

import java.util.Comparator;

public class UTMComparator implements Comparator {

	public int compare(Object arg0, Object arg1) {
		if (arg0 instanceof UserMenus && arg1 instanceof UserMenus) {
			Long order0 = ((UserMenus)arg0).getOrdering();
			Long order1 = ((UserMenus) arg1).getOrdering(); 
			String id0 = ((UserMenus) arg0).getMenuid();
			String id1 = ((UserMenus) arg1).getMenuid();

			if (id0 == null)
				return 1;
			if (id1 == null)
				return -1;
			if (order0.compareTo(order1) == 0)
				return id0.compareTo(id1);
			return order0.compareTo(order1);
		}
		return 0;
	}

}

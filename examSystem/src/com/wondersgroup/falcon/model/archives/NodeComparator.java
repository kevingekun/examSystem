package com.wondersgroup.falcon.model.archives;

import java.util.Comparator;

public class NodeComparator implements Comparator {

	public int compare(Object arg0, Object arg1) {
		if (arg0 instanceof Node && arg1 instanceof Node) {
			Integer order0 = new Integer(((Node) arg0).getOrdering());
			Integer order1 = new Integer(((Node) arg1).getOrdering());
			Long id0 = ((Node) arg0).getId();
			Long id1 = ((Node) arg1).getId();

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

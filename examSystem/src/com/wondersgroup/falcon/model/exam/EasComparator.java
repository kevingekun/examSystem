package com.wondersgroup.falcon.model.exam;

import java.util.Comparator;

public class EasComparator implements Comparator {

	public int compare(Object arg0, Object arg1) {
		if (arg0 instanceof ExamAnswersQuestions && arg1 instanceof ExamAnswersQuestions) {
			Integer order0 = new Integer(((ExamAnswersQuestions) arg0).getOrdering());
			Integer order1 = new Integer(((ExamAnswersQuestions) arg1).getOrdering());
			String id0 = ((ExamAnswersQuestions) arg0).getId();
			String id1 = ((ExamAnswersQuestions) arg1).getId();

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

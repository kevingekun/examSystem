package com.wondersgroup.kaoshi.action;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.util.JSONUtils;

import com.wondersgroup.falcon.question.model.EQuestions;
import com.wondersgroup.kaoshi.service.EQuestionsService;
import com.wondersgroup.kaoshi.service.EpaperquestionsService;
import com.wondersgroup.kaoshi.util.AbstractAction;

public class AjaxPaperFindQuestionCountByType extends AbstractAction {
	private EQuestionsService equestionsService;
	private EpaperquestionsService epaperquestionsService;
	private transient Long type;
	private String serviceType;
	private String bxType;
	private Integer count;
	private String counts;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getCounts() {
		return counts;
	}

	public void setCounts(String counts) {
		this.counts = counts;
	}

	public void setEquestionsService(EQuestionsService equestionsService) {
		this.equestionsService = equestionsService;
	}

	@Override
	public String execute() throws Exception {
		// this.count = this.equestionsService.findqQuestionCountByType(type);
		List<EQuestions> list = this.equestionsService.findQuestionCounts(
				serviceType, bxType);
		int[] array = new int[13];
		for (EQuestions questions : list) {
			Long id = questions.getEquestiontype().getId();
			int aa = Integer.parseInt(Long.toString(id));
			array[aa - 1] = array[aa - 1] + 1;
		}
		counts = "[";
		for (int i = 0; i < array.length; i++) {
			counts = counts + array[i] + ",";
		}
		if (counts.length() > 1)
			counts = counts.substring(0, counts.length() - 1) + "]";
		System.out.println("-------------"+counts);
		return SUCCESS;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getBxType() {
		return bxType;
	}

	public void setBxType(String bxType) {
		this.bxType = bxType;
	}

}

package com.wondersgroup.technocracy.action;

import java.util.List;

import com.wondersgroup.kaoshi.util.AbstractAction;
import com.wondersgroup.kaoshi.util.AbstractPageNavAction;
import com.wondersgroup.popedom.bo.ExamStaff;
import com.wondersgroup.popedom.service.AddStaffService;
import com.wondersgroup.technocracy.bo.Addexpert;
import com.wondersgroup.technocracy.bo.Addexperts;
import com.wondersgroup.technocracy.bo.HZ93;
import com.wondersgroup.technocracy.bo.Querydisplay;
import com.wondersgroup.technocracy.service.QueryExpertService;

public class QueryExpertAction extends AbstractPageNavAction {

	private QueryExpertService queryexpertService;
	private Addexpert expert;
	private Addexperts experts;
	private List<Querydisplay> expertinfo;
	private Addexpert expertinfo1;
	private Addexperts expertinfo2;
	private String expertinfo3;

	private String eid;
	private String name;
	private String org;
	private String committee;
	private String kcid;
	private String ksid;
	private String expertstyle;
	

	@SuppressWarnings("unchecked")
	@Override
	public String doAcion() throws Exception {

		this.pageReturn = this.queryexpertService.checkExpert(this.pageTool,
				name, org, committee, expertstyle);
		this.expertinfo = this.pageReturn.getReturnList();
		return SUCCESS;

	}

	public String detail() {
		// String hzz001=getRequest().getParameter("eid");
		Long id = Long.valueOf(eid);
		List<Object> list = queryexpertService.detailquery(id);
		expertinfo1 = (Addexpert) list.get(0);
		expertinfo2 = (Addexperts) list.get(1);
		expertinfo3 = (String) list.get(2);
		return SUCCESS;
	}

	public QueryExpertService getQueryexpertService() {
		return queryexpertService;
	}

	public void setQueryexpertService(QueryExpertService queryexpertService) {
		this.queryexpertService = queryexpertService;
	}

	public Addexpert getExpert() {
		return expert;
	}

	public void setExpert(Addexpert expert) {
		this.expert = expert;
	}

	public List<Querydisplay> getExpertinfo() {
		return expertinfo;
	}

	public void setExpertinfo(List<Querydisplay> expertinfo) {
		this.expertinfo = expertinfo;
	}

	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public Addexpert getExpertinfo1() {
		return expertinfo1;
	}

	public void setExpertinfo1(Addexpert expertinfo1) {
		this.expertinfo1 = expertinfo1;
	}

	public Addexperts getExpertinfo2() {
		return expertinfo2;
	}

	public void setExpertinfo2(Addexperts expertinfo2) {
		this.expertinfo2 = expertinfo2;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getCommittee() {
		return committee;
	}

	public void setCommittee(String committee) {
		this.committee = committee;
	}

	public Addexperts getExperts() {
		return experts;
	}

	public void setExperts(Addexperts experts) {
		this.experts = experts;
	}

	public String getKcid() {
		return kcid;
	}

	public void setKcid(String kcid) {
		this.kcid = kcid;
	}

	public String getExpertinfo3() {
		return expertinfo3;
	}

	public void setExpertinfo3(String expertinfo3) {
		this.expertinfo3 = expertinfo3;
	}

	public String getExpertstyle() {
		return expertstyle;
	}

	public void setExpertstyle(String expertstyle) {
		this.expertstyle = expertstyle;
	}


	public String getKsid() {
		return ksid;
	}


	public void setKsid(String ksid) {
		this.ksid = ksid;
	}

}

package com.wondersgroup.falcon.jdys.action;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import oracle.sql.DATE;
import oracle.sql.TIMESTAMP;

import org.apache.struts2.ServletActionContext;
import net.sf.json.JSONArray;
import com.wondersgroup.falcon.acegi.AcegiUtil;
import com.wondersgroup.falcon.acegi.UserDetailsImpl;
import com.wondersgroup.falcon.beans.auth.ProfessionBean;
import com.wondersgroup.falcon.jdys.bo.TreeOfJdys;
import com.wondersgroup.falcon.jdys.service.JdService;
import com.wondersgroup.kaoshi.bo.Tdjobexamdot;
import com.wondersgroup.kaoshi.bo.Tjobsubject;
import com.wondersgroup.kaoshi.bo.Tkcategory;
import com.wondersgroup.kaoshi.util.AbstractAction;

public class JdAction extends AbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JdService jdService;
	private String gzid;
	private String gzmc;
	private String dj;
	private String id_job;
	private String[] data;
	private String[] data_d;//被删除的鉴定要素id集合
	private JSONArray json;
	private String state;//1表示鉴定要素已经存在，0表示新增的鉴定要素
	private File jdysFile;
	private String tkCategory;//题库类别
	private String companyForShort;//企业简称
	private String companyBriefCode;//企业简码
	
	private String yjtk;//一级题库
	private String tkmc;//题库名称
	private String remark;//备注
	
	@Override
	public String execute(){
		return SUCCESS;
	}
	public void loadJdys(){
		
	}
	public void findJd(){
		List<Object> list = findJdByGzAndDj();
		/*Iterator<Object> it = list.iterator();
		while(it.hasNext()){
			//Tdjobexamdot t = (Tdjobexamdot)iterator.next();
			System.out.println(it.next());
		}*/
		JSONArray json = JSONArray.fromObject(list);
		//System.out.println("json"+json);
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 新增工种 校验
	 * 
	 * @author gkk
	 * @date 2016-11-21 上午9:54:20
	 */
	public void gzCheck(){
		try {
			gzmc = java.net.URLDecoder.decode(gzmc , "UTF-8").trim();
			gzid = java.net.URLDecoder.decode(gzid , "UTF-8").trim();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String rtmsg = jdService.gzCheck(gzmc, gzid);
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.getWriter().write(rtmsg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * cz70工种新增
	 * 
	 * @author gkk
	 * @date 2016-11-21 上午11:24:07
	 */
	public void gzAdd(){
		try {
			gzmc = java.net.URLDecoder.decode(gzmc, "UTF-8").trim();
			gzid = java.net.URLDecoder.decode(gzid, "UTF-8").trim();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String rtmsg = jdService.gzAdd(gzmc, gzid);
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.getWriter().write(rtmsg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	 
	public void getJdys(){
		List<Object> list = findJdByGzAndDj();
		List<Map<String, Object>> list2 = new ArrayList<Map<String,Object>>();
		if(list!=null){
			Iterator<Object> it = list.iterator();
			while(it.hasNext()){
				Map<String, Object> map = new HashMap<String, Object>();
				Object[] o = (Object[]) it.next();
				map.put("id", o[0]);
				map.put("xwly", o[1]);
				map.put("jdfw", o[2]);
				map.put("jdd", o[3]);
				if(o.length==4){
					map.put("bl", 0);
				}else{
					map.put("bl", o[4]);
				}
				list2.add(map);
			}
			JSONArray json = JSONArray.fromObject(list2);
			//System.out.println("json"+json);
			HttpServletResponse response = ServletActionContext.getResponse();
			try {
				response.getWriter().write(json.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	public List<Object> findJdByGzAndDj(){
		if(!"".equals(this.dj)){
			int dj = Integer.parseInt(this.dj);
			String job_id = "";
			if(gzid.contains("-")){
				job_id = gzid;
			}else{
				job_id = gzid.substring(0, 1)+"-"+gzid.substring(1, 3)+"-"+gzid.substring(3, 5)+"-"+gzid.substring(5, 7);
				if(!"null".equals(companyBriefCode)&&companyBriefCode!=null&&!"".equals(companyBriefCode)){
					job_id = companyBriefCode.toUpperCase()+"-"+job_id;
				}
			}
			
			//List<Object> list = jdService.findJdByGzAndDj(gzid, dj);
			List<Object> list = jdService.findJdByGzAndDj(job_id, dj);
			return list;
		}else{
			return null;
		}
	}
	/**
	 * 根据工种id获取工种等级
	 */
	public void findGzdjByGzid(){
		//System.out.println(gzid);
		List<Object> list = new ProfessionBean().getDjById_job(gzid);
		JSONArray json = JSONArray.fromObject(list);
		//System.out.println("json"+json);
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 构造鉴定要素树
	 */
	public void treeOfJdys(){
		List<Object> list = findJdByGzAndDj();
		JSONArray json = JSONArray.fromObject(list);
		//System.out.println(json);
		//System.out.println(json.size());
		List<TreeOfJdys> list2 = new ArrayList<TreeOfJdys>();
		List<String> list3 = new ArrayList<String>();
		int id = 0;
		for(int i=0;i<json.size();i++){
			JSONArray json2 = (JSONArray) json.get(i);
			int jdysid = (Integer) json2.get(0);
			String name1 = (String) json2.get(1);
			String name2 = (String) json2.get(2);
			String name3 = (String) json2.get(3);
			
			if(!list3.contains(name1)){//一级不存在
				//添加一级
				TreeOfJdys treeOfJdys = new TreeOfJdys();
				treeOfJdys.setId(++id);
				treeOfJdys.setParentid(0);
				treeOfJdys.setName(name1);
				treeOfJdys.setDj(1);
				list3.add(name1);
				list2.add(treeOfJdys);
				//添加二级
				TreeOfJdys treeOfJdys1 = new TreeOfJdys();
				treeOfJdys1.setParentid(id);
				treeOfJdys1.setId(++id);
				treeOfJdys1.setName(name2);
				treeOfJdys1.setDj(2);
				list3.add(name2);
				list2.add(treeOfJdys1);
				//添加三级
				TreeOfJdys treeOfJdys2 = new TreeOfJdys();
				treeOfJdys2.setParentid(id);
				treeOfJdys2.setId(++id);
				treeOfJdys2.setName(name3);
				treeOfJdys2.setDj(3);
				treeOfJdys2.setJd_id(jdysid);
				list3.add(name3);
				list2.add(treeOfJdys2);
			}else{//一级存在
				Iterator<TreeOfJdys> it2 = list2.iterator();
				boolean y = false;
				while(it2.hasNext()){
					TreeOfJdys t = it2.next();
					if(t.getName().equals(name1)&&t.getDj()==1){
						y = true;
					}
				}
				if(y){//一级真存在
					if(!list3.contains(name2)){//二级不存在
						//添加二级
						TreeOfJdys treeOfJdys1 = new TreeOfJdys();
						Iterator<TreeOfJdys> it = list2.iterator();
						while(it.hasNext()){
							TreeOfJdys t1 = it.next();
							if(t1.getName().equals(name1)&&t1.getDj()==1){
								treeOfJdys1.setParentid(t1.getId());
							}
						}
						treeOfJdys1.setId(++id);
						treeOfJdys1.setName(name2);
						treeOfJdys1.setDj(2);
						list3.add(name2);
						list2.add(treeOfJdys1);
						//添加三级
						TreeOfJdys treeOfJdys2 = new TreeOfJdys();
						treeOfJdys2.setParentid(id);
						treeOfJdys2.setId(++id);
						treeOfJdys2.setName(name3);
						treeOfJdys2.setDj(3);
						treeOfJdys2.setJd_id(jdysid);
						list3.add(name3);
						list2.add(treeOfJdys2);
					}else{//二级存在
						Iterator<TreeOfJdys> it3 = list2.iterator();
						int parentid = 0;
						while(it3.hasNext()){
							TreeOfJdys t3 = it3.next();
							if(t3.getName().equals(name1)&&t3.getDj()==1){
								parentid = t3.getId();
							}
						}
						Iterator<TreeOfJdys> it6 = list2.iterator();
						boolean y2 = false;
						while(it6.hasNext()){
							TreeOfJdys t3 = it6.next();
							if(t3.getName().equals(name2)&&t3.getDj()==2&&t3.getParentid()==parentid){//二级真存在
								y2 = true;
							}
						}
						if(y2){
							if(!list3.contains(name3)){//三级不存在
								TreeOfJdys treeOfJdys = new TreeOfJdys();
								Iterator<TreeOfJdys> it = list2.iterator();
								int id1 = 0;
								int id2 = 0;
								while(it.hasNext()){
									TreeOfJdys t4 = it.next();
									if(t4.getName().equals(name1)&&t4.getDj()==1){
										id1 = t4.getId();
									}
								}
								Iterator<TreeOfJdys> it4 = list2.iterator();
								while(it4.hasNext()){
									TreeOfJdys t4 = it4.next();
									if(t4.getParentid()==id1&&t4.getName().equals(name2)){
										id2 = t4.getId();
									}
								}
								treeOfJdys.setParentid(id2);
								treeOfJdys.setId(++id);
								treeOfJdys.setName(name3);
								treeOfJdys.setDj(3);
								treeOfJdys.setJd_id(jdysid);
								list3.add(name3);
								list2.add(treeOfJdys);
							}else{//三级存在
								TreeOfJdys treeOfJdys = new TreeOfJdys();
								Iterator<TreeOfJdys> it = list2.iterator();
								int id1 = 0;
								int id2 = 0;
								while(it.hasNext()){
									TreeOfJdys t4 = it.next();
									if(t4.getName().equals(name1)&&t4.getDj()==1){
										id1 = t4.getId();
									}
								}
								Iterator<TreeOfJdys> it4 = list2.iterator();
								while(it4.hasNext()){
									TreeOfJdys t4 = it4.next();
									if(t4.getParentid()==id1&&t4.getName().equals(name2)){
										id2 = t4.getId();
									}
								}
								Iterator<TreeOfJdys> it5 = list2.iterator();
								boolean y3 = false;
								while(it5.hasNext()){
									TreeOfJdys t4 = it5.next();
									if(t4.getName().equals(name3)&&t4.getParentid()==id2){//三级真存在
										y3 = true;
									}else{//三级假存在
										y3= false;
									}
								}
								if(!y3){
									treeOfJdys.setParentid(id2);
									treeOfJdys.setId(++id);
									treeOfJdys.setName(name3);
									treeOfJdys.setDj(3);
									treeOfJdys.setJd_id(jdysid);
									list3.add(name3);
									list2.add(treeOfJdys);
								}
							}
						}else{//二级假存在
							TreeOfJdys treeOfJdys = new TreeOfJdys();
							Iterator<TreeOfJdys> it4 = list2.iterator();
							while(it4.hasNext()){
								TreeOfJdys t4 = it4.next();
								if(t4.getName().equals(name1)&&t4.getDj()==1){
									treeOfJdys.setParentid(t4.getId());
								}
							}
							treeOfJdys.setId(++id);
							treeOfJdys.setName(name2);
							treeOfJdys.setDj(2);
							list3.add(name2);
							list2.add(treeOfJdys);
							
							TreeOfJdys treeOfJdys1 = new TreeOfJdys();
							treeOfJdys1.setParentid(id);
							treeOfJdys1.setId(++id);
							treeOfJdys1.setName(name3);
							treeOfJdys1.setDj(3);
							treeOfJdys1.setJd_id(jdysid);
							list3.add(name3);
							list2.add(treeOfJdys1);
						}
					}
				}else{//一级假存在
					TreeOfJdys treeOfJdys = new TreeOfJdys();
					treeOfJdys.setId(id);
					treeOfJdys.setParentid(0);
					treeOfJdys.setName(name1);
					treeOfJdys.setDj(1);
					list3.add(name1);
					list2.add(treeOfJdys);
					
					TreeOfJdys treeOfJdys1 = new TreeOfJdys();
					treeOfJdys1.setParentid(id);
					treeOfJdys1.setId(++id);
					treeOfJdys1.setName(name2);
					treeOfJdys1.setDj(2);
					list3.add(name2);
					list2.add(treeOfJdys1);
					
					TreeOfJdys treeOfJdys2 = new TreeOfJdys();
					treeOfJdys2.setParentid(id);
					treeOfJdys2.setId(++id);
					treeOfJdys2.setName(name3);
					treeOfJdys2.setDj(3);
					treeOfJdys2.setJd_id(jdysid);
					list3.add(name3);
					list2.add(treeOfJdys2);
				}
			}
		}
		
		JSONArray json2 = JSONArray.fromObject(list2);
		//System.out.println("--------"+json2);
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.getWriter().write(json2.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void saveOrUpdateJdys(){
		JSONArray array = JSONArray.fromObject(data);
		//JSONArray array_d = JSONArray.fromObject(data_d);
		String job_id = id_job.substring(0, 1)+"-"+id_job.substring(1, 3)+"-"+id_job.substring(3, 5)+"-"+id_job.substring(5, 7);
		if(!"null".equals(companyBriefCode)&&companyBriefCode!=null&&!"".equals(companyBriefCode)){
			job_id = companyBriefCode.toUpperCase()+"-"+job_id;
		}
		//System.out.println(array);
		//System.out.println(data.toString());
		JSONArray jsa = (JSONArray) array.get(0);
		Map<String, Object> map = new HashMap<String, Object>();
		for(int i=0;i<jsa.size();i++){
			map = jsa.getJSONObject(i);
			//map = array.getJSONObject(i);
			Tdjobexamdot t = new Tdjobexamdot();
			if("1".equals(state)){
				int id = (Integer)map.get("id");
				if(id!=0){
					t = jdService.findJdysById(id);
				}else{
					t.setCcz137(job_id);
				}
				//t.setJdysid(Long.valueOf((Integer)map.get("id")));
			}else{
				t.setCcz137(job_id);
			}
			t.setL1name((String)map.get("xwly"));
			t.setL2name((String)map.get("jdfw"));
			t.setDotname((String)map.get("jdd"));
			//t.setAaa131("0");
			Integer bl = (Integer) map.get("bl");
			//int bl = Integer.parseInt(s);
			if(dj.equals("一级")){
				t.setRank1pent(bl);
			}else if(dj.equals("二级")){
				t.setRank2pent(bl);
			}else if(dj.equals("三级")){
				t.setRank3pent(bl);
			}else if(dj.equals("四级")){
				t.setRank4pent(bl);
			}else if(dj.equals("五级")){
				t.setRank5pent(bl);
			}else if(dj.equals("专项")){
				t.setSpecialpent(bl);
			}
			t.setAaa131("1");
			jdService.saveOrUpdateJdys(t);
		}
		if("1".equals(state)){
			if(data_d!=null){
				jdService.changeState(data_d);
			}
		}
		String jobsubjectname = "";
		if(!"null".equals(companyForShort)&&companyForShort!=null&&!"".equals(companyForShort)){
			jobsubjectname = companyForShort+gzmc+dj;
		}else{
			jobsubjectname = gzmc+dj;
		}
		List<Tjobsubject> list = jdService.findJdysByJobsubjectname(jobsubjectname);
		if(!(list.size()>0)){
			saveOrUpdateTk(job_id);
		}
		data = null;
		data_d = null;
		companyForShort="";
		companyBriefCode=null;
		//saveOrUpdateTk(id_job);
		/*for(Map.Entry<String, String> entry:map.entrySet()){
			System.out.println(entry.);
		}*/
		//System.out.println(array);
	}
	@SuppressWarnings("static-access")
	public void saveOrUpdateTk(String job_id){
		Tjobsubject t = new Tjobsubject();
		AcegiUtil acegiUtil = new AcegiUtil();
		String userName= ((UserDetailsImpl) acegiUtil.getUserDetails()).getUser().getRealname();
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String time = format.format(date);
		t.setId_job(job_id);
		t.setJobname(companyForShort+gzmc);
		t.setRankname(dj);
		t.setJobsubjectname(companyForShort+gzmc+dj);
		t.setSubjecttablename(companyForShort);
		t.setCategory(tkCategory);
		t.setCreator(userName);
		t.setCreatedate(time);
		t.setIsreadonly(new Long(0));
		t.setHaspersonal(new Long(0));
		jdService.saveOrUpdateTk(t);
	}

	/**
	 * 单独录入工种信息（无鉴定要素关联-抽卷组卷）
	 * @throws IOException
	 */
	public void tkAdd() throws IOException{
		String job_id = id_job.substring(0, 1)+"-"+id_job.substring(1, 3)+"-"+id_job.substring(3, 5)+"-"+id_job.substring(5, 7);
		this.gzmc = java.net.URLDecoder.decode(gzmc , "UTF-8");
		this.dj = java.net.URLDecoder.decode(dj , "UTF-8");
		this.tkCategory = java.net.URLDecoder.decode(tkCategory , "UTF-8");
		this.companyForShort = java.net.URLDecoder.decode(companyForShort , "UTF-8");
		String state = "";
		String jobSubjectName = "";
		if("1".equals(tkCategory)||"2".equals(tkCategory)||"3".equals(tkCategory)||"5".equals(tkCategory)){
			jobSubjectName = gzmc+dj;
		}else{
			jobSubjectName = companyForShort+gzmc+dj;
			job_id = companyBriefCode.toUpperCase()+"-"+job_id;
		}
		/*if("4".equals(tkCategory)||"6".equals(tkCategory)||"7".equals(tkCategory)||"8".equals(tkCategory)){
			jobSubjectName = companyForShort+gzmc+dj;
			job_id = companyBriefCode.toUpperCase()+"-"+job_id;
		}else{
			jobSubjectName = gzmc+dj;
		}*/
		List<Tjobsubject> list = new ProfessionBean().getListByJobsubjectName(jobSubjectName);
		if(list.size()>0){
			state = "repeat";
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		if(!"repeat".equals(state)){
			try {
				saveOrUpdateTk(job_id);
				state = SUCCESS;
			} catch (Exception e) {
				state = ERROR;
				e.printStackTrace();
			}
		}
		try {
			response.getWriter().write(state);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 新增题库类别
	 */
	public void tkCategoryAdd(){
		Tkcategory t = new Tkcategory();
		try {
			tkmc = java.net.URLDecoder.decode(tkmc , "UTF-8");
			yjtk = java.net.URLDecoder.decode(yjtk , "UTF-8");
			remark = java.net.URLDecoder.decode(remark , "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		t.setName(tkmc.trim());
		t.setParentid(Integer.parseInt(yjtk));
		t.setOperatorid(AcegiUtil.getEUser().getId());
		t.setAddtime(new Timestamp(new Date().getTime()));
		t.setRemark(remark.trim());
		t.setFlag("0");
		String reult = jdService.tkCategoryAdd(t);
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.getWriter().write(reult);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String jdysBatchAdd(){
		return SUCCESS;
	}

	public String getGzid() {
		return gzid;
	}

	public void setGzid(String gzid) {
		this.gzid = gzid;
	}

	public String getDj() {
		return dj;
	}

	public void setDj(String dj) {
		this.dj = dj;
	}

	public void setJdService(JdService jdService) {
		this.jdService = jdService;
	}


	public String getGzmc() {
		return gzmc;
	}

	public void setGzmc(String gzmc) {
		this.gzmc = gzmc;
	}

	public String getId_job() {
		return id_job;
	}

	public void setId_job(String id_job) {
		this.id_job = id_job;
	}
	public JSONArray getJson() {
		return json;
	}
	public void setJson(JSONArray json) {
		this.json = json;
	}
	public String[] getData_d() {
		return data_d;
	}
	public void setData_d(String[] data_d) {
		this.data_d = data_d;
	}
	public void setData(String[] data) {
		this.data = data;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public File getJdysFile() {
		return jdysFile;
	}
	public void setJdysFile(File jdysFile) {
		this.jdysFile = jdysFile;
	}
	public String getTkCategory() {
		return tkCategory;
	}
	public void setTkCategory(String tkCategory) {
		this.tkCategory = tkCategory;
	}
	public String getCompanyForShort() {
		return companyForShort;
	}
	public void setCompanyForShort(String companyForShort) {
		this.companyForShort = companyForShort;
	}
	public String getCompanyBriefCode() {
		return companyBriefCode;
	}
	public void setCompanyBriefCode(String companyBriefCode) {
		this.companyBriefCode = companyBriefCode;
	}
	public String getYjtk() {
		return yjtk;
	}
	public void setYjtk(String yjtk) {
		this.yjtk = yjtk;
	}
	public String getTkmc() {
		return tkmc;
	}
	public void setTkmc(String tkmc) {
		this.tkmc = tkmc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}

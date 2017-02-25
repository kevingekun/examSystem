package com.wondersgroup.falcon.beans.auth;

import java.text.CollationKey;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.hibernate.Session;

import com.wondersgroup.falcon.dao.auth.ProfessionDAO;
import com.wondersgroup.falcon.paper.model.EPapers;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;
import com.wondersgroup.kaoshi.bo.EPapersSet;
import com.wondersgroup.kaoshi.bo.EPapersSetVo;
import com.wondersgroup.kaoshi.bo.Tjobsubject;
/*import org.hibernate.engine.Collections;
*/
import com.wondersgroup.kaoshi.bo.Tkcategory;

public class ProfessionBean {

	//private static final Log log = LogFactory.getLog(UserTeamBean.class);
	/**
	 * 获取工种表所有信息
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Tjobsubject> getAllProfessions(){
		return (List<Tjobsubject>)HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				List<Tjobsubject> list = null;
				ProfessionDAO professionDAO = new ProfessionDAO();
				list = professionDAO.findAll();
				return list;
			}
		});
	}
	/**
	 * 获取工种表所有有整套试卷的工种信息
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Tjobsubject> getAllProfessionsWithPapers(){
		return (List<Tjobsubject>)HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				List<Tjobsubject> list = null;
				ProfessionDAO professionDAO = new ProfessionDAO();
				list = professionDAO.findAllWithPapers();
				return list;
			}
		});
	}
	/**
	 * 获取整套试卷工种表所有信息
	 * @return
	 */
		@SuppressWarnings("unchecked")
		private List<Tjobsubject> getAllztgz() {
			return (List<Tjobsubject>)HibernateUtil.doInSession(new HibernateSessionCallback() {
				public Object execute(Session session) throws Throwable {
					List<Tjobsubject> list = null;
					ProfessionDAO professionDAO = new ProfessionDAO();
					list = professionDAO.findztgz();
					return list;
				}
			});
		}
	/**
	 * 获取工种名称 唯一 的工种集合
	 * @return
	 */
	public List<Tjobsubject> getDistinctProfessions() {
		List<Tjobsubject> tjobsubject = new ArrayList<Tjobsubject>();
		//Map<String, Tjobsubject> map = new TreeMap<String, Tjobsubject>();
		final Collator collator = Collator.getInstance();
        TreeMap<String, Tjobsubject> map = new TreeMap<String, Tjobsubject>(new Comparator<Object>() {
            public int compare(Object o1, Object o2) {//工种名称排序
                 //如果有空值，直接返回0
                  if (o1 == null || o2 == null)
                        return 0;
                CollationKey ck1 = collator.getCollationKey(String.valueOf(o1));
                CollationKey ck2 = collator.getCollationKey(String.valueOf(o2));
                return ck1.compareTo(ck2);              
          }
        });
		Iterator<Tjobsubject> it = getAllProfessions().iterator();//iterator迭代器   getAllProfessions工种所有
		while(it.hasNext()){
			Tjobsubject t = it.next();
			map.put(t.getJobname(),t);//检查map key唯一 工种名称 工种信息
		}
        Iterator<Entry<String, Tjobsubject>> it2 = map.entrySet().iterator();//通过Map.entrySet使用iterator遍历key和value：
		while(it2.hasNext()){
			Map.Entry<String, Tjobsubject> entry = (Entry<String, Tjobsubject>) it2.next();
			tjobsubject.add(entry.getValue());
		}
		return tjobsubject;
	}
	/**
	 * 获取工种名称 唯一 的含有整套试卷的工种集合
	 * @return
	 */
	public List<Tjobsubject> getProfessionsWithPapers() {
		List<Tjobsubject> tjobsubject = new ArrayList<Tjobsubject>();
		//Map<String, Tjobsubject> map = new TreeMap<String, Tjobsubject>();
		final Collator collator = Collator.getInstance();
        TreeMap<String, Tjobsubject> map = new TreeMap<String, Tjobsubject>(new Comparator<Object>() {
            public int compare(Object o1, Object o2) {//工种名称排序
                 //如果有空值，直接返回0
                  if (o1 == null || o2 == null)
                        return 0;
                CollationKey ck1 = collator.getCollationKey(String.valueOf(o1));
                CollationKey ck2 = collator.getCollationKey(String.valueOf(o2));
                return ck1.compareTo(ck2);              
          }
        });
		Iterator<Tjobsubject> it = getAllProfessionsWithPapers().iterator();//iterator迭代器   getAllProfessions工种所有
		while(it.hasNext()){
			Tjobsubject t = it.next();
			map.put(t.getJobname(),t);//检查map key唯一 工种名称 工种信息
		}
        Iterator<Entry<String, Tjobsubject>> it2 = map.entrySet().iterator();//通过Map.entrySet使用iterator遍历key和value：
		while(it2.hasNext()){
			Map.Entry<String, Tjobsubject> entry = (Entry<String, Tjobsubject>) it2.next();
			tjobsubject.add(entry.getValue());
		}
		return tjobsubject;
	}
	
	
	/**
	 * 获取机考整套试卷工种名称 唯一 的工种集合
	 * @return 
	 */
		public List<Tjobsubject> getztgz() {
		List<Tjobsubject> tjobsubject = new ArrayList<Tjobsubject>();
		//Map<String, Tjobsubject> map = new TreeMap<String, Tjobsubject>();
		final Collator collator = Collator.getInstance();
        TreeMap<String, Tjobsubject> map = new TreeMap<String, Tjobsubject>(new Comparator<Object>() {
            public int compare(Object o1, Object o2) {//工种名称排序
                 //如果有空值，直接返回0
                  if (o1 == null || o2 == null)
                        return 0;
                CollationKey ck1 = collator.getCollationKey(String.valueOf(o1));
                CollationKey ck2 = collator.getCollationKey(String.valueOf(o2));
                return ck1.compareTo(ck2);
          }
        });
		Iterator<Tjobsubject> it = getAllztgz().iterator();//iterator迭代器   getAllProfessions工种所有
		while(it.hasNext()){
			Tjobsubject t = it.next();
			map.put(t.getJobname(),t);//检查map key唯一 工种名称 工种信息
		}
        Iterator<Entry<String, Tjobsubject>> it2 = map.entrySet().iterator();//通过Map.entrySet使用iterator遍历key和value：
		while(it2.hasNext()){
			Map.Entry<String, Tjobsubject> entry = (Entry<String, Tjobsubject>) it2.next();
			tjobsubject.add(entry.getValue());
		}
		return tjobsubject;
	}

		/**
		 * 获取笔试整套试卷工种名称 唯一 的工种集合
		 * @return 
		 */	
		
		public List<Tjobsubject> getztgz_bs() {
			List<Tjobsubject> tjobsubject = new ArrayList<Tjobsubject>();
			//Map<String, Tjobsubject> map = new TreeMap<String, Tjobsubject>();
			final Collator collator = Collator.getInstance();
	        TreeMap<String, Tjobsubject> map = new TreeMap<String, Tjobsubject>(new Comparator<Object>() {
	            public int compare(Object o1, Object o2) {//工种名称排序
	                 //如果有空值，直接返回0
	                  if (o1 == null || o2 == null)
	                        return 0;
	                CollationKey ck1 = collator.getCollationKey(String.valueOf(o1));
	                CollationKey ck2 = collator.getCollationKey(String.valueOf(o2));
	                return ck1.compareTo(ck2);              
	          }
	        });
			Iterator<Tjobsubject> it = getAllztgz_bs().iterator();//iterator迭代器   getAllProfessions工种所有
			while(it.hasNext()){
				Tjobsubject t = it.next();
				map.put(t.getJobname(),t);//检查map key唯一 工种名称 工种信息
			}
	        Iterator<Entry<String, Tjobsubject>> it2 = map.entrySet().iterator();//通过Map.entrySet使用iterator遍历key和value：
			while(it2.hasNext()){
				Map.Entry<String, Tjobsubject> entry = (Entry<String, Tjobsubject>) it2.next();
				tjobsubject.add(entry.getValue());
			}
			return tjobsubject;
		}
	@SuppressWarnings("unchecked")
	private List<Tjobsubject> getAllztgz_bs() {
		return (List<Tjobsubject>)HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				List<Tjobsubject> list = null;
				ProfessionDAO professionDAO = new ProfessionDAO();
				list = professionDAO.findztgz_bs();
				return list;
			}
		});
	
		
		
		
		
	}
	/**
	 * 根据工种id获取对应的等级
	 * @param s
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object> getDjById_job(final String s){
		return (List<Object>)HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				List<Object> list = null;
				ProfessionDAO professionDAO = new ProfessionDAO();
				list = professionDAO.findDjById_job(s);
				return list;
			}
		});
	}
	@SuppressWarnings("unchecked")
	public List<Tjobsubject> getListByJobsubjectName(final String name){
		return (List<Tjobsubject>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			
			public Object execute(Session session) throws Throwable {
				ProfessionDAO professionDAO = new ProfessionDAO();
				List<Tjobsubject> list = professionDAO.getListByJobsubjectName(name);
				return list;
			}
		});
	}
	@SuppressWarnings("unchecked")
	public List<Object> getztdj(final String s, final int flag ){
		return (List<Object>)HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				List<Object> list = null;
				ProfessionDAO professionDAO = new ProfessionDAO();
				list = professionDAO.findztdj(s,flag);
				return list;
			}
		});
	}
	public List<EPapers> getNoUsePaper(){
		ProfessionDAO professionDAO = new ProfessionDAO();
		return professionDAO.getNoUsePaper();
	}
	/**
	 * 获取没有上传过的符合条件的试卷
	 * @return
	 */
	public List<EPapers> getUnUploadPapersOnPaper(){
		ProfessionDAO professionDAO = new ProfessionDAO();
		return professionDAO.getUnUploadPapersOnPaper();
	}
	public List<EPapers> getUnUploadPapersOnPaper_xf(){
		ProfessionDAO professionDAO = new ProfessionDAO();
		return professionDAO.getUnUploadPapersOnPaper_xf();
	}
	/**
	 * 获取没上传成绩的试卷
	 * @return
	 */
	public List<EPapers> getUnUploadPapers(){
		ProfessionDAO professionDAO = new ProfessionDAO();
		return professionDAO.getUnUploadPapers();
	}
	/**
	 * 获取没上传鉴定中心成绩的试卷
	 * @return
	 */
	public List<EPapers> getUnUploadToJdzxPapers(){
		ProfessionDAO professionDAO = new ProfessionDAO();
		return professionDAO.getUnUploadToJdzxPapers();
	}
	
	public List<EPapers> getExamingPapers(){
		ProfessionDAO professionDAO = new ProfessionDAO();
		return professionDAO.getExamingPapers();
	}
	
	public Tjobsubject findTjobsubjectBygzid(final int id){
		return (Tjobsubject) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				ProfessionDAO professionDAO = new ProfessionDAO();
				return professionDAO.findById(id);
			}
		});
	}
	/**
	 * 获取整套试卷信息
	 * @return
	 */
	public List<EPapersSetVo> geteEPapersSets(int sjType){
		ProfessionDAO professionDAO = new ProfessionDAO();
		return professionDAO.getEPapersSets(sjType);
	}
	/**
	 * 根据试卷id获取试卷
	 * @param sjid
	 * @return
	 */
	public EPapersSet getPapersBySjid(String sjid){
		ProfessionDAO professionDAO = new ProfessionDAO();
		return professionDAO.getEPapersBySjid(sjid);
	}
	/**
	 * 获取一级题库类别
	 * @return
	 */
	public List<Tkcategory> getYjTkcategories(){
		ProfessionDAO professionDAO = new ProfessionDAO();
		return professionDAO.getYjTkcategories();
	}
	/**
	 * 获取所有题库类别
	 * @return
	 */
	public List<Tkcategory> getAllTkcategories(){
		ProfessionDAO professionDAO = new ProfessionDAO();
		return professionDAO.getAllTkcategories();
	}
	/**
	 * 获取所有叶子节点题库
	 * @return
	 */
	public List<Tkcategory> getAllLeafcategories(){
		ProfessionDAO professionDAO = new ProfessionDAO();
		return professionDAO.getAllLeafcategories();
	}
}

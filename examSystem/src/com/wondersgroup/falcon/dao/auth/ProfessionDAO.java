package com.wondersgroup.falcon.dao.auth;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.mira.lucene.analysis.e;

import schemasMicrosoftComOfficeOffice.CTIdMap;

import com.sun.tools.javac.v8.tree.Tree.NewArray;
import com.wondersgroup.falcon.paper.model.EPapers;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;
import com.wondersgroup.framework.core5.util.parameter.types.LONG;
import com.wondersgroup.kaoshi.bo.EPapersSet;
import com.wondersgroup.kaoshi.bo.EPapersSetVo;
import com.wondersgroup.kaoshi.bo.Tjobsubject;
import com.wondersgroup.kaoshi.bo.Tkcategory;
import com.wondersgroup.popedom.bo.HZ95;

public class ProfessionDAO {

	//private static final Log log = LogFactory.getLog(ProfessionDAO.class);
	
	@SuppressWarnings("unchecked")
	public List<Tjobsubject> findAll(){
		return (List<Tjobsubject>)HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String queryString = "from Tjobsubject t order by t.id";
				Query queryObject = session.createQuery(queryString);
                return queryObject.list();
			}
		});
	}
	/**
	 * 获取所有有整套试卷的工种
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Tjobsubject> findAllWithPapers(){
		return (List<Tjobsubject>)HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String sql = "select a.* from TJOBSUBJECT a where a.id in (select distinct(b.sj_tkid) from e_papers_set b where b.sj_state=0) order by a.id";
				Query query = session.createSQLQuery(sql).addEntity(Tjobsubject.class);
				return query.list();
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> findDjById_job(final String s){
		return (List<Object>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String query = "from Tjobsubject t where t.id_job='"+s+"' order by t.rankname";
				Query q = session.createQuery(query);
				List<Tjobsubject> list = q.list();
				List<Object> list2  = new ArrayList<Object>();
				Iterator<Tjobsubject> it = list.iterator();
				while (it.hasNext()) {
					List<Object> list3  = new ArrayList<Object>();
					Tjobsubject t = it.next();
					String s = t.getRankname();
					long id = t.getId();
					String value = "";
					if(s.equals("一级")){
						value="1";
					}else if (s.equals("二级")) {
						value="2";
					}else if (s.equals("三级")) {
						value="3";
					}else if (s.equals("四级")) {
						value="4";
					}else if (s.equals("五级")) {
						value="5";
					}else if (s.equals("专项")) {
						value="6";
					}
					list3.add(value);
					list3.add(s);
					list3.add(id);
					list2.add(list3);
				}
				return list2;
			}
		});
	}
	
	public Tjobsubject findById(final int id){
		return (Tjobsubject) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String querString = "from Tjobsubject t where t.id="+id;
				Query query = session.createQuery(querString);
				return query.list().get(0);
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public List<EPapers> getNoUsePaper(){
		return (List<EPapers>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String sql = "from EPapers e where e.sjZt = 6 and e.sjId not in (select h.sj_id from HZ95 h where nvl(h.sj_id,0)!=0) order by e.sjMc";
				Query query = session.createQuery(sql);
				return query.list();
			}
		});
	}
	/**
	 * 获取所有没有上传过的 符合上传条件的试卷
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<EPapers> getUnUploadPapersOnPaper(){
		return (List<EPapers>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			
			public Object execute(Session session) throws Throwable {
				/*Criteria criteria = session.createCriteria(EPapers.class);
				criteria.add(Restrictions.eq("sjZt", new Long(6)))
				.add(Restrictions.eq("sjScyth", new Long(0)));*/
				
				String sql = "select * from e_papers e where e.sj_zt=6 and e.sj_scyth=0 and e.sj_id not in (select distinct t.sj_id from qdyth.e_paper_team@QDYTH t where t.flag=0) order by e.sj_mc asc";
				Query query = session.createSQLQuery(sql).addEntity(EPapers.class);
				return query.list();
			}
		});
	}
	/**
	 * 获取所有没有上传成绩的试卷
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<EPapers> getUnUploadPapers(){
		return (List<EPapers>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String sql = "from EPapers e where e.sjKslx='1' and e.sjZt = 2 and e.sjCjgbzt = 0 order by e.sjMc";
				Query query = session.createQuery(sql);
				return query.list();
			}
		});
	}
	/**
	 * 获取所有没有上传鉴定中心成绩的试卷
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<EPapers> getUnUploadToJdzxPapers(){
		return (List<EPapers>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String sql = "from EPapers e where e.sjKslx='1' and e.sjZt = 2 and e.sjCjhc = 0 order by e.sjMc";
				Query query = session.createQuery(sql);
				return query.list();
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public List<EPapers> getExamingPapers(){
		return (List<EPapers>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String sql = "from EPapers e where e.sjZt = 6 and e.sjId in (select h.sj_id from HZ95 h where nvl(h.sj_id,0)!=0) order by e.sjMc";
				Query query = session.createQuery(sql);
				return query.list();
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public List<EPapersSetVo> getEPapersSets(final int sjType){
		return (List<EPapersSetVo>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				/*Criteria criteria = session.createCriteria(EPapersSet.class);
				criteria.add(Restrictions.eq("sj_state", 0));
				criteria.addOrder(Order.asc("sj_mc"));*/
				List<EPapersSet> eList = new ArrayList<EPapersSet>();
				String sql = "";
				if(sjType==3){
					sql = "select a.*, nvl(c.nm,0) from e_papers_set a " +
							"left join (select b.st_sjid as sj_id, count(b.st_id) as nm from E_QUESTIONS b where b.st_scbz=0 group by b.st_sjid) c " +
							"on c.sj_id = to_char(a.sj_id) where a.sj_state=0 order by a.sj_mc";
				}else{
					sql = "select a.*, nvl(c.nm,0) from e_papers_set a " +
							"left join (select b.st_sjid as sj_id, count(b.st_id) as nm from E_QUESTIONS b where b.st_scbz=0 group by b.st_sjid) c " +
							"on c.sj_id = to_char(a.sj_id) where a.sj_state=0 and a.sj_type="+sjType+"order by a.sj_mc";
				}
				Query query = session.createSQLQuery(sql);
				List<Object[]> list = query.list();
				Iterator<Object[]> iterator = list.iterator();
				while(iterator.hasNext()){
					EPapersSetVo ePapersSet = new EPapersSetVo();
					Object[] o = iterator.next();
					ePapersSet.setSj_id(((BigDecimal)o[0]).intValue());
					ePapersSet.setSj_mc((String)o[1]);
					ePapersSet.setSj_zf(((BigDecimal) o[2]).doubleValue());
					ePapersSet.setSj_zjsj((Date) o[3]);
					ePapersSet.setSj_zjrid((String) o[4]);
					ePapersSet.setSj_tkid((String) o[5]);
					ePapersSet.setSj_state(((BigDecimal) o[6]).intValue());
					ePapersSet.setQuestionNum(((BigDecimal) o[8]).toString());
					eList.add(ePapersSet);
				}
				return eList;
			}
		});
	}
//机考工种
	@SuppressWarnings("unchecked")
	public List<Tjobsubject> findztgz() {
		return (List<Tjobsubject>)HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String sql = "from Tjobsubject e where e.id in (select r.sj_tkid from EPapersSet r where r.sj_state=0  and sj_type=1 )";
				Query query = session.createQuery(sql);
				List<Tjobsubject> list = query.list();
                return list;
			}
		});
	}
	
	

//机考工种等级
	@SuppressWarnings("unchecked")
	public List<Object> findztdj(final String s , final int flag) {
		return (List<Object>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String query = "from Tjobsubject e where e.id in(select r.sj_tkid  from EPapersSet r " +
						" where r.sj_tkid in(select e.id from Tjobsubject e where e.id_job='"+s+"') and sj_type = '"+flag+"') ";
				Query q = session.createQuery(query);
				List<Tjobsubject> list = q.list();
				List<Object> list2  = new ArrayList<Object>();
				Iterator<Tjobsubject> it = list.iterator();
				while (it.hasNext()) {
					List<Object> list3  = new ArrayList<Object>();
					Tjobsubject t = it.next();
					String s = t.getRankname();
					long id = t.getId();
					String value = "";
					if(s.equals("一级")){
						value="1";
					}else if (s.equals("二级")) {
						value="2";
					}else if (s.equals("三级")) {
						value="3";
					}else if (s.equals("四级")) {
						value="4";
					}else if (s.equals("五级")) {
						value="5";
					}else if (s.equals("专项")) {
						value="6";
					}
					list3.add(value);
					list3.add(s);
					list3.add(id);
					list2.add(list3);
				}
				return list2;
			}
		});
	
	}
	//机考试卷
	@SuppressWarnings("unchecked")
	public List<Object> getEPapers_set1(final String gzid ,final String dj, final String flag1){
		return (List<Object>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String s=dj;
				String value = "";
				if(s.equals("1")){
					value="一级";
				}else if (s.equals("2")) {
					value="二级";
				}else if (s.equals("3")) {
					value="三级";
				}else if (s.equals("4")) {
					value="四级";
				}else if (s.equals("5")) {
					value="五级";
				}else if (s.equals("6")) {
					value="专项";
				}
				String sql = "select * from (select r.sj_id,r.sj_mc,r.sj_zf from e_Papers_Set r  where r.sj_type='"+flag1+"' and r.sj_tkid =(select w.id from " +
							"Tjobsubject w where w.id_job='"+gzid+"' and w.rankname='"+value+"')order by dbms_random.value) where rownum=1";
				Query query = session.createSQLQuery(sql);
				List<Object[]> list = query.list();
				return list;
			}
		});
	}
//笔试 工种
	@SuppressWarnings("unchecked")
	public List<Tjobsubject> findztgz_bs() {
		return (List<Tjobsubject>)HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String sql = "from Tjobsubject e where e.id in (select r.sj_tkid from EPapersSet r where r.sj_state=0  and sj_type=2 )";
				Query query = session.createQuery(sql);
				List<Tjobsubject> list = query.list();
                return list;
			}
		});
	}
	
	public EPapersSet getEPapersBySjid(final String sjid){
		return (EPapersSet) HibernateUtil.doInSession(new HibernateSessionCallback() {
			
			public Object execute(Session session) throws Throwable {
				Criteria criteria = session.createCriteria(EPapersSet.class);
				criteria.add(Restrictions.eq("sj_id", Long.valueOf(sjid)));
				if(criteria.list().size()>0){
					return criteria.list().get(0);
				}
				return null;
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public List<Tjobsubject> getListByJobsubjectName(final String name){
		return (List<Tjobsubject>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			
			public Object execute(Session session) throws Throwable {
				Criteria criteria = session.createCriteria(Tjobsubject.class)
						.add(Restrictions.eq("jobsubjectname", name));
				return criteria.list();
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<EPapers> getUnUploadPapersOnPaper_xf() {
		return (List<EPapers>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String sql = "select * from e_papers  ui where ui.sj_zt=6  and sj_id not in(select  distinct sj_id from e_paper_team_xf)" +
						"and sj_id  in (select distinct sj_id from hz95 where aaa131='1')";
				Query query = session.createSQLQuery(sql).addEntity(EPapers.class);
				return query.list();
			}
		});
	}
	/**
	 * 获取一级题库类别
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Tkcategory> getYjTkcategories(){
		return (List<Tkcategory>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			
			public Object execute(Session session) throws Throwable {
				Criteria criteria = session.createCriteria(Tkcategory.class)
						.add(Restrictions.eq("flag", "0"))
						.add(Restrictions.eq("parentid", 0))
						.addOrder(Order.asc("id"));
				return criteria.list();
			}
		});
	}
	/**
	 * 获取所有题库类别
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Tkcategory> getAllTkcategories(){
		return (List<Tkcategory>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			
			public Object execute(Session session) throws Throwable {
				Criteria criteria = session.createCriteria(Tkcategory.class)
						.add(Restrictions.eq("flag", "0"))
						.addOrder(Order.asc("id"));
				return criteria.list();
			}
		});
	}
	/**
	 * 获取所有叶子节点题库
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Tkcategory> getAllLeafcategories(){
		return (List<Tkcategory>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			
			public Object execute(Session session) throws Throwable {
				String sql = "select * from TKCATEGORY a where a.id not in (select distinct parentid from TKCATEGORY where parentid!=0)";
				Query query = session.createSQLQuery(sql).addEntity(Tkcategory.class);
				
				/*Criteria criteria = session.createCriteria(Tkcategory.class)
						.add(Restrictions.eq("flag", "0"))
						.addOrder(Order.asc("id"));*/
				return query.list();
			}
		});
	}
}

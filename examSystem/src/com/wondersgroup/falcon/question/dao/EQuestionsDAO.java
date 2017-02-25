package com.wondersgroup.falcon.question.dao;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import com.wondersgroup.falcon.exceptions.DAOException;
import com.wondersgroup.falcon.exceptions.InfrastructureException;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;
import com.wondersgroup.falcon.question.model.*;
import com.wondersgroup.kaoshi.bo.Ae02;
import com.wondersgroup.kaoshi.bo.Tjobsubject;

/**
 * A data access object (DAO) providing persistence and search support for
 * EQuestions entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.wondersgroup.falcon.question.model.EQuestions
 * @author MyEclipse Persistence Tools
 */

public class EQuestionsDAO  {

	private static final Log log = LogFactory.getLog(EQuestionsDAO.class);


	// property constants
	public static final String ST_TG = "stTg";
	public static final String ST_XX = "stXx";
	public static final String ST_DA = "stDa";
	public static final String ST_DASM = "stDasm";
	public static final String ST_FJLJ = "stFjlj";
	public static final String ST_KSZY = "stKszy";
	public static final String ST_MTRY_ID = "stMtryId";
	public static final String ST_WJCC = "stWjcc";
	public static final String ST_WH = "stWh";
	public static final String ST_JYXGCS = "stJyxgcs";
	public static final String ST_SCBZ = "stScbz";

 
	public void saveOrUpdate(final EQuestions transientInstance)
			throws Exception {
		        HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				log.debug("saving EQuestions instance");
				try {
					session.saveOrUpdate(transientInstance);
					log.debug("save successful");
				} catch (RuntimeException re) {

					log.error("save failed", re);
					throw re;
				}
				return null;
			}
		});
		}
 
	public void saveOrUpdate_tmdot(final Tmdot tmdot)
			throws Exception {
		        HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				log.debug("saving Tmdot instance");
				try {
					session.saveOrUpdate(tmdot);
					log.debug("save successful");
				} catch (RuntimeException re) {
					log.error("save failed", re);
					throw re;
				} 
				return null;
			}
		});
		}
 
	public void saveOrUpdate_ae02(final Ae02 ae02) throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				log.debug("saving Ae02 instance");
				try {
					session.saveOrUpdate(ae02);
					log.debug("save successful");
				} catch (RuntimeException re) {
					log.error("save failed", re);
					throw re;
				}
				return null;
			}
		});
	}
	/**
	 * 获取当前试卷下试题的数量
	 * @param sjid
	 * @return
	 */
	public int getNumOfQuestionSet(final String sjid){
		return (Integer) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String sql = "select count(e.st_id) from E_QUESTIONS e where e.ST_SCBZ=0 and e.st_sjid='"+sjid+"'";
				Query query = session.createSQLQuery(sql);
				BigDecimal num = (BigDecimal) query.list().get(0);
				return num.intValue();
			}
		});
	}
	/**
	 * 比较当前试卷下试题的总分
	 * @param sjid
	 * @return
	 */
	public boolean checkSumOfQuestionSet(final String sjid,final String stfz){
		return (Boolean) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String sql = "select sum(e.st_fz) from E_QUESTIONS e where e.ST_SCBZ=0 and e.st_sjid='"+sjid+"'";
				String sql2 = "select e.sj_zf from e_papers_set e where e.sj_state=0 and sj_id="+sjid;
				Query query = session.createSQLQuery(sql);
				Query query2 = session.createSQLQuery(sql2);
				BigDecimal sum = (BigDecimal) query.list().get(0);
				boolean b = false;
				List<BigDecimal> list = query2.list();
				if(list!=null){
					double zf = list.get(0).doubleValue();
					if((sum==null?0:sum.doubleValue())+Double.valueOf(stfz)>zf){
						b = false;
					}else{
						b = true;
					}
				}
				return b;
			}
		});
	}
	public void savelog(final EModefy transientInstance)
			throws Exception {
		        HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				log.debug("saving EQuestions instance");
				try {
					session.saveOrUpdate(transientInstance);
					log.debug("save successful");
				} catch (RuntimeException re) {
					log.error("save failed", re);
					throw re;
				} 
				return null;
			}
		});
		}
//
//	public void delete(EQuestions persistentInstance) {
//		log.debug("deleting EQuestions instance");
//		try {
//			session.delete(persistentInstance);
//			log.debug("delete successful");
//		} catch (RuntimeException re) {
//			log.error("delete failed", re);
//			throw re;
//		}
//	}
//	
	/**
	 * 
	 * <p>Description:[批量删除] </p>
	 * 
	 * Created by [Godspeed He] [2010-6-3]
	 * Midified by [修改人] [修改时间]
	 *
	 * @param delId
	 * @return
	 */

 
	public void delete(final EQuestions persistentInstance) 
			throws Exception {
		        HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				log.debug("change EQuestions scbz");
				try {
					persistentInstance.setStScbz(1);
					session.save(persistentInstance);
					log.debug("delete successful");
				} catch (RuntimeException re) {
					log.error("delete failed", re);
					throw re;
				}
				return null;
			}
		});
		}
	/**
	 * 
	 * <p>Description:[批量删除] </p>
	 * 
	 * Created by [Godspeed He] [2010-6-3]
	 * Midified by [修改人] [修改时间]
	 *
	 * @param delId
	 * @return
	 */
	public int delete(final Long[] delId)throws Exception{
		return (Integer) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				int row=0;
				EQuestionsDAO dao=new EQuestionsDAO();
				try {
					for(int i=0;i<delId.length;i++){
						EQuestions persistentInstance=(dao.findById(delId[i]));
						persistentInstance.setStScbz(1);
						session.save(persistentInstance);				
						row++;
					}
//					String hql = "delete from EQuestions eqs  where eqs.stId  in ( :delId) ";   
//					int row = session.createQuery(hql)
//					.setParameterList("delId", delId)
//					.executeUpdate();
					return row;   
				}catch (Exception ex){
					log.debug("批量删除失败！");
					throw new DAOException(ex);
				}
			}
		});
	}
	public void batchCheck(final String[] ids) throws Exception{
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			@Override
			public Object execute(Session session) throws Throwable {
				for(int i=0;i<ids.length;i++){
					EQuestions persistent = (EQuestions) session.get(EQuestions.class, Long.parseLong(ids[i]));
					persistent.setStCheck((long)1);
					session.saveOrUpdate(persistent);
					// 业务日志
					if (persistent.getEbusinesstype() != null) {
						Ae02 ae02 = new Ae02();
						ae02 = findAe02BystId(Long.parseLong(ids[i]));
						ae02.setCae220("011");
						saveOrUpdate_ae02(ae02);
					}
				}
				return null;
			}
		});
	}
	
	
	public int turn(final Long[] delId)throws Exception{
		return (Integer) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				int row=0;
				EQuestionsDAO dao=new EQuestionsDAO();
				try {
					for(int i=0;i<delId.length;i++){
						EQuestions persistentInstance=(dao.findById(delId[i]));
						persistentInstance.setStKszy(1);
						session.save(persistentInstance);				
						row++;
					}
//					String hql = "delete from EQuestions eqs  where eqs.stId  in ( :delId) ";   
//					int row = session.createQuery(hql)
//					.setParameterList("delId", delId)
//					.executeUpdate();
					return row;   
				}catch (Exception ex){
					log.debug("批量转化失败！");

					throw new DAOException(ex);
				}	 

			}
		});


	}
	
	public int turnjd(final Long[] delId)throws Exception{
		return (Integer) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				int row=0;
				EQuestionsDAO dao=new EQuestionsDAO();
				try {
					for(int i=0;i<delId.length;i++){
						EQuestions persistentInstance=(dao.findById(delId[i]));
						persistentInstance.setStKszy(2);
						session.save(persistentInstance);				
						row++;
					}
//					String hql = "delete from EQuestions eqs  where eqs.stId  in ( :delId) ";   
//					int row = session.createQuery(hql)
//					.setParameterList("delId", delId)
//					.executeUpdate();
					return row;   
				}catch (Exception ex){
					log.debug("批量转化失败！");

					throw new DAOException(ex);
				}

			}
		});


	}
	
	public int unturnjd(final  Long[] delId)throws Exception{
		return (Integer) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				int row=0;
				EQuestionsDAO dao=new EQuestionsDAO();
				try {
					for(int i=0;i<delId.length;i++){
						EQuestions persistentInstance=(dao.findById(delId[i]));
						persistentInstance.setStKszy(3);
						session.save(persistentInstance);				
						row++;
					}
//					String hql = "delete from EQuestions eqs  where eqs.stId  in ( :delId) ";   
//					int row = session.createQuery(hql)
//					.setParameterList("delId", delId)
//					.executeUpdate();
					return row;   
				}catch (Exception ex){
					log.debug("批量转化失败！");

					throw new DAOException(ex);
				}
 

			}
		});


	}
	

	public int turnback(final Long[] delId)throws Exception{
		return (Integer) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				int row=0;
				EQuestionsDAO dao=new EQuestionsDAO();
				try {
					for(int i=0;i<delId.length;i++){
						EQuestions persistentInstance=(dao.findById(delId[i]));
						persistentInstance.setStKszy(0);
						session.save(persistentInstance);				
						row++;
					}
//					String hql = "delete from EQuestions eqs  where eqs.stId  in ( :delId) ";   
//					int row = session.createQuery(hql)
//					.setParameterList("delId", delId)
//					.executeUpdate();
					return row;   
				}catch (Exception ex){
					log.debug("批量转化失败！");

					throw new DAOException(ex);
				}	 

			}
		});


	}
//	public int delete(Long[] delId){   
//		int row=0;
//		try {
//			for(int i=0;i<delId.length;i++){
//				session.delete(this.findById(delId[i]));	
//				row++;
//			}
////			String hql = "delete from EQuestions eqs  where eqs.stId  in ( :delId) ";   
////			int row = session.createQuery(hql)
////			.setParameterList("delId", delId)
////			.executeUpdate();
//			return row;   
//		}catch (Exception ex){
//			log.debug("批量删除失败！");
//
//			throw new DAOException(ex);
//		}
//
//	}  
	/**
	 * 试题批量审核，全部审核通过
	 * 
	 * @author gkk
	 * @date 2017-1-4 上午10:14:24
	 */
	public void passAll(){
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			@Override
			public Object execute(Session session) throws Throwable {
				String sql = "update E_QUESTIONS a set a.st_jyxgcs=1,a.st_check=1,a.st_kszy=1 where a.st_scbz=0 and a.st_check=0";
				Query query = session.createSQLQuery(sql);
				query.executeUpdate();
				return null;
			}
		});
	}

	
	public EQuestions findById(final Long id)throws Exception{
		return (EQuestions) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				log.debug("getting EQuestions instance with id: " + id);
				try {
					EQuestions eQuestions = new EQuestions();
					eQuestions = (EQuestions) session.get(EQuestions.class, id);
					/*String hql = "select e.stTg,e.stCc,e.stNodeName,e.equestiontype,e.eimportance,e.bxType," +
							"e.stSyryId,e.stId,e.stXx,e.stDa,e.ebusinesstype from EQuestions as e where e.stId="+id;
					Query query = session.createQuery(hql);
					List<Object[]> list = query.list();
					
					if(list.size()!=0){
						Object[] o = list.get(0);
						eQuestions.setStTg((String)o[0]);
						eQuestions.setStCc((String)o[1]);
						eQuestions.setStNodeName((String)o[2]);
						eQuestions.setEquestiontype((EQuestiontype)o[3]);
						eQuestions.setEimportance((EImportance)o[4]);
						eQuestions.setBxType((String)o[5]);
						eQuestions.setStSyryId((String)o[6]);
						eQuestions.setStId((Long)o[7]);
						eQuestions.setStXx((String)o[8]);
						eQuestions.setStDa((String)o[9]);
						eQuestions.setEbusinesstype((EBusinesstype)o[10]);
					}*/
					//EQuestions instance = (EQuestions) session.get(EQuestions.class, id);
					return eQuestions;
				} catch (RuntimeException re) {
					log.error("get failed", re);
					throw re;
				} 

			}
		});


	}
	/**
	 * 查询试题审核页面相关参数
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	
	public EQuestionsVO getByid_loadcheck(final long id)throws Exception{
		return (EQuestionsVO) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				log.debug("getting EQuestions instance with id: " + id);
				try {
					/*String hql = "select e.stTg,e.stCc,e.stNodeName,e.equestiontype,e.eimportance,e.bxType," +
							"t.rankname,e.stLrsj,e.stSyryId,e.stId,t.id_job,e.stXx,e.stDa" +
							" from EQuestions as e,Tjobsubject as t where cast(e.stNodeName as string)=cast(t.id as string) and e.stId="+id;*/
					
					String hql = "select e.st_tg,e.st_cc,e.st_node_name,qt.id,ip.id,e.bx_type," +
							"t.rankname,e.st_lrsj,e.st_syry_id,e.st_id,t.id_job,e.st_xx,e.st_da,e.st_img,e.st_img_a,e.st_img_b,e.st_img_c,e.st_img_d,e.st_img_e,e.st_img_f,e.st_img_g,e.st_sjid" +
							" from E_QUESTIONS e,TJOBSUBJECT t,E_IMPORTANCE ip,E_QUESTIONTYPE qt where e.st_node_name=t.id and e.st_id="+id+" and e.st_lxid=qt.id and e.st_zyxid=ip.id";
					//System.out.println("---"+hql);
					Query query = session.createSQLQuery(hql);
					List<Object[]> list = query.list();
					Iterator<Object[]> it = list.iterator();
					EQuestionsVO eQuestionsVO = new EQuestionsVO();
					while(it.hasNext()){
						Object[] o = it.next();
						eQuestionsVO.setStTg((String)o[0]);
						eQuestionsVO.setStCc((String)o[1]);
						eQuestionsVO.setStNodeName((String)o[2]);
						//eQuestionsVO.setEquestiontype((EQuestiontype)o[3]);
						//eQuestionsVO.setEimportance((EImportance)o[4]);
						eQuestionsVO.setStlx(((BigDecimal)o[3]).toString());
						eQuestionsVO.setNyd(((BigDecimal)o[4]).toString());
						eQuestionsVO.setBxType((String)o[5]);
						eQuestionsVO.setRankname((String)o[6]);
						eQuestionsVO.setStLrsj((Date)o[7]);
						eQuestionsVO.setStSyryId((String)o[8]);
						eQuestionsVO.setStId(((BigDecimal)o[9]).longValue());
						eQuestionsVO.setId_job((String)o[10]);
						eQuestionsVO.setStXx((String)o[11]);
						eQuestionsVO.setStDa((String)o[12]);
						Object object = o[13];
						String img = "";
						if(object!=null){
							Blob blob = (Blob) o[13];
							img = blob.toString();
						}
						eQuestionsVO.setStImg(img);
						img = "";
						object = o[14];
						if(object!=null){
							Blob blob = (Blob) o[14];
							img = blob.toString();
						}
						eQuestionsVO.setStImgA(img);
						img = "";
						object = o[15];
						if(object!=null){
							Blob blob = (Blob) o[15];
							img = blob.toString();
						}
						eQuestionsVO.setStImgB(img);
						img = "";
						object = o[16];
						if(object!=null){
							Blob blob = (Blob) o[16];
							img = blob.toString();
						}
						eQuestionsVO.setStImgC(img);
						img = "";
						object = o[17];
						if(object!=null){
							Blob blob = (Blob) o[17];
							img = blob.toString();
						}
						eQuestionsVO.setStImgD(img);
						img = "";
						object = o[18];
						if(object!=null){
							Blob blob = (Blob) o[18];
							img = blob.toString();
						}
						eQuestionsVO.setStImgE(img);
						img = "";
						object = o[19];
						if(object!=null){
							Blob blob = (Blob) o[19];
							img = blob.toString();
						}
						eQuestionsVO.setStImgF(img);
						img = "";
						object = o[20];
						if(object!=null){
							Blob blob = (Blob) o[20];
							img = blob.toString();
						}
						eQuestionsVO.setStImgG(img);
						eQuestionsVO.setStSjId((String)o[21]);
					}
					/*Object[] objects = list.get(0);
					EQuestions bo=(EQuestions) objects[0];
					BeanUtils.copyProperties(bo, eQuestionsVO);

					eQuestionsVO.setId_job((String)objects[1]);
					eQuestionsVO.setRankname((String)objects[2]);*/
					
					return eQuestionsVO;
				} catch (RuntimeException re) {
					re.printStackTrace();
					log.error("get failed", re);
					throw re;
				} 

			}
		});


	}
	

	public List findByProperty(final String propertyName,final Object value)throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				log.debug("finding EQuestions instance with property: " + propertyName
						+ ", value: " + value);
				try {
					String queryString = "from EQuestions as model where model."
						+ propertyName + "= ?";
					Query queryObject = session.createQuery(queryString);
					queryObject.setParameter(0, value);
					return queryObject.list();
				} catch (RuntimeException re) {
					log.error("find by property name failed", re);
					throw re;
				} 

			}
		});


	}

	/**
	 * 
	 * <p>Description:[统计所有符合条件的记录总数] </p>
	 * 
	 * Created by [Kevin Liang] [2009-7-1]
	 * Midified by [修改人] [修改时间]
	 *
	 * @param subject
	 * @param documentnum
	 * @param examsign
	 * @param recorddatebegin
	 * @param recorddateend
	 * @param modifiydatebegin
	 * @param modifiydateend
	 * @param businesstype
	 * @param importance
	 * @param questiontype
	 * @param deletesign
	 * @return
	 * @throws Exception 
	 */
	 
	public int getQuestionsTotalCount(
			final String chuchu,
			final String subject,
			final String documentnum,
			final long examsign,//考试题标志
			final Date recorddatebegin,
			final Date recorddateend,
			final Date modifiydatebegin,
			final Date modifiydateend,
			final  long businesstype,
			final long difficulty,
			final long questiontype,
			final long Paperid,
			final long deletesign,
			final String gzid,
			final String gzdj,
			final int state,
			final String sjid)throws Exception{
		return (Integer) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				Query query = null;
				int size = 0;
				try {
					StringBuffer buffer = new StringBuffer();
					buffer.append("select count(*) from e_questions a,tjobsubject b,e_importance c,e_questiontype d where a.st_node_name=to_char(b.id) and a.st_zyxid=c.id and a.st_lxid=d.id and a.st_scbz=0");
					if(!subject.equals("")){
						buffer.append(" and a.st_tg like "+"'%"+subject+"%'");
					}
					if(questiontype!=0){
						buffer.append(" and d.id="+questiontype);
					}
					if(difficulty!=0){
						buffer.append(" and c.id="+difficulty);
					}
					if(!gzid.equals("")){
						buffer.append(" and b.id_job="+"'"+gzid+"'");
					}
					if(!gzdj.equals("")){
						buffer.append(" and b.rankname="+"'"+gzdj+"'");
					}
					if(state!=3){
						buffer.append(" and a.st_check="+"'"+state+"'");
					}
					 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					if(recorddatebegin!=null && recorddateend!=null){
						buffer.append(" and a.st_lrsj between to_date('"+formatter.format(recorddatebegin)+"','yyyy-MM-dd HH24:mi:ss') and to_date('"+formatter.format(recorddateend)+"','yyyy-MM-dd HH24:mi:ss')");
					}else if(recorddatebegin==null && recorddateend!=null){
						buffer.append(" and a.st_lrsj<"+recorddateend);
					}else if(recorddatebegin!=null && recorddateend==null){
						buffer.append(" and a.st_lrsj>"+recorddatebegin);
					}
					if(!"0".equals(sjid)){
						buffer.append(" and a.st_sjid='"+sjid+"'");
					}
					String querysString = buffer.toString();
					query = session.createSQLQuery(querysString);
					size =  ((BigDecimal) query.uniqueResult()).intValue();
				} catch (Exception e) {
					e.printStackTrace();
				} finally{
				}
				return size;
			}
		});


	}
	
	public int getQuestionsTotalCountUnchecked(
			final String chuchu,
			final String subject,
			final String documentnum,
			final long examsign,//考试题标志
			final Date recorddatebegin,
			final Date recorddateend,
			final Date modifiydatebegin,
			final Date modifiydateend,
			final long businesstype,
			final long difficulty,
			final long questiontype,
			final long Paperid,
			final long deletesign,
			final String gzid,
			final String gzdj,
			final String sjid)throws Exception{
		return (Integer) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				Query query = null;
				int size = 0;
				try {
					StringBuffer buffer = new StringBuffer();
					buffer.append("select qs.stId,js.jobname,qs.stTg,qs.bxType,qs.stLrsj,ip.name,qt.name, " +
							"js.rankname,qs.stSyryId,qs.stFjlj,qs.stCc,qs.stLrsj from " +
							"EQuestions as qs,Tjobsubject as js,EImportance as ip,EQuestiontype as qt where " +
							"qs.stNodeName=to_char(js.id) and qs.eimportance.id=ip.id and qs.equestiontype.id=qt.id and " +
							"qs.stCheck=0 and qs.stScbz=0");
					if(!subject.equals("")){
						buffer.append(" and qs.stTg like "+"'%"+subject+"%'");
					}
					if(questiontype!=0){
						buffer.append(" and qt.id="+questiontype);
					}
					if(difficulty!=0){
						buffer.append(" and ip.id="+difficulty);
					}
					if(!gzid.equals("")){
						buffer.append(" and js.id_job="+"'"+gzid+"'");
					}
					if(!gzdj.equals("")){
						buffer.append(" and js.rankname="+"'"+gzdj+"'");
					}
					 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					if(recorddatebegin!=null && recorddateend!=null){
						buffer.append(" and qs.stLrsj between to_date('"+formatter.format(recorddatebegin)+"','yyyy-MM-dd HH24:mi:ss') and to_date('"+formatter.format(recorddateend)+"','yyyy-MM-dd HH24:mi:ss')");
					}else if(recorddatebegin==null && recorddateend!=null){
						buffer.append(" and qs.stLrsj<"+recorddateend);
					}else if(recorddatebegin!=null && recorddateend==null){
						buffer.append(" and qs.stLrsj>"+recorddatebegin);
					}
					if(!"0".equals(sjid)){
						buffer.append(" and qs.stSjid='"+sjid+"'");
					}
					String querysString = buffer.toString();
					query = session.createQuery(querysString);
					size = query.list().size();
				}catch(Exception e){
					e.printStackTrace();
				}finally{
				}
				return size;
			}
		});


	}
	public int getQuestionsTotalCountUnpass(
			final String chuchu,
			final String subject,
			final String documentnum,
			final long examsign,//考试题标志
			final Date recorddatebegin,
			final Date recorddateend,
			final Date modifiydatebegin,
			final Date modifiydateend,
			final long businesstype,
			final long difficulty,
			final long questiontype,
			final long Paperid,
			final long deletesign,
			final String gzid,
			final String gzdj)throws Exception{
		return (Integer) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				Query query = null;
				int size = 0;
				try {
					StringBuffer buffer = new StringBuffer();
					buffer.append("select qs.stId,js.jobname,qs.stTg,qs.bxType,qs.stLrsj,ip.name,qt.name, " +
							"js.rankname,qs.stSyryId,qs.stFjlj,qs.stCc,qs.stLrsj from " +
							"EQuestions as qs,Tjobsubject as js,EImportance as ip,EQuestiontype as qt where " +
							"qs.stNodeName=to_char(js.id) and qs.eimportance.id=ip.id and qs.equestiontype.id=qt.id and " +
							"qs.stCheck=2 and qs.stScbz=0");
					if(!subject.equals("")){
						buffer.append(" and qs.stTg like "+"'%"+subject+"%'");
					}
					if(questiontype!=0){
						buffer.append(" and qt.id="+questiontype);
					}
					if(difficulty!=0){
						buffer.append(" and ip.id="+difficulty);
					}
					if(!gzid.equals("")){
						buffer.append(" and js.id_job="+"'"+gzid+"'");
					}
					if(!gzdj.equals("")){
						buffer.append(" and js.rankname="+"'"+gzdj+"'");
					}
					 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					if(recorddatebegin!=null && recorddateend!=null){
						buffer.append(" and qs.stLrsj between to_date('"+formatter.format(recorddatebegin)+"','yyyy-MM-dd HH24:mi:ss') and to_date('"+formatter.format(recorddateend)+"','yyyy-MM-dd HH24:mi:ss')");
					}else if(recorddatebegin==null && recorddateend!=null){
						buffer.append(" and qs.stLrsj<"+recorddateend);
					}else if(recorddatebegin!=null && recorddateend==null){
						buffer.append(" and qs.stLrsj>"+recorddatebegin);
					}
					String querysString = buffer.toString();
					//System.out.println(querysString);
					query = session.createQuery(querysString);
					size = query.list().size();
				}catch(Exception e){
					e.printStackTrace();
				}finally{
				}
				return size;
			}
		});


	}

	/**
	 * 
	 * <p>Description:[试题分页查询] </p>
	 * 
	 * Created by [Kevin Liang] [2009-7-1]
	 * Midified by [修改人] [修改时间]
	 *
	 * @param subject 题目
	 * @param documentnum 文号
	 * @param examsign 试题标志
	 * @param recorddatebegin	录入时间开始
	 * @param recorddateend		录入时间结束
	 * @param modifiydatebegin	修改时间开始
	 * @param modifiydateend	修改时间结束
	 * @param operation	业务类型
	 * @param importance	重要性
	 * @param questiontype	问题类型
	 * @param currentPage	当前页
	 * @param pageSize	页行数
	 * @return
	 */
	public List findQuestions(
			final String chuchu,
			final String subject,
			final String documentnum,
			final long examsign,//考试题标志
			final Date recorddatebegin,
			final Date recorddateend,
			final Date modifiydatebegin,
			final Date modifiydateend,
			final long businesstype,
			final long difficulty,
			final long questiontype,
			final long Paperid,
			final int currentPage, 
			final int pageSize,
			final String orderby,
			final long deletesign,
			final String gzid,
			final String gzdj,
			final int state,
			final String sjid)throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				 
				StringBuffer buffer = new StringBuffer();
				List<EQuestionsDTO> list = null;
				int currentPage1=currentPage;
				if (currentPage == 0) {
					currentPage1 = 1;
				}
				try {
					buffer.append("select qs.stId,js.jobname,qs.stTg,qs.bxType,qs.stLrsj,ip.name,qt.name, " +
							"js.rankname,qs.stSyryId,qs.stFjlj,qs.stCc,qs.stCheck,qs.stModefy,qs.stFz,qs.stXx,qs.stDa from " +
							"EQuestions as qs,Tjobsubject as js,EImportance as ip,EQuestiontype as qt where " +
							"qs.stNodeName=to_char(js.id) and qs.eimportance.id=ip.id and qs.equestiontype.id=qt.id and " +
							"qs.stScbz=0");
					if(!subject.equals("")){
						buffer.append(" and qs.stTg like "+"'%"+subject+"%'");
					}
					if(questiontype!=0){
						buffer.append(" and qt.id="+questiontype);
					}
					if(difficulty!=0){
						buffer.append(" and ip.id="+difficulty);
					}
					if(!gzid.equals("")){
						buffer.append(" and js.id_job="+"'"+gzid+"'");
					}
					if(!gzdj.equals("")){
						buffer.append(" and js.rankname="+"'"+gzdj+"'");
					}
					if(state!=3){
						buffer.append(" and qs.stCheck="+"'"+state+"'");
					}
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					if(recorddatebegin!=null && recorddateend!=null){
						buffer.append(" and qs.stLrsj between to_date('"+formatter.format(recorddatebegin)+"','yyyy-MM-dd HH24:mi:ss') and to_date('"+formatter.format(recorddateend)+"','yyyy-MM-dd HH24:mi:ss')");
					}else if(recorddatebegin==null && recorddateend!=null){
						buffer.append(" and qs.stLrsj<"+recorddateend);
					}else if(recorddatebegin!=null && recorddateend==null){
						buffer.append(" and qs.stLrsj>"+recorddatebegin);
					}
					if(!"0".equals(sjid)){
						buffer.append(" and qs.stSjid='"+sjid+"'");
					}
					buffer.append(" order by qs."+orderby+" desc");
					String querysString = buffer.toString();
					//System.out.println(querysString);
					list = new ArrayList<EQuestionsDTO>();
					Query query = session.createQuery(querysString);
					query.setFirstResult((currentPage1 - 1) * pageSize);
					query.setMaxResults(pageSize);
					
					List<Object[]> list1 = query.list();
					Iterator<Object[]> it = list1.iterator();
					while(it.hasNext()){
						EQuestionsDTO eq = new EQuestionsDTO();
						Object[] object = it.next();
						eq.setId((Long) object[0]);
						eq.setProfession((String)object[1]);
						eq.setTg((String)object[2]);
						eq.setImportence((String)object[3]);
						eq.setLrsj((Date)object[4]);
						eq.setDifficulty((String)object[5]);
						eq.setQuestiontype((String)object[6]);
						eq.setGrade((String)object[7]);
						eq.setExpert((String)object[8]);
						eq.setRemark((String)object[9]);
						eq.setReference((String)object[10]);
						eq.setState((Long) object[11]);
						eq.setUsageCount((Long)object[12]);
						eq.setStFz((String)object[13]);
						eq.setXx((String)object[14]);
						eq.setDa((String)object[15]);
						list.add(eq);
					}
					
				} catch (Exception e) {
					log.error("find all failed", e);
					e.printStackTrace();
				}finally{
				}
				return list;
			}
		});


	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List findQuestionsUnchecked(
			final String chuchu,
			final String subject,
			final String documentnum,
			final long examsign,//考试题标志
			final Date recorddatebegin,
			final Date recorddateend,
			final Date modifiydatebegin,
			final Date modifiydateend,
			final long businesstype,
			final long difficulty,
			final long questiontype,
			final long Paperid,
			final int currentPage, 
			final int pageSize,
			final String orderby,
			final long deletesign,
			final String gzid,
			final String gzdj,
			final String sjid) throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				log.info("分页查询");
				
				StringBuffer buffer = new StringBuffer();
				List<EQuestionsDTO> list = null;
				int  currentPage1=currentPage;
				if (currentPage == 0) {
					currentPage1 = 1;
				}
				try {
					buffer.append("select qs.stId,js.jobname,qs.stTg,qs.bxType,qs.stLrsj,ip.name,qt.name, " +
							"js.rankname,qs.stSyryId,qs.stFjlj,qs.stCc,qs.stLrsj,qs.stFz,qs.stXx,qs.stDa from " +
							"EQuestions as qs,Tjobsubject as js,EImportance as ip,EQuestiontype as qt where " +
							"qs.stNodeName=to_char(js.id) and qs.eimportance.id=ip.id and qs.equestiontype.id=qt.id and " +
							"qs.stCheck=0 and qs.stScbz=0");
					if(!subject.equals("")){
						buffer.append(" and qs.stTg like "+"'%"+subject+"%'");
					}
					if(questiontype!=0){
						buffer.append(" and qt.id="+questiontype);
					}
					if(difficulty!=0){
						buffer.append(" and ip.id="+difficulty);
					}
					if(!gzid.equals("")){
						buffer.append(" and js.id_job="+"'"+gzid+"'");
					}
					if(!gzdj.equals("")){
						buffer.append(" and js.rankname="+"'"+gzdj+"'");
					}
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					if(recorddatebegin!=null && recorddateend!=null){
						buffer.append(" and qs.stLrsj between to_date('"+formatter.format(recorddatebegin)+"','yyyy-MM-dd HH24:mi:ss') and to_date('"+formatter.format(recorddateend)+"','yyyy-MM-dd HH24:mi:ss')");
					}else if(recorddatebegin==null && recorddateend!=null){
						buffer.append(" and qs.stLrsj<"+recorddateend);
					}else if(recorddatebegin!=null && recorddateend==null){
						buffer.append(" and qs.stLrsj>"+recorddatebegin);
					}
					if(!"0".equals(sjid)){
						buffer.append(" and qs.stSjid='"+sjid+"'");
					}
					buffer.append(" order by qs."+orderby+" desc");
					String querysString = buffer.toString();
					//System.out.println(querysString);
					list = new ArrayList<EQuestionsDTO>();
					Query query = session.createQuery(querysString);
					query.setFirstResult((currentPage1 - 1) * pageSize);
					query.setMaxResults(pageSize);
					
					List<Object[]> list1 = query.list();
					Iterator<Object[]> it = list1.iterator();
					while(it.hasNext()){
						EQuestionsDTO eq = new EQuestionsDTO();
						Object[] object = it.next();
						eq.setId((Long) object[0]);
						eq.setProfession((String)object[1]);
						eq.setTg((String)object[2]);
						eq.setImportence((String)object[3]);
						eq.setLrsj((Date)object[4]);
						eq.setDifficulty((String)object[5]);
						eq.setQuestiontype((String)object[6]);
						eq.setGrade((String)object[7]);
						eq.setExpert((String)object[8]);
						eq.setRemark((String)object[9]);
						eq.setReference((String)object[10]);
						eq.setStFz((String)object[12]);
						eq.setXx((String)object[13]);
						eq.setDa((String)object[14]);
						list.add(eq);
					}
					
				} catch (Exception e) {
					log.error("find all failed", e);
					e.printStackTrace();
				}finally{
				}
				return list;
			}
		});


	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public  List findQuestionsUnpass(
			final String chuchu,
			final String subject,
			final String documentnum,
			final long examsign,//考试题标志
			final Date recorddatebegin,
			final Date recorddateend,
			final Date modifiydatebegin,
			final Date modifiydateend,
			final long businesstype,
			final long difficulty,
			final long questiontype,
			final long Paperid,
			final int currentPage, 
			final int pageSize,
			final String orderby,
			final long deletesign,
			final String gzid,
			final String gzdj) throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				log.info("分页查询");
				StringBuffer buffer = new StringBuffer();
				List list = null;
				int currentPage1=currentPage;
				if (currentPage == 0) {
					currentPage1 = 1;
				}
				try {
					buffer.append("select qs.stId,js.jobname,qs.stTg,qs.bxType,qs.stLrsj,ip.name,qt.name, " +
							"js.rankname,qs.stSyryId,qs.stFjlj,qs.stCc,qs.stLrsj,qs.stFz from " +
							"EQuestions as qs,Tjobsubject as js,EImportance as ip,EQuestiontype as qt where " +
							"qs.stNodeName=to_char(js.id) and qs.eimportance.id=ip.id and qs.equestiontype.id=qt.id and " +
							"qs.stCheck=2 and qs.stScbz=0");
					if(!subject.equals("")){
						buffer.append(" and qs.stTg like "+"'%"+subject+"%'");
					}
					if(questiontype!=0){
						buffer.append(" and qt.id="+questiontype);
					}
					if(difficulty!=0){
						buffer.append(" and ip.id="+difficulty);
					}
					if(!gzid.equals("")){
						buffer.append(" and js.id_job="+"'"+gzid+"'");
					}
					if(!gzdj.equals("")){
						buffer.append(" and js.rankname="+"'"+gzdj+"'");
					}
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					if(recorddatebegin!=null && recorddateend!=null){
						buffer.append(" and qs.stLrsj between to_date('"+formatter.format(recorddatebegin)+"','yyyy-MM-dd HH24:mi:ss') and to_date('"+formatter.format(recorddateend)+"','yyyy-MM-dd HH24:mi:ss')");
					}else if(recorddatebegin==null && recorddateend!=null){
						buffer.append(" and qs.stLrsj<"+recorddateend);
					}else if(recorddatebegin!=null && recorddateend==null){
						buffer.append(" and qs.stLrsj>"+recorddatebegin);
					}
					buffer.append(" order by qs."+orderby+" desc");
					String querysString = buffer.toString();
					//System.out.println(querysString);
					list = new ArrayList<EQuestionsDTO>();
					Query query = session.createQuery(querysString);
					query.setFirstResult((currentPage1 - 1) * pageSize);
					query.setMaxResults(pageSize);
					
					List<Object[]> list1 = query.list();
					Iterator<Object[]> it = list1.iterator();
					while(it.hasNext()){
						EQuestionsDTO eq = new EQuestionsDTO();
						Object[] object = it.next();
						eq.setId((Long) object[0]);
						eq.setProfession((String)object[1]);
						eq.setTg((String)object[2]);
						eq.setImportence((String)object[3]);
						eq.setLrsj((Date)object[4]);
						eq.setDifficulty((String)object[5]);
						eq.setQuestiontype((String)object[6]);
						eq.setGrade((String)object[7]);
						eq.setExpert((String)object[8]);
						eq.setRemark((String)object[9]);
						eq.setReference((String)object[10]);
						eq.setStFz((String)object[12]);
						list.add(eq);
					}
					
				} catch (Exception e) {
					log.error("find all failed", e);
					e.printStackTrace();
				}finally{
				}
				return list;
			}
		});


	}
	public List findPaperSt(final long Paperid)throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				log.debug("finding all EPaperquestions instances");
				try {
					String queryString = "select e.equestions.stId from EPaperquestions e where e.epapers.sjId='"+Paperid+"' ";
					Query queryObject = session.createQuery(queryString);
					return queryObject.list();
				} catch (RuntimeException re) {
					log.error("findPaperSt  failed", re);
					throw new DAOException(re);
				} 

			}
		});


	}
	//题目出过多少次
	public int getAllNunber(final long questionId) throws InfrastructureException{
		return (Integer)HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {	   
				int i=0;
				List lst1=null;
				String queryString = " from EPaper e  ";
				Query queryObject = s.createQuery(queryString);
				lst1=queryObject.list();
				   if(lst1!=null&&lst1.size()>0){
					   for(int j=0;j<lst1.size();j++){						   
						   List lst2=null;  
						   String sql = "select e.equestions.stId from EPaperquestions e where e.equestions.stId='"+questionId+"' "; 
						   Query query = s.createQuery(sql);
						   lst2=query.list();
						   if(lst2!=null&&lst2.size()>0){
							  i++; 
						   }						   						   
					   }
				   }
				return i;	
			}}); 
	}
 
	public List findAll()throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				log.debug("finding all EQuestions instances");
				try {
					String queryString = "from EQuestions";
					Query queryObject = session.createQuery(queryString);
					return queryObject.list();
				} catch (RuntimeException re) {
					log.error("find all failed", re);
					throw new DAOException(re);
				} 

			}
		});


	}
 
	public Ae02 findAe02BystId(final long id)throws Exception{
		return (Ae02) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				log.debug("finding Ae02 instances By stId");
				try {
					EQuestions e = (EQuestions) session.get(EQuestions.class, id);
					Ae02 ae02 = (Ae02) session.get(Ae02.class, e.getEbusinesstype().getId());
					return ae02;
				} catch (RuntimeException re) {
					log.error("find all failed", re);
					throw new DAOException(re);
				} 

			}
		});
	}
	
	public static void writeImg(final OutputStream os,final long stid){
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				EQuestions e = (EQuestions) session.get(EQuestions.class, stid);
				byte[] b = e.getStImg();
				if(b!=null){
					os.write(b);
				}
				os.close();
				return null;
			}
		});
	}
	
	public static void writeImg_xx(final OutputStream os,final long stid,final String xx){
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				EQuestions e = (EQuestions) session.get(EQuestions.class, stid);
				byte[] b = null;
				if("A".equals(xx)){
					b = e.getStImgA();
				}else if("B".equals(xx)){
					b = e.getStImgB();
				}else if("C".equals(xx)){
					b = e.getStImgC();
				}else if("D".equals(xx)){
					b = e.getStImgD();
				}else if("E".equals(xx)){
					b = e.getStImgE();
				}else if("F".equals(xx)){
					b = e.getStImgF();
				}else if("G".equals(xx)){
					b = e.getStImgG();
				}
				if(b!=null){
					os.write(b);
				}
				os.close();
				return null;
			}
		});
	}
}
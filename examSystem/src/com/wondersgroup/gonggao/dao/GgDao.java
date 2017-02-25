package com.wondersgroup.gonggao.dao;


import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;
import com.wondersgroup.gonggao.bo.TBm;
import com.wondersgroup.gonggao.bo.TGg;
import com.wondersgroup.gonggao.bo.TGgfj;
import com.wondersgroup.gonggao.bo.TGglm;
import com.wondersgroup.kaoshi.util.PageReturn;
import com.wondersgroup.kaoshi.util.PageTool;

public class GgDao extends HibernateDaoSupport {
	
 
	public PageReturn findAll(final  PageTool pageTool,final String biaoti,final Date sjbegin,final Date sjend)throws Exception{
		return (PageReturn) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				PageReturn pr=new PageReturn();
				Criteria criteria=session.createCriteria(TGg.class);
				
				//���ò�ѯ���
				if(biaoti!=null && !biaoti.equals("")){
					criteria.add(Restrictions.like("ggbt", biaoti, MatchMode.ANYWHERE));
				}
				//System.out.println(sjend);
				if (sjbegin!=null&&sjend!=null){
					criteria.add(Restrictions.between("ggrq", sjbegin, sjend));
				}else if (sjbegin!=null&&sjend==null){
					criteria.add(Restrictions.ge("ggrq", sjbegin));
				}else if (sjbegin==null&&sjend!=null){
					criteria.add(Restrictions.le("ggrq", sjend));
				}
				
				pr.setTotal(criteria.list().size());
				
				criteria.setFirstResult(pageTool.getStart());
				criteria.setMaxResults(pageTool.getSize());
				pr.setReturnList(criteria.list());
				return pr; 

			}
		});


	}
 
	public TGglm loadTGglm(final String lxmc)throws Exception{
		return (TGglm) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				TGglm Gglm = (TGglm) session.get(TGglm.class,lxmc);
				/*TGglm Gglm = null;
				String hql = "select t from TGglm as t where t.lxid='"+lxmc+"'";
				Query query = this.getSession().createQuery(hql);
				List<TGglm> list = query.list();
				if(list.size()!=0){
					Gglm = list.get(0);
				}*/
				return Gglm; 

			}
		});


	}
 
	public TGg loadTgg(final String ggid)throws Exception{
		return (TGg) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				TGg ggg=(TGg) session.load(TGg.class,ggid);
				return ggg; 

			}
		});


	}
  
    public TGgfj loadTGgfj(final String ggid)throws Exception{
		return (TGgfj) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				TGgfj fj=(TGgfj) session.load(TGgfj.class,ggid);
		    	/*TGgfj fj=null;
		    	String hql = "select t from TGgfj as t where t.ggid='"+ggid+"'";
				Query query = this.getSession().createQuery(hql);
				List<TGgfj> list = query.list();
				if(list.size()!=0){
					fj = list.get(0);
				}*/
				return fj; 

			}
		});


	}
 
public void removegg(final String ggid)
	throws Exception {
        HibernateUtil.doInSession(new HibernateSessionCallback() {
	public Object execute(Session session) throws Throwable {
		TGg tgg=(TGg) session.load(TGg.class,ggid);
		session.delete(tgg);
		return null;
	}
});
}
	 
	@SuppressWarnings("unchecked")
	public List<TGglm> getAllgglm()throws Exception{
		return (List<TGglm>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				Criteria criteria=session.createCriteria(TGglm.class);
				return criteria.list(); 

			}
		});


	}

	 
	@SuppressWarnings("unchecked")
	public List<TBm> getAlltbm()throws Exception{
		return (List<TBm>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				Criteria criteria=session.createCriteria(TBm.class);
				return criteria.list();

			}
		});


	}

	/**
	 * 
	 * <p>Description:��ӹ��� </p>
	 * 
	 * Created by [] [11 14, 2009]
	 * Midified by [�޸���] [�޸�ʱ��]
	 *
	 * @param epapers
	 */
	//���
 
	public void addGg(final TGg tgg)
			throws Exception {
		        HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				try{
					session.save(tgg);
				}catch (Exception e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
				} 
				return null;
			}
		});
		}
	//ɾ��
	 
	public void delete(final long id)
			throws Exception {
		        HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				TGg tgg=(TGg ) session.load(TGg.class, id);
				if(tgg!=null){
					session.delete(tgg);
				}
				return null;
			}
		});
		}
	//����
//	public EPost load(long id){
//		TGg tgg=(TGg) this.getSession().load(TGg.class, id);
//		return tgg;
//	}
//	
//	
	@SuppressWarnings("unchecked")
	public List<TGg> getAllPost()throws Exception{
		return (List<TGg>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				Criteria criteria=session.createCriteria(TGg.class);
				return  criteria.list(); 

			}
		});


	}
	
 
	public void saveFj(final TGgfj fj)
			throws Exception {
		        HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				session.save(fj);
				return null;
			}
		});
		}
}

package com.wondersgroup.falcon.dao.call;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.wondersgroup.falcon.exceptions.InfrastructureException;
import com.wondersgroup.falcon.model.call.HisZhijiankaohe;
import com.wondersgroup.falcon.model.call.Mingxi;
import com.wondersgroup.falcon.model.call.Zhijiankaohe;
import com.wondersgroup.falcon.model.call.Zhijianpingfen;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;

public class ZhijiankaoheDAO {
    public ZhijiankaoheDAO() throws InfrastructureException {
         //HibernateUtil.beginTransaction();
    }
    
	public List findAll() {
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			String hql = "from Zhijiankaohe t where t.visible=1 order by t.id ";
			Query query = s.createQuery(hql);
			return query.list();
			}

		});
	}
	
	public List getFirst() {
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			String hql = "from Zhijiankaohe t where t.parentid is null and t.visible=1 order by t.id ";
			Query query = s.createQuery(hql);
			return query.list();
			}

		});
	}
	
	public Zhijiankaohe findZhijiankaoheById(final Long id) {
		return (Zhijiankaohe) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			List list;
			String hql = "from Zhijiankaohe t where t.id=?";
			Query query = s.createQuery(hql);
			query.setString(0, ""+id+"");
			list=query.list();
			if(list!=null&&list.size()!=0)
			return (Zhijiankaohe)list.get(0);
			
		
			else return null;
	}

});	
	}
	
	public Zhijianpingfen findZhijianpingfenById(final String id) {
		return (Zhijianpingfen) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			List list;
			String hql = "from Zhijianpingfen t where t.id='"+id+"'";
			Query query = s.createQuery(hql);
			list=query.list();
			if(list!=null&&list.size()!=0)
			return (Zhijianpingfen)list.get(0);
			
		
			else return null;
			}

		});	
	}
	
	public Mingxi findMingxienById(final String id) {
		return (Mingxi) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			List list;
			String hql = "from Mingxi t where t.id='"+id+"'";
			Query query = s.createQuery(hql);
			list=query.list();
			if(list!=null&&list.size()!=0)
			return (Mingxi)list.get(0);
			
		
			else return null;
			}

		});	
	}
	
	public Mingxi findMingxienBykaoId(final String kaoheid,final String id) {
		return (Mingxi) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			List list;
			String hql = "from Mingxi t where t.kaoheid='"+kaoheid+"' and t.pingfenid='"+id+"'";
			Query query = s.createQuery(hql);
			list=query.list();
			if(list!=null&&list.size()!=0)
			return (Mingxi)list.get(0);
			
		
			else return null;
			}

		});	
	}
	
	public List findMingxienBypingfenid(final String id) {
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			List list;
			String hql = "from Mingxi t where t.pingfenid='"+id+"'";
			Query query = s.createQuery(hql);
			list=query.list();
			if(list!=null&&list.size()!=0)
			return list;
			
		
			else return null;
			}

		});
	}
	
	public Zhijianpingfen findBypingfenid(final String id) {
		return (Zhijianpingfen) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			List list;
			String hql = "from Zhijianpingfen t where t.pingfenid='"+id+"'";
			Query query = s.createQuery(hql);
			list=query.list();
			if(list!=null&&list.size()!=0)
			return (Zhijianpingfen)list.get(0);
			
		
			else return null;
			}

		});
	}
	
	public List findMingxienBykaoheid(final String id) {
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			List list;
			String hql = "from Mingxi t where t.kaoheid='"+id+"'";
			Query query = s.createQuery(hql);
			list=query.list();
			if(list!=null&&list.size()!=0)
			return list;
			
		
			else return null;
			}

		});
	}
	
	public List getChildren(final Long id) {
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			String hql = "from Zhijiankaohe t where t.parentid = " + id
					+ " and t.visible=1 order by t.id ";
			Query query = s.createQuery(hql);
			return query.list();
			}

		});
	}
	
	public void addZhijiankaohe(final Zhijiankaohe pi) throws InfrastructureException {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
		
			s.saveOrUpdate(pi);
			return null;
			}

		});	
	}
	
	public void addZhijianpingfen(final Zhijianpingfen pi) throws InfrastructureException {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
		
			s.saveOrUpdate(pi);
			return null;
			}

		});	
	}
	
	public void addZhijianpingfenmingxi(final Mingxi pi) throws InfrastructureException {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
	
			s.saveOrUpdate(pi);
			return null;
			}

		});	
	}
	
	
	public List findMingxi(final String gonghao,final String auth,final String flag,final String a,final String b) {
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			List list;
			String hql = "from Zhijianpingfen t where t.gonghao='"+gonghao+"'";
			if(!auth.equals("")){
				hql += "and t.auth='"+auth+"'";
			}
			if(!flag.equals("")){
				hql += "and t.tjflag='9'";
			}
			if(!a.equals("")&&!b.equals("")){
				hql += "and trunc(t.riqi) between to_date('"+a+"','yyyy-MM-dd')  and to_date('"+b+"','yyyy-MM-dd')";
			}
			Query query = s.createQuery(hql);
			list=query.list();
			if(list!=null&&list.size()!=0)
			return list;
			
		
			else return null;
			}

		});
	}
	
	//ÀúÊ·±í
	public List findAllFromHis() {
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			String hql = "from HisZhijiankaohe t where t.visible=1 order by t.id ";
			Query query = s.createQuery(hql);
			return query.list();
			}

		});
	}
	
	public List getFirstFromHis() {
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			String hql = "from HisZhijiankaohe t where t.parentid is null and t.visible=1 order by t.id ";
			Query query = s.createQuery(hql);
			return query.list();
			}

		});
	}
	
	public HisZhijiankaohe findZhijiankaoheByIdFromHis(final Long id) {
		return (HisZhijiankaohe) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			List list;
			String hql = "from HisZhijiankaohe t where t.id=?";
			Query query = s.createQuery(hql);
			query.setString(0, ""+id+"");
			list=query.list();
			if(list!=null&&list.size()!=0)
			return (HisZhijiankaohe)list.get(0);
			
		
			else return null;
	}

});	
	}
	
	public Zhijianpingfen findZhijianpingfenByIdFromHis(final String id) {
		return (Zhijianpingfen) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			List list;
			String hql = "from Zhijianpingfen t where t.id='"+id+"'";
			Query query = s.createQuery(hql);
			list=query.list();
			if(list!=null&&list.size()!=0)
			return (Zhijianpingfen)list.get(0);
			
		
			else return null;
			}

		});	
	}
	
	public Mingxi findMingxienByIdFormHis(final String id) {
		return (Mingxi) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			List list;
			String hql = "from Mingxi t where t.id='"+id+"'";
			Query query = s.createQuery(hql);
			list=query.list();
			if(list!=null&&list.size()!=0)
			return (Mingxi)list.get(0);
			
		
			else return null;
			}

		});	
	}
	
	public Mingxi findMingxienBykaoIdFromHis(final String kaoheid,final String id) {
		return (Mingxi) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			List list;
			String hql = "from Mingxi t where t.kaoheid='"+kaoheid+"' and t.pingfenid='"+id+"'";
			Query query = s.createQuery(hql);
			list=query.list();
			if(list!=null&&list.size()!=0)
			return (Mingxi)list.get(0);
			
		
			else return null;
			}

		});	
	}
	
	public List findMingxienBypingfenidFromHis(final String id) {
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			List list;
			String hql = "from Mingxi t where t.pingfenid='"+id+"'";
			Query query = s.createQuery(hql);
			list=query.list();
			if(list!=null&&list.size()!=0)
			return list;
			
		
			else return null;
			}

		});
	}
	
	public Zhijianpingfen findBypingfenidFromHis(final String id) {
		return (Zhijianpingfen) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			List list;
			String hql = "from Zhijianpingfen t where t.pingfenid='"+id+"'";
			Query query = s.createQuery(hql);
			list=query.list();
			if(list!=null&&list.size()!=0)
			return (Zhijianpingfen)list.get(0);
			
		
			else return null;
			}

		});
	}
	
	public List findMingxienBykaoheidFromHis(final String id) {
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			List list;
			String hql = "from Mingxi t where t.kaoheid='"+id+"'";
			Query query = s.createQuery(hql);
			list=query.list();
			if(list!=null&&list.size()!=0)
			return list;
			
		
			else return null;
			}

		});
	}
	
	public List getChildrenFromHis(final Long id) {
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			String hql = "from HisZhijiankaohe t where t.parentid = " + id
					+ " and t.visible=1 order by t.id ";
			Query query = s.createQuery(hql);
			return query.list();
			}

		});
	}
	
	public void addZhijiankaoheFromHis(final HisZhijiankaohe pi) throws InfrastructureException {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
		
			s.saveOrUpdate(pi);
			return null;
			}

		});	
	}
	
	public void addZhijianpingfenFromHis(final Zhijianpingfen pi) throws InfrastructureException {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
		
			s.saveOrUpdate(pi);
			return null;
			}

		});	
	}
	
	public void addZhijianpingfenmingxiFromHis(final Mingxi pi) throws InfrastructureException {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
	
			s.saveOrUpdate(pi);
			return null;
			}

		});	
	}
	
	
	public List findMingxiFromHis(final String gonghao,final String auth,final String flag,final String a,final String b) {
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			List list;
			String hql = "from Zhijianpingfen t where t.gonghao='"+gonghao+"'";
			if(!auth.equals("")){
				hql += "and t.auth='"+auth+"'";
			}
			if(!flag.equals("")){
				hql += "and t.tjflag='9'";
			}
			if(!a.equals("")&&!b.equals("")){
				hql += "and trunc(t.riqi) between to_date('"+a+"','yyyy-MM-dd')  and to_date('"+b+"','yyyy-MM-dd')";
			}
			Query query = s.createQuery(hql);
			list=query.list();
			if(list!=null&&list.size()!=0)
			return list;
			
		
			else return null;
			}

		});
	}
	

}

package com.wondersgroup.falcon.question.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;
import com.wondersgroup.kaoshi.bo.Admission_card_pc;
import com.wondersgroup.kaoshi.bo.PrintCardVO;

public class PrintCardDAO {
	private static final Log log = LogFactory.getLog(PrintCardDAO.class);
	
	public void add(final Admission_card_pc acp)throws Exception{
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			
			@Override
			public Object execute(Session session) throws Throwable {
				try {
					session.save(acp);
				} catch (Exception e) {
					log.error("准考证打印-信息保存失败", e);
					throw e;
				}
				return null;
			}
		});
	}
	public int querySize(final Admission_card_pc acp){
		return (Integer) HibernateUtil.doInSession(new HibernateSessionCallback() {
			
			@Override
			public Object execute(Session session) throws Throwable {
				StringBuffer sb = new StringBuffer();
				sb.append("select count(*) from ADMISSION_CARD_PC a where a.valid='1'");
				if(!"".equals(acp.getKs_name())){
					sb.append(" and a.ks_name like '%"+acp.getKs_name()+"%'");
				}
				if(!"".equals(acp.getPc_name())){
					sb.append(" and a.pc_name like '%"+acp.getPc_name()+"%'");
				}
				if(!"".equals(acp.getKd_name())){
					sb.append(" and a.kd_name like '%"+acp.getKd_name()+"%'");
				}
				Query query = session.createSQLQuery(sb.toString());
				int size =  ((BigDecimal) query.uniqueResult()).intValue();
				return size;
			}
		});
	}
	
	public int queryPrintListSize(final Admission_card_pc acp){
		return (Integer) HibernateUtil.doInSession(new HibernateSessionCallback() {
			
			@Override
			public Object execute(Session session) throws Throwable {
				StringBuffer sb = new StringBuffer();
				sb.append("select count(*) from " +
						"ADMISSION_CARD_PC a,ADMISSION_CARD_FILE b where " +
						"b.pc_id=a.id and a.valid='1' and b.valid='1' ");
				if(!"".equals(acp.getKs_name())){
					sb.append(" and a.ks_name like '%"+acp.getKs_name()+"%'");
				}
				if(!"".equals(acp.getPc_name())){
					sb.append(" and a.pc_name like '%"+acp.getPc_name()+"%'");
				}
				if(!"".equals(acp.getKd_name())){
					sb.append(" and a.kd_name like '%"+acp.getKd_name()+"%'");
				}
				Query query = session.createSQLQuery(sb.toString());
				int size =  ((BigDecimal) query.uniqueResult()).intValue();
				return size;
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public List<Admission_card_pc> queryList(
			final Admission_card_pc acp,
			final int currentPage, 
			final int pageSize){
		return (List<Admission_card_pc>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			
			@Override
			public Object execute(Session session) throws Throwable {
				
				int currentPage1=currentPage;
				if (currentPage == 0) {
					currentPage1 = 1;
				}
				
				StringBuffer sb = new StringBuffer();
				sb.append("select a.* from ADMISSION_CARD_PC a where a.valid='1'");
				if(!"".equals(acp.getKs_name())){
					sb.append(" and a.ks_name like '%"+acp.getKs_name()+"%'");
				}
				if(!"".equals(acp.getPc_name())){
					sb.append(" and a.pc_name like '%"+acp.getPc_name()+"%'");
				}
				if(!"".equals(acp.getKd_name())){
					sb.append(" and a.kd_name like '%"+acp.getKd_name()+"%'");
				}
				sb.append(" order by a.time desc");
				Query query = session.createSQLQuery(sb.toString()).addEntity(Admission_card_pc.class);
				query.setFirstResult((currentPage1 - 1) * pageSize);
				query.setMaxResults(pageSize);
				
				List<Admission_card_pc> list = query.list();
				return list;
			}
		});
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> queryPrintCardList(
			final Admission_card_pc acp,
			final int currentPage, 
			final int pageSize){
		return (List<Object[]>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			
			@Override
			public Object execute(Session session) throws Throwable {
				
				int currentPage1=currentPage;
				if (currentPage == 0) {
					currentPage1 = 1;
				}
				
				StringBuffer sb = new StringBuffer();
				sb.append("select a.id,a.pc_name,a.ks_name,a.kd_name,b.num from ADMISSION_CARD_PC a,ADMISSION_CARD_FILE b where b.pc_id=a.id and a.valid='1' and b.valid='1' ");
				if(!"".equals(acp.getKs_name())){
					sb.append(" and a.ks_name like '%"+acp.getKs_name()+"%'");
				}
				if(!"".equals(acp.getPc_name())){
					sb.append(" and a.pc_name like '%"+acp.getPc_name()+"%'");
				}
				if(!"".equals(acp.getKd_name())){
					sb.append(" and a.kd_name like '%"+acp.getKd_name()+"%'");
				}
				sb.append(" order by a.time desc");
				Query query = session.createSQLQuery(sb.toString());
				query.setFirstResult((currentPage1 - 1) * pageSize);
				query.setMaxResults(pageSize);
				
				List<Object[]> list = query.list();
				return list;
			}
		});
	}

}

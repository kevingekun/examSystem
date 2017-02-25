package com.wondersgroup.kaoshi.dao;

import java.sql.Timestamp;
import java.util.Date;

import oracle.sql.TIMESTAMP;

import org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.omg.PortableInterceptor.SUCCESSFUL;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wondersgroup.falcon.acegi.AcegiUtil;
import com.wondersgroup.falcon.acegi.UserDetailsImpl;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;
import com.wondersgroup.kaoshi.bo.EPapersSet;

public class PaperSetDAOImpl extends HibernateDaoSupport{

	public String paperAdd(final String sjmc,final String tkid,final String sjzf,final String sjType){
		return (String) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				Criteria criteria = session.createCriteria(EPapersSet.class);
				criteria.add(Restrictions.eq("sj_mc", sjmc));
				if (criteria.list().size()>0) {
					return "repeat";
				}else{
					String userid = ((UserDetailsImpl)AcegiUtil.getUserDetails()).getUser().getId().toString();
					EPapersSet ePapersSet = new EPapersSet();
					ePapersSet.setSj_mc(sjmc);
					ePapersSet.setSj_tkid(tkid);
					ePapersSet.setSj_zf(Double.valueOf(sjzf));
					ePapersSet.setSj_zjrid(userid);
					ePapersSet.setSj_zjsj(new Timestamp(System.currentTimeMillis()));
					ePapersSet.setSj_state(0);
					ePapersSet.setSj_type(Integer.valueOf(sjType));
					
					try {
						session.save(ePapersSet);
					} catch (Exception e) {
						e.printStackTrace();
						return "error";
					}
					return "success";
				}
			}
		});
	}
}

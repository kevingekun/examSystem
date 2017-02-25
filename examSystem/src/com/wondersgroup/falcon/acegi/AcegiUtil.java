package com.wondersgroup.falcon.acegi;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;
import com.wondersgroup.popedom.bo.EUser;

import net.sf.acegisecurity.Authentication; 
import net.sf.acegisecurity.UserDetails;
import net.sf.acegisecurity.context.Context;
import net.sf.acegisecurity.context.ContextHolder;
import net.sf.acegisecurity.context.security.SecureContext;

public class AcegiUtil { 

	public static UserDetails getUserDetails() {
		
		UserDetailsImpl userDetailsImpl = null;
		
		Context context = ContextHolder.getContext();
		if (context != null) {
			if (context instanceof SecureContext) {
				SecureContext sc = (SecureContext)context;
				Authentication auth = sc.getAuthentication();
				if (auth != null) {
					userDetailsImpl = (UserDetailsImpl)auth.getPrincipal();
				}
			}
		}
		
		return userDetailsImpl;
	}
	
	public static EUser getEUser() {
		return (EUser) HibernateUtil
				.doInSession(new HibernateSessionCallback() {

					public Object execute(Session session) throws Throwable {
						String username = AcegiUtil.getUserDetails()
								.getUsername();
						Criteria criteria = session.createCriteria(EUser.class);
						criteria.add(Restrictions.eq("username", username))
								.add(Restrictions.eq("enabled", (byte) 1));
						if (criteria.list().size() > 0) {
							EUser eUser = (EUser) criteria.list().get(0);
							return eUser;
						} else {
							return null;
						}
					}
				});
	}
}

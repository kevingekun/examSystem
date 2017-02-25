package com.wondersgroup.falcon.acegi;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.wondersgroup.falcon.dao.auth.UserDAO;
import com.wondersgroup.falcon.model.auth.Authority;
import com.wondersgroup.falcon.model.auth.User;
import com.wondersgroup.popedom.bo.EAuthority;
import com.wondersgroup.popedom.bo.EUser;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;

import net.sf.acegisecurity.GrantedAuthority;
import net.sf.acegisecurity.GrantedAuthorityImpl;
import net.sf.acegisecurity.UserDetails;
import net.sf.acegisecurity.providers.dao.AuthenticationDao;
import net.sf.acegisecurity.providers.dao.UsernameNotFoundException;

public class AuthenticationDaoImpl implements AuthenticationDao {
	private UserDAO userDAO;

	private static final Log logger = LogFactory.getLog(AuthenticationDaoImpl.class);
	
	public AuthenticationDaoImpl() {
	}
	
	public UserDetails loadUserByUsername(String username)
		throws UsernameNotFoundException, DataAccessException {
		
		UserDetailsImpl userDetailsImpl = null;
		
		try {
			EUser user = userDAO.getUserByUsername(username);
			System.out.println("username===>>>"+username);
			if (user == null) {
				throw new UsernameNotFoundException("User not found");
			}
			//��ɫ����
			ArrayList listAuths = new ArrayList();
			listAuths.add(new GrantedAuthorityImpl("ROLE_ANONYMOUS"));
			for (Iterator i = user.getAuthorities().iterator(); i.hasNext();) {
				EAuthority authority = (EAuthority)i.next();
				System.out.println(authority.getName());
				listAuths.add(new GrantedAuthorityImpl(authority.getName()));
			}
			//�ѽ�ɫ�ŵ������������
			GrantedAuthority[] arrayAuths = {};
			arrayAuths = (GrantedAuthority[])listAuths.toArray(arrayAuths);
			Byte userEnabled = user.getEnabled();
			
			boolean enabled, accountNonExpired, credentialsNonExpired, accountNonLocked;
			if (userEnabled.equals(UserDAO.ENABLED) || userEnabled.equals(UserDAO.LOCKED))
				enabled = true;
			else
				enabled = false;
			if (userEnabled.equals(UserDAO.DISABLED))
				accountNonExpired = false;
			else
				accountNonExpired = true;
			credentialsNonExpired = true;
			if (userEnabled.equals(UserDAO.LOCKED))
				accountNonLocked = false;
			else
				accountNonLocked = true;
			
			
			userDetailsImpl = new UserDetailsImpl(user, enabled,
					accountNonExpired, credentialsNonExpired, accountNonLocked,
					arrayAuths);

		} catch (Exception ex) {
//			HibernateUtil.rollbackTransaction();
			logger.error("loadUserByUsername: ", ex);
			System.out.println(ex);
			throw new DataRetrievalFailureException("loadUserByUsername error: ");
		} finally {
//			HibernateUtil.closeSession();
		}
		
		return userDetailsImpl;
	}
	
	public UserDetails loadUserByUsernameAndPassword(String username,String password)
			throws UsernameNotFoundException, DataAccessException {
			
			UserDetailsImpl userDetailsImpl = null;
			
			try {
				EUser user = userDAO.getUserByUsernameAndPassword(username, password);
				System.out.println("username===>>>"+username);
				if (user == null) {
					throw new UsernameNotFoundException("User not found");
				}
				//��ɫ����
				ArrayList listAuths = new ArrayList();
				listAuths.add(new GrantedAuthorityImpl("ROLE_ANONYMOUS"));
				for (Iterator i = user.getAuthorities().iterator(); i.hasNext();) {
					EAuthority authority = (EAuthority)i.next();
					System.out.println(authority.getName());
					listAuths.add(new GrantedAuthorityImpl(authority.getName()));
				}
				//�ѽ�ɫ�ŵ������������
				GrantedAuthority[] arrayAuths = {};
				arrayAuths = (GrantedAuthority[])listAuths.toArray(arrayAuths);
				Byte userEnabled = user.getEnabled();
				
				boolean enabled, accountNonExpired, credentialsNonExpired, accountNonLocked;
				if (userEnabled.equals(UserDAO.ENABLED) || userEnabled.equals(UserDAO.LOCKED))
					enabled = true;
				else
					enabled = false;
				if (userEnabled.equals(UserDAO.DISABLED))
					accountNonExpired = false;
				else
					accountNonExpired = true;
				credentialsNonExpired = true;
				if (userEnabled.equals(UserDAO.LOCKED))
					accountNonLocked = false;
				else
					accountNonLocked = true;
				
				
				userDetailsImpl = new UserDetailsImpl(user, enabled,
						accountNonExpired, credentialsNonExpired, accountNonLocked,
						arrayAuths);

			} catch (Exception ex) {
//				HibernateUtil.rollbackTransaction();
				logger.error("loadUserByUsername: ", ex);
				System.out.println(ex);
				throw new DataRetrievalFailureException("loadUserByUsername error: ");
			} finally {
//				HibernateUtil.closeSession();
			}
			
			return userDetailsImpl;
		}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
}

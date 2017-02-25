package net.sf.acegisecurity.providers.dao;

import net.sf.acegisecurity.UserDetails;
import org.springframework.dao.DataAccessException;

public abstract interface AuthenticationDao
{
  public abstract UserDetails loadUserByUsername(String paramString)
    throws UsernameNotFoundException, DataAccessException;
  
  public abstract UserDetails loadUserByUsernameAndPassword(String paramString,String paramString2)
		    throws UsernameNotFoundException, DataAccessException;
}
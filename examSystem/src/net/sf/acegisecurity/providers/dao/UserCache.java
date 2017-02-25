package net.sf.acegisecurity.providers.dao;

import net.sf.acegisecurity.UserDetails;

public abstract interface UserCache
{
  public abstract UserDetails getUserFromCache(String paramString);

  public abstract void putUserInCache(UserDetails paramUserDetails);

  public abstract void removeUserFromCache(String paramString);
}
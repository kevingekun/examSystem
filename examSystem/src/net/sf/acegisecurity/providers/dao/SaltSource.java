package net.sf.acegisecurity.providers.dao;

import net.sf.acegisecurity.UserDetails;

public abstract interface SaltSource
{
  public abstract Object getSalt(UserDetails paramUserDetails);
}
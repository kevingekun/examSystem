package net.sf.acegisecurity.providers.dao;

import net.sf.acegisecurity.BadCredentialsException;

public class UsernameNotFoundException extends BadCredentialsException
{
  public UsernameNotFoundException(String msg)
  {
    super(msg);
  }

  public UsernameNotFoundException(String msg, Throwable t)
  {
    super(msg, t);
  }
}
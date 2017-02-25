package net.sf.acegisecurity.providers.dao;

import net.sf.acegisecurity.AccountExpiredException;
import net.sf.acegisecurity.Authentication;
import net.sf.acegisecurity.AuthenticationException;
import net.sf.acegisecurity.AuthenticationServiceException;
import net.sf.acegisecurity.BadCredentialsException;
import net.sf.acegisecurity.CredentialsExpiredException;
import net.sf.acegisecurity.DisabledException;
import net.sf.acegisecurity.GrantedAuthority;
import net.sf.acegisecurity.LockedException;
import net.sf.acegisecurity.UserDetails;
import net.sf.acegisecurity.providers.AuthenticationProvider;
import net.sf.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import net.sf.acegisecurity.providers.dao.cache.NullUserCache;
import net.sf.acegisecurity.providers.dao.event.AuthenticationFailureAccountExpiredEvent;
import net.sf.acegisecurity.providers.dao.event.AuthenticationFailureAccountLockedEvent;
import net.sf.acegisecurity.providers.dao.event.AuthenticationFailureCredentialsExpiredEvent;
import net.sf.acegisecurity.providers.dao.event.AuthenticationFailureDisabledEvent;
import net.sf.acegisecurity.providers.dao.event.AuthenticationFailurePasswordEvent;
import net.sf.acegisecurity.providers.dao.event.AuthenticationFailureUsernameNotFoundEvent;
import net.sf.acegisecurity.providers.dao.event.AuthenticationSuccessEvent;
import net.sf.acegisecurity.providers.encoding.PasswordEncoder;
import net.sf.acegisecurity.providers.encoding.PlaintextPasswordEncoder;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.dao.DataAccessException;
import org.springframework.util.Assert;

public class DaoAuthenticationProvider
  implements AuthenticationProvider, InitializingBean, ApplicationContextAware
{
  private ApplicationContext context;
  private AuthenticationDao authenticationDao;
  private PasswordEncoder passwordEncoder;
  private SaltSource saltSource;
  private UserCache userCache;
  private boolean forcePrincipalAsString;
  private boolean hideUserNotFoundExceptions;

  public DaoAuthenticationProvider()
  {
    this.passwordEncoder = new PlaintextPasswordEncoder();

    this.userCache = new NullUserCache();
    this.forcePrincipalAsString = false;
    this.hideUserNotFoundExceptions = true;
  }

  public void setApplicationContext(ApplicationContext applicationContext)
    throws BeansException
  {
    this.context = applicationContext;
  }

  public void setAuthenticationDao(AuthenticationDao authenticationDao) {
    this.authenticationDao = authenticationDao;
  }

  public AuthenticationDao getAuthenticationDao() {
    return this.authenticationDao;
  }

  public ApplicationContext getContext() {
    return this.context;
  }

  public void setForcePrincipalAsString(boolean forcePrincipalAsString) {
    this.forcePrincipalAsString = forcePrincipalAsString;
  }

  public boolean isForcePrincipalAsString() {
    return this.forcePrincipalAsString;
  }

  public void setHideUserNotFoundExceptions(boolean hideUserNotFoundExceptions)
  {
    this.hideUserNotFoundExceptions = hideUserNotFoundExceptions;
  }

  public boolean isHideUserNotFoundExceptions() {
    return this.hideUserNotFoundExceptions;
  }

  public void setPasswordEncoder(PasswordEncoder passwordEncoder)
  {
    this.passwordEncoder = passwordEncoder;
  }

  public PasswordEncoder getPasswordEncoder() {
    return this.passwordEncoder;
  }

  public void setSaltSource(SaltSource saltSource)
  {
    this.saltSource = saltSource;
  }

  public SaltSource getSaltSource() {
    return this.saltSource;
  }

  public void setUserCache(UserCache userCache) {
    this.userCache = userCache;
  }

  public UserCache getUserCache() {
    return this.userCache;
  }

  public void afterPropertiesSet() throws Exception {
    Assert.notNull(this.authenticationDao, "An Authentication DAO must be set");
    Assert.notNull(this.userCache, "A user cache must be set");
  }

  public Authentication authenticate(Authentication authentication)
    throws AuthenticationException
  {
    String username = "NONE_PROVIDED";
	String password = "NONE_PASSWORD";

    if (authentication.getPrincipal() != null) {
      username = authentication.getPrincipal().toString();
	  password = authentication.getCredentials().toString();
    }

    if (authentication.getPrincipal() instanceof UserDetails) {
      username = ((UserDetails)authentication.getPrincipal()).getUsername();
	  password = ((UserDetails)authentication.getPrincipal()).getPassword();
    }

    boolean cacheWasUsed = true;
    UserDetails user = this.userCache.getUserFromCache(username);

    if (user == null) {
      cacheWasUsed = false;
      try
      {
        user = getUserFromBackend2(username,password);
      } catch (BadCredentialsException ex) {
        if (this.context != null) {
          this.context.publishEvent(new AuthenticationFailureUsernameNotFoundEvent(authentication, new User(username, "*****", false, false, false, false, new GrantedAuthority[0])));
        }

        throw ex;
      }
    }

    if (!(user.isEnabled())) {
      if (this.context != null) {
        this.context.publishEvent(new AuthenticationFailureDisabledEvent(authentication, user));
      }

      throw new DisabledException("User is disabled");
    }

    if (!(user.isAccountNonExpired())) {
      if (this.context != null) {
        this.context.publishEvent(new AuthenticationFailureAccountExpiredEvent(authentication, user));
      }

      throw new AccountExpiredException("User account has expired");
    }

    if (!(user.isAccountNonLocked())) {
      if (this.context != null) {
        this.context.publishEvent(new AuthenticationFailureAccountLockedEvent(authentication, user));
      }

      throw new LockedException("User account is locked");
    }

    if (!(user.isCredentialsNonExpired())) {
      if (this.context != null) {
        this.context.publishEvent(new AuthenticationFailureCredentialsExpiredEvent(authentication, user));
      }

      throw new CredentialsExpiredException("User credentials have expired");
    }

    if (!(isPasswordCorrect(authentication, user)))
    {
      if (cacheWasUsed) {
        cacheWasUsed = false;
        user = getUserFromBackend2(username,password);
      }

      if (!(isPasswordCorrect(authentication, user))) {
        if (this.context != null) {
          this.context.publishEvent(new AuthenticationFailurePasswordEvent(authentication, user));
        }

        throw new BadCredentialsException("Bad credentials presented");
      }
    }

    if (!(cacheWasUsed))
    {
      this.userCache.putUserInCache(user);

      if (this.context != null) {
        this.context.publishEvent(new AuthenticationSuccessEvent(authentication, user));
      }

    }

    Object principalToReturn = user;

    if (this.forcePrincipalAsString) {
      principalToReturn = user.getUsername();
    }

    return createSuccessAuthentication(principalToReturn, authentication, user);
  }

  public boolean supports(Class authentication)
  {
    return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
  }

  protected boolean isPasswordCorrect(Authentication authentication, UserDetails user)
  {
    Object salt = null;

    if (this.saltSource != null) {
      salt = this.saltSource.getSalt(user);
    }

    return this.passwordEncoder.isPasswordValid(user.getPassword(), authentication.getCredentials().toString(), salt);
  }

  protected Authentication createSuccessAuthentication(Object principal, Authentication authentication, UserDetails user)
  {
    UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(principal, authentication.getCredentials(), user.getAuthorities());

    result.setDetails((authentication.getDetails() != null) ? authentication.getDetails() : null);

    return result;
  }

  private UserDetails getUserFromBackend(String username)
  {
    UserDetails loadedUser;
    try {
      loadedUser = this.authenticationDao.loadUserByUsername(username);
    } catch (UsernameNotFoundException notFound) {
      if (this.hideUserNotFoundExceptions)
        throw new BadCredentialsException("Bad credentials presented");

      throw notFound;
    }
    catch (DataAccessException repositoryProblem) {
      throw new AuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
    }

    if (loadedUser == null) {
      throw new AuthenticationServiceException("AuthenticationDao returned null, which is an interface contract violation");
    }

    return loadedUser;
  }
  private UserDetails getUserFromBackend2(String username,String password)
  {
    UserDetails loadedUser;
    try {
      loadedUser = this.authenticationDao.loadUserByUsernameAndPassword(username,password);
    } catch (UsernameNotFoundException notFound) {
      if (this.hideUserNotFoundExceptions)
        throw new BadCredentialsException("Bad credentials presented");

      throw notFound;
    }
    catch (DataAccessException repositoryProblem) {
      throw new AuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
    }

    if (loadedUser == null) {
      throw new AuthenticationServiceException("AuthenticationDao returned null, which is an interface contract violation");
    }

    return loadedUser;
  }
}
package com.wondersgroup.falcon.acegi;

import org.springframework.util.Assert;

import com.wondersgroup.falcon.model.auth.User;
import com.wondersgroup.popedom.bo.EUser;

import net.sf.acegisecurity.GrantedAuthority;
import net.sf.acegisecurity.UserDetails;

public class UserDetailsImpl implements UserDetails {

    //~ Instance fields ========================================================

	private static final long serialVersionUID = 3240945400620987034L;
	
    private EUser user;
    private String username;
    private String password;
    private GrantedAuthority[] authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    //~ Constructors ===========================================================

    /**
     * Construct the <code>UserDetailsImpl</code> with the details required by {@link
     * DaoAuthenticationProvider}.
     *
     * @param username the username presented to the
     *        <code>DaoAuthenticationProvider</code>
     * @param password the password that should be presented to the
     *        <code>DaoAuthenticationProvider</code>
     * @param realname the user's real name
     * @param agentId agent id used to login CTI
     * @param AcdGroup acd group userd to login CTI
     * @param enabled set to <code>true</code> if the user is enabled
     * @param accountNonExpired set to <code>true</code> if the account has not
     *        expired
     * @param credentialsNonExpired set to <code>true</code> if the credentials
     *        have not expired
     * @param accountNonLocked set to <code>true</code> if the account is not
     *        locked
     * @param authorities the authorities that should be granted to the caller
     *        if they presented the correct username and password and the user
     *        is enabled
     *
     * @throws IllegalArgumentException if a <code>null</code> value was passed
     *         either as a parameter or as an element in the
     *         <code>GrantedAuthority[]</code> array
     */
    public UserDetailsImpl(EUser user, boolean enabled,
        boolean accountNonExpired, boolean credentialsNonExpired,
        boolean accountNonLocked, GrantedAuthority[] authorities)
        throws IllegalArgumentException {
    	
    	this.username = user.getUsername();
    	this.password = user.getPassword();
        if (((username == null) || "".equals(username)) || (password == null)
            || (authorities == null)) {
            throw new IllegalArgumentException(
                "Cannot pass null or empty values to constructor");
        }

        for (int i = 0; i < authorities.length; i++) {
            Assert.notNull(authorities[i], "Granted authority element "
                    + i
                    + " is null - GrantedAuthority[] cannot contain any null elements");
        }

        this.user = user;
        this.enabled = enabled;
        this.authorities = authorities;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;
    }

    protected UserDetailsImpl() {
        throw new IllegalArgumentException("Cannot use default constructor");
    }

    //~ Methods ================================================================

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    public GrantedAuthority[] getAuthorities() {
        return authorities;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Get user.
     */
    public EUser getUser() {
        return user;
    }

    /**
     * Get user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Get user's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Get acd group.
     * 
     * @return
     */
    public String getAcdGroup() {
		return user.getGroup().getAcdGroup();
	}

    /**
     * Get agent id.
     * 
     * @return
     */
	public String getAgentId() {
		return user.getAgentId();
	}

	/**
	 * Get user's real name.
	 * 
	 * @return
	 */
	public String getRealname() {
		return user.getRealname();
	}

	public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(super.toString() + ": ");
        sb.append("Username: " + this.username + "; ");
        sb.append("Password: [PROTECTED]; ");
        sb.append("Realname: " + getRealname() + "; ");
        sb.append("AgentId: " + getAgentId() + "; ");
        sb.append("AcdGroup: " + getAcdGroup() + "; ");
        sb.append("Enabled: " + this.enabled + "; ");
        sb.append("AccountNonExpired: " + this.accountNonExpired + "; ");
        sb.append("credentialsNonExpired: " + this.credentialsNonExpired + "; ");
        sb.append("AccountNonLocked: " + this.accountNonLocked + "; ");

        if (this.getAuthorities() != null) {
            sb.append("Granted Authorities: ");

            for (int i = 0; i < this.getAuthorities().length; i++) {
                if (i > 0) {
                    sb.append(", ");
                }

                sb.append(this.getAuthorities()[i].toString());
            }
        } else {
            sb.append("Not granted any authorities");
        }

        return sb.toString();
    }

}

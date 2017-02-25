// Decompiled by DJ v2.9.9.60 Copyright 2000 Atanas Neshkov  Date: 2009-8-21 13:38:37
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   AuthenticationProcessingFilter.java

package net.sf.acegisecurity.ui.webapp;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wondersgroup.falcon.persistence.HibernateUtil;

import net.sf.acegisecurity.*;
import net.sf.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import net.sf.acegisecurity.ui.AbstractProcessingFilter;
import net.sf.acegisecurity.ui.WebAuthenticationDetails;

public class AuthenticationProcessingFilter extends AbstractProcessingFilter
{
	private static Log log = LogFactory.getLog(AuthenticationProcessingFilter.class);

    public AuthenticationProcessingFilter()
    {
    	super.setAuthenticationFailureUrl("/acegilogin.jsp?login_error=1");
    }

    public String getDefaultFilterProcessesUrl()
    {
        return "/j_acegi_security_check";
    }

    public Authentication attemptAuthentication(HttpServletRequest request)
        throws AuthenticationException
    {
        String username = obtainUsername(request).replace("x", "X").trim();
        String password = obtainPassword(request).trim();
        String state = (String) request.getSession().getAttribute("state");
        if("admin".equals(state)){
        	super.setAuthenticationFailureUrl("/adminlogin.jsp?login_error=1");
        }else{
        	super.setAuthenticationFailureUrl("/acegilogin.jsp?login_error=1");
        }
        if(username == null)
            username = "";
        if(password == null)
            password = "";
        
        //���봦�?��������������� 
        //-------------------------------------------
        try {
			username = new String(username.getBytes("iso-8859-1"), "gb2312");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
        setDetails(request, authRequest);
        
//        log.info("==================================");
//        log.info("ACEGI_SECURITY_LAST_USERNAME��"+username);
//        log.info("==================================");

        request.getSession().setAttribute("ACEGI_SECURITY_LAST_USERNAME", username);
        
        //ϵͳ����
        String j_usertype =request.getParameter("j_usertype");
        String sofephonetype=request.getParameter("sofephonetype");
        if(j_usertype ==null ){
        	j_usertype="";
        }
        request.getSession().setAttribute("sofephonetype", sofephonetype);
        request.getSession().setAttribute("j_usertype", j_usertype);
//        log.info("==================================");
//        log.info("ѡ���½ϵͳ���ͣ�"+j_usertype);
//        log.info("==================================");
        
        return getAuthenticationManager().authenticate(authRequest);
    }

    public void init(FilterConfig filterconfig)
        throws ServletException
    {
    }

    protected void setDetails(HttpServletRequest request, UsernamePasswordAuthenticationToken authRequest)
    {
        authRequest.setDetails(new WebAuthenticationDetails(request));
    }

    protected String obtainPassword(HttpServletRequest request)
    {
        return request.getParameter("j_password");
    }

    protected String obtainUsername(HttpServletRequest request)
    {
        return request.getParameter("j_username");
    }

	public static final String ACEGI_SECURITY_FORM_USERNAME_KEY = "j_username";
    public static final String ACEGI_SECURITY_FORM_PASSWORD_KEY = "j_password";
    public static final String ACEGI_SECURITY_LAST_USERNAME_KEY = "ACEGI_SECURITY_LAST_USERNAME";
}
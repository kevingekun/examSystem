// Decompiled by DJ v2.9.9.60 Copyright 2000 Atanas Neshkov  Date: 2009-8-21 13:41:44
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   AuthenticationProcessingFilterEntryPoint.java

package net.sf.acegisecurity.ui.webapp;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.acegisecurity.AuthenticationException;
import net.sf.acegisecurity.intercept.web.AuthenticationEntryPoint;
import net.sf.acegisecurity.util.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

public class AuthenticationProcessingFilterEntryPoint
    implements AuthenticationEntryPoint, InitializingBean
{

    public AuthenticationProcessingFilterEntryPoint()
    {
        portMapper = new PortMapperImpl();
        portResolver = new PortResolverImpl();
        forceHttps = false;
    }

    public void setForceHttps(boolean forceHttps)
    {
        this.forceHttps = forceHttps;
    }

    public boolean getForceHttps()
    {
        return forceHttps;
    }

    public void setLoginFormUrl(String loginFormUrl)
    {
        this.loginFormUrl = loginFormUrl;
    }

    public String getLoginFormUrl()
    {
        return loginFormUrl;
    }

    public void setPortMapper(PortMapper portMapper)
    {
        this.portMapper = portMapper;
    }

    public PortMapper getPortMapper()
    {
        return portMapper;
    }

    public void setPortResolver(PortResolver portResolver)
    {
        this.portResolver = portResolver;
    }

    public PortResolver getPortResolver()
    {
        return portResolver;
    }

    public void afterPropertiesSet()
        throws Exception
    {
        Assert.hasLength(loginFormUrl, "loginFormUrl must be specified");
        Assert.notNull(portMapper, "portMapper must be specified");
        Assert.notNull(portResolver, "portResolver must be specified");
    }

    public void commence(ServletRequest request, ServletResponse response, AuthenticationException authException)
        throws IOException, ServletException
    {
        HttpServletRequest req = (HttpServletRequest)request;
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        int serverPort = portResolver.getServerPort(request);
        String contextPath = req.getContextPath();
        StringBuffer rUrl = req.getRequestURL();
        String u = rUrl.substring(40, rUrl.length());
        if(u.equals("adminlogin.jsp")){
        	this.loginFormUrl = "/adminlogin.jsp";
        }
        boolean includePort = true;
        if("http".equals(scheme.toLowerCase()) && serverPort == 80)
            includePort = false;
        if("https".equals(scheme.toLowerCase()) && serverPort == 443)
            includePort = false;
        String redirectUrl = scheme + "://" + serverName + (includePort ? ":" + serverPort : "") + contextPath + loginFormUrl;
        if(forceHttps && req.getScheme().equals("http"))
        {
            Integer httpPort = new Integer(portResolver.getServerPort(request));
            Integer httpsPort = portMapper.lookupHttpsPort(httpPort);
            if(httpsPort != null)
            {
                if(httpsPort.intValue() == 443)
                    includePort = false;
                else
                    includePort = true;
                redirectUrl = "https://" + serverName + (includePort ? ":" + httpsPort : "") + contextPath + loginFormUrl;
            }
        }
        if(logger.isDebugEnabled())
            logger.debug("Redirecting to: " + redirectUrl);
        ((HttpServletResponse)response).sendRedirect(((HttpServletResponse)response).encodeRedirectURL(redirectUrl));
    }



    private static final Log logger;
    private PortMapper portMapper;
    private PortResolver portResolver;
    private String loginFormUrl;
    private boolean forceHttps;

    static 
    {
        logger = LogFactory.getLog(net.sf.acegisecurity.ui.webapp.AuthenticationProcessingFilterEntryPoint.class);
    }
}
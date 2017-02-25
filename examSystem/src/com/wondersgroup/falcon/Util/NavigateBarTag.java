package com.wondersgroup.falcon.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import javax.servlet.jsp.JspException;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class NavigateBarTag
        extends TagSupport {
    private String navigateform;
    private String actionName;
    private String formName;

    public int doStartTag()
            throws JspException {

        HttpServletRequest request = (HttpServletRequest) pageContext.
                                     getRequest();
        NavigateForm navigateForm = (NavigateForm) request.getAttribute(
                navigateform);
        int total = navigateForm.getTotal();
        int currpage = navigateForm.getCurrpage();
        int pagesize = navigateForm.getPagesize();
        int pagenum = navigateForm.getPagenum();
        int pages = pagesize > 0 ?
                    (total / pagesize + ((total % pagesize == 0) ? 0 : 1)) :
                    total;

        StringBuffer result = new StringBuffer();
        
        result.append("<div class=\"page1\">&nbsp;共").append(total).append("条记录&nbsp;&nbsp;第").append(currpage).append("页/共").append(pages).append("页").append("\n");
        result.append("&nbsp;分页：").append("\n");
        result.append("&nbsp;<a href=\"Javascript:goto('1')\" title=\"首页\"><FONT class=\"sign2\">9</FONT></a>").append("\n");
        if (currpage > pagenum) {
            result.append("&nbsp;<a href=\"Javascript:goto('").append(((currpage-1)/pagenum)*pagenum).append("')\" title=\"前").append(pagenum).append("页\"><FONT class=\"sign2\">7</FONT></a>").append("\n");
        }
        for (int i = ((currpage - 1) / pagenum) * pagenum + 1;
                     (i <= ((currpage - 1) / pagenum + 1) * pagenum &&
                      i <= pages); i++) {
            result.append("    <a href=\"Javascript:goto('").append(i).append("')\">");
            if (i == currpage) {
                result.append("<font \"sign2\" color=\"red\">").append(i).append("</font>");
            } else {
                result.append(i);
            }
            result.append("    </a>").append("\n");
        }
        if(((currpage-1)/pagenum+1)*pagenum+1<=pages){
            result.append("    <a href=\"Javascript:goto('").append(((currpage-1)/pagenum+1)*pagenum+1).append("')\" title=\"后").append(pagenum).append("页\"><FONT class=\"sign2\">8</FONT></a>");
        }
        result.append("    <a href=\"Javascript:goto('").append(pages).append("')\" title=\"尾页\"><FONT class=\"sign\">:</FONT></a>");
        result.append("    转到<input id=\"gotopage\" name=\"gotopage\" type=\"text\" size=\"2\" onkeypress=\"allowKeyScope(48,58)\">页</div> <img src=\"").append(request.getContextPath()+"/images/GO.jpg").append("\" width=\"31\" height=\"21\" border=\"0\" onclick=\"goto2").append(formName).append("();\">")
        	.append(" &nbsp;");
        result.append("<input type=\"hidden\" id=\"currpage\" name=\"currpage\" value=\"").append(currpage).append("\">").append("\n");
        result.append("<input type=\"hidden\" id=\"pagesize\" name=\"pagesize\" value=\"").append(pagesize).append("\">").append("\n");
        result.append("<input type=\"hidden\" id=\"pagenum\" name=\"pagenum\" value=\"").append(pagenum).append("\">").append("\n");
        result.append("<script language=\"JavaScript\">").append("\n");
        result.append("//<!--").append("\n");
        result.append("function goto(page){").append("\n");
        result.append("	document.").append(formName).append(".action=\"").append(actionName).append("\";").append("\n");
        result.append("	document.getElementById(\"currpage\").value=page;").append("\n");
        result.append("	document.").append(formName).append(".submit();").append("\n");
        result.append("}").append("\n");
        result.append("\n");
        result.append("function goto2").append(formName).append("() {").append("\n");
        result.append("var gotopagewww=document.getElementById(\"gotopage\").value;").append("\n");
        result.append("	if(checkNum(gotopagewww)){").append("\n");
        result.append("		if(gotopagewww > ").append(pages).append("){").append("\n");
        result.append("			goto(").append(pages).append(");").append("\n");
        result.append("		}else if(gotopagewww <=0 ){").append("\n");
        result.append("			goto(1);").append("\n");
        result.append("		}else{").append("\n");
        result.append("			goto(gotopagewww);").append("\n");
        result.append("		}").append("\n");
        result.append("	}").append("\n");
        result.append("	else{").append("\n");
        result.append("		alert(\"下列输入不符合规范：页码必须是整数！\\n\\n\");").append("\n");
        result.append("	}").append("\n");
        result.append("}").append("\n");
        result.append("\n");
        result.append("function allowKeyScope(from,to){").append("\n");
        result.append("	if( event.keyCode < from || event.keyCode > to ) {").append("\n");
        result.append("		event.returnValue=false;").append("\n");
        result.append("	}").append("\n");
        result.append("}").append("\n");
        result.append("\n");
        result.append("function checkNum(str){").append("\n");
        result.append("	var strCheck = str + \"\";").append("\n");
        result.append("	if(strCheck.length < 1){").append("\n");
        result.append("		return false;").append("\n");
        result.append("	}").append("\n");
        result.append("	if(!check(strCheck,\"0123456789\")){").append("\n");
        result.append("		return false; ").append("\n");
        result.append("	}").append("\n");
        result.append("	return true;").append("\n");
        result.append("}").append("\n");
        result.append("\n");
        result.append("function check(srcStr,compareToStr)").append("\n");
        result.append("{").append("\n");
        result.append("	var i,j;").append("\n");
        result.append("	for (i=0;i<srcStr.length;i++){").append("\n");
        result.append("		j=compareToStr.indexOf(srcStr.charAt(i));").append("\n");
        result.append("		if (j==-1){").append("\n");
        result.append("			return false;").append("\n");
        result.append("		}").append("\n");
        result.append("	}").append("\n");
        result.append("	return true;").append("\n");
        result.append("}").append("\n");
        result.append("//-->").append("\n");
        result.append("</script>").append("\n");
        result.append("<!------------------------------------------------------------------------------------------->").append("\n");

        JspWriter writer = pageContext.getOut();
        try {
            writer.print(result);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
            }
        return 0;

    }

    public void setNavigateform(String navigateform) {
        this.navigateform = navigateform;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getNavigateform() {
        return navigateform;
    }

    public String getActionName() {
        return actionName;
    }

    public String getFormName() {
        return formName;
    }


}

/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2018-04-15 17:16:49 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class home_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n");
      out.write("<html>\n");
      out.write("\n");
      out.write("\t<head>\n");
      out.write("\t\t<meta charset=\"UTF-8\">\n");
      out.write("\t\t<title>公告栏、预警信息和代办事宜</title>\n");
      out.write("\t\t<!-- 导入jquery核心类库 -->\n");
      out.write("\t\t<script type=\"text/javascript\" src=\"./js/jquery-1.8.3.js\"></script>\n");
      out.write("\t\t<!-- 导入easyui类库 -->\n");
      out.write("\t\t<link rel=\"stylesheet\" type=\"text/css\" href=\"./js/easyui/themes/default/easyui.css\">\n");
      out.write("\t\t<link rel=\"stylesheet\" type=\"text/css\" href=\"./js/easyui/themes/icon.css\">\n");
      out.write("\t\t<link rel=\"stylesheet\" type=\"text/css\" href=\"./js/easyui/ext/portal.css\">\n");
      out.write("\t\t<link rel=\"stylesheet\" type=\"text/css\" href=\"./css/default.css\">\n");
      out.write("\t\t<script type=\"text/javascript\" src=\"./js/easyui/jquery.easyui.min.js\"></script>\n");
      out.write("\t\t<script type=\"text/javascript\" src=\"./js/easyui/ext/jquery.portal.js\"></script>\n");
      out.write("\t\t<script type=\"text/javascript\" src=\"./js/easyui/ext/jquery.cookie.js\"></script>\n");
      out.write("\t\t<script type=\"text/javascript\">\n");
      out.write("\t\t\t$(function() {\n");
      out.write("\t\t\t\tpanels = [ {\n");
      out.write("\t\t\t\t\tid : 'p1',\n");
      out.write("\t\t\t\t\ttitle : '公共栏',\n");
      out.write("\t\t\t\t\theight : 255,\n");
      out.write("\t\t\t\t\tcollapsible : true,\n");
      out.write("\t\t\t\t\thref:'./pages/portal/gonggao.html'\n");
      out.write("\t\t\t\t}, {\n");
      out.write("\t\t\t\t\tid : 'p2',\n");
      out.write("\t\t\t\t\ttitle : '代办事宜',\n");
      out.write("\t\t\t\t\theight : 255,\n");
      out.write("\t\t\t\t\tcollapsible : true,\n");
      out.write("\t\t\t\t\thref:'./pages/portal/daiban.html'\n");
      out.write("\t\t\t\t}, {\n");
      out.write("\t\t\t\t\tid : 'p3',\n");
      out.write("\t\t\t\t\ttitle : '预警信息',\n");
      out.write("\t\t\t\t\theight : 255,\n");
      out.write("\t\t\t\t\tcollapsible : true,\n");
      out.write("\t\t\t\t\thref:'./pages/portal/yujing.html'\n");
      out.write("\t\t\t\t}, {\n");
      out.write("\t\t\t\t\tid : 'p4',\n");
      out.write("\t\t\t\t\ttitle : '系统BUG反馈',\n");
      out.write("\t\t\t\t\theight : 255,\n");
      out.write("\t\t\t\t\tcollapsible : true,\n");
      out.write("\t\t\t\t\thref:'./pages/portal/bug.html'\n");
      out.write("\t\t\t\t}];\n");
      out.write("\t\t\t\t $('#layout_portal_portal').portal({\n");
      out.write("\t\t\t\t\tborder : false,\n");
      out.write("\t\t\t\t\tfit : true\n");
      out.write("\t\t\t\t });\n");
      out.write("\t\t\t\tvar state = state = 'p1,p2:p3,p4';/*冒号代表列，逗号代表行*/\n");
      out.write("\t\t\n");
      out.write("\t\t\t\taddPortalPanels(state);\n");
      out.write("\t\t\t\t$('#layout_portal_portal').portal('resize');\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t});\n");
      out.write("\t\t\t\n");
      out.write("\t\t\tfunction getPanelOptions(id) {\n");
      out.write("\t\t\t\tfor ( var i = 0; i < panels.length; i++) {\n");
      out.write("\t\t\t\t\tif (panels[i].id == id) {\n");
      out.write("\t\t\t\t\t\treturn panels[i];\n");
      out.write("\t\t\t\t\t}\n");
      out.write("\t\t\t\t}\n");
      out.write("\t\t\t\treturn undefined;\n");
      out.write("\t\t\t}\n");
      out.write("\t\t\tfunction getPortalState() {\n");
      out.write("\t\t\t\tvar aa=[];\n");
      out.write("\t\t\t\tfor(var columnIndex=0;columnIndex<2;columnIndex++) {\n");
      out.write("\t\t\t\t\tvar cc=[];\n");
      out.write("\t\t\t\t\tvar panels=$('#layout_portal_portal').portal('getPanels',columnIndex);\n");
      out.write("\t\t\t\t\tfor(var i=0;i<panels.length;i++) {\n");
      out.write("\t\t\t\t\t\tcc.push(panels[i].attr('id'));\n");
      out.write("\t\t\t\t\t}\n");
      out.write("\t\t\t\t\taa.push(cc.join(','));\n");
      out.write("\t\t\t\t}\n");
      out.write("\t\t\t\treturn aa.join(':');\n");
      out.write("\t\t\t}\n");
      out.write("\t\t\tfunction addPortalPanels(portalState) {\n");
      out.write("\t\t\t\tvar columns = portalState.split(':');\n");
      out.write("\t\t\t\tfor (var columnIndex = 0; columnIndex < columns.length; columnIndex++) {\n");
      out.write("\t\t\t\t\tvar cc = columns[columnIndex].split(',');\n");
      out.write("\t\t\t\t\tfor (var j = 0; j < cc.length; j++) {\n");
      out.write("\t\t\t\t\t\tvar options = getPanelOptions(cc[j]);\n");
      out.write("\t\t\t\t\t\tif (options) {\n");
      out.write("\t\t\t\t\t\t\tvar p = $('<div/>').attr('id', options.id).appendTo('body');\n");
      out.write("\t\t\t\t\t\t\tp.panel(options);\n");
      out.write("\t\t\t\t\t\t\t$('#layout_portal_portal').portal('add', {\n");
      out.write("\t\t\t\t\t\t\t\tpanel : p,\n");
      out.write("\t\t\t\t\t\t\t\tcolumnIndex : columnIndex\n");
      out.write("\t\t\t\t\t\t\t});\n");
      out.write("\t\t\t\t\t\t}\n");
      out.write("\t\t\t\t\t}\n");
      out.write("\t\t\t\t}\n");
      out.write("\t\t\t}\n");
      out.write("\t\t</script>\n");
      out.write("\t</head>\n");
      out.write("\n");
      out.write("\t<body>\n");
      out.write("\t\t<div id=\"layout_portal_portal\" style=\"position:relative;height:600px;\">\n");
      out.write("\t\t\t<div></div>\n");
      out.write("\t\t\t<div></div>\n");
      out.write("\t\t</div>\n");
      out.write("\t</body>\n");
      out.write("\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}

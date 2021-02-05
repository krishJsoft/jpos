<%@ page import="java.util.*"%>

<%
	Enumeration e;
	e = request.getHeaderNames();
	String userAgent = request.getHeader("user-agent");

	if(userAgent.matches(".*Android.*")) {
		%><jsp:forward page="mobileIndex.xhtml" /><%
		
	} else {
		%><jsp:forward page="index.xhtml" /><%
	}
%>

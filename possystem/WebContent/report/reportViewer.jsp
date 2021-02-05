<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tlds/birt.tld" prefix="birt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>
	<c:set var="reportNum" value="${param.reportNum}" />
	<c:set var="reportName"
		value="report/purchaseRequestListReport.rptdesign" />
	<c:set var="designFolder" value="report/designs/" />
	<birt:viewer id="birtViewer" reportDesign="${reportName}"
		pattern="/frameset" height="930" title="Sample XML Report"
		width="1000" format="html" scrolling="yes" showParameterPage="false"
		showToolBar="true" showNavigationBar="true">

		<birt:param name="referenceNo" value="PR40003822013121149">
		</birt:param>

	</birt:viewer>
</body>
</html>
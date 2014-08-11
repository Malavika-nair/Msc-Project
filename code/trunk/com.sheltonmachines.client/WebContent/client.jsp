<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Get Image</title>
</head>
<body>
  <form action="ClientServlet" method="post">
      <center>
      <%--Page heading --%>
		<table cellpadding=4 cellspacing=2 border=0>
		<th bgcolor="#CCCCFF" colspan=2>
		<font size=5>Backend interface</font>
		<br>
		</th>
		<%--Submit button to show image --%>
		<tr bgcolor="#c8d8f8">
		<td  align=center colspan=2>
		<input type="submit" value="Show Image">
		</td>
		</tr>
	  </table>
	</center>
   </form>
</body>
</html>
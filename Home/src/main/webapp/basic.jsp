<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="utf-8">
	<title>Insert title here</title>
</head>
<body>
	<%
		// Thiết lập tập kí tự cần lấy để đề phòng phiên bản java không hỗ trợ tiếng việt
		request.setCharacterEncoding("utf-8");
		// Tìm tham số lấy giá trị
		String name = request.getParameter("txtName");
		if(name != null) {
			out.append("Xin chào: ").append(name);
		} else {
	%>
	<form action="basic.jsp" method="post">
		Nhập tên bạn: 
		<input type="text" name="txtName">
		<button type="submit">Gửi</button>
	</form>
	<%} %>
</body>
</html>
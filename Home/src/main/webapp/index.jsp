<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- flush="true" để đảm bảo header phải load xong trước -->
<jsp:include page="header.jsp" flush="true"></jsp:include>

<main id="main">
	<jsp:include page="heroslide.jsp" flush="true">
		<jsp:param value="abc" name="abc" />
	</jsp:include>

	<jsp:include page="postGrid.jsp"></jsp:include>

	<jsp:include page="culture.jsp"></jsp:include>

	<jsp:include page="business.jsp"></jsp:include>

	<jsp:include page="lifestyles.jsp"></jsp:include>
</main>
<!-- End #main -->

<jsp:include page="footer.jsp" flush="true"></jsp:include>

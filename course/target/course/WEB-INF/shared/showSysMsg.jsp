<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:if test="${not empty errorMsg}">
	<script>
		ShowErrMsg("${errorMsg}");
	</script>
	<c:set var="errorMsg" value="null" />
</c:if>

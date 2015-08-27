<%@ tag pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="id" required="false" rtexprvalue="true"
	description="分页页面对应的id"%>
<%@ attribute name="pageUrl" required="true" rtexprvalue="true"
	description="分页页面对应的URl"%>
<%@ attribute name="pageAttrKey" required="true" rtexprvalue="true"
	description="Page对象在Request域中的键名称"%>
<c:set var="pageUrl" value="${pageUrl}" />
<%
	String separator = pageUrl.indexOf("?") > -1 ? "&" : "?";
	jspContext.setAttribute("pageResult",
			request.getAttribute(pageAttrKey));
	jspContext.setAttribute("pageUrl", pageUrl);
	jspContext.setAttribute("separator", separator);
%>


<c:choose>

	<c:when
		test="${pageResult.totalPageCount >1 && pageResult.totalPageCount <=10}">
		<div class="navPage">

			<div class="navPageRight">
				<nav style="text-align: right;">

					<ul class="pagination pagination-sm">
						<c:forEach var="i" begin="1" end="10" step="1">

							<c:if test="${pageResult.totalPageCount>=i}">
								<c:choose>
									<c:when test="${pageResult.currentPageNo ==i}">
										<li class="active"><a href="#">${i} <span
												class="sr-only">(current)</span></a></li>
									</c:when>
									<c:otherwise>
										<li><a
											href="<c:url value="${pageUrl}"/>${separator}pageNo=${i}">${i} <span
												class="sr-only"></span></a></li>
									</c:otherwise>
								</c:choose>
							</c:if>
						</c:forEach>

					</ul>
				</nav>
			</div>

		</div>

	</c:when>
	<c:when test="${pageResult.totalPageCount >10}">
		<div class="navPage">

			<div class="navPageRight">
				<nav style="text-align: right;">

					<ul class="pagination pagination-sm">

						<c:choose>
							<c:when test="${pageResult.currentPageNo <=1}">
								<li class="disabled"><span
										aria-hidden="true">首页</span></li>
							</c:when>

							<c:otherwise>
								<li><a
									href="<c:url value="${pageUrl}"/>${separator}pageNo=1"
									aria-label="Previous"><span aria-hidden="true">首页</span></a></li>
							</c:otherwise>
						</c:choose>

						<c:choose>
							<c:when test="${pageResult.hasPreviousPage}">
								<li><a
									href="<c:url value="${pageUrl}"/>${separator}pageNo=${pageResult.currentPageNo -1 }"
									aria-label="Previous"><span aria-hidden="true">上一页</span></a></li>
							</c:when>

							<c:otherwise>
								<li class="disabled"><span
										aria-hidden="true">上一页</span></li>
							</c:otherwise>
						</c:choose>








						<c:choose>
							<c:when test="${pageResult.hasNextPage}">
								<li><a
									href="<c:url value="${pageUrl}"/>${separator}pageNo=${pageResult.currentPageNo +1 }"
									aria-label="Next"><span aria-hidden="true">下一页</span></a></li>
							</c:when>

							<c:otherwise>
								<li class="disabled"><span
										aria-hidden="true">下一页</span></li>
							</c:otherwise>
						</c:choose>

						<c:choose>
							<c:when
								test="${pageResult.currentPageNo >= pageResult.totalPageCount}">
								<li class="disabled"><span
										aria-hidden="true">末页</span></li>
							</c:when>

							<c:otherwise>
								<li><a
									href="<c:url value="${pageUrl}"/>${separator}pageNo=${pageResult.totalPageCount }"
									aria-label="Previous"><span aria-hidden="true">末页</span></a></li>
							</c:otherwise>
						</c:choose>

					</ul>
				</nav>
			</div>

			<div class="navPageLeft">
				<span class="label label-info">共${pageResult.totalPageCount}页</span>
			</div>

		</div>
	</c:when>

</c:choose>





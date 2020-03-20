<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page language="java" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page language="java" import="manageruser.entities.UserInfo"%>
<%@page language="java" import="manageruser.entities.mst_group"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="z" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="./View/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="./View/js/user.js"></script>
<title>ユーザ管理</title>
</head>
<%
	String grId = request.getParameter("group_id");
	int groupId = 0;
	if (grId != null && !"".equals(grId)) {
		groupId = Integer.parseInt(grId);
		request.getSession().setAttribute("group_id", groupId);
	}
	int numbNext = 0, numbPrev = 0;
	Object numbNextObject = request.getAttribute("nextPage");
	Object numbPrevObject = request.getAttribute("prevPage");
	if (numbNextObject != null) {
		numbNext = Integer.parseInt(numbNextObject.toString());
	}
	if (numbPrevObject != null) {
		numbPrev = Integer.parseInt(numbPrevObject.toString());
	}

%>
<body>
	<!-- Begin vung header -->
	<z:Header></z:Header>
	<!-- End vung header -->
	<!-- Begin vung dieu kien tim kiem -->
	<form action="${pageContext.request.contextPath}/listUser.do"
		method="get" name="mainform">
		<table class="tbl_input" border="0" width="90%" cellpadding="0"
			cellspacing="0">
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>会員名称で会員を検索します。検索条件無しの場合は全て表示されます。</td>
			</tr>
			<tr>
				<td width="100%">
					<table class="tbl_input" cellpadding="4" cellspacing="0">
						<input type="hidden" value="search" name="type" />
						<tr>
							<td class="lbl_left">氏名:</td>
							<td align="left"><input class="txBox" type="text"
								name="keyWord" value='${keyWord}' size="20"
								onfocus="this.style.borderColor='#0066ff';"
								onblur="this.style.borderColor='#aaaaaa';" /></td>
							<td></td>
						</tr>
						<tr>
							<td class="lbl_left">グループ:</td>
							<td align="left" width="80px"><select name="group_id">
									<option value="0">全て</option>
									<c:set var="groupId" value="${groupId}" scope="request"></c:set>
									<c:forEach items="${listAllGroup}" var="item">
										<option value="${item.group_id}"
											${(item.group_id == groupId) ? 'selected' : '' }><c:out
												value="${item.group_name}" /></option>
									</c:forEach>
							</select></td>
							<td align="left"><input class="btn" type="submit" value="検索" />
								<input class="btn" type="button" onclick="openAddEditForm();"
								value="新規追加" /></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<!-- End vung dieu kien tim kiem -->
	</form>
	<!-- Begin vung hien thi danh sach user -->
	<c:if test="${not empty messError}">
		<p>検索条件に該当するユーザが見つかりません。</p>
	</c:if>

	<c:if test="${not empty listUserInfo}">
		<table class="tbl_list" border="1" cellpadding="4" cellspacing="0"
			width="80%">

			<tr class="tr2">
				<th align="center" width="20px">ID</th>
				<th align="left">氏名 
				<c:url value="/listUser.do"
						var="urlSortByFullName" scope="request">
						<c:param name="keyWord" value="${keyWord}" />
						<c:param name="group_id" value="${groupId}" />
						<c:param name="type" value="${sort}" />
						<c:param name="sortByFullName" value="${sortByFullName == DESC ? ASC : DESC}" />
						<c:param name="sortByCodeLevel" value="${sortByCodeLevel}" />
						<c:param name="sortByEndDate" value="${sortByEndDate}" />
						<c:param name="sortType" value="${listSort[0]}" />
					</c:url>
					<a href="${urlSortByFullName}">${sortByFullName == ASC ? '▲▽' : '▼△'}</a> 
				</th>
				<th align="left">生年月日</th>
				<th align="left">グループ</th>
				<th align="left">メールアドレス</th>
				<th align="left" width="70px">電話番号</th>
				<th align="left">日本語能力
				<c:url value="/listUser.do"
					var="urlSortByCodeLevel" scope="request">
					<c:param name="keyWord" value="${keyWord}" />
					<c:param name="group_id" value="${groupId}" />
					<c:param name="type" value="${sort}" />
					<c:param name="sortByFullName" value="${sortByFullName}" />
					<c:param name="sortByCodeLevel" value="${sortByCodeLevel == DESC ? ASC : DESC}" />
					<c:param name="sortByEndDate" value="${sortByEndDate}" />
					<c:param name="sortType" value="${listSort[1]}" />
				</c:url>
				 <a
					href="${urlSortByCodeLevel }">${sortByCodeLevel == ASC ? '▲▽' : '▼△'}
				</a>

				</th>
				<th align="left">失効日 
				<c:url value="/listUser.do"
					var="urlSortByEndDate" scope="request">
					<c:param name="keyWord" value="${keyWord}" />
					<c:param name="group_id" value="${groupId}" />
					<c:param name="type" value="${sort}" />
					<c:param name="sortByFullName" value="${sortByFullName}" />
						<c:param name="sortByCodeLevel" value="${sortByCodeLevel}" />
					<c:param name="sortByEndDate" value="${sortByEndDate == DESC ? ASC : DESC}" />
					<c:param name="sortType" value="${listSort[2]}" />
				</c:url>
				<a
					href="${urlSortByEndDate}">${sortByEndDate == ASC ? '▲▽' : '▼△'}
				</a>
				</th>
				<th align="left">点数</th>
			</tr>
			<c:forEach items="${listUserInfo}" var="user">
				<tr>
					<td><a
						href="${pageContext.request.contextPath}/userInfo.do?userId=${fn:escapeXml(user.user_id)}"><c:out
								value="${fn:escapeXml(user.user_id)}" /></a></td>
					<td><c:out value="${fn:escapeXml(user.full_name)}" /></td>
					<td><c:out value="${fn:escapeXml(user.birthday)}" /></td>
					<td><c:out value="${fn:escapeXml(user.group_name)}" /></td>
					<td><c:out value="${fn:escapeXml(user.email)}" /></td>
					<td><c:out value="${fn:escapeXml(user.tel)}" /></td>
					<td><c:out value="${fn:escapeXml(user.name_level)}" /></td>
					<td><c:out value="${fn:escapeXml(user.end_date)}" /></td>
					<td><c:out value="${fn:escapeXml(user.total)}" /></td>
				</tr>
			</c:forEach>

		</table>
		<!-- End vung hien thi danh sach user -->

		<!-- Begin vung paging -->
		<table>
			</c:if>
			<tr>
				<c:set var="numbPre" value="<%=numbPrev%>" scope="request"></c:set>
				<td><c:if test="${numbPre != 0}">
					<c:url value="/listUser.do"
							var="urlPagePrev" scope="request">
							<c:param name="keyWord" value="${keyWord}" />
							<c:param name="group_id" value="${groupId}" />
							<c:param name="type" value="${paging}" />
							<c:param name="sortByFullName" value="${sortByFullName}" />
							<c:param name="sortByCodeLevel" value="${sortByCodeLevel}" />
							<c:param name="sortByEndDate" value="${sortByEndDate}" />
							<c:param name="sortType" value="${sortType}" />
							<c:param name="currentPage" value="${numbPre}" />
					</c:url>
						<a
							href="${urlPagePrev}"><<</a></td>
				</c:if>
				<c:forEach items="${listPaging}" var="item">
					<c:if test="${item == currentPage}">
						<td class="lbl_paging"><a style="text-decoration: none">
								<c:out value="${item}" />
						</a> &nbsp;</td>
					</c:if>
					<c:if test="${item != currentPage}">
						<td class="lbl_paging"><a
							href="${pageContext.request.contextPath}/listUser.do?
							keyWord=${keyWord}
							&group_id=${groupId}
							&currentPage=${item}
							&type=${paging}&sortType=${sortType}
							&sortByFullName=${sortByFullName}
							&sortByCodeLevel=${sortByCodeLevel}
							&sortByEndDate=${sortByEndDate}"><c:out
									value="${item}" /> </a> &nbsp;</td>
					</c:if>
				</c:forEach>
				<td><c:set var="numbNext" value="<%=numbNext%>" scope="request"></c:set>
					
					<c:if test="${numbNext != 0 }">
					<c:url value="/listUser.do"
						var="urlPageNext" scope="request">
						<c:param name="keyWord" value="${keyWord}" />
						<c:param name="group_id" value="${groupId}" />
						<c:param name="type" value="${paging}" />
						<c:param name="sortByFullName" value="${sortByFullName}" />
						<c:param name="sortByCodeLevel" value="${sortByCodeLevel}" />
						<c:param name="sortByEndDate" value="${sortByEndDate}" />
						<c:param name="sortType" value="${sortType}" />
						<c:param name="currentPage" value="${numbNext }" />
					</c:url>
						<a href="${urlPageNext}">>></a></td>
					</c:if>
			</tr>
		</table>
		<!-- End vung paging -->
		<!-- Begin vung footer -->
		<z:Footer></z:Footer>
		<!-- End vung footer -->
</body>
<script>
	function openAddEditForm () {
		window.location.href = "${pageContext.request.contextPath}/addEditForm.do";
	}
</script>
</html>
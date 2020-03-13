<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page language="java" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page language="java" import="manageruser.entities.UserInfo"%>
<%@page language="java" import="manageruser.entities.mst_group"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="z" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="./View/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="./View/js/user.js"></script>
<title>ユーザ管理</title>
</head>
<%
	String keyWord = request.getParameter("keyWord");
	if (keyWord != null) {
		request.getSession().setAttribute("keyWord", keyWord);
	} else {
		keyWord = "";
	}
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
						<tr>
							<td class="lbl_left">氏名:</td>
							<td align="left"><input class="txBox" type="text"
								name="keyWord" value='<%=keyWord%>' size="20"
								onfocus="this.style.borderColor='#0066ff';"
								onblur="this.style.borderColor='#aaaaaa';" /></td>
							<td></td>
						</tr>
						<tr>
							<td class="lbl_left">グループ:</td>
							<td align="left" width="80px"><select name="group_id">
									<option value="0">全て</option>
									<c:set var="groupId" value="<%=groupId%>" scope="request"></c:set>
									<c:forEach items="${listAllGroup}" var="item">
										<c:if test="${item.group_id eq groupId}">
											<option value="${item.group_id}" selected><c:out
													value="${item.group_name}" /></option>
										</c:if>
										<option value="${item.group_id}"><c:out
												value="${item.group_name}" /></option>
									</c:forEach>
							</select></td>
							<td align="left"><input class="btn" type="submit" value="検索" />
								<input class="btn" type="button" onclick="openAddEditForm();" value="新規追加" /></td>
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
					<c:if test="${sortByFullName eq ASC}">
						<a
							href="${pageContext.request.contextPath}/listUser.do?keyWord=${keyWord}&group_id=${groupId}
							&currentPage=1&sortType=${listSort[0]}
							&sortByFullName=${DESC}
							&sortByCodeLevel=${sortByCodeLevel}
							&sortByEndDate=${sortByEndDate}">▲▽
						</a>
					</c:if> 
					<c:if test="${sortByFullName eq DESC}">
						<a
							href="${pageContext.request.contextPath}/listUser.do?keyWord=${keyWord}&group_id=${groupId}
							&currentPage=1&sortType=${listSort[0]}
							&sortByFullName=${ASC}
							&sortByCodeLevel=${sortByCodeLevel}
							&sortByEndDate=${sortByEndDate}">▼△</a>
					</c:if>
				</th>
				<th align="left">生年月日</th>
				<th align="left">グループ</th>
				<th align="left">メールアドレス</th>
				<th align="left" width="70px">電話番号</th>
				<th align="left">日本語能力 
					<c:if test="${sortByCodeLevel eq ASC}">
						<a
							href="${pageContext.request.contextPath}/listUser.do?keyWord=${keyWord}&group_id=${groupId}
							&currentPage=1&sortType=${listSort[1]}
							&sortByFullName=${sortByFullName}
							&sortByCodeLevel=${DESC}
							&sortByEndDate=${sortByEndDate}">▲▽
						</a>
					</c:if> 
					<c:if test="${sortByCodeLevel eq DESC}">
						<a
							href="${pageContext.request.contextPath}/listUser.do?keyWord=${keyWord}&group_id=${groupId}
							&currentPage=1&sortType=${listSort[1]}
							&sortByFullName=${sortByFullName}
							&sortByCodeLevel=${ASC}
							&sortByEndDate=${sortByEndDate}">▼△
							</a>
					</c:if>
				</th>
				<th align="left">失効日 <c:if test="${sortByEndDate eq ASC}">
						<a
							href="${pageContext.request.contextPath}/listUser.do?keyWord=${keyWord}&group_id=${groupId}
							&currentPage=1&sortType=${listSort[2]}
							&sortByFullName=${sortByFullName}
							&sortByCodeLevel=${sortByCodeLevel}
							&sortByEndDate=${DESC}">▲▽</a>
					</c:if> 
					<c:if test="${sortByEndDate eq DESC}">
						<a
							href="${pageContext.request.contextPath}/listUser.do?keyWord=${keyWord}&group_id=${groupId}
							&currentPage=1&sortType=${listSort[2]}
							&sortByFullName=${sortByFullName}
							&sortByCodeLevel=${sortByCodeLevel}
							&sortByEndDate=${ASC}">▼△</a>
					</c:if>
				</th>
				<th align="left">点数</th>
			</tr>
			<c:forEach items="${listUserInfo}" var="user">
				<tr>
					<td><c:out value="${fn:escapeXml(user.user_id)}" /></td>
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
						<a
							href="${pageContext.request.contextPath}/listUser.do?keyWord=${keyWord}
							&group_id=${groupId}
							&currentPage=<%=numbPrev%>
							&sortType=${sortType}
							&sortByFullName=${sortByFullName}
							&sortByCodeLevel=${DESC}
							&sortByEndDate=${sortByEndDate}"><<</a></td>
				</c:if>
				<c:forEach items="${listPaging}" var="item">
					<c:if test="${item == currentPage}">
						<td class="lbl_paging"><a style="text-decoration:none"
							href="${pageContext.request.contextPath}/listUser.do?
							keyWord=${keyWord}
							&group_id=${groupId}
							&currentPage=${item}
							&sortType=${sortType}
							&sortByFullName=${sortByFullName}
							&sortByCodeLevel=${DESC}
							&sortByEndDate=${sortByEndDate}"><c:out
									value="${item}"  />
									</a> &nbsp;
						</td>
					</c:if>
					<c:if test="${item != currentPage}">
					<td class="lbl_paging"><a
							href="${pageContext.request.contextPath}/listUser.do?
							keyWord=${keyWord}
							&group_id=${groupId}
							&currentPage=${item}
							&sortType=${sortType}
							&sortByFullName=${sortByFullName}
							&sortByCodeLevel=${DESC}
							&sortByEndDate=${sortByEndDate}"><c:out
									value="${item}" />
									</a> &nbsp;</td>
									</c:if>
				</c:forEach>
				<td><c:set var="numbNext" value="<%=numbNext%>" scope="request"></c:set>
					<c:if test="${numbNext != 0 }">
						<a
							href="${pageContext.request.contextPath}/listUser.do?keyWord=${keyWord}
							&group_id=${groupId}
							&currentPage=<%=numbNext%>
							&sortType=${sortType}
							&sortByFullName=${sortByFullName}
							&sortByCodeLevel=${DESC}
							&sortByEndDate=${sortByEndDate}">>></a></td>
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
		window.location.href = "${pageContext.request.contextPath}/addEdit.do";
	}
</script>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page language="java" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page language="java" import="manageruser.entities.UserInfo"%>
<%@page language="java" import="manageruser.entities.mst_group"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="./View/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/user.js"></script>
<title>ユーザ管理</title>
</head>
<%
	String keyWord = request.getParameter("keyWord");
	if (keyWord != null && !"".equals(keyWord)) {
		request.getSession().setAttribute("keyWord", keyWord);
	} else {
		keyWord = "";
	}
	String grId = request.getParameter("group_id");
	int groupId = 0;
	if (grId != null && !"".equals(grId)) {
		groupId = Integer.parseInt(grId);
		request.getSession().setAttribute("group_id", keyWord);
	}
%>
<body>
	<!-- Begin vung header -->
	<div>
		<div>
			<table>
				<tr>
					<td width="80%"><img src="../images/logo-manager-user.gif"
						alt="Luvina" />
						<td>
							<td align="left"><a
								href="${pageContext.request.contextPath}/logout.do">ログアウト</a>
								&nbsp; <a href="${pageContext.request.contextPath}/listUser.do">トップ</a>
								<td>
				</tr>
			</table>
		</div>
	</div>

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
								<input class="btn" type="button" value="新規追加" /></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<!-- End vung dieu kien tim kiem -->
	</form>
	<!-- Begin vung hien thi danh sach user -->
	<table class="tbl_list" border="1" cellpadding="4" cellspacing="0"
		width="80%">

		<tr class="tr2">
			<th align="center" width="20px">ID</th>
			<th align="left">氏名 <a href="">▲▽</a>
			</th>
			<th align="left">生年月日</th>
			<th align="left">グループ</th>
			<th align="left">メールアドレス</th>
			<th align="left" width="70px">電話番号</th>
			<th align="left">日本語能力 <a href="">▲▽</a>
			</th>
			<th align="left">失効日 <a href="">△▼</a>
			</th>
			<th align="left">点数</th>
		</tr>
		<c:forEach items="${listUserInfo}" var="user">
			<tr>
				<td><c:out value="${user.user_id}" /></td>
				<td><c:out value="${user.full_name}" /></td>
				<td><c:out value="${user.birthday}" /></td>
				<td><c:out value="${user.group_name}" /></td>
				<td><c:out value="${user.email}" /></td>
				<td><c:out value="${user.tel}" /></td>
				<td><c:out value="${user.name_level}" /></td>
				<td><c:out value="${user.end_date}" /></td>
				<td><c:out value="${user.total}" /></td>
			</tr>
		</c:forEach>

	</table>
	<!-- End vung hien thi danh sach user -->

	<!-- Begin vung paging -->
	<table>
		<tr>
			<td class="lbl_paging"><a href="#">1</a> &nbsp;<a href="#">2</a>
				&nbsp;<a href="#">3</a>&nbsp;<a href="#">>></a></td>
		</tr>
	</table>
	<!-- End vung paging -->

	<!-- Begin vung footer -->
	<div class="lbl_footer">
		<br><br><br><br> Copyright © 2010 ルビナソフトウエア株式会社.
						All rights reserved. 
	</div>
	<!-- End vung footer -->

</body>

</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page language="java" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="z" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page language="java" import="manageruser.entities.UserInfo"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${pageContext.request.contextPath}/View/css/style.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/View/js/user.js"></script>
<title>ユーザ管理</title>
</head>
<body>
	<!-- Begin vung header -->
	<z:Header></z:Header>
	<!-- End vung header --
	<!-- Begin vung input-->
	<form action="${pageContext.request.contextPath}/addEditForm.do" method="get" name="inputform">
		<table class="tbl_input" border="0" width="75%" cellpadding="0"
			cellspacing="0">
			<tr>
				<th align="left">
					<div style="padding-left: 100px;">情報確認</div>
					<div style="padding-left: 100px;">&nbsp;</div>
				</th>
			</tr>
			<tr>
				<input type="hidden" value="${userInfo.getUser_id()}" name="userId" />
				<td align="left">
					<div style="padding-left: 100px;">
						<table border="1" width="70%" class="tbl_input" cellpadding="4"
							cellspacing="0">
							<tr>
								<td class="lbl_left">アカウント名:</td>
								<td align="left">${fn:escapeXml(userInfo.getLogin_name())}</td>
							</tr>
							<tr>
								<td class="lbl_left">グループ:</td>
								<td align="left">${fn:escapeXml(userInfo.getGroup_name())}</td>
							</tr>
							<tr>
								<td class="lbl_left">氏名:</td>
								<td align="left">${fn:escapeXml(userInfo.getFull_name())}</td>
							</tr>
							<tr>
								<td class="lbl_left">カタカナ氏名:</td>
								<td align="left">${fn:escapeXml(userInfo.getFull_name_kana())}</td>
							</tr>
							<tr>
								<td class="lbl_left">生年月日:</td>
								<td align="left">${fn:escapeXml(userInfo.getBirthday())}</td>
							</tr>
							<tr>
								<td class="lbl_left">メールアドレス:</td>
								<td align="left">${fn:escapeXml(userInfo.getEmail())}</td>
							</tr>
							<tr>
								<td class="lbl_left">電話番号:</td>
								<td align="left">${fn:escapeXml(userInfo.getTel())}</td>
							</tr>
							<tr>
								<th colspan="2"><a href="#" onclick="toggleJp();">日本語能力</a></th>
							</tr>
							<tr class="jp-level">
								<td class="lbl_left">資格:</td>
								<td align="left">${fn:escapeXml(userInfo.getName_level())}
								</td>
							</tr>
							<tr class="jp-level">
								<td class="lbl_left">資格交付日:</td>
								<td align="left">${fn:escapeXml(userInfo.getStart_date())}</td>
							</tr>
							<tr class="jp-level">
								<td class="lbl_left">失効日:</td>
								<td align="left">${fn:escapeXml(userInfo.getEnd_date())}</td>
							</tr>
							<tr class="jp-level">
								<td class="lbl_left">点数:</td>
								<td align="left">${fn:escapeXml(userInfo.getTotal())}</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
		</table>
		<div style="padding-left: 100px;">&nbsp;</div>
		<!-- Begin vung button -->
		<div style="padding-left: 100px;">
			<table border="0" cellpadding="4" cellspacing="0" width="300px">
				<tr>
					<th width="200px" align="center">&nbsp;</th>
					<td><input class="btn" type="submit" value="編集" /></td>
					<td><input class="btn" type="button" value="削除"
						onclick="deleteUser()" /></td>
					<td><input class="btn" type="button" value="戻る"
						onclick="backPage()" /></td>
				</tr>
			</table>
			<!-- End vung button -->
	</form>
	<!-- End vung input -->

	<!-- Begin vung footer -->
	<z:Footer></z:Footer>
	<!-- End vung footer -->
	<script>
		function deleteUser() {
			var url = new URL(window.location.href);
			var query_string = url.search;
			var search_params = new URLSearchParams(query_string); 
			search_params.append('isDelete', 'true');
			url.search = search_params.toString();
			var new_url = url.toString();
			console.log(new_url);
			var isDelete = confirm("削除しますが、よろしいでしょうか。");
			if (isDelete) {
				window.location.href = new_url;	
			}
		}
		<c:if test="${userInfo.getName_level() eq null}" >
		    var jpLevel = document.getElementsByClassName("jp-level");
	        for(var i = 0; i < jpLevel.length ; i++){
	            jpLevel[i].style.visibility  = 'hidden';
	        }
		</c:if>
	</script>
</body>

</html>
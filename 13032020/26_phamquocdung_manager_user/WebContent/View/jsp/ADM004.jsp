<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page language="java" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="z" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
	<form
		action="${pageContext.request.contextPath}/addUser.do"
		method="post" name="inputform" onsubmit="handleClick()">
		<input type="hidden" value="${userId }" name="user_id" />
		<table class="tbl_input" border="0" width="75%" cellpadding="0"
			cellspacing="0">
			<tr>
				<th align="left">
					<div style="padding-left: 100px;">
						情報確認<br> 入力された情報をＯＫボタンクリックでＤＢへ保存してください 
					</div>
					<div style="padding-left: 100px;">&nbsp;</div>
				</th>
			</tr>
			<tr>
				<td align="left">
					<div style="padding-left: 100px;">
						<table border="1" width="70%" class="tbl_input" cellpadding="4"
							cellspacing="0">
							<tr>
								<td class="lbl_left">アカウント名:</td>
								<td align="left"><input type="hidden" value="${login_name}"
									name="login_name" />${fn:escapeXml(login_name)}<input type="hidden"
									value="${password}" name="password" /></td>
							</tr>
							<tr>
								<td class="lbl_left">グループ:</td>
								<td align="left"><input type="hidden" value="${group_id}"
									name="group_id" />${fn:escapeXml(groupName)}</td>
							</tr>
							<tr>
								<td class="lbl_left">氏名:</td>
								<td align="left"><input type="hidden" value="${fullName}"
									name="fullName" />${fn:escapeXml(fullName)}</td>
							</tr>
							<tr>
								<td class="lbl_left">カタカナ氏名:</td>
								<td align="left"><input type="hidden" value="${nameKata}"
									name="nameKata" />${fn:escapeXml(nameKata)}</td>
							</tr>
							<tr>
								<td class="lbl_left">生年月日:</td>
								<td align="left"><input type="hidden" value="${birthDay}"
									name="birthDay" /> <fmt:formatDate pattern="dd-MM-yyyy"
										value="${birthDay}" /></td>
							</tr>
							<tr>
								<td class="lbl_left">メールアドレス:</td>
								<td align="left"><input type="hidden" value="${email}"
									name="email" />${fn:escapeXml(email)}</td>
							</tr>
							<tr>
								<td class="lbl_left">電話番号:</td>
								<td align="left"><input type="hidden" value="${tel}"
									name="tel" />${fn:escapeXml(tel)}</td>
							</tr>
							<tr>
								<th colspan="2"><a href="#">日本語能力</a></th>
							</tr>
							<tr>
								<td class="lbl_left">資格:</td>
								<td align="left"><input type="hidden" value="${code_level}"
									name="code_level" />${fn:escapeXml(code_level)}</td>
							</tr>
							<tr>
								<td class="lbl_left">資格交付日:</td>
								<td align="left"><input type="hidden"
									value="${startDateCodeLevel}" name="startDateCodeLevel" /> <fmt:formatDate
										pattern="dd-MM-yyyy" value="${startDateCodeLevel}" /></td>
							</tr>
							<tr>
								<td class="lbl_left">失効日:</td>
								<td align="left"><input type="hidden"
									value="${endDateCodeLevel}" name="endDateCodeLevel" /> <fmt:formatDate
										pattern="dd-MM-yyyy" value="${endDateCodeLevel}" /></td>
							</tr>
							<tr>
								<td class="lbl_left">点数:</td>
								<td align="left"><input type="hidden" value="${total}"
									name="total" />${fn:escapeXml(total)}</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
		</table>
		<div style="padding-left: 100px;">&nbsp;</div>
		<!-- Begin vung button -->
		<div style="padding-left: 45px;">
			<table border="0" cellpadding="4" cellspacing="0" width="300px">
				<tr>
					<th width="200px" align="center">&nbsp;</th>
					<td><input class="btn" type="submit" value="OK"
						id="submit-btn" /></td>
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
		function handleClick() {
			document.getElementById("submit-btn").disabled = true;
		}
	</script>
</body>

</html>
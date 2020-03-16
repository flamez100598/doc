<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page language="java" import="java.util.*"%>
<%@ taglib prefix="z" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html
	PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="./View/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="./View/js/user.js"></script>
<title>ユーザ管理</title>
</head>

<body>
	<!-- Begin vung header -->
	<z:Header></z:Header>
	<!-- End vung header -->

	<!-- Begin vung input-->
	<form action="${pageContext.request.contextPath}/listUser.do"
		method="post" name="inputform">
		<table class="tbl_input" border="0" width="80%" cellpadding="0"
			cellspacing="0">
			<tr>
				<td align="center" colspan="2">
					<div style="height: 50px"></div>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">ユーザの登録が完了しました。 or ユーザの更新が完了しました。
					or ユーザの削除が完了しました。</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<div style="height: 70px"></div>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2"><input class="btn" type="submit"
					value="OK" onclick="" /></td>
			</tr>
		</table>
	</form>
	<!-- End vung input -->

	<!-- Begin vung footer -->
	<z:Footer></z:Footer>
	<!-- End vung footer -->
</body>

</html>
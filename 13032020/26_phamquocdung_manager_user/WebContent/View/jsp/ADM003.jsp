<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page language="java" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	String codeLevel = request.getParameter("code_level");
	if (grId != null && !"".equals(grId)) {
		request.getSession().setAttribute("code_level", codeLevel);
	}
%>
<body>
	<!-- Begin vung header -->
	<z:Header></z:Header>
	<!-- End vung header -->

	<!-- Begin vung input-->
	<form action="${pageContext.request.contextPath}/addEdit.do"
		method="post" name="inputform">
		<table class="tbl_input" border="0" width="75%" cellpadding="0"
			cellspacing="0">
			<tr>
				<th align="left">
					<div style="padding-left: 100px;">会員情報編集</div>
				</th>
			</tr>
			<tr>
				<td class="errMsg">
					<div style="padding-left: 120px">
						<c:forEach items="${listErr}" var="mes">
							<p style="margin: 0">
								<c:out value="${mes}" />
								&nbsp;
							</p>
						</c:forEach>
						&nbsp;
					</div>
				</td>
			</tr>
			<tr>
				<td align="left">
					<div style="padding-left: 100px;">
						<table border="0" width="100%" class="tbl_input" cellpadding="4"
							cellspacing="0">
							<tr>
								<td class="lbl_left"><font color="red">*</font> アカウント名:</td>
								<td align="left"><input class="txBox" type="text"
									name="login_name" value="" size="15"
									onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font> グループ:</td>
								<td align="left"><select name="group_id">
										<option value="0">選択してください</option>
										<c:set var="groupId" value="<%=groupId%>" scope="request"></c:set>
										<c:forEach items="${listAllGroup}" var="item">
											<c:if test="${item.group_id eq groupId}">
												<option value="${item.group_id}" selected><c:out
														value="${item.group_name}" /></option>
											</c:if>
											<c:if test="${item.group_id != groupId}">
												<option value="${item.group_id}"><c:out
														value="${item.group_name}" /></option>
											</c:if>
										</c:forEach>
								</select> <span>&nbsp;&nbsp;&nbsp;</span></td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font> 氏名:</td>
								<td align="left"><input class="txBox" type="text"
									name="fullName" value="" size="30"
									onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr>
								<td class="lbl_left">カタカナ氏名:</td>
								<td align="left"><input class="txBox" type="text"
									name="nameKata" value="" size="30"
									onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font> 生年月日:</td>
								<td align="left"><select name="yearBirth">
										<c:forEach var="i" begin="1900" end="${currentYear - 1}">
											<option value="${i}"><c:out value="${i}" /></option>
										</c:forEach>
										<option value="${currentYear}" selected>${currentYear}</option>
								</select>年 <select name="monthBirth">
										<c:forEach var="i" begin="1" end="12">
											<c:if test="${i == (currentMonth + 1)}">
												<option value="${i}" selected>${i}</option>
											</c:if>
											<c:if test="${i != (currentMonth + 1)}">
												<option value="${i}">${i}</option>
											</c:if>
										</c:forEach>
								</select>月 <select name="dateBirth">
										<c:forEach var="i" begin="1" end="31">
											<c:if test="${i == currentDate}">
												<option value="${i}" selected><c:out value="${i}" /></option>
											</c:if>
											<c:if test="${i != currentDate}">
												<option value="${i}"><c:out value="${i}" /></option>
											</c:if>
										</c:forEach>
								</select>日</td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font> メールアドレス:</td>
								<td align="left"><input class="txBox" type="text"
									name="email" value="" size="30"
									onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font>電話番号:</td>
								<td align="left"><input class="txBox" type="text"
									name="tel" value="" size="30"
									onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font> パスワード:</td>
								<td align="left"><input class="txBox" type="password"
									name="password" value="" size="30"
									onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr>
								<td class="lbl_left">パスワード（確認）:</td>
								<td align="left"><input class="txBox" type="password"
									name="reWritePass" value="" size="30"
									onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr>
								<th align="left" colspan="2"><a href="#">日本語能力</a></th>
							</tr>
							<tr>
								<td class="lbl_left">資格:</td>
								<td align="left"><select name="code_level">
										<option value="">選択してください</option>
										<c:set var="codeLevel" value="<%=codeLevel%>" scope="request"></c:set>
										<c:forEach items="${listAllJapanLevel}" var="item">
											<c:if test="${item.code_level eq codeLevel}">
												<option value="${item.code_level}" selected><c:out
														value="${item.name_level}" /></option>
											</c:if>
											<c:if test="${item.code_level != codeLevel}">
												<option value="${item.code_level}"><c:out
														value="${item.name_level}" /></option>
											</c:if>
										</c:forEach>
								</select></td>
							</tr>
							<tr>
								<td class="lbl_left">資格交付日:</td>
								<td align="left"><select name="startYear">
										<c:forEach var="i" begin="1900" end="${currentYear - 1}">
											<option value="${i}"><c:out value="${i}" /></option>
										</c:forEach>
										<option value="${currentYear}" selected>${currentYear}</option>
								</select>年 <select name="startMonth">
										<c:forEach var="i" begin="1" end="12">
											<c:if test="${i == (currentMonth + 1)}">
												<option value="${i}" selected>${i}</option>
											</c:if>
											<c:if test="${i != (currentMonth + 1)}">
												<option value="${i}">${i}</option>
											</c:if>
										</c:forEach>
								</select>月 <select name="startDate">
										<c:forEach var="i" begin="1" end="31">
											<c:if test="${i == currentDate}">
												<option value="${i}" selected><c:out value="${i}" /></option>
											</c:if>
											<c:if test="${i != currentDate}">
												<option value="${i}"><c:out value="${i}" /></option>
											</c:if>
										</c:forEach>
								</select>日</td>
							</tr>
							<tr>
								<td class="lbl_left">失効日:</td>
								<td align="left"><select name="endYear">
										<c:forEach var="i" begin="1900" end="${currentYear}">
											<option value="${i}"><c:out value="${i}" /></option>
										</c:forEach>
										<option value="${currentYear + 1}" selected>${currentYear + 1}</option>
								</select>年 <select name="endMonth">
										<c:forEach var="i" begin="1" end="12">
											<c:if test="${i == (currentMonth + 1)}">
												<option value="${i}" selected>${i}</option>
											</c:if>
											<c:if test="${i != (currentMonth + 1)}">
												<option value="${i}">${i}</option>
											</c:if>
										</c:forEach>
								</select>月 <select name="endDate">
										<c:forEach var="i" begin="1" end="31">
											<c:if test="${i == currentDate}">
												<option value="${i}" selected><c:out value="${i}" /></option>
											</c:if>
											<c:if test="${i != currentDate}">
												<option value="${i}"><c:out value="${i}" /></option>
											</c:if>
										</c:forEach>
								</select>日</td>
							</tr>
							<tr>
								<td class="lbl_left">点数:</td>
								<td align="left"><input class="txBox" type="text"
									name="total" value="" size="5"
									onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
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
					<td><input class="btn" type="submit" value="確認" /></td>
					<td><input class="btn" type="button" value="戻る" /></td>
				</tr>
			</table>
			<!-- End vung button -->
	</form>
	<!-- End vung input -->

	<!-- Begin vung footer -->
	<z:Footer></z:Footer>
	<!-- End vung footer -->
</body>

</html>
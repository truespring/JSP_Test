<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet">
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/res/css/common.css">
<c:forEach items="${css}" var="item">
	<link rel="stylesheet" type="text/css" href="/res/css/${item }.css">
</c:forEach>
<title>${title }</title>
</head>
<body>
	<div id="container">
		<header>
			<div id="headerLeft">
				<div class="containerPImg">
					<c:choose>
						<c:when test="${loginUser.profile_img != null }">
							<img class="pImg" src="/res/img/user/${loginUser.i_user}/${loginUser.profile_img}">
						</c:when>
						<c:otherwise>
							<img class="pImg" src="/res/img/default_profile.jpg">
						</c:otherwise>
					</c:choose>
				</div>
				<div class="ml5">${loginUser.nm}님 환영합니다.</div>
				<div class="ml15" id="headerLogout"><a href="/user/logout">로그아웃</a></div>
			</div>
			<div id="headerRight">
				<a href="/restaurant/restMap"><span class="material-icons" id="icon">map</span></a>
			 	<a class="ml15" href="/restaurant/restReg"><span class="material-icons" id="icon">add_circle_outline</span></a>
			 	<a class="ml15 mr15" href="/user/restFavorite"><span class="material-icons" id="icon">favorite</span></a>
			</div>
		</header>
		<section>
			<jsp:include page="/WEB-INF/view/${view}.jsp"></jsp:include>
		</section>
		<footer>
			회사정보
		</footer>
	</div>
</body>
</html>
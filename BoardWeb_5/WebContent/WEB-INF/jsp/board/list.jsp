<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글목록</title>
<style>
	.container {
		width: 1200px; margin: 30px auto; text-align: center; padding: 5px;
	}
	table {
		width: 1000px; border: 1px solid black;
		border-collapse: collapse;
	}
	th {
		border-bottom:1px solid black; padding: 10px;
		background-color: #03C75A;
	}
	td {
		text-align: right;
		padding: 10px;
	}
 	table .itemRow:hover {
 		background-color: #ecf0f1; cursor: pointer;
 	}
 	.nm {
 		font-weight: bold; font-size: 1.2em;
 	}
 	#now_page {
 		color: blue; font-size: 2em;
 	}
 	.pagingFont {
		font-size: 2em; color: red;
	}
	.pagingFont:not(:first-child) {
		margin-left: 13px;
	}
</style>
</head>
<body>
	<div class="container">
		<h1>글목록</h1>
		<div><span class="nm">${loginUser.nm }</span>님 환영합니다.</div> <!-- 세션을 활용하는 법 -->
		<div>
			<form action="/board/list" method="get" id="selFrm">
				<input type="hidden" name="page" value="${page }">
				<input type="hidden" name="searchText" value="${param.searchText}">
				레코드 수 :
				<select id="record_cnt" name="record_cnt" onchange="changeRecordCnt()">
					<c:forEach begin="10" end="30" step="10" var="item"> 
						<c:choose>
							<c:when test="${param.record_cnt == item}" >
								<option value="${item}" selected>${item}개</option>
							</c:when>
							<c:otherwise>
								<option value="${item}">${item}개</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</form>
		</div>
		<div>
			<a href="regmod">글쓰기</a>
			<a href="/logout">로그아웃</a>
			<a href="/profile">프로필</a>
		</div>
		<table>
			<tr>
				<th>글번호</th>
				<th>글제목</th>
				<th>작성자</th>
				<th>조회수</th>
				<th>작성 시간</th>
			</tr>
			<c:forEach items="${list}" var="item">
				<tr class="itemRow" onclick="moveToDetail(${item.i_board})">
					<td>${item.i_board }</td>
					<td>${item.title }[${item.cmt_cnt }]</td>
					<td>${item.nm }</td>
					<td>${item.hits }</td>
					<td>${item.r_dt }</td>
				</tr>
			</c:forEach>
		</table>
		<div>
			<form action="/board/list" id="serFrm">
				<input type="text" name="searchText" value="${param.searchText }">
				<input type="submit" value="검색">
			</form>
		</div>
		<c:forEach begin='1' end='${pagingCnt }' var="item">
			<c:choose>
				<c:when test="${page == item}">
					<span class="pagingFont pageSelected">${item}</span>
				</c:when>
				<c:otherwise>
					<span class="pagingFont">
						<a href="/board/list?page=${item}&record_cnt=${param.record_cnt}&searchText=${param.searchText}">${item}</a>
					</span>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</div>
	<script>
		function changeRecordCnt() {
			selFrm.submit() 
		}
	
		function moveToDetail(i_board) {
			location.href = '/board/detail?page=${page}&record_cnt=${param.record_cnt}&i_board=' + i_board + '&searchText=${param.searchText}'
		}
	</script>
</body>
</html>
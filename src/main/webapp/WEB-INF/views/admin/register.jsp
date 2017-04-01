<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ch">
<head>
<%@ include file="common/meta.jsp"%>
<link href="${pageContext.request.contextPath}/admin/css/fullcalendar.css" rel= "stylesheet">
<script src="${pageContext.request.contextPath}/admin/js/falgun/fullcalendar.min.js"></script>
<style type="text/css">
body{
background-color: white;;
}
</style>
<link>
</head>
<body>
	<div class="layout">
		<!-- Navbar
    ================================================== -->
		<!-- top -->
		
<div class="navbar navbar-inverse top-nav">
		<div class="navbar-inner">
			<div class="container">
				<span class="home-link"></span>
				<div class="btn-toolbar pull-right notification-nav">
				</div>
			</div>
		</div>
	</div>

		<!-- 导航 -->
		
		
					
				<div class="container" style="padding: 48px">
					<h2 style="text-align: center;">欢迎注册商家</h2>
					
					<form class="form-horizontal" action="${pageContext.request.contextPath}/admin/doregister" enctype="multipart/form-data" method="post">
							
							<div class="control-group">
								<label for="title" class="control-label">用户名：</label>
								<div class="controls">
									<input type="text" name='username' id="username" placeholder="">
								</div>
							</div>
							<div class="control-group">
								<label for="title" class="control-label">密码：</label>
								<div class="controls">
									<input type="text"  name='password' id="password" placeholder="">
								</div>
							</div>
							<div class="control-group">
								<label for="title" class="control-label">商家全称：</label>
								<div class="controls">
									<input type="text" name='name' id="name" placeholder="">
								</div>
							</div>
							
							<div class="control-group">
								<label for="title" class="control-label">商家地址：</label>
								<div class="controls">
									<input type="text" name='address' id=address placeholder="">
								</div>
							</div>
							
							<div class="control-group">
								<label for="title" class="control-label">商家电话：</label>
								<div class="controls">
									<input type="text" name='tel' id=tel placeholder="">
								</div>
							</div>
							<div class="control-group">
								<label for="title" class="control-label">营业执照：</label>
								<div class="controls">
									<input type="text" name='grade' id=grade placeholder="">
								</div>
							</div>
							
							<div class="control-group">
								<label for="title" class="control-label">平均等待时间：</label>
								<div class="controls">
									<input type="text" name='sendtime' id=sendtime placeholder="">
								</div>
							</div>
							
							<div class="control-group">
								<label for="title" class="control-label">最低消费：</label>
								<div class="controls">
									<input type="text" name='lowprice' id=lowprice placeholder="">
								</div>
							</div>
							
							
							
							<div class="control-group">
								<label for="title" class="control-label">分类：</label>
								<div class="controls">
									<select name='category.id'>
										<c:forEach var="bean" items="${categorys}">
											<option value="${bean.id }">${bean.name }</option>
										</c:forEach>
									</select>
								</div>
							</div>
							
							
							
							
							
								<div class="modal-footer center" id="div_footer">
									<button type="submit" class='btn btn-primary'>提交</button>
								</div>
						</form>
				</div>
					
		<!-- foot -->
		
	</div>

</body>
</html>
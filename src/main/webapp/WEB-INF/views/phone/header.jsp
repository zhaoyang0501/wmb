<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<<style>
<!--
* {
font-family: "Microsoft YaHei" ! important;
}
-->
</style>
       <header class="ui-header ui-header-positive ui-border-b">
            <i class="ui-icon-return" onclick="history.back()"></i><h1>快来点餐吧！ </h1><button class="ui-btn">回首页</button>
        </header>
        <footer class="ui-footer ui-footer-btn">
            <ul class="ui-tiled ui-border-t">
                <li data-href="${pageContext.request.contextPath}/phone/index" class="ui-border-r"><div>首页</div></li>
                <li data-href="${pageContext.request.contextPath}/phone/myorder" class="ui-border-r"><div>订单</div></li>
                <li data-href="${pageContext.request.contextPath}/phone/center"><div>我的</div></li>
                <li data-href="${pageContext.request.contextPath}/phone/about"><div>更多</div></li>
            </ul>
        </footer>
        
        
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
  <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
        <meta name="format-detection" content="telephone=no">
        <title>FrozenUI Demo</title>
        <script type="text/javascript" src="http://tajs.qq.com/stats?sId=37342703" charset="UTF-8"></script>
        <link rel="stylesheet" href="../css/frozen.css">
        <link rel="stylesheet" href="demo.css">
        <script src="../lib/zepto.min.js"></script>
        <script src="../js/frozen.js"></script>
    </head>
    
    <body ontouchstart>
  	<%@include file="./header.jsp" %>
    <section class="ui-container">
			<section id="grid"  class='"ui-panel' style="margin-top: 5px">
			 <div class="demo-item">
			        <div class="demo-block">
			            <ul class="ui-list ui-border-tb">
			            	 <li class="ui-border-t"  data-href="${pageContext.request.contextPath}/phone/seller?id=${bean.id}">
			                    <div class="ui-list-info">
			                        <h4 class="ui-nowrap">订单详细</h4>
			                    </div>
			                </li>
			            </ul>
			        </div>
			    </div>
			</section>
			<section   style="background: #FFFFFF;" >
			        <div class="demo-block">
			        <c:forEach items="${order.items }" var="bean">
			        	<div class="ui-row-flex ui-whitespace">
			                <div class="ui-col ui-col-3">${bean.project.name }(${bean.project.seller.name })</div>
			                <div class="ui-col ui-col-3">${bean.num }*${bean.project.price }</div>
			                <div class="ui-col ui-col">￥${bean.num*bean.project.price }</div>
		            	</div>
			        </c:forEach>
		             <hr/>
		             
		             <div class="ui-row-flex ui-whitespace">
		               <div class="ui-col ui-col-3">总价</div>
		                <div class="ui-col ui-col-3"></div>
		                <div class="ui-col ui-col" style="color: red">￥${order.toalprice }</div>
		            </div>
		            </div>
	</section>
	
	<section style="margin-top: 10px">
		 <div class="demo-item">
        <div class="demo-block">
            <div class="ui-form ui-border-t">
                <form action="${pageContext.request.contextPath}/phone/sumbitcart">
                   
                   <ul class="ui-list ui-list-text ui-list-radio ui-border-tb">
				    <li class="ui-border-t">
				        <label class="ui-radio" for="radio">
				            <input type="radio" name="radio">
				        </label>
				        <p>即使订餐</p>
				    </li>
				    <li class="ui-border-t">
				        <label class="ui-radio" for="radio">
				            <input type="radio" checked name="radio">
				        </label>
				        <p>预约</p>
				    </li>
				</ul>
        
                    <div class="ui-form-item ui-border-b">
                        <label>
                        		  备注
                        </label>
                        <input type="text" placeholder="备注"  name='remark' />
                    </div>
                    
                     <div class="ui-btn-wrap">
                <button class="ui-btn-lg ui-btn-primary" type="submit">
                  		提交订单
                </button>
            </div>
                </form>
            </div>
        </div>
    </div>
	</section>
        </section><!-- /.ui-container-->
        <script>
        $('.ui-list li,.ui-tiled li').click(function(){
            if($(this).data('href')){
                location.href= $(this).data('href');
            }
        });
        $('.ui-header .ui-btn').click(function(){
            location.href= 'index.html';
        });
        </script>
    </body>
</html>
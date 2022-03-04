<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>

<head>
    <title>商品管理</title>
    <meta charset="utf-8"/>
    <link href="${ pageContext.request.contextPath }/css/managestyle.css" rel="stylesheet" type="text/css">
    <script src="${ pageContext.request.contextPath }/js/jquery-1.4.2.js"></script>
</head>
<body>
<c:set var="flag" value="0"/>
<div class="top">
    <h1>商品管理</h1>
</div>
<div class="content">
    <div class="left">
        <%@ include file = "_left.jsp" %>
    </div>
    <div class="right">
        <table border="1" style="margin-left: 50px;margin-top:50px" id="mytable">
            <tr>
                <th>名称</th>
                <th>描述</th>
            </tr>
            <tr>
                <td>${category.name}</td>
                <td><input type="text" name="description" value="${category.description}" id="description" /></td>
            </tr>
            <tr>
                <td><a href="#" onclick="save()">保存</a></td>
            </tr>
        </table>
    </div>
</div>
    <script>
        function save(){
            $.ajax({
                url:"${ pageContext.request.contextPath }/admin/updateCategory",
                type:"post",
                data:JSON.stringify({
                    "name":"${category.name}",
                    "description":$("#description").val()
                }),
                contentType : "application/json;charset=utf-8",
                //定义回调响应的数据格式为JSON字符串，该属性可以省略
                dataType : "json",
                //成功响应的结果
                success : function(data) {
                    window.open("${ pageContext.request.contextPath }/admin/getCategory");
                }
            })
        }
    </script>
</body>

</html>
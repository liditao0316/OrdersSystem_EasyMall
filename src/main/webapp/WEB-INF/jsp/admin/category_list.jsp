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
            <table border="1" style="margin-left: 20px;margin-top:20px" id="mytable">
                <tr>
                    <th>名称</th>
                    <th>描述</th>
                </tr>

                <c:forEach items="${categories}" var="catagory">
                    <tr>
                        <td>${catagory.name}</td>
                        <td>${catagory.description}</td>
                        <td>
                            <a id="modify" href="${ pageContext.request.contextPath }/admin/oneCategory?name=${catagory.name}">修改</a>
                        </td>
                        <td><a href="${ pageContext.request.contextPath }/admin/deleteCategory?name=${catagory.name}">删除</a></td>
                    </tr>
                </c:forEach>
            </table>
            <div style="margin-left: 20px"><a href="#" onclick="add()">添加</a></div>

    </div>
</div>
<script>
    function add(){
        $("#mytable").append("<tr><td><input type='text' id='newName'/></td>" +
            "<td><input type='text' id='newDescription'/></td>" +
            "<td colspan='2'><a href='#' onclick='insert()'>插入</a></td></tr>")
    }
    function insert(){
        var name = $("#newName").val();
        var description  = $("#newDescription").val();
        console.log(name)
        console.log(description)
        $.ajax({
            url:"${ pageContext.request.contextPath }/admin/insertCategory",
            type:"post",
            data:JSON.stringify({
                "name":name,
                "description":description
            }),
            contentType : "application/json;charset=utf-8",
            //定义回调响应的数据格式为JSON字符串，该属性可以省略
            dataType : "json",
            //成功响应的结果
            success : function(data) {
                window.location.reload();
            }
        })
    }
</script>
</body>

</html>
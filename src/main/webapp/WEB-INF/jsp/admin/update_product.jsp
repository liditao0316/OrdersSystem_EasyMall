<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>添加商品</title>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
    <link href="${ pageContext.request.contextPath }/css/managestyle.css" rel="stylesheet" type="text/css">
    <script  type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery-1.4.2.js"></script>

</head>
<body>
<div class="top">
    <h1>&nbsp;&nbsp;EasyMall商城管理后台</h1>
</div>
<div class="content">
    <div class="left">
        <%@ include file = "_left.jsp" %>
    </div>
    <div class="right">
        <div class="addprod">
            <jsp:useBean id="UpdateProduct"  class="easymall.pojo.UpdateProduct" scope="request" ></jsp:useBean>
            <%--@elvariable id="upproducts" type="easymall.pojo.UpdateProduct"--%>
            <form:form modelAttribute="updateProduct" onsubmit="return formobj.checkForm();" method="POST"
                       enctype="multipart/form-data" action="${ pageContext.request.contextPath }/admin/update">
                <fieldset>
                    <form:input path="id" disabled="false"/>
                    <legend>添加一件商品</legend>
                    <p>
                        <label>商品名:</label>
                        <form:input path="name"/>
                    </p><p>
                    <label>商品价格:</label>
                    <form:input path="price"/>
                </p><p>
                    <label>商品类别:</label>
                    <form:select path="category">
                        <!-- 通过循环语句将所有商品类别显示在下拉列表中 -->
                        <c:forEach items="${categories}" var="c">
                            <option value="${c}">${c}</option>
                        </c:forEach>
                    </form:select>
                </p><p>
                    <label>库存:</label>
                    <form:input path="pnum"/>
                </p><p>
                    <label>图片:</label>
                    <input type="file" name="imgurl"/>
                </p><p>
                    <label>商品描述:</label>
                    <form:input path="description"/>
                </p><p id="buttons">
                    <!-- <input id="submit" type="submit" value="重置"> -->
                    <input id="submit" type="submit" value="修改">
                </p>
                </fieldset>
                <!-- 取出所有验证错误 -->
                <form:errors path="*"/>
            </form:form>
        </div>
    </div><!-- right结束 -->
</div><!-- content结束 -->
<script>
    $(function (){
        console.log("${updateProduct.name}");
        $("#name").val("${updateProduct.name}");
        $("#price").val("${updateProduct.price}");
        $("#pnum").val("${updateProduct.pnum}");
        $("#description").val("${updateProduct.description}");
    })
</script>

</body>
</html>
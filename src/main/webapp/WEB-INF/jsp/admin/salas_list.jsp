<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
    <title>商品管理</title>
    <meta charset="utf-8"/>
    <link href="${ pageContext.request.contextPath }/css/managestyle.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${ pageContext.request.contextPath }/js/echarts.min.js"></script>
    <script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery-1.4.2.js"></script>
</head>
<body>
<div class="top">
    <h1>商品管理</h1>
</div>
<div class="content">
    <div class="left">
        <%@ include file = "_left.jsp" %>
    </div>
    <div style="float: left;width:80%">
        <div id="main" style="width: 600px;height:400px;margin: 0 auto"></div>
        <div id="app" style="width: 600px;height:400px;margin: 0 auto"></div>
        <div style="width: 800px;margin:0 auto">
            <button onclick="Output()">导出Excel</button>
            <table width="799" height="80" border="1" cellpadding="0" cellspacing="0" bordercolor="#d8d8d8">
                <tr>
                    <th width="276">商品图片</th>
                    <th width="247">商品名称</th>
                    <th width="231">商品单价</th>
                    <th width="214">销售数量</th>
                    <th width="232">库存数量</th>

                </tr>
                <c:forEach items="${products}" var="prod">
                    <tr>
                        <td><img src="${ pageContext.request.contextPath }${prod.imgurl}"
                                 width="90" height="90" class="prodimg" /></td>
                        <td>${prod.name }</td>
                        <td>${prod.price }元</td>
                        <td>${prod.soldnum }件</td>
                        <td>${prod.pnum }件</td>
                    </tr>
                </c:forEach>
            </table>
        </div>

    </div>


</div>
<script type="text/javascript">
    function Output(){
        console.log(111)
        window.open("${ pageContext.request.contextPath }/admin/salas_poi")
    }
    $.ajax({
        url:"${ pageContext.request.contextPath }/admin/salas_echarts",
        type:"get",
        contentType : "application/json;charset=utf-8",
        //定义回调响应的数据格式为JSON字符串，该属性可以省略
        dataType : "json",
        //成功响应的结果
        success : function(result) {
            if (result != null) {
                var list_name =[];
                var list_salas = [];
                var list_pnum = [];
                for(var i=0;i<result.length;i++){
                    list_name.push(result[i].name);
                    list_salas.push(result[i].soldnum);
                    list_pnum.push(result[i].pnum);
                }
                console.log(list_salas);
                console.log(list_name);
                // 基于准备好的dom，初始化echarts实例
                var myChart1 = echarts.init(document.getElementById('main'));

                // 指定图表的配置项和数据
                var option = {
                    title: {
                        text: '销售榜单前四名'
                    },
                    tooltip: {},
                    legend: {
                        data:['销量']
                    },
                    xAxis: {
                        data: list_name
                    },
                    yAxis: {},
                    series: [{
                        name: '销量',
                        type: 'bar',
                        data: list_salas
                    }]
                };

                // 使用刚指定的配置项和数据显示图表。
                myChart1.setOption(option);

                var myChart2 = echarts.init(document.getElementById('app'));

                // 指定图表的配置项和数据
                let option1 = {
                    title: {
                        text: '热售物品的库存数量'
                    },
                    xAxis:{
                        type:"category",
                        data:list_name
                    },
                    yAxis:{
                        type:"value"
                    },
                    series:[
                        {
                            name:"数学",
                            type:"line",
                            data:list_pnum,
                            label:{show:true},
                        }
                    ]
                };

                // 使用刚指定的配置项和数据显示图表。
                myChart2.setOption(option1);
            }
        }
    })

</script>
</body>
</html>
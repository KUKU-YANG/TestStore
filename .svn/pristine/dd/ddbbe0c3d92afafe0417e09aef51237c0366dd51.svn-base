<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>菜单</title>
<link href="${pageContext.request.contextPath}/css/left.css" rel="stylesheet" type="text/css"/>
<link rel="StyleSheet" href="${pageContext.request.contextPath}/css/dtree.css" type="text/css" />
</head>
<body>
<table width="100" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="12"></td>
  </tr>
</table>
<table width="100%" border="0">
  <tr>
    <td>
<div class="dtree">

	<a href="javascript: d.openAll();">展开所有</a> | <a href="javascript: d.closeAll();">关闭所有</a>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/dtree.js"></script>
	<script type="text/javascript">
	//在js中写html注释的作用：那些不支持 JavaScript 的浏览器会把脚本作为页面的内容来显示，加上html注释即便无法识别也不会泄露代码
	<!--
		d = new dTree('d');
		d.add('01',-1,'系统菜单树');
		//参数：当前节点id，父节点id，节点上的文字描述，跳转路径，提示信息，发生变化的frame区域（name属性值）
		d.add('0102','01','分类管理','','','mainFrame');
		d.add('010201','0102','分类管理','${pageContext.request.contextPath}/AdminCategoryServlet?method=getAllCats','','mainFrame');
		d.add('0104','01','商品管理');
		d.add('010401','0104','商品管理','${pageContext.request.contextPath}/AdminProductServlet?method=findAllProductsWithPage&num=1','','mainFrame');
		d.add('010402','0104','已下架商品管理','${pageContext.request.contextPath}/AdminProductServlet?method=pushDownUI','','mainFrame');
		d.add('0105','01','订单管理');
		d.add('010501','0105','订单管理','${pageContext.request.contextPath}/AdminOrderServlet?method=findAllOrdersWithPage&num=1','','mainFrame');
		d.add('010502','0105','未付款的订单','${pageContext.request.contextPath}/AdminOrderServlet?method=findAllOrdersWithPage&state=1&num=1','','mainFrame');
		d.add('010503','0105','已付款订单','${pageContext.request.contextPath}/AdminOrderServlet?method=findAllOrdersWithPage&state=2&num=1','','mainFrame');
		d.add('010504','0105','已发货的订单','${pageContext.request.contextPath}/AdminOrderServlet?method=findAllOrdersWithPage&state=3&num=1','','mainFrame');
		d.add('010505','0105','已完成的订单','${pageContext.request.contextPath}/AdminOrderServlet?method=findAllOrdersWithPage&state=4&num=1','','mainFrame');
		d.add('0106','01','用户管理','','','mainFrame');
		d.add('010601','0106','普通用户','${pageContext.request.contextPath}/admin/user/list.jsp','','mainFrame');
		d.add('010602','0106','白银用户','${pageContext.request.contextPath}/admin/user/list.jsp','','mainFrame');
		d.add('010603','0106','黄金用户','${pageContext.request.contextPath}/admin/user/list.jsp','','mainFrame');
		document.write(d);
	-->
	</script>
</div>	</td>
  </tr>
</table>
</body>
</html>

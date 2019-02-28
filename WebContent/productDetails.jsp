<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/productDetails.css">
<link rel="stylesheet" href="./css/cyan.css">
<title>商品詳細</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div id="contents">
<h1>商品詳細画面</h1>
<s:if test="#session.productInfoDTOList.isEmpty()">
<div class="info">
	商品の詳細情報がありません。
</div>
</s:if>
<s:elseif test="!#session.productInfoDTOList.isEmpty()">
	<s:form action="AddCartAction">
	<div class="box">
	<div class="two-column-container">
	<div class="right">
		<div class="item-image-box-320"><img src='<s:property value="%{#session.imageFilePath}"/>/<s:property value="%{#session.imageFileName}"/>' height="320px" width="320px"/></div><br>
	</div>
	<div class="left">
	<table class="product-vertical-list-table">
		<tr>
		<th scope="row"><s:label value="商品名"/></th>
		<td><s:property value="%{#session.productName}"/></td>
		</tr>
		<tr>
		<th scope="row"><s:label value="商品名ふりがな"/></th>
		<td><s:property value="%{#session.productNameKana}"/></td>
		</tr>
		<tr>
		<th scope="row"><s:label value="値段"/></th>
		<td><s:property value="%{#session.price}"/>円</td>
		</tr>
		<tr>
		<th scope="row"><s:label value="購入個数"/></th>
		<td><s:select name="productCount" list="%{#session.productCountList}"/>個</td>
		</tr>
		<tr>
		<th scope="row"><s:label value="発売会社名"/></th>
		<td><s:property value="%{#session.releaseCompany}"/></td>
		</tr>
		<tr>
		<th scope="row"><s:label value="発売年月日"/></th>
		<td><s:property value="%{#session.releaseDate}"/></td>
		</tr>
		<tr>
		<th scope="row"><s:label value="商品詳細情報"/></th>
		<td><s:property value="%{#session.productDescription}"/></td>
		</tr>
	</table>
	</div>
	<div class="submit_btn_box">
	<s:submit value="カートに追加" class="submit_btn"/>
	</div>
	</div>
	</div>
	</s:form>

	<div class="box">
	<div class="product-details-recommend-box">
	<s:iterator value="#session.productInfoDTOList">
		<div class="recommend-box">
		<div class="item-image-box-170">
		<a href='<s:url action="ProductDetailsAction">
		<s:param name="productId" value="%{productId}"/>
		</s:url>'><img src='<s:property value="imageFilePath"/>/<s:property value="imageFileName"/>' height="170px" width="170px"/></a></div>
		<div class="product-title"><s:property value="productName"/></div><br>
		</div>
	</s:iterator>
	</div>
	</div>
</s:elseif>
</div>

</body>
</html>
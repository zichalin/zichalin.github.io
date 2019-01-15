<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<div style='text-align:center;'>
<a class='btn btn-info' <c:if test="${page.pageNumber == 1 }">disabled</c:if> <c:if test="${page.pageNumber != 1 }">href="${pageContext.request.contextPath }${param.url }?pageNumber=1${param.param }"</c:if> >首页</a> 
<a class='btn btn-info' <c:if test="${page.pageNumber == 1 }">disabled</c:if> <c:if test="${page.pageNumber != 1 }">href="${pageContext.request.contextPath }${param.url }?pageNumber=${page.pageNumber-1 }${param.param }"</c:if> >上一页</a>
<h3 style='display:inline;'>[${page.pageNumber }/${page.totalPage }]</h3>
<h3 style='display:inline;'>[${page.totalCount }]</h3>                   
<a class='btn btn-info' <c:if test="${page.totalPage == 0 || page.pageNumber == page.totalPage }">disabled</c:if> <c:if test="${page.pageNumber != page.totalPage }">href="${pageContext.request.contextPath }${param.url }?pageNumber=${page.pageNumber+1 }${param.param }"</c:if> >下一页</a> 
<a class='btn btn-info' <c:if test="${page.totalPage == 0 || page.pageNumber == page.totalPage }">disabled</c:if> <c:if test="${page.pageNumber != page.totalPage }">href="${pageContext.request.contextPath }${param.url }?pageNumber=${page.totalPage }${param.param }"</c:if> >尾页</a>
<input type='text' class='form-control' style='display:inline;width:60px;' value=''/><a class='btn btn-info' href='javascript:void(0);' onclick='location.href="${pageContext.request.contextPath }${param.url }?pageNumber="+(this.previousSibling.value)+"${param.param }"'>GO</a>
</div>
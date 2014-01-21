<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>应用管理中心</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Robert">

    <!-- Le styles -->
    <link href="../static/css/bootstrap.css" rel="stylesheet">
    <style>
      body {
        padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
      }
    </style>
    <link href="../static/css/bootstrap-responsive.css" rel="stylesheet">

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="../static/js/html5shiv.js"></script>
    <![endif]-->

    <!-- Fav and touch icons -->
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../static/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../static/ico/apple-touch-icon-114-precomposed.png">
      <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../static/ico/apple-touch-icon-72-precomposed.png">
                    <link rel="apple-touch-icon-precomposed" href="../static/ico/apple-touch-icon-57-precomposed.png">
                                   <link rel="shortcut icon" href="../static/ico/favicon.png">
  </head>

  <body>

    <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="brand" href="<%=request.getContextPath() %>/">应用管理</a>
          <div class="nav-collapse collapse">
            <ul class="nav">
              <li><a href="<%=request.getContextPath() %>/app/list.do">应用类表</a></li>
              <li><a href="<%=request.getContextPath() %>/app/get.do">添加应用</a></li>
            </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>

    <div class="container">

      <h1><a href="<%=request.getContextPath() %>/app/show.do?id=${app.id}">${app.appname}</a>/应用安装包列表</h1>
      <table class="table table-hover">
              <thead>
                <tr>
                  <th>#</th>
                  <th>应用类型</th>
                  <th>版本</th>
                  <th>更新时间</th>
                  <th>操作</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach items="${packages}" var="item" varStatus="stat">
                    <tr>
                        <td>${stat.index + 1}</td>
                        <td>${types[item.type]}</td>
                        <td>${item.version}</td>
                        <td>${item.updateTime}</td>
                        <td>
                        <a href="<%=request.getContextPath() %>/package/show.do?id=${item.id}"><button class="btn btn-small btn-primary" type="button">查看</button></a>
                        <a href="<%=request.getContextPath() %>/package/destroy.do?id=${item.id}&appid=${app.id}"><button class="btn btn-small btn-danger" type="button">删除</button></a>
                        </td>
                    </tr>
                </c:forEach>
              </tbody>
              <thead>
                <tr>
                  <th>#</th>
                  <th>应用类型</th>
                  <th>版本</th>
                  <th>更新时间</th>
                  <th>操作</th>
                </tr>
              </thead>
            </table>
            <a href="<%=request.getContextPath() %>/package/get.do?appid=${app.id}"><button class="btn btn-large btn-primary" type="button">添加安装包</button></a>
    </div> <!-- /container -->

    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="../static/js/jquery-1.10.2.min.js"></script>
    <script src="../static/js/bootstrap-transition.js"></script>
    <script src="../static/js/bootstrap-alert.js"></script>
    <script src="../static/js/bootstrap-modal.js"></script>
    <script src="../static/js/bootstrap-dropdown.js"></script>
    <script src="../static/js/bootstrap-scrollspy.js"></script>
    <script src="../static/js/bootstrap-tab.js"></script>
    <script src="../static/js/bootstrap-tooltip.js"></script>
    <script src="../static/js/bootstrap-popover.js"></script>
    <script src="../static/js/bootstrap-button.js"></script>
    <script src="../static/js/bootstrap-collapse.js"></script>
    <script src="../static/js/bootstrap-carousel.js"></script>
    <script src="../static/js/bootstrap-typeahead.js"></script>

  </body>
</html>

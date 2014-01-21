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

      <h1>应用安装包</h1>
      <form class="form-horizontal" method="post" action="<%=request.getContextPath() %>/package/update.do" enctype="multipart/form-data">
  <div class="control-group">
    <label class="control-label" for="inputDescription">版本</label>
    <div class="controls">
        <input type="text" id="inputVersion" placeholder="Version" name="version" value="${package.version}">
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="inputDescription">应用类型</label>
    <div class="controls">
    <select name="type">
        <c:forEach  items="${appTypes}" var="item" varStatus="stat">
            <c:choose>
                <c:when test="${item.value} == package.version">
                    <option value="${item.value}" selected="selected">${item.key}</option>
                </c:when>
                <c:otherwise>
                    <option value="${item.value}">${item.key}</option>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </select>
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="inputPackage">修改安装包</label>
    <div class="controls">
        <input type="file" id="inputPFile" placeholder="Package" name="pfile">
    </div>
  </div>
  
  <div class="control-group">
    <label class="control-label" for="inputOldPackage">应用安装包</label>
    <div class="controls">
        <a href="<%=request.getContextPath() %>/download/get.do?id=${package.id}">点此下载</a>
        <img src="<%=request.getContextPath() %>/mark/get.do?id=${package.id}" class="img-rounded">
    </div>
  </div>
  <input type="hidden" name="appid" value="${package.app_id}"/>
  <input type="hidden" name="id" value="${package.id}"/>
  <div class="control-group">
    <div class="controls">
      <button type="submit" class="btn btn-primary">更新</button>
    </div>
  </div>
</form>
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

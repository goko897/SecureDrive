<%
if(session.getAttribute("name")==null){
	response.sendRedirect("login.jsp");
}
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import= "java.util.ArrayList" %>

<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title>mylocalfile</title>
<link rel="stylesheet" href="css/homepage.css">
<link href="css/ffolders.min.css" rel="stylesheet">
<link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">
<link href="https://cdn.jsdelivr.net/gh/eliyantosarage/font-awesome-pro@main/fontawesome-pro-6.5.1-web/css/all.min.css" rel="stylesheet">

</head>
<body>
   	<header class="header">
		<h1 class="logo"><a href="#">secure-drive</a></h1>
      <ul class="main-nav">
          <li><a href="<%= request.getContextPath() %>/FileController"><i class="zmdi zmdi-home zmdi-hc-fw"></i></a></li>
          <li><a href="login.jsp">Login</a></li>
          <li><a href="#">Registration</a></li>
      </ul>
	</header> 
   
   <div class="row">
  <div class="column1">
  

   <%
     String path = (String) request.getAttribute("path");
     ArrayList <String> folders = (ArrayList<String>) request.getAttribute("folders"); 
     ArrayList <String> files = (ArrayList<String>) request.getAttribute("files");
   %>
  <h2> Current folder - <%= path %></h2>
 

<ul class="folder-list">
  <c:forEach items="${folders }" var="folder">
    <li class="folder-button">
      <form id="folder-form-${folder}" action="<%= request.getContextPath() %>/FileController" method="get">
        <button type="submit">
          <i class="fa fa-folder" style="font-size:48px;"></i>
          <span class="folder-name">${folder}</span>
          <input type="hidden" name="path" value="${path}/${folder }"/>
        </button>
      </form>
      <c:url value="/FolderController" var="deleteurl">
        <c:param name="path" value="${path}"></c:param>
        <c:param name="action" value="delete"></c:param>
        <c:param name="folder" value="${folder}"></c:param>
      </c:url>
      <div class="delete-link">
        <a href="${deleteurl}"><i class="fa-solid fa-trash"></i></a>
      </div>
    </li>
  </c:forEach>
</ul>
  <br/>
  
  <c:forEach items="${files }" var="file">
  <c:url value="/DownloadController" var="downloadurl">
      <c:param name="path" value="${path }"></c:param>
      <c:param name="file" value="${file }"></c:param>
  </c:url>
  <div class="file-item">
    <c:choose>
      <c:when test="${fn:contains(file, '.pdf')}">
        <i class="fa-regular fa-file-pdf" style="font-size:48px;"></i>
        <c:set var="filetype" value="pdf" />
      </c:when>
      <c:when test="${fn:contains(file, '.docx') || fn:contains(file, '.doc')}">
        <i class="fa-brands fa-dochub" style="font-size:48px;"></i>
        <c:set var="filetype" value="doc" />
      </c:when>
      <c:when test="${fn:contains(file, '.xlsx') || fn:contains(file, '.xls')}">
        <i class="fa-regular fa-file-xls" style="font-size:48px;"></i>
        <c:set var="filetype" value="xls" />
      </c:when>
      <c:when test="${fn:contains(file, '.pptx') || fn:contains(file, '.ppt')}">
        <i class="fa-duotone fa-file-ppt" style="font-size:48px;"></i>
        <c:set var="filetype" value="ppt" />
      </c:when>
      <c:when test="${fn:contains(file, '.zip') || fn:contains(file, '.rar')}">
        <i class="fa-duotone fa-file-zip" style="font-size:48px;"></i>
        <c:set var="filetype" value="rar" />
      </c:when>
      <c:otherwise>
        <i class="fa-solid fa-file" style="font-size:48px;"></i>
        <c:set var="filetype" value="file" />
      </c:otherwise>
    </c:choose>
    <a href="${downloadurl }" class="file-link"><span class="file-name">${file }</span></a>
  </div>
</c:forEach>
  <br/>
  </div>
  <div class="column2">
  <div class="container">
        <h1>Upload File</h1>
        <c:url value="/UploadController" var="uploadurl">
      <c:param name="path" value="${path }"></c:param>
  </c:url>
  <form action="${uploadurl }" method="post" enctype="multipart/form-data">
      <div class="">Select files :</div> <input type="file" name="files" class="custom-file-input" multiple />
      
      <input type="submit" value="Upload Files" class="upload-button"/>
      <input type="reset" class="upload-button"/>
  </form>
        
    </div>
  <div class="container">
        <h1>Create Folder</h1>
        <form action="<%= request.getContextPath() %>/FolderController" method="get">
        <input type="hidden" name="path" value="${path}" />
        <input type="hidden" name="action" value="create"/>
        <input type="text" name="folder" class="effect-24" placeholder="Enter your folder name"/>
        <input type="submit" value="Create Folder" class="upload-button"/>
  </form>
        
        
    </div>
  </div>
  </div>
</body>
</html>
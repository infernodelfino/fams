<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
</head>
<body>
<!-- Header -->
    <div class="container-fluid">
        <div class="row" style="background-color: #205081; vertical-align: baseline;">
            <div class="col-lg-2 pl-3 my-auto">
                <a href="landing-page.html"><img src='<c:url value="/resources/img/fpt-logo 70x53.png"></c:url>' alt="fpt-logo"></a>
            </div>
            <div class="col-lg-3 my-auto">
                <h5 class="my-auto text-white">
                    FSOFT HR UTILITY
                </h5>
            </div>
            <sec:authorize access="isAuthenticated()">
	            <div class="col-lg-7 my-auto">
	                <p class="float-right my-auto text-white">
	                	<sec:authentication property="principal" var="user" />
	                    Welcome, ${user.username}
	                </p>
	                <br>
	                <p class="float-right my-auto text-white">
	                    <a style="color: white" href='<c:url value="/logout"></c:url>'><i class="fas fa-sign-out-alt mr-1"></i>Logout</a>
	                </p>
	            </div>
            </sec:authorize>
        </div>
    </div>
    <!-- Header ends -->
</body>
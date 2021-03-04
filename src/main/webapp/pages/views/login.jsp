<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
	<head>
	    <meta charset="UTF-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
	    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	    <link rel="stylesheet" type="text/css"
	        href="https://cdn.datatables.net/v/bs4/dt-1.10.20/sc-2.0.1/datatables.min.css" />
	        <link rel="stylesheet" href='<c:url value="/resources/css/login.css"></c:url>' h>
	    <title>Login</title>
	</head>
	<body>
	    <!-- Header -->
	    <%@ include file="/pages/templates/header.jsp"%>
	    <!-- Header ends -->
	    <div class="container-fluid">
	        <div class="row">
	            <div class="col-lg-2" style="height:90vh">
	                <h3>
	                    <a href="#">
	                        <span></span>
	                    </a>
	                </h3>
	            </div>
	            <div class="col-lg-10 border-left shadow">
	                <h3 class="pt-3">Login</h3>
	                <hr class="shadow-sm">
	                <div class="row">
	                    <div class="col-lg-6">
	                    <!-- action='<c:url value="/landing-page"></c:url>' -->
	                    <c:if test="${not empty message }">
	                   	 	<p style="color: red"><spring:message code="MSG1" /></p>
						</c:if>
	                        <form:form id="login-form" action="${pageContext.request.contextPath}/authenticateTheUser" method="post">
	                            <div class="form-group row">
	                                <label for="user-name" class="col-lg-2 col-form-label">Username</label>
	                                <div class="col-lg-5">
	                                    <input type="text" name="username" class="form-control" id="username">
	                                </div>
	                            </div>
	                            <div class="form-group row">
	                                <label for="inputPassword3" class="col-lg-2 col-form-label">Password</label>
	                                <div class="col-lg-5">
	                                    <input type="password" name="password" class="form-control" id="password">
	                                </div>
	                            </div>
	                            <div class="form-group row">
	                                <div class="col-lg-2"></div>
	                                <div class="col-lg-5">
	                                    <button id="login" style="padding: 8px 20px;" type="submit" class="btn btn-customized"><i class="fas fa-sign-in-alt mr-1" style="color: #205081;"></i>Login</button>
	                                    <button style="padding: 8px 20px;margin-left: 15px;" type="reset" class="btn btn-customized"><i class="fas fa-redo-alt mr-1" style="color: cornflowerblue;"></i>Reset</button>
	                                </div>
	                            </div>
	                        </form:form>
	                    </div>
	                    <div class="col-lg-6">
	                        <div class="card">
	                            <div class="card-body">
	                                <h5 class="card-title">Welcome to Fsoft HR Utility</h5>
	                                <p class="card-text" style="font-size: 14px;">This tool is developed to help Employees,
	                                    Project Manager, BJ
	                                    Manager, FSU Leads, HR
	                                    <br>(SSC, BP Manager) CTC, FWA, QA... in submitting HR related informations,
	                                    approving
	                                    the<br>
	                                    submitted info, monitoring status of you submissing .. and futher more
	                                </p>
	                                <a href="#" style="font-size: 14px;text-align: right;">More...</a>
	                                <hr>
	                                <p class="card-text" style="font-size: 14px;"><i>The recommended web browser is <span
	                                            style="color:blue;">G</span><span style="color:red;">o</span><span
	                                            style="color:read;">o</span><span style="color:blue;">g</span><span
	                                            style="color:rgb(255, 255, 0);">l</span><span style="color:red;">e</span>
	                                        Chrome Sorry for any inconvenience
	                                    </i></p>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	    <!-- Footer -->
	    <%@ include file="/pages/templates/footer.jsp"%>
	    <!-- Footer ends -->
	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/jquery.validate.min.js"></script>
	    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
	    <script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap4.min.js"></script>
	    <!-- landing-page.js -->
	    <script language="javascript" src='<c:url value="/resources/js/login-page.js"></c:url>'></script>
	    <!-- landing-page.js ends -->
	</body>
</html>
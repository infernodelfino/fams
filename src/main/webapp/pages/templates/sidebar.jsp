<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
			<div class="col-lg-2" id="side-bar">
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">Your role now is</li>
                    <li class="list-group-item">
                    <sec:authorize access="isAuthenticated()">
                    <sec:authentication property="principal" var="user" />
                        <select class="form-control">
                         <c:forEach var="ele" items="${roles}">
                   				 <option>${ele }</option>
                 			 </c:forEach>
                        <select class="form-control" id="">
                     </sec:authorize>
                    </li>
                    <li class="list-group-item">
                        <a href="#" id="landing-page-link">
                            Dashboard
                        </a>
                    </li>
                    <li class="list-group-item">
                        <a href="#">
                            Class Management
                        </a>
                    </li>
                    <li class="list-group-item">
                        <a href="#">
                            Candidate Management
                        </a>
                    </li>
                    <li class="list-group-item">
                        <a href="#">
                            Trainer Management
                        </a>
                    </li>
                    <li class="list-group-item">
                        <a href="#" id="trainee-management-link">
                            Trainee Management
                        </a>
                    </li>
                    <li class="list-group-item">
                        <a href="#">
                            General Setting
                        </a>
                    </li>
                </ul>
            </div>
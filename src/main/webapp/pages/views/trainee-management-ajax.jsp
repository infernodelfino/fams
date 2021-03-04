<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
    <link rel="stylesheet" href= '<c:url value="/resources/css/trainee-management-ajax.css"></c:url>'>
</head>

<h3 class="pt-3">Trainee Listing</h3>
<table id="traineeTable" class="table table-striped table-bordered">
    <thead>
        <tr>
            <th>
                <input type="checkbox" name="checkAll" id="checkAll">
            </th>
            <th>#</th>
            <th>Empl ID</th>
            <th>Account</th>
            <th>Name</th>
            <th>Status</th>
        </tr>
    </thead>
    <tbody>

     <c:forEach items="${traineeList}" var="trainee" varStatus="index">
        <tr>
        	<td>
        		<input type="checkbox" class="checkBoxTrainee" ids="${trainee.id}">
        	</td>
        	<td>
        		${index.count}
        	</td>
        	<td>
        		<a href="#" class="ajax-class" ids="${trainee.id }">
                    ${trainee.traineeID}
                </a>
        	</td>
        	<td>${trainee.account}</td>
        	<td>${trainee.name}</td>
        	<td>${trainee.status}</td>
        </tr>
     </c:forEach>
           
    </tbody>
</table>
<!-- update button -->
<a href="#" id="update-link" >
    <button type="button" class="btn btn-customized" id="update-btn">
        <i class="fa fa-arrow-circle-up mr-1" style="font: size 20px; color: cornflowerblue;"></i>Update Trainee
    </button>
</a>

<!-- delete button -->
<button type="button" class="btn btn-customized" id="delete-btn">
    <i class="fa fa-times" style="color: crimson;"></i> Delete trainee</button>
<!-- body end -->

<script src='<c:url value="/resources/js/trainee-management-ajax.js"></c:url>'></script>
</body>

</html>
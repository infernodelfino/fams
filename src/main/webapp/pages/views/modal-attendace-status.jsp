<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="modalViewAttendence" class="modal fade" role="dialog"> 
    <div class="modal-dialog modal-lg">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Attendace Status</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <div class="card">
                    <div class="card-header" style="background-color:rgb(142, 179, 247);">
                       ${dateCode}
                    </div>
                    <div class="card-body">
                        <div style="overflow: auto;">
                            <table class="table table-bordered">
                                <thead>
                                  <c:forEach items="${days}" var="day">
                                    	<th>${day}</th>
                                    </c:forEach>
                                </thead>
                                <tbody>
                                    <tr>
                                        <c:forEach items="${days}" var="day">
                                    	<th>P</th>
                                    </c:forEach>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <button data-toggle="modal" data-target="#modalUpdateAttendence"
                            data-dismiss="modal" type="button" class="btn btn-outline-dark">
                            <i class="fa fa-arrow-circle-up"
                                style="font: size 20px; color: cornflowerblue;"></i>Update</button>
                        <button type="button" class="btn btn-default btn-outline-dark" data-dismiss="modal">
										<i class="fas fa-reply" style="color: red;"></i>Close</button>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
            </div>
        </div>

    </div>
</div>

<!-- Second Modal to Update Attendence Status-->
<div id="modalUpdateAttendence" class="modal fade" role="dialog">
    <div class="modal-dialog modal-lg">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Attendace Status</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <div class="card">
                    <div class="card-header" style="background-color:rgb(142, 179, 247);">
                        ${dateCode}
                    </div>
                    <div class="card-body">
                        <div style="overflow: auto;">
                            <table class="table table-bordered">
                                <thead>
                                    <c:forEach items="${days}" var="day">
                                    	<th>${day}</th>
                                    </c:forEach>
                                </thead>
                                <tbody>
                                    <tr>
                                        <c:forEach items="${days}" var="day">
                                    		<td>
	                                            <select class="form-control check-update-attendace-status">
	                                                <option>P</option>
	                                                <option>A</option>
	                                                <option>E</option>
	                                                <option>L</option>
	                                                <option>An</option>
	                                                <option>En</option>
	                                                <option>Ln</option>
	                                            </select>
                                        	</td>
                                    	</c:forEach>
                                    </tr>
                                </tbody>
                                
                            </table>
                        </div>
                        <button data-toggle="modal" data-target="#modalUpdateAttendence"
                            type="button" id="btn-update-attendance-status" class="btn btn-primary btn-outline-dark">Submit</button>
                        <button type="button" class="btn btn-default btn-outline-dark" data-dismiss="modal"><i
                                class="fas fa-reply" style="color: crimson;"></i>Close</button>
                    </div>
                </div>
            </div>
            <div class="modal-footer">

            </div>
        </div>

    </div>
</div>
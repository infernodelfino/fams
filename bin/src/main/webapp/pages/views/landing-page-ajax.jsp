<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- body -->
                <h3 class="pt-3">Dashboard</h3>
                <hr class="shadow-sm">
                <div class="row">
                    <div class="col-lg-3">
                        <td class="float-left">
                            <form class="form-inline">
                                <label class="col-form-label mr-2">Dashboard for</label>
                                <select class="form-control" id="dashboard-for">
                                    <option value="class">Class</option>
                                    <option value="candidate">Candidate</option>
                                    <option value="trainee">Trainee</option>
                                </select>
                            </form>
                        </td>
                    </div>
                    <div class="col-lg-3">
                        <td class="float-left">
                            <form class="form-inline">
                                <label class="col-form-label mr-2">Location</label>
                                <select class="form-control" id="location">
                                    <option value="all">All</option>
                                    <c:forEach var="ele" items="${locations }">
                                    	<option value="${ele }" style="text-transform: uppercase">${ele }</option>
                                    </c:forEach>
                                    <!-- <option value="hanoi">Ha Noi</option>
                                    <option value="danang">Da Nang</option>
                                    <option value="hcm">Ho Chi Minh</option> -->
                                </select>
                            </form>
                        </td>
                    </div>
                    <div class="col-lg-3">
                        <td class="float-left">
                            <form class="form-inline">
                                <label class="col-form-label mr-2">Type of Dashboard</label>
                                <select class="form-control" id="type-of-dashboard">
                                    <option value="table" selected>Table</option>
                                    <option value="chart">Chart</option>
                                </select>
                            </form>
                        </td>
                    </div>
           			<!--  -->         
                    <div class="col-lg-3">
                        <td class="float-left">
                            <form class="form-inline">
                                <label class="col-form-label mr-2">Status</label>
                                <select class="form-control" id="status-for">
                                    <option value="all">All</option>
                                    <c:forEach var="ele" items="${statuses }">
                                    	<option value="${ele }" style="text-transform: uppercase">${ele }</option>
                                    </c:forEach>
<!--                                     <option value="planning">Planning</option>
                                    <option value="inprogress">In-Progress</option>
                                    <option value="planned">Planned</option> -->
                                </select>
                            </form>
                        </td>
                    </div>
                </div>
                <hr class="shadow-sm mb-5">
                <div class="dashboard-content">
                    
                </div>
<!-- body end -->
<!-- JS for login and landing page -->
<script language="javascript" src='<c:url value="/resources/js/landing-page.js"></c:url>'></script>
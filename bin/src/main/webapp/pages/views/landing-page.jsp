<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css"
        href="https://cdn.datatables.net/v/bs4/dt-1.10.20/sc-2.0.1/datatables.min.css" />
    <link rel="stylesheet" href='<c:url value="/resources/css/landing-page.css"></c:url>'>

    <!-- jquery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <!-- jquery ends -->
    
    <!-- chart library -->
    <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
    <!-- chart library end-->

    <!-- datePicker -->
    <link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css" />
    <!-- datePicker ends -->

    <title>Landing page</title>
</head>

<body>
    <!-- Header -->
    <%@ include file="/pages/templates/header.jsp"%>
    <!-- Header ends -->

    <div class="container-fluid">
        <div class="row">
            <!-- side menu -->
            <%@ include file="/pages/templates/sidebar.jsp"%>
            <!-- side menu end -->

            <!-- body -->
            <div class="col-lg-10 border-left shadow" id="ajax-area">
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
            </div>
            <!-- body end -->
        </div>
    </div>

    <!-- Footer -->
    <%@ include file="/pages/templates/footer.jsp"%>
    <!-- Footer ends -->

    <!-- landing-page.js -->
    <!-- landing-page.js ends -->
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/jquery.validate.min.js"></script>
    
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap4.min.js"></script>

    <!-- google chart -->
    <script src="https://www.gstatic.com/charts/loader.js"></script>

    <!-- datePicker -->
    <script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js" type="text/javascript"></script>

    <!-- JS for login and landing page -->
    <script language="javascript" src= '<c:url value="/resources/js/landing-page.js"></c:url>'></script>
</body>

</html>
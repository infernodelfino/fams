<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div id="modalAddLearningPath" class="modal fade" role="dialog"> 
    <div class="modal-dialog modal-lg">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Learning detail</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <div class="card text-center">
                    <div id="learnCodeOfLearnDetail" class="card-header" style="background-color:rgb(142, 179, 247);">${learnCode}</div>
                    <div class="card-body">
                    <div id="mess-add-learnDetail" style="color: red; text-align: center;"></div>
						  <div class="card-text row">
						      <div class="col-6  w-100 border border-secondary border-right-0"
						      	 id="listLearnDetailCurrent">
						      	<span>Topic of learning path ${learnCode}</span><hr>
						      		<select  multiple="multiple" size="6" class="w-100">
		                            	<c:forEach items="${listOfLearningDetailCurrent}" var="lp">
		                            		<option style="text-align: center;" value="${lp.topic.id}" lcode="${lp.id}">${lp.topic.topicName}</option>
		                            	</c:forEach>
		                            </select>
	                           </div>
						      <div class="col-6 w-100 border border-secondary" id="listTopic">
	                            	<span>List of topic</span><hr>
	                            	<select multiple="multiple" size="6" class="w-100">
	                            		<c:forEach items="${listOfTopic}" var="topic">
	                            			<c:set var="check" value="${1}" />
		                            		<c:forEach items="${listOfLearningDetailCurrent}" var="lp">
		                            			<c:if test="${lp.topic.id==topic.id}">
	                            					<c:set var="check" value="${0}" />
		                            			</c:if>
		                            		</c:forEach>
		                            		<c:if test="${check==1}">
	                            				<option style="text-align: center;" value="${topic.id}" lcode="">${topic.topicName}</option>
		                            		</c:if>
	                            		</c:forEach>
                            		</select>
	                           </div>
						  </div>
                        <div class="row" style="padding-left: 40%; margin-top: 20px;">
	                        <button id="btn-add-addLearnDetail" type="button" class="btn btn-outline-dark">
	                            <i class="fa fa-arrow-circle-up"
	                                style="font: size 20px; color: cornflowerblue;"></i>Add</button>&nbsp;
	                        <button id="btn-delete-addLearnDetail" type="button" class="btn btn-outline-dark">
	                            <i class="fa fa-arrow-circle-up"
	                                style="font: size 20px; color: cornflowerblue;"></i>Delete</button>
                        </div>
                    </div>
                </div>
            </div>
            <div style="padding-left: 75%; padding-bottom: 30px;">
            	<button id="btn-save-addLearnDetail" type="button" class="btn btn-outline-dark">
                            <i class="fa fa-arrow-circle-up"style="font: size 20px; color: cornflowerblue;">
                            </i>Save</button>
                <button type="button" class="btn btn-default btn-outline-dark" data-dismiss="modal">
									<i class="fas fa-reply" style="color: red;"></i>Close</button>
            </div>
        </div>

    </div>
</div>
<!-- Modal success error -->
<div class="modal fade" id="success-error-modal" tabindex="-1" role="dialog"
	aria-labelledby="rejectClass1" aria-hidden="true">

	<div class="modal-dialog" role="document">

		<div class="modal-content">

			<div class="modal-header">

				<h5 class="modal-title" id="rejectClass1">Cotification</h5>

				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<label for="recipient-name" class="col-form-label">
					<h5>
						<i class="fas fa-info-circle green"></i> 
							<span id="message"></span>
					</h5>
				</label>
			</div>
			<div class="modal-footer">
				<button type="button" id="return-view"
					class="btn btn-secondary return-view" data-dismiss="modal">Ok</button>
			</div>
		</div>
	</div>
</div>
<script src='<c:url value="/resources/js/modal-add-learnDetail.js"></c:url>'></script>

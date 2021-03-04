<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
tbody {
	height: 10em;
	overflow: scroll;
}

.scroll-table {
	overflow-y: scroll; 
	overflow-x: hidden;
}
</style>
<body>
	<div class="row">
		<c:forEach items="${statuses}" var="status">
			<div class="col-lg-4">
				<div>
					<caption align="center">
						<h4 align="center" style="text-transform: uppercase">${status}</h4>
					</caption>
				</div>

				<table class="table table-striped table-bordered">
					<thead>
						<tr>
							<th>Skill</th>
							<th>Plan of enrolment</th>
						</tr>
					</thead>
					<div class=".scroll-table">
						<tbody>
							<c:forEach items="${dataVo }" var="ele">
								<c:if test="${ele.status == status }">
									<tr>
										<td style="text-transform: uppercase">${ele.skill }</td>
										<td class=${status}>${ele.countData }</td>
									</tr>
								</c:if>
							</c:forEach>
							<tr>
								<td>Total</td>
								<td id="total-${status}"></td>
							</tr>
						</tbody>
					</div>
				</table>
			</div>
		</c:forEach>
	</div>

	<script>
		$(document).ready(function() {
			var total1 = 0;
			var total2 = 0;
			var total3 = 0;
			$('tr').each(function() {
				$(this).find('.planning').each(function() {
					var skill = $(this).text();
					if (skill.length !== 0) {
						total1 = total1 + Number(skill);
					}
				});
				$(this).find('.planned').each(function() {
					var skill = $(this).text();
					if (skill.length !== 0) {
						total2 = total2 + Number(skill);
					}
				});
				$(this).find('.inprogress').each(function() {
					var skill = $(this).text();
					if (skill.length !== 0) {
						total3 = total3 + Number(skill);
					}
				});
			});
			$(this).find('#total-planning').html(total1);
			$(this).find('#total-planned').html(total2);
			$(this).find('#total-inprogress').html(total3);
		});
	</script>
</body>
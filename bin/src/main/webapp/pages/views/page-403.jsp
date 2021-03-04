<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container-fluid">
		<!-- body -->
		<div class="row">
			<div class="col align-self-start"></div>
			<div class="col align-self-center">
				<div class="row justify-content-md-center">
					<div class="col col-lg-2"></div>
					<div style="padding-top: 10px" class="col-md-auto">
						<img src="https://media.giphy.com/media/2H67VmB5UEBmU/giphy.gif"
							alt="Wrong face">
						<p align="center">
						<h6 style="color: red">You do not have permission. Please log
							in with other account!!</h6>
						</p>
						<div class="row">
							<div class="col">
								<p align="center">
									<a href="javascript:history.back()">Go back home</a>
								</p>
							</div>
							<div class="col">
								<p align="center">
									<a href="/logout?notify=logout">Switch account</a>
								</p>
							</div>
						</div>

					</div>
					<div class="col col-lg-2"></div>
				</div>
			</div>
			<div class="col align-self-end"></div>
		</div>
	</div>

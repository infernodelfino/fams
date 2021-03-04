<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Wrong</title>
<style type="text/css">
@import 'https://fonts.googleapis.com/css?family=Inconsolata';

.content-error html {
	min-height: 100%;
}

.content-error {
	box-sizing: border-box;
	height: 100%;
	background-color: #000000;
	background-image: radial-gradient(#11581E, #041607),
		url("https://media.giphy.com/media/oEI9uBYSzLpBK/giphy.gif");
	background-repeat: no-repeat;
	background-size: cover;
	font-family: 'Inconsolata', Helvetica, sans-serif;
	font-size: 1.5rem;
	color: rgba(128, 255, 128, 0.8);
	text-shadow: 0 0 1ex rgba(51, 255, 51, 1), 0 0 2px
		rgba(255, 255, 255, 0.8);
}

.content-error .noise {
	pointer-events: none;
	position: absolute;
	width: 100%;
	height: 100%;
	background-image:
		url("https://media.giphy.com/media/oEI9uBYSzLpBK/giphy.gif");
	background-repeat: no-repeat;
	background-size: cover;
	z-index: -1;
	opacity: .02;
}

.content-error .overlay {
	pointer-events: none;
	position: absolute;
	width: 100%;
	height: 100%;
	background: repeating-linear-gradient(180deg, rgba(0, 0, 0, 0) 0,
		rgba(0, 0, 0, 0.3) 50%, rgba(0, 0, 0, 0) 100%);
	background-size: auto 4px;
	z-index: 1;
}

.content-error .overlay::before {
	content: "";
	pointer-events: none;
	position: absolute;
	display: block;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	width: 100%;
	height: 100%;
	background-image: linear-gradient(0deg, transparent 0%, rgba(32, 128, 32, 0.2)
		2%, rgba(32, 128, 32, 0.8) 3%, rgba(32, 128, 32, 0.2) 3%, transparent
		100%);
	background-repeat: no-repeat;
	animation: scan 7.5s linear 0s infinite;
}

@keyframes scan { 0% {
	background-position: 0 -100vh;
}

35%,
100%
{
background-position
:
 
0
100
vh
;
 
}
}
.content-error .terminal {
	box-sizing: inherit;
	position: absolute;
	height: 100%;
	width: 1000px;
	max-width: 100%;
	padding: 4rem;
	text-transform: uppercase;
}

.content-error .output {
	color: rgba(128, 255, 128, 0.8);
	text-shadow: 0 0 1px rgba(51, 255, 51, 0.4), 0 0 2px
		rgba(255, 255, 255, 0.8);
}

.content-error .output::before {
	content: "> ";
}

/*
.input {
  color: rgba(192, 255, 192, 0.8);
  text-shadow:
      0 0 1px rgba(51, 255, 51, 0.4),
      0 0 2px rgba(255, 255, 255, 0.8);
}

.input::before {
  content: "$ ";
}
*/
.content-error a {
	color: #fff;
	text-decoration: none;
}

.content-error a::before {
	content: "[";
}

.content-error a::after {
	content: "]";
}

.content-error .errorcode {
	color: white;
}
</style>
</head>
<body>
<div class="content-error">
	<div class="noise"></div>
	<div class="overlay"></div>
	<div class="terminal">
		<h1>
			Error <span class="errorcode">${error }</span>
		</h1>
		<p class="output">Oops! Something's wrong here...</p>
		<p class="output">
			Please try to <a href="javascript:history.back()">go back</a> or <a
				href='<c:url value="/landing-page" ></c:url>'>return to the
				homepage</a>.
		</p>
		<p class="output">Good luck.</p>
	</div>
</div>
</body>
</html>
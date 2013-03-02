<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page import="javax.servlet.http.HttpSession"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Project Runway</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- Added by Nambi -->
<meta name="robots" content="index, follow, noarchive" />
<meta name="googlebot" content="noarchive" />
<meta name="google-site-verification"
	content="taXffYEsxMjJzoLPkwToVQvHy5xu9R3mq0h1mlfDAOI" />
<link rel="SHORTCUT ICON" href="/images/favicon.ico" />
<!-- Added by Nambi -->

<!-- Bootstrap -->
<link href="/js/bootstrap/2.0.2/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="/js/bootstrap/2.0.2/css/bootstrap-responsive.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery.js"></script>
<script src="/js/bootstrap/2.0.2/js/bootstrap.min.js"></script>

<style type="text/css">
body {
	padding-top: 60px;
	background-repeat: repeat;
	background-image: url("/images/content_inner_bg_dark.png");
}
</style>


</head>

<body>
	<div class="container">
		<!-- begin : header -->
		<tiles:insertAttribute name="header" />
		<!-- end : header -->
	</div>



	<!--  begin: main page -->
	<tiles:insertAttribute name="body" />
	<!--  begin: main page -->

	<div class="footer-holder">
		<div class="container">
			<footer>
				<span>
					Icons from <a href="http://glyphicons.com">Glyphicons Free</a>,
					licensed under <a href="http://creativecommons.org/licenses/by/3.0/">CC BY 3.0</a>.
				</span>
				<span class="pull-right">Runway &copy;2013</span>
				<!--
				<span class="pull-right"><a href="">Privacy Policy</a> |&nbsp; </span>
				<span class="pull-right"><a href=""> About Us</a>|&nbsp; </span>
				 -->
			</footer>
		</div>
	</div>

	<!-- Le javascript -->

	<!-- Placed at the end of the document so the pages load faster -->
	<!-- 
	<script src="/js/bootstrap/59503e7/js/bootstrap-transition.js"></script>
	<script src="/js/bootstrap/59503e7/js/bootstrap-alert.js"></script>
	<script src="/js/bootstrap/59503e7/js/bootstrap-modal.js"></script>
	<script src="/js/bootstrap/59503e7/js/bootstrap-dropdown.js"></script>
	<script src="/js/bootstrap/59503e7/js/bootstrap-scrollspy.js"></script>
	<script src="/js/bootstrap/59503e7/js/bootstrap-tab.js"></script>
	<script src="/js/bootstrap/59503e7/js/bootstrap-tooltip.js"></script>
	<script src="/js/bootstrap/59503e7/js/bootstrap-popover.js"></script>
	<script src="/js/bootstrap/59503e7/js/bootstrap-button.js"></script>
	<script src="/js/bootstrap/59503e7/js/bootstrap-collapse.js"></script>
	<script src="/js/bootstrap/59503e7/js/bootstrap-carousel.js"></script>
	<script src="/js/bootstrap/custom/js/bootstrap-typeahead.js"></script>
	<script src="/js/bootstrap/59503e7/js/bootstrap-datepicker.js"></script>
	 -->
</body>
</html>

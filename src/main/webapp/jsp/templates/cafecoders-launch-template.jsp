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
<title>CafeCoders</title>
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

<script src="/js/bootstrap/59503e7/js/jquery.js"></script>
<!-- Le styles -->
<link href="/js/bootstrap/custom/css/bootstrap.css" rel="stylesheet" />
<link href="/js/bootstrap/custom/css/application.css" rel="stylesheet" />
<link href="/js/bootstrap/59503e7/css/datepicker.css" rel="stylesheet">
<link href='http://fonts.googleapis.com/css?family=Ubuntu:400' rel='stylesheet' type='text/css'>
<style type="text/css">
body {
	padding-top: 60px;
	padding-bottom: 40px;
	background-image: url("/images/launch-background.png");
}

</style>
<link href="/js/bootstrap/custom/css/bootstrap-responsive.css"
	rel="stylesheet">

<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<!-- Le fav and touch icons -->
<!-- 
    <link rel="shortcut icon" href="/js/bootstrap/59503e7/ico/favicon.ico">
     -->
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="/js/bootstrap/59503e7/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="/js/bootstrap/59503e7/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="/js/bootstrap/59503e7/ico/apple-touch-icon-57-precomposed.png">
</head>

<body>
 
	<!--  begin: main page -->
	<tiles:insertAttribute name="body" />
	<!--  begin: main page -->

	<div class="container">
		<footer>
			<p>&copy; CafeCoders.us 2012</p>
		</footer>
	</div>


	<!-- Le javascript -->

	<!-- Placed at the end of the document so the pages load faster -->
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

</body>
</html>

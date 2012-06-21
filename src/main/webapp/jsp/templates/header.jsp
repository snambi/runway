
<%@page import="twitter4j.User"%>
<div class="navbar navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
          <a class="brand" href="/"><icon class="icon-logo-medium"></icon>&nbsp;CafeCoders</a>
          <div class="nav-collapse">
            <ul class="nav">
              <li class="active"><a href="/"><i class="icon-home icon-aqua"></i>&nbsp;Home</a></li>
              <li><a href="/cafes"><i class="icon-list icon-aqua"></i>&nbsp;Cafes</a></li>
              <li><a href="/meetups"><i class="icon-tasks icon-aqua"></i>&nbsp;Meetups</a></li>
              <li><a href="/about"><i class="icon-info-sign icon-aqua"></i>&nbsp;About</a></li>
              <!-- 
              <li><a href="#contact">Contact</a></li>
              -->
            </ul>
            <ul class="nav pull-right">
            	<li>
            		<% if(session.getAttribute("twitter") == null) { %>
            		<a href="/login"><i class="icon-twitter"></i> sign in with twitter</a>
            		<% } else {
            		User user = ((User) session.getAttribute("user"));
            		%>
            		<a href="http://twitter.com/<%=user.getScreenName() %>"><span class="profile-img"><img src="<%=user.getProfileImageURL() %>"/></span><%= user.getName() %></a>
            		<% } %>
            	</li>
            </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>
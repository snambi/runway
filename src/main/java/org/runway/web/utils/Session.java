package org.runway.web.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

public class Session {
	
	public static final String AUTO_LOGIN = "AUTO_LOGIN";
	public static final String ID = "ID";
	public static final String TARGET_URI = "TARGET";
	public static final String IS_AUTHENTICATED = "IS_AUTHENTICATED";
	public static final String PLACED_ORDER = "PLACED_ORDER";
	public static final String CASH_TARNSFER = "CASH_TRANSFER";
	public static final String POST_DATA = "POST_DATA";
	public static final String GROUP = "GROUP";
	public static final String GROUP_INVITATION = "INVITATION";
	public static final String GROUP_MESSAGE = "GROUP_MESSAGE";
	public static final String POST_ID = "POST_ID";
	public static final String SEARCH_RESULTS = "SEARCH_RESULTS";
	public static final String MESSAGE = "MESSAGE";
	public static final String PROFILE_DATA = "PROFILE_DATA";
	public static final String PROMOTION_ID = "PROMOTION_ID";
	public static final String REVIEW = "REVIEW";
	public static final String LOGIN_SUCCESS_URL = "LOGIN_SUCCESS_URL";
	
	public static boolean isAuthenticated( HttpSession session ){
		boolean isLoggedin = false;
		SecurityContext context = SecurityContextHolder.getContext();
		if( context instanceof SecurityContext ){
			Authentication auth = context.getAuthentication();
			if( auth instanceof AnonymousAuthenticationToken){
				
			}else if( auth instanceof Authentication){
				isLoggedin = true;
			}
		}
		return isLoggedin;
	}
	
	public static String getUserId( HttpSession session){
		String userId = null;
		if( isAuthenticated(session)){
			UserDetails acegiUser= null ;
			Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if( object instanceof UserDetails ){
				acegiUser = (UserDetails) object;
				userId= acegiUser.getUsername();
			}
		}
		return userId;
	}
	
	public static String getURIWithQueryString( HttpServletRequest request){
		
		String uri = null;
		
		uri = request.getRequestURI();
		
		String queryStr = request.getQueryString();
		
		if( queryStr  != null && !queryStr.trim().equals("")){
			uri = uri + "?" + queryStr;
		}
		
		return uri;
	}
}

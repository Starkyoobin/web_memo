package com.starkyb.memo.common;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class PermissionInterceptor implements HandlerInterceptor{
	//요청이 들어올때
	@Override
	public boolean preHandle(
			HttpServletRequest request
			, HttpServletResponse response
			, Object handler) throws IOException {
		//로그인 상태에 따른 접근 권한 관리
		HttpSession session = request.getSession();
		//현재 요청한 uri(path) 알아오기
		String uri = request.getRequestURI();
		//비로그인 상태
		if(session.getAttribute("userId") == null) {
			// 리스트화면, 디테일화면, 글쓰기화면 접근 X
			// /post/** 접근 못하도록 -> 로그인 페이지로 이동
			if(uri.startsWith("/post")) {
				response.sendRedirect("/user/signin_view");
				return false;
			}
		} else {	//로그인 상태
			// 로그인화면, 회원가입화면 접근 X
			// /user/**
			if(uri.startsWith("/user")) {
				response.sendRedirect("/post/list_view");
				return false;
			}
		}
		
		return true;
	}
	//response 처리할때
	@Override
	public void postHandle(
			HttpServletRequest request
			, HttpServletResponse response
			, Object handler
			, ModelAndView modelAndView) {
		
	}
	//모든것이 완료되었을때
	@Override
	public void afterCompletion(
			HttpServletRequest request
			, HttpServletResponse response
			, Object handler
			, Exception ex) {
		
	}
}

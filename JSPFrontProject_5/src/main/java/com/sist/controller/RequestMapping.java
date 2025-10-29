package com.sist.controller;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/*
	어노테이션 : Spring(XML + 어노테이션)
		1) 정의 : 자바 코드의 부가 정보 표시
				-> 구분자(인덱스)
				- if추가하는 기능
		2) 사용
			---> 클래스 구분
			@class 구분 -----> TYPE
			class A
			{
				@field ------> FIELD
				B b;
				@constructor ---> 생성자 CONSTRUCTOR
				public A(@parameter B b){} PARAMETER
				@method --> METHOD
				public void display(){}
			}	
			-----------------------------------------------------
			@Target : 구분자
				- 생성자 CONSTURCTOR
				- 멤버변수 FIELD
				- 클래스 TYPE
				- 매개변수 PARAMETER
				- 메소드 METHOD
				
			@Retention : 유지 정책
				- SOURCE : 소스코드에만 남아있고, 컴파일 시에 사라진다
							@Override
				- CLASS : 컴파일 시에는 클래스 파일에 남아있으나 실행시 제거
				- RUNTIME : 프로그램 종료시까지 남아있다
			-> 어노테이션 이용
				바로 밑의 자원 사용
				@
				public void a()
				@
				public void b()
				
				------------------------------
				한 개의 기능 --> 클래스
				------------------------------
				관련된 기능 --> 클래스 : 메소드
				------------------------------
				-> 인터페이스 / 상속 -> 처리 가능
					-------------- POJO
					
			-> 메소드
				void ----> JSON
				Stirng ----> 파일(이동)
				----------------------
				return "../main/main.jsp" -> forward
				return "redirect:main.do" -> sendRedirect
*/
@Retention(RUNTIME)
@Target({ TYPE, METHOD })
public @interface RequestMapping {
	public String value();
}

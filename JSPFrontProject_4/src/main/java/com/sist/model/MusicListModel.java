package com.sist.model;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MusicListModel implements Model {

	@Override
	public String excute(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("msg", "뮤직 장르별 출력");
		
		return "../music/list.jsp";
	}

}

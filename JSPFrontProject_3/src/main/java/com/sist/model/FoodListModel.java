package com.sist.model;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.*;

import org.json.simple.*;

import com.sist.dao.*;
import com.sist.vo.*;

public class FoodListModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request, HttpServletResponse response) {
		String strPage = request.getParameter("page");
		if(strPage==null)
			strPage = "1";
		int curPage = Integer.parseInt(strPage);
		Map map = new HashMap();
		int rowSize = 12;
		int start = (rowSize * curPage) - (rowSize-1);
		int end = rowSize * curPage;
		map.put("start", start);
		map.put("end", end);
		List<FoodVO> list = FoodDAO.foodListData(map);
		
		int totalPage = FoodDAO.foodTotalPage();
		final int BLOCK = 10;
		int startPage = ((curPage-1) / BLOCK* BLOCK) + 1;
		int endPage = ((curPage -1) / BLOCK * BLOCK) + BLOCK;
		if(endPage > totalPage)
			endPage = totalPage;
		
		// 결과값 전송
		JSONArray arr = new JSONArray(); // List매칭
		// [] -> [{},{},{},{},...]
		int i = 0;
		for(FoodVO vo : list)
		{
			JSONObject obj = new JSONObject();
			//JSONObject = new JSONObject();
			obj.put("fno", vo.getFno());
			obj.put("name", vo.getName());
			obj.put("poster", vo.getPoster());
			if(i==0)
			{
				obj.put("curPage", curPage);
				obj.put("totalPage", totalPage);
				obj.put("startPage", startPage);
				obj.put("endPage", endPage);
			}
			i++;
			arr.add(obj);
		}
		request.setAttribute("json", arr.toJSONString());
		return "list.jsp";
	}

}

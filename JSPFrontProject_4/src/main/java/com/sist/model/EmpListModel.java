package com.sist.model;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import org.json.simple.*;
import com.sist.dao.*;
import com.sist.vo.*;
public class EmpListModel implements Model {

	@Override
	public String excute(HttpServletRequest request, HttpServletResponse response) {
		List<EmpVO> list = EmpDAO.empListData();
		JSONArray arr = new JSONArray();// list
		for(EmpVO vo : list)
		{
			JSONObject obj = new JSONObject(); // vo
			obj.put("empno", vo.getEmpno());
			obj.put("ename", vo.getEname());
			obj.put("job", vo.getJob());
			obj.put("dbday", vo.getDbday());
			obj.put("deptno", vo.getDeptno());
			arr.add(obj);
		}
		request.setAttribute("json", arr.toJSONString());
		return "../emp/list.jsp";
	}

}

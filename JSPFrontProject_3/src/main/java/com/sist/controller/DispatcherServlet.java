package com.sist.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;

import com.sist.model.*;

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map clsMap = new HashMap();
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		try {
			String path="C:\\webDev\\webStudy\\JSPFrontProject_3\\src\\main\\webapp\\WEB-INF\\config\\application.xml";
			File file = new File(path);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			//  -> xml 파싱기 생성 -> 싱글톤
			
			DocumentBuilder db = dbf.newDocumentBuilder();
			// -> Xml 파싱
			
			Document doc = db.parse(file);
			// -> Xml의 파실된 냉용 저장 -> 트리 형태 저장
			// 1. Rood 태그 읽기 -> table명
			Element root = doc.getDocumentElement();
			//System.out.println(root.getTagname());
			// 같은 태그를 묶어서 반복문 수행
			NodeList list = root.getElementsByTagName("bean");
			for(int i = 0; i < list.getLength(); i++)
			{
				Element bean = (Element)list.item(i);
				String id = bean.getAttribute("id");
				String cls = bean.getAttribute("class");
				//System.out.println(id+":" + cls);
				Class className=Class.forName(cls);
				Object obj = className.getDeclaredConstructor().newInstance();
				System.out.println(i+":"+obj);
				clsMap.put(id, obj);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 메뉴를 찾아서 주문요청
		String cmd = request.getRequestURI();
		cmd = cmd.substring(request.getContextPath().length()+1);
		//System.out.println("cmd="+cmd);
		// Model을 찾아서 메소드 호출 -> 요청처리 -> JSP로 전송
		Model model=(Model)clsMap.get(cmd);
		String jsp = model.handlerRequest(request, response);
		RequestDispatcher rd = request.getRequestDispatcher(jsp);
		rd.forward(request, response);
	}

}

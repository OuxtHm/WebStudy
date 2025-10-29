package com.sist.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;

import java.net.*;

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private List<String> cList = new ArrayList<String>();
	
	public void init(ServletConfig config) throws ServletException {
		try {
			// realpath
			URL url = this.getClass().getClassLoader().getResource(".");
			File file = new File(url.toURI());
			System.out.println(file.getPath());
			String path = file.getPath();
			path = path.replace("\\", File.separator);
			path = path.substring(0, path.lastIndexOf(File.separator));
			System.out.println(path);
			path = path + File.separator + "application.xml";
			System.out.println(path);
			// XML 파싱
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			// 파서기 생성기
			DocumentBuilder db = dbf.newDocumentBuilder();
			// 파서 -> DOM -> 트리형태
			Document doc = db.parse(new File(path));
			// ROOT
			Element beans = doc.getDocumentElement();
			System.out.println(beans.getTagName());
			NodeList list = beans.getElementsByTagName("context:component-scan");
			Element cs = (Element)list.item(0);
			String pack = cs.getAttribute("basepackage");
			
			System.out.println(pack);
			cList = FileRead.pacakgeClassLoader(file.getPath(), pack);
			
			for(String s : cList)
			{
				System.out.println(s);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 사용자의 요청 정보 받기
		/*
			http://localhost/JSPFrontProject_5/sawon/list.do
			cmd = "sawon/list.do"
		*/
		String cmd = request.getRequestURI();
		cmd = cmd.substring(request.getContextPath().length()+1);
		
		try {
			for(String cls : cList)
			{
				Class clsName = Class.forName(cls);
				if(!clsName.isAnnotationPresent(Controller.class))
					continue;
				Object obj = clsName.getDeclaredConstructor().newInstance();
				
				Method[] methods = clsName.getDeclaredMethods();
				/*
					@RequestMapping("board/list.do")
					public String board_list()
					{
					}
					sawon/list.do
				*/
				for(Method method : methods)
				{
					RequestMapping rm = method.getAnnotation(RequestMapping.class);
					if(cmd.equals(rm.value()))
					{
						String jsp = (String)method.invoke(obj, request, response);
						if(jsp == null) // void --> JSON
						{
							return;
						}
						else
						{
							if(jsp.startsWith("redirect:"))
							{
								String s = jsp.substring(jsp.indexOf(":") + 1);
								response.sendRedirect(s);
							}
							else
							{
								RequestDispatcher rd = request.getRequestDispatcher(jsp);
								rd.forward(request, response);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}

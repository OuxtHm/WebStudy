package com.sist.temp;

import com.sist.controller.Controller;
import java.util.*;

public class MainClass3 {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("com.sist.model.BoardModel");
		list.add("com.sist.model.SawonModel");
		list.add("com.sist.model.MainClass");
		list.add("com.sist.model.FoodModel");
		
		try {
			for(String cls : list)
			{
				Class clsName=Class.forName(cls);
				if(!clsName.isAnnotationPresent(Controller.class))
				{
					continue;
				}
					
				Object obj = clsName.getDeclaredConstructor().newInstance();
				System.out.println(obj);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

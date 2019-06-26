package com.spring.demo.action;



import com.spring.demo.service.IDemoService;
import com.spring.framework.annotation.LyAutowired;
import com.spring.framework.annotation.LyController;
import com.spring.framework.annotation.LyRequestMapping;
import com.spring.framework.annotation.LyRequestParam;
import com.spring.framework.webmvc.LyModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@LyController
@LyRequestMapping("/demo")
public class DemoAction {
	
	@LyAutowired
	private IDemoService demoService;
	
	@LyRequestMapping("/query.json")
	public LyModelAndView query(HttpServletRequest req, HttpServletResponse resp,
								@LyRequestParam("name") String name){
		String result = demoService.get(name);
		System.out.println(result);
		try {
			resp.getWriter().write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@LyRequestMapping("/edit.json")
	public LyModelAndView edit(HttpServletRequest req, HttpServletResponse resp, Integer id){
		return null;

	}
	
}

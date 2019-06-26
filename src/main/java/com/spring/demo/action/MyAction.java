package com.spring.demo.action;


import com.spring.demo.service.IDemoService;
import com.spring.framework.annotation.LyAutowired;
import com.spring.framework.annotation.LyController;
import com.spring.framework.annotation.LyRequestMapping;
import com.spring.framework.webmvc.LyModelAndView;

@LyController
public class MyAction {

		@LyAutowired
		IDemoService demoService;
	
		@LyRequestMapping("/index.html")
		public LyModelAndView query(){

			return null;
		}
	
}

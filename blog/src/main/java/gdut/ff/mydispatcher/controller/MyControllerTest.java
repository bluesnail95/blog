package gdut.ff.mydispatcher.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gdut.ff.mydispatcher.annotation.MyAutowired;
import gdut.ff.mydispatcher.annotation.MyController;
import gdut.ff.mydispatcher.annotation.MyRequestMapping;
import gdut.ff.mydispatcher.service.MyServiceTest;

@MyController
@MyRequestMapping("/myTest")
public class MyControllerTest {
	
	@MyAutowired
	MyServiceTest myServiceTest;
	
	@MyRequestMapping("/test")
	public void test(HttpServletRequest request, HttpServletResponse response) {
		
		myServiceTest.createMyAnntationService();
	}

}

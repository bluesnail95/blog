package gdut.ff.mydispatcher.service;

import org.springframework.transaction.annotation.Transactional;

import gdut.ff.mydispatcher.annotation.MyService;

@Transactional
@MyService
public class MyServiceTest {

	public void createMyAnntationService() {
		System.out.println("my annotation service");
	}
}

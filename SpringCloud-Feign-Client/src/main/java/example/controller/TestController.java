package example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import example.service.MyService;

@RestController
@RequestMapping("feign")
public class TestController {

	@Autowired
	private MyService myService;
	
	@RequestMapping("/get")
	public String getServiceInfo() {
		return myService.getServiceInfo();
	}
}

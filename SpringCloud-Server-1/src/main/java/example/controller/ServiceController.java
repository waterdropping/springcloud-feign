package example.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service")
public class ServiceController {

	@Value("${server.port}")
	private String port;
	
	@GetMapping("/info")
	public String getServiceInfo() {
		return "Service is available, port = " + port;
	}
}

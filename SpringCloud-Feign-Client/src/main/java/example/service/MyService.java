package example.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("myservice")
public interface MyService {
	
	@GetMapping("/service/info")
	public String getServiceInfo();
	
}

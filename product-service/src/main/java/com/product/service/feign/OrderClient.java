package com.product.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "order-service", url = "http://localhost:8082")
public interface OrderClient {
	@GetMapping(value = "/order", headers = "Authorization=Bearer my_token")
	String getOrder();
	
	// Or, you can pass the header value dynamically
    @GetMapping("/order")
    String getOrderWithHeader(@RequestHeader("Authorization") String authorization);
}

package com.avensys.rts.dashboardservice.apiclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.avensys.rts.dashboardservice.interceptor.JwtTokenInterceptor;
import com.avensys.rts.dashboardservice.response.HttpResponse;

@FeignClient(name = "user-service", url = "${api.user.url}", configuration = JwtTokenInterceptor.class)
public interface UserAPIClient {
	@GetMapping("/{id}")
	HttpResponse getUserById(@PathVariable("id") Integer id);

	@GetMapping("/email/{email}")
	HttpResponse getUserByEmail(@PathVariable("email") String email);

	@GetMapping("/{id}")
	HttpResponse find(@PathVariable("id") Long id);

	@GetMapping("/profile")
	HttpResponse getUserDetail();

	@GetMapping("/users-under-manager")
	HttpResponse getUsersUnderManager();

}

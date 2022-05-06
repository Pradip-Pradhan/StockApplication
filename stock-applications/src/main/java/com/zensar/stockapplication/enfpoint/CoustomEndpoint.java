package com.zensar.stockapplication.enfpoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Endpoint(id = "custom")
@Component
public class CoustomEndpoint {

	@ReadOperation
	public String myOwnCustomEndpoint() {
		return "MY Custom EndPoint";
	}
}

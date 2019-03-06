package com.loran.controller;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExRibbonController {

	private DiscoveryClient discoveryClient;
	public String RibbonNumber()
	{
		discoveryClient.getInstances("loran-member");
		return null;
	}
}

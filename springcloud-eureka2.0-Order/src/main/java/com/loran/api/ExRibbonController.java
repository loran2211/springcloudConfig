package com.loran.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ExRibbonController {
	// 获取注册中心上的服务列表
	@Autowired
	private DiscoveryClient discoveryClient;	
	@Autowired
	private RestTemplate restTemplate;

	// 接口请求总数
	private int reqCount;
	@RequestMapping(value="/RibbonNumber")
	public String RibbonNumber() {
		// 1、获取对应服务器远程调用地址
		String instances = getInstance() + "/member/getMember";
		System.out.println("instancesURL:" + instances);
		// 2、使用rest方式发送请求
		String Result=restTemplate.getForObject(instances, String.class);
		return Result;
	}

	public String getInstance() {

		List<ServiceInstance> listInstance = discoveryClient.getInstances("loran-member");
		if (listInstance == null || listInstance.size() <= 0) {
			return null;
		}
		// 获取服务器集群个数
		int InstanceSize = listInstance.size();
		int ServiceIndex = reqCount % InstanceSize;
		reqCount++;
		return listInstance.get(ServiceIndex).getUri().toString();
	}
}

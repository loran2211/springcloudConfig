package com.loran.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.loran.entity.Student;

@RestController
public class OrderController {

	private String REST_URL_PREFIX = "http://loran-member:8001";
	@Autowired
	private DiscoveryClient client;

	// RestTemplate 是由SpringBoot Web组件提供默认整合ribbon负载均衡器
	@Autowired
	private RestTemplate restTemplate;

	// 在spirngcloud有两种方式调用 rest、fegin(springcloud)
	// 底层采用httpclient，
	@RequestMapping("/getOrder")
	public String GetOrder() {
		String Result = restTemplate.getForObject(REST_URL_PREFIX+"/member/getMember", String.class);
		System.out.println("订单服务调用会员服务：" + Result);
		return Result;
	}

	@RequestMapping(value = "/order/discovery", method = RequestMethod.GET)
	public Object discovery() {
		// 获取所有的微服务
		List<String> list = client.getServices();
		System.out.println("**********" + list);
		// 获取指定的服务
		List<ServiceInstance> srvList = client.getInstances("member-8001");
		// 打印这个服务的信息
		for (ServiceInstance element : srvList) {
			System.out.println(element.getServiceId() + "\t" + element.getHost() + "\t" + element.getPort() + "\t"
					+ element.getUri());
		}
		return this.client;
	}

	@RequestMapping(value = "/order/addStu", method = RequestMethod.GET)
	public boolean addStu() {
		Student stu = new Student();
		stu.setId(1);
		stu.setName("张三");
		stu.setAddress("北京市海淀区");
		stu.setAge(18);
		return restTemplate.postForObject(REST_URL_PREFIX + "/member/addStu", stu, Boolean.class);
	}
	
	@RequestMapping(value = "/order/getStu/{id}", method = RequestMethod.GET)
	public Student getStu(@PathVariable("id") Long id) {
		return restTemplate.getForObject(REST_URL_PREFIX + "/member/getStu/" + id, Student.class);
	}
}

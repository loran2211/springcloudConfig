package com.loran.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.loran.entity.Student;

@RestController
public class HelloController {
	@Value("${server.port}")
	private String serverPort;

	@RequestMapping("/member/getMember")
	public String getMember() {
		return "我是会员服务,端口号：" + serverPort;
	}

	@RequestMapping(value = "/member/addStu")
	public boolean add(Student stu) {
		if (stu != null) {
			return true;
		}
		return false;
	}

	@RequestMapping(value = "/member/getStu/{id}")
	public Student GetStu(@PathVariable("id") Long id) {
		Student stu=new Student();
		stu.setId(1);
		stu.setName("张三");
		stu.setAddress("北京市海淀区");
		stu.setAge(18);
		return stu;
	}
}

package com.loran;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderAppliaction {
	public static void main(String[] args) {
		SpringApplication.run(OrderAppliaction.class, args);
		//如果使用rest方式以别名方式进行调用依赖ribbon负载均衡器@Loadbalanced
	}
	//将RestTemplate注册至Springboot容器中@bean
	@Bean
	//@LoadBalanced  //@Loadbalanced就能让RestTemplate在请求时拥有客户端负载均衡的能力
	RestTemplate restTemplate()
	{
		return new RestTemplate();
	}
}

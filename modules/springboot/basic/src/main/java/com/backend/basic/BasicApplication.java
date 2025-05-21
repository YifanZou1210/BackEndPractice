// 代码声明在com.backend.basic包下，通过package组织类路径
package com.backend.basic;
// 导入Spring Boot启动和自动配置核心类，该结构适用于在src/main/java下搭建一个Spring Boot项目
// 确保组件扫描能自动发现同包及子包的bean
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 组合注解，集成了@Configuration 配置类
// @EnableAutoConfiguration 启动自动装配
// @ComponentScan 组件扫描
@SpringBootApplication
// @RestController表示可用endpoint
@RestController
public class BasicApplication {
public static void main(String[] args) {
	// 创建SpringApplication实例、准备ApplicationContext、加载Environment（环境）、执行自动配置、注册CommandLineRunner并启动嵌入式Web服务器（如Tomcat）。
	// args参数可用于激活不同Profile或传递自定义选项；若传入错误类或参数，会导致上下文初始化异常。

	// SpringApplication.run(...)是一个静态便捷方法，内部等同于：
	// new SpringApplication(BasicApplication.class).run(args);
	// 即使外部没有创建SpringApplication实例，SpringApplication.run(...)也会创建一个实例并执行run启动方法
	SpringApplication.run(BasicApplication.class, args);
}
	// 具体启动流程：
	// 1. 读取@SpringBootApplication注解的属性，注册默认的ApplicationContextInitializer和ApplicationListeners
	// 2. 根据类路径依赖判断使用哪种ApplicationContext(Web or Non-Web)
	// 3. 随后app.run(args)执行：环境准备(Environment)→ 创建上下文→ 加载bean定义→ 自动配置→ 刷新上下文→ 启动嵌入式Web服务器。

	// 表示用后端自设hello方法来respond发送到http://localhost:8081/hello地址的请求
	@GetMapping("/hello")
	// RequestParam告诉spring在请求中期望name value，如果不存在则默认为World
	// 如果在request中加入name=xxx，即http://localhost:8081/hello?name=xxx,则会在api page中显示hello xxx
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}
}

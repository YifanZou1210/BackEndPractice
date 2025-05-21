package com.backend.basic;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;
// 组合注解，等于@Controller+@ResponseBody,方法返回值直接序列化为JSON
@RestController
public class GreetingController {
    private final AtomicLong counter = new AtomicLong();

    //这种格式没有MVC架构，是直接将业务逻辑放在Controller并返回Greeting，意味着Controller承担接受请求、处理逻辑和组装响应的职责
    //如果用MVC的话会将将业务逻辑放在独立的Service层，Controller仅负责参数绑定与调用Service，并返回结果，如下
    /**
     * //@RestController
     * public class GreetingController {
     *     private final GreetingService service;
     *     public GreetingController(GreetingService service) { this.service = service; }
     *
     *     //@GetMapping("/greeting")
     *     public Greeting greeting(@RequestParam(defaultValue="World") String name) {
     *         return service.createGreeting(name);
     *     }
     * }
     * //@Service
     * public class GreetingService {
     *     private final AtomicLong counter = new AtomicLong();
     *     public Greeting createGreeting(String name) {
     *         return new Greeting(counter.incrementAndGet(), String.format("Hello, %s!", name));
     *     }
     * }
     */
    @GetMapping("/greeting")
    // 将HTTP GET /greeting请求映射到该greeting方法上
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        //从counter获取自增id，使用String.format格式化字符串，返回Greeting对象
        String template = "Hello, %s!";
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }}

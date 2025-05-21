package com.backend.basic;
// 使用jackson转换器映射需要确认Greeting.java与GreetingController.java在同一包或子包下
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * 资源表示类（Resource Representation Class）
 * 使用 Java 16+ record 简化不可变数据载体定义
 * Jackson 自动序列化 record 的组件属性为 JSON 字段
 * 等同于定义私有final字段,全参构造器和getID()/getContent()方法
 */
// Jackson 默认集成于 spring-boot-starter-web，通过 MappingJackson2HttpMessageConverter 将对象序列化/反序列化为 JSON，无需额外配置
// 若实体复杂或需自定义映射，可编写 JsonSerializer/JsonDeserializer 类并用 @JsonComponent 注册
// 下一步可在控制器（Controller）中直接注入实体/DTO，使用 @RestController、@GetMapping 或 @PostMapping 接收并返回 record 对象
public record Greeting(
//record时Java16引入的专门用于简化不可变数据载体的语法糖，自动生成构造器和只读字段
        @JsonProperty("id") long id, // JSON id字段绑定entity id字段
        @JsonProperty("content") String content) // JSON content字段
{}

// 如果为了适应java8或者添加额外逻辑，可以改成普通类，即：
/* 资源表示类-普通POJO
 * public class Greeting {
 *     @JsonProperty("id")
 *     private final long id;
 *
 *     @JsonProperty("content")
 *     private final String content;
 *
 *     // 全参构造器
 *     public Greeting(long id, String content) {
 *         this.id = id;
 *         this.content = content;
 *     }
 *
 *     // Getter 方法
 *     public long getId() {
 *         return id;
 *     }
 *     public String getContent() {
 *         return content;
 *     }
 * }
 */

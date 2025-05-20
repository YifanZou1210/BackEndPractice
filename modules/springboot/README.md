##### Spring Core Concept | 核心概念与练习要点

* **依赖注入（Dependency Injection）**：通过 XML、`@Component` + `@Autowired`、`@Bean` 方法比对 field、setter、constructor 注入的优缺点
* **控制反转（IoC，Inversion of Control）**：演示 ApplicationContext 的启动流程与 BeanFactory 的懒加载
* **Bean 生命周期（Bean Lifecycle）**：使用 `InitializingBean`、`DisposableBean`、`@PostConstruct`、`@PreDestroy` 回调，定义自定义 BeanPostProcessor
* **Bean 范围（Scope）**：练习 `singleton`、`prototype`、`request`、`session` 作用域及自定义 Scope
* **条件装配（Conditional）**：利用 `@ConditionalOnProperty`、`@Profile` 在不同环境加载不同 Bean
* **事件发布/监听（ApplicationEvent & Listener）**：自定义事件、同步/异步监听、优先级设置

##### Basic 入门项目练习

* **Spring Boot 启动流程**：查看 `SpringApplication.run()`、Banner、Environment、AutoConfiguration 加载机制
* **外部化配置（Externalized Configuration）**：`application.properties/yml`、`@Value`、`@ConfigurationProperties` 绑定复杂类型
* **日志（Logging）**：配置 Logback、Log4J2，演示不同环境的日志级别切换
* **健康检查（Actuator）**：启用 Spring Boot Actuator，练习自定义 Endpoint
* **命令行运行（CommandLineRunner & ApplicationRunner）**：实现初始化逻辑

##### REST CRUD 服务项目

* **Spring MVC 基础**：`@RestController`、`@RequestMapping`、路径与参数绑定（`@PathVariable`、`@RequestParam`、`@RequestBody`）
* **请求/响应格式**：JSON 序列化（Jackson）、日期格式、全局异常处理（`@ControllerAdvice` + `@ExceptionHandler`）
* **Swagger/OpenAPI 集成**：`springdoc-openapi` 或 `springfox` 文档自动生成
* **分页与排序（Pagination & Sorting）**：`Pageable`、`@PageableDefault`、统一响应封装
* **HATEOAS 实现**：添加超媒体链接，练习 `RepresentationModel`

##### Data JPA & Hibernate

* **实体映射（@Entity）**：主键生成策略（`IDENTITY`、`SEQUENCE`）、字段映射与约束
* **关联关系**：一对一（`@OneToOne`）、一对多（`@OneToMany`）、多对一（`@ManyToOne`）、多对多（`@ManyToMany`）与级联（Cascade）、抓取策略（FetchType）
* **查询方法**：方法名查询（Query Creation）、`@Query` 自定义 JPQL/原生 SQL、`@Modifying` 更新/删除
* **分页查询**：`Page<T>`、`Slice<T>` 以及性能考量
* **锁（Locking）**：悲观锁（Pessimistic Lock）、乐观锁（Optimistic Lock + `@Version`）
* **批量操作**：设置 `spring.jpa.properties.hibernate.jdbc.batch_size` 与 `saveAll()`

##### Spring Security 安全认证

* **基于表单/Basic Auth**：`WebSecurityConfigurerAdapter`（Spring Boot 2） 或 `SecurityFilterChain`（Spring Boot 3）自定义登录流程
* **JWT 实现**：`UsernamePasswordAuthenticationFilter`、`OncePerRequestFilter` 验证 token
* **OAuth2 客户端与资源服务器**：`spring-security-oauth2-client`、`spring-security-oauth2-resource-server`
* **权限注解**：`@PreAuthorize`、`@PostAuthorize`、方法级安全

##### Cache 缓存实践

* **Spring Cache 抽象**：配置 `@EnableCaching`、`@Cacheable`、`@CacheEvict`、`@CachePut`
* **Redis 集成**：使用 `Lettuce` 或 `Jedis` 连接池，配置序列化策略
* **本地缓存（Caffeine）**：自定义缓存容量、过期策略与统计监控

##### Messaging 消息驱动

* **Spring Kafka**：`KafkaTemplate` 生产者、`@KafkaListener` 消费者、分区与消费组配置
* **Spring AMQP（RabbitMQ）**：`RabbitTemplate`、`@RabbitListener`、交换机/队列/路由键绑定

##### Testing & CI

* **单元测试**：`@WebMvcTest`、`@DataJpaTest`、`MockMvc`、`Mockito`
* **集成测试**：`@SpringBootTest` + Testcontainers（MySQL、Kafka、Redis）
* **CI 配置**：GitHub Actions 矩阵测试，多 JDK + Docker Compose

##### Follow-Up Question

你想先从哪个模块（如 Spring Core、REST CRUD 或 Data JPA）开始深入实战？

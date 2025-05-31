##### 可代码实践的基础特性

* **启动流程（Startup Flow）**：在项目中插入日志或断点，打印 `SpringApplication.run()` 各阶段事件（EnvironmentPrepared、ContextRefreshed 等），理解自动装配（Auto‐Configuration）和 Banner 打印顺序。
* **外部化配置（Externalized Configuration）**：使用 `@Value` 与 `@ConfigurationProperties` 绑定 `application.yml` 中多环境（Profile）配置，演练属性覆盖、命令行参数和环境变量优先级。
* **日志切换（Logging）**：编写 `logback-spring.xml`，针对 `dev`/`prod` Profile 配置不同日志级别和输出格式，验证运行时切换与异步日志优化。
* **健康检查（Actuator）**：引入 `spring-boot-starter-actuator`，暴露 `/actuator/health`、`/actuator/metrics`，并自定义 `@Endpoint` 实现新端点，结合安全配置控制访问。
* **命令行运行（CommandLineRunner & ApplicationRunner）**：实现初始化 Bean，加载启动数据或校验第三方服务连通性，并演示阻塞与异步执行的启动性能对比。
* **多环境 Profiles**：创建 `application-dev.yml`、`application-prod.yml`，通过 `spring.profiles.active` 切换并在运行时打印生效配置源，验证 Profile 隔离与动态刷新（`@RefreshScope`）。
* **Bean 作用域（Bean Scope）**：分别定义 `singleton`、`prototype`、`@RequestScope` 和 `@SessionScope` Bean，在 Controller 中演练它们的创建与销毁时机；处理单例内注入原型 Bean 的用法。
* **Bean 生命周期（Lifecycle）**：实现 `BeanPostProcessor`、`InitializingBean`、`DisposableBean`、`@PostConstruct`/`@PreDestroy` 回调，观察 Bean 在容器启动和关闭时的完整流程。
* **自动配置原理（Auto‐Configuration）**：开启 `--debug` 并查看 `/actuator/conditions`，分析 `spring.factories` 中的自动配置条件（`@ConditionalOnClass`、`@ConditionalOnMissingBean` 等）加载情况。
* **依赖注入（Dependency Injection）**：在不同场景下对比构造器注入、字段注入与 `@Qualifier`、`@Primary` 冲突解决，演示懒加载（`@Lazy`）与循环依赖处理。

###### Other Features 

1. **微服务架构 + 容器化部署**

   * **拆分子服务**：将单体 REST 应用拆分为若干相互独立的微服务（如用户服务、订单服务、商品服务等），每个服务拥有独立数据库与职责。
   * **Docker 化**：为每个微服务编写 `Dockerfile`，将其构建为容器镜像，模拟生产环境部署。
   * **Kubernetes（或 Docker Compose）编排**：利用 K8s 或 Docker Compose 编排多容器，演示服务发现、负载均衡、自动扩缩容（HPA）等能力。
   * **项目亮点**：展示你对分布式系统拆解、容器化、DevOps 理念以及服务注册与发现（Eureka、Consul 或 Kubernetes DNS）有实际动手经验。

2. **响应式编程（Reactive Stack）**

   * **Spring WebFlux / Reactor**：将传统的同步阻塞式 `Spring MVC` 替换为响应式非阻塞 `Spring WebFlux`，合理使用 `Mono`、`Flux` 处理异步数据流。
   * **R2DBC 或 Reactive Redis**：在数据库访问层使用 R2DBC 驱动连接关系型数据库，或使用 `Lettuce` 的异步 Redis 客户端。
   * **项目亮点**：展示对 Reactive Programming 模型的理解，能处理高并发场景下的“背压”问题（backpressure）及资源利用率优化。

3. **弹性设计（Resilience）与容错模式**

   * **Circuit Breaker（断路器）**：集成 Resilience4j 或 Spring Cloud Circuit Breaker，实现对下游服务调用失败时的熔断与降级逻辑。
   * **Bulkhead 隔舱模式**：通过限流或线程池隔离，将某个依赖服务的失败限制在特定隔舱内，避免连锁故障。
   * **Retry 重试机制**：对外部 HTTP 调用或数据库操作设置重试次数与退避策略。
   * **项目亮点**：展示你对分布式系统常见故障（延迟、雪崩、级联）有预案，以及对 Resilience4j、Spring Retry 等组件的实战应用。

4. **消息驱动架构（Event-Driven / Messaging）**

   * **Kafka / RabbitMQ 集成**：将某些业务流程（如订单创建后通知库存、发邮件或推送日志）改为异步消息队列方式，实现“生产者-消费者”解耦。
   * **Spring Cloud Stream / Spring AMQP**：使用 Spring 自带的抽象层简化与消息中间件交互。
   * **项目亮点**：展示事件驱动架构设计思路、Kafka Topic 分区策略、消息幂等性以及消费位点管理（Offset）等高级用法。

5. **分布式追踪（Distributed Tracing）**

   * **Spring Cloud Sleuth + Zipkin / Jaeger**：自动在微服务调用链中插入 TraceId、SpanId，便于调用链追踪与性能分析。
   * **OpenTelemetry**：集成更现代的 OTEL SDK，并将追踪数据导出到 Prometheus 与 Grafana。
   * **项目亮点**：展示对分布式系统难以调试的调用链问题的解决方案，能在多服务跨进程场景下快速定位性能瓶颈或异常调用。

6. **全面监控 & 可观测性（Observability）**

   * **Actuator + Micrometer + Prometheus**：在项目中暴露 `metrics`、`health`、`httptrace`、`loggers` 等端点，并将指标推送给 Prometheus。
   * **Grafana 可视化**：通过 Grafana 仪表盘展示关键指标（如请求延迟、TPS、JVM 内存、GC 情况、Kafka 消费速率等），配置告警规则（Alert）。
   * **项目亮点**：展示你对生产环境监控体系的搭建能力，以及对指标采集、可视化、告警策略的整体把控。

7. **安全加固（Security）**

   * **OAuth2 / JWT 鉴权**：使用 Spring Security OAuth2 或 Spring Security Resource Server，集成 Keycloak/Okta/自建 Authorization Server，实现令牌颁发与资源访问控制。
   * **权限细粒度控制**：在方法级别使用 `@PreAuthorize("hasAuthority('ROLE_ADMIN')")` 等注解控制不同角色的访问范围。
   * **接口审计与敏感数据脱敏**：结合 AOP（切面）记录每次敏感接口访问日志，脱敏返回用户隐私字段（如手机号、身份证号）。
   * **项目亮点**：展示你对企业级安全体系（认证、授权、审计）有深入理解，能够处理复杂的 RBAC、OAuth2 Flows、Token 刷新等问题。

8. **GraphQL / API 组合查询**

   * **Spring Boot + GraphQL**：使用 `spring-boot-starter-graphql`（或 `graphql-java-tools`）实现 GraphQL 服务端，支持单个查询请求自定义字段组合。
   * **Schema 设计**：定义 Type、Query、Mutation，注入数据源通过 DataFetcher 获取数据。
   * **项目亮点**：相比传统 REST 提供多端灵活查询能力，展示你有能力设计可演进的 API 层并解决过渡依赖问题。

9. **高级缓存策略**

   * **Redis / Caffeine 二级缓存**：不仅对常见热点数据做缓存，还实现本地 L1 + 分布式 L2 组合，提高读取命中率并减少 Redis 压力。
   * **Cache Aside 模式 + 缓存穿透防护**：对特定接口添加布隆过滤器（Bloom Filter）判断无效请求，配合 `@Cacheable`、`@CacheEvict` 完成缓存读写更新。
   * **项目亮点**：具体展示 Cache Key 设计、TTL 策略、热点缓存降级方案、Cache Stampede（击穿）防护。

10. **高可用多数据源 & 灾备方案**

    * **读写分离**：配置主从数据库（Primary-Replica），通过 AbstractRoutingDataSource 或 MyBatis-Spring 分表分库插件将写请求路由到主库，读请求路由到从库。
    * **热备 & 冷备切换**：演示主库故障后自动切换或代码层面快速切换备用连接。
    * **项目亮点**：展示对高可用架构设计、事务一致性（XA 或 TCC 事务处理）、读写一致性（读偏移 & 延迟）的深度理解。

11. **CI/CD 流水线与自动化**

    * **Jenkins / GitHub Actions / GitLab CI**：配置流水线脚本，实现代码检出、单元测试、静态代码检查（SonarQube）、Docker 镜像构建、推送 Registry、K8s 部署与回滚。
    * **自动化回归测试**：在每次合并到主分支时自动触发全量集成测试与端到端测试。
    * **项目亮点**：展示你对 DevOps 流水线全流程的理解，能将“持续集成、持续交付”灌入日常开发节奏，从代码提交到生产部署实现零信任自动化。

12. **复杂场景下的数据处理**

    * **大数据分片/分页与流式处理**：使用 MyBatis、Spring Data JPA 或 MyBatis Plus 做大表分批查询，并结合 Streaming API（`Stream<T>`）或 Reactive Flux 快速处理海量数据。
    * **文件/Excel 异步导入导出**：使用异步消息队列消费导入任务并回写结果（如 Apache POI + EasyExcel），在导出侧用分页流写，支持断点续传与导出进度通知。
    * **项目亮点**：展示你对大数据量场景下性能优化（内存控制、批量操作）、断点续传与用户体验（进度反馈）的实战经验。

13. **数据库 & 持久层进阶**

    * **多种数据库支持**：MySQL + PostgreSQL + MongoDB 或 Elasticsearch 联合使用，实现不同场景下的聚合查询、全文检索与时序数据管理。
    * **MyBatis 动态 SQL & 性能优化**：使用 MyBatis-Plus 或手写优雅的动态 SQL，结合 SQL 性能分析（`EXPLAIN`）、分库分表策略、全量分片索引与联合索引优化。
    * **分布式事务**：集成 Seata、RocketMQ 转账场景，演示 TCC 或 AT 模式保障多库跨服务事务一致性。
    * **项目亮点**：展示你对各类数据库选型的深思熟虑，以及分布式数据一致性与性能调优的能力。

14. **前后端协同与 API 文档**

    * **OpenAPI / Swagger**：用 `springdoc-openapi` 或 Springfox 自动生成规范的 Swagger 文档，并结合 ReDoc 页面供前端或测试人员查看。
    * **Mock Server**：在文档生成基础上启用 Mock 功能，让前端或 QA 可模拟后端接口。
    * **项目亮点**：展示你对接口契约的重要性重视，能为团队降低前后端协作成本，避免“接口联调”阶段的高昂沟通成本。

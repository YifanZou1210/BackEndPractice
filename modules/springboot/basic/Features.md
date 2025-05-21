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

##### follow up

> **Follow Up Question**

* 如何在启动流程中捕获并处理特定事件？
  实现并注册 `ApplicationListener<EnvironmentPreparedEvent>` 等监听器。
* `@ConfigurationProperties` 校验失败如何反馈？
  添加 `@Validated` 并在字段上使用 JSR-303 注解，会抛 `BindException`。
* 如何为 Actuator 端点添加自定义安全策略？
  在 `WebSecurityConfigurerAdapter` 或 `SecurityFilterChain` 中配置端点权限。
* 在单例 Bean 中按需获取原型 Bean 应如何实现？
  注入 `ObjectProvider<T>` 或使用 `@Lookup` 方法注入新实例。
* 如何查看自动配置某个功能具体加载了哪些 Bean？
  启用调试模式并访问 `/actuator/conditions` 或查看 `ConditionEvaluationReport`。

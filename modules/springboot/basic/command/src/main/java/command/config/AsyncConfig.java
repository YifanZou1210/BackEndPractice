// 配置异步执行configuration包
package command;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * 异步执行器配置类
 * - @EnableAsync：启用 Spring 的异步方法调用
 * - 定义名为 "initExecutor" 的线程池，用于初始化任务
 */
@Configuration
@EnableAsync //通过该注解启动异步与自定义的ThreadPoolTaskExecutor任务执行器实现异步方法执行，避免阻塞主线程
public class AsyncConfig {

    @Bean(name = "initExecutor")//这里设定executor的名字为initExecutor并在asyncInitRunner中根据name注入
    public Executor initExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 核心线程数：处理并发任务的最低线程数
        executor.setCorePoolSize(2);
        // 最大线程数：当队列满时可创建的最大线程
        executor.setMaxPoolSize(5);
        // 队列容量：在核心线程数满时，任务等待队列大小
        executor.setQueueCapacity(50);
        // 线程名前缀，方便日志追踪
        executor.setThreadNamePrefix("init-exec-");
        executor.initialize();
        return executor;
    }
}

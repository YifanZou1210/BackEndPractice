package command;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 应用启动入口
 * - @SpringBootApplication：组合注解，包含 @Configuration、@EnableAutoConfiguration、@ComponentScan
 */
@SpringBootApplication
public class CommandApplication {

    public static void main(String[] args) {
        // 触发 SpringApplication.run()，依次刷新容器并执行 Runner
        SpringApplication.run(CommandApplication.class, args);
    }
}
/**
 * 启动后的结果中
 * 日志显示了两个初始化任务的启动与完成情况，分别由**异步初始化 Runner（AsyncInitRunner）和同步初始化 Runner（SyncInitRunner）**打印：
 * [AsyncInit] 在 init-exec-1 线程打印，表明该任务使用了异步（asynchronous）线程池执行；
 * [SyncInit] 在 main 主线程打印，表明该任务在应用主线程（main thread）中执行；
 * 两者的开始时间几乎相同，完成时间也都记录了约 2005 ms，说明二者同时启动并并行执行。
 */

//异步初始化逻辑

package command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.ApplicationArguments;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.Instant;

/**
 * 异步初始化 Runner
 * - @Async("initExecutor")：使用指定线程池异步执行
 * - 应用可在此任务执行期间进入可用状态
 */
@Component
public class AsyncInitRunner implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(AsyncInitRunner.class);
    //在run上添加以下注解将初始化逻辑提交到线程池异步执行，不阻塞主线程
    @Async("initExecutor")
    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 打印开始时间
        log.info("[AsyncInit] 开始初始化 at {}", Instant.now());
        long start = System.nanoTime();

        // 模拟同样的耗时操作
        Thread.sleep(2000); // 2秒

        long durationMs = (System.nanoTime() - start) / 1_000_000;
        // 打印耗时
        log.info("[AsyncInit] 完成初始化，耗时 {} ms", durationMs);
    }
}

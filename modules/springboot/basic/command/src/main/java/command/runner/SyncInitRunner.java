//同步初始化逻辑
// 文件路径：src/main/java/com/example/runner/SyncInitRunner.java

package command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;

/**
 * 同步初始化 Runner
 * - run 方法在容器刷新后执行，主线程阻塞等待完成
 * - 记录开始与结束时间，用于对比启动性能
 */
@Component
// 实现command line runner接口，在容器刷新后同步执行初始化逻辑，主线程阻塞到任务完成
public class SyncInitRunner implements CommandLineRunner {
    //构造日志对象
    private static final Logger log = LoggerFactory.getLogger(SyncInitRunner.class);

    @Override
    public void run(String... args) throws Exception {
        // 打印开始时间
        log.info("[SyncInit] 开始初始化 at {}", Instant.now());
        long start = System.nanoTime();

        // 模拟耗时操作，例如加载启动数据或校验外部服务
        Thread.sleep(2000); // 2秒

        long durationMs = (System.nanoTime() - start) / 1_000_000;
        // 打印耗时
        log.info("[SyncInit] 完成初始化，耗时 {} ms", durationMs);
    }
}

package startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.*;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.Instant;

/**
 * 统一监听 Spring Boot 启动各阶段事件并记录时间戳
 */
@Component  // 注册为 Spring Bean，自动被 SpringApplication.run() 扫描
public class StartupEventListener implements ApplicationListener<ApplicationEvent> {

    // 使用 SLF4J 的 Logger 而非 java.util.logging.Logger
    private static final Logger log = LoggerFactory.getLogger(StartupEventListener.class);

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        String timestamp = Instant.now().toString();  // 获取当前 UTC 时间戳

        // 根据事件类型记录对应日志，统一使用 {} 占位符传入 timestamp
        if (event instanceof ApplicationStartingEvent) {
            log.info("[StartupEvent] ApplicationStartingEvent at {}", timestamp);
        } else if (event instanceof ApplicationEnvironmentPreparedEvent) {
            log.info("[StartupEvent] ApplicationEnvironmentPreparedEvent at {}", timestamp);
        } else if (event instanceof ApplicationContextInitializedEvent) {
            log.info("[StartupEvent] ApplicationContextInitializedEvent at {}", timestamp);
        } else if (event instanceof ApplicationPreparedEvent) {
            log.info("[StartupEvent] ApplicationPreparedEvent at {}", timestamp);
        } else if (event instanceof ApplicationStartedEvent) {
            log.info("[StartupEvent] ApplicationStartedEvent at {}", timestamp);
        } else if (event instanceof ApplicationReadyEvent) {
            log.info("[StartupEvent] ApplicationReadyEvent at {}", timestamp);
        } else if (event instanceof ApplicationFailedEvent) {
            log.error("[StartupEvent] ApplicationFailedEvent at {}", timestamp);
        }
    }
}


package com.backend.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

//! 本file用于实现自定义@Endpoint: /actuator/custom
@Component
@Endpoint(id = "custom")//表示暴露/actuator/custom
public class CustomEndpoint {
    // 使用 SLF4J Logger 记录审计信息
    // 1. 注意：这里使用的是 org.slf4j.Logger，而不是 java.util.logging.Logger
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(CustomEndpoint.class);
    @ReadOperation 
    // 该anno作用是1. 告诉spring boot此方法属于自定义的Actuator端点 2.会映射为一个HTTP GET请求
    // 标注了 @ReadOperation 方法后，Spring Boot 会自动注册该方法对应的 URL，并统一由 Actuator 的 Dispatcher（管理端点调度器）来处理，而不是由 Spring MVC 的 DispatcherServlet 处理。
    public Map<String, Object> customHealth() {
        // 正确获取当前已认证用户名，应通过 SecurityContextHolder
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        // 记录访问审计日志，需要给占位符传入具体参数，如用户名和时间
        logger.info("进入 custom endpoint");

        Map<String, Object> data = new HashMap<>();
        // 填充业务相关字段，例如：服务版本、运行实例数等,假设业务需要返回这些项
        data.put("service", "user-service");
        data.put("version", "1.2.3");
        data.put("status", "OK");
        data.put("timestamp", Instant.now().toString());
        // 脱敏示例：假设 sensitiveValue 需要脱敏，只显示后两位
        String rawValue = "1234567890";
        String masked = "***" + rawValue.substring(rawValue.length() - 2);
        data.put("totalRevenue", masked);
        // 假设从数据库或者缓存中读取某些指标
        data.put("activeOrders",fetchActiveOrderCount());
        data.put("pendingTasks", fetchPendingTaskCount());
        return data;
    }
    private int fetchActiveOrderCount(){
        // TODO [2025-05-31 16:06]: 在实际项目中可以调用Service/Repository查询数据库
        return 128;
    }
    private int fetchPendingTaskCount(){
        // TODO [2025-05-31 16:14]: 查询任务队列/缓存/数据库
        return 24;
    }
}

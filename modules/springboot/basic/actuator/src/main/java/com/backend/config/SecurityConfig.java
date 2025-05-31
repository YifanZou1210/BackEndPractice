package com.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// 允许使用 @PreAuthorize、@PostAuthorize 等注解，已废弃，换成EnableMethodSecurity 注解
// import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
// 用于配置 HTTP 安全策略
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// 替代 WebSecurityConfigurerAdapter 的配置方式
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.UserDetailsService;

/** class Security Config 目的
 * 该SecurityConfig类用于配置Spring Security 的配, 在 Spring Boot 6中采用新的SecurityFilterChain方式替代弃用的WebSecurity ConfigurerAdapter,完成以下目标：
 * 1. 仅允许拥有ROLE_MONITOR角色的用户通过HTTP Basic认证访问/actuator/**
 * 2. 关闭 CSRF（Cross‐Site Request Forgery）保护，以免对 Actuator 的 POST/DELETE 等操作产生 403 错误；
 * 3. 其余业务接口无需认证即可访问；
 * 4. 以内存用户（InMemoryUserDetailsManager）方式演示“监控用户” monitorUser 的创建；
 * 5. 使用 NoOpPasswordEncoder（明文密码）便于开发调试环境。
 */
@Configuration // 标识为Spring配置类,启动 时会自动扫描和实例化@Bean
@EnableMethodSecurity(prePostEnabled = true)// 启动方法级别安全控制，在业务方法上做权限校验
public class SecurityConfig {
    /**
     * Spring Security 6.x 中，HttpSecurity 的 DSL 发生了变化：
     * - antMatchers 已移除，使用 requestMatchers。
     * - httpBasic() 推荐用 httpBasic(Customizer.withDefaults())。
     * - csrf() 直接链式调用 csrf(csrf -> csrf.disable())。
     */
    @Bean
    // 配置安全过滤器链
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. 配置 URL 授权：只有 ROLE_MONITOR 可访问 /actuator/**
                .authorizeHttpRequests(authorize -> authorize
                        // 替换了 antMatchers，使用 requestMatchers("/actuator/**")
                        .requestMatchers("/actuator/**").hasRole("MONITOR")
                        // 其它请求无需认证
                        .anyRequest().permitAll()
                )
                // 2. 启用 HTTP Basic 认证，使用带 Customizer 的版本避免废弃警告
                // Customizer.withDefaults() 表示使用默认配置，不需要额外自定义
                .httpBasic(Customizer.withDefaults())
                // 3. 关闭 CSRF 保护，否则对 POST/DELETE 端点可能返回 403
                .csrf(csrf -> csrf.disable());
        // 将配置好的 HttpSecurity 构建成一个 SecurityFilterChain bean
        return http.build();
    }
    /**
     * 内存用户管理：创建用户名 monitorUser，密码 password，角色 ROLE_MONITOR
     */
    @Bean
    public UserDetailsService userDetailsService() {
        //InMemoryUserDetailsManager 是一个内存用户存储（不需要数据库），适合开发或测试
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(
                User.withUsername("monitorUser")
                        .password("password")
                        .roles("MONITOR") // Spring Security 自动前缀 ROLE_
                        .build()
        );
        return manager;
    }

    /**
     * 密码编码器：此处为示例使用 NoOp（明文），生产环境务必用 BCrypt 等安全哈希算法
     */
    @Bean
    @SuppressWarnings("deprecation")
    public PasswordEncoder passwordEncoder() {
        // NoOpPasswordEncoder 表示使用明文密码（不做加密），仅能用于开发或测试
        //生产环境必须使用 BCryptPasswordEncoder、Argon2PasswordEncoder 等安全哈希算法
        return NoOpPasswordEncoder.getInstance();
    }}




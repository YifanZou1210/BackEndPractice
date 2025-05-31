package startup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

    /**
     * 应用入口类
     * 通过add listener注册启动时间监听器以模拟各阶段日志
     */
    @SpringBootApplication
    public class StartupListenersApplication {
        public static void main(String[] args){
            // SpringApplication的构造函数接收一个或者多个source class,用于标识应用的猪配置入口。该类通常被标注为@SpringBootApplication。
            // 1. SpringApplication会基于此类加载主配置(将该类以及其依赖的自动配置类注册到容器）
            // 2. 确定组件扫描基础包路径，默认扫描该类所在包以及子包
            // 3. 作为错误报告与上下文环境初始化的参考点
            SpringApplication app = new SpringApplication(StartupListenersApplication.class);
            app.addListeners(new StartupEventListener());
            app.run(args);
            
        }
    }

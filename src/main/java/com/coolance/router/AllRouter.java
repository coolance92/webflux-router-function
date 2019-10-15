package com.coolance.router;

import com.coolance.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * @ClassName AllRouter
 * @Description 路由配置类
 * @Author Coolance
 * @Version
 * @Date 2019/10/15 10:10
 */
@Configuration
public class AllRouter {

    @Bean
    public RouterFunction<ServerResponse> userRouter(UserHandler userHandler) {
        return nest(
                // 相当于类上面的 @RequestMapping("/user")
                path("/user"),
                // 相当于方法上面的 @RequestMapping
                // 得到所有用户
                route(GET("/"), userHandler::getAll)
                        // 创建用户
                        .andRoute(POST("/").and(accept(APPLICATION_JSON_UTF8)), userHandler::create)
                        // 删除用户
                        .andRoute(DELETE("/{id}"), userHandler::delete)
                        .andRoute(PUT("/{id}"), userHandler::update));
    }
}

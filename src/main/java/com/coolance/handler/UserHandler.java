package com.coolance.handler;

import com.coolance.domain.User;
import com.coolance.repository.UserRepository;
import com.coolance.util.CheckUtil;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.web.reactive.function.server.ServerResponse.notFound;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * @ClassName UserHandler
 * @Description HandlerFunction
 * @Author Coolance
 * @Version
 * @Date 2019/10/15 9:36
 */
@Component
public class UserHandler {
    private final UserRepository userRepository;

    public UserHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 得到所有用户
     *
     * @param serverRequest
     * @return
     */
    public Mono<ServerResponse> getAll(ServerRequest serverRequest) {
        return ok().contentType(APPLICATION_JSON_UTF8)
                .body(userRepository.findAll(), User.class);
    }

    /**
     * 新建用户
     *
     * @param serverRequest
     * @return
     */
    public Mono<ServerResponse> create(ServerRequest serverRequest) {
        Mono<User> userMono = serverRequest.bodyToMono(User.class);

        return userMono.flatMap(user -> {
            CheckUtil.checkName(user.getName());
            return ok().contentType(APPLICATION_JSON_UTF8)
                    .body(userRepository.save(user), User.class);
        });
    }

    /**
     * 根据id删除用户
     *
     * @param serverRequest
     * @return
     */
    public Mono<ServerResponse> delete(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");

        return userRepository.findById(id)
                .flatMap(user -> userRepository.delete(user)
                        .then(ok().build()))
                .switchIfEmpty(notFound().build());
    }

    /**
     * 更新用户
     *
     * @param serverRequest
     * @return
     */
    public Mono<ServerResponse> update(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        Mono<User> userMono = serverRequest.bodyToMono(User.class);

        return userMono.flatMap(user -> {
                    CheckUtil.checkName(user.getName());
                    return userRepository.findById(id)
                            .flatMap(u -> {
                                u.setName(user.getName());
                                u.setAge(user.getAge());
                                return ok().contentType(APPLICATION_JSON_UTF8)
                                        .body(userRepository.save(u), User.class);
                            }).switchIfEmpty(notFound().build());
                }
        );
    }
}

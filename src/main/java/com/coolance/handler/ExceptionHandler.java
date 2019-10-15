package com.coolance.handler;

import com.coolance.exception.CheckException;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

/**
 * @ClassName ExceptionHandler
 * @Description 捕获异常处理类
 * @Author Coolance
 * @Version
 * @Date 2019/10/15 11:45
 */
@Component
@Order(-2)
public class ExceptionHandler implements WebExceptionHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, Throwable throwable) {
        ServerHttpResponse response = serverWebExchange.getResponse();
        response.setStatusCode(HttpStatus.BAD_REQUEST);
        response.getHeaders().setContentType(MediaType.TEXT_PLAIN);
        String errorMsg = toStr(throwable);
        DataBuffer db = response.bufferFactory().wrap(errorMsg.getBytes());
        return response.writeWith(Mono.just(db));
    }

    private String toStr(Throwable throwable) {
        if(throwable instanceof CheckException) {
            CheckException e = (CheckException)throwable;
            return e.getFieldName() + " invalid value " + e.getFieldValue();
        } else {
            throwable.printStackTrace();
            return throwable.toString();
        }
    }
}

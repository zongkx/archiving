package com.zong.filter;

import com.zong.util.JwtUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 全局过滤器,用户校验
 */
@Component

public class AuthFilter implements GlobalFilter, Ordered {

    public static final String AUTH_TOKEN = "Auth";
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取用户令牌
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        //
        String token = request.getHeaders().getFirst(AUTH_TOKEN);
        boolean hasToken = true;
        if(StringUtils.isEmpty(token)){
            token = request.getQueryParams().getFirst(AUTH_TOKEN);
            hasToken = false;
        }
        if(StringUtils.isEmpty(token)){
            HttpCookie cookie = request.getCookies().getFirst(AUTH_TOKEN);
            if (cookie != null) {
                token = cookie.getValue();
                hasToken = false;
            }
        }
        if(StringUtils.isEmpty(token)){
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        try {
            //有效
            JwtUtil.parseJWT(token);
        } catch (Exception e) {
            //无效拦截
            e.printStackTrace();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        //放行
        //将令牌放入header
        if(!hasToken){
            request.mutate().header(AUTH_TOKEN,token);
        }
        return chain.filter(exchange);
    }

    @Override//排序,越小越先执行
    public int getOrder() {
        return 0;
    }
}

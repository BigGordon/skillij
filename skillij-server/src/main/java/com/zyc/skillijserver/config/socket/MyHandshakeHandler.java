package com.zyc.skillijserver.config.socket;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;

/**
 * 设置websocket用户的principle
 * Created on 2018/7/17.
 * Author: Gordon
 * Email: biggordon@163.com
 */
public class MyHandshakeHandler extends DefaultHandshakeHandler {
    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        String user = (String) httpServletRequest.getSession().getAttribute("user");
        return new StompPrincipal(user);
    }
}

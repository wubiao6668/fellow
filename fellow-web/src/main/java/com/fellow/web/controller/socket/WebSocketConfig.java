package com.fellow.web.controller.socket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * Created by wubiao on 11/10/2017.
 */
//@Configuration
//@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    /**
     * 将"/hello"路径注册为STOMP端点，这个路径与发送和接收消息的目的路径有所不同，这是一个端点，客户端在订阅或发布消息到目的地址前，要连接该端点，
     * 即用户发送请求url="/applicationName/hello"与STOMP server进行连接。之后再转发到订阅url；
     * PS：端点的作用——客户端在订阅或发布消息到目的地址前，要连接该端点。
     *
     * @param stompEndpointRegistry
     */
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        //在网页上可以通过"/applicationName/hello"来和服务器的WebSocket连接
        stompEndpointRegistry.addEndpoint("/gs-guide-websocket").withSockJS();
    }

    /**
     * 配置了一个简单的消息代理，如果不重载，默认情况下回自动配置一个简单的内存消息代理，用来处理以"/topic"为前缀的消息。这里重载configureMessageBroker()方法，
     * 消息代理将会处理前缀为"/topic"和"/queue"的消息。
     *
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //应用程序以/app为前缀，代理目的地以/topic、/user为前缀
//        registry.enableSimpleBroker("/topic", "/user");
        registry.enableStompBrokerRelay("/topic", "/user")
                .setRelayHost("127.0.0.1") //activeMq服务器地址
                .setRelayPort(61613)//activemq 服务器服务端口
                .setClientLogin("guest")    //登陆账户
                .setClientPasscode("guest").
                 setSystemHeartbeatReceiveInterval(2000) // 设置心跳信息接收时间间隔
                .setSystemHeartbeatSendInterval(2000); // 设置心跳信息发送时间间隔();
        registry.setApplicationDestinationPrefixes("/app");
        registry.setUserDestinationPrefix("/user");
    }

}
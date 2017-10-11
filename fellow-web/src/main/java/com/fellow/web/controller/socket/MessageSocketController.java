package com.fellow.web.controller.socket;

import com.fellow.web.base.WebAbstract;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wubiao on 11/10/2017.
 */


@Controller
@RequestMapping(MessageSocketController.VIEW_PATH)
public class MessageSocketController{
    public static final String VIEW_PATH = "/socket/message";

    @RequestMapping(value = "index")
    public String index(){
        return "/socket/message/index";
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + message.getName() + "!");
    }
}

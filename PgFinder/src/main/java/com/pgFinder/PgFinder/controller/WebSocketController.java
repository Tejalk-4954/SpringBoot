package com.pgFinder.PgFinder.controller;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/notify")
    @SendTo("/topic/pg-updates")
    public String notifyNewPg(String message) {
        return message;
    }
}

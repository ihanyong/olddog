package com.monkey.olddog.nio.chat.dto;

import lombok.Data;

import java.util.Date;

/**
 * Created by HanYong on 2018/7/17.
 */
@Data
public class ChatMessage {
    long fromId;
    long toId;
    Date sendTime;
    String message;
}

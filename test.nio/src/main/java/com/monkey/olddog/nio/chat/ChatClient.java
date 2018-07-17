package com.monkey.olddog.nio.chat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by HanYong on 2018/7/17.
 *

 *
 *
 *
 */
public class ChatClient {
    private Logger logger = LoggerFactory.getLogger(">>");

    public static void main(String[] args) throws IOException {
        new ChatClient().start();

    }

    private SocketChannel sc ;
    private boolean isRunning = false;


    private void start() throws IOException {
        initClient();
        new Thread(() -> {
            while (isRunning && sc.isConnected()) {
                ByteBuffer head = ByteBuffer.allocate(26);
                try {
                    read(head, sc);
                    short type = head.getShort();
                    long from = head.getLong();
                    long to = head.getLong();
                    long bodyLen = head.getLong();

                    if (2 == type) {
                        ByteBuffer bodyBuff = ByteBuffer.allocate((int)bodyLen);
                        read(bodyBuff, sc);
                        logger.info("{} --> {} : {}", from, to, StandardCharsets.UTF_8.decode(bodyBuff).toString());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        monitorInput();

    }

//    * type::[userId]::[]message
//    *      type: login; logout; send

    private static Map<String, Short> typeMap = new HashMap<>();
    static {
        typeMap.put("login", (short) 0);
        typeMap.put("logout", (short) 1);
        typeMap.put("send", (short) 2);
        typeMap.put("exit", (short) 2);
    }

    private long currentUser = -1;

    private void monitorInput() {
        // TODO
        Scanner scanner = new Scanner(System.in);
        while (isRunning) {
            String input = scanner.nextLine();

            String[] args =  input.split("::");
            if (input == null || input.length() == 0) {
                continue;
            }
            if (args == null || args.length < 1) {
                logger.info("the command {} is unknown.", input);
                continue;
            }
            String type = args[0];
            long user = args.length > 1 ? Long.valueOf(args[1]) : -1;
            String msg = args.length > 2 ? args[2] : "";

            if (!typeMap.containsKey(type)) {
                logger.info("the type {} is unknown.", type);
                continue;
            }

            if ("login".equals(type)) {
                currentUser = user;
            }
            writeMessage(sc,typeMap.get(type), currentUser, user, ByteBuffer.wrap(msg.getBytes(StandardCharsets.UTF_8)));

            if ("logout".equals(type)) {
                this.currentUser = -1;
            } else if ("exit".equals(type)) {
                isRunning = false;
            }
        }
    }

    private void initClient() throws IOException {
        sc = SocketChannel.open(new InetSocketAddress(8888));
        isRunning = true;
    }

    private static void read(ByteBuffer buff, SocketChannel ch) throws IOException {
        while (buff.hasRemaining()) {
            int r = ch.read(buff);
            if (r == -1) {
                throw new IOException("end of stream when reading");
            }
        }
    }

    private void writeMessage(SocketChannel ch,short type, long fromKey, long toKey, ByteBuffer body) {
        ByteBuffer head = genHead(type, fromKey, toKey, body.remaining());

        try {
            ch.write(new ByteBuffer[]{head, body});
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private ByteBuffer genHead(short type, long from, long to, long len) {
        ByteBuffer bb = ByteBuffer.allocate(26);
        bb.putShort(type).putLong(from).putLong(to).putLong(len);
        return bb;
    }

}

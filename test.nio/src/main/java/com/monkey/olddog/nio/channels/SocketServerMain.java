package com.monkey.olddog.nio.channels;/**
 * Created by hanyong on 2017/7/19.
 */

import java.io.IOException;
import java.nio.channels.ServerSocketChannel;

/**
 * @author hanyong
 * @Date 2017/7/19
 */
public class SocketServerMain {

    public static void main(String[] args) {
        try (ServerSocketChannel channel = ServerSocketChannel.open()){


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

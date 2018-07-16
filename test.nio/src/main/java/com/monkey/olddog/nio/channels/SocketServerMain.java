package com.monkey.olddog.nio.channels;/**
 * Created by hanyong on 2017/7/19.
 */

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author hanyong
 * @Date 2017/7/19
 */
public class SocketServerMain {

    public static void main(String[] args) {
        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()){
//            channel.configureBlocking(false);

            serverSocketChannel.bind(new InetSocketAddress(8800));

            while (true) {

                SocketChannel channel = serverSocketChannel.accept();

//                channel.
                // todo




            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

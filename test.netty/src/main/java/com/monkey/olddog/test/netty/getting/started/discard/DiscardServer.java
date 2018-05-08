package com.monkey.olddog.test.netty.getting.started.discard;/**
 * Created by hanyong on 2017/7/20.
 */

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hanyong
 * @Date 2017/7/20
 */
public class DiscardServer {
    private static Logger logger = LoggerFactory.getLogger(DiscardServer.class);

    private int port;

    public DiscardServer(int port) {
        this.port = port;
    }

    public void run() {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();

        try {

            ServerBootstrap b = new ServerBootstrap();

            b.group(boss, work)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new DiscardServerHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            logger.info("started to wait incoming connections on port {}", port);
            ChannelFuture future = b.bind(port).sync();

            future.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            logger.error("", e);

        } finally {
            work.shutdownGracefully();
            boss.shutdownGracefully();

        }
    }

    public static void main(String[] args) {
        int port = 8080;
        if (null != args && args.length > 0) {
            port = Integer.parseInt(args[0]);
        }

        new DiscardServer(port).run();
    }
}

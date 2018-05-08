package com.monkey.olddog.test.netty.getting.started.discard;/**
 * Created by hanyong on 2017/7/20.
 */

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hanyong
 * @Date 2017/7/20
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter{

    static Logger logger = LoggerFactory.getLogger(DiscardServerHandler.class);

    StringBuffer sb = new StringBuffer();

    private void print(char c) {
        if (c == '\n') {
            logger.info("{}", sb.toString());
            sb = new StringBuffer();
        } else {
            sb.append(c);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        super.channelRead(ctx, msg);
        ByteBuf in = (ByteBuf) msg;
        try {
            while (in.isReadable()) {
//                logger.info("{}", (char) in.readByte());
//                System.out.print((char) in.readByte());
                print((char) in.readByte());

            }
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        super.exceptionCaught(ctx, cause);
        logger.error("Exception occur: {}", cause.getMessage(),  cause);
        ctx.close();
    }

}

package com.monkey.olddog.nio;/**
 * Created by hanyong on 2017/7/19.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;

/**
 * @author hanyong
 * @Date 2017/7/19
 */
public class BufferTest {

    static Logger logger = LoggerFactory.getLogger(BufferTest.class);

    public static void main(String[] args) {
        testByteBuffer();

    }


    private static void testByteBuffer() {

        ByteBuffer b = ByteBuffer.allocate(20);


        logger.info("allocate {}", b.toString());

        b.putChar('0');
        logger.info("after put char of 0, {}", b.toString());
        b.putChar('1');
        b.putChar('2');
        logger.info("after put char of 1,2, {}", b.toString());

        b.flip();
        logger.info("after flip, {}", b.toString());

        while (b.hasRemaining()) {
            char c = b.getChar();
            logger.info("after read {}, {}", c, b.toString());
        }

        logger.info("after read all chars, {}", b.toString());

        b.flip();
        logger.info("flip after read, {}", b.toString());


        while (b.hasRemaining()) {
            char c = b.getChar();
            logger.info("after read {}, {}", c, b.toString());
        }

        logger.info("after read all chars, {}", b.toString());

        b.clear();
        logger.info("after clear, {}", b.toString());

    }
}

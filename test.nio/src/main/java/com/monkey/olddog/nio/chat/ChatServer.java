package com.monkey.olddog.nio.chat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by HanYong on 2018/7/17.
 */
public class ChatServer {
    public static Logger logger = LoggerFactory.getLogger("-->");

    public static void main(String[] args) {
        new ChatServer().start();
    }

    public  void start() {
        initServer();
        doService();
    }

    public void close() {
        if (isRunning && null != serverChannel && serverChannel.isOpen()) {
            try {
                serverChannel.close();
                serviceThread.close();
                isRunning = false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isRunning = false;
    private ServerSocketChannel serverChannel;
    private ServiceThread serviceThread;

    private void initServer() {
        if (isRunning) {
            return;
        }
        try {
            int port = 8888;
            serverChannel = ServerSocketChannel.open();
//            serverChannel.configureBlocking(false);
            serverChannel.bind(new InetSocketAddress(port));
            serviceThread = new ServiceThread();
            serviceThread.init();
            serviceThread.start();
            isRunning = true;

            logger.info("started on port {}", port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // 先用两个线程来处理
    // 主线程用来监听新链接
    // 另一个线程用selector的方式来处理交互

    private void doService() {

        while (isRunning) {
            try {
                logger.info("try to accept new channel");
                SocketChannel channel = this.serverChannel.accept();
                if (null != channel) {
                    accept(channel);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void accept(SocketChannel ch) {
        logger.info("come in a new channel: {}", ch);

        this.serviceThread.addChannel(ch);
    }

    public static class ServiceThread extends Thread {
        private Map<Long, SocketChannel> regiesteredChannel = null;
        private Selector selector;
        private boolean closed = false;

        public void init() {
            logger.info("init the service thread");
            try {
                selector = Selector.open();

            } catch (IOException e) {
                e.printStackTrace();
            }
            regiesteredChannel = new HashMap<>();
        }

        private void closeChannel(long key) {
            logger.info("close the chanale of {}", key);

            if (regiesteredChannel.containsKey(key)) {
                try {
                    regiesteredChannel.get(key).close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public void addChannel(SocketChannel channel) {
            logger.info("add channel {}", channel);

            try {
                channel.configureBlocking(false);
                channel.register(selector, SelectionKey.OP_READ);
            } catch (ClosedChannelException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void close() {
            logger.info("close the service thread");

            closed = true;
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            regiesteredChannel.entrySet().stream().forEach(entry -> {
                try {
                    entry.getValue().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            regiesteredChannel = null;
        }

        @Override
        public void run() {
            logger.info("the service thread start, the closed is {}", closed);

            while (!closed) {
                try {
//                    logger.info("try to select the ready channels");

                    selector.select(10000);

                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    logger.info("selected {} ready keys", selectionKeys.size());

                    Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();
                    while (selectionKeyIterator.hasNext()) {
                        SelectionKey selectionKey = selectionKeyIterator.next();
                        selectionKeyIterator.remove();
                        if (selectionKey.isReadable()) {
                            SocketChannel ch = (SocketChannel) selectionKey.channel();

                            ByteBuffer headBuff = ByteBuffer.allocate(26);

                            read(headBuff, ch);
                            headBuff.flip();
                            short type = headBuff.getShort();
                            long fromKey = headBuff.getLong();
                            long toKey = headBuff.getLong();
                            long bodyLen = headBuff.getLong();

                            switch (type) {
                                case 0: // login
                                    doLogin(fromKey, ch);
                                    break;
                                case 1: // logout
                                    doLogout(fromKey,selectionKey, ch);
                                    break;
                                case 2: // message
                                    doMessage(fromKey, toKey, bodyLen, ch);
                                    break;
                                default:
                                    logger.info("read wrong head type [{}] from {}",type, ch);
                                    break;
                            }
                        }
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void doMessage(long fromKey, long toKey, long bodyLen, SocketChannel fromCh) {
            // get toCh
            SocketChannel toCh = regiesteredChannel.get(toKey);

            // 不考虑离线消息, 只是回复客户端目标用户离线;

            try {
                // read message   // 先不考虑太长的消息
                ByteBuffer bodyBuf = ByteBuffer.allocate((int) bodyLen);
                read(bodyBuf, fromCh);
                bodyBuf.flip();


                detectMessage(bodyBuf, fromKey, toKey);

                if (null == toCh) {
                    writeMessage(fromCh, toKey, fromKey, ByteBuffer.wrap("I am off-line.".getBytes(StandardCharsets.UTF_8)));
                } else {
                    bodyBuf.rewind();
                    writeMessage( toCh, fromKey, toKey, bodyBuf);
                }


                // write the message to toCh
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        private void detectMessage(ByteBuffer bodyBuf, long fromKey, long toKey) {
            String body = StandardCharsets.UTF_8.decode(bodyBuf).toString();
            logger.info("user {} send to user {} : {}", fromKey, toKey, body);
        }

        private void read(ByteBuffer buff, SocketChannel ch) throws IOException {
            while (buff.hasRemaining()) {
                int r = ch.read(buff);
                if (r == -1) {
                    throw new IOException("end of stream when reading");
                }
            }
        }

        private void writeMessage(SocketChannel ch, long fromKey, long toKey, ByteBuffer body) throws IOException {
            short type = 2; // message
            ByteBuffer head = genHead(type, fromKey, toKey, body.remaining());

            ch.write(new ByteBuffer[]{head, body});

        }

        private ByteBuffer genHead(short type, long from, long to, long len) {
            ByteBuffer bb = ByteBuffer.allocate(26);
            bb.putShort(type).putLong(from).putLong(to).putLong(len);
            bb.flip();
            return bb;
        }

        private void doLogin(long fromKey, SocketChannel ch) {
            addChannel(ch);
            regiesteredChannel.put(fromKey, ch);
            logger.info("user {} login", fromKey);
        }
        private void doLogout(long fromKey,SelectionKey selectionKey, SocketChannel ch) {
            selectionKey.cancel();
            closeChannel(fromKey);
            logger.info("user {} logout", fromKey);
        }

    }

}

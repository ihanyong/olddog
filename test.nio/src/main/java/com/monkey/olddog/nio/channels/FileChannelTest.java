package com.monkey.olddog.nio.channels;/**
 * Created by hanyong on 2017/7/20.
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.util.Calendar;

/**
 * @author hanyong
 * @Date 2017/7/20
 */
public class FileChannelTest {

    public static void main(String[] args) {
        String path = "/Users/hanyong/code/olddog";
        String file = "hanyong.text";

        test(path, file);

    }

    public static void test(String path, String file) {
        try (RandomAccessFile afile = new RandomAccessFile(Paths.get(path, file).toFile(), "rw");
             FileChannel channel = afile.getChannel();
        ) {
            channel.position(channel.size());

            Calendar calendar = Calendar.getInstance();
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int month = 1+calendar.get(Calendar.MONTH);

            ByteBuffer buffer = ByteBuffer.allocate(48);
            buffer.put(("\nhello hanyong, today is " + day + "/" + month).getBytes());

            buffer.flip();
            while (buffer.hasRemaining()) {
                channel.write(buffer);
            }


            buffer.clear();

            channel.position(0);

            int count = channel.read(buffer);
            while (-1 != count) {

                buffer.flip();

                byte[] bytes = new byte[2];

                while (buffer.hasRemaining()) {
                    buffer.get(bytes);
                }

                buffer.clear();
            }




        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

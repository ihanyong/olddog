package com.monkey.ClassLoaderExample;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Paths;
import java.text.MessageFormat;

/**
 * MyClassLoader
 *
 * @author yong.han
 * 2018/12/28
 */
public class MyClassLoader extends ClassLoader {


    public MyClassLoader(ClassLoader parent) {
        super(parent);
    }

    private static final String base_path = "E:/workspace/";




    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024*1024*5);
        String filePath = name.replace(".", "/").concat(".class");

        try (RandomAccessFile file = new RandomAccessFile(base_path + filePath, "r");
             ReadableByteChannel channel = file.getChannel();) {

            while (channel.read(byteBuffer) > -1) {
            }

            System.out.println(MessageFormat.format("load class {0} from {1}", name , base_path + filePath));
            byteBuffer.flip();
            return defineClass(name, byteBuffer, null);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new ClassNotFoundException(name);
    }
}

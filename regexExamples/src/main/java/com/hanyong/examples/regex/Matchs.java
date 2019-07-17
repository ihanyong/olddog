package com.hanyong.examples.regex;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.WRITE;

/**
 * Matchs
 *
 * @author yong.han
 * 2019/5/24
 */
public class Matchs {


//    public static final Pattern imgPattern = Pattern.compile("<img *[ \\w./\"]*src=\"([\\w/.-_]+)\" [ \\w./\"]*>");
    public static final Pattern imgPattern = Pattern.compile("<img[ \\w./\"%=]*src=\"([\\w/.-_]+)\"[ \\w./\"%=]*>");

    private static final int batchSize = 2048*10;

    public static void main(String[] args) throws IOException {
//        String img =  "<img src=\"../fig/levels_of_abstraction.svg\" alt=\"Programming levels of abstraction\" class=\"offset\" width=\"80%\">";
//        Matcher m = imgPattern.matcher(img);
//        while (m.find()) {
//            System.out.println(m.group());
//            System.out.println(m.group(1));
//
//        }
        String url = "https://ci.apache.org/projects/flink/flink-docs-release-1.8/concepts/programming-model.html";
//        String url = "https://www.19lou.com/forum-9-thread-10611557216517058-1-1.html";
        String dir = "D:/test/images";
        downloadPicFromPage(url, dir);
    }




    public static void downloadPicFromPage(String urlStr, String localDir) throws MalformedURLException {
        URL pageUrl = new URL(urlStr);

        String pagePath = urlStr.substring(0, 1 + urlStr.lastIndexOf("/"));
        String fileName = urlStr.substring(1+urlStr.lastIndexOf("/"));
        String filePathStr = localDir + (localDir.endsWith("/") ? fileName : "/" + fileName);
        Path filePath = Paths.get(filePathStr);
        filePath.toFile().mkdirs();



        try (
                InputStream inputStream = pageUrl.openStream();
                Scanner scanner = new Scanner(inputStream)
        ) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                Matcher m = imgPattern.matcher(line);
                while (m.find()) {
                    System.out.println(m.group());
                    String imageName = m.group(1);
                    String imageUrl = null;
                    if (imageName.startsWith("http://") || imageName.startsWith("https://")) {
                        imageUrl = imageName;
                    } else {
                        imageUrl = pagePath + imageName;
                    }
                    downloadFromWeb(imageUrl, filePathStr);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public static void downloadFromWeb(String urlStr, String localDir) throws IOException {
        URL url = new URL(urlStr);

        int filNameIndex = urlStr.lastIndexOf("/");
        String fileName = urlStr.substring(filNameIndex+1);

        String filePathStr = localDir + (localDir.endsWith("/") ? fileName : "/" + fileName);
        Path filePath = Paths.get(filePathStr);
        filePath.getParent().toFile().mkdirs();


        long begin = System.currentTimeMillis();
        try (InputStream inputStream = url.openStream();
             ReadableByteChannel remoteFile = Channels.newChannel(inputStream);
             FileChannel localFile = FileChannel.open(filePath, CREATE, WRITE);
        ) {

            ByteBuffer bf = ByteBuffer.allocate(batchSize);
            bf.clear();
            long bts = 0;
            while ((bts += remoteFile.read(bf)) > 0) {
                bf.flip();
                localFile.write(bf);
                bf.clear();
            }

            System.out.println("download " + fileName + " completed! cost " + (System.currentTimeMillis() - begin) + "ms");
        }
//        URL url = new URL("https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/batch/");
//        Object content = url.getContent();
//        System.out.println(content);
//        Scanner scanner = new Scanner((InputStream) content);
//        while (scanner.hasNext()) {
//            System.out.println(scanner.nextLine());
//        }


    }


    public static void testMatcher(String[] args) {
        Pattern p = Pattern.compile("([a-z]+):([0-9]+)");

        Matcher matcher = p.matcher("6546hanyong:28safasd");
        matcher.find();

        System.out.println(matcher.group());
        System.out.println(matcher.group(0));
        System.out.println(matcher.group(1));
        System.out.println(matcher.group(2));
//        System.out.println(matcher.group(3));
        System.out.println(matcher.groupCount());


    }
}

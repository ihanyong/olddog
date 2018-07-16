package com.monkey.olddog.nio;/**
 * Created by hanyong on 2018/7/16.
 */

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hanyong
 * @Date 2018/7/16
 */
public class CountSrc {
    public static void main(String[] args) throws IOException {
        List<String> srcs = new ArrayList<>();

        Path dir = Paths.get("").toAbsolutePath();

        Files.walkFileTree(dir, new SimpleFileVisitor<Path>(){
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

                if (file.toString().endsWith(".java") || file.toString().endsWith(".properties")) {
                    srcs.add(file.toString());
                }
                return FileVisitResult.CONTINUE;
            }
        });

        Path resultFile = dir.resolve(Paths.get("result.txt"));
        Files.write(resultFile, srcs);

    }


}

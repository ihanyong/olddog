package olddog.serializable;

import java.io.*;

/**
 * SerializableObjectExample
 *
 * @author yong.han
 * 2019/3/7
 */
public class SerializableObjectExample {


    public static void serialize(Object obj, OutputStream outputStream) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(outputStream)) {
            out.writeObject(obj);
            out.flush();
        }
    }

    public static Object deserialize(InputStream inputStream) throws IOException, ClassNotFoundException {
        try (ObjectInputStream input = new ObjectInputStream(inputStream)) {
            Object obj = input.readObject();
            return obj;
        }
    }


    public static Object testInMemory(Object originObj) throws IOException, ClassNotFoundException {

        ByteArrayOutputStream byteout = new ByteArrayOutputStream(1024);
        serialize(originObj, byteout);

        byte[] bytes = byteout.toByteArray();


        return deserialize(new ByteArrayInputStream(bytes));
    }


    public static Object testWithFile(String path, Object originObj) throws IOException, ClassNotFoundException {
        writeObj2File(path, originObj);
        return readObjFile(path);
    }
    public static void writeObj2File(String path, Object originObj) throws IOException {
        FileOutputStream byteout = new FileOutputStream(path);
        serialize(originObj, byteout);
    }
    public static Object readObjFile(String path) throws IOException, ClassNotFoundException {
        return deserialize(new FileInputStream(path));
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Book book = new Book();
        book.setAuthor("qianzhongshu");
        book.setName("weicheng");
        book.setPublisher("zhonghuashuju");
        System.out.println(book);


        Book book2 = new Book();
        book2.setAuthor("曹禺");
        book2.setName("雷雨");
        book2.setPublisher("人民出版社");
        System.out.println(book2);

//        Book backBook = (Book) testInMemory(book);
//        Book backBook = (Book) testWithFile("E:\\myspace\\diary\\book.obj", book);
        Book backBook = (Book) readObjFile("E:\\myspace\\diary\\book.obj");

        System.out.println(backBook);
//        System.out.println(backBook==book);
    }
}

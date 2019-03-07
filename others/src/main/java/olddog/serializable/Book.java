package olddog.serializable;

import java.io.Serializable;

/**
 * Book
 *
 * @author yong.han
 * 2019/3/7
 */
public class Book implements Serializable {
    //public class Book  {
    public static final long serialVersionUID = 10L;


    private String name;
    private String publisher;
    private String author;

//    private String test;

    private double price;


    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", publisher='" + publisher + '\'' +
                ", author='" + author + '\'' +
//                ", test='" + test + '\'' +
                ", price=" + price +
                '}';
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}


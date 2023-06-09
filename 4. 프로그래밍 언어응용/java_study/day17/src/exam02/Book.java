package exam02;

import java.io.Serializable;

public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    private String title;
    private transient String bookName;
    private String author;

    public Book(String title, String bookName) {
        this.title = title;
        this.bookName = bookName;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", bookName='" + bookName + '\'' +
                '}';
    }
}

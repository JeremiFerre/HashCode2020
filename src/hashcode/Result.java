package hashcode;

import java.util.List;

public class Result {

    private long libId;
    private List<Book> booksToSend;

    public Result(long libId, List<Book> booksToSend) {
        this.libId = libId;
        this.booksToSend = booksToSend;
    }

    public long getLibId() {
        return libId;
    }

    public void setLibId(long libId) {
        this.libId = libId;
    }

    public List<Book> getBooksToSend() {
        return booksToSend;
    }

    public void setBooksToSend(List<Book> booksToSend) {
        this.booksToSend = booksToSend;
    }
}

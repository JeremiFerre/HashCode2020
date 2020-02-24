package hashcode;

import java.util.List;

public class Library {

    private long ID;
    private List<Book> books;
    private long signUpDays;
    private long maxBooksPerDay;
    private long totalBooksScore;

    public Library() {
    }

    public Library(long ID, List<Book> books, long signUpDays, long maxBooksPerDay) {
        this.ID = ID;
        this.books = books;
        this.signUpDays = signUpDays;
        this.maxBooksPerDay = maxBooksPerDay;
    }

    public long totalBooksScore() {
        return books.stream().mapToLong(Book::getScore).sum();
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public long getSignUpDays() {
        return signUpDays;
    }

    public void setSignUpDays(long signUpDays) {
        this.signUpDays = signUpDays;
    }

    public long getMaxBooksPerDay() {
        return maxBooksPerDay;
    }

    public void setMaxBooksPerDay(long maxBooksPerDay) {
        this.maxBooksPerDay = maxBooksPerDay;
    }

    public long getTotalBooksScore() {
        return totalBooksScore;
    }

    public void setTotalBooksScore(long totalBooksScore) {
        this.totalBooksScore = totalBooksScore;
    }
}

package hashcode;

import java.util.*;

public class Book {

    private long ID;
    private long score;
    private Set<Library> libraries = new HashSet<>();
    private long scoreANous;

    public Book() {
    }

    public Book(long ID, long score) {
        this.ID = ID;
        this.score = score;
    }

    public Book(long ID, long score, Set<Library> libraries) {
        this.ID = ID;
        this.score = score;
        this.libraries = libraries;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public Set<Library> getLibraries() {
        return libraries;
    }

    public void setLibraries(Set<Library> libraries) {
        this.libraries = libraries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return ID == book.ID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }

    public long getScoreANous() {
        return scoreANous;
    }

    public void setScoreANous(long scoreANous) {
        this.scoreANous = scoreANous;
    }
}

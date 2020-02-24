package hashcode.algo;

import hashcode.Book;
import hashcode.Library;
import hashcode.Result;

import java.util.*;
import java.util.stream.Collectors;

public class GreedyLibraryDaysAlgo3 implements Algorithm {

    @Override
    public List<Result> run(List<Library> libraries, List<Book> books, long maxDays) {
        List<Library> libCopy = new ArrayList<>(libraries);
        List<Book> booksCopy = new ArrayList<>(books);

        booksCopy.forEach(book -> {
            book.setScoreANous(book.getScore() / (book.getLibraries().size() == 0 ? 1 : book.getLibraries().size()));
        });

        libCopy.forEach(lib -> {
            lib.getBooks().sort((book, t1) -> Long.compare(t1.getScore(), book.getScore()));
            long max = maxDays * lib.getMaxBooksPerDay();
            int maxInt = max >= Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) max;
            lib.setTotalBooksScore(lib.getBooks().subList(0, maxInt >= lib.getBooks().size() ? lib.getBooks().size() : maxInt)
                    .stream().mapToLong(Book::getScoreANous).sum() / (long) Math.pow(lib.getSignUpDays(), 2));
        });

        Collections.sort(libCopy, (library, t1) -> Long.compare(t1.getTotalBooksScore(), library.getTotalBooksScore()));

        Set<Book> booksLib = new HashSet<>();
        libCopy.forEach(lib -> {
            List<Book> toRemove = new ArrayList<>();
            lib.getBooks().forEach(book -> {
                if (booksLib.contains(book)) {
                    toRemove.add(book);
                } else {
                    booksLib.add(book);
                }
            });

            List<Book> copy = new ArrayList<>(lib.getBooks());
            copy.removeAll(toRemove);
            lib.setBooks(copy);
        });

        List<Library> collect = libCopy.stream().filter(lib -> !lib.getBooks().isEmpty()).collect(Collectors.toList());

        collect.forEach(lib -> {
            lib.getBooks().sort((book, t1) -> Long.compare(t1.getScore(), book.getScore()));
            long max = maxDays * lib.getMaxBooksPerDay();
            int maxInt = max >= Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) max;
            lib.setTotalBooksScore(lib.getBooks().subList(0, maxInt >= lib.getBooks().size() ? lib.getBooks().size() : maxInt)
                    .stream().mapToLong(Book::getScoreANous).sum() / (long) Math.pow(lib.getSignUpDays(), 2));
        });

        Collections.sort(collect, (library, t1) -> Long.compare(t1.getTotalBooksScore(), library.getTotalBooksScore()));

        Set<Book> booksLib2 = new HashSet<>();
        collect.forEach(lib -> {
            List<Book> toRemove = new ArrayList<>();
            lib.getBooks().forEach(book -> {
                if (booksLib2.contains(book)) {
                    toRemove.add(book);
                } else {
                    booksLib2.add(book);
                }
            });

            List<Book> copy = new ArrayList<>(lib.getBooks());
            copy.removeAll(toRemove);
            lib.setBooks(copy);
        });

        List<Library> collect1 = collect.stream().filter(lib -> !lib.getBooks().isEmpty()).collect(Collectors.toList());
        List<Result> results = collect1.stream().map(lib -> new Result(lib.getID(), lib.getBooks())).collect(Collectors.toList());

        return results;
    }
}

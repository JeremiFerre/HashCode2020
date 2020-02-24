package hashcode.algo;

import hashcode.Book;
import hashcode.Library;
import hashcode.Result;

import java.util.*;
import java.util.stream.Collectors;

public class GreedyLibraryDaysAlgo2 implements Algorithm {

    @Override
    public List<Result> run(List<Library> libraries, List<Book> books, long maxDays) {
        List<Library> libCopy = new ArrayList<>(libraries);
        List<Book> booksCopy = new ArrayList<>(books);

        libCopy.forEach(lib -> {
            lib.getBooks().sort((book, t1) -> Long.compare(t1.getScore(), book.getScore()));
            long max = maxDays * lib.getMaxBooksPerDay();
            int maxInt = max >= Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) max;
            lib.setTotalBooksScore(lib.getBooks().subList(0, maxInt >= lib.getBooks().size() ? lib.getBooks().size() : maxInt)
                    .stream().mapToLong(Book::getScore).sum() / lib.getSignUpDays());
        });

        Collections.sort(libCopy, (library, t1) -> Long.compare(t1.getTotalBooksScore(), library.getTotalBooksScore()));

        Set<Book> booksLib = new HashSet<>();
        libCopy.forEach(lib -> {
            List<Book> toRemove = new ArrayList<>();
            lib.getBooks().forEach(book -> {
                if (booksLib.contains(book)) {
                    toRemove.add(book);
                }
            });

            List<Book> copy = new ArrayList<>(lib.getBooks());
            copy.removeAll(toRemove);
            lib.setBooks(copy);
        });

        libCopy.forEach(lib -> {
            long max = maxDays * lib.getMaxBooksPerDay();
            int maxInt = max >= Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) max;
            lib.setTotalBooksScore(lib.getBooks().subList(0, maxInt >= lib.getBooks().size() ? lib.getBooks().size() : maxInt)
                    .stream().mapToLong(Book::getScore).sum() / lib.getSignUpDays());
        });

        Collections.sort(libCopy, (library, t1) -> Long.compare(t1.getTotalBooksScore(), library.getTotalBooksScore()));

        List<Result> results = libCopy.stream().map(lib -> new Result(lib.getID(), lib.getBooks())).collect(Collectors.toList());

        return results;
    }
}

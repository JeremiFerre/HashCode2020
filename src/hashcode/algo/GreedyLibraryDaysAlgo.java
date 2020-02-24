package hashcode.algo;

import hashcode.Book;
import hashcode.Library;
import hashcode.Result;

import java.util.*;
import java.util.stream.Collectors;

public class GreedyLibraryDaysAlgo implements Algorithm {

    @Override
    public List<Result> run(List<Library> libraries, List<Book> books, long maxDays) {
        List<Library> libCopy = new ArrayList<>(libraries).stream().filter(lib -> lib.getSignUpDays() <= maxDays).collect(Collectors.toList());
        List<Book> booksCopy = new ArrayList<>(books);

        libCopy.forEach(lib -> {
            lib.getBooks().sort((book, t1) -> Long.compare(t1.getScore(), book.getScore()));
            long max = maxDays * lib.getMaxBooksPerDay();
            int maxInt = max >= Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) max;
            lib.setTotalBooksScore(lib.getBooks().subList(0, maxInt >= lib.getBooks().size() ? lib.getBooks().size() : maxInt)
                    .stream().mapToLong(Book::getScore).sum() / lib.getSignUpDays() * 2);
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
                    .stream().mapToLong(Book::getScore).sum() / lib.getSignUpDays() * 2);
        });

        Collections.sort(collect, (library, t1) -> Long.compare(t1.getTotalBooksScore(), library.getTotalBooksScore()));

        for (int i = collect.size() - 1; i >= 1; i--) {
            double rand = new Random().nextDouble();
            if (rand > 0.5) {
                Library temp = collect.get(i);
                collect.set(i, collect.get(i - 1));
                collect.set(i - 1, temp);
            }
        }

        List<Result> results = collect.stream().map(lib -> new Result(lib.getID(), lib.getBooks())).collect(Collectors.toList());

        return results;
    }
}

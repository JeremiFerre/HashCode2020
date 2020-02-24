package hashcode.algo;

import hashcode.Book;
import hashcode.Library;
import hashcode.Result;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RandomAlgo implements Algorithm {

    @Override
    public List<Result> run(List<Library> libraries, List<Book> books, long maxDays) {
        List<Library> libCopy = new ArrayList<>(libraries);
        List<Book> booksCopy = new ArrayList<>(books);

        Collections.shuffle(libraries);
        List<Result> results = libraries.stream().map(lib -> new Result(lib.getID(), lib.getBooks())).collect(Collectors.toList());

        return results;
    }
}

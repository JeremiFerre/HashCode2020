package hashcode.algo;

import hashcode.Book;
import hashcode.Library;
import hashcode.Result;

import java.util.List;

public interface Algorithm {

    List<Result> run(List<Library> libraries, List<Book> books, long maxDays);
}

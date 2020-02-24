package hashcode;

import hashcode.algo.Algorithm;
import hashcode.algo.GreedyLibraryDaysAlgo;
import hashcode.algo.RandomAlgo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String[] line = sc.nextLine().split(" ");
        long B = Integer.parseInt(line[0]);
        long L = Integer.parseInt(line[1]);
        long D = Integer.parseInt(line[2]);

        List<Book> books = new ArrayList<>();
        line = sc.nextLine().split(" ");

        long id = 0;
        for (String s : line) {
            books.add(new Book(id++, Integer.parseInt(s)));
        }

        List<Library> libraries = new ArrayList<>();
        for (long i = 0; i < L; i++) {
            line = sc.nextLine().split(" ");
            long S = Integer.parseInt(line[1]);
            long Bpd = Integer.parseInt(line[2]);

            List<Book> inLib = new ArrayList<>();
            line = sc.nextLine().split(" ");

            for (String s : line) {
                inLib.add(books.get(Integer.parseInt(s)));
            }
            libraries.add(new Library(i, inLib, S, Bpd));
            long finalI = i;
            libraries.get((int) i).getBooks().forEach(book -> book.getLibraries().add(libraries.get((int) finalI)));
        }

        Algorithm algorithm = new GreedyLibraryDaysAlgo();
        List<Result> results = algorithm.run(libraries, books, D);

        StringBuilder sb = new StringBuilder()
                .append(results.size())
                .append("\n");

        results.forEach(res -> {
            sb.append(res.getLibId()).append(" ").append(res.getBooksToSend().size()).append("\n");
            res.getBooksToSend().forEach(book -> {
                sb.append(book.getID()).append(" ");
            });
            sb.append("\n");
        });

        switch (args[0]) {
            case "A":
                Files.writeString(Paths.get("outA.txt"), sb.toString());
                break;
            case "B":
                Files.writeString(Paths.get("outB.txt"), sb.toString());
                break;
            case "C":
                Files.writeString(Paths.get("outC.txt"), sb.toString());
                break;
            case "D":
                Files.writeString(Paths.get("outD.txt"), sb.toString());
                break;
            case "E":
                Files.writeString(Paths.get("outE.txt"), sb.toString());
                break;
            case "F":
                Files.writeString(Paths.get("outF.txt"), sb.toString());
                break;
        }
    }
}

package session02_IO;

import java.util.stream.Stream;

public class Ex02_Stream_API {
    public static void main(String[] args) {
        Stream<String> stream = Stream.of("Clip1...", "Clip2...", "Clip3...");

        System.out.println("Youtube has been streaming");
        stream.forEach(s -> {
            System.out.println(s);
        });
    }
}

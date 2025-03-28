package ch03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ExecuteAround {
    public static void main(String... args) throws IOException {
        // 동작 파라미터화를 해서 다양한 동작을 processFile로 전달하는 과정.
        String oneLine = processFile((BufferedReader b) -> b.readLine());
        System.out.println(oneLine);

        String twoLines = processFile((BufferedReader b) -> b.readLine() + b.readLine());
        System.out.println(twoLines);
    }

    // 여기서는 한줄의 텍스트만 읽는 것을 할 수 있다.
//    public String processFile() throws IOException {
//        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
//            return br.readLine();
//        }
//    }

    // 인터페이스를 이용해서 동작 파라미터화
    public static String processFile(BufferedReaderProcessor p) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            return p.process(br);
        }
    }

    public interface BufferedReaderProcessor {
        String process(BufferedReader b) throws IOException;
    }
}

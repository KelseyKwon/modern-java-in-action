package ch03;

public class Lambda {
    // lambda 사용
    Runnable r1 = () -> System.out.println("Hello World 1");

    // 익명 클래스 사용
    Runnable r2 = new Runnable() {
        public void run() {
            System.out.println("Hello World 2");
        }
    };

    public static void process(Runnable r) {
        r.run();
    }

}

package sebfisch;

public class Test {
    public static void main(String[] args) {
        record Point(int x, int y) {}

        Object p = new Point(17,4);
        switch (p) {
            case Point point -> {
                System.out.println(2*(point.x() + point.y()));
            }
            default -> {}
        }
    }
}

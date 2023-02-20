package example;

public class Demo {
    public boolean demo() {
        try {
            return false;
        } finally {
            return true;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Demo().demo());
    }
}

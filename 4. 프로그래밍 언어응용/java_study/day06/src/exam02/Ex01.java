package exam02;

public class Ex01 {
    public static void main(String[] args) {
        Calculator cal = new SimpleCalculator();
        int result = cal.add(10,20);
        System.out.println(result);
        int result2 = cal.minus(10,20);
        System.out.println(result2);
        System.out.println(Calculator.num);     // Calculator.num -> public static final
    }
}

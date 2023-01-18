import java.math.BigDecimal;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BigDecimal num1 = new BigDecimal(scanner.next());
        BigDecimal num2 = new BigDecimal(scanner.next());
        BigDecimal multiply = num1.multiply(num2);
        System.out.println(multiply);
    }
}
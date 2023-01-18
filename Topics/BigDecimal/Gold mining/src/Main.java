import java.math.BigDecimal;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BigDecimal sum = new BigDecimal(0);
        while (scanner.hasNext()) {
            BigDecimal num = new BigDecimal(scanner.next());
            sum = sum.add(num);
        }
        System.out.println(sum);
    }
}
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BigDecimal num = new BigDecimal(scanner.next());
        int type = scanner.nextInt();
        System.out.println(num.setScale(type, RoundingMode.HALF_DOWN));
    }   
}
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int power = scanner.nextInt();
        int scale = scanner.nextInt();
        BigDecimal num = new BigDecimal(scanner.next());
        BigDecimal roundNum = num.setScale(scale, RoundingMode.FLOOR);
        System.out.println(roundNum.pow(power));
    }
}
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> lst = new ArrayList<>(List.of(scanner.nextLine().split("\\s+")));
        System.out.println(lst);
    }
}
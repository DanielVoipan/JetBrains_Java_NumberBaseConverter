import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> lst = new ArrayList<>(List.of(scanner.nextLine().split("\\s+")));
        List<String> out = new ArrayList<>();
        for (int i = lst.size() - 1; i >= 0; i--) {
            if (i % 2 != 0) {
                out.add(lst.get(i));
            }
        }
        out.forEach(o -> System.out.print(o + " "));
    }
}

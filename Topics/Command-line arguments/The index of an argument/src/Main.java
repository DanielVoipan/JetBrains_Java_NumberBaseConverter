import java.util.ArrayList;
import java.util.List;

class Problem {
    public static void main(String[] args) {
        List<String> lst = new ArrayList<>(List.of(args));
        System.out.println(lst.indexOf("test"));
    }
}
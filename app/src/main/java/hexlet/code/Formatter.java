package hexlet.code;

import java.util.List;

import static hexlet.code.formatter.Plain.formatToPlain;
import static hexlet.code.formatter.Stylish.formatToStylish;

public class Formatter {

    static void showDiff(List<List<String>> difference, String format) {
        switch (format) {
            case "plain":
                formatToStylish(difference).forEach(System.out::println);
                break;
            default:
                formatToPlain(difference).forEach(System.out::println);
        }
    }
}

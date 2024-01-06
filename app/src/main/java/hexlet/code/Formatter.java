package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

import static hexlet.code.formatter.Json.formatToJson;
import static hexlet.code.formatter.Plain.formatToPlain;
import static hexlet.code.formatter.Stylish.formatToStylish;

public class Formatter {

    static void showDiff(List<List<String>> difference, String format) throws JsonProcessingException {
        switch (format.toLowerCase()) {
            case "plain":
                formatToStylish(difference).forEach(System.out::println);
                break;
            case "json":
                System.out.println(formatToJson(difference));
                break;
            default:
                formatToPlain(difference).forEach(System.out::println);
        }
    }
}

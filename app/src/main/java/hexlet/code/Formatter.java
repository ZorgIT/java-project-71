package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

import static hexlet.code.formatter.Json.formatToJson;
import static hexlet.code.formatter.Plain.formatToPlain;
import static hexlet.code.formatter.Stylish.formatToStylish;

public class Formatter {

    static String convertToFormat(List<List<String>> difference, String format) {
        switch (format.toLowerCase()) {
            case "plain":
                return formatToStylish(difference);
            case "json":
                return formatToJson(difference);
            default:
                return formatToPlain(difference);
        }

    }

    static void showDiff(List<List<String>> difference, String format) throws JsonProcessingException {
        switch (format.toLowerCase()) {
            case "plain":
                System.out.println(formatToStylish(difference));
                break;
            case "json":
                System.out.println(formatToJson(difference));
                break;
            default:
                System.out.println(formatToPlain(difference));
        }
    }
}

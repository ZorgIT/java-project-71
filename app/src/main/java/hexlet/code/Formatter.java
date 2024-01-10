package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

import static hexlet.code.formatter.Json.formatToJson;
import static hexlet.code.formatter.Plain.formatToPlain;
import static hexlet.code.formatter.Stylish.formatToStylish;

public class Formatter {

    static String convertToFormat(List difference, String format) throws JsonProcessingException {
        switch (format.toLowerCase()) {
            case "plain":
                return formatToPlain(difference);
            case "json":
                return formatToJson(difference);
            default:
                return formatToStylish(difference);
        }

    }

    static void showDiff(List<List<String>> difference, String format) throws JsonProcessingException {
        switch (format.toLowerCase()) {
            case "plain":
                System.out.println(formatToPlain(difference));
                break;
            case "json":
                System.out.println();
                break;
            default:
                System.out.println(formatToStylish(difference));
        }
    }
}

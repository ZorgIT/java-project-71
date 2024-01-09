package hexlet.code.formatter;

import java.util.ArrayList;
import java.util.List;

public class Plain {
    public static String formatToPlain(List<List<String>> difference) {
        List<String> plainFormat = new ArrayList<>();
        plainFormat.add("{");
        for (List<String> line : difference) {
            plainFormat.add(" " + line.get(0) + " " + line.get(1) + ": " + line.get(2).toString().replace(",", ", "));
        }
        String plainString = "";
        for (String line : plainFormat) {
            plainString += line + "\n";
        }
        plainString += "}";
        return plainString;
    }
}

package hexlet.code.formatter;

import java.util.ArrayList;
import java.util.List;

public class Plain {
    public static List<String> formatToPlain(List<List<String>> difference) {
        List<String> plainFormat = new ArrayList<>();
        plainFormat.add("\n{");
        for (List<String> line : difference) {
            plainFormat.add(" " + line.get(0) + " " + line.get(1) + ": " + line.get(2));
        }
        plainFormat.add("}");
        return plainFormat;
    }
}

package hexlet.code.formatter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Json {
    public static String formatToJson(List<List<String>> difference) {
        List<String> jsonFormat = new ArrayList<>();
        Iterator<List<String>> iterator = difference.iterator();
        jsonFormat.add("{");
        while (iterator.hasNext()) {
            StringBuilder sb = new StringBuilder();
            List<String> line1 = iterator.next();
            String lineStatus = line1.get(0);
            sb.append("\t" + lineStatus + " " + line1.get(1)
                    + ": " + line1.get(2));
            jsonFormat.add(sb.toString());
        }
        jsonFormat.add("}");
        String jsonString = "";
        for (String line: jsonFormat) {
            jsonString += line + "\n";
        }
        return jsonString;
    }
}

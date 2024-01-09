package hexlet.code.formatter;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Stylish {
    public static String formatToStylish(List<List<String>> difference) {
        List<String> stylishFormat = new ArrayList<>();
        Iterator<List<String>> iterator = difference.iterator();

        while (iterator.hasNext()) {
            StringBuilder sb = new StringBuilder();
            List<String> line1 = iterator.next();
            String lineStatus = line1.get(0);
            if (lineStatus.equals(" ")) {
                continue;
            }
            if (lineStatus.equals("-")) {
                sb.append("Property " + "\'" + line1.get(1) + "\' ");
                List<String> line2;
                if (iterator.hasNext()) {
                    line2 = iterator.next();
                    if (line1.get(1).equals(line2.get(1))) {
                        sb.append("was updated. From " + checkValueToComplex(line1.get(2)));
                        sb.append(" to " + checkValueToComplex(line2.get(2)));
                    } else {
                        sb.append("was removed" + "\n");
                        sb.append("Property " + "\'" + line2.get(1) + "' "
                                + "was added with value: " + checkValueToComplex(line2.get(2)));
                    }

                } else {
                    sb.append("was removed");
                }
            } else {
                sb.append("Property " + "\'" + line1.get(1) + "\' was"
                        + " added with value: " + checkValueToComplex(line1.get(2)));
            }
            stylishFormat.add(sb.toString());
        }
        String stylishString = "";
        for (String line : stylishFormat) {
            stylishString += line + "\n";
        }
        return stylishString;
    }

    public static String checkValueToComplex(String line) {
        if (line.equals("null") || line.equals("true") || line.equals("false")||StringUtils.isNumeric(line)) {
            return line;
        }

        return (line.charAt(0) == '['
                || line.charAt(0) == '{') ? "[complex value]" : ("'" + (line) + "'");
    }
}

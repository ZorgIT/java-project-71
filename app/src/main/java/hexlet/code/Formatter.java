package hexlet.code;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Formatter {

    static void showDiff(List<List<String>> difference, String format) {
        if (format.equals("stylish")) {
            System.out.println("\n{");
            for (List<String> line : difference) {
                System.out.printf("  %s %s: %s \n", line.get(0), line.get(1),
                        line.get(2));
            }
            System.out.println("}");
        } else if (format.equals("plain")) {
            StringBuilder sb = new StringBuilder();
            Iterator<List<String>> iterator = difference.iterator();
            while (iterator.hasNext()) {
                List<String> line1 = iterator.next();
                String lineStatus = line1.get(0);
                if (lineStatus.equals(" ")) {
                    continue;
                }
                if (lineStatus.equals("-")) {
                    sb.append("Property " + "\'" + line1.get(1) + "\' ");
                    List<String> line2 = new ArrayList<>();
                    if (iterator.hasNext()) {
                        line2 = iterator.next();
                        if (line1.get(1).equals(line2.get(1))) {
                            sb.append("was updated. From ");
                            if (line1.get(2).charAt(0) == '['
                                    || line1.get(2).charAt(0) == '{') {
                                sb.append("[complex value] ");
                            } else {
                                sb.append(line1.get(2) + " ");
                            }
                            if (line2.get(2).charAt(0) == '['
                                    || line1.get(2).charAt(0) == '{') {
                                sb.append("to [complex value]");
                            } else {
                                sb.append("to " + line2.get(2) + " ");
                            }
                        } else {
                            sb.append("was removed" + "\n");
                            sb.append("Property " + "\'" + line2.get(1) + "' "
                                    + "was added with value: ");
                            if (line2.get(2).charAt(0) == '['
                                    || line2.get(2).charAt(0) == '{') {
                                sb.append("[complex value] ");
                            } else {
                                sb.append(line2.get(2) + " ");
                            }
                        }
                    } else {
                        sb.append("was removed");
                    }
                } else {
                    sb.append("Property " + "\'" + line1.get(1) + "\' was"
                            + " added with value: ");
                    if (line1.get(2).charAt(0) == '['
                            || line1.get(2).charAt(0) == '{') {
                        sb.append("[complex value] ");
                    } else {
                        sb.append(line1.get(2) + " ");
                    }
                }
                sb.append("\n");
            }
            System.out.printf(sb.toString());
        }
    }
}

package hexlet.code;

import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Objects;

public class ObjectComparator {

    public static final String UNCHANGED = "unchanged";
    public static final String ADDED = "added";
    public static final String DELETED = "deleted";
    public static final String CHANGED = "changed";

    public static List<Map<String, Object>> diff(Map<String, Object> map1, Map<String, Object> map2) {
        List<Map<String, Object>> diffList = new ArrayList<>();
        Set<String> keys = new TreeSet<>(Comparator.naturalOrder());
        keys.addAll(map1.keySet());
        keys.addAll(map2.keySet());

        for (String key : keys) {
            Map<String, Object> resultMap = new LinkedHashMap<>();
            Object oldValue = map1.get(key);
            Object newValue = map2.get(key);

            resultMap.put("key", key);
            if (!map1.containsKey(key)) {
                resultMap.put("status", ADDED);
                resultMap.put("value2", newValue);
            } else if (!map2.containsKey(key)) {
                resultMap.put("status", DELETED);
                resultMap.put("value1", oldValue);
            } else if (Objects.equals(map1.get(key), map2.get(key))) {
                resultMap.put("status", UNCHANGED);
                resultMap.put("value", oldValue);
            } else if (!Objects.equals(map1.get(key), map2.get(key))) {
                resultMap.put("status", CHANGED);
                resultMap.put("value1", oldValue);
                resultMap.put("value2", newValue);
            }
            diffList.add(resultMap);
        }
        return diffList;

    }
}

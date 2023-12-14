package hexlet.code;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Differ {
    public static void generate(String json1, String json2) throws Exception  {
        Map<String, Object> map1 = getData(json1);
        Map<String, Object> map2 = getData(json2);

        System.out.println("json1 " + map1);
        System.out.println("json2 " + map2);

    }
    public static Map getData(String content) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map
                = objectMapper.readValue(content, new TypeReference<Map<String,Object>>(){});
        return  map;
    }

}




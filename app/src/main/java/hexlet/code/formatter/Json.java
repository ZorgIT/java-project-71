package hexlet.code.formatter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class Json {
    public static String formatToJson(List<Map<String, Object>> difference) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        String jsonFormat = mapper.writeValueAsString(difference);

        return jsonFormat;

    }
}



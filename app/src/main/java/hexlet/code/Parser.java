package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

final class Parser {
    private Parser() {
    }

    public static Map<String, String> parseData(final String content,
                                                final String contentType) {
        try {
            ObjectMapper objectMapper;
            if (contentType.equalsIgnoreCase("json")) {
                objectMapper = new ObjectMapper();
            } else if (contentType.equalsIgnoreCase("yml")) {
                objectMapper = new YAMLMapper();
            } else {
                System.out.println("Unsupported content type: " + contentType);
                return null;
            }
            JsonNode jsonNode = objectMapper.readTree(content);
            Map<String, String> map = new HashMap<>();
            Iterator<Map.Entry<String, JsonNode>> line = jsonNode.fields();
            while (line.hasNext()) {
                Map.Entry<String, JsonNode> next = line.next();
                map.put(next.getKey(), next.getValue().toString().replace("\"", ""));
            }
            return map;
        } catch (IOException e) {
            System.out.println("Error parsing " + contentType + ": " + e.getMessage());
            return null;
        }

    }
}

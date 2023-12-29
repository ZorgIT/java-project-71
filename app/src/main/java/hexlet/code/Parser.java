package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.util.Map;

final class Parser {
    private Parser() {
    }

    public static Map<String, Object> parseData(final String content,
                                                final String contentType) {
        //System.out.println("Received " + contentType + " content: " +
        // content);

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

            Map<String, Object> map = objectMapper.readValue(content,
                    new TypeReference<>() {
                    });
//            System.out.println(contentType.toUpperCase() + " parsed successfully.");
            return map;
        } catch (IOException e) {
            System.out.println("Error parsing " + contentType + ": " + e.getMessage());
            //e.printStackTrace();
            return null;
        }
    }
}

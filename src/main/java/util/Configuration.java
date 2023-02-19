package util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Configuration {
    JsonNode root;

    public Configuration (String path){
        byte[] jsonData;
        try {
            jsonData = Files.readAllBytes(Path.of(path));
            ObjectMapper objectMapper = new ObjectMapper();
            root = objectMapper.readTree(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getParam(String name){
        JsonNode param = root.path(name);
        return param.asText();
    }
}

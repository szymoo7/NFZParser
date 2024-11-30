package pl.backend.client;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser {

    public List<String> parseLocalities(String response) {
        List<String> localities = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode rootNode = mapper.readTree(response);
            JsonNode resultsNode = rootNode.path("data");
            for(JsonNode result : resultsNode) {
                localities.add(result.asText());
            }
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            return localities;
        }
        return localities;
    }
}
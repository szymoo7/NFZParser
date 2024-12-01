package pl.backend.client;

import java.util.ArrayList;
import java.util.List;
import pl.backend.client.pojos.Queue;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.backend.client.pojos.QueueAttributes;

public class JsonParser {

    private final ObjectMapper mapper = new ObjectMapper();

    public List<String> parseLocalities(String response) {
        List<String> localities = new ArrayList<>();
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

    public List<Queue> readPageQueue(String response) {
        List<Queue> queues = new ArrayList<>();
        try {
            JsonNode links = mapper.readTree(response);
            JsonNode dataNode = links.path("data");
            String data = dataNode.toString();
            Queue[] queue = mapper.readValue(data, Queue[].class);
            queues.addAll(List.of(queue));
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            System.out.println("Error while parsing the response");
        }

        return queues;
    }

    public String getNextQueueURL(String response) {
        try {
            JsonNode current = mapper.readTree(response);
            JsonNode currentNode = current.path("links").path("self");
            return "https://api.nfz.gov.pl" + currentNode.asText();
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            System.out.println("Error while parsing the response");
        }
    }

}
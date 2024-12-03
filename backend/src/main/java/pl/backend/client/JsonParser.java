package pl.backend.client;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.node.ArrayNode;
import pl.backend.client.pojos.ProvisionsData;
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
            ArrayNode data = mapper.createArrayNode();
            dataNode.elements().forEachRemaining(data::add);
            data.forEach(q -> {
                try {
                    QueueAttributes attributes = mapper.treeToValue(q.path("attributes"), QueueAttributes.class);
                    String date = q.path("attributes").path("dates").path("date").asText();
                    attributes.setDate(date);
                    Queue queue = new Queue();
                    queue.setAttributes(attributes);
                    queues.add(queue);
                } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
                    System.out.println("Error while parsing the response");
                }
            });
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            System.out.println("Error while parsing the response");
        }

        return queues;
    }

    public JsonNode getNextURL(String response) {
        try {
            JsonNode current = mapper.readTree(response);
            JsonNode nextUrl = current.path("links").path("next");
            return nextUrl;
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            System.out.println("Error while parsing the response");
            return null;
        }
    }

    public List<ProvisionsData> readPageProvisions(String response) {
        //TODO: fix
        List<ProvisionsData> provisions = new ArrayList<>();
        try {
            JsonNode links = mapper.readTree(response);
            JsonNode dataNode = links.path("data").path("data");
            ArrayNode data = mapper.createArrayNode();
            dataNode.elements().forEachRemaining(data::add);
            data.forEach(q -> {
        try {
            ProvisionsData provision = mapper.treeToValue(q, ProvisionsData.class);
            long quantity = q.path("attributes").path("quantity").asLong();
            double value = q.path("attributes").path("value").asDouble();
            double contributionOfPatient = q.path("attributes").path("contribution-of-patient").asDouble();
            double marketPrice = value / quantity;
            double patientPrice = contributionOfPatient / quantity;
            provision.setMarketPrice(marketPrice);
            provision.setPatientPrice(patientPrice);
            provisions.add(provision);
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            System.out.println("Error while parsing the response");
        }
    });
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            System.out.println("Error while parsing the response");
        }

        return provisions;
    }

}
package pl.backend.client;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.node.ArrayNode;
import pl.backend.client.pojos.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser {

    private final ObjectMapper mapper = new ObjectMapper();

    public List<String> readPageLocalities(String response) {
        List<String> localities = new ArrayList<>();
        try {
            JsonNode rootNode = mapper.readTree(response);
            JsonNode resultsNode = rootNode.path("data");
            for(JsonNode result : resultsNode) {
                if(!result.isNull()) {
                    String locality = result.asText();
                    localities.add(locality);
                }
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
        List<ProvisionsData> provisions = new ArrayList<>();
        try {
            JsonNode links = mapper.readTree(response);
            JsonNode dataNode = links.path("data").path("data");
            ArrayNode data = mapper.createArrayNode();
            dataNode.elements().forEachRemaining(data::add);
            data.forEach(q -> {
                try {
                    ProvisionsData provision = mapper.treeToValue(q.path("attributes"), ProvisionsData.class);
                    String medicineProduct = q.path("attributes").path("medicine-product").isNull()
                            ? "-" : q.path("attributes").path("medicine-product").asText();
                    String internationalName = q.path("attributes").path("international-name").isNull()
                            ? "-" : q.path("attributes").path("international-name").asText();
                    provision.setMedicineProduct(medicineProduct);
                    provision.setInternationalName(internationalName);
                    long quantity = q.path("attributes").path("quantity").asLong();
                    double value = q.path("attributes").path("value").asDouble();
                    double contributionOfPatient = q.path("attributes").path("contribution-of-patient").asDouble();
                    if (quantity > 0) {
                        double marketPrice = value / quantity;
                        double patientPrice = contributionOfPatient / quantity;
                        provision.setMarketPrice(marketPrice);
                        provision.setPatientPrice(patientPrice);
                        provisions.add(provision);
                    }
                } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
                    System.out.println("Error while parsing the response");
                }
            });
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            System.out.println("Error while parsing the response");
        }

        return provisions;
    }

    public int readTotalPages(String response) {
        try {
            JsonNode current = mapper.readTree(response);
            JsonNode meta = current.path("meta");
            int count = meta.path("count").asInt();
            return count % 25 == 0 ? count / 25 : count / 25 + 1;
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            System.out.println("Error while parsing the response");
            return 0;
        }
    }

    public List<Table> getTables(String response) {
        List<Table> indexes = new ArrayList<>();
        try {
            JsonNode rootNode = mapper.readTree(response);
            JsonNode resultsNode = rootNode.path("data").path("attributes").path("years");
            ArrayNode years = mapper.createArrayNode();
            resultsNode.elements().forEachRemaining(years::add);
            for(JsonNode year : years) {
                int yearValue = year.path("year").asInt();
                ArrayNode tables = mapper.createArrayNode();
                year.path("tables").elements().forEachRemaining(tables::add);
                for(JsonNode table : tables) {
                    String type = table.path("type").asText();
                    if(!type.equalsIgnoreCase("hospitalization-by-age")) {
                        continue;
                    }
                    String url = table.path("links").path("related").asText();
                    Table index = new Table();
                    index.setYear(yearValue);
                    index.setId(table.path("id").asText());
                    index.setType(type);
                    index.setUrl(url);
                    indexes.add(index);
                }
            }
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            return indexes;
        }
        return indexes;
    }

    public List<HospitalizationByAge> readHospitalizationPage(String response) {
        List<HospitalizationByAge> hospitalizations = new ArrayList<>();
        try {
            JsonNode rootNode = mapper.readTree(response);
            JsonNode attributes = rootNode.path("data").
                    path("attributes");
            int year = attributes.path("year").asInt();
            ArrayNode data = mapper.createArrayNode();
            attributes.path("data").elements().forEachRemaining(data::add);
            for(JsonNode hospitalization : data) {
                HospitalizationByAge hospitalizationByAge = new HospitalizationByAge();
                hospitalizationByAge.setBranch(hospitalization.path("branch").asText());
                hospitalizationByAge.setHospitalType(hospitalization.path("hospital-types").asText());
                hospitalizationByAge.setNumberOfPatients(hospitalization.path("number-of-patients").asInt());
                hospitalizationByAge.setNumberOfHospitalizations(hospitalization.path("number-of-hospitalizations").asInt());
                hospitalizationByAge.setPercentage(hospitalization.path("percentage").asDouble());
                hospitalizationByAge.setYear(year);
                hospitalizations.add(hospitalizationByAge);
            }
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            System.out.println("Error while parsing the response");
        }
        return hospitalizations;
    }
}
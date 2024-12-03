package pl.backend.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.sun.jdi.connect.Connector;
import pl.backend.client.pojos.ProvisionsData;
import pl.backend.client.pojos.Queue;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class Middleman {

    HttpClient client = new HttpClient();
    JsonParser parser = new JsonParser();

    public void printLocalities(String cityName, String provinceCode) {
        try {
            String response = client.getLocalities(cityName, provinceCode);
            System.out.println(parser.parseLocalities(response));
        } catch (java.io.IOException e) {
            System.out.println("Error while connecting to the server");
        }
    }

    public List<String> getLocalities(String cityName, String provinceCode) {
        try {
            String response = client.getLocalities(cityName, provinceCode);
            return parser.parseLocalities(response);
        } catch (java.io.IOException e) {
            System.out.println("Error while connecting to the server");
            return null;
        }
    }

    public List<Queue> getQueues(int status, String provinceCode, String benefitName,
                                   boolean forChildren, String providerName, String providerPlaceName,
                                   String providerPlaceStreetName, String providerCityName) {
        String response = null;
        JsonNode next = null;
        List<Queue> queues = new ArrayList<>();
        int page = 0;
        do {
            try {
                page++;
                response = client.getQueues(status, provinceCode, benefitName, forChildren, providerName,
                        providerPlaceName, providerPlaceStreetName, providerCityName, page);
                queues.addAll(parser.readPageQueue(response));
                next = parser.getNextURL(response);
                sleep(500);
            } catch (IOException | InterruptedException e) {
                System.out.println("Error while connecting to the server");
            }
        } while (!next.isNull());
        return queues;
    }

    public List<ProvisionsData> getProvisions(String provinceCode, LocalDateTime dateFrom, LocalDateTime dateTo,
                                              String medicineProduct, String activeSubstance, String atc,
                                              String gender, int ageGroup, String privilegesAdditional,
                                              String announcement) {
        String response = null;
        JsonNode next = null;
        List<ProvisionsData> provisions = new ArrayList<>();
        int page = 0;
        do {
            try {
                page++;
                response = client.getProvisions(provinceCode, dateFrom, dateTo, medicineProduct, activeSubstance,
                        atc, gender, ageGroup, privilegesAdditional, announcement, page);
                provisions.addAll(parser.readPageProvisions(response));
                next = parser.getNextURL(response);
                sleep(500);
                System.out.println("Page: " + page);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        } while (!next.isNull());
        return provisions;
    }

//    public void printQueues(int status, String provinceCode, String benefitName,
//                                 boolean forChildren, String providerName, String providerPlaceName,
//                                 String providerPlaceStreetName, String providerCityName) {
//        List<Queue> queues = getQueues(status, provinceCode, benefitName, forChildren, providerName, providerPlaceName, providerPlaceStreetName, providerCityName);
//        queues.forEach(System.out::println);
//    }

    public static void main(String[] args) {
        Middleman connector = new Middleman();
//        connector.printQueues(1, "02", "Leczenie", true, null, null, null, "Toruń");
        connector.getProvisions("02", null, null, null,
                null, null, "M", 2,
                null, null).forEach(System.out::println);
    }
}
package pl.backend.client;

import com.sun.jdi.connect.Connector;
import pl.backend.client.pojos.Queue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    public void printQueues(int status, String provinceCode, String benefitName,
                                   boolean forChildren, String providerName, String providerPlaceName,
                                   String providerPlaceStreetName, String providerCityName) {
        String start = null;
        try {
            start = client.getQueues(status, provinceCode, benefitName, forChildren,
                    providerName, providerPlaceName, providerPlaceStreetName, providerCityName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<String> urls = new ArrayList<>();
        urls.add(start);




    }

    public static void main(String[] args) {
        Middleman connector = new Middleman();
        connector.printQueues(1, "02", "Leczenie", true, null, null, null, "Toruń");
    }
}

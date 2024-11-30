package pl.backend.client;

public class FrontendConntector {

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

    public static void main(String[] args) {
        FrontendConntector connector = new FrontendConntector();
        connector.printLocalities("Toruń", "02");
    }
}

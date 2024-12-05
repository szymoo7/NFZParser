package pl.backend.client;

import com.fasterxml.jackson.databind.JsonNode;
import pl.backend.client.pojos.HospitalizationByAge;
import pl.backend.client.pojos.ProvisionsData;
import pl.backend.client.pojos.Queue;
import pl.backend.client.pojos.Table;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;

public class Middleman {

    private HttpClient client = new HttpClient();
    private JsonParser parser = new JsonParser();
    private Logger logger = Logger.getLogger(Middleman.class.getName());

    public List<String> getLocalities(String cityName, String provinceCode) {

        String response = null;
        JsonNode next = null;
        List<String> localities = new ArrayList<>();
        int totalPages = -1;
        int page = 0;
        do {
            try {
                page++;
                response = client.getLocalities(cityName, provinceCode)
                        != null ? client.getLocalities(cityName, provinceCode) : null;
                if(response == null) {
                    break;
                }
                if(totalPages == -1) {
                    totalPages = parser.readTotalPages(response);
                }
                localities.addAll(parser.readPageLocalities(response));
                logger.info("\u001B[32mStrona: " + page + " / " + totalPages + "\u001B[0m");
                next = parser.getNextURL(response);
                sleep(500);
            } catch (IOException | InterruptedException e) {
                System.out.println("Błąd podczas łączenia z serwerem");
            }
        } while (!next.isNull());
        return localities;
    }

    public List<Queue> getQueues(int status, String provinceCode, String benefitName,
                                   boolean forChildren, String providerName, String providerPlaceName,
                                   String providerPlaceStreetName, String providerCityName) {
        String response = null;
        JsonNode next = null;
        List<Queue> queues = new ArrayList<>();
        int totalPages = -1;
        int page = 0;
        do {
            try {
                page++;
                response = client.getQueues(status, provinceCode, benefitName, forChildren, providerName,
                        providerPlaceName, providerPlaceStreetName, providerCityName, page);
                if(response == null) {
                    break;
                }
                if(totalPages == -1) {
                    totalPages = parser.readTotalPages(response);
                }
                queues.addAll(parser.readPageQueue(response));
                logger.info("\u001B[32mStrona: " + page + " / " + totalPages + "\u001B[0m");
                next = parser.getNextURL(response);
                sleep(500);
            } catch (IOException | InterruptedException e) {
                System.out.println("Błąd podczas łączenia z serwerem");
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
        int totalPages = -1;
        int page = 0;
        do {
            try {
                page++;
                response = client.getProvisions(provinceCode, dateFrom, dateTo, medicineProduct, activeSubstance,
                        atc, gender, ageGroup, privilegesAdditional, announcement, page);
                if(response == null) {
                    break;
                }
                if(totalPages == -1) {
                    totalPages = parser.readTotalPages(response);
                }
                provisions.addAll(parser.readPageProvisions(response));
                logger.info("\u001B[32mStrona: " + page + " / " + totalPages + "\u001B[0m");
                next = parser.getNextURL(response);
                sleep(500);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        } while (!next.isNull());
        return provisions;
    }

    private List<Table> getTables(String catalog, String name, int year) {
        logger.info("\u001B[32mCzytanie tabel"+ "\u001B[0m");
        List<Table> tables = new ArrayList<>();
        String response = client.getIndexOfTable(catalog, name, year);
        if (response == null) {
            return tables;
        }
        tables.addAll(parser.getTables(response));
        return tables;
    }

    public List<HospitalizationByAge>getHospitalizationsByAge(String catalog, String name, int year) {
        List<Table> tables = new ArrayList<>(getTables(catalog, name, year));
        List<HospitalizationByAge> hospitalizations = new ArrayList<>();
        for(Table t : tables) {
            int page = 0;
            String response = null;
            try {
                do {
                    page++;
                    response = client.getBasicData(t.getId(), page);
                    if(response == null) {
                        break;
                    }
                    List<HospitalizationByAge> parsed = parser.readHospitalizationPage(response);
                    hospitalizations.addAll(parsed);
                    logger.info("\u001B[32mCzytanie hospitalizacji strona: " + page + "\u001B[0m");
                    sleep(500);
                } while (response != null);
            } catch (InterruptedException e) {
                System.out.println("Błąd podczas łączenia z serwerem");
            }
        }
        return hospitalizations;
    }

    public static void main(String[] args) {
        Middleman connector = new Middleman();
//        connector.printQueues(1, "02", "Leczenie", true, null, null, null, "Toruń");
//        connector.getProvisions(null, null, null, null,
//                null, "zs", null, 0,
//                null, null).forEach(System.out::println);

        connector.getHospitalizationsByAge("1w", "PRZESZCZEPIENIE/WSPOMAGANIE SERCA", -1).forEach(System.out::println);
    }
}
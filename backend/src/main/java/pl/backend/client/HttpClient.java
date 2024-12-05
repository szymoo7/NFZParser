package pl.backend.client;

import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;

import java.time.LocalDateTime;
import java.util.UUID;

public class HttpClient {
    okhttp3.OkHttpClient client = new okhttp3.OkHttpClient();

    String getLocalities(String cityName, String provinceCode) throws java.io.IOException {
        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host("api.nfz.gov.pl")
                .addPathSegment("app-itl-api")
                .addPathSegment("places")
                .addQueryParameter("page", "1")
                .addQueryParameter("limit", "25")
                .addQueryParameter("format", "json")
                .addQueryParameter("name", cityName)
                .addQueryParameter("province", provinceCode)
                .addQueryParameter("api-version", "1.3")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .addHeader("accept", "text/plain")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                return response.body().string();
            } else {
                return null;
            }
        }
    }

    String getQueues(int status, String provinceCode, String benefitName,
                     boolean forChildren, String providerName, String providerPlaceName,
                     String providerPlaceStreetName, String providerCityName, int page) throws java.io.IOException {

        HttpUrl.Builder urlBuilder = new HttpUrl.Builder()
                .scheme("https")
                .host("api.nfz.gov.pl")
                .addPathSegment("app-itl-api")
                .addPathSegment("queues")
                .addQueryParameter("page", String.valueOf(page))
                .addQueryParameter("limit", "25")
                .addQueryParameter("format", "json")
                .addQueryParameter("case", String.valueOf(status));

        if (provinceCode != null) {
            urlBuilder.addQueryParameter("province", provinceCode);
        }
        if (benefitName != null) {
            urlBuilder.addQueryParameter("benefit", benefitName);
        }
        if (forChildren) {
            urlBuilder.addQueryParameter("benefitForChildren", "true");
        }
        if (providerName != null) {
            urlBuilder.addQueryParameter("provider", providerName);
        }
        if (providerPlaceName != null) {
            urlBuilder.addQueryParameter("place", providerPlaceName);
        }
        if (providerPlaceStreetName != null) {
            urlBuilder.addQueryParameter("street", providerPlaceStreetName);
        }
        if (providerCityName != null) {
            urlBuilder.addQueryParameter("locality", providerCityName);
        }

        urlBuilder.addQueryParameter("api-version", "1.3");


        HttpUrl url = urlBuilder.build();

        Request request = new Request.Builder()
                .url(url)
                .addHeader("accept", "text/plain")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                return response.body().string();
            } else {
                return null;
            }
        }
    }

    String getQueues(int status, String provinceCode, String benefitName,
                     boolean forChildren, String providerName, String providerPlaceName,
                     String providerPlaceStreetName, String providerCityName) throws java.io.IOException {
        return getQueues(status, provinceCode, benefitName, forChildren,
                providerName, providerPlaceName, providerPlaceStreetName, providerCityName, 1);
    }

    String getProvisions(String provinceCode, LocalDateTime dateFrom, LocalDateTime dateTo,
                         String medicineProduct, String activeSubstance, String atc,
                         String gender, int ageGroup, String privilegesAdditional,
                         String announcement, int page) throws java.io.IOException {

        HttpUrl.Builder urlBuilder = new HttpUrl.Builder()
                .scheme("https")
                .host("api.nfz.gov.pl")
                .addPathSegment("app-stat-api-ra")
                .addPathSegment("provisions")
                .addQueryParameter("page", String.valueOf(page))
                .addQueryParameter("limit", "25")
                .addQueryParameter("format", "json");

        if (provinceCode != null) {
            urlBuilder.addQueryParameter("province", provinceCode);
        }
        if (dateFrom != null) {
            urlBuilder.addQueryParameter("dateFrom", dateFrom.toString());
        }
        if (dateTo != null) {
            urlBuilder.addQueryParameter("dateTo", dateTo.toString());
        }
        if (medicineProduct != null) {
            urlBuilder.addQueryParameter("medicineProduct", medicineProduct);
        }
        if (activeSubstance != null) {
            urlBuilder.addQueryParameter("activeSubstance", activeSubstance);
        }
        if (atc != null) {
            urlBuilder.addQueryParameter("atc", atc);
        }
        if (gender != null) {
            urlBuilder.addQueryParameter("gender", gender);
        }
        if(ageGroup != 0) {
            urlBuilder.addQueryParameter("ageGroup", String.valueOf(ageGroup));
        }
        if(privilegesAdditional != null) {
            urlBuilder.addQueryParameter("privilegesAdditional", privilegesAdditional);
        }
        if(announcement != null) {
            urlBuilder.addQueryParameter("announcement", announcement);
        }

        urlBuilder.addQueryParameter("api-version", "1.0");

        HttpUrl url = urlBuilder.build();

        Request request = new Request.Builder()
                .url(url)
                .addHeader("accept", "text/plain")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                return response.body().string();
            } else {
                return null;
            }
        }
    }

    String getIndexOfTable(String catalog, String name, int year) {
        HttpUrl.Builder urlBuilder = new HttpUrl.Builder()
                .scheme("https")
                .host("api.nfz.gov.pl")
                .addPathSegment("app-stat-api-jgp")
                .addPathSegment("index-of-tables")
                .addQueryParameter("limit", "25")
                .addQueryParameter("format", "json");

        if (catalog != null) {
            urlBuilder.addQueryParameter("catalog", catalog);
        }
        if (name != null) {
            urlBuilder.addQueryParameter("name", name);
        }
        if (year != -1) {
            urlBuilder.addQueryParameter("year", String.valueOf(year));
        }

        urlBuilder.addQueryParameter("api-version", "1.1");
        HttpUrl url = urlBuilder.build();

        Request request = new Request.Builder()
                .url(url)
                .addHeader("accept", "text/plain")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                return response.body().string();
            } else {
                return null;
            }
        } catch (java.io.IOException e) {
            return null;
        }
    }

    String getHospitalizationByAge(UUID id, int page) {
        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host("api.nfz.gov.pl")
                .addPathSegment("app-stat-api-jgp")
                .addPathSegment("hospitalization-by-age")
                .addPathSegment(id.toString())
                .addQueryParameter("page", String.valueOf(page))
                .addQueryParameter("limit", "25")
                .addQueryParameter("format", "json")
                .addQueryParameter("api-version", "1.1")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .addHeader("accept", "text/plain")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                return response.body().string();
            } else {
                return null;
            }
        } catch (java.io.IOException e) {
            return null;
        }
    }

}

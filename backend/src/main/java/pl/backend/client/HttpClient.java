package pl.backend.client;

import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;
import pl.backend.client.pojos.Queue;

import java.util.List;

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
            return response.body().string();
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
                    return response.body().string();
                }
    }

    String getQueues(int status, String provinceCode, String benefitName,
                     boolean forChildren, String providerName, String providerPlaceName,
                     String providerPlaceStreetName, String providerCityName) throws java.io.IOException {
        return getQueues(status, provinceCode, benefitName, forChildren,
                providerName, providerPlaceName, providerPlaceStreetName, providerCityName, 1);
    }

    String getResponse(String url) {
        Request request = new Request.Builder()
                .url(url)
                .addHeader("accept", "text/plain")
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (java.io.IOException e) {
            return null;
        }
    }
}

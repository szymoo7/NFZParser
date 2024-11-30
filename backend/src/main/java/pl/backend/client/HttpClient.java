package pl.backend.client;

import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;

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


}

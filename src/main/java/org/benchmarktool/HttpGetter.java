package org.benchmarktool;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.HttpResponse;

import java.io.IOException;
import java.util.Arrays;

class HttpGetter {
    private int statusCode;
    private int bytesCount;

    HttpGetter(String url, int timeout) throws IOException {
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(timeout)
                .setConnectionRequestTimeout(timeout)
                .setSocketTimeout(timeout).build();
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(config)
                .build();

        System.out.println(url);
        HttpGet getMethod = new HttpGet(url);
        HttpResponse response = httpClient.execute(getMethod);

        statusCode = response.getStatusLine().getStatusCode();
        bytesCount = Arrays.stream(response.getAllHeaders())
                .mapToInt(header -> header.getName().length() + header.getValue().length())
                .sum();
    }

    int statusCode() {
        return statusCode;
    }

    int bytesCount() {
        return bytesCount;
    }
}

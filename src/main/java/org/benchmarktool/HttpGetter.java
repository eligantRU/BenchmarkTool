package org.benchmarktool;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.HttpResponse;

import java.io.IOException;
import java.util.Arrays;

class HttpGetter {
    CloseableHttpClient httpClient;
    private final String url;

    HttpGetter(String url_, int timeout) {
        url = url_;

        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(timeout)
                .setConnectionRequestTimeout(timeout)
                .setSocketTimeout(timeout).build();
        httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(config)
                .build();
    }

    ResponseInfo emit() throws IOException {
        HttpResponse response = httpClient.execute(new HttpGet(url));
        int statusCode = response.getStatusLine().getStatusCode();
        int bytesCount = Arrays.stream(response.getAllHeaders())
                .mapToInt(header -> header.getName().length() + header.getValue().length())
                .sum();
        return new ResponseInfo(statusCode, bytesCount);
    }
}

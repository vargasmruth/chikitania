package com.chikitania.adapters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.chikitania.ChikitaniaConfiguration;
import com.chikitania.config.ExternalServer;
import io.dropwizard.jackson.Jackson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by Ruth on 23/09/2019.
 */
 
public abstract class BaseAdapter {
    protected final ObjectMapper MAPPER = Jackson.newObjectMapper();
    protected ExternalServer externalServer;

    public BaseAdapter(ChikitaniaConfiguration chikitaniaConfiguration, String server) {
        this.externalServer = chikitaniaConfiguration.getExternalSever(server);
    }

    protected HttpResponse doRequestGetTo(String url) throws IOException {
        final HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        Optional<String> tokenOptional = this.getToken();
        tokenOptional.ifPresent(token -> request.setHeader("Api-Token", token));
        return client.execute(request);
    }

    protected HttpResponse doRequestPostTo(String url) throws IOException {
        final HttpClient client = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(url);
        Optional<String> tokenOptional = this.getToken();
        tokenOptional.ifPresent(token -> request.setHeader("Api-Token", token));
        return client.execute(request);
    }

    protected String buildRequestUrl(String path) {
        return String.format("%s/%s",
                this.externalServer.getUrl(), path);
    }

    protected Optional<String> getToken() {
        return Optional.ofNullable(this.externalServer.getKey());
    }

    public <E> E parseJsonToObject(String value, Class<E> type) throws IOException {
        return new ObjectMapper().readValue(value, type);
    }
}
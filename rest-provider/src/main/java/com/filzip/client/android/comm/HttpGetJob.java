package com.filzip.client.android.comm;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;

import java.util.UUID;

/**
 * @author Philipp Engel <philipp@filzip.com>
 */
public abstract class HttpGetJob implements IHttpJob {

    protected final HttpGet request;
    private String id;

    public HttpGetJob(String url) {
        this.request = new HttpGet(url);
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return this.id;
    }

    public HttpUriRequest getRequest() {
        return this.request;
    }

    public abstract void onResponse(HttpResponse response);
}

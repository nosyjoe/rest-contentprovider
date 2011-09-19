package com.filzip.client.android.comm;

import com.filzip.client.android.comm.http.multipart.MultipartEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;

import java.util.UUID;

/**
 * @author Philipp Engel <philipp@filzip.com>
 */
public abstract class AHttpPostJob implements IHttpJob {

    protected final HttpPost request;
    private String id;

    public AHttpPostJob(String url) {
        this.request = new HttpPost(url);
        this.id = UUID.randomUUID().toString();
    }

    public AHttpPostJob(String url, MultipartEntity e) {
        this.request = new HttpPost(url);
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

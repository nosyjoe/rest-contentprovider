package com.filzip.client.android.comm;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;

/**
 * @author Philipp Engel <philipp@filzip.com>
 */
public interface IHttpJob {


    String getId();

    HttpUriRequest getRequest();

    void onResponse(HttpResponse response);
}

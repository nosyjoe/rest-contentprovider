package com.filzip.client.android.comm;

import android.util.Log;
import com.filzip.client.android.MuLog;
import com.filzip.client.android.Setup;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author Philipp Engel <philipp@filzip.com>
 */
public class HttpWorker implements IHttpWorker {

    private HttpClient httpClient;
    private HttpContext localContext;
    private IHttpJob job;
    private WorkerState state;

    public HttpWorker() {
        httpClient = new DefaultHttpClient();
        localContext = new BasicHttpContext();
        this.state = WorkerState.IDLE;
    }

    public HttpWorker(IHttpJob job) {
        this();
        this.setJob(job);
    }

    public IHttpJob getJob() {
        return job;
    }

    public void setJob(IHttpJob job) {
        if (job == null)
            return;

        this.state = WorkerState.NEW_JOB;
        this.job = job;
    }

    public WorkerState getState() {
        return this.state;
    }

    public void run() {
        try {
            this.state = WorkerState.PROCESSING;
            MuLog.i(this, "Starting to work on job: " + this.job + ", id: " + this.job.getId());
            HttpResponse response = httpClient.execute(this.job.getRequest(), localContext);
            this.job.onResponse(response);
            MuLog.i(this, "Finished working on job: " + this.job + ", id: " + this.job.getId());
            this.state = WorkerState.IDLE;
        } catch (IOException e) {
            Log.w(Setup.LOG_TAG, "HttpUtil.get() IOException: " + e.getMessage());
        }
    }

}

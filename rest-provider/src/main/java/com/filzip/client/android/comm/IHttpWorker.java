package com.filzip.client.android.comm;

/**
 * @author Philipp Engel <philipp@filzip.com>
 */
public interface IHttpWorker extends Runnable {

    public enum WorkerState {
        IDLE,
        NEW_JOB,
        PROCESSING;
    }

    void setJob(IHttpJob job);

    WorkerState getState();

}

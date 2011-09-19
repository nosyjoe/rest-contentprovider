package com.filzip.client.android.comm;

import com.filzip.client.android.MuLog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

/**
 * @author Philipp Engel <philipp@filzip.com>
 */
public enum HttpJobQueue implements Runnable {

    INSTANCE;

    private List<IHttpJob> jobs;
    private List<IHttpWorker> worker = new Vector<IHttpWorker>();
    private Boolean shouldRun = Boolean.TRUE;
    private static final long WAIT_PERIOD = 250;

    private HttpJobQueue() {
        this.jobs = Collections.synchronizedList(new ArrayList<IHttpJob>());
        this.worker.add(new HttpWorker());
        this.worker.add(new HttpWorker());
        this.worker.add(new HttpWorker());
        new Thread(this).start();
    }

    public boolean add(IHttpJob iHttpJob) {
        return jobs.add(iHttpJob);
    }

    public void add(int i, IHttpJob iHttpJob) {
        jobs.add(i, iHttpJob);
    }

    public boolean contains(Object o) {
        return jobs.contains(o);
    }

    public IHttpJob get(int i) {
        return jobs.get(i);
    }

    public boolean remove(Object o) {
        return jobs.remove(o);
    }

    public void start() {
        synchronized (this.shouldRun) {
            this.shouldRun = Boolean.TRUE;
        }
        //this.notifyAll();
    }

    public void stop() {
        synchronized (this.shouldRun) {
            this.shouldRun = Boolean.FALSE;
        }
        //this.notifyAll();
    }

    public void run() {
        while (true) {
            synchronized (this) {
                boolean shouldWait = true;
                if (shouldRun && jobs.size() > 0) {

                    // try to find free worker
                    for (int i = 0; i < worker.size(); i++) {
                        IHttpWorker aWorker = worker.get(i);
                        if (aWorker.getState() == IHttpWorker.WorkerState.IDLE) {
                            IHttpJob httpJob = jobs.remove(0);
                            aWorker.setJob(httpJob);
                            new Thread(aWorker).start();
                            break;
                        }
                    }

                    if (jobs.size() > 0)
                        shouldWait = false;
                }


                if (shouldWait) {
                    try {
                        this.wait(WAIT_PERIOD);
                    } catch (InterruptedException e) {
                        MuLog.d(this, "InterruptedException - reason: " + e.getMessage());
                    }
                }
            }
        }
    }

}

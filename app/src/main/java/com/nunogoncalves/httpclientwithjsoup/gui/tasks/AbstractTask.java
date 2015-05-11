package com.nunogoncalves.httpclientwithjsoup.gui.tasks;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by nuno on 11-05-2015.
 */
abstract class AbstractTask<Params, Result> extends AsyncTask<Params, Integer, Result> {

    private ProgressUpdater progressUpdater;

    AbstractTask() {
        super();
    }

    @Override
    protected Result doInBackground(Params... params) {
        Result response;
        final long startTime = System.currentTimeMillis();
        try {
            response = executeTask(params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        final long timeTaken = getTimeTaken(startTime);
        Log.i("Task", "Task took " + String.valueOf(timeTaken) + " ms...");
        return response;
    }

    private long getTimeTaken(long startTime) {
        final long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    protected abstract Result executeTask(Params... params) throws Exception;

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected final void onPreExecute() {
        super.onPreExecute();
        startUpdateProgress();
    }

    @Override
    protected final void onPostExecute(Result result) {
        super.onPostExecute(result);
        dismissUpdateProgress();
    }

    public void setProgressUpdater(ProgressUpdater progressUpdater) {
        this.progressUpdater = progressUpdater;
    }

    private void updateProgress(Integer... values) {
        if (progressUpdater != null && values != null) {
            for (Integer value : values) {
                progressUpdater.setValue(value);
            }
        }
    }

    private void startUpdateProgress() {
        if (progressUpdater != null) {
            progressUpdater.start();
        }
    }

    private void dismissUpdateProgress() {
        if (progressUpdater != null) {
            progressUpdater.dismiss();
        }
    }
}

package com.nunogoncalves.httpclientwithjsoup.gui.tasks;

/**
 * Created by nuno on 11-05-2015.
 */
public interface ProgressUpdater {

    public void start();

    public void setValue(int value);

    public void dismiss();

}

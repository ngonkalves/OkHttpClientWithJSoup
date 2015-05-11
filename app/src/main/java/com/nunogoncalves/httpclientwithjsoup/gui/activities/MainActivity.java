package com.nunogoncalves.httpclientwithjsoup.gui.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nunogoncalves.httpclientwithjsoup.R;
import com.nunogoncalves.httpclientwithjsoup.gui.tasks.ProgressUpdater;
import com.nunogoncalves.httpclientwithjsoup.gui.tasks.TaskFactory;

import java.util.concurrent.ExecutionException;


public class MainActivity extends ActionBarActivity {

    private final String TAG = MainActivity.class.getSimpleName();

    private final TaskFactory taskFactory = new TaskFactory();

    private EditText urlEditText;

    private EditText cssQueryEditText;

    private Button executeButton;

    private TextView responseTextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        urlEditText = (EditText) findViewById(R.id.urlEditText);
        cssQueryEditText = (EditText) findViewById(R.id.cssQueryEditText);
        executeButton = (Button) findViewById(R.id.executeButton);
        responseTextview = (TextView) findViewById(R.id.responseTextview);

        // add scrolling support to textView
        responseTextview.setMovementMethod(new ScrollingMovementMethod());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onExecuteClick(View view) throws ExecutionException, InterruptedException {

        showResponse("Loading...");

        final String url = getUrl();

        final String cssQuery = getCssQuery();

        Log.i(TAG, "Url: " + url + " CSSQuery: " + cssQuery);

        ProgressUpdater progressUpdater = new CustomProgressUpdater(getProgressDialog("Loading..."));

        final String html = taskFactory.createCustomHttpCallTask(url, progressUpdater).execute().get();

        Log.d(TAG, "Html: " + html);

        final String parsedOuput = taskFactory.createCustomHtmlParserTask(html, cssQuery, progressUpdater).execute().get();

        Log.d(TAG, "ParsedOuput: " + parsedOuput);

        showResponse(parsedOuput);
    }

    private ProgressDialog getProgressDialog(String message) {
        final ProgressDialog progressDialog = new ProgressDialog(this);

        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        progressDialog.setMessage(message);

        return progressDialog;
    }

    private void showResponse(String response) {
        TextView textView = (TextView) findViewById(R.id.responseTextview);
        textView.setText(response);
    }

    private String getUrl() {
        return this.urlEditText.getText().toString();
    }

    private String getCssQuery() {
        return this.cssQueryEditText.getText().toString();
    }

    private static class CustomProgressUpdater implements ProgressUpdater {

        final ProgressDialog progressDialog;

        CustomProgressUpdater(final ProgressDialog progressDialog) {
            super();
            this.progressDialog = progressDialog;
        }

        @Override
        public void start() {
            progressDialog.show();
        }

        @Override
        public void setValue(int value) {
            progressDialog.setProgress(value);
        }

        @Override
        public void dismiss() {
            progressDialog.dismiss();
        }
    }
}

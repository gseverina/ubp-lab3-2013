package com.example.asyncTaskSample;

import com.example.asyncTaskSample.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;

public class AsyncTaskActivity extends Activity {
	private Button button;
	private ProgressBar progressBar;
	private Boolean toggle = false;
	private UpdateProgressAsyncTask updateAsync;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		button = (Button) findViewById(R.id.btnStart);
		progressBar = (ProgressBar) findViewById(R.id.progressBar);

		button.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View arg0) {
				if (toggle) {
					updateAsync.cancel(true);
				} else {
					updateAsync = new UpdateProgressAsyncTask();
					updateAsync.execute();
				}
			}
		});
	}

	public class UpdateProgressAsyncTask extends AsyncTask<Void, Integer, Void> {
		private int progress;

		@Override
		protected void onPreExecute() {
			button.setText(R.string.btn_cancel);
			toggle = true;
			progress = 0;
		}

		@Override
		protected Void doInBackground(Void... params) {
			while (progress < 100) {
				if (isCancelled()) {
					break;
				}
				progress++;
				publishProgress(progress);
				SystemClock.sleep(100);
			}
			return null;
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			progressBar.setProgress(values[0]);
		}

		@Override
		protected void onPostExecute(Void result) {
			button.setText(R.string.btn_finish);
			toggle = false;
			button.setClickable(true);
		}

		@Override
		protected void onCancelled() {
			button.setText(R.string.btn_start);
			toggle = false;
			progress = 0;
			progressBar.setProgress(0);
		}
	}
}
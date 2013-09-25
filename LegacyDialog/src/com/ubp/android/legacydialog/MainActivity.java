package com.ubp.android.legacydialog;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class MainActivity extends Activity {

	private Button btnAlert;
	private Button btnProgress;
	private Button btnShowDialog;

	private static final int DIALOG_TYPE_ALERT = 0;
	private static final int DIALOG_TYPE_PROGRESS = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnAlert = (Button) findViewById(R.id.btn_show_alert);
		btnProgress = (Button) findViewById(R.id.btn_show_progress);
		btnShowDialog = (Button) findViewById(R.id.btn_show_dialog);

		btnAlert.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialog(DIALOG_TYPE_ALERT);
			}

		});

		btnProgress.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialog(DIALOG_TYPE_PROGRESS);
			}

		});

		btnShowDialog.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				show();
			}

		});
	}

	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {
		super.onPrepareDialog(id, dialog);
	}

	@Override
	protected Dialog onCreateDialog(int id) {

		return (id == DIALOG_TYPE_ALERT) ? this.createAlertDialog() : this
				.createProgressDialog();
	}

	private Dialog createAlertDialog() {
		final AlertDialog.Builder builder = new AlertDialog.Builder(this)
				.setIcon(R.drawable.ic_launcher).setTitle("Alert Dialog")
				.setPositiveButton("Aceptar", null)
				.setMessage("Este es un Alert Dialog");

		final Dialog dialog = builder.create();

		dialog.setOnDismissListener(new OnDismissListener() {

			@Override
			public void onDismiss(DialogInterface dialog) {
				Toast.makeText(MainActivity.this, "Dismiss!",
						Toast.LENGTH_SHORT).show();
			}

		});

		return dialog;
	}

	private Dialog createProgressDialog() {
		final ProgressDialog dialog = new ProgressDialog(this);
		dialog.setTitle("Configuración");
		dialog.setMessage("Cargando...");
		dialog.setCancelable(true);

		return dialog;
	}

	private void show() {
		final Dialog dialog = new AlertDialog.Builder(this)
				.setIcon(R.drawable.ic_launcher).setTitle("Alert Dialog")
				.setPositiveButton("Aceptar", null)
				.setMessage("Este es un Alert Dialog sin Contexto de Activity")
				.create();

		dialog.show();
	}
}

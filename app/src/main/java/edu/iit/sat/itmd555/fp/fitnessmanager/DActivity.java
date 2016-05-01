package edu.iit.sat.itmd555.fp.fitnessmanager;

/**
 * Created by lisiling on 4/24/16.-----------PreferenceActivity
 */

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.View.OnClickListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import edu.iit.sat.itmd555.fp.fitnessmanager.model.User;


public class DActivity extends AppCompatActivity {

    private Button btnchangepass;
    private Button save_settings_changes;
    private TextView passview;
    private RadioButton rb1,rb2;
    final Context context = this;

    private SqlHelper db;

    private User usr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system);

        db = SqlHelper.getInstance(getApplicationContext());

        usr = db.getUser(1);

        btnchangepass = (Button) findViewById(R.id.btn_sys_pass);
        save_settings_changes = (Button) findViewById(R.id.btn_save_settings);
        passview = (TextView) findViewById(R.id.sys_pass_view);
        passview.setText(usr.getPassword());
        rb1 = (RadioButton) findViewById(R.id.radioButton_metrics);
        rb2 = (RadioButton) findViewById(R.id.radioButton_imperial);

        if(Objects.equals(usr.getMetrics(), "Metrics")){
            rb1.setChecked(true);
            rb2.setChecked(false);
        } else if(Objects.equals(usr.getMetrics(), "Imperial")){
            rb1.setChecked(false);
            rb2.setChecked(true);

        } else { // default situation
            rb1.setChecked(true);
            rb2.setChecked(false);
        }

        save_settings_changes.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(rb1.isChecked()){
                    usr.setMetrics("Metrics");
                } else {
                    usr.setMetrics("Imperial");
                }
                usr.setPassword(passview.getText().toString());
                db.updateUser(usr);
            }
        });

        btnchangepass.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.custom_dialog, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                alertDialogBuilder.setView(promptsView);

                final EditText tohide = (EditText) promptsView.findViewById(R.id.dialog_input);
                tohide.setVisibility(View.GONE);
                final EditText userInput = (EditText) promptsView.findViewById(R.id.pswd_input);
                userInput.setVisibility(View.VISIBLE);

                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        passview.setText(userInput.getText());
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_c, menu);
        return true;
    }
}

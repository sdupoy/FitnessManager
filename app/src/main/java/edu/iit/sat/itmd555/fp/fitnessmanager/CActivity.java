package edu.iit.sat.itmd555.fp.fitnessmanager;

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
import android.widget.Spinner;
import android.widget.TextView;
import android.view.View.OnClickListener;
import java.util.ArrayList;
import java.util.List;

public class CActivity extends AppCompatActivity {

    Spinner sp1, sp2;
    TextView view1,view2;
    List<String> list1,list2;

    private Button btnsave, btngoalsave;
    private EditText etNum;
    private TextView view3;

    private TextView heightResult,weightResult,ageResult,sexResult;
    Button btn_dia_height, btn_dia_weight, btn_dia_age, btn_dia_sex;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        heightResult = (TextView) findViewById(R.id.height_result);
        weightResult = (TextView) findViewById(R.id.weight_result);
        ageResult = (TextView) findViewById(R.id.age_result);
        sexResult = (TextView) findViewById(R.id.sex_result);
        btn_dia_height = (Button) findViewById(R.id.btn_dia_height);
        btn_dia_weight = (Button) findViewById(R.id.btn_dia_weight);
        btn_dia_age = (Button) findViewById(R.id.btn_dia_age);
        btn_dia_sex = (Button) findViewById(R.id.btn_dia_sex);

        view1 =(TextView) findViewById(R.id.spinnerTextgoal);
        view2 =(TextView) findViewById(R.id.spinnerTextfrequency);
        sp1 = (Spinner) findViewById(R.id.spinner_goal);
        sp2 = (Spinner) findViewById(R.id.spinner_frequency);
        btnsave = (Button) findViewById(R.id.btn_num_save);
        btngoalsave = (Button) findViewById(R.id.btn_goal_save);
        etNum = (EditText) findViewById(R.id.input_goal_number);
        view3 = (TextView) findViewById(R.id.spinner_view3);


        list1 = new ArrayList<String>();
        list1.add("Steps");
        list1.add("Running");
        list1.add("Swimming");
        list1.add("Push Up");
        list1.add("Sit Up");
        list1.add("Planking");
        list1.add("Bicycling");


        list2 = new ArrayList<String>();
        list2.add("Per Day");
        list2.add("Per Week");
        list2.add("Per Month");

        ArrayAdapter<String> adp1 = new ArrayAdapter<String>
                (this, android.R.layout.simple_dropdown_item_1line, list1);
        adp1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        ArrayAdapter<String> adp2 = new ArrayAdapter<String>
                (this, android.R.layout.simple_dropdown_item_1line, list2);
        adp2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        sp1.setAdapter(adp1);
        sp2.setAdapter(adp2);

        sp1.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                switch(arg2) {
                    case 0 :
                        view1.setText("Steps");
                        break;
                    case 1 :
                        view1.setText("Running");
                        break;
                    case 2 :
                        view1.setText("Swimming");
                        break;
                    case 3 :
                        view1.setText("Push Up");
                        break;
                    case 4 :
                        view1.setText("Sit Up");
                        break;
                    case 5 :
                        view1.setText("Planking");
                        break;
                    case 6 :
                        view1.setText("Bicycling");
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        sp2.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                switch(arg2) {
                    case 0 :
                        view2.setText("Per Day");
                        break;
                    case 1 :
                        view2.setText("Per Week");
                        break;
                    case 2 :
                        view2.setText("Per Month");
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        btnsave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v){
                // TODO Auto-generated method stub
                view3.setText(etNum.getText().toString());
            }
        });

        btngoalsave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
            }
        });

        btn_dia_height.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.custom_dialog, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                alertDialogBuilder.setView(promptsView);
                final EditText userInput = (EditText) promptsView.findViewById(R.id.dialog_input);

                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        heightResult.setText(userInput.getText());
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

        btn_dia_weight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.custom_dialog, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                alertDialogBuilder.setView(promptsView);
                final EditText userInput = (EditText) promptsView.findViewById(R.id.dialog_input);

                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        weightResult.setText(userInput.getText());
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

        btn_dia_age.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.custom_dialog, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                alertDialogBuilder.setView(promptsView);
                final EditText userInput = (EditText) promptsView.findViewById(R.id.dialog_input);

                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        ageResult.setText(userInput.getText());
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

        btn_dia_sex.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.custom_dialog, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                alertDialogBuilder.setView(promptsView);
                final EditText userInput = (EditText) promptsView.findViewById(R.id.dialog_input);

                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        sexResult.setText(userInput.getText());
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

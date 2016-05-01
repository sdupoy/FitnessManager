package edu.iit.sat.itmd555.fp.fitnessmanager;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.View.OnClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import edu.iit.sat.itmd555.fp.fitnessmanager.model.Goal;
import edu.iit.sat.itmd555.fp.fitnessmanager.model.User;

public class CActivity extends AppCompatActivity{

    private Spinner sp1, sp2;
    private TextView spinnerTextgoal,spinnerTextfrequency, spinnerGoalNumber;
    List<String> list1,list2;

    private SqlHelper db;

    private Button btnsave, btngoalsave;
    private EditText etNum;

    private TextView heightResult,weightResult,ageResult;
    private Button btn_dia_height, btn_dia_weight, btn_dia_age, btn_dia_sex;
    private RadioButton btn_sex_male, btn_sex_female;

    final Context context = this;

    private List<Goal> goals;

    private ListView listContent;
    private GoalAdapter goalAdapter;

    private User usr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        db = SqlHelper.getInstance(getApplicationContext());

        heightResult = (TextView) findViewById(R.id.height_result);
        weightResult = (TextView) findViewById(R.id.weight_result);
        ageResult = (TextView) findViewById(R.id.age_result);
        btn_dia_height = (Button) findViewById(R.id.btn_dia_height);
        btn_dia_weight = (Button) findViewById(R.id.btn_dia_weight);
        btn_dia_age = (Button) findViewById(R.id.btn_dia_age);
        btn_sex_male = (RadioButton) findViewById(R.id.radioButtonMale);
        btn_sex_female = (RadioButton) findViewById(R.id.radioButtonFemale);
        btnsave = (Button) findViewById(R.id.btn_save_perso);

        spinnerTextgoal =(TextView) findViewById(R.id.spinnerTextgoal);
        spinnerTextfrequency =(TextView) findViewById(R.id.spinnerTextfrequency);
        sp1 = (Spinner) findViewById(R.id.spinner_goal);
        sp2 = (Spinner) findViewById(R.id.spinner_frequency);
        btngoalsave = (Button) findViewById(R.id.btn_goal_save);
        etNum = (EditText) findViewById(R.id.input_goal_number);
        spinnerGoalNumber = (TextView) findViewById(R.id.spinner_view3);

        usr = db.getUser(1);

        heightResult.setText(usr.getHeight());
        weightResult.setText(usr.getWeight());
        ageResult.setText(String.valueOf(usr.getAge()));
        if(Objects.equals(usr.getGender(), "Male")){
            btn_sex_male.setChecked(true);
            btn_sex_female.setChecked(false);
        } else if (Objects.equals(usr.getGender(), "Female")){
            btn_sex_male.setChecked(false);
            btn_sex_female.setChecked(true);
        } else { // default situation
            btn_sex_male.setChecked(true);
            btn_sex_female.setChecked(false);
        }

        list1 = new ArrayList<String>();
        list1.add("Steps");list1.add("Running");list1.add("Swimming");list1.add("Push Up");list1.add("Sit Up");list1.add("Planking");list1.add("Bicycling");


        list2 = new ArrayList<String>();
        list2.add("Per Day");list2.add("Per Week");list2.add("Per Month");

        ArrayAdapter<String> adp1 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, list1);
        adp1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        ArrayAdapter<String> adp2 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, list2);
        adp2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        sp1.setAdapter(adp1);
        sp2.setAdapter(adp2);

        sp1.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                switch(arg2) {
                    case 0 :
                        spinnerTextgoal.setText("Steps");
                        break;
                    case 1 :
                        spinnerTextgoal.setText("Running");
                        break;
                    case 2 :
                        spinnerTextgoal.setText("Swimming");
                        break;
                    case 3 :
                        spinnerTextgoal.setText("Push Up");
                        break;
                    case 4 :
                        spinnerTextgoal.setText("Sit Up");
                        break;
                    case 5 :
                        spinnerTextgoal.setText("Planking");
                        break;
                    case 6 :
                        spinnerTextgoal.setText("Bicycling");
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
                        spinnerTextfrequency.setText("Per Day");
                        break;
                    case 1 :
                        spinnerTextfrequency.setText("Per Week");
                        break;
                    case 2 :
                        spinnerTextfrequency.setText("Per Month");
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        listContent = (ListView) findViewById(R.id.goalslist);
        goals = db.getAllGoalsByUser(1);

        listContent.destroyDrawingCache();
        listContent.setVisibility(ListView.INVISIBLE);
        listContent.setVisibility(ListView.VISIBLE);
        goalAdapter= new GoalAdapter(this, goals);
        listContent.setAdapter(goalAdapter);


        listContent.setLongClickable(true);
        listContent.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, final int pos, long id) {
                Log.d("long clicked","pos: " + pos);
                android.app.AlertDialog diaBox = AskOption(goals.get(pos));
                diaBox.show();
                return true;
            }
        });

        btnsave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                usr.setHeight(heightResult.getText().toString());
                usr.setWeight(weightResult.getText().toString());
                usr.setAge(Integer.parseInt(ageResult.getText().toString()));
                db.updateUser(usr);
            }
        });

        btngoalsave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                int targetNumber = Integer.parseInt(etNum.getText().toString());
                String targetName = spinnerTextgoal.getText().toString();
                String targetFrequency = spinnerTextfrequency.getText().toString();
                Goal goal = new Goal();
                goal.setIdUser(1);
                goal.setTargetNumber(targetNumber);
                goal.setTargetName(targetName);
                goal.setTargetFrequency(targetFrequency);
                db.addGoal(goal);
                goals = db.getAllGoalsByUser(1);
                listContent.destroyDrawingCache();
                goalAdapter = new GoalAdapter(getApplicationContext(), goals);
                listContent.setAdapter(goalAdapter);
                listContent.setVisibility(ListView.INVISIBLE);
                listContent.setVisibility(ListView.VISIBLE);
                goalAdapter.updateAdapter(goals);
                etNum.setText("");
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_c, menu);
        return true;
    }

    private android.app.AlertDialog AskOption(final Goal goal)
    {
        android.app.AlertDialog myQuittingDialogBox =new android.app.AlertDialog.Builder(this)

                .setTitle("Delete")
                .setMessage("Do you want to delete all details of this day ?")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        db.deleteGoal(goal);
                        goals = db.getAllGoalsByUser(1);
                        listContent.destroyDrawingCache();
                        goalAdapter = new GoalAdapter(getApplicationContext(), goals);
                        listContent.setAdapter(goalAdapter);
                        listContent.setVisibility(ListView.INVISIBLE);
                        listContent.setVisibility(ListView.VISIBLE);
                        goalAdapter.updateAdapter(goals);
                    }

                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        return myQuittingDialogBox;

    }
}

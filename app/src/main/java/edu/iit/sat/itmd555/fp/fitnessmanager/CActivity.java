package edu.iit.sat.itmd555.fp.fitnessmanager;

/**
 * Created by lisiling on 4/24/16.
 */

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
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

public class CActivity extends Activity {

//    Spinner sp1, sp2;
//    TextView view1,view2;
//    List<String> list1,list2;
//
//    private Button btnsave;
//    private EditText etNum;
//    private TextView view3;
//
//    TextView heightResult;
//    Button btn_dia_height;
//    final Context context = this;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_scrolling);
//
//        view1 =(TextView) findViewById(R.id.spinnerTextgoal);
//        view2 =(TextView) findViewById(R.id.spinnerTextfrequency);
//        sp1 = (Spinner) findViewById(R.id.spinner_goal);
//        sp2 = (Spinner) findViewById(R.id.spinner_frequency);
//        btnsave = (Button) findViewById(R.id.btn_num_save);
//        etNum = (EditText) findViewById(R.id.input_goal_number);
//        view3 = (TextView) findViewById(R.id.spinner_view3);
//        heightResult = (TextView) findViewById(R.id.height_result);
//        btn_dia_height = (Button) findViewById(R.id.btn_dia_height);
//
//        list1 = new ArrayList<String>();
//        list1.add("Steps");
//        list1.add("Running");
//        list1.add("Swimming");
//        list1.add("Push Up");
//
//        list2 = new ArrayList<String>();
//        list2.add("/Day");
//        list2.add("/Week");
//        list2.add("/Month");
//
//        ArrayAdapter<String> adp1 = new ArrayAdapter<String>
//                (this, android.R.layout.simple_dropdown_item_1line, list1);
//        adp1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
//        ArrayAdapter<String> adp2 = new ArrayAdapter<String>
//                (this, android.R.layout.simple_dropdown_item_1line, list2);
//        adp2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
//
//        sp1.setAdapter(adp1);
//        sp2.setAdapter(adp2);
//
//        sp1.setOnItemSelectedListener(new OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//                // TODO Auto-generated method stub
//                switch(arg2) {
//                    case 0 :
//                        view1.setText("Steps");
//                        break;
//                    case 1 :
//                        view1.setText("Running");
//                        break;
//                    case 2 :
//                        view1.setText("Swimming");
//                        break;
//                    case 3 :
//                        view1.setText("Push Up");
//                        break;
//                    case 4 :
//                        view1.setText("Sit Up");
//                        break;
//                }
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> arg0) {
//                // TODO Auto-generated method stub
//            }
//        });
//
//        sp2.setOnItemSelectedListener(new OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//                // TODO Auto-generated method stub
//                switch(arg2) {
//                    case 0 :
//                        view2.setText("/Day");
//                        break;
//                    case 1 :
//                        view2.setText("/Week");
//                        break;
//                    case 2 :
//                        view2.setText("/Month");
//                        break;
//                }
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> arg0) {
//                // TODO Auto-generated method stub
//            }
//        });
//
//        btnsave.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v){
//                // TODO Auto-generated method stub
//                view3.setText(etNum.getText().toString());
//            }
//        });
//
////        btn_dia_height.setOnClickListener(new OnClickListener() {
////            @Override
////            public void onClick(View arg0) {
////            }
////        });
//
//    }
//
//    private Dialog onCreateDialog (Bundle savedInstanceState){
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        LayoutInflater li = LayoutInflater.from(context);
//        View promptsView = li.inflate(R.layout.custom_dialog, null);
//        builder.setView(promptsView);
//        final EditText userInput;
//        userInput = (EditText) promptsView.findViewById(R.id.dialog_input);
//
//        builder
////                .setCancelable(false)
//                .setPositiveButton("OK",
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int id) {
//                                heightResult.setText(userInput.getText().toString());
//                            }
//                        })
//                .setNegativeButton("Cancel",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                dialog.dismiss();
//                            }
//                        });
//        return builder.create();
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
//        return true;
//    }

}


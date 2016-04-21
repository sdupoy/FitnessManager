package edu.iit.sat.itmd555.fp.fitnessmanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.view.View;
import android.widget.FrameLayout;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.Window;

public class HomePgActivity extends AppCompatActivity implements View.OnClickListener{

    //UI Object
    private TextView txt_me;
    private TextView txt_history;
    private TextView txt_newact;
    private TextView txt_setting;
    private FrameLayout ly_content;

    //Fragment Object
    private MyFragment fg1,fg2,fg3,fg4;
    private FragmentManager fManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        fManager = getFragmentManager();
        bindViews();
        txt_me.performClick();   //like fisrt click, enter at first like "me"
    }

    //UI
    private void bindViews() {
        txt_me = (TextView) findViewById(R.id.txt_me);
        txt_history = (TextView) findViewById(R.id.txt_history);
        txt_newact = (TextView) findViewById(R.id.txt_newact);
        txt_setting = (TextView) findViewById(R.id.txt_setting);
        ly_content = (FrameLayout) findViewById(R.id.ly_content);

        txt_me.setOnClickListener(this);
        txt_history.setOnClickListener(this);
        txt_newact.setOnClickListener(this);
        txt_setting.setOnClickListener(this);
    }

    //reset all txt
    private void setSelected(){
        txt_me.setSelected(false);
        txt_history.setSelected(false);
        txt_newact.setSelected(false);
        txt_setting.setSelected(false);
    }

    //hide all fragment
    private void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(fg1 != null)fragmentTransaction.hide(fg1);
        if(fg2 != null)fragmentTransaction.hide(fg2);
        if(fg3 != null)fragmentTransaction.hide(fg3);
        if(fg4 != null)fragmentTransaction.hide(fg4);
    }


    @Override
    public void onClick(View v) {
        FragmentTransaction fTransaction = fManager.beginTransaction();
        hideAllFragment(fTransaction);
        switch (v.getId()){
            case R.id.txt_me:
                setSelected();
                txt_me.setSelected(true);
                if(fg1 == null){
//                    fg1 = new MyFragment("fisrt Fragment");
                    fTransaction.add(R.id.ly_content,fg1);
                }else{
                    fTransaction.show(fg1);
                }
                break;
            case R.id.txt_history:
                setSelected();
                txt_history.setSelected(true);
                if(fg2 == null){
//                    fg2 = new MyFragment("second Fragment");
                    fTransaction.add(R.id.ly_content,fg2);
                }else{
                    fTransaction.show(fg2);
                }
                break;
            case R.id.txt_newact:
                setSelected();
                txt_newact.setSelected(true);
                if(fg3 == null){
//                    fg3 = new MyFragment("third Fragment");
                    fTransaction.add(R.id.ly_content,fg3);
                }else{
                    fTransaction.show(fg3);
                }
                break;
            case R.id.txt_setting:
                setSelected();
                txt_setting.setSelected(true);
                if(fg4 == null){
//                    fg4 = new MyFragment("forth Fragment");
                    fTransaction.add(R.id.ly_content,fg4);
                }else{
                    fTransaction.show(fg4);
                }
                break;
        }
        fTransaction.commit();
    }
}


//public class HomePgActivity extends Activity {
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_homepg);
//    }
//}

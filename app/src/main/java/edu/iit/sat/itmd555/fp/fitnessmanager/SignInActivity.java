package edu.iit.sat.itmd555.fp.fitnessmanager;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class SignInActivity extends Activity {

    private TextView link_signup;
    private SqlHelper db;
    private EditText Email, password;
    private CheckBox rememberPass;
    private Button btnSignIn;
    private String EmailValue, passwordValue;
    private SharedPreferences sp;
    private SharedPreferences.Editor spEditor;
    private Boolean saveLogin;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_screen);
        db = SqlHelper.getInstance(getApplicationContext());

        //get objects
        sp = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        spEditor = sp.edit();
        rememberPass = (CheckBox) findViewById(R.id.rmPass);
        Email = (EditText) findViewById(R.id.etEmail);
        password = (EditText) findViewById(R.id.etPass);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        link_signup = (TextView) findViewById(R.id.link_signup);


        saveLogin = sp.getBoolean("saveLogin", false);
        if (saveLogin == true) {
            Email.setText(sp.getString("Email", ""));
            password.setText(sp.getString("password", ""));
            rememberPass.setChecked(true);
        }

            // set OnclickListener  useremail：li@123.com password：123
        btnSignIn.setOnClickListener(new OnClickListener() {

                public void onClick(View v) {
                    EmailValue = Email.getText().toString();
                    passwordValue = password.getText().toString();
                    if (Email.getText().length() != 0 || password.getText().length() != 0) {
                        if (db.getUserByEmail(EmailValue) != null) {
                            if (Objects.equals(db.getUserByEmail(EmailValue).getPassword(), passwordValue)) {
                                Toast.makeText(SignInActivity.this, "SignIn successful!", Toast.LENGTH_SHORT).show();

                                //until successfully sign in and check box is clicked ----then save users information
                                if (rememberPass.isChecked()) {
                                    //remember password and login email
                                    spEditor.putBoolean("saveLogin", true);
                                    spEditor.putString("Email", EmailValue);
                                    spEditor.putString("password", db.getUserByEmail(EmailValue).getPassword());
                                    spEditor.commit();
                                }else{
                                    spEditor.clear();
                                    spEditor.commit();
                                }
                                //switch page
                                Intent i = new Intent(getApplicationContext(), MainTabActivity.class);
                                startActivity(i);
                            } else if (EmailValue.equals("li@123.com") && passwordValue.equals("123")) {
                                Toast.makeText(SignInActivity.this, "SignIn successful!", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(), MainTabActivity.class);
                                startActivity(i);
                                //finish();

                            } else {

                                Toast.makeText(SignInActivity.this, "Email or Password wrong! Please try again!", Toast.LENGTH_LONG).show();
                            }

                        }

                    } else {

                        Toast.makeText(SignInActivity.this, "Email or Password empty! Please try again!", Toast.LENGTH_LONG).show();
                    }

                }
        });

        //set OnclickListener for move to signup page
        link_signup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(i);
            }
        });
    }
}



package edu.iit.sat.itmd555.fp.fitnessmanager;

import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import edu.iit.sat.itmd555.fp.fitnessmanager.model.ActivityDistance;
import edu.iit.sat.itmd555.fp.fitnessmanager.model.ActivitySport;
import edu.iit.sat.itmd555.fp.fitnessmanager.model.User;

/**
 * Created by Simon on 4/26/2016.
 */
public class AddDistanceActivity extends Activity {

    private SqlHelper db;
    private Button addSportDistActivity;
    private TextView distUnits;
    private User usr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_distance_activity);

        db = SqlHelper.getInstance(getApplicationContext());
        usr = db.getUser(1);
        addSportDistActivity = (Button) findViewById(R.id.addDistActivity);

        distUnits = (TextView) findViewById(R.id.distUnits);
        distUnits.setText(usr.getMetrics());

        addSportDistActivity.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                // Create a new distance activity and add it the user input
                ActivityDistance actDistance = new ActivityDistance();
                EditText distanceInput = (EditText) findViewById(R.id.distanceInput);
                // Units to set distance for the activity depending on the user preference !!!
                if(distanceInput.getText().length()!=0){
                    actDistance.setDistanceKms(distanceInput.getText().toString());
                    actDistance.setDistanceMiles(distanceInput.getText().toString());
                    ActivitySport retrAct = db.getActivityJustCreated();
                    actDistance.setIdActivity(retrAct.getId());
                    db.updateActivity(retrAct, retrAct.getDurationHours(), retrAct.getDurationMinutes(), retrAct.getDurationSeconds(), retrAct.getDate(), retrAct.getFeedback(), retrAct.getType(), 0, retrAct.getTitle());
                    db.addActivityDistance(actDistance);
                    Toast.makeText(getApplicationContext(), "Activity saved !", Toast.LENGTH_LONG).show();

                    Intent i = new Intent(getApplicationContext(), MainTabActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(), "Please input a distance !", Toast.LENGTH_LONG).show();

                }


            }
        });
    }
}

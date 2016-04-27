package edu.iit.sat.itmd555.fp.fitnessmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import edu.iit.sat.itmd555.fp.fitnessmanager.model.ActivitySport;
import edu.iit.sat.itmd555.fp.fitnessmanager.model.ActivityWorkout;

/**
 * Created by Simon on 4/26/2016.
 */
public class AddWorkoutActivity extends Activity {

    private SqlHelper db;
    private Button addWkoutActivity;
    private Button addMoreWkout;
    private EditText wkoutType;
    private EditText wkoutNb;
    private ListView wkoutListView;

    private ActivitySport retrAct;
    private ActivityWorkout actWorkout;
    private List<ActivityWorkout> workouts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_workout_activity);

        db = SqlHelper.getInstance(getApplicationContext());
        addWkoutActivity = (Button) findViewById(R.id.addWkoutActivity);
        addMoreWkout = (Button) findViewById(R.id.addMoreWkout);

        addWkoutActivity.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            // Create a new workout activity and add it the user input
            actWorkout = new ActivityWorkout();
            wkoutType = (EditText) findViewById(R.id.wkoutTypeInput);
            wkoutNb = (EditText) findViewById(R.id.wkoutNbInput);
            retrAct = db.getActivityJustCreated();
            if(db.getWorkoutsByActivity(retrAct.getId()).isEmpty()){
                if(wkoutType.getText().length()!=0 && wkoutNb.getText().length()!=0){
                    actWorkout.setTypeOfRep(wkoutType.getText().toString());
                    actWorkout.setNbOfRep(Integer.parseInt(wkoutNb.getText().toString()));
                    actWorkout.setIdActivity(retrAct.getId());
                    db.addActivityWorkout(actWorkout);
                }
                db.updateActivity(retrAct, retrAct.getDurationHours(), retrAct.getDurationMinutes(), retrAct.getDurationSeconds(), retrAct.getDate(), retrAct.getFeedback(), retrAct.getType(), 0, retrAct.getTitle());

                Intent i = new Intent(getApplicationContext(), MainTabActivity.class);
                startActivity(i);
            } else {
                Toast.makeText(getApplicationContext(), "Some input is needed !", Toast.LENGTH_LONG).show();
            }
            }
        });

        addMoreWkout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                retrAct = db.getActivityJustCreated();

                // Create a new workout activity and add it the user input
                actWorkout = new ActivityWorkout();
                //EditText wkoutTitle = (EditText) findViewById(R.id.wkoutTitleInput);
                wkoutType = (EditText) findViewById(R.id.wkoutTypeInput);
                wkoutNb = (EditText) findViewById(R.id.wkoutNbInput);
                if(wkoutType.getText().length()!=0 && wkoutNb.getText().length()!=0){
                    actWorkout.setTypeOfRep(wkoutType.getText().toString());
                    actWorkout.setNbOfRep(Integer.parseInt(wkoutNb.getText().toString()));
                    actWorkout.setIdActivity(retrAct.getId());
                    db.addActivityWorkout(actWorkout);
                    Toast.makeText(getApplicationContext(), "Workout added !", Toast.LENGTH_LONG).show();
                    wkoutType.setText("");
                    wkoutNb.setText("");
                }


                wkoutListView = (ListView) findViewById(R.id.workoutlist);
                wkoutListView.destroyDrawingCache();
                wkoutListView.setVisibility(ListView.INVISIBLE);
                wkoutListView.setVisibility(ListView.VISIBLE);
                workouts = db.getWorkoutsByActivity(retrAct.getId());
                //get data from the table by the ListAdapter
                WorkoutAdapter customAdapter = new WorkoutAdapter(getApplicationContext(),  workouts);
                wkoutListView.setAdapter(customAdapter);

            }
        });


    }
}

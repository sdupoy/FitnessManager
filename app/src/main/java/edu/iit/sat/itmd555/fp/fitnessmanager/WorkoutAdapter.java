package edu.iit.sat.itmd555.fp.fitnessmanager;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import edu.iit.sat.itmd555.fp.fitnessmanager.model.ActivityWorkout;
import edu.iit.sat.itmd555.fp.fitnessmanager.model.Step;

/**
 * Created by Simon on 4/26/2016.
 */
public class WorkoutAdapter extends BaseAdapter {

    private ImageButton deleteWorkout;
    private TextView repType;
    private TextView repNb;

    private Context mContext;
    private LayoutInflater mInflator;
    private List<ActivityWorkout> mListWorkout;

    private SqlHelper db;

    public WorkoutAdapter(Context c, List<ActivityWorkout> workouts) {
        mContext = c;
        mInflator = (LayoutInflater)
                mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mListWorkout = workouts;
    }

    @Override
    public int getCount() {
        return mListWorkout.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        db = new SqlHelper(mContext);

        if(convertView == null) {
            convertView = mInflator.inflate(R.layout.workout_list_row, parent,
                    false);
        }

        repType = (TextView) convertView.findViewById(R.id.repType);
        repType.setText(mListWorkout.get(position).getTypeOfRep());

        repNb = (TextView) convertView.findViewById(R.id.repNb);
        repNb.setText(String.valueOf(mListWorkout.get(position).getNbOfRep()));

        //Do the button delete
        deleteWorkout = (ImageButton) convertView.findViewById(R.id.deleteWorkout);
        deleteWorkout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                db.deleteWorkoutActivity(mListWorkout.get(position).getId());
                mListWorkout = db.getWorkoutsByActivity(mListWorkout.get(position).getIdActivity());
                updateAdapter(mListWorkout);
            }
        });

        return convertView;
    }

    public void updateAdapter(List<ActivityWorkout> arrylst) {
        this.mListWorkout= arrylst;
        //and call notifyDataSetChanged
        notifyDataSetChanged();
    }
}

package edu.iit.sat.itmd555.fp.fitnessmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import edu.iit.sat.itmd555.fp.fitnessmanager.model.Goal;

/**
 * Created by Simon on 4/30/2016.
 */
public class GoalAdapter extends BaseAdapter {

    private TextView goalType;
    private TextView goalNb;
    private TextView goalFreq;

    private Context mContext;
    private LayoutInflater mInflator;
    private List<Goal> mListGoal;

    private SqlHelper db;

    public GoalAdapter(Context c, List<Goal> goals) {
        mContext = c;
        mInflator = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mListGoal = goals;
    }

    @Override
    public int getCount() {
        return mListGoal.size();
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

        db = SqlHelper.getInstance(mContext);

        if(convertView == null) {
            convertView = mInflator.inflate(R.layout.goal_list_row, parent, false);
        }

        goalNb = (TextView) convertView.findViewById(R.id.goalNb);
        goalNb.setText(String.valueOf(mListGoal.get(position).getTargetNumber()));

        goalType = (TextView) convertView.findViewById(R.id.goalType);
        goalType.setText(mListGoal.get(position).getTargetName());

        goalFreq = (TextView) convertView.findViewById(R.id.goalFreq);
        goalFreq.setText(mListGoal.get(position).getTargetFrequency());

        return convertView;
    }

    public void updateAdapter(List<Goal> arrylst) {
        this.mListGoal= arrylst;
        //and call notifyDataSetChanged
        notifyDataSetChanged();
    }
}

package edu.iit.sat.itmd555.fp.fitnessmanager;

import android.widget.TextView;
import android.app.Fragment;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;

/**
 * Created by lisiling on 4/21/16.
 */
public class MyFragment extends Fragment {

    public MyFragment() {

    }

    private String content;

//    public  MyFragment(String content) {
//        this.content = content;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_content,container,false);
        TextView txt_content = (TextView) view.findViewById(R.id.txt_content);
        txt_content.setText(content);
        return view;
    }
}

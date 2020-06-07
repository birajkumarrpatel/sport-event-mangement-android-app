package com.example.dell.teamup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static android.content.Context.MIDI_SERVICE;
import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Dell on 3/29/2018.
 */

public class Organizer_TabFragment5 extends Fragment{
    View myView;
    Context c;
    Button logout;
    String [] titles={"Add Results","Add Statistics","Add Photos","Logout"};
    int [] images={R.drawable.ic_reorder_black_24dp,R.drawable.ic_assessment_black_24dp,R.drawable.ic_photo_library_black_24dp,R.drawable.ic_keyboard_tab_black_24dp};
    ListView lv;
    Session session;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.organizer_tabfragment5, container, false);
        lv = (ListView)myView.findViewById(R.id.idListView);
        Context c = getActivity();
        session = new Session(c);
        Myadapter myadapter = new Myadapter(getActivity(),titles,images);
        lv.setAdapter(myadapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i== 0)
                {
                    Intent i1 = new Intent(getContext(),Results.class);
                    startActivity(i1);
                }
                else if(i==1)
                {
                    Intent i2 = new Intent(getContext(),Statitics.class);
                    startActivity(i2);
                }
                else if(i==2)
                {
                    Intent i3 = new Intent(getContext(),Photoes.class);
                    startActivity(i3);
                }
                else if(i==3)
                {
                    session.logout();
                    Intent i4 = new Intent(getContext(),MainActivity.class);
                    startActivity(i4);
                }

            }
        });
        return myView;

    }
}

class Myadapter extends ArrayAdapter {
    int [] imagearray;
    String [] titleArray;
    public Myadapter(Context context, String[] titles1, int [] img1){
        super(context,R.layout.example_cuslistview_row,R.id.idTitle,titles1);
        this.imagearray = img1;
        this.titleArray = titles1;
    }
    @NonNull
    @Override
    public View getView(int position,View convertView,ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.example_cuslistview_row,parent,false);
        ImageView myImage = (ImageView)row.findViewById(R.id.idPic);
        TextView myTitle = (TextView)row.findViewById(R.id.idTitle);
        myImage.setImageResource(imagearray[position]);
        myTitle.setText(titleArray[position]);
        myTitle.setTextColor(getContext().getResources().getColor(R.color.seagreen));
        return row;
    }
}

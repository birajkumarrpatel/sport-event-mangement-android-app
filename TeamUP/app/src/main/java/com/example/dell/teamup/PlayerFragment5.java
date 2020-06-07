package com.example.dell.teamup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Dell on 3/29/2018.
 */

public class PlayerFragment5 extends Fragment {
    View myView;
    Context c;
    Button logout;
    String [] titles={"Edit Profile","Show Results","Photos","Logout"};
    int [] images={R.drawable.ic_reorder_black_24dp,R.drawable.ic_assessment_black_24dp,R.drawable.ic_photo_library_black_24dp,R.drawable.ic_keyboard_tab_black_24dp};
    ListView lv;
    Session1 session1;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.player_fragment5, container, false);
        lv = (ListView)myView.findViewById(R.id.idListView1);
        Context c = getActivity();
        session1 = new Session1(c);
        Myadapter myadapter = new Myadapter(getActivity(),titles,images);
        lv.setAdapter(myadapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i== 0)
                {
                    Intent i1 = new Intent(getContext(),EditProfile.class);
                    startActivity(i1);
                }
                else if(i==1)
                {
                    Intent i2 = new Intent(getContext(),ShowResults.class);
                    startActivity(i2);
                }
                else if(i==2)
                {
                    Intent i3 = new Intent(getContext(),ShowPhotoes.class);
                    startActivity(i3);
                }
                else if(i==3)
                {
                    session1.logout();
                    Intent i4 = new Intent(getContext(),MainActivity.class);
                    startActivity(i4);
                }

            }
        });
        return myView;
    }
}
class Myadapter1 extends ArrayAdapter {
    int [] imagearray;
    String [] titleArray;
    public Myadapter1(Context context, String[] titles1, int [] img1){
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
        return row;
    }
}


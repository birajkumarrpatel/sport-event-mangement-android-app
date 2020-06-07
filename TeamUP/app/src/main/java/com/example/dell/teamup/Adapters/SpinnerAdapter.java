package com.example.dell.teamup.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dell.teamup.DataObject;
import com.example.dell.teamup.R;

import java.util.List;

/**
 * Created by Dell on 4/1/2018.
 */

public class SpinnerAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private List<DataObject> listData;
    private Context context;
    public SpinnerAdapter(Context context, List<DataObject> listData) {
        this.context = context;
        layoutInflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.listData = listData;
        Log.d("size",String.valueOf(listData.size()));
    }
    @Override
    public int getCount() {
        return listData.size();
    }
    @Override
    public Object getItem(int position) {
        return (DataObject)listData.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder spinnerHolder;
        if(convertView == null){
            spinnerHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.spinner_layout, parent, false);
            spinnerHolder.spinnerItemList = (TextView)convertView.findViewById(R.id.spinner_list_item);
            spinnerHolder.spinnerItemList.setTextColor(context.getResources().getColor(R.color.seagreen));
            convertView.setTag(spinnerHolder);
        }else{
            spinnerHolder = (ViewHolder)convertView.getTag();
        }
        spinnerHolder.spinnerItemList.setText(listData.get(position).getName());
        Log.d("setspin",listData.get(position).getName());
        return convertView;
    }
    class ViewHolder {
        TextView spinnerItemList;
    }

}

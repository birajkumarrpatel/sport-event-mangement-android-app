package com.example.dell.teamup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Dell on 4/2/2018.
 */

public class SpinnerAdapter1 extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private List<DataObject1> listData;
    private Context context;
    public SpinnerAdapter1(Context context, List<DataObject1> listData) {
        this.context = context;
        layoutInflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.listData = listData;
    }

    public int getCount() {
        return listData.size();
    }

    public Object getItem(int position) {
        return (DataObject1)listData.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        SpinnerAdapter1.ViewHolder spinnerHolder;
        if(convertView == null){
            spinnerHolder = new SpinnerAdapter1.ViewHolder();
            convertView = layoutInflater.inflate(R.layout.spinner_layout, parent, false);
            spinnerHolder.spinnerItemList = (TextView)convertView.findViewById(R.id.spinner_list_item);
            spinnerHolder.spinnerItemList.setTextColor(context.getResources().getColor(R.color.seagreen));
            convertView.setTag(spinnerHolder);
        }else{
            spinnerHolder = (SpinnerAdapter1.ViewHolder)convertView.getTag();
        }
        spinnerHolder.spinnerItemList.setText(listData.get(position).getName());
        return convertView;
    }
    class ViewHolder{
        TextView spinnerItemList;
    }
}

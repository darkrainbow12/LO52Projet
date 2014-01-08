package com.utbm.domotiquekiller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.utbm.domotiquekiller.entity.Sensor;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 
public class LazyAdapter extends BaseAdapter {
 
    private Activity activity;
    List<Sensor> sensors;
    private static LayoutInflater inflater=null;
 
    public LazyAdapter(Activity a, List<Sensor> s) {
        activity = a;
        sensors = s;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
 

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.list_row, null);
 
        TextView title = (TextView)vi.findViewById(R.id.title); // title
        TextView actual = (TextView)vi.findViewById(R.id.actual); 
        TextView minute = (TextView)vi.findViewById(R.id.minute); 
        TextView hour = (TextView)vi.findViewById(R.id.hour);
        //ImageView thumb_image=(ImageView)vi.findViewById(R.id.list_image); // thumb image
 
        Sensor sensor = new Sensor();
        sensor = sensors.get(position);
 
        //Setting all values in listview
        title.setText(sensor.getPinValue());
        actual.setText(sensor.getLastValues().get(0).toString());
        minute.setText(sensor.getLastMinutesMean().get(0).toString());
        //hour.setText(sensor.getLastHoursMean().get(0).toString());
      
        return vi;
    }


	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return sensors.size();
	}


	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}
}
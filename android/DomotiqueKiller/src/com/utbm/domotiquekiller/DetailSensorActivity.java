package com.utbm.domotiquekiller;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.utbm.domotiquekiller.entity.Sensor;


public class DetailSensorActivity extends Activity {
    Sensor sensor;
    private ListView listSensors;
    List<Sensor> sensors;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {

    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_detail_sensor);
    	//getActionBar().setDisplayHomeAsUpEnabled(true);
    	
    	listSensors = (ListView) findViewById(R.id.listValues);
    	sensors = new ArrayList<Sensor>();
    	
    	sensor = getIntent().getParcelableExtra("sensor");
    	sensors.add(sensor);
       
        listSensors.setAdapter(new ArrayAdapter<Sensor>(this, android.R.layout.simple_list_item_1, android.R.id.text1, sensors));
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.room, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            //case R.id.action_settings:
                //return true;
        case android.R.id.home:
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
       
        return super.onOptionsItemSelected(item);
    }
    
}

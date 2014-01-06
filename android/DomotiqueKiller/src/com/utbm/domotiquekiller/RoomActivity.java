package com.utbm.domotiquekiller;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.utbm.domotiquekiller.entity.Room;
import com.utbm.domotiquekiller.entity.Sensor;


public class RoomActivity extends Activity {
    Room room;
    private ListView listSensors;
    List<Sensor> sensors;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        
        room = getIntent().getParcelableExtra("room");
        
        setTitle("Room : " + room.getRoomName());
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        
        
    	
    	List<String> sensorsName = new ArrayList<String>();

        listSensors = (ListView) findViewById(R.id.listSensors);
        sensors = room.getSensors();
        
        //Get only the names of the room for the firstList in screen
        for (Sensor sensor : sensors) {
			
        	String tmp = sensor.getPinValue();
        	sensorsName.add(tmp);
        
		}
        
        
        listSensors.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, sensorsName));
        
        listSensors.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent sensorIntent = new Intent(RoomActivity.this,DetailSensorActivity.class);
                
                Sensor temp = sensors.get(i);
                
                sensorIntent.putExtra("sensor",temp);
                startActivity(sensorIntent);
            }
        });
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

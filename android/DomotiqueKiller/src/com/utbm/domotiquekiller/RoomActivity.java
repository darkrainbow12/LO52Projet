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
    LazyAdapter adapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        listSensors = (ListView) findViewById(R.id.listSensors);
        
        room = getIntent().getParcelableExtra("room");
        
        sensors = room.getSensors();
        
        setTitle(convertToName(room.getRoomName()));
        
        //Enable navigation back to parent activity
        getActionBar().setDisplayHomeAsUpEnabled(true);
        
        
        //Custom Adapter for the list of sensor
        adapter=new LazyAdapter(this, sensors);
        listSensors.setAdapter(adapter);
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
    
	private String convertToName(String idRoom) {
		
		String[][] conversion = {{"1", "Cuisine"},{"2","Salon"},{"3", "Salle de bain"}, {"4", "Chambre 1"},{"5", "Chambre 2"}};
		
		for (int i = 0; i < 5; i++) {
			
			for (int j = 0; j < 1; j++) {
		        if (conversion[i][j].equals(idRoom))
		            return conversion[i][j+1];
			}
		}
		
		return null;
	}
    
}

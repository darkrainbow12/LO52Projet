package com.utbm.domotiquekiller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.utbm.domotiquekiller.entity.Room;
import com.utbm.domotiquekiller.json.JsonDownloader;

import java.util.List;

public class MainActivity extends Activity implements JsonDownloader.Callback{


    private ListView listRooms;
    List<Room> rooms;
    @Override
    public void onDataReceived(List<Room> data) {
        rooms=data;
        listRooms.setAdapter(new ArrayAdapter<Room>(this, android.R.layout.simple_list_item_1, android.R.id.text1, data));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listRooms = (ListView) findViewById(R.id.listRooms);
        listRooms.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent roomIntent = new Intent(MainActivity.this,RoomActivity.class);
                roomIntent.putExtra("room",rooms.get(i));
                startActivity(roomIntent);
            }
        });

        JsonDownloader downloader = new JsonDownloader(MainActivity.this);
        downloader.execute();


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_refresh:
                JsonDownloader downloader = new JsonDownloader(MainActivity.this);
                downloader.execute();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}

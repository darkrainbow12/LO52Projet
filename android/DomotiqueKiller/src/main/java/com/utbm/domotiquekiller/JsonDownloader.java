

package com.utbm.domotiquekiller;

import android.os.AsyncTask;
import android.util.Log;

import com.utbm.domotiquekiller.Room;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by Matt on 21/07/13.
 */
public class JsonDownloader extends AsyncTask<Void , Void, List<Room>> {
    private static String url ="http://www.add-besancon.fr/test/jsonexample.txt";


    @Override
    protected List<Room> doInBackground(Void... voids) {
        String urlDisplay = url;
        List<Room> mIcon11;
        mIcon11 = getJsonFromURL(urlDisplay);
        return mIcon11;
    }



    public List<Room> getJsonFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            List<Room> rooms = JsonInterpreter.readJsonStream(input);
            return rooms;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
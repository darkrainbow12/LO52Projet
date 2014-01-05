

package com.utbm.domotiquekiller.json;

import android.os.AsyncTask;

import com.utbm.domotiquekiller.entity.Room;

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
    List<Room> rooms;


    public static interface Callback
    {
        public void onDataReceived(List<Room> data);
    }
    Callback callback;

    public JsonDownloader(Callback cb){
        this.callback=cb;
    }

    @Override
    protected List<Room> doInBackground(Void... voids) {
        String urlDisplay = url;

        InputStream input = getJsonFromURL(urlDisplay);
        try {
            rooms= JsonInterpreter.readJsonStream(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    @Override
    public void onPostExecute(List<Room> data)
    {
        this.callback.onDataReceived(data);
    }

    public InputStream getJsonFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();

            return input;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
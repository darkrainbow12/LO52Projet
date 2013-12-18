package com.utbm.domotiquekiller;

import android.os.Message;
import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by matthieu on 13/12/13.
 */
public  class JsonInterpreter {
    public static List readJsonStream(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        reader.setLenient(true);
        try {
            return readRoomsArray(reader);
        }
        finally {
            reader.close();
        }
    }

    private static List readRoomsArray(JsonReader reader) throws IOException {
        List rooms = new ArrayList();

        reader.beginObject();
        while (reader.hasNext()) {
            Room room = readRoom(reader);
            rooms.add(room);
            Log.d("ROOM", room.toString());
        }
        reader.endObject();
        return rooms;
    }

    private static List readSensorArray(JsonReader reader) throws IOException {
        List sensors = new ArrayList();

        reader.beginObject();
        while (reader.hasNext()) {
            Sensor sensor = readSensor(reader);
            sensors.add(sensor);
        }
        reader.endObject();
        return sensors;
    }

    private static Room readRoom(JsonReader reader) throws IOException {
        Room room = new Room();
        List sensors = new ArrayList();
        reader.beginObject();
        reader.nextName();
        sensors = readSensorArray(reader);
        reader.endObject();

        room.setSensors(sensors);
        return room;
    }

    private static Sensor readSensor(JsonReader reader) throws IOException {

        List lastValues = null;
        List lastMinutesMean = null;
        List lastHoursMean = null;
        Sensor sensor = new Sensor();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("lastValues")) {
                lastValues = readIntArray(reader);
            } else if (name.equals("lastMinutesMean")) {
                lastMinutesMean = readIntArray(reader);
            } else if (name.equals("lastHoursMean")) {
                lastHoursMean = readIntArray(reader);
            } else {
                reader.skipValue();
            }
        }
        sensor.setLastHoursMean(lastHoursMean);
        sensor.setLastMinutesMean(lastHoursMean);
        sensor.setLastValues(lastValues);
        return sensor ;
    }

    private static List readIntArray(JsonReader reader) throws IOException {
        List integers = new ArrayList();

        reader.beginArray();
        while (reader.hasNext()) {
            integers.add(reader.nextInt());
        }
        reader.endArray();
        return integers;
    }

}

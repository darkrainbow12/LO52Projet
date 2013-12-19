package com.utbm.domotiquekiller.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matthieu on 16/12/13.
 */
public class Room implements Parcelable{
    List<Sensor> sensors;

    @Override
    public String toString() {
        return "Room{" +
                "roomName='" + roomName + '\'' +
                ", sensors=" + sensors +
                '}';
    }

    String roomName;

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public List<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(List<Sensor> sensors) {
        this.sensors = sensors;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel destination, int flags) {
        destination.writeString(roomName);
        destination.writeTypedList(sensors);
    }

    public static final Parcelable.Creator<Room> CREATOR= new Parcelable.Creator<Room>() {

        @Override
        public Room createFromParcel(Parcel source) {
            Room room = new Room();
            room.setRoomName(source.readString());

            List<Sensor> sensors = new ArrayList<Sensor>();
            source.readTypedList(sensors,Sensor.CREATOR);
            room.setSensors(sensors);
            return room;  //using parcelable constructor
        }
        @Override
        public Room[] newArray(int size) {
            return new Room[size];
        }


};
}

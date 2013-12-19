package com.utbm.domotiquekiller;

import java.util.List;

/**
 * Created by matthieu on 16/12/13.
 */
public class Room {
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
}

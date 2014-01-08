package com.utbm.domotiquekiller.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matthieu on 13/12/13.
 */
public class Sensor implements Parcelable{


    ArrayList lastValues = null;
    ArrayList lastMinutesMean = null;
    ArrayList lastHoursMean = null;
    String pinValue;

    public String getPinValue() {
        return pinValue;
    }

    public void setPinValue(String pinValue) {
        this.pinValue = pinValue;
    }



    @Override
    public String toString() {
        return "Sensor{" +
                "pinNumber="+pinValue+
                ", lastValues=" + lastValues +
                ", lastMinutesMean=" + lastMinutesMean +
                ", lastHoursMean=" + lastHoursMean +
                '}';
    }



    public ArrayList getLastValues() {
        return lastValues;
    }

    public void setLastValues(ArrayList lastValues) {
        this.lastValues = lastValues;
    }

    public ArrayList getLastMinutesMean() {
        return lastMinutesMean;
    }

    public void setLastMinutesMean(ArrayList lastMinutesMean) {
        this.lastMinutesMean = lastMinutesMean;
    }

    public ArrayList getLastHoursMean() {
        return lastHoursMean;
    }

    public void setLastHoursMean(ArrayList lastHoursMean) {
        this.lastHoursMean = lastHoursMean;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeSerializable(lastValues);
        out.writeSerializable(lastMinutesMean);
        out.writeSerializable(lastHoursMean);
        out.writeString(pinValue);
    }

    public static final Parcelable.Creator<Sensor> CREATOR = new Parcelable.Creator<Sensor>() {

        public Sensor createFromParcel(Parcel in) {
            Sensor sensor = new Sensor();
            sensor.setLastValues((ArrayList<Integer>) in.readSerializable());
            sensor.setLastMinutesMean((ArrayList<Integer>) in.readSerializable());
            sensor.setLastHoursMean((ArrayList<Integer>) in.readSerializable());
            sensor.setPinValue(in.readString());
            return sensor;
        }

        public Sensor[] newArray(int size) {
            return new Sensor[size];
        }
    };
}

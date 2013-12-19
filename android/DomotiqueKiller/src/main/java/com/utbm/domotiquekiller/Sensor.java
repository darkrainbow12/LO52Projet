package com.utbm.domotiquekiller;

import java.util.List;

/**
 * Created by matthieu on 13/12/13.
 */
public class Sensor {


    List lastValues = null;
    List lastMinutesMean = null;
    List lastHoursMean = null;


    public String getPinValue() {
        return pinValue;
    }

    public void setPinValue(String pinValue) {
        this.pinValue = pinValue;
    }

    String pinValue;

    @Override
    public String toString() {
        return "Sensor{" +
                "pinNumber="+pinValue+
                ", lastValues=" + lastValues +
                ", lastMinutesMean=" + lastMinutesMean +
                ", lastHoursMean=" + lastHoursMean +
                '}';
    }



    public List getLastValues() {
        return lastValues;
    }

    public void setLastValues(List lastValues) {
        this.lastValues = lastValues;
    }

    public List getLastMinutesMean() {
        return lastMinutesMean;
    }

    public void setLastMinutesMean(List lastMinutesMean) {
        this.lastMinutesMean = lastMinutesMean;
    }

    public List getLastHoursMean() {
        return lastHoursMean;
    }

    public void setLastHoursMean(List lastHoursMean) {
        this.lastHoursMean = lastHoursMean;
    }

}

package com.boardinglabs.taskficien.bean;

import java.util.Date;

/**
 * Created by rizkygustinaldi on 11/29/14.
 */
public class Event {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    private String name;
    private Date startTime;
    private Date endTime;
    private int type;

    public static class EventType{
        public static int SCHEDULE = 1;
        public static int TASK = 2;
    }
}

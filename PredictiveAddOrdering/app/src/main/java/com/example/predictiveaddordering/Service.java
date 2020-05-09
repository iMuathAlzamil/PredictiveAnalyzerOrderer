package com.example.predictiveaddordering;

public class Service extends Item
{
    private String startTime, endTime;

    public Service() {
        super();
        setStartTime("");
        setEndTime("");
    }

    public Service(int u_id, int c_id, String nm, String desc, String stime, String etime, double pr) {
        super(u_id, c_id, nm, desc, pr);
        setStartTime(stime);
        setEndTime(etime);
    }

    public void setStartTime(String time) {
        startTime = time;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setEndTime(String time) {
        endTime = time;
    }

    public String getEndTime() {
        return endTime;
    }
}

package com.example.predictiveorderer;

public class FDService extends Product
{
    private int startHour;
    private int endHour;

    public FDService()
    {
        super();
        startHour = 0;
        endHour = 0;
    }

    public FDService(int id, String nm, double ct, String desc, int sHour, int eHour)
    {
        super(id, nm, ct, desc);
        startHour = sHour;
        endHour = eHour;
    }

    public void setStartHour(int sHour)
    {
        startHour = sHour;
    }

    public int getStartHour()
    {
        return startHour;
    }

    public void setEndHour(int eHour)
    {
        endHour = eHour;
    }

    public int getEndHour()
    {
        return endHour;
    }
}

package com.example.predictiveorderer;

import android.util.Pair;

import java.util.*;

public abstract class Agent
{
    // private DBManagerBB db;
    protected Queue<Pair<Integer, String>> msgQueue;

    public Agent()
    {
        msgQueue = new LinkedList<Pair<Integer, String>>();
    }

    public void receive(int id, String msg)
    {
        msgQueue.add(new Pair<Integer, String>(id, msg));
    }

    public abstract Pair<Integer, String> act();
    public abstract void processHelper();
}

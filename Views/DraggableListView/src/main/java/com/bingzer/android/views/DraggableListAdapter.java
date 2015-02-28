package com.bingzer.android.views;

import android.widget.BaseAdapter;

import java.util.List;

public abstract class DraggableListAdapter<T> extends BaseAdapter {

    public abstract List<T> getObjectList();

    public abstract void onSwapped(T object, int newIndex);
}

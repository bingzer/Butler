package com.bingzer.android.views.listeners;

import android.support.v7.widget.SearchView;

public abstract class SimpleOnQueryTextListener implements SearchView.OnQueryTextListener {
    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}

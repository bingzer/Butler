package com.bingzer.android.views.listeners;

import android.support.v7.widget.SearchView;

public abstract class SimpleOnSuggestionListener implements SearchView.OnSuggestionListener {
    @Override
    public boolean onSuggestionSelect(int position) {
        return true;
    }
}

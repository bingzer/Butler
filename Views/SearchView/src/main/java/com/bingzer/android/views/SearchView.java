package com.bingzer.android.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.bingzer.android.Res;
import com.bingzer.android.Stringify;
import com.bingzer.android.views.listeners.OnSearchRequestedListener;
import com.bingzer.android.views.listeners.SimpleOnQueryTextListener;
import com.bingzer.android.views.listeners.SimpleOnSuggestionListener;
import com.bingzer.android.views.searchview.R;
import com.devspark.robototextview.util.RobotoTypefaceManager;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is a total hack but it works
 */
@SuppressLint("ViewConstructor")
public class SearchView extends android.support.v7.widget.SearchView {

    private static String searchQuery;
    private OnSearchRequestedListener listener;
    private final AutoCompleteTextView textView;
    private final List<String> historyList = new ArrayList<>();

    ///////////////////////////////////////////////////////////////////////////////////////////

    public SearchView(Context context, MenuItem item) {
        super(context);
        initializeView();
        textView = initializeTextView();
        initializeMenuItem(item);
    }

    public void setOnSearchRequestedListener(OnSearchRequestedListener listener){
        this.listener = listener;
    }

    public void setHistoryList(List<String> historyList){
        this.historyList.addAll(historyList);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.bv_spinner_dropdown_item, historyList);
        textView.setAdapter(adapter);
    }

    /////////////////////////////////////////////////////////////////////////////////////////

    private boolean doSearch(final String query){
        searchQuery = query;
        if(listener != null){
            listener.onSearchRequested(searchQuery);
            return postDelayedSearchText();
        }
        return true;
    }

    private void initializeView(){
        setQueryHint(Res.getString(android.R.string.search_go));
        setIconifiedByDefault(true);
        setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        setSelected(true);
        setActivated(true);
        setOnQueryTextListener(new SimpleOnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return doSearch(query);
            }
        });
        setOnSuggestionListener(new SimpleOnSuggestionListener() {
            @Override
            public boolean onSuggestionClick(int position) {
                String searchTerm = "" + textView.getAdapter().getItem(position);
                return doSearch(searchTerm);
            }
        });
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    private AutoCompleteTextView initializeTextView(){
        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.search_src_text);
        textView.setTypeface(RobotoTypefaceManager.obtainTypeface(getContext(), RobotoTypefaceManager.Typeface.ROBOTO_REGULAR));
        ((View)textView.getParent()).setBackgroundResource(R.drawable.bv_textfield_activated_holo_light);
        textView.setHintTextColor(Res.getColor(R.color.holo_white_alpha));
        textView.setTextColor(Res.getColor(R.color.holo_white));
        setHistoryList(historyList);
        return textView;
    }

    private MenuItem initializeMenuItem(MenuItem menuItem){
        menuItem.setActionView(this);
        menuItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override public boolean onMenuItemActionExpand(MenuItem menuItem) {
                return postDelayedSearchText();
            }
            @Override public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                if(listener != null) {
                    if(listener.onSearchClosed()) searchQuery = null;
                }
                return true;
            }
        });
        return menuItem;
    }

    private boolean postDelayedSearchText(){
        textView.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!Stringify.isNullOrEmpty(searchQuery)) {
                    textView.setText(searchQuery);
                    InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(textView.getWindowToken(), 0);
                }
                textView.dismissDropDown();
            }
        }, 100);
        return true;
    }
}

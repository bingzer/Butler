package com.bingzer.android.patterns.uicommand.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.bingzer.android.patterns.UICommand;

/**
 * Base Fragments
 * Created by Ricky Tobing on 2/20/2015.
 */
public class BaseFragment extends Fragment implements UICommand.Listener{

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        UICommand.addCommandListener(this);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        UICommand.removeCommandListener(this);
        super.onDestroyView();
    }


    @Override
    public void onCommandInvoked(int commandType, Object... args) {
        // placeholder
    }
}

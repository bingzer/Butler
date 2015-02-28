package com.bingzer.android.patterns.uicommand.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;

import com.bingzer.android.patterns.UICommand;

/**
 * Base ActionBarActivity
 * Created by Ricky on 2/20/2015.
 */
public class BaseActionBarActivity extends ActionBarActivity implements UICommand.Listener {

    @Override
    protected void onResume() {
        super.onResume();
        UICommand.addCommandListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        UICommand.removeCommandListener(this);
    }

    /**
     * Convenient method to replace fragment at placeholderId
     * @param placeholderId the placeholder id
     * @param fragmentClazz the fragment class
     */
    protected void replaceFragment(int placeholderId, Class<? extends Fragment> fragmentClazz){
        Fragment fragment = Fragment.instantiate(this, fragmentClazz.getName());
        replaceFragment(placeholderId, fragment);
    }

    /**
     * Convenient method to replace fragment at placeholderId
     * @param placeholderId the placeholder id
     * @param fragmentClazz the fragment class
     * @param args optional bundle args
     */
    protected void replaceFragment(int placeholderId, Class<? extends Fragment> fragmentClazz, Bundle args){
        Fragment fragment = Fragment.instantiate(this, fragmentClazz.getName(), args);
        replaceFragment(placeholderId, fragment);
    }

    /**
     * Convenient method to replace fragment at placeholderId
     */
    protected void replaceFragment(int placeholderId, Fragment fragment){
        @SuppressLint("CommitTransaction")
        FragmentTransaction ft = getSupportFragmentManager()
                .beginTransaction()
                .replace(placeholderId, fragment);
        ft.commitAllowingStateLoss();
    }

    @Override
    public void onCommandInvoked(int commandType, Object... args) {
        // placeholder
    }
}

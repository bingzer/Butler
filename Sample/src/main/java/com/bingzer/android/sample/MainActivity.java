package com.bingzer.android.sample;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.bingzer.android.eula.EulaFragment;
import com.bingzer.android.eula.OnEulaAgreedTo;

public class MainActivity extends FragmentActivity implements OnEulaAgreedTo {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EulaFragment.showDialog(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.item_about){
        }
        else if(item.getItemId() == R.id.item_eula){
            EulaFragment.setEulaAccepted(false);
            EulaFragment.showDialog(this);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onEulaAgreed() {
    }
}

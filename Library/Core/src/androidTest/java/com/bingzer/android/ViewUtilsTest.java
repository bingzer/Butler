package com.bingzer.android;

import android.test.AndroidTestCase;
import android.widget.LinearLayout;
import android.widget.TextView;

import static com.bingzer.android.ViewUtils.findView;

public class ViewUtilsTest extends AndroidTestCase {

    public void testFindView(){
        TextView tv = new TextView(getContext());
        tv.setId(R.id.br_text);
        tv.setText("Hello");

        LinearLayout parent = new LinearLayout(getContext());
        parent.addView(tv);

        TextView textView = findView(parent, R.id.br_text);
        assertNotNull(textView);
        assertEquals("Hello", textView.getText().toString());

    }
}

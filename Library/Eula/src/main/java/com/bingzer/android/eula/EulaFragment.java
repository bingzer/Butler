/**
 * Copyright 2013 Ricky Tobing
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bingzer.android.eula;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bingzer.android.BundleKeys;
import com.bingzer.android.Res;

/**
 * EULA Fragments.
 * With full screen by default
 */
@SuppressWarnings("UnusedDeclaration")
public class EulaFragment extends DialogFragment  {
    static String TAG = "EulaFragment";

    private TextView textView;
    private Button agreeButton;
    private Button disagreeButton;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Holo_Light);
        else setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Light_NoTitleBar_Fullscreen);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.br_fragment_eula, container, false);
        if(root != null){
            textView = initializeTextView(root);
            agreeButton = initializeAgreeButton(root);
            disagreeButton = initializeDisagreeButton(root);
            initializeDialog();
        }

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        textView.postDelayed(new Runnable() {
            @Override
            public void run() {
                String eulaRaw = Res.readRaw(getRawEulaResourceId()).toString();
                String eulaText = eulaRaw.replaceAll("%APP_NAME%", Res.getApplicationName()).replaceAll("%APP_VERSION%", Res.getVersionName());

                textView.setText(Html.fromHtml(eulaRaw));
                agreeButton.setEnabled(true);
            }
        }, 1000);
    }

    protected String getDialogTitle(){
        return "EULA";
    }

    protected String getAgreedText(){
        return "Agreed";
    }

    protected String getDisagreeText(){
        return "Disagree";
    }

    protected int getRawEulaResourceId(){
        return R.raw.eula_html;
    }

    /////////////////////////////////////////////////////////////////////////////

    private Button initializeAgreeButton(View root){
        agreeButton = (Button) root.findViewById(R.id.br_button1);
        agreeButton.setText(getAgreedText());
        agreeButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Res.setPreference(getPreferenceName(), true);
                // dismiss this
                dismiss();

                // go to eula agreed to
                if(getActivity() instanceof OnEulaAgreedTo)
                    ((OnEulaAgreedTo) getActivity()).onEulaAgreed();
            }
        });
        return agreeButton;
    }

    private Button initializeDisagreeButton(View root){
        disagreeButton = (Button) root.findViewById(R.id.br_button);
        disagreeButton.setText(getDisagreeText());
        disagreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        return disagreeButton;
    }

    private TextView initializeTextView(View root){
        textView = (TextView) root.findViewById(R.id.br_text);
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
        return textView;
    }

    private void initializeDialog(){
        getDialog().setTitle(getDialogTitle());
    }

    /////////////////////////////////////////////////////////////////////////////

    public static void showDialog(FragmentActivity fa){
        boolean accepted = Res.getPreference(getPreferenceName(), false);
        if(!accepted){
            EulaFragment fragment = (EulaFragment) EulaFragment.instantiate(fa, EulaFragment.class.getName());
            fragment.show(fa.getSupportFragmentManager(), TAG);
        }
        else{
            // go to eula agreed to
            if(fa instanceof OnEulaAgreedTo)
                ((OnEulaAgreedTo) fa).onEulaAgreed();
        }
    }

    public static void setEulaAccepted(boolean accepted){
        Res.setPreference(getPreferenceName(), accepted);
    }

    private static String getPreferenceName(){
        return BundleKeys.BUNDLE_KEY_URI + ":" + Res.getPackageName();
    }

}

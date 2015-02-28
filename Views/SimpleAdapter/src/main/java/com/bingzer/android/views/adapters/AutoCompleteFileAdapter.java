package com.bingzer.android.views.adapters;

import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.bingzer.android.Path;
import com.bingzer.android.Res;
import com.bingzer.android.views.listeners.OnAutoCompleteFileListener;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper for any auto complete text view
 */
public class AutoCompleteFileAdapter extends ArrayAdapter<String> {

    private final static String TAG = "AutoCompleteFileAdapter";
    private final File file;
    private final List<String> autoCompleteList;

    public AutoCompleteFileAdapter(AutoCompleteTextView textView, String file){
        this(textView, file, (String) null);
    }

    public AutoCompleteFileAdapter(AutoCompleteTextView textView, String file, final String assetName){
        this(textView, file, new OnAutoCompleteFileListener() {
            @Override public void onAutoCompleteFileDoesNotExists(File file) {
                if(assetName == null) return;
                try{
                    InputStream input = new BufferedInputStream(Res.getAssets().open(assetName));
                    Path.copyFile(input, file);
                    input.close();
                }
                catch (IOException e){
                    Log.e(TAG, "copyMedicineFileFromAsset", e);
                }
            }
        });
    }

    public AutoCompleteFileAdapter(AutoCompleteTextView textView, String file, OnAutoCompleteFileListener listener){
        super(textView.getContext(), android.R.layout.simple_dropdown_item_1line);
        this.file = initializeFile(file, listener);
        this.autoCompleteList = initializeAutoCompleteList();
        for (String entry : autoCompleteList){
            add(entry);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    public void commitEntry(String entryName){
        if(entryName == null) return;
        boolean exists = false;
        for(String entry : autoCompleteList){
            if(entry != null && entry.equalsIgnoreCase(entryName)){
                exists = true;
                break;
            }
        }
        // if doesn't exists
        if(!exists){
            try{
                FileWriter writer = new FileWriter(file, true);
                writer.append(entryName).append("\n").flush();
                writer.close();
            }
            catch (IOException e){
                Log.e(TAG, "commitEntry()", e);
            }
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    private File initializeFile(String fullpath, OnAutoCompleteFileListener listener){
        File file = new File(fullpath);
        try{
            if(!file.exists()) {
                if(!file.createNewFile()) throw new IOException("Failed to create " + fullpath);
                if (listener != null) listener.onAutoCompleteFileDoesNotExists(file);
            }
        }
        catch (IOException e){
            Log.e(TAG, "initializeFile()", e);
        }
        return file;
    }

    private List<String> initializeAutoCompleteList(){
        List<String> list = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line;
            while((line = br.readLine()) != null){
                String med = line.trim();
                if(med.length() > 0)
                    list.add(med);
            }
        }
        catch (IOException e){
            Log.e(TAG, "initializeAutoCompleteList()", e);
        }

        return list;
    }

}

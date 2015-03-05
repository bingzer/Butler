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
package com.bingzer.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.method.LinkMovementMethod;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * Use this class as such:
 * <pre>
 * // for alert
 * Modal.alert(context, "Message").show(callback);
 * Modal.confirm(context, "Message").show(callback);
 * </pre>
 *
 * @author Ricky Tobing
 */
@SuppressWarnings("UnusedDeclaration")
public final class Modal extends AlertDialog implements DialogInterface.OnClickListener, DialogInterface.OnDismissListener{

    public static int GAP      = 5;
    
    /**
     * The callback
     */
    private Callback callback;

    /**
     * True input dialog is intended
     */
    private View customView = null;
    
    /**
     * Sets auto dismiss after receiving input
     */
    private boolean autoDismiss = true;

    /**
     * Private
     * New Instance of Common dialog
     * 
     */
    protected Modal(Context context){
        super(context);
    }

    /**
     * Build the common dialog
     * 
     */
	protected static Modal buildDialog(Context context){
    	Modal dialog = new Modal(context);
        dialog.setOnDismissListener(dialog);
        return dialog;
    }
	
	public Modal title(int resourceId){
		setTitle(resourceId);
		return this;
	}
	
	public Modal title(CharSequence title){
		if(title != null)
			setTitle(title);
		return this;
	}
	
	public Modal icon(int resourceId){
		setIcon(resourceId);
		return this;
	}
	
	public Modal icon(Drawable drawable){
		setIcon(drawable);
		return this;
	}
	
	public Modal message(int resourceId){
		setMessage(Res.getString(resourceId));
		return this;
	}
	
	public Modal message(CharSequence message){
		setMessage(message);
		return this;
	}
	
	public Modal button(int whichButton, CharSequence text){
		setButton(whichButton, text, this);
		return this;
	}
	
	public Modal button(int whichButton, int resourceId){
		setButton(whichButton, Res.getString(resourceId), this);
		return this;
	}
	
	public Modal view(View view){
		return view(view, GAP, GAP, GAP, GAP);
	}
	
	public Modal view(View view, int left, int top, int right, int bottom){
		setView(view, left, top, right, bottom);
		return this;
	}
	
	public Modal callback(Callback callback){
		this.callback = callback;
		return this;
	}
	
	public Modal auto(boolean autoDismiss){
		this.autoDismiss = autoDismiss;
		return this;
	}
	
	// ---------------- overridden -------------//
	
	/**
	 * If context is an {@link android.app.Activity},
	 * This method is guaranteed to be run in UI Thread
	 */
	@Override
	public void dismiss(){
		if(getContext() instanceof Activity){
			((Activity)getContext()).runOnUiThread(new Runnable(){
				@Override public void run(){
					Modal.super.dismiss();
				}
			});
		}
		else{
			super.dismiss();
		}
	}

	/**
	 * If context is an {@link android.app.Activity},
	 * This method is guaranteed to be run in UI Thread
	 */
	@Override
	public void hide(){
		if(getContext() instanceof Activity){
			((Activity)getContext()).runOnUiThread(new Runnable(){
				@Override public void run(){
					Modal.super.hide();
				}
			});
		}
		else{
			super.hide();
		}
	}

	@Override
	public void setMessage(CharSequence message){
		if(customView instanceof ProgressView){
			((ProgressView)customView).textView.setText(message);
		}
		else{
			super.setMessage(message);
		}
	}

	public static Modal build(Context context){
		return buildDialog(context);
	}

    /**
     * Builds an alert dialog
     * 
     * 
     * 
     */
	public static Modal alert(Context context, int resourceId){
		return alert(context, Res.getString(resourceId));
	}

    /**
     * Builds an alert dialog
     * 
     * 
     * 
     */
    public static Modal alert(Context context, CharSequence message){
        return alert(context, message, null);
    }

    /**
     * Builds an alert dialog
     * 
     * 
     * 
     * 
     */
    public static Modal alert(Context context, CharSequence message, CharSequence title){
        return alert(context, message, title, null);
    }

    /**
     * Builds an alert dialog
     * 
     * 
     * 
     * 
     * 
     */
    public static Modal alert(Context context, CharSequence message, CharSequence title, CharSequence okText){
        Modal dialog = buildDialog(context);
        // -- configuration
        dialog.setMessage(message);
        if(title != null)
            dialog.setTitle(title);
        dialog.setButton(BUTTON_POSITIVE, okText == null ? Res.getString(android.R.string.ok) : okText, dialog);

        // return dialog
        return dialog;
    }


    /**
     * Builds a confirm dialog
     * 
     * 
     * 
     */
    public static Modal confirm(Context context, int resourceId){
        return confirm(context, Res.getString(resourceId));
    }

    /**
     * Builds a confirm dialog
     * 
     * 
     * 
     */
    public static Modal confirm(Context context, CharSequence message){
        return confirm(context, message, null);
    }

    /**
     * Builds a confirm dialog
     * 
     * 
     * 
     * 
     */
    public static Modal confirm(Context context, CharSequence message, CharSequence title){
        return confirm(context, message, title, null, null);
    }

    /**
     * Builds confirm dialog
     * 
     * 
     * 
     */
    public static Modal confirm(Context context,
    							CharSequence message,
    							CharSequence title,
    							CharSequence okText,
    							CharSequence cancelText){
        Modal dialog = buildDialog(context);
        // -- configuration
        dialog.setMessage(message);
        if(title != null)
            dialog.setTitle(title);
        // -- OK Button
        dialog.setButton(BUTTON_POSITIVE,
                         okText == null ? Res.getString(android.R.string.ok) : okText,
                         dialog);
        // -- Cancel Button
        dialog.setButton(BUTTON_NEGATIVE,
                         cancelText == null ? Res.getString(android.R.string.cancel) : cancelText,
                         dialog);

        // return dialog
        return dialog;
    }


    /**
     * Build Question dialog box. Question has 3 choices (YES, NO, CANCEL)
     * 
     * 
     * 
     */
    public static Modal question(Context context, int resourceId){
    	return question(context, Res.getString(resourceId));
    }

    /**
     * Build Question dialog box. Question has 3 choices (YES, NO, CANCEL)
     * 
     * 
     * 
     */
    public static Modal question(Context context, CharSequence message){
        return question(context, message, null);
    }

    /**
     * Build Question dialog box. Question has 3 choices (YES, NO, CANCEL)
     * 
     * 
     * 
     * 
     */
    public static Modal question(Context context, CharSequence message, CharSequence title){
        return question(context, message, title, null, null, null);
    }

    /**
     * Build Question dialog box. Question has 3 chioces (YES, NO, CANCEL)
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     */
    public static Modal question(Context context,
    							CharSequence message,
    							CharSequence title,
    							CharSequence yesText,
    							CharSequence noText,
    							CharSequence cancelText){
        Modal dialog = buildDialog(context);
        // -- configuration
        dialog.setMessage(message);
        if(title != null)
            dialog.setTitle(title);
        // -- Yes Button
        dialog.setButton(BUTTON_POSITIVE,
                         yesText == null ? Res.getString(android.R.string.yes) : yesText,
                         dialog);
        // -- No Button
        dialog.setButton(BUTTON_NEGATIVE,
                         noText == null ? Res.getString(android.R.string.no) : noText,
                         dialog);
        // -- Cancel Button
        dialog.setButton(BUTTON_NEUTRAL,
                         cancelText == null ? Res.getString(android.R.string.cancel) : cancelText,
                         dialog);

        // return dialog
        return dialog;
    }

    public static Modal question(Context context,
								CharSequence message,
								CharSequence title,
								CharSequence yesText,
								CharSequence noText,
								CharSequence cancelText,
								View view){
		Modal dialog = buildDialog(context);
		// -- configuration
		dialog.setView(view, GAP, GAP, GAP, GAP);
		if(title != null)
		dialog.setTitle(title);
		// -- Yes Button
		dialog.setButton(BUTTON_POSITIVE, yesText == null ? Res.getString(android.R.string.yes) : yesText, dialog);
		// -- No Button
		dialog.setButton(BUTTON_NEGATIVE, noText == null ? Res.getString(android.R.string.no) : noText, dialog);
		// -- Cancel Button
		dialog.setButton(BUTTON_NEUTRAL, cancelText == null ? Res.getString(android.R.string.cancel) : cancelText, dialog);

		// return dialog
		return dialog;
	}

    /**
     * Shows default input view
     * 
     * 
     * 
     */
    public static Modal input(Context context, int resourceId){
        return input(context, Res.getString(resourceId));
    }

    /**
     * Shows default input view
     * 
     * 
     * 
     */
    public static Modal input(Context context, CharSequence message){
        return input(context, message, (CharSequence) null);
    }

    /**
     * Shows default input view
     * 
     * 
     * 
     * 
     */
    public static Modal input(Context context, CharSequence message, CharSequence title){
        return input(context, message, title, (CharSequence) null);
    }

    /**
     * Shows default input view
     * 
     * 
     * 
     * 
     */
    public static Modal input(Context context, CharSequence message, CharSequence title, CharSequence initialValue){
        return input(context, message, title, initialValue, null, null);
    }

    /**
     * Shows default input view
     * 
     * 
     * 
     * 
     * 
     * 
     */
    public static Modal input(Context context,
    							CharSequence message,
    							CharSequence title,
    							CharSequence initialValue,
                                CharSequence okText,
                                CharSequence cancelText){
        Modal dialog = buildDialog(context);
        // -- configuration
        dialog.setView(createTextView(dialog, message, initialValue));
        if(title != null)
            dialog.setTitle(title);
        // -- OK Button
        dialog.setButton(BUTTON_POSITIVE,
                         okText == null ? Res.getString(android.R.string.ok) : okText,
                         dialog);
        // -- Cancel Button
        dialog.setButton(BUTTON_NEGATIVE,
                         cancelText == null ? Res.getString(android.R.string.cancel) : cancelText,
                         dialog);

        // return dialog
        return dialog;
    }

    /**
     * Builds an input dialog with custom text view
     * 
     * 
     * 
     * 
     */
    public static Modal input(Context context, TextView inputView){
        return input(context, null, inputView);
    }

    /**
     * Builds an input dialog with custom text view
     * 
     * 
     * 
     * 
     * 
     */
    public static Modal input(Context context, CharSequence message, TextView inputView){
        return input(context, message, null, inputView);
    }

    /**
     * Builds an input dialog with custom text view
     * 
     * 
     * 
     * 
     * 
     */
    public static Modal input(Context context, CharSequence message, CharSequence title, TextView inputView){
        return input(context, message, title, inputView, null, null);
    }

    /**
     * builds an input dialog
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     */
    public static Modal input(Context context,
    							CharSequence message,
    							CharSequence title,
    							TextView inputView,
                                CharSequence okText,
                                CharSequence cancelText){
        Modal dialog = buildDialog(context);
        // -- configuration
        if(message != null)
            dialog.setMessage(message);
        dialog.setView(inputView);
        if(inputView != null)
            dialog.customView = inputView;
        if(title != null)
            dialog.setTitle(title);
        // -- OK Button
        dialog.setButton(BUTTON_POSITIVE,
                         okText == null ? Res.getString(android.R.string.ok) : okText,
                         dialog);
        // -- OK Button
        dialog.setButton(BUTTON_NEGATIVE,
                         cancelText == null ? Res.getString(android.R.string.cancel) : cancelText,
                         dialog);

        // return dialog
        return dialog;
    }


    /**
     * Shows
     * 
     * 
     * 
     */
    public static Modal message(Context context, View customView){
        Modal dialog = buildDialog(context);
        // -- configuration
        dialog.setView(customView, GAP, GAP, GAP, GAP);

        return dialog;
    }

    public static Modal message(Context context, CharSequence title, View customView, CharSequence okText){
        Modal dialog = buildDialog(context);
        // -- configuration
        dialog.setView(customView, GAP, GAP, GAP, GAP);
        if(title != null) dialog.setTitle(title);
        // -- OK Button
        dialog.setButton(BUTTON_POSITIVE,
                         okText == null ? Res.getString(android.R.string.ok) : okText,
                         dialog);

        return dialog;
    }

    public static Modal message(Context context, CharSequence title, View customView, CharSequence okText, CharSequence cancelText){
        Modal dialog = buildDialog(context);
        // -- configuration
        dialog.setView(customView, GAP, GAP, GAP, GAP);
        if(title != null) dialog.setTitle(title);
        // -- OK Button
        dialog.setButton(BUTTON_POSITIVE,
                         okText == null ? Res.getString(android.R.string.ok) : okText,
                         dialog);
        // -- OK Button
        dialog.setButton(BUTTON_NEGATIVE,
                         cancelText == null ? Res.getString(android.R.string.cancel) : cancelText,
                         dialog);

        return dialog;
    }

    public static Modal message(Context context, int resourceId){
    	return message(context, Res.getString(resourceId));
    }

    /**
     * Shows the message dialog.
     * No button
     * 
     * 
     */
    public static Modal message(Context context, CharSequence message){
        return message(context, message, null);
    }

    /**
     * Builds message dialog
     * 
     * 
     * 
     */
    public static Modal message(Context context, CharSequence message, CharSequence title){
        Modal dialog = buildDialog(context);
        // -- configuration
        dialog.setMessage(message);
        if(title != null)
            dialog.setTitle(title);

        return dialog;
    }

    /**
     * Builds a list items dialog.
     * Use InputCallback instead Callback interface
     * 
     * 
     * 
     */
    public static Modal list(Context context, CharSequence... items){
        Modal dialog = buildDialog(context);
        dialog.setView(createListView(dialog, items));

        // return dialog
        return dialog;
    }

    /**
     * Prompts user for multiple choice dialog
     * Use ChoiceCallback interface
     * 
     * 
     * 
     */
    public static Modal choice(Context context, CharSequence... items){
        return choice(context, null, items);
    }

    /**
     * Prompts user for multiple choice dialog
     * Use ChoiceCallback interface
     * 
     * 
     * 
     * 
     */
    public static Modal choice(Context context, CharSequence title, CharSequence... items){
        return choice(context, title, null, null, items);
    }

    /**
     * Prompts user for multiple choice dialog
     * Use ChoiceCallback interface
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     */
    public static Modal choice(Context context, CharSequence title, CharSequence okText, CharSequence cancelText, CharSequence... items){
        Modal dialog = buildDialog(context);
        // -- configuration
        if(title != null)
            dialog.setTitle(title);
        dialog.setView(createChoiceView(dialog, items));
        // -- OK Button
        dialog.setButton(BUTTON_POSITIVE,
                         okText == null ? Res.getString(android.R.string.ok) : okText,
                         dialog);
        // -- Cancel Button
        dialog.setButton(BUTTON_NEGATIVE,
                         cancelText == null ? Res.getString(android.R.string.cancel) : cancelText,
                         dialog);

        // return dialog
        return dialog;
    }

    public static Modal loading(Context context){
    	return loading(context, null);
    }

    public static Modal loading(Context context, CharSequence title){
    	return loading(context, title, null);
    }

    /**
     * Show an indetermined loading
     * 
     * 
     */
    public static Modal loading(Context context, CharSequence title, CharSequence message){
    	Modal dialog = buildDialog(context);
    	if(title != null)
    		dialog.setTitle(title);
    	View view = createLoadingView(dialog, message);
    	dialog.customView = view;
    	dialog.setView(view);
    	return dialog;
    }

    /**
     * Return the custom view
     * 
     */
    public View getCustomView(){
        return customView;
    }

    /**
     * Sets the callback
     * 
     * 
     */
    public Modal setCallback(Callback callback){
        this.callback = callback;

        return this;
    }

	/**
	 * If context is an {@link android.app.Activity},
	 * This method is guaranteed to be run in UI Thread
	 */
    @Override
    public void show(){
        show(callback);
    }

	/**
	 * If context is an {@link android.app.Activity},
	 * This method is guaranteed to be run in UI Thread
	 * 
	 * 
	 */
    public Modal show(Callback callback){
        if(callback != null) {
        	setCallback(callback);
            
            // -- check for callback
            if(customView != null && (customView instanceof EditText) && !(callback instanceof InputCallback))
                // it has to be input callback
                throw new IllegalArgumentException("When using input() use InputCallback instead of Callback interface");
        }
        
        if(getContext() instanceof Activity){
        	((Activity)getContext()).runOnUiThread(new Runnable(){
        		@Override public void run(){
        	        Modal.super.show();
        	        //if(inputView instanceof EditText) ((EditText)inputView).selectAll();
        	        
        	        // this is to activate URL links if any
        	        TextView messageView = (TextView) findViewById(android.R.id.message);
        	        if(messageView != null){
        	        	messageView.setClickable(true);
        	        	messageView.setMovementMethod(LinkMovementMethod.getInstance());
        	        }
        		}
        	});
        }
        else{
        	super.show();
            //if(inputView instanceof EditText) ((EditText)inputView).selectAll();
            
            // this is to activate URL links if any
            TextView messageView = (TextView) findViewById(android.R.id.message);
            if(messageView != null){
            	messageView.setClickable(true);
            	messageView.setMovementMethod(LinkMovementMethod.getInstance());
            }
        }
        return this;
    }

    /**
     * On clicked
     * 
     * 
     */
    public void onClick(DialogInterface dialogInterface, int whichButton) {
        if(callback != null){
            // -- if input dialog
            if(callback instanceof InputCallback){
            	if(customView != null){
                    Editable input = ((EditText) customView).getText();
                    assert input != null;
            		((InputCallback)callback).onUserInput(whichButton, input.toString());
                }
            	else
            		((InputCallback)callback).onUserInput(whichButton, "");
            }
            // -- if multiple choice dialog
            else if(callback instanceof ChoiceCallback)
            {
                //Integer[] integers = new Integer[0];
                LinkedList<Integer> integers = new LinkedList<Integer>();
                SparseBooleanArray sparse = ((ListView)customView).getCheckedItemPositions();
                assert sparse != null;
                for(int i = 0; i < sparse.size(); i++){
                    if(sparse.get(i))
                        integers.add(i);
                }
                int[] indices = new int[integers.size()];
                for(int i = 0; i < indices.length; i++)
                    indices[i] = integers.get(i);

                ((ChoiceCallback)callback).onSelected(whichButton, indices);
            }
            // -- if list choice dialog
            else if(callback instanceof ListCallback){
            	if(callback instanceof ListDialogCallback){
                    ((ListDialogCallback)callback).onClicked(whichButton);
            	}
                // nothing
            }
            else if(callback instanceof AlertDialogCallback){
            	((AlertDialogCallback)callback).onClicked();
            }
            else if(callback instanceof DialogCallback){
                ((DialogCallback)callback).onClicked(whichButton);
            }
            // error!
            else
                throw new IllegalArgumentException("Fatal error: " + callback.getClass().getSimpleName());
        }
    }

    /**
     * On Dismiss
     * 
     */
    public void onDismiss(DialogInterface dialogInterface) {
        //onClick(dialogInterface, CANCELED);
    }

    /**
     * Creates input view
     *
     * 
     */
    private static View createTextView(Modal dialog, CharSequence message, CharSequence initialValue){
        LinearLayout layout = new LinearLayout(dialog.getContext());
        layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(5, 2, 5, 2);

        TextView textView = new TextView(dialog.getContext());
        textView.setText(message);
        layout.addView(textView);

        dialog.customView = new EditText(dialog.getContext());
        dialog.customView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        if(initialValue != null) ((EditText)dialog.customView).setText(initialValue);
        ((EditText)dialog.customView).setSingleLine(true);
        layout.addView(dialog.customView);

        return layout;
    }

    /**
     * Creates list view..
     *
     * 
     */
    private static View createListView(final Modal dialog, final CharSequence... items){
        dialog.customView = new ListView(dialog.getContext());
        ((ListView)dialog.customView).setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        ((ListView)dialog.customView).setAdapter(new ListAdapter<CharSequence>(dialog.getContext(), android.R.layout.simple_list_item_1, items));
        ((ListView)dialog.customView).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // --- single choice
                if(dialog.callback != null)
                    ((ListCallback)dialog.callback).onSelected((String) ((ListView)dialog.customView).getAdapter().getItem(position), position);
                // dismiss..
                if(dialog.autoDismiss) dialog.dismiss();
            }
        });
        
        return dialog.customView;
    }
    
    private static class ListAdapter<T> extends ArrayAdapter<T>{
    	
    	public ListAdapter(Context context, int resourceId, T[] objects){
    		super(context, resourceId, objects);
    	}
    	
    	public View getView(int position, View convertView, ViewGroup parent){
    		convertView = super.getView(position, convertView, parent);

    		if(convertView instanceof TextView && android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH){
    			//((TextView)convertView).setTextAppearance(getContext(), R.style. TextAppearance_Sherlock_Widget_PopupMenu);
    		}
    		return convertView;
    	}
    }

    /**
     * Creates list view..
     *
     * 
     */
    private static View createChoiceView(final Modal dialog, final CharSequence... items){
        LinearLayout layout = new LinearLayout(dialog.getContext());
        layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        layout.setOrientation(LinearLayout.VERTICAL);

        dialog.customView = new ListView(dialog.getContext());
        ((ListView)dialog.customView).setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        ((ListView)dialog.customView).setItemsCanFocus(false);
        ((ListView)dialog.customView).setAdapter(new ArrayAdapter<CharSequence>(dialog.getContext(), android.R.layout.simple_list_item_multiple_choice, items));

        layout.addView(dialog.customView);

        return layout;
    }
    
    private static View createLoadingView(final Modal dialog, CharSequence message){
    	ProgressView progressView = new ProgressView(dialog.getContext());
    	if(message != null) progressView.textView.setText(message);
    	else progressView.textView.setText("Loading");
    	return progressView;
    }

    /**
     * Used for input callback
     */
    public static interface InputCallback extends Callback{
        /**
         * Input from user
         * 
         * 
         */
        void onUserInput(int whichButton, CharSequence input);
    }
    
    public static interface AlertDialogCallback extends Callback{
    	void onClicked();
    }

    /**
     * The callback
     */
    public static interface DialogCallback extends Callback{

        /**
         * Which button
         * 
         */
        void onClicked(int whichButton);
    }
    
    /**
     * ListView callback
     */
    public static interface ListCallback extends Callback{
        /**
         * Index that is selected
         * 
         */
        void onSelected(String selection, int index);
    }
    
    /**
     * To be used by listview that has button
     * @author Ricky Tobing
     *
     */
    public static interface ListDialogCallback extends ListCallback, DialogCallback{
    }

    /**
     * Multiple choice callback
     */
    public static interface ChoiceCallback extends Callback{
        /**
         * The indices that are selected.
         * If inidices.length = 0, meaning there's nothing selected
         * 
         */
        void onSelected(int whichButton, int[] indices);
    }

    /**
     * The interface
     */
    public static interface Callback{
        // nothing
    }
    
    
    private static class ProgressView extends RelativeLayout {

    	TextView textView;
    	ProgressBar progressBar;
    	
		ProgressView(Context context) {
			super(context);
			
			LayoutInflater.from(context).inflate(R.layout.br_modal_progress, this);
			textView = (TextView) findViewById(R.id.br_text);
			progressBar = (ProgressBar) findViewById(R.id.br_progress);
		}
    	
    }
}




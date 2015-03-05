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

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * Simple animation builder.
 *
 * <code>
 * <pre>
 * Anim.with(view, R.anim.id).animate(new Runnable(){
 *    public void run(){
 *        // called after animation ends
 *    }
 * });
 * </pre>
 * </code>
 *
 */
@SuppressWarnings("UnusedDeclaration")
public final class Anim {

    public static Anim with(View view, int animResId){
        return new Anim(view, animResId);
    }

    private View view;
    private Animation animation;
    private Animation.AnimationListener listener;
    private Runnable onStart;
    private Runnable onEnd;

    private Anim(View view, int animResId){
        this.view = view;
        this.animation = AnimationUtils.loadAnimation(view.getContext(), animResId);
        this.listener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if(onStart != null) onStart.run();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(onEnd != null) onEnd.run();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
        this.animation.setAnimationListener(listener);
    }

    public Anim onStart(Runnable runnable){
        onStart = runnable;
        return this;
    }

    public Anim onEnd(Runnable runnable){
        onEnd = runnable;
        return this;
    }

    public void animate(Runnable onEnd){
        this.onEnd = onEnd;
        view.startAnimation(animation);
    }

    public void animate(){
        view.startAnimation(animation);
    }
}

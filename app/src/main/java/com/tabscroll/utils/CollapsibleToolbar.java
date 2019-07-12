/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tabscroll.utils;
import android.content.Context;
import android.support.constraint.motion.MotionLayout;
import android.support.design.widget.AppBarLayout;
import android.util.AttributeSet;
import android.view.ViewParent;

public class CollapsibleToolbar extends MotionLayout implements AppBarLayout.OnOffsetChangedListener{
    public CollapsibleToolbar(Context context) {
        super(context);
    }

    public CollapsibleToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CollapsibleToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    (context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
//) : MotionLayout(context, attrs, defStyleAttr), AppBarLayout.OnOffsetChangedListener {
//
//    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
//        progress = -verticalOffset / appBarLayout?.totalScrollRange?.toFloat()!!
//    }
//
//    override fun onAttachedToWindow() {
//        super.onAttachedToWindow()
//        (parent as? AppBarLayout)?.addOnOffsetChangedListener(this)
//    }
//

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (null != appBarLayout) {
            float range = Float.valueOf(appBarLayout.getTotalScrollRange());
            setProgress(-verticalOffset / range);
        }
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewParent parent = getParent();
        if (parent instanceof AppBarLayout) {
            ((AppBarLayout)parent).addOnOffsetChangedListener(this);
        }
    }
}
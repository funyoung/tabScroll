package com.tabscroll.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tabscroll.R;

/**
 * @author yangfeng
 */
public class KeyTriggerActivity extends TabRecyclerActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentId() {
        return R.layout.motion_25_keytrigger;
    }

    @Override
    protected int getRecyclerViewId() {
        return R.id.scrollable;
    }

    @Override
    protected void selectTab(int pos) {
        // do nothing
    }
}

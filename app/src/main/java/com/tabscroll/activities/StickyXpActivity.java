package com.tabscroll.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.tabscroll.R;

public class StickyXpActivity extends AppCompatActivity {
    private static final String LAYOUNT_ID_KEY = "layoutId";

    public static void start(Context context, int layoutId) {
        Intent intent = new Intent(context, StickyXpActivity.class);
        intent.putExtra(LAYOUNT_ID_KEY, layoutId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int layoutId = getIntent().getIntExtra(LAYOUNT_ID_KEY, R.layout.motion_25_keytrigger);
        setContentView(layoutId);
    }
}

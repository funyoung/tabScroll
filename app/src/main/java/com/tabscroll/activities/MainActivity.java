package com.tabscroll.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.tabscroll.R;

/**
 * @author tx
 * @date 2018/7/30
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnScroll;
    private Button btnRecycler;
    private Button btnSticky;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnScroll = findViewById(R.id.scroll);
        btnRecycler = findViewById(R.id.recycler);
        btnSticky = findViewById(R.id.sticky);
        btnScroll.setOnClickListener(this);
        btnRecycler.setOnClickListener(this);
        btnSticky.setOnClickListener(this);

        findViewById(R.id.stickyXp1).setOnClickListener(this);
        findViewById(R.id.stickyXp2).setOnClickListener(this);
        findViewById(R.id.stickyXp3).setOnClickListener(this);
        findViewById(R.id.motion_keyTrigger).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.scroll:
                Intent intent1 = new Intent(this, TabScrollActivity.class);
                startActivity(intent1);
                break;
            case R.id.recycler:
                Intent intent2 = new Intent(this, TabRecyclerActivity.class);
                startActivity(intent2);
                break;
            case R.id.sticky:
                Intent intent3 = new Intent(this, StickyActivity.class);
                startActivity(intent3);
                break;
            case R.id.stickyXp1:
                startCoordinator(R.layout.motion_09_coordinatorlayout);
                break;
            case R.id.stickyXp2:
                startCoordinator(R.layout.motion_10_coordinatorlayout);
                break;
            case R.id.stickyXp3:
                startCoordinator(R.layout.motion_11_coordinatorlayout);
                break;
            case R.id.motion_keyTrigger:
                Intent intent4 = new Intent(this, KeyTriggerActivity.class);
                startActivity(intent4);
                break;
            default:
                break;
        }
    }

    private void startCoordinator(int layoutId) {
        StickyXpActivity.start(this, layoutId);
    }

//    public static void start(Context context, int layoutId) {
//        Intent intent = new Intent(context, StickyXpActivity.class);
//        intent.putExtra("layoutId", layoutId);
//        context.startActivity(intent);
//    }
}

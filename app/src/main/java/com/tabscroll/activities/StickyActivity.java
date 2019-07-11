package com.tabscroll.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.LinearLayout;

import com.tabscroll.AnchorView;
import com.tabscroll.R;

public class StickyActivity extends TabScrollActivity {
    private TabLayout tabLayoutHolder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tabLayoutHolder = findViewById(R.id.tablayout_holder);
        //tablayout设置标签
        fillTab(tabLayoutHolder);
    }

    @Override
    protected void setScrollChanged(int x, int y, int oldx, int oldy) {
        //根据滑动的距离y(不断变化的) 和 holderTabLayout距离父布局顶部的距离(这个距离是固定的)对比，
        //当y < tabLayoutHolder.getTop()时，tabLayoutHolder 仍在屏幕内，realTabLayout不断移动holderTabLayout.getTop()距离，覆盖holderTabLayout
        //当y > tabLayoutHolder.getTop()时，tabLayoutHolder 移出，realTabLayout不断移动y，相对的停留在顶部，看上去是静止的
        int translation = Math.max(y, tabLayoutHolder.getTop());
        setTabLayoutY(translation);
        super.setScrollChanged(x, y, oldx, oldy);
    }

    @Override
    protected void onLayoutUpdated() {
        //一开始让实际的tablayout 移动到 占位的tablayout处，覆盖占位的tablayout
        showTabLayout(tabLayoutHolder.getTop());
        super.onLayoutUpdated();
    }

    @Override
    protected int getContentId() {
        return R.layout.activity_ali_home_more;
    }

    @Override
    protected int getStickyHeight() {
        //需要y减去顶部内容区域的高度(具体看项目的高度，这里demo写死的200dp)
        return 200 * 3;
    }

    @Override
    protected int getTabHeight() {
        return tabLayoutHolder.getHeight();
    }
}

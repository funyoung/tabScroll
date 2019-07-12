package com.tabscroll.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.tabscroll.AnchorView;
import com.tabscroll.MyAdapter;
import com.tabscroll.R;

import java.util.List;

public abstract class BaseTabActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private String[] tabTxt = {"客厅", "卧室", "餐厅", "书房", "阳台", "儿童房"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentId());
        setupTabLayout();
    }

    private void setupTabLayout() {
        tabLayout = findViewById(R.id.tablayout);
        if (null == tabLayout) {
            return;
        }

        fillTab(tabLayout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                selectTab(pos);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    protected abstract int getContentId();
    protected abstract void selectTab(int pos);

    protected final void fillTab(TabLayout tabLayout) {
        if (null == tabLayout) {
            return;
        }

        // tablayout设置标签
        for (int i = 0; i < tabTxt.length; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(tabTxt[i]));
        }
    }

    protected final void fillAnchorViews(List<AnchorView> anchorList, LinearLayout container) {
        for (int i = 0; i < tabTxt.length; i++) {
            AnchorView anchorView = new AnchorView(this);
            anchorView.setAnchorTxt(tabTxt[i]);
            anchorView.setContentTxt(tabTxt[i]);
            anchorList.add(anchorView);
            container.addView(anchorView);
        }
    }

    protected final void setTabLayoutY(int translation) {
        if (null != tabLayout) {
            tabLayout.setTranslationY(translation);
        }
    }

    protected final int getMaxTabIndex() {
        return tabTxt.length - 1;
    }

    protected final void setScrollPosition(int newPos, int positionOffset, boolean updateSelectedText) {
        if (null != tabLayout) {
            tabLayout.setScrollPosition(newPos, positionOffset, updateSelectedText);
        }
    }

    protected int getTabHeight() {
        if (null != tabLayout) {
            return tabLayout.getHeight();
        }
        return 0;
    }

    protected void showTabLayout(int top) {
        if (null != tabLayout) {
            tabLayout.setTranslationY(top);
            tabLayout.setVisibility(View.VISIBLE);
        }
    }

    protected final RecyclerView.Adapter getAdapter(int lastH) {
        return new MyAdapter(this, tabTxt, lastH);
    }
}

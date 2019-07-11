package com.tabscroll.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import com.tabscroll.AnchorView;
import com.tabscroll.CustomScrollView;
import com.tabscroll.R;

import java.util.ArrayList;
import java.util.List;

public class TabScrollActivity extends BaseTabActivity {

    private CustomScrollView contentView;
    private LinearLayout container;
    //内容块view的集合
    private List<AnchorView> anchorList = new ArrayList<>();
    //判读是否是scrollview主动引起的滑动，true-是，false-否，由tablayout引起的
    private boolean isScroll;
    //记录上一次位置，防止在同一内容块里滑动 重复定位到tablayout
    private int lastPos = 0;
    //监听判断最后一个模块的高度，不满一屏时让最后一个模块撑满屏幕
    private ViewTreeObserver.OnGlobalLayoutListener listener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        contentView = findViewById(R.id.scrollView);
        container = findViewById(R.id.container);

        //模拟数据，填充scrollview
        fillAnchorViews(anchorList, container);

        listener = new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                onLayoutUpdated();
            }
        };
        container.getViewTreeObserver().addOnGlobalLayoutListener(listener);

        contentView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //当滑动由scrollview触发时，isScroll 置true
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    isScroll = true;
                }
                return false;
            }
        });

        //监听scrollview滑动
        contentView.setCallbacks(new CustomScrollView.Callbacks() {
            @Override
            public void onScrollChanged(int x, int y, int oldx, int oldy) {
                setScrollChanged(x, y, oldx, oldy);
            }
        });

    }

    @CallSuper
    protected void setScrollChanged(int x, int y, int oldx, int oldy) {
        if (isScroll) {
            for (int i = getMaxTabIndex(); i >= 0; i--) {
                //需要y减去顶部内容区域的高度(具体看项目的高度，这里demo写死的200dp)
                if (y - getStickyHeight() > anchorList.get(i).getTop() - 10) {
                    setScrollPos(i);
                    break;
                }
            }
        }
    }

    @Override
    protected void selectTab(int pos) {
        //点击标签，使scrollview滑动，isScroll置false
        isScroll = false;
        int top = anchorList.get(pos).getTop();
        //同样这里滑动要加上顶部内容区域的高度(这里写死的高度)
        contentView.smoothScrollTo(0, top + getStickyHeight());
    }

    @CallSuper
    protected void onLayoutUpdated() {
        //计算让最后一个view高度撑满屏幕
        int screenH = getScreenHeight();
        int statusBarH = getStatusBarHeight(this);
        int tabH = getTabHeight();
        //计算内容块所在的高度，全屏高度-状态栏高度-tablayout的高度-内容container的padding 16dp
        int lastH = screenH - statusBarH - tabH - 16 * 3;
        AnchorView anchorView = anchorList.get(anchorList.size() - 1);
        //当最后一个view 高度小于内容块高度时，设置其高度撑满
        if (anchorView.getHeight() < lastH) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.height = lastH;
            anchorView.setLayoutParams(params);
        }
        container.getViewTreeObserver().removeOnGlobalLayoutListener(listener);
    }

    @Override
    protected int getContentId() {
        return R.layout.activity_tab_scroll;
    }

    //tablayout对应标签的切换
    protected void setScrollPos(int newPos) {
        if (lastPos != newPos) {
            //该方法不会触发tablayout 的onTabSelected 监听
            setScrollPosition(newPos, 0, true);
        }
        lastPos = newPos;
    }

    protected int getStickyHeight() {
        return 0;
    }

    private int getScreenHeight() {
        return getResources().getDisplayMetrics().heightPixels;
    }

    public int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources()
                .getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}

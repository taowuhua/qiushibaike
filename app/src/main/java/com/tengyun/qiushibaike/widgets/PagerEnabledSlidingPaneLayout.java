package com.tengyun.qiushibaike.widgets;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.SlidingPaneLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/**
 * Created by Administrator on 2015/12/28.
 */
public class PagerEnabledSlidingPaneLayout extends SlidingPaneLayout {

    private float mInitialMotionX;
    private float mInitialMotionY;
    private float mEdgeSlop;

    public PagerEnabledSlidingPaneLayout(Context context) {
        this(context, null);

    }

    public PagerEnabledSlidingPaneLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public PagerEnabledSlidingPaneLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        ViewConfiguration config = ViewConfiguration.get(context);
        mEdgeSlop = config.getScaledEdgeSlop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        switch (MotionEventCompat.getActionMasked(ev)) {
            case MotionEvent.ACTION_DOWN: {
                mInitialMotionX = ev.getX();
                mInitialMotionY = ev.getY();
                break;
            }

            case MotionEvent.ACTION_MOVE: {
                final float x = ev.getX();
                final float y = ev.getY();
                // The user should always be able to "close" the pane, so we only check
                // for child scrollability if the pane is currently closed.
               System.out.println("PagerEnabledSlidingPaneLayout**********"+mInitialMotionX);
               System.out.println("PagerEnabledSlidingPaneLayout-----------------"+mEdgeSlop);
               System.out.println("PagerEnabledSlidingPaneLayout%%%%%%%%%%%%%%%%%%%%"+x);

                if (mInitialMotionX > mEdgeSlop && !isOpen() ) {

                    MotionEvent cancelEvent = MotionEvent.obtain(ev);
                    cancelEvent.setAction(MotionEvent.ACTION_CANCEL);
                    return super.onInterceptTouchEvent(cancelEvent);
                }
            }
        }

        return super.onInterceptTouchEvent(ev);
    }

}

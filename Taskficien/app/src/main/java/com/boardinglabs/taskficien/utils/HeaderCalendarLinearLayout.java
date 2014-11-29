package com.boardinglabs.taskficien.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by rizkygustinaldi on 11/28/14.
 */
public class HeaderCalendarLinearLayout extends LinearLayout {
    public HeaderCalendarLinearLayout(Context context) {
        super(context);
    }

    public HeaderCalendarLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HeaderCalendarLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOrientation(LinearLayout.HORIZONTAL);
        LayoutParams layoutParams = (LayoutParams)getLayoutParams();
        layoutParams.width = LayoutParams.MATCH_PARENT;
        layoutParams.height = LayoutParams.WRAP_CONTENT;
    }
}
